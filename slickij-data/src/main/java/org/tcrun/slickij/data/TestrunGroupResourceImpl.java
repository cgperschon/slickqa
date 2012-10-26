package org.tcrun.slickij.data;

import com.google.code.morphia.query.Query;
import com.google.inject.Inject;
import org.bson.types.ObjectId;
import org.tcrun.slickij.api.TestrunGroupResource;
import org.tcrun.slickij.api.TestrunResource;
import org.tcrun.slickij.api.data.InvalidDataError;
import org.tcrun.slickij.api.data.Testrun;
import org.tcrun.slickij.api.data.TestrunGroup;
import org.tcrun.slickij.api.data.dao.TestrunDAO;
import org.tcrun.slickij.api.data.dao.TestrunGroupDAO;

import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of the TestrunGroupResource REST endpoint
 * User: jcorbett
 * Date: 8/3/12
 * Time: 8:26 PM
 */
public class TestrunGroupResourceImpl implements TestrunGroupResource
{
    private TestrunGroupDAO trgDAO;
    private TestrunResource trApi;

    @Inject
    public TestrunGroupResourceImpl(TestrunGroupDAO trgDAO, TestrunResource trApi)
    {
        this.trgDAO = trgDAO;
        this.trApi = trApi;
    }

    @Override
    public List<TestrunGroup> getMatchingTestrunGroups(Long date, String name)
    {
        Query<TestrunGroup> query = trgDAO.createQuery();
        if(date > 0)
        {
            query.criteria("created").greaterThan(new Date(date));
        }
        if (name != null && !name.equals(""))
        {
            query.criteria("name").equal(name);
        }
        return query.asList();
    }

    @Override
    public TestrunGroup createTestrunGroup(TestrunGroup trgroup)
    {
        try
        {
            trgroup.validate();
        } catch (InvalidDataError e)
        {
            throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
        }
        trgDAO.save(trgroup);
        return trgroup;
    }

    @Override
    public TestrunGroup getTestrunGroup(@PathParam("trgroupid") ObjectId id)
    {
        TestrunGroup retval = null;
        try
        {
            retval = trgDAO.get(id);
        } catch (RuntimeException e)
        {
            throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
        }

        if(retval == null)
            throw new NotFoundError(TestrunGroup.class, id.toString());
        return retval;
    }

    @Override
    public void deleteTestrunGroup(@PathParam("trgroupid") ObjectId id)
    {
        TestrunGroup trgroup = getTestrunGroup(id);
        trgDAO.delete(trgroup);
    }

    @Override
    public TestrunGroup updateTestrunGroup(@PathParam("trgroupid") ObjectId id, TestrunGroup update)
    {
        TestrunGroup trgroup = getTestrunGroup(id);
        if(update.getName() != null)
            trgroup.setName(update.getName());
        if(update.getTestruns() != null)
            trgroup.setTestruns(update.getTestruns());
        trgDAO.save(trgroup);
        return trgroup;
    }

    @Override
    public TestrunGroup addTestrunToTestrunGroup(@PathParam("trgroupid") ObjectId id, @PathParam("testrunid") ObjectId testrunId)
    {
        TestrunGroup trgroup = getTestrunGroup(id);
        Testrun tr = trApi.getTestrun(testrunId.toString());

        trgroup.getTestruns().add(tr);
        trgDAO.save(trgroup);

        return trgroup;
    }

    @Override
    public TestrunGroup removeTestrunFromTestrunGroup(@PathParam("trgroupid") ObjectId id, @PathParam("testrunid") ObjectId testrunId)
    {
        TestrunGroup trgroup = getTestrunGroup(id);
        Testrun tr = trApi.getTestrun(testrunId.toString());

        List<Testrun> runs = trgroup.getTestruns();
        List<Testrun> updated = new ArrayList<Testrun>(runs.size() - 1);
        for(Testrun possible_match : runs)
        {
            if(!possible_match.getId().equals(tr.getId()))
                updated.add(possible_match);
        }
        trgroup.setTestruns(updated);
        trgDAO.save(trgroup);

        return trgroup;
    }
}

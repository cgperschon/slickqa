package org.tcrun.slickij.api.data.testqueries;

import com.google.code.morphia.query.Criteria;
import com.google.code.morphia.query.Query;
import org.bson.types.ObjectId;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.tcrun.slickij.api.data.Testcase;

/**
 *
 * @author jcorbett
 */
public class BelongsToProject implements TestcaseQuery
{
	private ObjectId projectId;

	@Override
	public Criteria toMorphiaQuery(Query<Testcase> original)
	{
		return original.criteria("project.id").equal(projectId);
	}

	@Override
	public String getQueryDescription()
	{
		return "references project with id '" + projectId.toString() + "'.";
	}

    @Override
    public void setQueryDescription(String description)
    {
    }

    @JsonIgnore
    public ObjectId getProjectObjectId()
	{
		return projectId;
	}

    public String getProjectId()
    {
        if(projectId == null)
            return null;
        else
            return projectId.toString();
    }

	public void setProjectId(ObjectId projectId)
	{
		this.projectId = projectId;
	}

    @Override
    public TestcaseQuery createCopy()
    {
        BelongsToProject copy = new BelongsToProject();

        copy.setProjectId(projectId);

        return copy;
    }
}

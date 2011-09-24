package org.tcrun.slickij.api.data;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.PostLoad;
import com.google.code.morphia.annotations.Property;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.bson.types.ObjectId;

/**
 *
 * @author jcorbett
 */
@Entity("configurations")
public class Configuration implements Serializable
{
	@Id
	private ObjectId id;

	@Property
	private String name;

	@Property
	private String configurationType;

	@Property
	private String filename;

	@Property
	private Map<String, String> configurationData;

	public String getId()
	{
		if(id == null)
			return null;
		return id.toString();
	}

	public void setId(ObjectId id)
	{
		this.id = id;
	}

	public Map<String, String> getConfigurationData()
	{
		return configurationData;
	}

	public void setConfigurationData(Map<String, String> configurationData)
	{
		this.configurationData = configurationData;
	}

	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getConfigurationType()
	{
		return configurationType;
	}

	public void setConfigurationType(String type)
	{
		this.configurationType = type;
	}

	@PostLoad
	public void postLoad()
	{
		if(getConfigurationData() == null)
			setConfigurationData(new HashMap<String, String>());
	}

	public void validate() throws InvalidDataError
	{
		if(getName() == null || getName().equals(""))
			throw new InvalidDataError("Configuration", "name", "name cannot be null or empty.");
		if(getConfigurationData() == null)
			setConfigurationData(new HashMap<String, String>());
	}

	public ConfigurationReference createReference()
	{
		ConfigurationReference retval = new ConfigurationReference();
		retval.setConfigId(id);
		retval.setFilename(filename);
		retval.setName(name);
		return retval;
	}
}

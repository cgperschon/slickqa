package org.tcrun.slickij.api.data;

import com.google.code.morphia.annotations.Property;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jcorbett
 */
public class LogEntry implements Serializable
{
	@Property
	private Date entryTime;

	@Property
	private LogLevel level;

	@Property
	private String loggerName;

	@Property
	private String message;

	@Property
	private String exceptionClassName;

	@Property
	private String exceptionMessage;

	@Property
	private List<String> exceptionStackTrace;

	public Date getEntryTime()
	{
		return entryTime;
	}

	public void setEntryTime(Date entryTime)
	{
		this.entryTime = entryTime;
	}

	public String getExceptionClassName()
	{
		return exceptionClassName;
	}

	public void setExceptionClassName(String exceptionClassName)
	{
		this.exceptionClassName = exceptionClassName;
	}

	public String getExceptionMessage()
	{
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage)
	{
		this.exceptionMessage = exceptionMessage;
	}

	public List<String> getExceptionStackTrace()
	{
		return exceptionStackTrace;
	}

	public void setExceptionStackTrace(List<String> exceptionStackTrace)
	{
		this.exceptionStackTrace = exceptionStackTrace;
	}

	public LogLevel getLevel()
	{
		return level;
	}

	public void setLevel(LogLevel level)
	{
		this.level = level;
	}

	public String getLoggerName()
	{
		return loggerName;
	}

	public void setLoggerName(String loggerName)
	{
		this.loggerName = loggerName;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}


}

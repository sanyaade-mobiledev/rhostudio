package rhogenwizard.sdk.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.directory.InvalidAttributesException;

public class GenerateRhoconnectAdapterTask extends RhoconnectTask
{
	public static final String taskTag = "rhoconnect-adapter-gen";
	public static final String sourceName = "source-name";

	@Override
	public String getTag() 
	{
		return taskTag;
	}

	@Override
	public void run() 
	{
		m_taskResult.clear();
		
		try 
		{			
			if (m_taskParams == null || m_taskParams.size() == 0)
				throw new InvalidAttributesException("parameters data is invalid [GenerateRhoconnectAdapterTask]");		
			
			String workDir    = (String) m_taskParams.get(this.workDir);
			String sourceName = (String) m_taskParams.get(this.sourceName);
								
			m_executor.setWorkingDirectory(workDir);
			
			List<String> cmdLine = new ArrayList<String>();
			cmdLine.add(m_rhoConnectExe);
			cmdLine.add("source");
			cmdLine.add(sourceName);
			
			int res = m_executor.runCommand(cmdLine);
			
			Integer resCode = new Integer(res);			
			m_taskResult.put(resTag, resCode);		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}

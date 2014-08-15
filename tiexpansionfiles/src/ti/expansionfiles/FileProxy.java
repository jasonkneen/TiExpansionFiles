package ti.expansionfiles;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.appcelerator.titanium.io.TiBaseFile;
import org.appcelerator.titanium.io.TiFileFactory;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.util.TiFileHelper2;
import org.appcelerator.titanium.util.TiUrl;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiBlob;

import android.content.Context;
import android.net.Uri;

@Kroll.proxy(parentModule=TiexpansionfilesModule.class)
public class FileProxy extends KrollProxy
{
	private static final String TAG = "TiFileProxy";

	protected String path;
	protected TiBaseFile tbf;
	

	public FileProxy(Context context, String path, int mainFileVersion, int patchFileVersion) throws IOException
	{
		this.path = path;
		ZipResourceFile zf = APKExpansionSupport.getAPKExpansionZipFile(context, mainFileVersion, patchFileVersion);
		tbf = new InZipFile(zf, path);
	}

	
	public FileProxy(Context context, String path, String expansionFilePath) throws IOException
	{
		this.path = path;
		// Get a ZipResourceFile representing a specific expansion file
		ZipResourceFile zf = new ZipResourceFile(expansionFilePath);
		tbf = new InZipFile(zf, path);
	}


	public TiBaseFile getBaseFile()
	{
		return tbf;
	}

	@Kroll.method
	public boolean exists()
	{
		return tbf.exists();
	}

	@Kroll.method
	public String extension()
	{
		return tbf.extension();
	}

	@Kroll.getProperty @Kroll.method
	public String[] getDirectoryListing()
	{
		List<String> dl = tbf.getDirectoryListing();
		return dl != null ? dl.toArray(new String[0]) : null;
	}


	@Kroll.getProperty @Kroll.method
	public String getName()
	{
		return tbf.name();
	}

	@Kroll.getProperty @Kroll.method
	public String getNativePath()
	{
		return tbf.nativePath();
	}

	@Kroll.method
	public TiBlob read()
		throws IOException
	{
		return tbf.read();
	}

	@Kroll.method
	public String readLine()
		throws IOException
	{
		return tbf.readLine();
	}

	@Kroll.method
	public boolean rename(String destination)
	{
		return tbf.rename(destination);
	}

	@Kroll.method
	public String resolve()
	{
		return getNativePath();
	}

	@Kroll.getProperty @Kroll.method
	public double getSize()
	{
		return tbf.size();
	}

	@Kroll.method
	public double spaceAvailable()
	{
		return tbf.spaceAvailable();
	}


	@Kroll.method
	public double createTimestamp()
	{
		return tbf.createTimestamp();
	}

	@Kroll.method
	public double modificationTimestamp()
	{
		return tbf.modificationTimestamp();
	}



	public InputStream getInputStream() throws IOException
	{
		return getBaseFile().getInputStream();
	}

	public String toString()
	{
		return "[object TiexpansionfilesFileProxy]";
	}
}
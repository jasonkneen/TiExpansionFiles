package ti.expansionfiles;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.TiBlob;
import org.appcelerator.titanium.io.TiBaseFile;

import ti.expansionfiles.ZipResourceFile.ZipEntryRO;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;

/**
 * An extension of {@link TiBaseFile}, used for representing a file on the device's true file system. 
 * This differentiates it from TiResourceFile, which represents a file inside the application's resource bundle.
 */
public class InZipFile extends TiBaseFile
{
	private static final String TAG = "TiFile";
	
	private final String path;
	private final ZipResourceFile mZipFile;

	
	public InZipFile(ZipResourceFile zipFile, String path)
	{
		super(TiBaseFile.TYPE_FILE);
		this.path = path;
		this.mZipFile = zipFile;
	}

	
	
	/**
	 * @return true if the file is a plain file, false otherwise.
	 */
	@Override
	public boolean isFile()
	{
		return true;
	}

	/**
	 * @return true if the file is a directory, false otherwise.
	 */
	@Override
	public boolean isDirectory()
	{
		return false;
	}

	/**
	 * @return true if the file is hidden, false otherwise.
	 */
	@Override
	public boolean isHidden()
	{
		return false;
	}

	/**
	 * @return true if the file is read-only, false otherwise.
	 */
	@Override
	public boolean isReadonly()
	{
		return true;
	}

	/**
	 * @return true if the file is writable, false otherwise.
	 */
	@Override
	public boolean isWriteable()
	{
		return false;
	}

	/**
	 * Attempts to create a directory named by the trailing filename of this file.
	 * @param recursive  whether to recursively create any missing parent directories in the path.
	 * @return  true if directory was sucessfully created, false otherwise.
	 */
	@Override
	public boolean createDirectory(boolean recursive)
	{
		return false;
	}

	
	/**
	 * Attempts to delete a directory in the file system.
	 * @param recursive whether to recursively delete any parent directories in the path.
	 * @return true if the directory was successfully deleted, false otherwise.
	 */
	@Override
	public boolean deleteDirectory(boolean recursive) {
		return false;
	}
	
	/**
	 * Deletes this file.
	 * @return true if the file was successfully deleted, false otherwise.
	 */
	@Override
	public boolean deleteFile()
	{
		return false;
	}

	/**
	 * @return true if the file exists, false otherwise.
	 */
	@Override
	public boolean exists()
	{
		InputStream stream = null;
		try {
			stream = mZipFile.getInputStream(path);
		}
		catch (IOException e) {
			
		}
		return stream != null;
	}

	@Override
	public double createTimestamp()
	{
		return 0;
	}

	@Override
	public double modificationTimestamp()
	{
		return 0;
	}

	@Override
	public String name()
	{
		File file = new File(path);
		return file.getName();
	}

	@Override
	public String extension()
	{
		File file = new File(path);
		String name = file.getName();
		int idx = name.lastIndexOf(".");
		if (idx != -1)
		{
			return name.substring(idx+1);
		}
		return null;
	}

	@Override
	public String nativePath()
	{	
		return path;
	}

	public String toURL() 
	{
		File file = new File(path);
		String url = null;
		url = Uri.fromFile(file).toString();
		return url;
	}

	@Override
	public long size()
	{
		AssetFileDescriptor fd = mZipFile.getAssetFileDescriptor(path);
		return fd.getLength();
	}

	@Override
	public double spaceAvailable()
	{
		return 0;
	}

	/**
	 * Sets the file to read-only.
	 * @return true if the file is verified as read-only, false otherwise.
	 */
	@Override
	public boolean setReadonly()
	{
		return true;
	}

	public String toString()
	{
		return path;
	}


	@Override
	public InputStream getInputStream() throws IOException {
		return mZipFile.getInputStream(path);
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		return null;
	}

	@Override
	public List<String> getDirectoryListing() {
		List<String> listing = new ArrayList<String>();
		ZipEntryRO[] entries = mZipFile.getEntriesAt(path);
		if (entries != null) {
			int len = entries.length;
			for (int i = 0; i < len; i++) {
				listing.add(entries[i].mFileName);
			}
		}

		return listing;
	}


	@Override
	public TiBaseFile getParent()
	{
		return null;
	}

	/**
	 * Instantiates and opens a file with the appropriate read/write buffer.
	 * For instance, if MODE_READ and true are passed in, respectively, then
	 * {@link TiBaseFile#getExistingInputStream()} will now be the {@link java.io.BufferedInputStream BufferedInputStream} for this file.
	 * @param mode MODE_READ. MODE_WRITE, or MODE_APPEND.
	 * @param binary whether the content of the file is binary or characters/lines.
	 */
	@Override
	public void open(int mode, boolean binary) throws IOException
	{
		this.binary = binary;

		if (mode == MODE_READ) {
			if (!exists()) {
				throw new FileNotFoundException(path);
			}
			instream = getInputStream();
			if (binary) {
				instream = new BufferedInputStream(getInputStream());
			} else {
				inreader = new BufferedReader(new InputStreamReader(getInputStream(), "utf-8"));
			}
			opened = true; // no exception getting here.
		}
	}

	@Override
	public TiBlob read() throws IOException
	{
		return TiBlob.blobFromFile(this);
	}

	@Override
	public String readLine() throws IOException
	{
		String result = null;

		if (!opened) {
			throw new IOException("Must open before calling readLine");
		}
		if (binary) {
			throw new IOException("File opened in binary mode, readLine not available.");
		}

		try {
			result = inreader.readLine();
		} catch (IOException e) {
			Log.e(TAG, "Error reading a line from the file: ", e);
		}

		return result;
	}

	public void write(TiBlob blob, boolean append) throws IOException
	{
		throw new IOException("write not allowed");
	}

	
	@Override
	public void write(String data, boolean append) throws IOException
	{
		throw new IOException("write not allowed");
	}

	@Override
	public void writeLine(String data) throws IOException
	{
		throw new IOException("write not allowed");
	}

	@Override
	public File getNativeFile() {
		return null;
	}
}

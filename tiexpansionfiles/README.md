# TiExpansionFiles Titanium Android module

The Titanium Expansion Files module allows taking advantage of the [APK Expansion Files](http://developer.android.com/google/play/expansion-files.html) feature provided by the Android Play Store in Titanium Mobile applications.

The module uses Google's Licensing and Downloader libraries distributed as Extras within the Android SDK install.


## Building and installing the Module

For building the module from source you need the tools listed in the [Appcelerator Android Module Development Guide](http://docs.appcelerator.com/titanium/latest/#!/guide/Android_Module_Development_Guide-section-29004945_AndroidModuleDevelopmentGuide-InstallingtheNecessaryComponents)

Once all the necessary tools have been installed, you need to manually modify the following files:

* `.classpath`
* `build.properties`

in order to reflect the actual location of the required tools in your system.

The build process can be started by executing

	$ ant

from the command line.

Once completed, the module package can be found in the `dist` directory and it can be installed globally by unzipping it in the appropriate Titanium directory. For example, on a Mac OS X system:


	ant
	unzip -uo dist/ti.expansionfiles-android-0.1.0.zip -d ~/Library/Application\ Support/Titanium



## Referencing the module in your Titanium Mobile application

In order to use the module in your Titanium application, add the following lines to your `tiapp.xml` file:

	<modules>
		<module platform="android">ti.expansionfiles</module>
	</modules>


You'll also need to configure the module with your Android Play Store publisher key, and a random salt array, by adding them as app properties in `tiapp.xml`:

	<property name="ti.android.licensing.key" type="string">
	<!-- your BASE64 licensing key -->
	</property>
	<property name="ti.android.licensing.salt" type="string">
	<!-- your 20 bytes salt array -->
	</property>

like for example:

	<property name="ti.android.licensing.key" type="string">
		MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAi95M
		OOAYhQIXMN4xHgMxlrKtJ8Ydxp1yjU0ArO6L1zex11v981Lzck9l
		...
		qgspd5Yjo8zVjgb6y428KZD69cq5iwIDAQAB
	</property>

	<property name="ti.android.licensing.salt" type="string">
		[170,218,244,241,16,93,95,38,195,124,122,151,45,178,176,189,104,103,50,103]
	</property>

The BASE-64 licensing key can be retrieved from your personal Play publisher profile, while the random salt array can be generated, for example trough the following service: [http://www.random.org/integers/?num=20&min=0&max=255&col=5&base=10&format=html&rnd=new](http://www.random.org/integers/?num=20&min=0&max=255&col=5&base=10&format=html&rnd=new)

## Accessing the Module from JavaScript code

First we must `require()` the module:

	var expansionFiles = require('ti.expansionfiles');

then, in order to be notified about download progress events we can register event listeners, like for example:

	expansionFiles.addEventListener('downloadProgress', function(e) {
        var progress = e.overallProgress / e.overallTotal * 100;
        console.log('downloadProgress: ' + progress + "%");
    });

(For a comprehensive list of supported events, please see the next section of this document)

Finally we can start the download of the expansion files uploaded with the app APK through the Play Store dashboard:

	expansionFiles.downloadXAPKs({
        mainFile: {
            version: 4,
            size: 12013233
        },
        patchFile: {
            version: 3,
            size: 213
        }
    });

If the requested files are already present on the device no download will be performed, though they will be checked to be valid `.zip` files. The validation process can be monitored through the `validateAPKProgress` event.

Downloaded files are stored on the SD card and their actual path can be retrieved through the `getDownloadedFilePaths()` method:

	var filePaths = expansionFiles.getDownloadedFilePaths();
	console.log('main file location: ' + filePaths.mainFile);
	console.log('patch file location: ' + filePaths.patchFile);

## Module Reference

### Methods

* `downloadXAPKs(fileDescriptors)`: starts the download of the expansion files if not already present on the device.
	* Arguments:
		* `fileDescriptors` (object): a dictionary containing the description of the expansion files uploaded to the Play Store. The dictionary must contain a `mainFile` property, and it can contain an optional `patchFile` property. These correspond respectively to the main and patch file descriptors for the expansion files uploaded to the Play Store. Each file descriptor must contain a `version` and a `size` property. The `version` property corresponds to the version of the APK with which the expansion file has been uploaded. The `size` argument is the size in bytes of the file (this information is used for zip file verification).
	* return: nothing

* `getDownloadedFilePaths()`: gets the actual paths of the downloaded expansion files.
	* Arguments: none
	* return: a dictionary containing the following properties:
		* `mainFile` (string): local path of the main expansion file
		* `patchFile` (string): local path of the patch expansion file

* `listAllFilesInMain()`: gets a list of the files contained in the main expansion file
    * Arguments: none
    * return: an array containing a list of all the paths of the files contained in the main expansion file

* `listAllFilesInPatch()`: gets a list of the files contained in the main expansion file
    * Arguments: none
    * return: an array containing a list of all the paths of the files contained in the patch expansion file

* `getFileFromMain(path)`: gets a `TiExpansionFiles.File` object representing a file contained (and zipped) in the main expansion file, providing direct access to it, without needin to first decompressing the expansion file.
    * Arguments:
        * `path` (string): the path inside of the **main** zip expansion file
    * return: a `TiExpansionFiles.File` object

* `getFileFromPatch(path)`: gets a `TiExpansionFiles.File` object representing a file contained (and zipped) in the patch expansion file, providing direct access to it, without needin to first decompressing the expansion file.
    * Arguments:
        * `path` (string): the path inside of the **patch** zip expansion file
    * return: a `TiExpansionFiles.File` object


### Constants

* Download state constants: the `downloaderStateChanged` event will report one of the following states through the `state` property of the event object.
    * `STATE_IDLE`
    * `STATE_FETCHING_URL`
    * `STATE_CONNECTING`
    * `STATE_DOWNLOADING`
    * `STATE_COMPLETED`
    * `STATE_PAUSED_NETWORK_UNAVAILABLE`
    * `STATE_PAUSED_BY_REQUEST`
    * `STATE_PAUSED_WIFI_DISABLED_NEED_CELLULAR_PERMISSION`
    * `STATE_PAUSED_NEED_CELLULAR_PERMISSION`
    * `STATE_PAUSED_WIFI_DISABLED`
    * `STATE_PAUSED_NEED_WIFI`
    * `STATE_PAUSED_ROAMING`
    * `STATE_PAUSED_NETWORK_SETUP_FAILURE`
    * `STATE_PAUSED_SDCARD_UNAVAILABLE`
    * `STATE_FAILED_UNLICENSED`
    * `STATE_FAILED_FETCHING_URL`
    * `STATE_FAILED_SDCARD_FULL`
    * `STATE_FAILED_CANCELED`
    * `STATE_FAILED`

### Events

#### Download events:

* `downloaderServiceConnected`: fired when the background downloader service connects to the Play Store backend
* `downloadProgress`: fired during the expansion files download in order to report the overall progress of the operation.
	* event properties:
		* `overallProgress` (number): amount of bytes downloaded so far
		* `overallTotal` (number): total size in bytes of the download
* `downloaderStateChanged`: fired throughout the download process for notifying about the current download state
	* event properties:
		* `state` (number): one of the above mentioned download state constants

#### Validation events
these events are fired throughout the validation process of already downloaded expansion files.

* `validateAPKStarted`: fired at the beginning of the validation
* `validateAPKProgress`: fired during the zip file validation process in order to report the overall progress of the operation
	* event properties:
		* `overallProgress` (number): estimated amount of processing done so far
		* `overallTotal` (number): estimated total amount to be processed
* `validateAPKFinished`: fired at the end of the validation process



## `TiExpansionFiles.File` object reference

A `TiExpansionFiles.File` object represents a file contained in an expansion file and it provides direct access to it, without needin to first decompressing the expansion file. It provides an API very close to that of the standard `Ti.Filesystem.File` object of the Titanium SDK.

### Methods
Most of the methods of the `Ti.Filesystem.File` object are supported, among those the followings are the most relevant:

* `exists()`: tells if the specified file is present in the expansion file
    * Arguments: none
    * return: true if the file is present, false otherwise

* `getName()`:
    * Arguments: none
    * return (string): the name of the file

* `extension()`:
    * Arguemnts: none
    * return (string): the extension of the file

* `getSize()`:
    * Arguments: none
    * return (string): the size of the file in bytes


* `read()`: reads the content of the file in a TiBlob
    * Arguments: none
    * return (TiBlob): a blob representing the contents of the file






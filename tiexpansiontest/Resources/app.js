var expansionFiles = require('ti.expansionfiles');

expansionFiles.addEventListener('downloadProgress', function(e) {
    var progress = e.overallProgress / e.overallTotal * 100;
    console.log('downloadProgress: ' + progress + "%");
});


expansionFiles.addEventListener('validateAPKStarted', function() {
    console.log('validateAPKStarted');
});

expansionFiles.addEventListener('validateAPKProgress', function(e) {
    var progress = e.overallProgress / e.overallTotal * 100;
    console.log('validateAPKProgress: ' + progress + "%");
});

expansionFiles.addEventListener('validateAPKFinished', function() {
    console.log('validateAPKFinished');
});

expansionFiles.addEventListener('downloaderServiceConnected', function() {
    console.log('downloaderServiceConnected');
});

expansionFiles.addEventListener('downloaderStateChanged', function(e) {
    if (e.state == expansionFiles.STATE_COMPLETED) {
        console.log('download completed');
        var filePaths = expansionFiles.getDownloadedFilePaths();
        console.log('main file location: ' + filePaths.mainFile);
        console.log('patch file location: ' + filePaths.patchFile);

    }
});

expansionFiles.addEventListener('downloaderStateChanged', function(e) {
    console.log('downloaderStateChanged: ' + e.state);
    var state = "unknown";
    switch(e.state) {
        case expansionFiles.STATE_IDLE:
            state = "STATE_IDLE";
            break;
        case expansionFiles.STATE_FETCHING_URL:
            state = "STATE_FETCHING_URL";
            break;
        case expansionFiles.STATE_CONNECTING:
            state = "STATE_CONNECTING";
            break;
        case expansionFiles.STATE_DOWNLOADING:
            state = "STATE_DOWNLOADING";
            break;
        case expansionFiles.STATE_COMPLETED:
            state = "STATE_COMPLETED";
            break;
        case expansionFiles.STATE_PAUSED_NETWORK_UNAVAILABLE:
            state = "STATE_PAUSED_NETWORK_UNAVAILABLE";
            break;
        case expansionFiles.STATE_PAUSED_BY_REQUEST:
            state = "STATE_PAUSED_BY_REQUEST";
            break;
        case expansionFiles.STATE_PAUSED_WIFI_DISABLED_NEED_CELLULAR_PERMISSION:
            state = "STATE_PAUSED_WIFI_DISABLED_NEED_CELLULAR_PERMISSION";
            break;
        case expansionFiles.STATE_PAUSED_NEED_CELLULAR_PERMISSION:
            state = "STATE_PAUSED_NEED_CELLULAR_PERMISSION";
            break;
        case expansionFiles.STATE_PAUSED_WIFI_DISABLED:
            state = "STATE_PAUSED_WIFI_DISABLED";
            break;
        case expansionFiles.STATE_PAUSED_NEED_WIFI:
            state = "STATE_PAUSED_NEED_WIFI";
            break;
        case expansionFiles.STATE_PAUSED_ROAMING:
            state = "STATE_PAUSED_ROAMING";
            break;
        case expansionFiles.STATE_PAUSED_NETWORK_SETUP_FAILURE:
            state = "STATE_PAUSED_NETWORK_SETUP_FAILURE";
            break;
        case expansionFiles.STATE_PAUSED_SDCARD_UNAVAILABLE:
            state = "STATE_PAUSED_SDCARD_UNAVAILABLE";
            break;
        case expansionFiles.STATE_FAILED_UNLICENSED:
            state = "STATE_FAILED_UNLICENSED";
            break;
        case expansionFiles.STATE_FAILED_FETCHING_URL:
            state = "STATE_FAILED_FETCHING_URL";
            break;
        case expansionFiles.STATE_FAILED_SDCARD_FULL:
            state = "STATE_FAILED_SDCARD_FULL";
            break;
        case expansionFiles.STATE_FAILED_CANCELED:
            state = "STATE_FAILED_CANCELED";
            break;
        case expansionFiles.STATE_FAILED:
            state = "STATE_FAILED";
            break;
    }
    console.log('downloaderStateChanged to: ' + state);
});




// this sets the background color of the master UIView (when there are no windows/tab groups on it)
Titanium.UI.setBackgroundColor('#000');

// create tab group
var tabGroup = Titanium.UI.createTabGroup();


tabGroup.addEventListener('open', function() {
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

});


//
// create base UI tab and root window
//
var win1 = Titanium.UI.createWindow({
    title:'Tab 1',
    backgroundColor:'#fff'
});
var tab1 = Titanium.UI.createTab({
    icon:'KS_nav_views.png',
    title:'Tab 1',
    window:win1
});

var label1 = Titanium.UI.createLabel({
	color:'#999',
	text:'I am Window 1',
	font:{fontSize:20,fontFamily:'Helvetica Neue'},
	textAlign:'center',
	width:'auto'
});

win1.add(label1);

//
// create controls tab and root window
//
var win2 = Titanium.UI.createWindow({
    title:'Tab 2',
    backgroundColor:'#fff'
});
var tab2 = Titanium.UI.createTab({
    icon:'KS_nav_ui.png',
    title:'Tab 2',
    window:win2
});

var label2 = Titanium.UI.createLabel({
	color:'#999',
	text:'I am Window 2',
	font:{fontSize:20,fontFamily:'Helvetica Neue'},
	textAlign:'center',
	width:'auto'
});

win2.add(label2);



//
//  add tabs
//
tabGroup.addTab(tab1);
tabGroup.addTab(tab2);


// open tab group
tabGroup.open();

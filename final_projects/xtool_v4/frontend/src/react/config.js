let config = {
    localstorageKey : {
        userinfo : 'userInfo'
    },
    
    system: {
        vietnam: 'NVN',
        thailand: 'NTL'
    },
    role:{
        "teacher"   : "TEACHER",
        "gv"        : "GV",
        "student"   : "STUDENT",
        "po"        : "PO",
        "assistant" : "ASSISTANT",
        "mb"        : "MB",
        "hv"        : "HV",
        "tg"        : "TG"
    },
    domain : {
        api: 'https://vcrxapitester.topica.vn',
       socket: 'https://vcrxsocket.topica.vn/',
       media: 'mediasuper.topica.vn',
       mediaSendmessage: 'mediauni.topica.vn',
       xtool: 'http://xtooldev8.topica.vn',
       thailand: {
           api: 'https://vcrxapitester.topica.vn',
           lms: 'https://lms40ntltester.topica.vn',
           meet: 'https://vcrxntltester.topica.vn'
       },
       vietnam: {
           api: 'https://vcrxapitester.topica.vn',
           lms: 'http://dev16lmspoco.topicanative.edu.vn',
           meet: 'https://vcrxnvntester.topica.vn',
       }
    },
}

export default config;

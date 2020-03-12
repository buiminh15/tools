var base64ToImage = require('base64-to-image');
const fs = require('fs');
const dir = './manga';


fs.readdir(dir, (err, files) => {
    if (err) {
        throw err
    }

   try {

    for (let index = 0; index < files.length; index++) {
        let base64Str = '' ;
        if (index.toString().length === 1) {
            base64Str  = fs.readFileSync(`./manga/${'00'+index}.txt`, 'utf8')   
            var path = './images/'
            var optionalObj = {'fileName': `${'00'+index}`, 'type':'jpg'};
        }
        if (index.toString().length === 2) {
            base64Str  = fs.readFileSync(`./manga/${'0'+index}.txt`, 'utf8')   
            var path = './images/'
            var optionalObj = {'fileName': `${'0'+index}`, 'type':'jpg'};
        }
        if (index.toString().length === 3) {
            base64Str  = fs.readFileSync(`./manga/${index}.txt`, 'utf8')   
            var path = './images/'
            var optionalObj = {'fileName': `${index}`, 'type':'jpg'};
        }
  
        base64ToImage(base64Str ,path,optionalObj); 
        
    }


  } catch (err) {
    console.error(err)
  }
});








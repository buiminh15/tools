const request = require('request');
const fs = require('fs');
const bookName = require("../utils/common");
// const uri = `https://kids-km3.shogakukan.co.jp/contents/nichireki01/${pageNum}/base64`

var index = 2;
request(
  `https://kids-km3.shogakukan.co.jp/contents/nichirekia2/113/base64`,
  function(error, response, body) {
    try {
      let dataStr = body;
      dataStr = dataStr.replace(/^/, "data:image/png;base64,");

      let fileName = 113;
      if (dataStr !== "Not Found") {
        if (fileName.toString().length === 1) {
          fileName = "00" + fileName;
          writeFile(fileName, dataStr);
        }

        if (fileName.toString().length === 2) {
          fileName = "0" + fileName;
          writeFile(fileName, dataStr);
        }

        if (fileName.toString().length === 3) {
          writeFile(fileName, dataStr);
        }
      } else {
        return;
      }
    } catch (error) {
      console.log(error);
    }
  }
);



function writeFile(fileName, dataStr) {
  const file = fs.createWriteStream(`./manga/${fileName}.txt`);
  file.write(dataStr, (err) => {
    if (err) throw err;
    console.log('The file has been saved!');
  });
}
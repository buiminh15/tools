const request = require('request');
const fs = require('fs');

// const uri = `https://kids-km3.shogakukan.co.jp/contents/nichireki01/${pageNum}/base64`

var index = 0;
do {
  request(
    `https://kids-km3.shogakukan.co.jp/contents/nichireki01/${index}/base64`,
    function (error, response, body) {
      let dataStr = body;
      dataStr = dataStr.replace(/^/, "data:image/png;base64,");

      let fileName = index;
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
    }
  );
  setTimeout(function () {
    index++;
  }, 7000);
} while (index < 4);

function writeFile(fileName, dataStr) {
  const file = fs.createWriteStream(`./manga/${fileName}.txt`);
  file.write(dataStr, (err) => {
    if (err) throw err;
    console.log('The file has been saved!');
  });
}
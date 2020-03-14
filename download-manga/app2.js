const request = require("request");
const fs = require("fs");
const bookName = require("./utils/common");
var async = require("async");

function apicall(urlApi, index) {
  request(urlApi, function(error, response, body) {
    if (error) {
      console.log(error);
    }
    let dataStr = body;
    // dataStr = dataStr.replace(/^/, "data:image/png;base64,");
    dataStr = "data:image/png;base64," + dataStr;
    let fileName = index;
    if (!dataStr.includes("Not Found")) {
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
  });
}

let promises = [];
// for (let i = 100; i < 101; i++) {
//   let uri = `https://kids-km3.shogakukan.co.jp/contents/${bookName}/${i}/base64`;
//   promises.push(apicall(uri, i));
// }
function donwloadTo30() {
  for (let i = 0; i <= 30; i++) {
    let uri = `https://kids-km3.shogakukan.co.jp/contents/${bookName}/${i}/base64`;
    promises.push(apicall(uri, i));
  }
};

function donwloadTo60 () {
  for (let i = 31; i <= 60; i++) {
    let uri = `https://kids-km3.shogakukan.co.jp/contents/${bookName}/${i}/base64`;
    promises.push(apicall(uri, i));
  }
};

const tasks = [donwloadTo30(), donwloadTo60()]

async.waterfall(tasks,(err, results) => {
        if (err) {
            return next(err);
        }
        return res.json(results);
    })

Promise.all(promises)
  .then(() => {
    // all done here
    console.log("done");
  })
  .catch(err => {
    // error here
  });

function writeFile(fileName, dataStr) {
  const file = fs.createWriteStream(`./manga/${fileName}.txt`);
  file.write(dataStr, err => {
    if (err) throw err;
  });
}

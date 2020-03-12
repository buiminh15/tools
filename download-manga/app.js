const puppeteer = require('puppeteer');
const request = require('request');
const fs = require('fs');

// const uri = `https://kids-km3.shogakukan.co.jp/contents/nichireki01/${pageNum}/base64`


request('https://kids-km3.shogakukan.co.jp/contents/nichireki01/0/base64', function (error, response, body) {

    let dataStr = JSON.stringify(body);


    dataStr = 'data:image/png;base64,'+dataStr;
    dataStr = dataStr.replace('==', '=="');

    let fileName = 0;
    writeFile('tst', body)
    // if (dataStr !=='Not Found') {
    //     if (fileName.toString().length === 1) {
    //         fileName = '00'+fileName
    //         writeFile(fileName, dataStr)
    //     }

    //     if (fileName.toString().length === 2) {
    //         fileName = '0'+fileName
    //         writeFile(fileName, dataStr)
    //     }

    //     if (fileName.toString().length === 3) {
    //         writeFile(fileName, dataStr)
    //     }
    // } else {
    //     return
    // }
});

  function writeFile(fileName, dataStr) {
    const file = fs.createWriteStream(`./manga/${fileName}.txt`);
    file.write(dataStr, (err) => {
        if (err) throw err;
        console.log('The file has been saved!');
      });
  }
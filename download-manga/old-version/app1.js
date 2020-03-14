const puppeteer = require('puppeteer');
const fs = require('fs');

// const uri = `https://kids-km3.shogakukan.co.jp/contents/nichireki01/${pageNum}/base64`

(async () => {
    var index = 0
    do {
        console.log('start...');
        const browser = await puppeteer.launch({ headless: false })
        const page = await browser.newPage()
        await page.goto(`https://kids-km3.shogakukan.co.jp/contents/nichireki01/${index}/base64`, { waitUntil: 'load' })
        // await page.waitForNavigation({
        //     waitUntil: 'networkidle0',
        //   });
        let data = await page.evaluate(() => {
            let content = document.querySelector('body').innerText;
            return {
                content
            }
        })
        let dataStr = JSON.stringify(data);
        dataStr = dataStr.replace('{"content":"', 'data:image/png;base64,');
        dataStr = dataStr.replace('"}', '');

        let fileName = index;
        if (dataStr !== 'Not Found') {
            if (fileName.toString().length === 1) {
                fileName = '00' + fileName
                writeFile(fileName, dataStr)
            }

            if (fileName.toString().length === 2) {
                fileName = '0' + fileName
                writeFile(fileName, dataStr)
            }

            if (fileName.toString().length === 3) {
                writeFile(fileName, dataStr)
            }
        } else {
            return
        }
        await page.waitFor(3000)
        await browser.close()
        setTimeout(function () { index++; console.log(index); }, 5000);
    } while (index < 2)
})()



function writeFile(fileName, dataStr) {
    const file = fs.createWriteStream(`./manga/${fileName}.txt`);
    file.write(dataStr, (err) => {
        if (err) throw err;
        console.log('The file has been saved!');
    });
}

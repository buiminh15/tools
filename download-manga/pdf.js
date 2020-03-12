//requiring path and fs modules
const path = require("path");
const fs = require("fs");
const imagesToPdf = require("images-to-pdf");
const bookName = require("./utils/common");

//joining path of directory
const directoryPath = path.join(__dirname, "images");
//passsing directoryPath and callback function
fs.readdir(directoryPath,async function(err, files) {
  //handling error
  if (err) {
    return console.log("Unable to scan directory: " + err);
  }
  //listing all files using forEach
  let arrayPath = []
  files.forEach(function(file) {
    // Do whatever you want to do with the file
    file = path.join(__dirname, "images",file);
    arrayPath.push(file)
  });
  await imagesToPdf(arrayPath, `./${bookName}.pdf`);
});

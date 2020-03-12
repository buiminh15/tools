const fs = require("fs");
const path = require("path");

const directory1 = "./images";
const directory2 = "./manga";

fs.readdir(directory1, (err, files) => {
  if (err) throw err;

  for (const file of files) {
    fs.unlink(path.join(directory1, file), err => {
      if (err) throw err;
    });
  }
})

fs.readdir(directory2, (err, files) => {
    if (err) throw err;

    for (const file of files) {
      fs.unlink(path.join(directory2, file), err => {
        if (err) throw err;
      });
    }
  })

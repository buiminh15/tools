import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'convertS';
  inputText: string;
  convertProcess: string;
  outputText: string;
  onSubmit(form: NgForm) {

      console.log(form.value.inputText)
      let str = form.value.inputText;
      let regex1 = /<.*?>/gim;
      let originArr = str.match(regex1);
      let uppercaseArr=[]
      originArr.forEach((el,i) => {
          uppercaseArr.push(this.convertStr(el))
      });

      for (let index = 0; index < originArr.length; index++) {
          str =str.replace(originArr[index], uppercaseArr[index])

      }

      this.outputText = str
    }

    convertStr(str) {

      if (!str.includes(" ")) {
          return str.toUpperCase()
      }
      let str1="";
      let regex2 = /(^<\w+)|("(.*?)")/gmi;
      let result2 = str.match(regex2)
      result2.forEach(element => {
          str = str.replace(element, element.toUpperCase())
      });
      return str
  }
}

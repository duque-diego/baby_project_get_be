export class Store {
    id: number;
    name: string;
    imgSrc: string;
    constructor(id: number, name: string, imgSrc:string) {
      this.id = id;
      this.name = name;
      this.imgSrc = imgSrc;
    }
}
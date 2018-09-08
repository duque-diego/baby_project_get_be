export class Product {
    id: number;
    name: string;
    brandId: number;
    imageSrc: string;
    constructor(id: number, name: string, brandId:number, imageSrc:string) {
      this.id = id;
      this.name = name;
      this.brandId = brandId;
      this.imageSrc = imageSrc;
    }
}
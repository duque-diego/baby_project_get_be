export class Promotion {
    id: number;
    unitValue: number;
    packageValue: number;
    promotionLink: string;
    productId: number;
    sizeId: number;
    storeId: number;
    constructor(
      id: number, 
      unitValue: number, 
      packageValue: number, 
      promotionLink: string, 
      productId: number, 
      sizeId: number,
      storeId:number
    ) 
    {
      this.id = id;
      this.unitValue = unitValue;
      this.packageValue = packageValue;
      this.promotionLink = promotionLink;
      this.productId = productId;
      this.sizeId = sizeId;
      this.storeId = storeId;
    }
}
export class InstrumentType {

    private id: number;
    private instrumentTypeName: string;
    private aboutType: string;

    constructor(obj?: any) {
        this.id = (obj && obj.id) || 0;
        this.instrumentTypeName = (obj && obj.instrumentTypeName) || "";
        this.aboutType = (obj && obj.aboutType) || "";
    }

    // Getters
    
    public getId(): number {
        return this.id;
    }

    public getInstrumentTypeName(): string {
        return this.instrumentTypeName;
    }

    public getAboutType(): string {
        return this.aboutType;
    }

    // Setters

    public setInstrumentTypeName(instrumentTypeName: string){
        this.instrumentTypeName = instrumentTypeName;
    }

    public setAboutType(aboutType: string) {
        this.aboutType = aboutType;
    }

}
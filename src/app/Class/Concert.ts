import { Location } from '@angular/common';

export class Concert {

    private id: number;
    private startTime: Date;
    private concertName: string;
    private location: Location;

    constructor(obj?: any) {
        this.startTime = (obj && obj.startTime) || null;
        this.concertName = (obj && obj.concertName) || '';
        this.location = (obj && obj.location) || null;
    }

    // Getters

    getId(): number {
        return this.id;
    }

    getStartTime(): Date {
        return this.startTime
    }

    getConcertName(): string {
        return this.concertName;
    }

    getLocation(): Location {
        return this.location;
    }

    // Setters

    setStartTime(startTime: Date) { 
        this.startTime = startTime;
    }

    setConcertname(concertName: string) {
        this.concertName = concertName;
    }

    setLocation(location: Location) {
        this.location = location;
    }

}

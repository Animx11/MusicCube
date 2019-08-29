import { Users } from './Users';
import { Song } from './Song';

export class Rate {

    private id: number;
    private user: Users;
    private song: Song;
    private rate: number;

    constructor(obj?: any) {
        this.id = (obj && obj.id) || 0;
        this.user = (obj && obj.user) || null;
        this.song = (obj && obj.song) || null;
        this.rate = (obj && obj.rate) || 0;
    }

    getId(): number {
        return this.id;
    }

    getUser(): Users {
        return this.user;
    }
    
    getSong(): Song {
        return this.song;
    }
    
    getRate(): number {
        return this.rate;
    }

    setUser(user: Users) {
        this.user = user;
    }
    
    setSong(song: Song) {
        this.song = song;
    }
    
    setRate(rate: number) {
        this.rate = rate;
    }
}

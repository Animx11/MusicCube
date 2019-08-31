import { Users } from './Users';
import { Song } from './Song';
import { Album } from './Album';
import { Band } from './Band';
import { Artist } from './Artist';

export class CommentClass {

    private id: number;
    private user: Users;
    private song: Song;
    private album: Album;
    private band: Band;
    private artist: Artist;
    private commentDate: Date;
    private wasEdited: boolean;
    private commentContent: string;

    constructor(obj?: any) {
        this.id = (obj && obj.id) || 0;
        this.user = (obj && obj.user) || null;
        this.album = (obj && obj.album) || null;
        this.band = (obj && obj.band) || null;
        this.artist = (obj && obj.artist) || null;
        this.song = (obj && obj.song) || null;
        this.commentDate = (obj && obj.commentDate) || null;
        this.wasEdited = (obj && obj.wasEdited) || false;
        this.commentContent = (obj && obj.commentContent) || '';
    }

    // Getters

    getId(): number {
        return this.id;
    }

    getUser(): Users {
        return this.user;
    }

    getSong(): Song {
        return this.song;
    }

    getAlbum(): Album {
        return this.album;
    }

    getBand(): Band {
        return this.band;
    }

    getArtist(): Artist {
        return this.artist;
    }

    getCommentDate(): Date {
        return this.commentDate;
    }

    getWasEdited(): boolean {
        return this.wasEdited;
    }

    getCommentContent(): string {
        return this.commentContent;
    }


    // Setters

    setUser(user: Users) {
        this.user = user;
    }

    setSong(song: Song){
        this.song = song;
    }

    setAlbum(album: Album){
        this.album = album;
    }

    setBand(band: Band){
        this.band = band;
    }

    setArtist(artist: Artist){
        this.artist = artist;
    }

    setCommentDate(commentDate: Date) {
        this.commentDate = commentDate;
    }

    setWasEdited(wasEdited: boolean) {
        this.wasEdited = wasEdited;
    }

    setCommentContent(commentContent: string) {
        this.commentContent = commentContent;
    }

}

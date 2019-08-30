import { Users } from './Users';
import { Song } from './Song';

export class CommentClass {

    private id: number;
    private user: Users;
    private song: Song;
    private commentDate: Date;
    private wasEdited: boolean;
    private commentContent: string;

    constructor(obj?: any) {
        this.id = (obj && obj.id) || 0;
        this.user = (obj && obj.user) || null;
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

import { Song } from "./Song";
import { Person } from "./Person";

export class SongAuthorship {
  id: number;
  private wroteText: boolean;
  private wroteMusic: boolean;
  private song: Song;
  private author: Person;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.wroteText = (obj && obj.wroteText) || null;
    this.wroteMusic = (obj && obj.wroteMusic) || null;
    this.song = (obj && obj.song) || null;
    this.author = (obj && obj.author) || null;
  }

  // Getters

  getId(): number {
    return this.id;
  }

  getWroteText(): boolean {
    return this.wroteText;
  }

  getWroteMusic(): boolean {
    return this.wroteMusic;
  }

  getSong(): Song {
    return this.song;
  }

  getAuthor(): Person {
    return this.author;
  }

  // Setters

  setWroteText(wroteText: boolean) {
    this.wroteText = wroteText;
  }

  setWroteMusic(wroteMusic: boolean) {
    this.wroteMusic = wroteMusic;
  }

  setSong(song: Song) {
    this.song = song;
  }

  setAuthor(author: Person) {
    this.author = author;
  }
}

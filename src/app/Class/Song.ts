import { Album } from './Album';
import { Band } from './Band';
import { Genre } from './Genre';

export class Song {
  id: number;
  private songName: string;
  private songLengthSeconds: number;
  private album: Album;
  private band: Band;
  private genre: Genre;

  constructor(obj?: any) {
    this.id = (obj && obj.id) || 0;
    this.songName = (obj && obj.songName) || '';
    this.songLengthSeconds = (obj && obj.songLengthSeconds) || 0;
    this.album = (obj && obj.album) || null;
    this.band = (obj && obj.band) || null;
    this.genre = (obj && obj.genre) || null;
  }

  // Getters

  getId(): number {
    return this.id;
  }

  getSongName(): string {
    return this.songName;
  }

  getSongLengthSeconds(): number {
    return this.songLengthSeconds;
  }

  getAlbum(): Album {
    return this.album;
  }

  getBand(): Band {
    return this.band;
  }

  getGenre(): Genre {
    return this.genre;
  }

  // Setters

  setSongName(songName: string) {
    this.songName = songName;
  }

  setSongLengthSeconds(songLengthSeconds: number) {
    this.songLengthSeconds = songLengthSeconds;
  }

  setAlbum(album: Album) {
    this.album = album;
  }

  setBand(band: Band) {
    this.band = band;
  }

  setGenre(genre: Genre) {
    this.genre = genre;
  }
}

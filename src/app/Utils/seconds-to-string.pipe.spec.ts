import { SecondsToStringPipe } from './seconds-to-string.pipe';

describe('SecondsToStringPipe', () => {
  it('create an instance', () => {
    const pipe = new SecondsToStringPipe();
    expect(pipe).toBeTruthy();
  });
});

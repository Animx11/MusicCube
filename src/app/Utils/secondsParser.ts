export function secondsToString(seconds: number): string {

  if (seconds < 0) { return ''; }

  const hours = Math.floor(seconds / 3600);
  const mins = Math.floor((seconds % 3600) / 60);
  const secs = seconds % 60;

  const hourStr = hours === 0 ? '' : hours.toString() + ':';
  const minStr = mins === 0 ? hours === 0 ? '' : '00:' : (mins < 10 && hours !== 0) ? '0' + mins.toString() + ':' : mins.toString() + ':';
  const secStr = secs === 0 ? '00' : secs < 10 ? '0' + secs.toString() : secs.toString();
  return hourStr + minStr + secStr;
}

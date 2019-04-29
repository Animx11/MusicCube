package MusicCube.tockenCreator;

import MusicCube.cipher.EncrypterAES;
import MusicCube.entities.AuthorisationToken;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TokenCreator {

    DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

    private Calendar calendar = Calendar.getInstance(); // creates calendar
    private Date expiryDate = new Date();

    public AuthorisationToken create(String userName, String userPermission){

        calendar.setTime(new Date()); // sets calendar time/date
        calendar.add(Calendar.HOUR_OF_DAY, 8); // adds one hour
        expiryDate = calendar.getTime(); // returns new date object, one hour in the future

        EncrypterAES encrypterAES = new EncrypterAES(calendar.toString());

        String code = encrypterAES.encrypt(userName + userPermission);

        AuthorisationToken token = new AuthorisationToken();
        token.setUserName(userName);
        token.setUserPermission(userPermission);
        token.setCode(code);
        token.setExpiryTime(expiryDate);

        return token;
    }

}

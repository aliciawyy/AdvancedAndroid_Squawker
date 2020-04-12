package android.example.com.squawker.provider;

import android.content.Context;
import android.database.Cursor;
import androidx.room.Room;
import java.util.List;

public class SquawkRepository {
  private static SquawkRepository sInstance;
  private static final String DATABASE_NAME = "equawk-database";

  private final SquawkContractDao squawkContractDao;

  private SquawkRepository(Context context) {
    SquawkDatabase database = Room.databaseBuilder(
        context.getApplicationContext(), SquawkDatabase.class, DATABASE_NAME).build();
    squawkContractDao = database.squawkContractDao();
  }

  public static SquawkRepository get(Context context) {
    if (sInstance == null) {
      sInstance = new SquawkRepository(context);
    }
    return sInstance;
  }

  public List<SquawkContract> getAll() {
    return squawkContractDao.getAll();
  }

  public void addSquawkContract(SquawkContract squawkContract) {
    squawkContractDao.addSquawkContract(squawkContract);
  }

  public Cursor getSquawkContractsWithCursor(long messageId) {
    return squawkContractDao.getSquawkContractsWithCursor(messageId);
  }

}

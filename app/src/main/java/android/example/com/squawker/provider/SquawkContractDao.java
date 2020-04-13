package android.example.com.squawker.provider;

import android.database.Cursor;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface SquawkContractDao {
  @Query("SELECT * FROM squawkcontract")
  Cursor getAllWithCursor();

  @Insert
  void addSquawkContract(SquawkContract squawkContract);

  @Query("SELECT * FROM squawkcontract WHERE messageId = :messageId")
  Cursor getSquawkContractsWithCursor(long messageId);
}

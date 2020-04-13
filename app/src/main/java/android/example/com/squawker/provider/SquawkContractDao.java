package android.example.com.squawker.provider;

import android.database.Cursor;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface SquawkContractDao {
  @Query("SELECT * FROM squawkcontract ORDER BY date DESC")
  Cursor getAllWithCursor();

  @Query("SELECT * FROM squawkcontract WHERE authorKey IN (:authorKeys) ORDER BY date DESC")
  Cursor getByAuthorsWithCursor(String[] authorKeys);

  @Insert
  void addSquawkContract(SquawkContract squawkContract);

  @Query("SELECT * FROM squawkcontract WHERE messageId = :messageId ORDER BY date DESC")
  Cursor getSquawkContractsWithCursor(long messageId);
}

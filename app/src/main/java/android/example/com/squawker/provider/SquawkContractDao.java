package android.example.com.squawker.provider;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface SquawkContractDao {
  @Query("SELECT * FROM squawkcontract")
  List<SquawkContract> getAll();

  @Insert
  void addSquawkContract(SquawkContract squawkContract);
}

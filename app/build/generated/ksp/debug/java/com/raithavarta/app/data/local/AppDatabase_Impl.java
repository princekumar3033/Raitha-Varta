package com.raithavarta.app.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile TipDao _tipDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `tips` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `titleEn` TEXT NOT NULL, `titleKn` TEXT NOT NULL, `titleHi` TEXT NOT NULL, `descriptionEn` TEXT NOT NULL, `descriptionKn` TEXT NOT NULL, `descriptionHi` TEXT NOT NULL, `actionEn` TEXT NOT NULL, `actionKn` TEXT NOT NULL, `actionHi` TEXT NOT NULL, `cropCategory` TEXT NOT NULL, `imageResName` TEXT NOT NULL, `tipType` TEXT NOT NULL, `season` TEXT NOT NULL, `isBookmarked` INTEGER NOT NULL, `dateAdded` INTEGER NOT NULL, `priority` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `success_stories` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `farmerNameEn` TEXT NOT NULL, `farmerNameKn` TEXT NOT NULL, `farmerNameHi` TEXT NOT NULL, `locationEn` TEXT NOT NULL, `locationKn` TEXT NOT NULL, `locationHi` TEXT NOT NULL, `storyEn` TEXT NOT NULL, `storyKn` TEXT NOT NULL, `storyHi` TEXT NOT NULL, `cropCategory` TEXT NOT NULL, `yieldImprovement` TEXT NOT NULL, `imageResName` TEXT NOT NULL, `tipId` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '47355cf69cfba65b1e18eebb377b7bd4')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `tips`");
        db.execSQL("DROP TABLE IF EXISTS `success_stories`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsTips = new HashMap<String, TableInfo.Column>(17);
        _columnsTips.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTips.put("titleEn", new TableInfo.Column("titleEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTips.put("titleKn", new TableInfo.Column("titleKn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTips.put("titleHi", new TableInfo.Column("titleHi", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTips.put("descriptionEn", new TableInfo.Column("descriptionEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTips.put("descriptionKn", new TableInfo.Column("descriptionKn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTips.put("descriptionHi", new TableInfo.Column("descriptionHi", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTips.put("actionEn", new TableInfo.Column("actionEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTips.put("actionKn", new TableInfo.Column("actionKn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTips.put("actionHi", new TableInfo.Column("actionHi", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTips.put("cropCategory", new TableInfo.Column("cropCategory", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTips.put("imageResName", new TableInfo.Column("imageResName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTips.put("tipType", new TableInfo.Column("tipType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTips.put("season", new TableInfo.Column("season", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTips.put("isBookmarked", new TableInfo.Column("isBookmarked", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTips.put("dateAdded", new TableInfo.Column("dateAdded", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTips.put("priority", new TableInfo.Column("priority", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTips = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTips = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTips = new TableInfo("tips", _columnsTips, _foreignKeysTips, _indicesTips);
        final TableInfo _existingTips = TableInfo.read(db, "tips");
        if (!_infoTips.equals(_existingTips)) {
          return new RoomOpenHelper.ValidationResult(false, "tips(com.raithavarta.app.data.model.Tip).\n"
                  + " Expected:\n" + _infoTips + "\n"
                  + " Found:\n" + _existingTips);
        }
        final HashMap<String, TableInfo.Column> _columnsSuccessStories = new HashMap<String, TableInfo.Column>(14);
        _columnsSuccessStories.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSuccessStories.put("farmerNameEn", new TableInfo.Column("farmerNameEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSuccessStories.put("farmerNameKn", new TableInfo.Column("farmerNameKn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSuccessStories.put("farmerNameHi", new TableInfo.Column("farmerNameHi", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSuccessStories.put("locationEn", new TableInfo.Column("locationEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSuccessStories.put("locationKn", new TableInfo.Column("locationKn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSuccessStories.put("locationHi", new TableInfo.Column("locationHi", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSuccessStories.put("storyEn", new TableInfo.Column("storyEn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSuccessStories.put("storyKn", new TableInfo.Column("storyKn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSuccessStories.put("storyHi", new TableInfo.Column("storyHi", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSuccessStories.put("cropCategory", new TableInfo.Column("cropCategory", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSuccessStories.put("yieldImprovement", new TableInfo.Column("yieldImprovement", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSuccessStories.put("imageResName", new TableInfo.Column("imageResName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSuccessStories.put("tipId", new TableInfo.Column("tipId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSuccessStories = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSuccessStories = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSuccessStories = new TableInfo("success_stories", _columnsSuccessStories, _foreignKeysSuccessStories, _indicesSuccessStories);
        final TableInfo _existingSuccessStories = TableInfo.read(db, "success_stories");
        if (!_infoSuccessStories.equals(_existingSuccessStories)) {
          return new RoomOpenHelper.ValidationResult(false, "success_stories(com.raithavarta.app.data.model.SuccessStory).\n"
                  + " Expected:\n" + _infoSuccessStories + "\n"
                  + " Found:\n" + _existingSuccessStories);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "47355cf69cfba65b1e18eebb377b7bd4", "dfeef08e5aa0cb436beb6b2f94800641");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "tips","success_stories");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `tips`");
      _db.execSQL("DELETE FROM `success_stories`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(TipDao.class, TipDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public TipDao tipDao() {
    if (_tipDao != null) {
      return _tipDao;
    } else {
      synchronized(this) {
        if(_tipDao == null) {
          _tipDao = new TipDao_Impl(this);
        }
        return _tipDao;
      }
    }
  }
}

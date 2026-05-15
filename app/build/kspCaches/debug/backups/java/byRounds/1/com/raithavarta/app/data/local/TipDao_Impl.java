package com.raithavarta.app.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.raithavarta.app.data.model.SuccessStory;
import com.raithavarta.app.data.model.Tip;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TipDao_Impl implements TipDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Tip> __insertionAdapterOfTip;

  private final EntityInsertionAdapter<SuccessStory> __insertionAdapterOfSuccessStory;

  private final EntityDeletionOrUpdateAdapter<Tip> __deletionAdapterOfTip;

  private final EntityDeletionOrUpdateAdapter<Tip> __updateAdapterOfTip;

  private final SharedSQLiteStatement __preparedStmtOfSetBookmarked;

  public TipDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTip = new EntityInsertionAdapter<Tip>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `tips` (`id`,`titleEn`,`titleKn`,`titleHi`,`descriptionEn`,`descriptionKn`,`descriptionHi`,`actionEn`,`actionKn`,`actionHi`,`cropCategory`,`imageResName`,`tipType`,`season`,`isBookmarked`,`dateAdded`,`priority`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Tip entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitleEn());
        statement.bindString(3, entity.getTitleKn());
        statement.bindString(4, entity.getTitleHi());
        statement.bindString(5, entity.getDescriptionEn());
        statement.bindString(6, entity.getDescriptionKn());
        statement.bindString(7, entity.getDescriptionHi());
        statement.bindString(8, entity.getActionEn());
        statement.bindString(9, entity.getActionKn());
        statement.bindString(10, entity.getActionHi());
        statement.bindString(11, entity.getCropCategory());
        statement.bindString(12, entity.getImageResName());
        statement.bindString(13, entity.getTipType());
        statement.bindString(14, entity.getSeason());
        final int _tmp = entity.isBookmarked() ? 1 : 0;
        statement.bindLong(15, _tmp);
        statement.bindLong(16, entity.getDateAdded());
        statement.bindLong(17, entity.getPriority());
      }
    };
    this.__insertionAdapterOfSuccessStory = new EntityInsertionAdapter<SuccessStory>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `success_stories` (`id`,`farmerNameEn`,`farmerNameKn`,`farmerNameHi`,`locationEn`,`locationKn`,`locationHi`,`storyEn`,`storyKn`,`storyHi`,`cropCategory`,`yieldImprovement`,`imageResName`,`tipId`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SuccessStory entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getFarmerNameEn());
        statement.bindString(3, entity.getFarmerNameKn());
        statement.bindString(4, entity.getFarmerNameHi());
        statement.bindString(5, entity.getLocationEn());
        statement.bindString(6, entity.getLocationKn());
        statement.bindString(7, entity.getLocationHi());
        statement.bindString(8, entity.getStoryEn());
        statement.bindString(9, entity.getStoryKn());
        statement.bindString(10, entity.getStoryHi());
        statement.bindString(11, entity.getCropCategory());
        statement.bindString(12, entity.getYieldImprovement());
        statement.bindString(13, entity.getImageResName());
        statement.bindLong(14, entity.getTipId());
      }
    };
    this.__deletionAdapterOfTip = new EntityDeletionOrUpdateAdapter<Tip>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `tips` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Tip entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfTip = new EntityDeletionOrUpdateAdapter<Tip>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `tips` SET `id` = ?,`titleEn` = ?,`titleKn` = ?,`titleHi` = ?,`descriptionEn` = ?,`descriptionKn` = ?,`descriptionHi` = ?,`actionEn` = ?,`actionKn` = ?,`actionHi` = ?,`cropCategory` = ?,`imageResName` = ?,`tipType` = ?,`season` = ?,`isBookmarked` = ?,`dateAdded` = ?,`priority` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Tip entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitleEn());
        statement.bindString(3, entity.getTitleKn());
        statement.bindString(4, entity.getTitleHi());
        statement.bindString(5, entity.getDescriptionEn());
        statement.bindString(6, entity.getDescriptionKn());
        statement.bindString(7, entity.getDescriptionHi());
        statement.bindString(8, entity.getActionEn());
        statement.bindString(9, entity.getActionKn());
        statement.bindString(10, entity.getActionHi());
        statement.bindString(11, entity.getCropCategory());
        statement.bindString(12, entity.getImageResName());
        statement.bindString(13, entity.getTipType());
        statement.bindString(14, entity.getSeason());
        final int _tmp = entity.isBookmarked() ? 1 : 0;
        statement.bindLong(15, _tmp);
        statement.bindLong(16, entity.getDateAdded());
        statement.bindLong(17, entity.getPriority());
        statement.bindLong(18, entity.getId());
      }
    };
    this.__preparedStmtOfSetBookmarked = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE tips SET isBookmarked = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertTip(final Tip tip, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfTip.insertAndReturnId(tip);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAllTips(final List<Tip> tips, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTip.insert(tips);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAllStories(final List<SuccessStory> stories,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSuccessStory.insert(stories);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertStory(final SuccessStory story,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfSuccessStory.insertAndReturnId(story);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTip(final Tip tip, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTip.handle(tip);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateTip(final Tip tip, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTip.handle(tip);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object setBookmarked(final long tipId, final boolean bookmarked,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfSetBookmarked.acquire();
        int _argIndex = 1;
        final int _tmp = bookmarked ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, tipId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfSetBookmarked.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Tip>> getAllTips() {
    final String _sql = "SELECT * FROM tips ORDER BY priority DESC, dateAdded DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"tips"}, false, new Callable<List<Tip>>() {
      @Override
      @Nullable
      public List<Tip> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitleEn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleEn");
          final int _cursorIndexOfTitleKn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleKn");
          final int _cursorIndexOfTitleHi = CursorUtil.getColumnIndexOrThrow(_cursor, "titleHi");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfDescriptionKn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionKn");
          final int _cursorIndexOfDescriptionHi = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionHi");
          final int _cursorIndexOfActionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "actionEn");
          final int _cursorIndexOfActionKn = CursorUtil.getColumnIndexOrThrow(_cursor, "actionKn");
          final int _cursorIndexOfActionHi = CursorUtil.getColumnIndexOrThrow(_cursor, "actionHi");
          final int _cursorIndexOfCropCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "cropCategory");
          final int _cursorIndexOfImageResName = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResName");
          final int _cursorIndexOfTipType = CursorUtil.getColumnIndexOrThrow(_cursor, "tipType");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
          final int _cursorIndexOfDateAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "dateAdded");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final List<Tip> _result = new ArrayList<Tip>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Tip _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitleEn;
            _tmpTitleEn = _cursor.getString(_cursorIndexOfTitleEn);
            final String _tmpTitleKn;
            _tmpTitleKn = _cursor.getString(_cursorIndexOfTitleKn);
            final String _tmpTitleHi;
            _tmpTitleHi = _cursor.getString(_cursorIndexOfTitleHi);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpDescriptionKn;
            _tmpDescriptionKn = _cursor.getString(_cursorIndexOfDescriptionKn);
            final String _tmpDescriptionHi;
            _tmpDescriptionHi = _cursor.getString(_cursorIndexOfDescriptionHi);
            final String _tmpActionEn;
            _tmpActionEn = _cursor.getString(_cursorIndexOfActionEn);
            final String _tmpActionKn;
            _tmpActionKn = _cursor.getString(_cursorIndexOfActionKn);
            final String _tmpActionHi;
            _tmpActionHi = _cursor.getString(_cursorIndexOfActionHi);
            final String _tmpCropCategory;
            _tmpCropCategory = _cursor.getString(_cursorIndexOfCropCategory);
            final String _tmpImageResName;
            _tmpImageResName = _cursor.getString(_cursorIndexOfImageResName);
            final String _tmpTipType;
            _tmpTipType = _cursor.getString(_cursorIndexOfTipType);
            final String _tmpSeason;
            _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            final boolean _tmpIsBookmarked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
            _tmpIsBookmarked = _tmp != 0;
            final long _tmpDateAdded;
            _tmpDateAdded = _cursor.getLong(_cursorIndexOfDateAdded);
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            _item = new Tip(_tmpId,_tmpTitleEn,_tmpTitleKn,_tmpTitleHi,_tmpDescriptionEn,_tmpDescriptionKn,_tmpDescriptionHi,_tmpActionEn,_tmpActionKn,_tmpActionHi,_tmpCropCategory,_tmpImageResName,_tmpTipType,_tmpSeason,_tmpIsBookmarked,_tmpDateAdded,_tmpPriority);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getAllTipsList(final Continuation<? super List<Tip>> $completion) {
    final String _sql = "SELECT * FROM tips ORDER BY priority DESC, dateAdded DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Tip>>() {
      @Override
      @NonNull
      public List<Tip> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitleEn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleEn");
          final int _cursorIndexOfTitleKn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleKn");
          final int _cursorIndexOfTitleHi = CursorUtil.getColumnIndexOrThrow(_cursor, "titleHi");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfDescriptionKn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionKn");
          final int _cursorIndexOfDescriptionHi = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionHi");
          final int _cursorIndexOfActionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "actionEn");
          final int _cursorIndexOfActionKn = CursorUtil.getColumnIndexOrThrow(_cursor, "actionKn");
          final int _cursorIndexOfActionHi = CursorUtil.getColumnIndexOrThrow(_cursor, "actionHi");
          final int _cursorIndexOfCropCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "cropCategory");
          final int _cursorIndexOfImageResName = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResName");
          final int _cursorIndexOfTipType = CursorUtil.getColumnIndexOrThrow(_cursor, "tipType");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
          final int _cursorIndexOfDateAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "dateAdded");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final List<Tip> _result = new ArrayList<Tip>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Tip _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitleEn;
            _tmpTitleEn = _cursor.getString(_cursorIndexOfTitleEn);
            final String _tmpTitleKn;
            _tmpTitleKn = _cursor.getString(_cursorIndexOfTitleKn);
            final String _tmpTitleHi;
            _tmpTitleHi = _cursor.getString(_cursorIndexOfTitleHi);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpDescriptionKn;
            _tmpDescriptionKn = _cursor.getString(_cursorIndexOfDescriptionKn);
            final String _tmpDescriptionHi;
            _tmpDescriptionHi = _cursor.getString(_cursorIndexOfDescriptionHi);
            final String _tmpActionEn;
            _tmpActionEn = _cursor.getString(_cursorIndexOfActionEn);
            final String _tmpActionKn;
            _tmpActionKn = _cursor.getString(_cursorIndexOfActionKn);
            final String _tmpActionHi;
            _tmpActionHi = _cursor.getString(_cursorIndexOfActionHi);
            final String _tmpCropCategory;
            _tmpCropCategory = _cursor.getString(_cursorIndexOfCropCategory);
            final String _tmpImageResName;
            _tmpImageResName = _cursor.getString(_cursorIndexOfImageResName);
            final String _tmpTipType;
            _tmpTipType = _cursor.getString(_cursorIndexOfTipType);
            final String _tmpSeason;
            _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            final boolean _tmpIsBookmarked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
            _tmpIsBookmarked = _tmp != 0;
            final long _tmpDateAdded;
            _tmpDateAdded = _cursor.getLong(_cursorIndexOfDateAdded);
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            _item = new Tip(_tmpId,_tmpTitleEn,_tmpTitleKn,_tmpTitleHi,_tmpDescriptionEn,_tmpDescriptionKn,_tmpDescriptionHi,_tmpActionEn,_tmpActionKn,_tmpActionHi,_tmpCropCategory,_tmpImageResName,_tmpTipType,_tmpSeason,_tmpIsBookmarked,_tmpDateAdded,_tmpPriority);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Tip>> getTipsByCategory(final String category) {
    final String _sql = "SELECT * FROM tips WHERE cropCategory = ? ORDER BY priority DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, category);
    return __db.getInvalidationTracker().createLiveData(new String[] {"tips"}, false, new Callable<List<Tip>>() {
      @Override
      @Nullable
      public List<Tip> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitleEn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleEn");
          final int _cursorIndexOfTitleKn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleKn");
          final int _cursorIndexOfTitleHi = CursorUtil.getColumnIndexOrThrow(_cursor, "titleHi");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfDescriptionKn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionKn");
          final int _cursorIndexOfDescriptionHi = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionHi");
          final int _cursorIndexOfActionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "actionEn");
          final int _cursorIndexOfActionKn = CursorUtil.getColumnIndexOrThrow(_cursor, "actionKn");
          final int _cursorIndexOfActionHi = CursorUtil.getColumnIndexOrThrow(_cursor, "actionHi");
          final int _cursorIndexOfCropCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "cropCategory");
          final int _cursorIndexOfImageResName = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResName");
          final int _cursorIndexOfTipType = CursorUtil.getColumnIndexOrThrow(_cursor, "tipType");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
          final int _cursorIndexOfDateAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "dateAdded");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final List<Tip> _result = new ArrayList<Tip>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Tip _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitleEn;
            _tmpTitleEn = _cursor.getString(_cursorIndexOfTitleEn);
            final String _tmpTitleKn;
            _tmpTitleKn = _cursor.getString(_cursorIndexOfTitleKn);
            final String _tmpTitleHi;
            _tmpTitleHi = _cursor.getString(_cursorIndexOfTitleHi);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpDescriptionKn;
            _tmpDescriptionKn = _cursor.getString(_cursorIndexOfDescriptionKn);
            final String _tmpDescriptionHi;
            _tmpDescriptionHi = _cursor.getString(_cursorIndexOfDescriptionHi);
            final String _tmpActionEn;
            _tmpActionEn = _cursor.getString(_cursorIndexOfActionEn);
            final String _tmpActionKn;
            _tmpActionKn = _cursor.getString(_cursorIndexOfActionKn);
            final String _tmpActionHi;
            _tmpActionHi = _cursor.getString(_cursorIndexOfActionHi);
            final String _tmpCropCategory;
            _tmpCropCategory = _cursor.getString(_cursorIndexOfCropCategory);
            final String _tmpImageResName;
            _tmpImageResName = _cursor.getString(_cursorIndexOfImageResName);
            final String _tmpTipType;
            _tmpTipType = _cursor.getString(_cursorIndexOfTipType);
            final String _tmpSeason;
            _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            final boolean _tmpIsBookmarked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
            _tmpIsBookmarked = _tmp != 0;
            final long _tmpDateAdded;
            _tmpDateAdded = _cursor.getLong(_cursorIndexOfDateAdded);
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            _item = new Tip(_tmpId,_tmpTitleEn,_tmpTitleKn,_tmpTitleHi,_tmpDescriptionEn,_tmpDescriptionKn,_tmpDescriptionHi,_tmpActionEn,_tmpActionKn,_tmpActionHi,_tmpCropCategory,_tmpImageResName,_tmpTipType,_tmpSeason,_tmpIsBookmarked,_tmpDateAdded,_tmpPriority);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getTipsByCategoryList(final String category,
      final Continuation<? super List<Tip>> $completion) {
    final String _sql = "SELECT * FROM tips WHERE cropCategory = ? ORDER BY priority DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, category);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Tip>>() {
      @Override
      @NonNull
      public List<Tip> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitleEn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleEn");
          final int _cursorIndexOfTitleKn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleKn");
          final int _cursorIndexOfTitleHi = CursorUtil.getColumnIndexOrThrow(_cursor, "titleHi");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfDescriptionKn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionKn");
          final int _cursorIndexOfDescriptionHi = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionHi");
          final int _cursorIndexOfActionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "actionEn");
          final int _cursorIndexOfActionKn = CursorUtil.getColumnIndexOrThrow(_cursor, "actionKn");
          final int _cursorIndexOfActionHi = CursorUtil.getColumnIndexOrThrow(_cursor, "actionHi");
          final int _cursorIndexOfCropCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "cropCategory");
          final int _cursorIndexOfImageResName = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResName");
          final int _cursorIndexOfTipType = CursorUtil.getColumnIndexOrThrow(_cursor, "tipType");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
          final int _cursorIndexOfDateAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "dateAdded");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final List<Tip> _result = new ArrayList<Tip>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Tip _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitleEn;
            _tmpTitleEn = _cursor.getString(_cursorIndexOfTitleEn);
            final String _tmpTitleKn;
            _tmpTitleKn = _cursor.getString(_cursorIndexOfTitleKn);
            final String _tmpTitleHi;
            _tmpTitleHi = _cursor.getString(_cursorIndexOfTitleHi);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpDescriptionKn;
            _tmpDescriptionKn = _cursor.getString(_cursorIndexOfDescriptionKn);
            final String _tmpDescriptionHi;
            _tmpDescriptionHi = _cursor.getString(_cursorIndexOfDescriptionHi);
            final String _tmpActionEn;
            _tmpActionEn = _cursor.getString(_cursorIndexOfActionEn);
            final String _tmpActionKn;
            _tmpActionKn = _cursor.getString(_cursorIndexOfActionKn);
            final String _tmpActionHi;
            _tmpActionHi = _cursor.getString(_cursorIndexOfActionHi);
            final String _tmpCropCategory;
            _tmpCropCategory = _cursor.getString(_cursorIndexOfCropCategory);
            final String _tmpImageResName;
            _tmpImageResName = _cursor.getString(_cursorIndexOfImageResName);
            final String _tmpTipType;
            _tmpTipType = _cursor.getString(_cursorIndexOfTipType);
            final String _tmpSeason;
            _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            final boolean _tmpIsBookmarked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
            _tmpIsBookmarked = _tmp != 0;
            final long _tmpDateAdded;
            _tmpDateAdded = _cursor.getLong(_cursorIndexOfDateAdded);
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            _item = new Tip(_tmpId,_tmpTitleEn,_tmpTitleKn,_tmpTitleHi,_tmpDescriptionEn,_tmpDescriptionKn,_tmpDescriptionHi,_tmpActionEn,_tmpActionKn,_tmpActionHi,_tmpCropCategory,_tmpImageResName,_tmpTipType,_tmpSeason,_tmpIsBookmarked,_tmpDateAdded,_tmpPriority);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Tip>> getBookmarkedTips() {
    final String _sql = "SELECT * FROM tips WHERE isBookmarked = 1 ORDER BY dateAdded DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"tips"}, false, new Callable<List<Tip>>() {
      @Override
      @Nullable
      public List<Tip> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitleEn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleEn");
          final int _cursorIndexOfTitleKn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleKn");
          final int _cursorIndexOfTitleHi = CursorUtil.getColumnIndexOrThrow(_cursor, "titleHi");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfDescriptionKn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionKn");
          final int _cursorIndexOfDescriptionHi = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionHi");
          final int _cursorIndexOfActionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "actionEn");
          final int _cursorIndexOfActionKn = CursorUtil.getColumnIndexOrThrow(_cursor, "actionKn");
          final int _cursorIndexOfActionHi = CursorUtil.getColumnIndexOrThrow(_cursor, "actionHi");
          final int _cursorIndexOfCropCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "cropCategory");
          final int _cursorIndexOfImageResName = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResName");
          final int _cursorIndexOfTipType = CursorUtil.getColumnIndexOrThrow(_cursor, "tipType");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
          final int _cursorIndexOfDateAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "dateAdded");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final List<Tip> _result = new ArrayList<Tip>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Tip _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitleEn;
            _tmpTitleEn = _cursor.getString(_cursorIndexOfTitleEn);
            final String _tmpTitleKn;
            _tmpTitleKn = _cursor.getString(_cursorIndexOfTitleKn);
            final String _tmpTitleHi;
            _tmpTitleHi = _cursor.getString(_cursorIndexOfTitleHi);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpDescriptionKn;
            _tmpDescriptionKn = _cursor.getString(_cursorIndexOfDescriptionKn);
            final String _tmpDescriptionHi;
            _tmpDescriptionHi = _cursor.getString(_cursorIndexOfDescriptionHi);
            final String _tmpActionEn;
            _tmpActionEn = _cursor.getString(_cursorIndexOfActionEn);
            final String _tmpActionKn;
            _tmpActionKn = _cursor.getString(_cursorIndexOfActionKn);
            final String _tmpActionHi;
            _tmpActionHi = _cursor.getString(_cursorIndexOfActionHi);
            final String _tmpCropCategory;
            _tmpCropCategory = _cursor.getString(_cursorIndexOfCropCategory);
            final String _tmpImageResName;
            _tmpImageResName = _cursor.getString(_cursorIndexOfImageResName);
            final String _tmpTipType;
            _tmpTipType = _cursor.getString(_cursorIndexOfTipType);
            final String _tmpSeason;
            _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            final boolean _tmpIsBookmarked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
            _tmpIsBookmarked = _tmp != 0;
            final long _tmpDateAdded;
            _tmpDateAdded = _cursor.getLong(_cursorIndexOfDateAdded);
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            _item = new Tip(_tmpId,_tmpTitleEn,_tmpTitleKn,_tmpTitleHi,_tmpDescriptionEn,_tmpDescriptionKn,_tmpDescriptionHi,_tmpActionEn,_tmpActionKn,_tmpActionHi,_tmpCropCategory,_tmpImageResName,_tmpTipType,_tmpSeason,_tmpIsBookmarked,_tmpDateAdded,_tmpPriority);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getTipById(final long tipId, final Continuation<? super Tip> $completion) {
    final String _sql = "SELECT * FROM tips WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, tipId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Tip>() {
      @Override
      @Nullable
      public Tip call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitleEn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleEn");
          final int _cursorIndexOfTitleKn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleKn");
          final int _cursorIndexOfTitleHi = CursorUtil.getColumnIndexOrThrow(_cursor, "titleHi");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfDescriptionKn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionKn");
          final int _cursorIndexOfDescriptionHi = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionHi");
          final int _cursorIndexOfActionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "actionEn");
          final int _cursorIndexOfActionKn = CursorUtil.getColumnIndexOrThrow(_cursor, "actionKn");
          final int _cursorIndexOfActionHi = CursorUtil.getColumnIndexOrThrow(_cursor, "actionHi");
          final int _cursorIndexOfCropCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "cropCategory");
          final int _cursorIndexOfImageResName = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResName");
          final int _cursorIndexOfTipType = CursorUtil.getColumnIndexOrThrow(_cursor, "tipType");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
          final int _cursorIndexOfDateAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "dateAdded");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final Tip _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitleEn;
            _tmpTitleEn = _cursor.getString(_cursorIndexOfTitleEn);
            final String _tmpTitleKn;
            _tmpTitleKn = _cursor.getString(_cursorIndexOfTitleKn);
            final String _tmpTitleHi;
            _tmpTitleHi = _cursor.getString(_cursorIndexOfTitleHi);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpDescriptionKn;
            _tmpDescriptionKn = _cursor.getString(_cursorIndexOfDescriptionKn);
            final String _tmpDescriptionHi;
            _tmpDescriptionHi = _cursor.getString(_cursorIndexOfDescriptionHi);
            final String _tmpActionEn;
            _tmpActionEn = _cursor.getString(_cursorIndexOfActionEn);
            final String _tmpActionKn;
            _tmpActionKn = _cursor.getString(_cursorIndexOfActionKn);
            final String _tmpActionHi;
            _tmpActionHi = _cursor.getString(_cursorIndexOfActionHi);
            final String _tmpCropCategory;
            _tmpCropCategory = _cursor.getString(_cursorIndexOfCropCategory);
            final String _tmpImageResName;
            _tmpImageResName = _cursor.getString(_cursorIndexOfImageResName);
            final String _tmpTipType;
            _tmpTipType = _cursor.getString(_cursorIndexOfTipType);
            final String _tmpSeason;
            _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            final boolean _tmpIsBookmarked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
            _tmpIsBookmarked = _tmp != 0;
            final long _tmpDateAdded;
            _tmpDateAdded = _cursor.getLong(_cursorIndexOfDateAdded);
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            _result = new Tip(_tmpId,_tmpTitleEn,_tmpTitleKn,_tmpTitleHi,_tmpDescriptionEn,_tmpDescriptionKn,_tmpDescriptionHi,_tmpActionEn,_tmpActionKn,_tmpActionHi,_tmpCropCategory,_tmpImageResName,_tmpTipType,_tmpSeason,_tmpIsBookmarked,_tmpDateAdded,_tmpPriority);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Tip>> getTipsByType(final String type) {
    final String _sql = "SELECT * FROM tips WHERE tipType = ? ORDER BY priority DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, type);
    return __db.getInvalidationTracker().createLiveData(new String[] {"tips"}, false, new Callable<List<Tip>>() {
      @Override
      @Nullable
      public List<Tip> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitleEn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleEn");
          final int _cursorIndexOfTitleKn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleKn");
          final int _cursorIndexOfTitleHi = CursorUtil.getColumnIndexOrThrow(_cursor, "titleHi");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfDescriptionKn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionKn");
          final int _cursorIndexOfDescriptionHi = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionHi");
          final int _cursorIndexOfActionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "actionEn");
          final int _cursorIndexOfActionKn = CursorUtil.getColumnIndexOrThrow(_cursor, "actionKn");
          final int _cursorIndexOfActionHi = CursorUtil.getColumnIndexOrThrow(_cursor, "actionHi");
          final int _cursorIndexOfCropCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "cropCategory");
          final int _cursorIndexOfImageResName = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResName");
          final int _cursorIndexOfTipType = CursorUtil.getColumnIndexOrThrow(_cursor, "tipType");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
          final int _cursorIndexOfDateAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "dateAdded");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final List<Tip> _result = new ArrayList<Tip>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Tip _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitleEn;
            _tmpTitleEn = _cursor.getString(_cursorIndexOfTitleEn);
            final String _tmpTitleKn;
            _tmpTitleKn = _cursor.getString(_cursorIndexOfTitleKn);
            final String _tmpTitleHi;
            _tmpTitleHi = _cursor.getString(_cursorIndexOfTitleHi);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpDescriptionKn;
            _tmpDescriptionKn = _cursor.getString(_cursorIndexOfDescriptionKn);
            final String _tmpDescriptionHi;
            _tmpDescriptionHi = _cursor.getString(_cursorIndexOfDescriptionHi);
            final String _tmpActionEn;
            _tmpActionEn = _cursor.getString(_cursorIndexOfActionEn);
            final String _tmpActionKn;
            _tmpActionKn = _cursor.getString(_cursorIndexOfActionKn);
            final String _tmpActionHi;
            _tmpActionHi = _cursor.getString(_cursorIndexOfActionHi);
            final String _tmpCropCategory;
            _tmpCropCategory = _cursor.getString(_cursorIndexOfCropCategory);
            final String _tmpImageResName;
            _tmpImageResName = _cursor.getString(_cursorIndexOfImageResName);
            final String _tmpTipType;
            _tmpTipType = _cursor.getString(_cursorIndexOfTipType);
            final String _tmpSeason;
            _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            final boolean _tmpIsBookmarked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
            _tmpIsBookmarked = _tmp != 0;
            final long _tmpDateAdded;
            _tmpDateAdded = _cursor.getLong(_cursorIndexOfDateAdded);
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            _item = new Tip(_tmpId,_tmpTitleEn,_tmpTitleKn,_tmpTitleHi,_tmpDescriptionEn,_tmpDescriptionKn,_tmpDescriptionHi,_tmpActionEn,_tmpActionKn,_tmpActionHi,_tmpCropCategory,_tmpImageResName,_tmpTipType,_tmpSeason,_tmpIsBookmarked,_tmpDateAdded,_tmpPriority);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Tip>> getTipsBySeason(final String season) {
    final String _sql = "SELECT * FROM tips WHERE season = ? OR season = 'all' ORDER BY priority DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, season);
    return __db.getInvalidationTracker().createLiveData(new String[] {"tips"}, false, new Callable<List<Tip>>() {
      @Override
      @Nullable
      public List<Tip> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitleEn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleEn");
          final int _cursorIndexOfTitleKn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleKn");
          final int _cursorIndexOfTitleHi = CursorUtil.getColumnIndexOrThrow(_cursor, "titleHi");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfDescriptionKn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionKn");
          final int _cursorIndexOfDescriptionHi = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionHi");
          final int _cursorIndexOfActionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "actionEn");
          final int _cursorIndexOfActionKn = CursorUtil.getColumnIndexOrThrow(_cursor, "actionKn");
          final int _cursorIndexOfActionHi = CursorUtil.getColumnIndexOrThrow(_cursor, "actionHi");
          final int _cursorIndexOfCropCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "cropCategory");
          final int _cursorIndexOfImageResName = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResName");
          final int _cursorIndexOfTipType = CursorUtil.getColumnIndexOrThrow(_cursor, "tipType");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
          final int _cursorIndexOfDateAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "dateAdded");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final List<Tip> _result = new ArrayList<Tip>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Tip _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitleEn;
            _tmpTitleEn = _cursor.getString(_cursorIndexOfTitleEn);
            final String _tmpTitleKn;
            _tmpTitleKn = _cursor.getString(_cursorIndexOfTitleKn);
            final String _tmpTitleHi;
            _tmpTitleHi = _cursor.getString(_cursorIndexOfTitleHi);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpDescriptionKn;
            _tmpDescriptionKn = _cursor.getString(_cursorIndexOfDescriptionKn);
            final String _tmpDescriptionHi;
            _tmpDescriptionHi = _cursor.getString(_cursorIndexOfDescriptionHi);
            final String _tmpActionEn;
            _tmpActionEn = _cursor.getString(_cursorIndexOfActionEn);
            final String _tmpActionKn;
            _tmpActionKn = _cursor.getString(_cursorIndexOfActionKn);
            final String _tmpActionHi;
            _tmpActionHi = _cursor.getString(_cursorIndexOfActionHi);
            final String _tmpCropCategory;
            _tmpCropCategory = _cursor.getString(_cursorIndexOfCropCategory);
            final String _tmpImageResName;
            _tmpImageResName = _cursor.getString(_cursorIndexOfImageResName);
            final String _tmpTipType;
            _tmpTipType = _cursor.getString(_cursorIndexOfTipType);
            final String _tmpSeason;
            _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            final boolean _tmpIsBookmarked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
            _tmpIsBookmarked = _tmp != 0;
            final long _tmpDateAdded;
            _tmpDateAdded = _cursor.getLong(_cursorIndexOfDateAdded);
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            _item = new Tip(_tmpId,_tmpTitleEn,_tmpTitleKn,_tmpTitleHi,_tmpDescriptionEn,_tmpDescriptionKn,_tmpDescriptionHi,_tmpActionEn,_tmpActionKn,_tmpActionHi,_tmpCropCategory,_tmpImageResName,_tmpTipType,_tmpSeason,_tmpIsBookmarked,_tmpDateAdded,_tmpPriority);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Tip>> searchTips(final String query) {
    final String _sql = "SELECT * FROM tips WHERE titleEn LIKE '%' || ? || '%' OR descriptionEn LIKE '%' || ? || '%' OR titleKn LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    _argIndex = 3;
    _statement.bindString(_argIndex, query);
    return __db.getInvalidationTracker().createLiveData(new String[] {"tips"}, false, new Callable<List<Tip>>() {
      @Override
      @Nullable
      public List<Tip> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitleEn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleEn");
          final int _cursorIndexOfTitleKn = CursorUtil.getColumnIndexOrThrow(_cursor, "titleKn");
          final int _cursorIndexOfTitleHi = CursorUtil.getColumnIndexOrThrow(_cursor, "titleHi");
          final int _cursorIndexOfDescriptionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionEn");
          final int _cursorIndexOfDescriptionKn = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionKn");
          final int _cursorIndexOfDescriptionHi = CursorUtil.getColumnIndexOrThrow(_cursor, "descriptionHi");
          final int _cursorIndexOfActionEn = CursorUtil.getColumnIndexOrThrow(_cursor, "actionEn");
          final int _cursorIndexOfActionKn = CursorUtil.getColumnIndexOrThrow(_cursor, "actionKn");
          final int _cursorIndexOfActionHi = CursorUtil.getColumnIndexOrThrow(_cursor, "actionHi");
          final int _cursorIndexOfCropCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "cropCategory");
          final int _cursorIndexOfImageResName = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResName");
          final int _cursorIndexOfTipType = CursorUtil.getColumnIndexOrThrow(_cursor, "tipType");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfIsBookmarked = CursorUtil.getColumnIndexOrThrow(_cursor, "isBookmarked");
          final int _cursorIndexOfDateAdded = CursorUtil.getColumnIndexOrThrow(_cursor, "dateAdded");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final List<Tip> _result = new ArrayList<Tip>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Tip _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitleEn;
            _tmpTitleEn = _cursor.getString(_cursorIndexOfTitleEn);
            final String _tmpTitleKn;
            _tmpTitleKn = _cursor.getString(_cursorIndexOfTitleKn);
            final String _tmpTitleHi;
            _tmpTitleHi = _cursor.getString(_cursorIndexOfTitleHi);
            final String _tmpDescriptionEn;
            _tmpDescriptionEn = _cursor.getString(_cursorIndexOfDescriptionEn);
            final String _tmpDescriptionKn;
            _tmpDescriptionKn = _cursor.getString(_cursorIndexOfDescriptionKn);
            final String _tmpDescriptionHi;
            _tmpDescriptionHi = _cursor.getString(_cursorIndexOfDescriptionHi);
            final String _tmpActionEn;
            _tmpActionEn = _cursor.getString(_cursorIndexOfActionEn);
            final String _tmpActionKn;
            _tmpActionKn = _cursor.getString(_cursorIndexOfActionKn);
            final String _tmpActionHi;
            _tmpActionHi = _cursor.getString(_cursorIndexOfActionHi);
            final String _tmpCropCategory;
            _tmpCropCategory = _cursor.getString(_cursorIndexOfCropCategory);
            final String _tmpImageResName;
            _tmpImageResName = _cursor.getString(_cursorIndexOfImageResName);
            final String _tmpTipType;
            _tmpTipType = _cursor.getString(_cursorIndexOfTipType);
            final String _tmpSeason;
            _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            final boolean _tmpIsBookmarked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsBookmarked);
            _tmpIsBookmarked = _tmp != 0;
            final long _tmpDateAdded;
            _tmpDateAdded = _cursor.getLong(_cursorIndexOfDateAdded);
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            _item = new Tip(_tmpId,_tmpTitleEn,_tmpTitleKn,_tmpTitleHi,_tmpDescriptionEn,_tmpDescriptionKn,_tmpDescriptionHi,_tmpActionEn,_tmpActionKn,_tmpActionHi,_tmpCropCategory,_tmpImageResName,_tmpTipType,_tmpSeason,_tmpIsBookmarked,_tmpDateAdded,_tmpPriority);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getTipCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM tips";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<SuccessStory>> getAllStories() {
    final String _sql = "SELECT * FROM success_stories ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"success_stories"}, false, new Callable<List<SuccessStory>>() {
      @Override
      @Nullable
      public List<SuccessStory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFarmerNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "farmerNameEn");
          final int _cursorIndexOfFarmerNameKn = CursorUtil.getColumnIndexOrThrow(_cursor, "farmerNameKn");
          final int _cursorIndexOfFarmerNameHi = CursorUtil.getColumnIndexOrThrow(_cursor, "farmerNameHi");
          final int _cursorIndexOfLocationEn = CursorUtil.getColumnIndexOrThrow(_cursor, "locationEn");
          final int _cursorIndexOfLocationKn = CursorUtil.getColumnIndexOrThrow(_cursor, "locationKn");
          final int _cursorIndexOfLocationHi = CursorUtil.getColumnIndexOrThrow(_cursor, "locationHi");
          final int _cursorIndexOfStoryEn = CursorUtil.getColumnIndexOrThrow(_cursor, "storyEn");
          final int _cursorIndexOfStoryKn = CursorUtil.getColumnIndexOrThrow(_cursor, "storyKn");
          final int _cursorIndexOfStoryHi = CursorUtil.getColumnIndexOrThrow(_cursor, "storyHi");
          final int _cursorIndexOfCropCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "cropCategory");
          final int _cursorIndexOfYieldImprovement = CursorUtil.getColumnIndexOrThrow(_cursor, "yieldImprovement");
          final int _cursorIndexOfImageResName = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResName");
          final int _cursorIndexOfTipId = CursorUtil.getColumnIndexOrThrow(_cursor, "tipId");
          final List<SuccessStory> _result = new ArrayList<SuccessStory>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SuccessStory _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpFarmerNameEn;
            _tmpFarmerNameEn = _cursor.getString(_cursorIndexOfFarmerNameEn);
            final String _tmpFarmerNameKn;
            _tmpFarmerNameKn = _cursor.getString(_cursorIndexOfFarmerNameKn);
            final String _tmpFarmerNameHi;
            _tmpFarmerNameHi = _cursor.getString(_cursorIndexOfFarmerNameHi);
            final String _tmpLocationEn;
            _tmpLocationEn = _cursor.getString(_cursorIndexOfLocationEn);
            final String _tmpLocationKn;
            _tmpLocationKn = _cursor.getString(_cursorIndexOfLocationKn);
            final String _tmpLocationHi;
            _tmpLocationHi = _cursor.getString(_cursorIndexOfLocationHi);
            final String _tmpStoryEn;
            _tmpStoryEn = _cursor.getString(_cursorIndexOfStoryEn);
            final String _tmpStoryKn;
            _tmpStoryKn = _cursor.getString(_cursorIndexOfStoryKn);
            final String _tmpStoryHi;
            _tmpStoryHi = _cursor.getString(_cursorIndexOfStoryHi);
            final String _tmpCropCategory;
            _tmpCropCategory = _cursor.getString(_cursorIndexOfCropCategory);
            final String _tmpYieldImprovement;
            _tmpYieldImprovement = _cursor.getString(_cursorIndexOfYieldImprovement);
            final String _tmpImageResName;
            _tmpImageResName = _cursor.getString(_cursorIndexOfImageResName);
            final long _tmpTipId;
            _tmpTipId = _cursor.getLong(_cursorIndexOfTipId);
            _item = new SuccessStory(_tmpId,_tmpFarmerNameEn,_tmpFarmerNameKn,_tmpFarmerNameHi,_tmpLocationEn,_tmpLocationKn,_tmpLocationHi,_tmpStoryEn,_tmpStoryKn,_tmpStoryHi,_tmpCropCategory,_tmpYieldImprovement,_tmpImageResName,_tmpTipId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<SuccessStory>> getStoriesByCategory(final String category) {
    final String _sql = "SELECT * FROM success_stories WHERE cropCategory = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, category);
    return __db.getInvalidationTracker().createLiveData(new String[] {"success_stories"}, false, new Callable<List<SuccessStory>>() {
      @Override
      @Nullable
      public List<SuccessStory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFarmerNameEn = CursorUtil.getColumnIndexOrThrow(_cursor, "farmerNameEn");
          final int _cursorIndexOfFarmerNameKn = CursorUtil.getColumnIndexOrThrow(_cursor, "farmerNameKn");
          final int _cursorIndexOfFarmerNameHi = CursorUtil.getColumnIndexOrThrow(_cursor, "farmerNameHi");
          final int _cursorIndexOfLocationEn = CursorUtil.getColumnIndexOrThrow(_cursor, "locationEn");
          final int _cursorIndexOfLocationKn = CursorUtil.getColumnIndexOrThrow(_cursor, "locationKn");
          final int _cursorIndexOfLocationHi = CursorUtil.getColumnIndexOrThrow(_cursor, "locationHi");
          final int _cursorIndexOfStoryEn = CursorUtil.getColumnIndexOrThrow(_cursor, "storyEn");
          final int _cursorIndexOfStoryKn = CursorUtil.getColumnIndexOrThrow(_cursor, "storyKn");
          final int _cursorIndexOfStoryHi = CursorUtil.getColumnIndexOrThrow(_cursor, "storyHi");
          final int _cursorIndexOfCropCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "cropCategory");
          final int _cursorIndexOfYieldImprovement = CursorUtil.getColumnIndexOrThrow(_cursor, "yieldImprovement");
          final int _cursorIndexOfImageResName = CursorUtil.getColumnIndexOrThrow(_cursor, "imageResName");
          final int _cursorIndexOfTipId = CursorUtil.getColumnIndexOrThrow(_cursor, "tipId");
          final List<SuccessStory> _result = new ArrayList<SuccessStory>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SuccessStory _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpFarmerNameEn;
            _tmpFarmerNameEn = _cursor.getString(_cursorIndexOfFarmerNameEn);
            final String _tmpFarmerNameKn;
            _tmpFarmerNameKn = _cursor.getString(_cursorIndexOfFarmerNameKn);
            final String _tmpFarmerNameHi;
            _tmpFarmerNameHi = _cursor.getString(_cursorIndexOfFarmerNameHi);
            final String _tmpLocationEn;
            _tmpLocationEn = _cursor.getString(_cursorIndexOfLocationEn);
            final String _tmpLocationKn;
            _tmpLocationKn = _cursor.getString(_cursorIndexOfLocationKn);
            final String _tmpLocationHi;
            _tmpLocationHi = _cursor.getString(_cursorIndexOfLocationHi);
            final String _tmpStoryEn;
            _tmpStoryEn = _cursor.getString(_cursorIndexOfStoryEn);
            final String _tmpStoryKn;
            _tmpStoryKn = _cursor.getString(_cursorIndexOfStoryKn);
            final String _tmpStoryHi;
            _tmpStoryHi = _cursor.getString(_cursorIndexOfStoryHi);
            final String _tmpCropCategory;
            _tmpCropCategory = _cursor.getString(_cursorIndexOfCropCategory);
            final String _tmpYieldImprovement;
            _tmpYieldImprovement = _cursor.getString(_cursorIndexOfYieldImprovement);
            final String _tmpImageResName;
            _tmpImageResName = _cursor.getString(_cursorIndexOfImageResName);
            final long _tmpTipId;
            _tmpTipId = _cursor.getLong(_cursorIndexOfTipId);
            _item = new SuccessStory(_tmpId,_tmpFarmerNameEn,_tmpFarmerNameKn,_tmpFarmerNameHi,_tmpLocationEn,_tmpLocationKn,_tmpLocationHi,_tmpStoryEn,_tmpStoryKn,_tmpStoryHi,_tmpCropCategory,_tmpYieldImprovement,_tmpImageResName,_tmpTipId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getStoryCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM success_stories";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

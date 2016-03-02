package cn.figo.mydemo.http;


import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * 缓存对象
 * @author Ligx
 *
 */
@Table("httpcache")
public class Cache {

	public static final String COL_ID = "_id";
	public static final String COL_KEY = "_key";
	public static final String COL_RESULT= "_result";
	public static final String COL_UPDATETIME= "_updatetime";
	public static final String COL_EXPIRETIME= "_expiretime";

	public Cache() {
	}

	public Cache(Long updateTime, Long id, String key, String result) {
		this.updateTime = updateTime;
		this.id = id;
		this.key = key;
		this.result = result;
	}

	@PrimaryKey(AssignType.AUTO_INCREMENT)
	@Column(COL_ID)
	public Long id;

	@NotNull
	@Column(COL_KEY)
	public String key;

	@NotNull
	@Column(COL_RESULT)
	public String result;

	@NotNull
	@Column(COL_UPDATETIME)
	public Long updateTime;

	@NotNull
	@Column(COL_EXPIRETIME)
	public Long expireTime=-1l;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}
}

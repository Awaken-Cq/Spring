package com.kitri.cafe.board.model;

public class AlbumDto extends BoardDto {

	private int aseq;
	private String savefolder;
	private String originalpicture;
	private String savepicture;
	private String picturemode;

	public int getAseq() {
		return aseq;
	}

	public void setAseq(int aseq) {
		this.aseq = aseq;
	}

	public String getSavefolder() {
		return savefolder;
	}

	public void setSavefolder(String savefolder) {
		this.savefolder = savefolder;
	}

	public String getOriginalpicture() {
		return originalpicture;
	}

	public void setOriginalpicture(String originalpicture) {
		this.originalpicture = originalpicture;
	}

	public String getSavepicture() {
		return savepicture;
	}

	public void setSavepicture(String savepicture) {
		this.savepicture = savepicture;
	}

	public String getPicturemode() {
		return picturemode;
	}

	public void setPicturemode(String picturemode) {
		this.picturemode = picturemode;
	}

}

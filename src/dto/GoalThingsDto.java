package dto;

public class GoalThingsDto {
	private String title;	// 목표 제목
	private int periodIdx;	// 기간idx
	private String profileImg;	// 프로필 이미지
	private String projectPeriodName;	// 기간 이름
	
	public GoalThingsDto(String title, int periodIdx, String profileImg, String projectPeriodName) {
		this.title = title;
		this.periodIdx = periodIdx;
		this.profileImg = profileImg;
		this.projectPeriodName = projectPeriodName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPeriodIdx() {
		return periodIdx;
	}

	public void setPeriodIdx(int periodIdx) {
		this.periodIdx = periodIdx;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getProjectPeriodName() {
		return projectPeriodName;
	}

	public void setProjectPeriodName(String projectPeriodName) {
		this.projectPeriodName = projectPeriodName;
	}
	
}

package showcase.mvc.form;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class FormBean {

	@Size(min = 1, max = 20)
	private String name;

	@Min(1)
	private int age;

	@DateTimeFormat(iso = ISO.DATE)
	private Date birthDate;

	private String phone;

	@NumberFormat(pattern = "$###,###.00")
	private BigDecimal currency = BigDecimal.ZERO;

	@NumberFormat(style = Style.PERCENT)
	private BigDecimal percent = BigDecimal.ZERO;
	
	private InquiryType inquiry;
	
	private String inquiryDetails;
	
	private boolean subscribeNewsletter = false;
	
	private Map<String, String> additionalInfo;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public BigDecimal getCurrency() {
		return currency;
	}

	public void setCurrency(BigDecimal currency) {
		this.currency = currency;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}

	public InquiryType getInquiry() {
		return inquiry;
	}

	public void setInquiry(InquiryType inquiry) {
		this.inquiry = inquiry;
	}

	public String getInquiryDetails() {
		return inquiryDetails;
	}

	public void setInquiryDetails(String inquiryDetails) {
		this.inquiryDetails = inquiryDetails;
	}

	public boolean isSubscribeNewsletter() {
		return subscribeNewsletter;
	}

	public void setSubscribeNewsletter(boolean subscribeNewsletter) {
		this.subscribeNewsletter = subscribeNewsletter;
	}

	public Map<String, String> getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(Map<String, String> additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("FormBean{");
		sb.append("name='").append(name).append('\'');
		sb.append(", age=").append(age);
		sb.append(", birthDate=").append(birthDate);
		sb.append(", phone='").append(phone).append('\'');
		sb.append(", currency=").append(currency);
		sb.append(", percent=").append(percent);
		sb.append(", inquiry=").append(inquiry);
		sb.append(", inquiryDetails='").append(inquiryDetails).append('\'');
		sb.append(", subscribeNewsletter=").append(subscribeNewsletter);
		sb.append(", additionalInfo=").append(additionalInfo);
		sb.append('}');
		return sb.toString();
	}

}

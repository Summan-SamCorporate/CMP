package com.java.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "Extraction")

public class Extraction {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "extraction_code")
	private String extraction_code;
	
	@Column(name = "container_code")
	private String container_code;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "column1")
	private String column1;
	@Column(name = "column2")
	private String column2;
	@Column(name = "column3")
	private String column3;
	@Column(name = "column4")
	private String column4;
	@Column(name = "column5")
	private String column5;
	@Column(name = "column6")
	private String column6;
	@Column(name = "column7")
	private String column7;
	@Column(name = "column8")
	private String column8;
	@Column(name = "column9")
	private String column9;
	@Column(name = "column10")
	private String column10;
	@Column(name = "column11")
	private String column11;
	@Column(name = "column12")
	private String column12;
	@Column(name = "column13")
	private String column13;
	@Column(name = "column14")
	private String column14;
	@Column(name = "column15")
	private String column15;
	@Column(name = "column16")
	private String column16;
	@Column(name = "column17")
	private String column17;
	@Column(name = "column18")
	private String column18;
	@Column(name = "column19")
	private String column19;
	@Column(name = "column20")
	private String column20;
	@Column(name = "column21")
	private String column21;
	@Column(name = "column22")
	private String column22;
	@Column(name = "column23")
	private String column23;
	@Column(name = "column24")
	private String column24;
	@Column(name = "column25")
	private String column25;
	@Column(name = "column26")
	private String column26;
	@Column(name = "column27")
	private String column27;
	@Column(name = "column28")
	private String column28;
	@Column(name = "column29")
	private String column29;
	@Column(name = "column30")
	private String column30;
	@Column(name = "column31")
	private String column31;
	@Column(name = "column32")
	private String column32;
	@Column(name = "column33")
	private String column33;
	@Column(name = "column34")
	private String column34;
	@Column(name = "column35")
	private String column35;
	@Column(name = "column36")
	private String column36;
	@Column(name = "column37")
	private String column37;
	@Column(name = "column38")
	private String column38;
	@Column(name = "column39")
	private String column39;
	@Column(name = "column40")
	private String column40;
	@Column(name = "column41")
	private String column41;
	@Column(name = "column42")
	private String column42;
	@Column(name = "column43")
	private String column43;
	@Column(name = "column44")
	private String column44;
	@Column(name = "column45")
	private String column45;
	@Column(name = "column46")
	private String column46;
	@Column(name = "column47")
	private String column47;
	@Column(name = "column48")
	private String column48;
	@Column(name = "column49")
	private String column49;
	@Column(name = "column50")
	private String column50;
	@Column(name = "column51")
	private String column51;
	@Column(name = "column52")
	private String column52;
	@Column(name = "column53")
	private String column53;
	@Column(name = "column54")
	private String column54;
	@Column(name = "column55")
	private String column55;
	@Column(name = "column56")
	private String column56;
	@Column(name = "column57")
	private String column57;
	@Column(name = "column58")
	private String column58;
	@Column(name = "column59")
	private String column59;
	@Column(name = "column60")
	private String column60;
	@Column(name = "column61")
	private String column61;
	@Column(name = "column62")
	private String column62;
	@Column(name = "column63")
	private String column63;
	@Column(name = "column64")
	private String column64;
	@Column(name = "column65")
	private String column65;
	@Column(name = "column66")
	private String column66;
	@Column(name = "column67")
	private String column67;
	@Column(name = "column68")
	private String column68;
	@Column(name = "column69")
	private String column69;
	@Column(name = "column70")
	private String column70;
	@Column(name = "column71")
	private String column71;
	@Column(name = "column72")
	private String column72;
	@Column(name = "column73")
	private String column73;
	@Column(name = "column74")
	private String column74;
	@Column(name = "column75")
	private String column75;
	@Column(name = "column76")
	private String column76;
	@Column(name = "column77")
	private String column77;
	@Column(name = "column78")
	private String column78;
	@Column(name = "column79")
	private String column79;
	@Column(name = "column80")
	private String column80;
	@Column(name = "column81")
	private String column81;
	@Column(name = "column82")
	private String column82;
	@Column(name = "column83")
	private String column83;
	@Column(name = "column84")
	private String column84;
	@Column(name = "column85")
	private String column85;
	@Column(name = "column86")
	private String column86;
	@Column(name = "column87")
	private String column87;
	@Column(name = "column88")
	private String column88;
	@Column(name = "column89")
	private String column89;
	@Column(name = "column90")
	private String column90;
	@Column(name = "column91")
	private String column91;
	@Column(name = "column92")
	private String column92;
	@Column(name = "column93")
	private String column93;
	@Column(name = "column94")
	private String column94;
	@Column(name = "column95")
	private String column95;
	@Column(name = "column96")
	private String column96;
	@Column(name = "column97")
	private String column97;
	@Column(name = "column98")
	private String column98;
	@Column(name = "column99")
	private String column99;
	@Column(name = "column100")
	private String column100;

	
	
	@Column(name = "column101")
	private String column101;
	@Column(name = "column102")
	private String column102;
	@Column(name = "column103")
	private String column103;
	@Column(name = "column104")
	private String column104;
	@Column(name = "column105")
	private String column105;
	@Column(name = "column106")
	private String column106;
	@Column(name = "column107")
	private String column107;
	@Column(name = "column108")
	private String column108;
	@Column(name = "column109")
	private String column109;
	@Column(name = "column110")
	private String column110;
	@Column(name = "column111")
	private String column111;
	@Column(name = "column112")
	private String column112;
	@Column(name = "column113")
	private String column113;
	@Column(name = "column114")
	private String column114;
	@Column(name = "column115")
	private String column115;
	@Column(name = "column116")
	private String column116;
	@Column(name = "column117")
	private String column117;
	@Column(name = "column118")
	private String column118;
	@Column(name = "column119")
	private String column119;
	@Column(name = "column120")
	private String column120;
	@Column(name = "column121")
	private String column121;
	@Column(name = "column122")
	private String column122;
	@Column(name = "column123")
	private String column123;
	@Column(name = "column124")
	private String column124;
	@Column(name = "column125")
	private String column125;
	@Column(name = "column126")
	private String column126;
	@Column(name = "column127")
	private String column127;
	@Column(name = "column128")
	private String column128;
	@Column(name = "column129")
	private String column129;
	@Column(name = "column130")
	private String column130;
	@Column(name = "column131")
	private String column131;
	@Column(name = "column132")
	private String column132;
	@Column(name = "column133")
	private String column133;
	@Column(name = "column134")
	private String column134;
	@Column(name = "column135")
	private String column135;
	@Column(name = "column136")
	private String column136;
	@Column(name = "column137")
	private String column137;
	@Column(name = "column138")
	private String column138;
	@Column(name = "column139")
	private String column139;
	@Column(name = "column140")
	private String column140;
	@Column(name = "column141")
	private String column141;
	@Column(name = "column142")
	private String column142;
	@Column(name = "column143")
	private String column143;
	@Column(name = "column144")
	private String column144;
	@Column(name = "column145")
	private String column145;
	@Column(name = "column146")
	private String column146;
	@Column(name = "column147")
	private String column147;
	@Column(name = "column148")
	private String column148;
	@Column(name = "column149")
	private String column149;
	@Column(name = "column150")
	private String column150;
	@Column(name = "column151")
	private String column151;
	@Column(name = "column152")
	private String column152;
	@Column(name = "column153")
	private String column153;
	@Column(name = "column154")
	private String column154;
	@Column(name = "column155")
	private String column155;
	@Column(name = "column156")
	private String column156;
	@Column(name = "column157")
	private String column157;
	@Column(name = "column158")
	private String column158;
	@Column(name = "column159")
	private String column159;
	@Column(name = "column160")
	private String column160;
	@Column(name = "column161")
	private String column161;
	@Column(name = "column162")
	private String column162;
	@Column(name = "column163")
	private String column163;
	@Column(name = "column164")
	private String column164;
	@Column(name = "column165")
	private String column165;
	@Column(name = "column166")
	private String column166;
	@Column(name = "column167")
	private String column167;
	@Column(name = "column168")
	private String column168;
	@Column(name = "column169")
	private String column169;
	@Column(name = "column170")
	private String column170;
	@Column(name = "column171")
	private String column171;
	@Column(name = "column172")
	private String column172;
	@Column(name = "column173")
	private String column173;
	@Column(name = "column174")
	private String column174;
	@Column(name = "column175")
	private String column175;
	@Column(name = "column176")
	private String column176;
	@Column(name = "column177")
	private String column177;
	@Column(name = "column178")
	private String column178;
	@Column(name = "column179")
	private String column179;
	@Column(name = "column180")
	private String column180;
	@Column(name = "column181")
	private String column181;
	@Column(name = "column182")
	private String column182;
	@Column(name = "column183")
	private String column183;
	@Column(name = "column184")
	private String column184;
	@Column(name = "column185")
	private String column185;
	@Column(name = "column186")
	private String column186;
	@Column(name = "column187")
	private String column187;
	@Column(name = "column188")
	private String column188;
	@Column(name = "column189")
	private String column189;
	@Column(name = "column190")
	private String column190;
	@Column(name = "column191")
	private String column191;
	@Column(name = "column192")
	private String column192;
	@Column(name = "column193")
	private String column193;
	@Column(name = "column194")
	private String column194;
	@Column(name = "column195")
	private String column195;
	@Column(name = "column196")
	private String column196;
	@Column(name = "column197")
	private String column197;
	@Column(name = "column198")
	private String column198;
	@Column(name = "column199")
	private String column199;
	@Column(name = "column200")
	private String column200;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Column(name = "created_user")
	private String created_user;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date created_date;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_date")
	private Date updated_date;

	@Column(name = "column_status")
	private String column_status;

	public String getExtraction_code() {
		return extraction_code;
	}

	public void setExtraction_code(String extraction_code) {
		this.extraction_code = extraction_code;
	}

	public String getContainer_code() {
		return container_code;
	}

	public void setContainer_code(String container_code) {
		this.container_code = container_code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}

	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}

	public String getColumn5() {
		return column5;
	}

	public void setColumn5(String column5) {
		this.column5 = column5;
	}

	public String getColumn6() {
		return column6;
	}

	public void setColumn6(String column6) {
		this.column6 = column6;
	}

	public String getColumn7() {
		return column7;
	}

	public void setColumn7(String column7) {
		this.column7 = column7;
	}

	public String getColumn8() {
		return column8;
	}

	public void setColumn8(String column8) {
		this.column8 = column8;
	}

	public String getColumn9() {
		return column9;
	}

	public void setColumn9(String column9) {
		this.column9 = column9;
	}

	public String getColumn10() {
		return column10;
	}

	public void setColumn10(String column10) {
		this.column10 = column10;
	}

	public String getColumn11() {
		return column11;
	}

	public void setColumn11(String column11) {
		this.column11 = column11;
	}

	public String getColumn12() {
		return column12;
	}

	public void setColumn12(String column12) {
		this.column12 = column12;
	}

	public String getColumn13() {
		return column13;
	}

	public void setColumn13(String column13) {
		this.column13 = column13;
	}

	public String getColumn14() {
		return column14;
	}

	public void setColumn14(String column14) {
		this.column14 = column14;
	}

	public String getColumn15() {
		return column15;
	}

	public void setColumn15(String column15) {
		this.column15 = column15;
	}

	public String getColumn16() {
		return column16;
	}

	public void setColumn16(String column16) {
		this.column16 = column16;
	}

	public String getColumn17() {
		return column17;
	}

	public void setColumn17(String column17) {
		this.column17 = column17;
	}

	public String getColumn18() {
		return column18;
	}

	public void setColumn18(String column18) {
		this.column18 = column18;
	}

	public String getColumn19() {
		return column19;
	}

	public void setColumn19(String column19) {
		this.column19 = column19;
	}

	public String getColumn20() {
		return column20;
	}

	public void setColumn20(String column20) {
		this.column20 = column20;
	}

	public String getColumn21() {
		return column21;
	}

	public void setColumn21(String column21) {
		this.column21 = column21;
	}

	public String getColumn22() {
		return column22;
	}

	public void setColumn22(String column22) {
		this.column22 = column22;
	}

	public String getColumn23() {
		return column23;
	}

	public void setColumn23(String column23) {
		this.column23 = column23;
	}

	public String getColumn24() {
		return column24;
	}

	public void setColumn24(String column24) {
		this.column24 = column24;
	}

	public String getColumn25() {
		return column25;
	}

	public void setColumn25(String column25) {
		this.column25 = column25;
	}

	public String getColumn26() {
		return column26;
	}

	public void setColumn26(String column26) {
		this.column26 = column26;
	}

	public String getColumn27() {
		return column27;
	}

	public void setColumn27(String column27) {
		this.column27 = column27;
	}

	public String getColumn28() {
		return column28;
	}

	public void setColumn28(String column28) {
		this.column28 = column28;
	}

	public String getColumn29() {
		return column29;
	}

	public void setColumn29(String column29) {
		this.column29 = column29;
	}

	public String getColumn30() {
		return column30;
	}

	public void setColumn30(String column30) {
		this.column30 = column30;
	}

	public String getColumn31() {
		return column31;
	}

	public void setColumn31(String column31) {
		this.column31 = column31;
	}

	public String getColumn32() {
		return column32;
	}

	public void setColumn32(String column32) {
		this.column32 = column32;
	}

	public String getColumn33() {
		return column33;
	}

	public void setColumn33(String column33) {
		this.column33 = column33;
	}

	public String getColumn34() {
		return column34;
	}

	public void setColumn34(String column34) {
		this.column34 = column34;
	}

	public String getColumn35() {
		return column35;
	}

	public void setColumn35(String column35) {
		this.column35 = column35;
	}

	public String getColumn36() {
		return column36;
	}

	public void setColumn36(String column36) {
		this.column36 = column36;
	}

	public String getColumn37() {
		return column37;
	}

	public void setColumn37(String column37) {
		this.column37 = column37;
	}

	public String getColumn38() {
		return column38;
	}

	public void setColumn38(String column38) {
		this.column38 = column38;
	}

	public String getColumn39() {
		return column39;
	}

	public void setColumn39(String column39) {
		this.column39 = column39;
	}

	public String getColumn40() {
		return column40;
	}

	public void setColumn40(String column40) {
		this.column40 = column40;
	}

	public String getColumn41() {
		return column41;
	}

	public void setColumn41(String column41) {
		this.column41 = column41;
	}

	public String getColumn42() {
		return column42;
	}

	public void setColumn42(String column42) {
		this.column42 = column42;
	}

	public String getColumn43() {
		return column43;
	}

	public void setColumn43(String column43) {
		this.column43 = column43;
	}

	public String getColumn44() {
		return column44;
	}

	public void setColumn44(String column44) {
		this.column44 = column44;
	}

	public String getColumn45() {
		return column45;
	}

	public void setColumn45(String column45) {
		this.column45 = column45;
	}

	public String getColumn46() {
		return column46;
	}

	public void setColumn46(String column46) {
		this.column46 = column46;
	}

	public String getColumn47() {
		return column47;
	}

	public void setColumn47(String column47) {
		this.column47 = column47;
	}

	public String getColumn48() {
		return column48;
	}

	public void setColumn48(String column48) {
		this.column48 = column48;
	}

	public String getColumn49() {
		return column49;
	}

	public void setColumn49(String column49) {
		this.column49 = column49;
	}

	public String getColumn50() {
		return column50;
	}

	public void setColumn50(String column50) {
		this.column50 = column50;
	}

	public String getColumn51() {
		return column51;
	}

	public void setColumn51(String column51) {
		this.column51 = column51;
	}

	public String getColumn52() {
		return column52;
	}

	public void setColumn52(String column52) {
		this.column52 = column52;
	}

	public String getColumn53() {
		return column53;
	}

	public void setColumn53(String column53) {
		this.column53 = column53;
	}

	public String getColumn54() {
		return column54;
	}

	public void setColumn54(String column54) {
		this.column54 = column54;
	}

	public String getColumn55() {
		return column55;
	}

	public void setColumn55(String column55) {
		this.column55 = column55;
	}

	public String getColumn56() {
		return column56;
	}

	public void setColumn56(String column56) {
		this.column56 = column56;
	}

	public String getColumn57() {
		return column57;
	}

	public void setColumn57(String column57) {
		this.column57 = column57;
	}

	public String getColumn58() {
		return column58;
	}

	public void setColumn58(String column58) {
		this.column58 = column58;
	}

	public String getColumn59() {
		return column59;
	}

	public void setColumn59(String column59) {
		this.column59 = column59;
	}

	public String getColumn60() {
		return column60;
	}

	public void setColumn60(String column60) {
		this.column60 = column60;
	}

	public String getColumn61() {
		return column61;
	}

	public void setColumn61(String column61) {
		this.column61 = column61;
	}

	public String getColumn62() {
		return column62;
	}

	public void setColumn62(String column62) {
		this.column62 = column62;
	}

	public String getColumn63() {
		return column63;
	}

	public void setColumn63(String column63) {
		this.column63 = column63;
	}

	public String getColumn64() {
		return column64;
	}

	public void setColumn64(String column64) {
		this.column64 = column64;
	}

	public String getColumn65() {
		return column65;
	}

	public void setColumn65(String column65) {
		this.column65 = column65;
	}

	public String getColumn66() {
		return column66;
	}

	public void setColumn66(String column66) {
		this.column66 = column66;
	}

	public String getColumn67() {
		return column67;
	}

	public void setColumn67(String column67) {
		this.column67 = column67;
	}

	public String getColumn68() {
		return column68;
	}

	public void setColumn68(String column68) {
		this.column68 = column68;
	}

	public String getColumn69() {
		return column69;
	}

	public void setColumn69(String column69) {
		this.column69 = column69;
	}

	public String getColumn70() {
		return column70;
	}

	public void setColumn70(String column70) {
		this.column70 = column70;
	}

	public String getColumn71() {
		return column71;
	}

	public void setColumn71(String column71) {
		this.column71 = column71;
	}

	public String getColumn72() {
		return column72;
	}

	public void setColumn72(String column72) {
		this.column72 = column72;
	}

	public String getColumn73() {
		return column73;
	}

	public void setColumn73(String column73) {
		this.column73 = column73;
	}

	public String getColumn74() {
		return column74;
	}

	public void setColumn74(String column74) {
		this.column74 = column74;
	}

	public String getColumn75() {
		return column75;
	}

	public void setColumn75(String column75) {
		this.column75 = column75;
	}

	public String getColumn76() {
		return column76;
	}

	public void setColumn76(String column76) {
		this.column76 = column76;
	}

	public String getColumn77() {
		return column77;
	}

	public void setColumn77(String column77) {
		this.column77 = column77;
	}

	public String getColumn78() {
		return column78;
	}

	public void setColumn78(String column78) {
		this.column78 = column78;
	}

	public String getColumn79() {
		return column79;
	}

	public void setColumn79(String column79) {
		this.column79 = column79;
	}

	public String getColumn80() {
		return column80;
	}

	public void setColumn80(String column80) {
		this.column80 = column80;
	}

	public String getColumn81() {
		return column81;
	}

	public void setColumn81(String column81) {
		this.column81 = column81;
	}

	public String getColumn82() {
		return column82;
	}

	public void setColumn82(String column82) {
		this.column82 = column82;
	}

	public String getColumn83() {
		return column83;
	}

	public void setColumn83(String column83) {
		this.column83 = column83;
	}

	public String getColumn84() {
		return column84;
	}

	public void setColumn84(String column84) {
		this.column84 = column84;
	}

	public String getColumn85() {
		return column85;
	}

	public void setColumn85(String column85) {
		this.column85 = column85;
	}

	public String getColumn86() {
		return column86;
	}

	public void setColumn86(String column86) {
		this.column86 = column86;
	}

	public String getColumn87() {
		return column87;
	}

	public void setColumn87(String column87) {
		this.column87 = column87;
	}

	public String getColumn88() {
		return column88;
	}

	public void setColumn88(String column88) {
		this.column88 = column88;
	}

	public String getColumn89() {
		return column89;
	}

	public void setColumn89(String column89) {
		this.column89 = column89;
	}

	public String getColumn90() {
		return column90;
	}

	public void setColumn90(String column90) {
		this.column90 = column90;
	}

	public String getColumn91() {
		return column91;
	}

	public void setColumn91(String column91) {
		this.column91 = column91;
	}

	public String getColumn92() {
		return column92;
	}

	public void setColumn92(String column92) {
		this.column92 = column92;
	}

	public String getColumn93() {
		return column93;
	}

	public void setColumn93(String column93) {
		this.column93 = column93;
	}

	public String getColumn94() {
		return column94;
	}

	public void setColumn94(String column94) {
		this.column94 = column94;
	}

	public String getColumn95() {
		return column95;
	}

	public void setColumn95(String column95) {
		this.column95 = column95;
	}

	public String getColumn96() {
		return column96;
	}

	public void setColumn96(String column96) {
		this.column96 = column96;
	}

	public String getColumn97() {
		return column97;
	}

	public void setColumn97(String column97) {
		this.column97 = column97;
	}

	public String getColumn98() {
		return column98;
	}

	public void setColumn98(String column98) {
		this.column98 = column98;
	}

	public String getColumn99() {
		return column99;
	}

	public void setColumn99(String column99) {
		this.column99 = column99;
	}

	public String getColumn100() {
		return column100;
	}

	public void setColumn100(String column100) {
		this.column100 = column100;
	}

	public String getColumn101() {
		return column101;
	}

	public void setColumn101(String column101) {
		this.column101 = column101;
	}

	public String getColumn102() {
		return column102;
	}

	public void setColumn102(String column102) {
		this.column102 = column102;
	}

	public String getColumn103() {
		return column103;
	}

	public void setColumn103(String column103) {
		this.column103 = column103;
	}

	public String getColumn104() {
		return column104;
	}

	public void setColumn104(String column104) {
		this.column104 = column104;
	}

	public String getColumn105() {
		return column105;
	}

	public void setColumn105(String column105) {
		this.column105 = column105;
	}

	public String getColumn106() {
		return column106;
	}

	public void setColumn106(String column106) {
		this.column106 = column106;
	}

	public String getColumn107() {
		return column107;
	}

	public void setColumn107(String column107) {
		this.column107 = column107;
	}

	public String getColumn108() {
		return column108;
	}

	public void setColumn108(String column108) {
		this.column108 = column108;
	}

	public String getColumn109() {
		return column109;
	}

	public void setColumn109(String column109) {
		this.column109 = column109;
	}

	public String getColumn110() {
		return column110;
	}

	public void setColumn110(String column110) {
		this.column110 = column110;
	}

	public String getColumn111() {
		return column111;
	}

	public void setColumn111(String column111) {
		this.column111 = column111;
	}

	public String getColumn112() {
		return column112;
	}

	public void setColumn112(String column112) {
		this.column112 = column112;
	}

	public String getColumn113() {
		return column113;
	}

	public void setColumn113(String column113) {
		this.column113 = column113;
	}

	public String getColumn114() {
		return column114;
	}

	public void setColumn114(String column114) {
		this.column114 = column114;
	}

	public String getColumn115() {
		return column115;
	}

	public void setColumn115(String column115) {
		this.column115 = column115;
	}

	public String getColumn116() {
		return column116;
	}

	public void setColumn116(String column116) {
		this.column116 = column116;
	}

	public String getColumn117() {
		return column117;
	}

	public void setColumn117(String column117) {
		this.column117 = column117;
	}

	public String getColumn118() {
		return column118;
	}

	public void setColumn118(String column118) {
		this.column118 = column118;
	}

	public String getColumn119() {
		return column119;
	}

	public void setColumn119(String column119) {
		this.column119 = column119;
	}

	public String getColumn120() {
		return column120;
	}

	public void setColumn120(String column120) {
		this.column120 = column120;
	}

	public String getColumn121() {
		return column121;
	}

	public void setColumn121(String column121) {
		this.column121 = column121;
	}

	public String getColumn122() {
		return column122;
	}

	public void setColumn122(String column122) {
		this.column122 = column122;
	}

	public String getColumn123() {
		return column123;
	}

	public void setColumn123(String column123) {
		this.column123 = column123;
	}

	public String getColumn124() {
		return column124;
	}

	public void setColumn124(String column124) {
		this.column124 = column124;
	}

	public String getColumn125() {
		return column125;
	}

	public void setColumn125(String column125) {
		this.column125 = column125;
	}

	public String getColumn126() {
		return column126;
	}

	public void setColumn126(String column126) {
		this.column126 = column126;
	}

	public String getColumn127() {
		return column127;
	}

	public void setColumn127(String column127) {
		this.column127 = column127;
	}

	public String getColumn128() {
		return column128;
	}

	public void setColumn128(String column128) {
		this.column128 = column128;
	}

	public String getColumn129() {
		return column129;
	}

	public void setColumn129(String column129) {
		this.column129 = column129;
	}

	public String getColumn130() {
		return column130;
	}

	public void setColumn130(String column130) {
		this.column130 = column130;
	}

	public String getColumn131() {
		return column131;
	}

	public void setColumn131(String column131) {
		this.column131 = column131;
	}

	public String getColumn132() {
		return column132;
	}

	public void setColumn132(String column132) {
		this.column132 = column132;
	}

	public String getColumn133() {
		return column133;
	}

	public void setColumn133(String column133) {
		this.column133 = column133;
	}

	public String getColumn134() {
		return column134;
	}

	public void setColumn134(String column134) {
		this.column134 = column134;
	}

	public String getColumn135() {
		return column135;
	}

	public void setColumn135(String column135) {
		this.column135 = column135;
	}

	public String getColumn136() {
		return column136;
	}

	public void setColumn136(String column136) {
		this.column136 = column136;
	}

	public String getColumn137() {
		return column137;
	}

	public void setColumn137(String column137) {
		this.column137 = column137;
	}

	public String getColumn138() {
		return column138;
	}

	public void setColumn138(String column138) {
		this.column138 = column138;
	}

	public String getColumn139() {
		return column139;
	}

	public void setColumn139(String column139) {
		this.column139 = column139;
	}

	public String getColumn140() {
		return column140;
	}

	public void setColumn140(String column140) {
		this.column140 = column140;
	}

	public String getColumn141() {
		return column141;
	}

	public void setColumn141(String column141) {
		this.column141 = column141;
	}

	public String getColumn142() {
		return column142;
	}

	public void setColumn142(String column142) {
		this.column142 = column142;
	}

	public String getColumn143() {
		return column143;
	}

	public void setColumn143(String column143) {
		this.column143 = column143;
	}

	public String getColumn144() {
		return column144;
	}

	public void setColumn144(String column144) {
		this.column144 = column144;
	}

	public String getColumn145() {
		return column145;
	}

	public void setColumn145(String column145) {
		this.column145 = column145;
	}

	public String getColumn146() {
		return column146;
	}

	public void setColumn146(String column146) {
		this.column146 = column146;
	}

	public String getColumn147() {
		return column147;
	}

	public void setColumn147(String column147) {
		this.column147 = column147;
	}

	public String getColumn148() {
		return column148;
	}

	public void setColumn148(String column148) {
		this.column148 = column148;
	}

	public String getColumn149() {
		return column149;
	}

	public void setColumn149(String column149) {
		this.column149 = column149;
	}

	public String getColumn150() {
		return column150;
	}

	public void setColumn150(String column150) {
		this.column150 = column150;
	}

	public String getColumn151() {
		return column151;
	}

	public void setColumn151(String column151) {
		this.column151 = column151;
	}

	public String getColumn152() {
		return column152;
	}

	public void setColumn152(String column152) {
		this.column152 = column152;
	}

	public String getColumn153() {
		return column153;
	}

	public void setColumn153(String column153) {
		this.column153 = column153;
	}

	public String getColumn154() {
		return column154;
	}

	public void setColumn154(String column154) {
		this.column154 = column154;
	}

	public String getColumn155() {
		return column155;
	}

	public void setColumn155(String column155) {
		this.column155 = column155;
	}

	public String getColumn156() {
		return column156;
	}

	public void setColumn156(String column156) {
		this.column156 = column156;
	}

	public String getColumn157() {
		return column157;
	}

	public void setColumn157(String column157) {
		this.column157 = column157;
	}

	public String getColumn158() {
		return column158;
	}

	public void setColumn158(String column158) {
		this.column158 = column158;
	}

	public String getColumn159() {
		return column159;
	}

	public void setColumn159(String column159) {
		this.column159 = column159;
	}

	public String getColumn160() {
		return column160;
	}

	public void setColumn160(String column160) {
		this.column160 = column160;
	}

	public String getColumn161() {
		return column161;
	}

	public void setColumn161(String column161) {
		this.column161 = column161;
	}

	public String getColumn162() {
		return column162;
	}

	public void setColumn162(String column162) {
		this.column162 = column162;
	}

	public String getColumn163() {
		return column163;
	}

	public void setColumn163(String column163) {
		this.column163 = column163;
	}

	public String getColumn164() {
		return column164;
	}

	public void setColumn164(String column164) {
		this.column164 = column164;
	}

	public String getColumn165() {
		return column165;
	}

	public void setColumn165(String column165) {
		this.column165 = column165;
	}

	public String getColumn166() {
		return column166;
	}

	public void setColumn166(String column166) {
		this.column166 = column166;
	}

	public String getColumn167() {
		return column167;
	}

	public void setColumn167(String column167) {
		this.column167 = column167;
	}

	public String getColumn168() {
		return column168;
	}

	public void setColumn168(String column168) {
		this.column168 = column168;
	}

	public String getColumn169() {
		return column169;
	}

	public void setColumn169(String column169) {
		this.column169 = column169;
	}

	public String getColumn170() {
		return column170;
	}

	public void setColumn170(String column170) {
		this.column170 = column170;
	}

	public String getColumn171() {
		return column171;
	}

	public void setColumn171(String column171) {
		this.column171 = column171;
	}

	public String getColumn172() {
		return column172;
	}

	public void setColumn172(String column172) {
		this.column172 = column172;
	}

	public String getColumn173() {
		return column173;
	}

	public void setColumn173(String column173) {
		this.column173 = column173;
	}

	public String getColumn174() {
		return column174;
	}

	public void setColumn174(String column174) {
		this.column174 = column174;
	}

	public String getColumn175() {
		return column175;
	}

	public void setColumn175(String column175) {
		this.column175 = column175;
	}

	public String getColumn176() {
		return column176;
	}

	public void setColumn176(String column176) {
		this.column176 = column176;
	}

	public String getColumn177() {
		return column177;
	}

	public void setColumn177(String column177) {
		this.column177 = column177;
	}

	public String getColumn178() {
		return column178;
	}

	public void setColumn178(String column178) {
		this.column178 = column178;
	}

	public String getColumn179() {
		return column179;
	}

	public void setColumn179(String column179) {
		this.column179 = column179;
	}

	public String getColumn180() {
		return column180;
	}

	public void setColumn180(String column180) {
		this.column180 = column180;
	}

	public String getColumn181() {
		return column181;
	}

	public void setColumn181(String column181) {
		this.column181 = column181;
	}

	public String getColumn182() {
		return column182;
	}

	public void setColumn182(String column182) {
		this.column182 = column182;
	}

	public String getColumn183() {
		return column183;
	}

	public void setColumn183(String column183) {
		this.column183 = column183;
	}

	public String getColumn184() {
		return column184;
	}

	public void setColumn184(String column184) {
		this.column184 = column184;
	}

	public String getColumn185() {
		return column185;
	}

	public void setColumn185(String column185) {
		this.column185 = column185;
	}

	public String getColumn186() {
		return column186;
	}

	public void setColumn186(String column186) {
		this.column186 = column186;
	}

	public String getColumn187() {
		return column187;
	}

	public void setColumn187(String column187) {
		this.column187 = column187;
	}

	public String getColumn188() {
		return column188;
	}

	public void setColumn188(String column188) {
		this.column188 = column188;
	}

	public String getColumn189() {
		return column189;
	}

	public void setColumn189(String column189) {
		this.column189 = column189;
	}

	public String getColumn190() {
		return column190;
	}

	public void setColumn190(String column190) {
		this.column190 = column190;
	}

	public String getColumn191() {
		return column191;
	}

	public void setColumn191(String column191) {
		this.column191 = column191;
	}

	public String getColumn192() {
		return column192;
	}

	public void setColumn192(String column192) {
		this.column192 = column192;
	}

	public String getColumn193() {
		return column193;
	}

	public void setColumn193(String column193) {
		this.column193 = column193;
	}

	public String getColumn194() {
		return column194;
	}

	public void setColumn194(String column194) {
		this.column194 = column194;
	}

	public String getColumn195() {
		return column195;
	}

	public void setColumn195(String column195) {
		this.column195 = column195;
	}

	public String getColumn196() {
		return column196;
	}

	public void setColumn196(String column196) {
		this.column196 = column196;
	}

	public String getColumn197() {
		return column197;
	}

	public void setColumn197(String column197) {
		this.column197 = column197;
	}

	public String getColumn198() {
		return column198;
	}

	public void setColumn198(String column198) {
		this.column198 = column198;
	}

	public String getColumn199() {
		return column199;
	}

	public void setColumn199(String column199) {
		this.column199 = column199;
	}

	public String getColumn200() {
		return column200;
	}

	public void setColumn200(String column200) {
		this.column200 = column200;
	}

	public String getCreated_user() {
		return created_user;
	}

	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public String getColumn_status() {
		return column_status;
	}

	public void setColumn_status(String column_status) {
		this.column_status = column_status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
	
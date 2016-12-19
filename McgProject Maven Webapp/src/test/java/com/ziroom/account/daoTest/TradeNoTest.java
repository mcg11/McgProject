package com.ziroom.account.daoTest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.ziroom.common.util.NetUtil;

public class TradeNoTest extends Exception {
	// 日志处理类
	private final static transient Log logger = LogFactory
			.getLog(TradeNoTest.class);

	/**
	 * 获取账户信息Test
	 * 
	 * @throws Exception
	 */
	@Test
	public void getTradeNoTest() throws Exception {

		String tradeNo;
		String[] tradeNos = { "201510131504111", "201510131216552",
				"201510120955351", "201510131744511", "201510131745371",
				"201510131245271", "201510112031051", "201510110946321",
				"201510121401342", "201510121450092", "201510121237522",
				"201510121033361", "201510111030571", "201510111125552",
				"201510101656532", "201510121058061", "201510101437282",
				"201510101445571", "201510101519072", "201510112304021",
				"201510101510422", "201510101509221", "201510111149341",
				"201510111211351", "201510112124322", "201510111643271",
				"201510111913592", "201510111601291", "201510101011412",
				"201510111452382", "201510101955491", "201510101615541",
				"201510092023352", "201510100856181", "201510101352241",
				"201510121057231", "201510091950221", "201510101903451",
				"201510091950521", "201510101909142", "201510092150192",
				"201510111534422", "201510101339372", "201510091720321",
				"201510122152151", "201510091641332", "201510101030011",
				"201510091740471", "201510101325592", "201510111417081",
				"201510091551121", "201510101657551", "201510091755271",
				"201510111010592", "201510101145592", "201510091535371",
				"201510111554002", "201510100008091", "201510111143101",
				"201510101314222", "201510101921411", "201510111053351",
				"201510092152321", "201510101349312", "201510091102521",
				"201510091625341", "201510091133271", "201510102148171",
				"201510091342422", "201510111754032", "201510081336251",
				"201510071600561", "201510071525072", "201510101917341",
				"201510100700501", "201510111107161", "201510111908251",
				"201510081207181", "201510071135141", "201510071503113",
				"201510071236191", "201510071141001", "201510061738491",
				"201510061621281", "201510081619292", "201510061821411",
				"201510061233141", "201510081900161", "201510061144041",
				"201510080035152", "201510071358511", "201510061958091",
				"201510061753112", "201510071130121", "201510071211562",
				"201510071311211", "201510070808201", "201510061621101",
				"201510071833451", "201510070935281", "201510060739371",
				"201510061857062", "201510061729521", "201510051800302",
				"201510051156441", "201510052118371", "201510061049022",
				"201510051459031", "201510051435132", "201510051045472",
				"201510050848362", "201510041502071", "201510071231501",
				"201510051135001", "201510052213371", "201510051428001",
				"201510041811281", "201510040845431", "201510031430302",
				"201510031050412", "201510021705241", "201510031122561",
				"201510021707421", "201510021641061", "201510021723382",
				"201510061648441", "201510021501251", "201510040940291",
				"201510021356412", "201510062014061", "201510021325371",
				"201510021631141", "201510040034542", "201510032120062",
				"201510031215451", "201510032120341", "201510041420521",
				"201510071420502", "201510031828522", "201510031644131",
				"201510061530201", "201510020427292", "201510011544341",
				"201510022302161", "201510021647282", "201510081530432",
				"201510011252021", "201510050924481", "201510051451051",
				"201510011812381", "201510041023052", "201509301605341",
				"201509301730442", "201510011204051", "201509301926201",
				"201509301334131", "201510010948501", "201510041151482",
				"201509301011461", "201509301038081", "201510021613212",
				"201509301441411", "201510011622322", "201510011744241",
				"201510010935281", "201510081205031", "201510021208241",
				"201510010912002", "201509292155261", "201509292016321",
				"201509302016212", "201509291927172", "201509291643121",
				"201509301150052", "201510061600141", "201509300749551",
				"201509301857261", "201509301419492", "201509301129472",
				"201510021012581", "201509300914201", "201509292147581",
				"201509291018492", "201509291151011", "201510011655272",
				"201510041846272", "201509291118491", "201510041047401",
				"201510020018081", "201509291018331", "201509301215561",
				"201509291851261", "201510011646421", "201510011647281",
				"201510011649081", "201510011649572", "201510011650562",
				"201510011652261", "201509281701012", "201509291113251",
				"201510021101402", "201509281859081", "201509282141121",
				"201509281049571", "201510020743472", "201509291724041",
				"201509301754531", "201509301840051", "201509281549321",
				"201510011609472", "201509281237081", "201509281503131",
				"201509271415521", "201510041033301", "201509271236361",
				"201509271505261", "201510021446532", "201509281259321",
				"201509271542141", "201509282036521", "201509271743081",
				"201509261419521", "201509261351461", "201509271727362",
				"201509261435431", "201509281658081", "201509261145171",
				"201509261348271", "201509262029121", "201509271027391",
				"201509292018051", "201510011545381", "201509261826071",
				"201509281758231", "201509271344171", "201509261317211",
				"201509251937581", "201509271000451", "201509252039091",
				"201509261954551", "201509271431271", "201509271304151",
				"201509271407021", "201509261532411", "201509260902091",
				"201509281150031", "201509261159121", "201509261200521",
				"201509281002071", "201509261056392", "201509251639511",
				"201509261808311", "201509261610271", "201509251315421",
				"201509261342151", "201509261942561", "201509261550331",
				"201509251159411", "201509261300421", "201509251328351",
				"201509251056581", "201509241611512", "201509251046541",
				"201509261811011", "201510011453171", "201509261632431",
				"201509260929331", "201509261210161", "201509261459011",
				"201509241114061", "201509260914401", "201509261003111",
				"201509241149181", "201509300950403", "201509251514211",
				"201509282202241", "201509282202491", "201509282203092",
				"201509282203311", "201509231530372", "201509231503051",
				"201509241432221", "201509241718261", "201509241718441",
				"201509241719051", "201509241719271", "201509241719491",
				"201509241720251", "201509241720451", "201509241721262",
				"201509251133481", "201509241721481", "201509241722131",
				"201509251259211", "201509251258291", "201509251257392",
				"201509251229451", "201509251228091", "201509251226051",
				"201509251225021", "201509251223501", "201509251222001",
				"201509251221131", "201509251220201", "201509251117011",
				"201509251117351", "201509251118031", "201509251118292",
				"201509251119011", "201509251120071", "201509301142302",
				"201509301141341", "201509251121191", "201509301141151",
				"201509251121521", "201509301140492", "201509251122202",
				"201509301140161", "201509301139501", "201509301139222",
				"201509301139041", "201509301138361", "201509301138091",
				"201509251123201", "201509261209022", "201509231458142",
				"201509261210331", "201509261212031", "201509261207081",
				"201509261203471", "201509261206361", "201509261209541",
				"201509261208131", "201509261203071", "201509261202341",
				"201509241522052", "201509231117422", "201509261246181",
				"201509231802041", "201509240923071", "201509261117591",
				"201509231208401", "201509231208511", "201509261127401",
				"201509261128241", "201509261130031", "201509261141061",
				"201509261147161", "201509261137351", "201509261131141",
				"201509261135391", "201509261132491", "201509261138101",
				"201509251101471", "201509251104311", "201509251105201",
				"201509251105591", "201509251106261", "201509300957432",
				"201509251106571", "201509300957231", "201509251107252",
				"201509300957001", "201509251112011", "201509300956393",
				"201509251112282", "201509241722331", "201509300956141",
				"201509241722531", "201509241723111", "201509251112581",
				"201509241723371", "201509300955491", "201509241724121",
				"201509300858541", "201509300955171", "201509300859192",
				"201509300858313", "201509300859412", "201509300858092",
				"201509300954331", "201509300953522", "201509251219391",
				"201509251217451", "201509251216271", "201509251215101",
				"201509251212391", "201509251211141", "201509251210142",
				"201509251209081", "201509251206421", "201509251157061",
				"201509241604521", "201509230811322", "201509220920032",
				"201509231157451", "201509251723141", "201509220930371",
				"201509261137001", "201509261140002", "201509261140351",
				"201509261148371", "201509261147531", "201509261136101",
				"201509261149101", "201509261133281", "201509220928422",
				"201509261150561", "201509261150031", "201509231155192",
				"201509231146521", "201509231142472", "201509231141501",
				"201509231140491", "201509231139531", "201509231138371",
				"201509231137331", "201509231136231", "201509231135081",
				"201509231416141", "201509231415451", "201509231415102",
				"201509231414301", "201509231413471", "201509221641301",
				"201509241209021", "201509241208411", "201509231413131",
				"201509241208241", "201509241208071", "201509241207491",
				"201509241207311", "201509241207102", "201509231412301",
				"201509231412011", "201509231411291", "201509231410521",
				"201509241206432", "201509230908442", "201509241205521",
				"201509230909071", "201509230909342", "201509241204071",
				"201509230908222", "201509230910461", "201509221509131",
				"201509230911063", "201509230911311", "201509230911591",
				"201509230912231", "201509230912521", "201509211505311",
				"201509222308531", "201509261153381", "201509261152591",
				"201509261152271", "201509261131591", "201509261157371",
				"201509261154531", "201509261158181", "201509261134241",
				"201509261135021", "201509282203561", "201509261159001",
				"201509282204251", "201509282209562", "201509282204472",
				"201509282207581", "201509241215151", "201509241214501",
				"201509241214141", "201509230913111", "201509241213461",
				"201509230916191", "201509230916421", "201509230917072",
				"201509230917291", "201509230917511", "201509230919262",
				"201509230920232", "201509230920562", "201509230908002",
				"201509241213211", "201509241212491", "201509241212121",
				"201509241211411", "201509241210071", "201509241209411",
				"201509220924212", "201509231401182", "201509231401521",
				"201509231402281", "201509231403211", "201509231406521",
				"201509231407301", "201509231408012", "201509231408341",
				"201509231418542", "201509231409402", "201509201231011",
				"201509201508481", "201509201040362", "201509191604171",
				"201509191721281", "201509211436131", "201509211435081",
				"201509211434071", "201509211430452", "201509211429542",
				"201509211428582", "201509211428052", "201509211427012",
				"201509211425501", "201509211423401", "201509221724311",
				"201509221724571", "201509282208481", "201509282206382",
				"201509282207401", "201509282208221", "201509282210271",
				"201509282209111", "201509282205162", "201509282209361",
				"201509282211041", "201509282211391", "201509282212082",
				"201509282212422", "201509282213181", "201509282215311",
				"201509282213541", "201509282215522", "201509282216462",
				"201509282221091", "201509191231442", "201510032135191",
				"201510052138501", "201510032134402", "201509282216221",
				"201510032136071", "201510032133591", "201510052138351",
				"201509241220441", "201509211400472", "201509241220101",
				"201509241219481", "201509241219221", "201509282217321",
				"201509241218571", "201509241218151", "201510032133361",
				"201509241217471", "201510032133172", "201509241217221",
				"201509241216531", "201509210920311", "201510032132572",
				"201509241216181", "201509282201281", "201509210920551",
				"201509211440001", "201509211440571", "201509211442491",
				"201509211444441", "201509191614591", "201509211445321",
				"201509211446172", "201509211447071", "201509210921341",
				"201509211447482", "201509211448412", "201509211449461",
				"201509210921521", "201509210922142", "201509210922321",
				"201509210922432", "201509221419201", "201509201402562",
				"201509201253121", "201509191606262", "201509201833342",
				"201509262041481", "201509191708441", "201509231501491",
				"201509282201131", "201509282200561", "201510032136281",
				"201509282200421", "201510032136501", "201509191110561",
				"201510032137042", "201509181543442", "201509191111121",
				"201509191110431", "201510032135451", "201509210923012",
				"201509210923122", "201509210923251", "201509210923362",
				"201509210923531", "201509210924041", "201509210924171",
				"201509190855421", "201509210924321", "201509210926071",
				"201509191053441", "201509210926222", "201509210926353",
				"201509210926522", "201509210927061", "201509192241491",
				"201509192242141", "201509192243001", "201509192242461",
				"201509192243232", "201509192243461", "201509192244061",
				"201509192244492", "201509192245082", "201509201017531",
				"201509192245281", "201509192245462", "201509182030462",
				"201509201055361", "201509191444041", "201509282200282",
				"201509191110291", "201509191112262", "201509282215071",
				"201509171237031", "201509171237311", "201509180957093",
				"201509180957261", "201509180957381", "201509180957521",
				"201509180958281", "201509180958431", "201509180959021",
				"201509180959201", "201509191624572", "201509191007492",
				"201509182057341", "201509181140482", "201509190051371",
				"201510032135001", "201509161618211", "201509191110161",
				"201509282200132", "201509191111421", "201509171455521",
				"201509191110011", "201509171214362", "201509171912371",
				"201509201726121", "201509210717192", "201509161903161",
				"201509182032141", "201509191109482", "201509182031591",
				"201509191109332", "201509182037301", "201509161016101",
				"201509151458371", "201509151702391", "201509161705591",
				"201509151005211", "201509141644582", "201509160958121",
				"201509160830051", "201509161546532", "201509160829521",
				"201509161547232", "201509161547541", "201509161548201",
				"201509160829252", "201509160829092", "201509300900382",
				"201509161548531", "201509141357382", "201509162009171",
				"201509161605311", "201509161612401", "201509161613551",
				"201509161615051", "201509161615551", "201509151140131",
				"201509141013531", "201509151322221", "201509141519392",
				"201509151156001", "201509141138391", "201509141052121",
				"201509151326592", "201509131215031", "201509141230481",
				"201509141303091", "201509191008552", "201509131042521",
				"201509191109152", "201509282219362", "201509131411421",
				"201509191108581", "201509182037162", "201509161531511",
				"201509282220351", "201509191108412", "201509182036592",
				"201509122117591", "201509151851101", "201509151852221",
				"201509141252581", "201509141253311", "201509141254011",
				"201509141254291", "201509141255081", "201509141255491",
				"201509141256171", "201509141256471", "201509131335301",
				"201509160828532", "201509160828381", "201509160828242",
				"201509160828112", "201509160827562", "201509160827421",
				"201509160827131", "201509131601221", "201509132059571",
				"201509131115412", "201509132232251", "201509161531381",
				"201509191108231", "201509182031451", "201509191108092",
				"201509182036451", "201509131626002", "201509182031311",
				"201509161530561", "201509182031181", "201509182031041",
				"201509161530421", "201509121016441", "201509131048291",
				"201509131048581", "201509131049272", "201509131049551",
				"201509131050221", "201509121543211", "201509140835271",
				"201509121542101", "201509140835511", "201509121541231",
				"201509140836101", "201509140836291", "201509140836461",
				"201509121212362", "201509121541081", "201509121540341",
				"201509121540221", "201509121540051", "201509121539471",
				"201509121030471", "201509131050521", "201509131051221",
				"201509131051591", "201509131052361", "201509131053031",
				"201509140837071", "201509140837361", "201509140838051",
				"201509140838341", "201509130826411", "201509111235441",
				"201509121003141", "201509111147401", "201509121142561",
				"201509111405001", "201509111040421", "201509110926462",
				"201509101655021", "201509282218561", "201509161530281",
				"201509191107541", "201509161530081", "201509191107262",
				"201509182036281", "201509182036031", "201509182033031",
				"201509191107061", "201509161535071", "201509142240541",
				"201509161534541", "201509130827121", "201509130827411",
				"201509130828491", "201509121449571", "201509121449151",
				"201509121448591", "201509111520241", "201509111522292",
				"201509111523461", "201509111523101", "201509111524321",
				"201509111525191", "201509111526012", "201509111528371",
				"201509111529111", "201509111530051", "201509101604011",
				"201509121448461", "201509121448311", "201509121448061",
				"201509121447451", "201509121447321", "201509130829101",
				"201509130829391", "201509130830002", "201509130830212",
				"201509130830511", "201509130831142", "201509120818331",
				"201509101102001", "201509101039591", "201509151714011",
				"201509111335001", "201509111523062", "201509101009251",
				"201509101009484", "201509142240371", "201509101010042",
				"201509101010302", "201509101010442", "201509101008121",
				"201509101008301", "201509111253161", "201509130831402",
				"201509130826051", "201509130846371", "201509111202051",
				"201509111201441", "201509111201271", "201509111201121",
				"201509101327201", "201509111452491", "201509111453351",
				"201509122016401", "201509111459221", "201509111500001",
				"201509111501032", "201509111504401", "201509091210271",
				"201509130825391", "201509121401231", "201509121359531",
				"201509121359311", "201509121359022", "201509111200562",
				"201509111200361", "201509111200191", "201509111200031",
				"201509111159301", "201509131547352", "201509100953511",
				"201509101115121", "201509101114541", "201509101114301",
				"201509101122021", "201509101122411", "201509101123252",
				"201509101124051", "201509101124402", "201509100849391",
				"201509130911321", "201509100850272", "201509100850471",
				"201509100851091", "201509100851381", "201509100852042",
				"201509081239152", "201509141318161", "201509091542331",
				"201509101010571", "201509101011101", "201509101011242",
				"201509161534342", "201509091828551", "201509091800131",
				"201509081616282", "201509081322391", "201509131732511",
				"201509101011371", "201509081423561", "201509101016401",
				"201509081423081", "201509081424012", "201509161534201",
				"201509161529541", "201509101011572", "201509161529411",
				"201509061801131", "201509061803011", "201509061804021",
				"201509061805591", "201509061806431", "201509061809441",
				"201509061811401", "201509061816301", "201509121147381",
				"201509101016532", "201509101012111", "201509142240211",
				"201509101012242", "201509161533521", "201509161529251",
				"201509061401401", "201509092248361", "201509101012511",
				"201509161533371", "201509101013081", "201509161533231",
				"201509101013211", "201509051244121", "201509041238361",
				"201509142239531", "201509101013342", "201509182032461",
				"201509161533051", "201509101013532", "201509142239362",
				"201509142238591", "201509161532491", "201509161532361",
				"201509142238452", "201509142238301", "201509101014191",
				"201509101008571", "201509051418252", "201509101014321",
				"201509142238132", "201509101359312", "201509142237581",
				"201509101359001", "201509161532221", "201509081526491",
				"201509101014461", "201509101358421", "201509041313181",
				"201509050838011", "201509041226121", "201509070936421",
				"201509141320161", "201509141322051", "201509141323001",
				"201509101358201", "201509101015471", "201509101016211",
				"201509101357502", "201509101357322", "201510021726531",
				"201510041604292", "201508291234301", "201508281831401",
				"201508311600441", "201508311600031", "201508311559181",
				"201508301431061", "201508301430211", "201508281421042",
				"201508281422411", "201508281423551", "201508281221111",
				"201508281218051", "201508281210521", "201508281203031",
				"201508281155431", "201508281158051", "201508281223361",
				"201508281151582", "201508281117181", "201508281015542",
				"201509011440181", "201509200750091", "201508311711471",
				"201508311712161", "201508311713322", "201508301429272",
				"201508301428411", "201508301428001", "201508301427181",
				"201508301426301", "201508301425401", "201508301424551",
				"201508301424111", "201508301423341", "201508301422471",
				"201508281152081", "201508281152581", "201508281154022",
				"201508281155021", "201508281155371", "201508281151071",
				"201508291005441", "201509151737371", "201508271347051",
				"201508271636231", "201508281101401", "201508281141381",
				"201508281142411", "201508281147111", "201508281146151",
				"201508281145271", "201508242117491", "201508261404211",
				"201508231545051", "201508241117431", "201508241119042",
				"201508241119591", "201508241121141", "201508241134051",
				"201508241135042", "201508241135581", "201508241136571",
				"201508241137561", "201508281332421", "201508241139072",
				"201508241142111", "201508281156531", "201508281148351",
				"201508281143421", "201508281140022", "201508271641322",
				"201508241141111", "201508241152501", "201508241303391",
				"201508241305031", "201508241154511", "201508241157132",
				"201508241243151", "201508281134101", "201508271710573",
				"201508281331381", "201508241244371", "201508241156141",
				"201508241158341", "201508241245521", "201508191735112",
				"201508181111521", "201508221059311", "201508181456591",
				"201508201434041", "201508201433271", "201508201432551",
				"201508201432131", "201508241250111", "201508241145491",
				"201508241300371", "201508241257181", "201508241254451",
				"201508241144171", "201508171425011", "201508161417231",
				"201508171348251", "201508211326371", "201508201446111",
				"201508161002581", "201508201443411", "201508221432021",
				"201508191719571", "201508151148151", "201508151926561",
				"201508151438421", "201508121535031", "201508141524093",
				"201508101606211", "201508101626411", "201508101631151",
				"201508101209201", "201508101607031", "201508101607341",
				"201508102047081", "201509221725131", "201508101407161",
				"201508101407461", "201508101408211", "201508101409011",
				"201508101409361", "201508101626031", "201508101210381",
				"201508101211251", "201508101415341", "201508101208351",
				"201508101207391", "201508101206301", "201508101030151",
				"201508101214131", "201508101200451", "201508101212301",
				"201508091143581", "201508101213381", "201508091554231",
				"201508101411381", "201508101412061", "201508101412351",
				"201508101413201", "201508101413551", "201508081610451",
				"201508101729171", "201508090942461", "201508091056581",
				"201508091057362", "201508091059291", "201508091100051",
				"201508090929361", "201508090929131", "201508091101161",
				"201508071111101", "201508091101501", "201508091102211",
				"201508091102511", "201508091103221", "201508081015211",
				"201508081016021", "201508081016491", "201508081004461",
				"201508081017321", "201508081004271", "201508081019111",
				"201508091144521", "201508081021021", "201508081022391",
				"201508081023191", "201508081024191", "201508081025181",
				"201508071012501", "201508071013241", "201508071016001",
				"201508071023531", "201508071016451", "201508071023071",
				"201508071022281", "201508071017171", "201508071022061",
				"201508071021471", "201508061331261", "201508061331581",
				"201508061332191", "201508061332421", "201508091143301",
				"201508071007571", "201508071008411", "201508071009231",
				"201508071010001", "201508071010271", "201508071231381",
				"201508061015201", "201508061015531", "201508061017261",
				"201508061018051", "201508061018331", "201508051516221",
				"201508061340481", "201508051015221", "201508051015491",
				"201508051017101", "201508061341511", "201508051134121",
				"201508051134311", "201508051134501", "201508051135111",
				"201508060906421", "201508081829501", "201508041519462",
				"201508041345001", "201508041345241", "201508041345511",
				"201508041346161", "201508041346551", "201508052215343",
				"201508050849561", "201508031825131", "201508031148492",
				"201508040915501", "201508040918111", "201508040918481",
				"201508040919461", "201508031446202", "201508040930031",
				"201508040929211", "201508040928111", "201508030915171",
				"201508040909491", "201508040910441", "201508040911291",
				"201508040914031", "201508040914571", "201508040915291",
				"201508040916201", "201508040918181", "201508040919061",
				"201508040908021", "201508040909281", "201508040910191",
				"201508040910561", "201508040911391", "201508040912282",
				"201508062220351", "201508031501041", "201508021410351",
				"201508030915591", "201508030918481", "201508030919281",
				"201508030921261", "201508030922142", "201508030922461",
				"201508030923211", "201508030924051", "201508030926571",
				"201508030927361", "201508010934301", "201508020931131",
				"201508020932011", "201508020932331", "201508020934231",
				"201508020934571", "201508020937012", "201508020937401",
				"201508020938072", "201508020938441", "201508011240081",
				"201508011239441", "201508031309411", "201508031310311",
				"201507311427311", "201507311427531", "201507311429191",
				"201507311431001", "201507311433361", "201507311701201",
				"201507311701401", "201507311702012", "201507311702501",
				"201507311703211", "201507311703501", "201507301423171",
				"201507301424331", "201507301425001", "201507301425291",
				"201507311042121", "201507291610241", "201507291620351",
				"201507291621131", "201507291621561", "201507291622211",
				"201508031309161", "201508031309011", "201508010959111",
				"201507290935581", "201507290935231", "201507290936561",
				"201507290938261", "201507290939281", "201507300933501",
				"201508031308391", "201508052340301", "201507271125011",
				"201507271034181", "201507271210121", "201507271211161",
				"201507271211382", "201507271212021", "201508031308221",
				"201507261559211", "201507271217071", "201507271217341",
				"201508041337231", "201508041340051", "201508041336261",
				"201507271210071", "201507271211021", "201507271211501",
				"201507271212371", "201507271213121", "201507271140353",
				"201507271137221", "201507271135241", "201507271125451",
				"201507251124311", "201508041335491", "201508041339471",
				"201508031307541", "201507261302221", "201507260009452",
				"201507260011081", "201508041335031", "201507251057121",
				"201507251317081", "201508031307391", "201507251605371",
				"201507241057241", "201508031310061", "201508031307201",
				"201507260011551", "201507260012402", "201507260014301",
				"201507261426081", "201507231701201", "201507250944131",
				"201507251928121", "201507250851381", "201507250854011",
				"201507250855291", "201507250936061", "201507231553381",
				"201507250948011", "201507251051582", "201507250939381",
				"201507250929101", "201507250926231", "201507250937341",
				"201507250920541", "201507250914481", "201507231904491",
				"201507241653571", "201507241926211", "201507251605201",
				"201507231422262", "201507231424121", "201508041333481",
				"201507240858451", "201508041339171", "201507231428582",
				"201507231429251", "201507240900311", "201507231430031",
				"201507240916471", "201507240901141", "201507240918221",
				"201507231548091", "201507240905561", "201507231536021",
				"201507231140051", "201507231533381", "201507231534371",
				"201507231535181", "201508041338341", "201508041338011",
				"201507230905581", "201507230902011", "201507230856411",
				"201507230948162", "201507230947241", "201507221613171",
				"201507230946521", "201507230946061", "201507231011361",
				"201507231016101", "201507231016521", "201507221219461",
				"201507221217531", "201507221437281", "201507221438041",
				"201508061228001", "201507221439081", "201507230738131",
				"201507221546471", "201507221547071", "201507221547261",
				"201507221547461", "201507221548311", "201507221433421",
				"201507221434271", "201507221435291", "201507221416292",
				"201507211204011", "201507231607301", "201507211323371",
				"201507210925301", "201507211203131", "201507201810242",
				"201507211108221", "201507201849181", "201507201811191",
				"201507210925501", "201507210925151", "201507211112301",
				"201507211116581", "201507211117431", "201507211118191",
				"201507240901501", "201507250858381", "201507240903281",
				"201507220958201", "201507220958591", "201507220911291",
				"201507220906081", "201507220852221", "201507220848251",
				"201507220851121", "201507211119001", "201507211119471",
				"201507211344531", "201507210837311", "201507210837491",
				"201507210827561", "201507201814192", "201507191558361",
				"201507201843331", "201507191545121", "201507201139211",
				"201507221216181", "201507201528251", "201507251704531",
				"201507181725021", "201507221136281", "201507191550061",
				"201507191733241", "201507210831441", "201507191640531",
				"201507191416341", "201507210828541", "201507191541011",
				"201507191613591", "201507191707341", "201507201015401",
				"201507191550281", "201507211237441", "201507191847331",
				"201507220957211", "201507181631441", "201507181316381",
				"201507181622111", "201507191133521", "201507181205052",
				"201507191446451", "201507191447221", "201507201724331",
				"201507181310101", "201507181310271", "201507181310451",
				"201507181311081", "201507181217391", "201507250917571",
				"201507230939071", "201507231018481", "201507220929061",
				"201507200026271", "201507172245381", "201507181557351",
				"201507180705111", "201507171336471", "201507191632171",
				"201507201003301", "201507161803461", "201507171900221",
				"201507151927151", "201507161224061", "201507201631031",
				"201507161648051", "201507151345101", "201507191657341",
				"201507160920572", "201507141646551", "201507191736201",
				"201507151218311", "201507181448331", "201507141421151",
				"201507150858181", "201507151238311", "201507152116021",
				"201507141055271", "201507141449561", "201507152046331",
				"201507131707491", "201507131502581", "201507131745311",
				"201507141623411", "201507131454101", "201507131527431",
				"201507131457562", "201507121024581", "201507121345321",
				"201507122258061", "201507121607001", "201507121544461",
				"201507111718131", "201507130942401", "201507121339251",
				"201507131206331", "201507121215111", "201507121743091",
				"201507121810011", "201507101713181", "201507101625031",
				"201507101609421", "201507111614041", "201507101249492",
				"201507101128581", "201507111415281", "201507111841331",
				"201507111206211", "201507101645311", "201507110930211",
				"201507131524521", "201507111518231", "201507091111391",
				"201507101815112", "201507091108481", "201507081804031",
				"201507071525111", "201507071653051", "201507111622551",
				"201507081018311", "201507111311471", "201507061222481",
				"201507061408361", "201507111533461", "201507071814491",
				"201507062039381", "201507051348071", "201507051820481",
				"201507051007461", "201507050952162", "201507071735181",
				"201507060959091", "201507050900321", "201507051945211",
				"201507031404431", "201507031130481", "201507051256581",
				"201507051620221", "201507051012141", "201507041556574",
				"201507051407271", "201507041431251", "201507021147361",
				"201507040137171", "201507021044421", "201507061325141",
				"201507021126151", "201507021053561", "201507041602171",
				"201507031045051", "201507021353311", "201506301428471",
				"201506291851001", "201506301713021", "201506302014451",
				"201507051933131", "201507041618401", "201506281445082",
				"201506271850021", "201506281759142", "201506281037161",
				"201506271016412", "201506291502331", "201506282221261",
				"201506281727171", "201506271705431", "201507011158581",
				"201507031906471", "201506281215491", "201506252050491",
				"201506281520141", "201506271227271", "201506271344121",
				"201506262130001", "201506242014081", "201506241538343",
				"201506251216321", "201506261515371", "201506261611371",
				"201506241554344", "201506271841301", "201506271016282",
				"201506241121551", "201506232334011", "201506241559041",
				"201506241257311", "201506231657201", "201506281139221",
				"201506241400541", "201506221749171", "201506242134223",
				"201506271223161", "201506240901351", "201506282313191",
				"201506242040091", "201506262033322", "201506301056371",
				"201506251549061", "201506221753251", "201506221248331",
				"201506220809141", "201506221355402", "201506221611122",
				"201506221149041", "201506211143431", "201506221421431",
				"201506221656172", "201506271609581", "201506221136581",
				"201506210951351", "201506221130182", "201506221738361",
				"201506201645281", "201506221152401", "201506221100061",
				"201506212350301", "201507012105202", "201506201258342",
				"201506201045171", "201506191012342", "201506191401491",
				"201506281433171", "201506230858174", "201506201722221",
				"201506201624271", "201506201057431", "201506201101111",
				"201506241547411", "201506180949121", "201506181343511",
				"201506180950131", "201506171020051", "201506201102142",
				"201506261446201", "201506191606531", "201506171354561",
				"201506241417121", "201506190713041", "201507041319241",
				"201506171352081", "201506161624402", "201506151537121",
				"201506151805291", "201506161505181", "201506142136221",
				"201506161707471", "201506201335351", "201506141840232",
				"201506151957251", "201506152019291", "201506141707312",
				"201506171549451", "201506150907461", "201506202149131",
				"201506131417361", "201506141314191", "201506131423011",
				"201506141446072", "201506151643121", "201506141235141",
				"201506121733551", "201506121742461", "201507040901371",
				"201506141733381", "201506121811502", "201506131411251",
				"201506140156331", "201506101554001", "201507100806141",
				"201506112024031", "201506091124511", "201506102344181",
				"201506252032191", "201506131259491", "201506101133012",
				"201506091214191", "201506081652471", "201506081554552",
				"201506081608111", "201506090737411", "201506091029472",
				"201506081523101", "201506081521501", "201506081516581",
				"201506101415231", "201506111913061", "201506131026161",
				"201506091114031", "201506131733591", "201506061753511",
				"201506081110251", "201506061606221", "201506061833251" };

		logger.info(tradeNos.length);
		System.out.println(tradeNos.length);
		for (int i = 0; i < tradeNos.length; i++) {
			tradeNo = tradeNos[i];
			if (tradeNo == null) {
				System.out.println("null");

				continue;
			}

			Map<String, String> param = new HashMap<String, String>();
			param.put("tradeNo", tradeNo);
			// String refundUrl = "http://test.account.ziroom.com/tradeNo.html";
			String refundUrl = "http://account.ziroom.com/tradeNo.html";
			InputStream resultContentInputStream = NetUtil.sendPostRequest(
					refundUrl, param);

			String resultContent = NetUtil.getTextContent(
					resultContentInputStream, "UTF-8");
			System.out.println(resultContent);
			// MockHttpServletRequestBuilder mo =
			// MockMvcRequestBuilders.get(path
			// + "selectAddTrade?tradeNo=" + tradeNo);
			// // 请求
			// ResultActions result = mockMvc.perform(mo);
			// // 日志 返回值详情
			// logger.info("单元测试  getTradeNoTest result is "
			// + result.andReturn().getResponse().getContentAsString());

		}

	}
}
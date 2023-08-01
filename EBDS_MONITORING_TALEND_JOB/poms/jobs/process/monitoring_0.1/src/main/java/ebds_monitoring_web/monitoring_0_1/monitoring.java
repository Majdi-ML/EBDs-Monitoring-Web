// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package ebds_monitoring_web.monitoring_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

//the import part of tJavaRow_2
//import java.util.List;

//the import part of tJavaRow_3
//import java.util.List;

//the import part of tJavaRow_4
//import java.util.List;

//the import part of tJavaRow_5
//import java.util.List;

//the import part of tJavaRow_6
//import java.util.List;

//the import part of tJavaRow_7
//import java.util.List;

//the import part of tJavaRow_8
//import java.util.List;

//the import part of tJavaRow_9
//import java.util.List;

//the import part of tJavaRow_10
//import java.util.List;

@SuppressWarnings("unused")

/**
 * Job: monitoring Purpose: excel to db<br>
 * Description: l'insertion les données d'un fichier excel dans base de données
 * <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class monitoring implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "monitoring";
	private final String projectName = "EBDS_MONITORING_WEB";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					monitoring.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(monitoring.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileList_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tJavaRow_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_13_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tJavaRow_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_10_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_18_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tJavaRow_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_15_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileList_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_6_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tJavaRow_5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_24_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tJavaRow_6_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_6_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_21_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_48_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_6_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tJavaRow_7_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_7_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileList_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_39_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_7_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tJavaRow_8_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_8_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_36_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_31_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_8_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tJavaRow_9_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_9_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_28_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_9_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tJavaRow_10_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_10_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_6_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tIterateToFlow_1_ITFO_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tIterateToFlow_1_AI_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tIterateToFlow_2_ITFO_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tIterateToFlow_2_AI_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tIterateToFlow_3_ITFO_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tIterateToFlow_3_AI_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileList_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileList_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileList_3_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tIterateToFlow_1_AI_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tIterateToFlow_2_AI_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tIterateToFlow_3_AI_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class insertclustersStruct implements routines.system.IPersistableRow<insertclustersStruct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String etat;

		public String getEtat() {
			return this.etat;
		}

		public String Nom_du_Ressource_Group_Package_Service_Guard;

		public String getNom_du_Ressource_Group_Package_Service_Guard() {
			return this.Nom_du_Ressource_Group_Package_Service_Guard;
		}

		public String Adresse_IP;

		public String getAdresse_IP() {
			return this.Adresse_IP;
		}

		public String Liste_des_serveurs_concernes;

		public String getListe_des_serveurs_concernes() {
			return this.Liste_des_serveurs_concernes;
		}

		public String Logiciel_Cluster;

		public String getLogiciel_Cluster() {
			return this.Logiciel_Cluster;
		}

		public String Version;

		public String getVersion() {
			return this.Version;
		}

		public String Mode;

		public String getMode() {
			return this.Mode;
		}

		public String Serveur_actif;

		public String getServeur_actif() {
			return this.Serveur_actif;
		}

		public String Complements_d_informations;

		public String getComplements_d_informations() {
			return this.Complements_d_informations;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final insertclustersStruct other = (insertclustersStruct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(insertclustersStruct other) {

			other.id = this.id;
			other.Ref = this.Ref;
			other.etat = this.etat;
			other.Nom_du_Ressource_Group_Package_Service_Guard = this.Nom_du_Ressource_Group_Package_Service_Guard;
			other.Adresse_IP = this.Adresse_IP;
			other.Liste_des_serveurs_concernes = this.Liste_des_serveurs_concernes;
			other.Logiciel_Cluster = this.Logiciel_Cluster;
			other.Version = this.Version;
			other.Mode = this.Mode;
			other.Serveur_actif = this.Serveur_actif;
			other.Complements_d_informations = this.Complements_d_informations;

		}

		public void copyKeysDataTo(insertclustersStruct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.Ref = readString(dis);

					this.etat = readString(dis);

					this.Nom_du_Ressource_Group_Package_Service_Guard = readString(dis);

					this.Adresse_IP = readString(dis);

					this.Liste_des_serveurs_concernes = readString(dis);

					this.Logiciel_Cluster = readString(dis);

					this.Version = readString(dis);

					this.Mode = readString(dis);

					this.Serveur_actif = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.Ref = readString(dis);

					this.etat = readString(dis);

					this.Nom_du_Ressource_Group_Package_Service_Guard = readString(dis);

					this.Adresse_IP = readString(dis);

					this.Liste_des_serveurs_concernes = readString(dis);

					this.Logiciel_Cluster = readString(dis);

					this.Version = readString(dis);

					this.Mode = readString(dis);

					this.Serveur_actif = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.etat, dos);

				// String

				writeString(this.Nom_du_Ressource_Group_Package_Service_Guard, dos);

				// String

				writeString(this.Adresse_IP, dos);

				// String

				writeString(this.Liste_des_serveurs_concernes, dos);

				// String

				writeString(this.Logiciel_Cluster, dos);

				// String

				writeString(this.Version, dos);

				// String

				writeString(this.Mode, dos);

				// String

				writeString(this.Serveur_actif, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.etat, dos);

				// String

				writeString(this.Nom_du_Ressource_Group_Package_Service_Guard, dos);

				// String

				writeString(this.Adresse_IP, dos);

				// String

				writeString(this.Liste_des_serveurs_concernes, dos);

				// String

				writeString(this.Logiciel_Cluster, dos);

				// String

				writeString(this.Version, dos);

				// String

				writeString(this.Mode, dos);

				// String

				writeString(this.Serveur_actif, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",Ref=" + Ref);
			sb.append(",etat=" + etat);
			sb.append(",Nom_du_Ressource_Group_Package_Service_Guard=" + Nom_du_Ressource_Group_Package_Service_Guard);
			sb.append(",Adresse_IP=" + Adresse_IP);
			sb.append(",Liste_des_serveurs_concernes=" + Liste_des_serveurs_concernes);
			sb.append(",Logiciel_Cluster=" + Logiciel_Cluster);
			sb.append(",Version=" + Version);
			sb.append(",Mode=" + Mode);
			sb.append(",Serveur_actif=" + Serveur_actif);
			sb.append(",Complements_d_informations=" + Complements_d_informations);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(insertclustersStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id, other.id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row19Struct implements routines.system.IPersistableRow<row19Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Nom_du_Ressource_Group___Package_Service_Guard;

		public String getNom_du_Ressource_Group___Package_Service_Guard() {
			return this.Nom_du_Ressource_Group___Package_Service_Guard;
		}

		public String Adresse_IP;

		public String getAdresse_IP() {
			return this.Adresse_IP;
		}

		public String Liste_des_serveurs_concernes;

		public String getListe_des_serveurs_concernes() {
			return this.Liste_des_serveurs_concernes;
		}

		public String Logiciel_Cluster;

		public String getLogiciel_Cluster() {
			return this.Logiciel_Cluster;
		}

		public String Version;

		public String getVersion() {
			return this.Version;
		}

		public String Mode;

		public String getMode() {
			return this.Mode;
		}

		public String Serveur_Actif;

		public String getServeur_Actif() {
			return this.Serveur_Actif;
		}

		public String Complements_d_informations;

		public String getComplements_d_informations() {
			return this.Complements_d_informations;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Nom_du_Ressource_Group___Package_Service_Guard = readString(dis);

					this.Adresse_IP = readString(dis);

					this.Liste_des_serveurs_concernes = readString(dis);

					this.Logiciel_Cluster = readString(dis);

					this.Version = readString(dis);

					this.Mode = readString(dis);

					this.Serveur_Actif = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Nom_du_Ressource_Group___Package_Service_Guard = readString(dis);

					this.Adresse_IP = readString(dis);

					this.Liste_des_serveurs_concernes = readString(dis);

					this.Logiciel_Cluster = readString(dis);

					this.Version = readString(dis);

					this.Mode = readString(dis);

					this.Serveur_Actif = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Nom_du_Ressource_Group___Package_Service_Guard, dos);

				// String

				writeString(this.Adresse_IP, dos);

				// String

				writeString(this.Liste_des_serveurs_concernes, dos);

				// String

				writeString(this.Logiciel_Cluster, dos);

				// String

				writeString(this.Version, dos);

				// String

				writeString(this.Mode, dos);

				// String

				writeString(this.Serveur_Actif, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Nom_du_Ressource_Group___Package_Service_Guard, dos);

				// String

				writeString(this.Adresse_IP, dos);

				// String

				writeString(this.Liste_des_serveurs_concernes, dos);

				// String

				writeString(this.Logiciel_Cluster, dos);

				// String

				writeString(this.Version, dos);

				// String

				writeString(this.Mode, dos);

				// String

				writeString(this.Serveur_Actif, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Nom_du_Ressource_Group___Package_Service_Guard="
					+ Nom_du_Ressource_Group___Package_Service_Guard);
			sb.append(",Adresse_IP=" + Adresse_IP);
			sb.append(",Liste_des_serveurs_concernes=" + Liste_des_serveurs_concernes);
			sb.append(",Logiciel_Cluster=" + Logiciel_Cluster);
			sb.append(",Version=" + Version);
			sb.append(",Mode=" + Mode);
			sb.append(",Serveur_Actif=" + Serveur_Actif);
			sb.append(",Complements_d_informations=" + Complements_d_informations);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row19Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row10Struct implements routines.system.IPersistableRow<row10Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Nom_du_Ressource_Group___Package_Service_Guard;

		public String getNom_du_Ressource_Group___Package_Service_Guard() {
			return this.Nom_du_Ressource_Group___Package_Service_Guard;
		}

		public String Adresse_IP;

		public String getAdresse_IP() {
			return this.Adresse_IP;
		}

		public String Liste_des_serveurs_concernes;

		public String getListe_des_serveurs_concernes() {
			return this.Liste_des_serveurs_concernes;
		}

		public String Logiciel_Cluster;

		public String getLogiciel_Cluster() {
			return this.Logiciel_Cluster;
		}

		public String Version;

		public String getVersion() {
			return this.Version;
		}

		public String Mode;

		public String getMode() {
			return this.Mode;
		}

		public String Serveur_Actif;

		public String getServeur_Actif() {
			return this.Serveur_Actif;
		}

		public String Complements_d_informations;

		public String getComplements_d_informations() {
			return this.Complements_d_informations;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Nom_du_Ressource_Group___Package_Service_Guard = readString(dis);

					this.Adresse_IP = readString(dis);

					this.Liste_des_serveurs_concernes = readString(dis);

					this.Logiciel_Cluster = readString(dis);

					this.Version = readString(dis);

					this.Mode = readString(dis);

					this.Serveur_Actif = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Nom_du_Ressource_Group___Package_Service_Guard = readString(dis);

					this.Adresse_IP = readString(dis);

					this.Liste_des_serveurs_concernes = readString(dis);

					this.Logiciel_Cluster = readString(dis);

					this.Version = readString(dis);

					this.Mode = readString(dis);

					this.Serveur_Actif = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Nom_du_Ressource_Group___Package_Service_Guard, dos);

				// String

				writeString(this.Adresse_IP, dos);

				// String

				writeString(this.Liste_des_serveurs_concernes, dos);

				// String

				writeString(this.Logiciel_Cluster, dos);

				// String

				writeString(this.Version, dos);

				// String

				writeString(this.Mode, dos);

				// String

				writeString(this.Serveur_Actif, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Nom_du_Ressource_Group___Package_Service_Guard, dos);

				// String

				writeString(this.Adresse_IP, dos);

				// String

				writeString(this.Liste_des_serveurs_concernes, dos);

				// String

				writeString(this.Logiciel_Cluster, dos);

				// String

				writeString(this.Version, dos);

				// String

				writeString(this.Mode, dos);

				// String

				writeString(this.Serveur_Actif, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Nom_du_Ressource_Group___Package_Service_Guard="
					+ Nom_du_Ressource_Group___Package_Service_Guard);
			sb.append(",Adresse_IP=" + Adresse_IP);
			sb.append(",Liste_des_serveurs_concernes=" + Liste_des_serveurs_concernes);
			sb.append(",Logiciel_Cluster=" + Logiciel_Cluster);
			sb.append(",Version=" + Version);
			sb.append(",Mode=" + Mode);
			sb.append(",Serveur_Actif=" + Serveur_Actif);
			sb.append(",Complements_d_informations=" + Complements_d_informations);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row10Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Nom_du_Ressource_Group___Package_Service_Guard;

		public String getNom_du_Ressource_Group___Package_Service_Guard() {
			return this.Nom_du_Ressource_Group___Package_Service_Guard;
		}

		public String Adresse_IP;

		public String getAdresse_IP() {
			return this.Adresse_IP;
		}

		public String Liste_des_serveurs_concernes;

		public String getListe_des_serveurs_concernes() {
			return this.Liste_des_serveurs_concernes;
		}

		public String Logiciel_Cluster;

		public String getLogiciel_Cluster() {
			return this.Logiciel_Cluster;
		}

		public String Version;

		public String getVersion() {
			return this.Version;
		}

		public String Mode;

		public String getMode() {
			return this.Mode;
		}

		public String Serveur_Actif;

		public String getServeur_Actif() {
			return this.Serveur_Actif;
		}

		public String Complements_d_informations;

		public String getComplements_d_informations() {
			return this.Complements_d_informations;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Nom_du_Ressource_Group___Package_Service_Guard = readString(dis);

					this.Adresse_IP = readString(dis);

					this.Liste_des_serveurs_concernes = readString(dis);

					this.Logiciel_Cluster = readString(dis);

					this.Version = readString(dis);

					this.Mode = readString(dis);

					this.Serveur_Actif = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Nom_du_Ressource_Group___Package_Service_Guard = readString(dis);

					this.Adresse_IP = readString(dis);

					this.Liste_des_serveurs_concernes = readString(dis);

					this.Logiciel_Cluster = readString(dis);

					this.Version = readString(dis);

					this.Mode = readString(dis);

					this.Serveur_Actif = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Nom_du_Ressource_Group___Package_Service_Guard, dos);

				// String

				writeString(this.Adresse_IP, dos);

				// String

				writeString(this.Liste_des_serveurs_concernes, dos);

				// String

				writeString(this.Logiciel_Cluster, dos);

				// String

				writeString(this.Version, dos);

				// String

				writeString(this.Mode, dos);

				// String

				writeString(this.Serveur_Actif, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Nom_du_Ressource_Group___Package_Service_Guard, dos);

				// String

				writeString(this.Adresse_IP, dos);

				// String

				writeString(this.Liste_des_serveurs_concernes, dos);

				// String

				writeString(this.Logiciel_Cluster, dos);

				// String

				writeString(this.Version, dos);

				// String

				writeString(this.Mode, dos);

				// String

				writeString(this.Serveur_Actif, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Nom_du_Ressource_Group___Package_Service_Guard="
					+ Nom_du_Ressource_Group___Package_Service_Guard);
			sb.append(",Adresse_IP=" + Adresse_IP);
			sb.append(",Liste_des_serveurs_concernes=" + Liste_des_serveurs_concernes);
			sb.append(",Logiciel_Cluster=" + Logiciel_Cluster);
			sb.append(",Version=" + Version);
			sb.append(",Mode=" + Mode);
			sb.append(",Serveur_Actif=" + Serveur_Actif);
			sb.append(",Complements_d_informations=" + Complements_d_informations);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class insertprocessStruct implements routines.system.IPersistableRow<insertprocessStruct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref_composant;

		public String getRef_composant() {
			return this.Ref_composant;
		}

		public String Process;

		public String getProcess() {
			return this.Process;
		}

		public String Cricite;

		public String getCricite() {
			return this.Cricite;
		}

		public String Message_alarme;

		public String getMessage_alarme() {
			return this.Message_alarme;
		}

		public String Intervalle_de_polling;

		public String getIntervalle_de_polling() {
			return this.Intervalle_de_polling;
		}

		public String Objet;

		public String getObjet() {
			return this.Objet;
		}

		public String Nom_Template;

		public String getNom_Template() {
			return this.Nom_Template;
		}

		public String Monitored_By;

		public String getMonitored_By() {
			return this.Monitored_By;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final insertprocessStruct other = (insertprocessStruct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(insertprocessStruct other) {

			other.id = this.id;
			other.Ref = this.Ref;
			other.Etat = this.Etat;
			other.Ref_composant = this.Ref_composant;
			other.Process = this.Process;
			other.Cricite = this.Cricite;
			other.Message_alarme = this.Message_alarme;
			other.Intervalle_de_polling = this.Intervalle_de_polling;
			other.Objet = this.Objet;
			other.Nom_Template = this.Nom_Template;
			other.Monitored_By = this.Monitored_By;

		}

		public void copyKeysDataTo(insertprocessStruct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref_composant = readString(dis);

					this.Process = readString(dis);

					this.Cricite = readString(dis);

					this.Message_alarme = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Objet = readString(dis);

					this.Nom_Template = readString(dis);

					this.Monitored_By = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref_composant = readString(dis);

					this.Process = readString(dis);

					this.Cricite = readString(dis);

					this.Message_alarme = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Objet = readString(dis);

					this.Nom_Template = readString(dis);

					this.Monitored_By = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref_composant, dos);

				// String

				writeString(this.Process, dos);

				// String

				writeString(this.Cricite, dos);

				// String

				writeString(this.Message_alarme, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Nom_Template, dos);

				// String

				writeString(this.Monitored_By, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref_composant, dos);

				// String

				writeString(this.Process, dos);

				// String

				writeString(this.Cricite, dos);

				// String

				writeString(this.Message_alarme, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Nom_Template, dos);

				// String

				writeString(this.Monitored_By, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref_composant=" + Ref_composant);
			sb.append(",Process=" + Process);
			sb.append(",Cricite=" + Cricite);
			sb.append(",Message_alarme=" + Message_alarme);
			sb.append(",Intervalle_de_polling=" + Intervalle_de_polling);
			sb.append(",Objet=" + Objet);
			sb.append(",Nom_Template=" + Nom_Template);
			sb.append(",Monitored_By=" + Monitored_By);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(insertprocessStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id, other.id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row20Struct implements routines.system.IPersistableRow<row20Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref__des_composants;

		public String getRef__des_composants() {
			return this.Ref__des_composants;
		}

		public String Process;

		public String getProcess() {
			return this.Process;
		}

		public String Criticite;

		public String getCriticite() {
			return this.Criticite;
		}

		public String Message_d_alarme;

		public String getMessage_d_alarme() {
			return this.Message_d_alarme;
		}

		public String Intervalle_de_polling;

		public String getIntervalle_de_polling() {
			return this.Intervalle_de_polling;
		}

		public String Objet;

		public String getObjet() {
			return this.Objet;
		}

		public String Nom_Template;

		public String getNom_Template() {
			return this.Nom_Template;
		}

		public String Monitored_By;

		public String getMonitored_By() {
			return this.Monitored_By;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.Process = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Objet = readString(dis);

					this.Nom_Template = readString(dis);

					this.Monitored_By = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.Process = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Objet = readString(dis);

					this.Nom_Template = readString(dis);

					this.Monitored_By = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.Process, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Nom_Template, dos);

				// String

				writeString(this.Monitored_By, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.Process, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Nom_Template, dos);

				// String

				writeString(this.Monitored_By, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref__des_composants=" + Ref__des_composants);
			sb.append(",Process=" + Process);
			sb.append(",Criticite=" + Criticite);
			sb.append(",Message_d_alarme=" + Message_d_alarme);
			sb.append(",Intervalle_de_polling=" + Intervalle_de_polling);
			sb.append(",Objet=" + Objet);
			sb.append(",Nom_Template=" + Nom_Template);
			sb.append(",Monitored_By=" + Monitored_By);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row20Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row11Struct implements routines.system.IPersistableRow<row11Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref__des_composants;

		public String getRef__des_composants() {
			return this.Ref__des_composants;
		}

		public String Process;

		public String getProcess() {
			return this.Process;
		}

		public String Criticite;

		public String getCriticite() {
			return this.Criticite;
		}

		public String Message_d_alarme;

		public String getMessage_d_alarme() {
			return this.Message_d_alarme;
		}

		public String Intervalle_de_polling;

		public String getIntervalle_de_polling() {
			return this.Intervalle_de_polling;
		}

		public String Objet;

		public String getObjet() {
			return this.Objet;
		}

		public String Nom_Template;

		public String getNom_Template() {
			return this.Nom_Template;
		}

		public String Monitored_By;

		public String getMonitored_By() {
			return this.Monitored_By;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.Process = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Objet = readString(dis);

					this.Nom_Template = readString(dis);

					this.Monitored_By = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.Process = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Objet = readString(dis);

					this.Nom_Template = readString(dis);

					this.Monitored_By = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.Process, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Nom_Template, dos);

				// String

				writeString(this.Monitored_By, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.Process, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Nom_Template, dos);

				// String

				writeString(this.Monitored_By, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref__des_composants=" + Ref__des_composants);
			sb.append(",Process=" + Process);
			sb.append(",Criticite=" + Criticite);
			sb.append(",Message_d_alarme=" + Message_d_alarme);
			sb.append(",Intervalle_de_polling=" + Intervalle_de_polling);
			sb.append(",Objet=" + Objet);
			sb.append(",Nom_Template=" + Nom_Template);
			sb.append(",Monitored_By=" + Monitored_By);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row11Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref__des_composants;

		public String getRef__des_composants() {
			return this.Ref__des_composants;
		}

		public String Process;

		public String getProcess() {
			return this.Process;
		}

		public String Criticite;

		public String getCriticite() {
			return this.Criticite;
		}

		public String Message_d_alarme;

		public String getMessage_d_alarme() {
			return this.Message_d_alarme;
		}

		public String Intervalle_de_polling;

		public String getIntervalle_de_polling() {
			return this.Intervalle_de_polling;
		}

		public String Objet;

		public String getObjet() {
			return this.Objet;
		}

		public String Nom_Template;

		public String getNom_Template() {
			return this.Nom_Template;
		}

		public String Monitored_By;

		public String getMonitored_By() {
			return this.Monitored_By;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.Process = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Objet = readString(dis);

					this.Nom_Template = readString(dis);

					this.Monitored_By = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.Process = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Objet = readString(dis);

					this.Nom_Template = readString(dis);

					this.Monitored_By = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.Process, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Nom_Template, dos);

				// String

				writeString(this.Monitored_By, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.Process, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Nom_Template, dos);

				// String

				writeString(this.Monitored_By, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref__des_composants=" + Ref__des_composants);
			sb.append(",Process=" + Process);
			sb.append(",Criticite=" + Criticite);
			sb.append(",Message_d_alarme=" + Message_d_alarme);
			sb.append(",Intervalle_de_polling=" + Intervalle_de_polling);
			sb.append(",Objet=" + Objet);
			sb.append(",Nom_Template=" + Nom_Template);
			sb.append(",Monitored_By=" + Monitored_By);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class insertrequetesStruct implements routines.system.IPersistableRow<insertrequetesStruct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref_composant;

		public String getRef_composant() {
			return this.Ref_composant;
		}

		public String RG_SG_si_Cluster;

		public String getRG_SG_si_Cluster() {
			return this.RG_SG_si_Cluster;
		}

		public String Requete_SQL;

		public String getRequete_SQL() {
			return this.Requete_SQL;
		}

		public String UserName_DB_Name;

		public String getUserName_DB_Name() {
			return this.UserName_DB_Name;
		}

		public String Resultat_Attendu_de_la_requete;

		public String getResultat_Attendu_de_la_requete() {
			return this.Resultat_Attendu_de_la_requete;
		}

		public String Perform_action;

		public String getPerform_action() {
			return this.Perform_action;
		}

		public String Criticite;

		public String getCriticite() {
			return this.Criticite;
		}

		public String Message_alarme;

		public String getMessage_alarme() {
			return this.Message_alarme;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Intervalle_de_polling;

		public String getIntervalle_de_polling() {
			return this.Intervalle_de_polling;
		}

		public String Ref_Service;

		public String getRef_Service() {
			return this.Ref_Service;
		}

		public String Objet;

		public String getObjet() {
			return this.Objet;
		}

		public String Monitored_By;

		public String getMonitored_By() {
			return this.Monitored_By;
		}

		public String Nom_Template;

		public String getNom_Template() {
			return this.Nom_Template;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final insertrequetesStruct other = (insertrequetesStruct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(insertrequetesStruct other) {

			other.id = this.id;
			other.Ref = this.Ref;
			other.Etat = this.Etat;
			other.Ref_composant = this.Ref_composant;
			other.RG_SG_si_Cluster = this.RG_SG_si_Cluster;
			other.Requete_SQL = this.Requete_SQL;
			other.UserName_DB_Name = this.UserName_DB_Name;
			other.Resultat_Attendu_de_la_requete = this.Resultat_Attendu_de_la_requete;
			other.Perform_action = this.Perform_action;
			other.Criticite = this.Criticite;
			other.Message_alarme = this.Message_alarme;
			other.Instructions = this.Instructions;
			other.Intervalle_de_polling = this.Intervalle_de_polling;
			other.Ref_Service = this.Ref_Service;
			other.Objet = this.Objet;
			other.Monitored_By = this.Monitored_By;
			other.Nom_Template = this.Nom_Template;

		}

		public void copyKeysDataTo(insertrequetesStruct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref_composant = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Requete_SQL = readString(dis);

					this.UserName_DB_Name = readString(dis);

					this.Resultat_Attendu_de_la_requete = readString(dis);

					this.Perform_action = readString(dis);

					this.Criticite = readString(dis);

					this.Message_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Ref_Service = readString(dis);

					this.Objet = readString(dis);

					this.Monitored_By = readString(dis);

					this.Nom_Template = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref_composant = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Requete_SQL = readString(dis);

					this.UserName_DB_Name = readString(dis);

					this.Resultat_Attendu_de_la_requete = readString(dis);

					this.Perform_action = readString(dis);

					this.Criticite = readString(dis);

					this.Message_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Ref_Service = readString(dis);

					this.Objet = readString(dis);

					this.Monitored_By = readString(dis);

					this.Nom_Template = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref_composant, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Requete_SQL, dos);

				// String

				writeString(this.UserName_DB_Name, dos);

				// String

				writeString(this.Resultat_Attendu_de_la_requete, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Ref_Service, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Nom_Template, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref_composant, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Requete_SQL, dos);

				// String

				writeString(this.UserName_DB_Name, dos);

				// String

				writeString(this.Resultat_Attendu_de_la_requete, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Ref_Service, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Nom_Template, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref_composant=" + Ref_composant);
			sb.append(",RG_SG_si_Cluster=" + RG_SG_si_Cluster);
			sb.append(",Requete_SQL=" + Requete_SQL);
			sb.append(",UserName_DB_Name=" + UserName_DB_Name);
			sb.append(",Resultat_Attendu_de_la_requete=" + Resultat_Attendu_de_la_requete);
			sb.append(",Perform_action=" + Perform_action);
			sb.append(",Criticite=" + Criticite);
			sb.append(",Message_alarme=" + Message_alarme);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Intervalle_de_polling=" + Intervalle_de_polling);
			sb.append(",Ref_Service=" + Ref_Service);
			sb.append(",Objet=" + Objet);
			sb.append(",Monitored_By=" + Monitored_By);
			sb.append(",Nom_Template=" + Nom_Template);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(insertrequetesStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id, other.id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row21Struct implements routines.system.IPersistableRow<row21Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref__des_composants;

		public String getRef__des_composants() {
			return this.Ref__des_composants;
		}

		public String RG_SG_si_Cluster;

		public String getRG_SG_si_Cluster() {
			return this.RG_SG_si_Cluster;
		}

		public String Requete_SQL;

		public String getRequete_SQL() {
			return this.Requete_SQL;
		}

		public String UserName_DB_Name;

		public String getUserName_DB_Name() {
			return this.UserName_DB_Name;
		}

		public String Resultat_Attendu_de_la_requete;

		public String getResultat_Attendu_de_la_requete() {
			return this.Resultat_Attendu_de_la_requete;
		}

		public String Perform_action;

		public String getPerform_action() {
			return this.Perform_action;
		}

		public String Criticite;

		public String getCriticite() {
			return this.Criticite;
		}

		public String Message_d_alarme;

		public String getMessage_d_alarme() {
			return this.Message_d_alarme;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Intervalle_de_polling;

		public String getIntervalle_de_polling() {
			return this.Intervalle_de_polling;
		}

		public String Ref__Service;

		public String getRef__Service() {
			return this.Ref__Service;
		}

		public String Objet;

		public String getObjet() {
			return this.Objet;
		}

		public String Monitored_By;

		public String getMonitored_By() {
			return this.Monitored_By;
		}

		public String Nom_Template;

		public String getNom_Template() {
			return this.Nom_Template;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Requete_SQL = readString(dis);

					this.UserName_DB_Name = readString(dis);

					this.Resultat_Attendu_de_la_requete = readString(dis);

					this.Perform_action = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Ref__Service = readString(dis);

					this.Objet = readString(dis);

					this.Monitored_By = readString(dis);

					this.Nom_Template = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Requete_SQL = readString(dis);

					this.UserName_DB_Name = readString(dis);

					this.Resultat_Attendu_de_la_requete = readString(dis);

					this.Perform_action = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Ref__Service = readString(dis);

					this.Objet = readString(dis);

					this.Monitored_By = readString(dis);

					this.Nom_Template = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Requete_SQL, dos);

				// String

				writeString(this.UserName_DB_Name, dos);

				// String

				writeString(this.Resultat_Attendu_de_la_requete, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Nom_Template, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Requete_SQL, dos);

				// String

				writeString(this.UserName_DB_Name, dos);

				// String

				writeString(this.Resultat_Attendu_de_la_requete, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Nom_Template, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref__des_composants=" + Ref__des_composants);
			sb.append(",RG_SG_si_Cluster=" + RG_SG_si_Cluster);
			sb.append(",Requete_SQL=" + Requete_SQL);
			sb.append(",UserName_DB_Name=" + UserName_DB_Name);
			sb.append(",Resultat_Attendu_de_la_requete=" + Resultat_Attendu_de_la_requete);
			sb.append(",Perform_action=" + Perform_action);
			sb.append(",Criticite=" + Criticite);
			sb.append(",Message_d_alarme=" + Message_d_alarme);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Intervalle_de_polling=" + Intervalle_de_polling);
			sb.append(",Ref__Service=" + Ref__Service);
			sb.append(",Objet=" + Objet);
			sb.append(",Monitored_By=" + Monitored_By);
			sb.append(",Nom_Template=" + Nom_Template);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row21Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row12Struct implements routines.system.IPersistableRow<row12Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref__des_composants;

		public String getRef__des_composants() {
			return this.Ref__des_composants;
		}

		public String RG_SG_si_Cluster;

		public String getRG_SG_si_Cluster() {
			return this.RG_SG_si_Cluster;
		}

		public String Requete_SQL;

		public String getRequete_SQL() {
			return this.Requete_SQL;
		}

		public String UserName_DB_Name;

		public String getUserName_DB_Name() {
			return this.UserName_DB_Name;
		}

		public String Resultat_Attendu_de_la_requete;

		public String getResultat_Attendu_de_la_requete() {
			return this.Resultat_Attendu_de_la_requete;
		}

		public String Perform_action;

		public String getPerform_action() {
			return this.Perform_action;
		}

		public String Criticite;

		public String getCriticite() {
			return this.Criticite;
		}

		public String Message_d_alarme;

		public String getMessage_d_alarme() {
			return this.Message_d_alarme;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Intervalle_de_polling;

		public String getIntervalle_de_polling() {
			return this.Intervalle_de_polling;
		}

		public String Ref__Service;

		public String getRef__Service() {
			return this.Ref__Service;
		}

		public String Objet;

		public String getObjet() {
			return this.Objet;
		}

		public String Monitored_By;

		public String getMonitored_By() {
			return this.Monitored_By;
		}

		public String Nom_Template;

		public String getNom_Template() {
			return this.Nom_Template;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Requete_SQL = readString(dis);

					this.UserName_DB_Name = readString(dis);

					this.Resultat_Attendu_de_la_requete = readString(dis);

					this.Perform_action = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Ref__Service = readString(dis);

					this.Objet = readString(dis);

					this.Monitored_By = readString(dis);

					this.Nom_Template = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Requete_SQL = readString(dis);

					this.UserName_DB_Name = readString(dis);

					this.Resultat_Attendu_de_la_requete = readString(dis);

					this.Perform_action = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Ref__Service = readString(dis);

					this.Objet = readString(dis);

					this.Monitored_By = readString(dis);

					this.Nom_Template = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Requete_SQL, dos);

				// String

				writeString(this.UserName_DB_Name, dos);

				// String

				writeString(this.Resultat_Attendu_de_la_requete, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Nom_Template, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Requete_SQL, dos);

				// String

				writeString(this.UserName_DB_Name, dos);

				// String

				writeString(this.Resultat_Attendu_de_la_requete, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Nom_Template, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref__des_composants=" + Ref__des_composants);
			sb.append(",RG_SG_si_Cluster=" + RG_SG_si_Cluster);
			sb.append(",Requete_SQL=" + Requete_SQL);
			sb.append(",UserName_DB_Name=" + UserName_DB_Name);
			sb.append(",Resultat_Attendu_de_la_requete=" + Resultat_Attendu_de_la_requete);
			sb.append(",Perform_action=" + Perform_action);
			sb.append(",Criticite=" + Criticite);
			sb.append(",Message_d_alarme=" + Message_d_alarme);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Intervalle_de_polling=" + Intervalle_de_polling);
			sb.append(",Ref__Service=" + Ref__Service);
			sb.append(",Objet=" + Objet);
			sb.append(",Monitored_By=" + Monitored_By);
			sb.append(",Nom_Template=" + Nom_Template);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row12Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref__des_composants;

		public String getRef__des_composants() {
			return this.Ref__des_composants;
		}

		public String RG_SG_si_Cluster;

		public String getRG_SG_si_Cluster() {
			return this.RG_SG_si_Cluster;
		}

		public String Requete_SQL;

		public String getRequete_SQL() {
			return this.Requete_SQL;
		}

		public String UserName_DB_Name;

		public String getUserName_DB_Name() {
			return this.UserName_DB_Name;
		}

		public String Resultat_Attendu_de_la_requete;

		public String getResultat_Attendu_de_la_requete() {
			return this.Resultat_Attendu_de_la_requete;
		}

		public String Perform_action;

		public String getPerform_action() {
			return this.Perform_action;
		}

		public String Criticite;

		public String getCriticite() {
			return this.Criticite;
		}

		public String Message_d_alarme;

		public String getMessage_d_alarme() {
			return this.Message_d_alarme;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Intervalle_de_polling;

		public String getIntervalle_de_polling() {
			return this.Intervalle_de_polling;
		}

		public String Ref__Service;

		public String getRef__Service() {
			return this.Ref__Service;
		}

		public String Objet;

		public String getObjet() {
			return this.Objet;
		}

		public String Monitored_By;

		public String getMonitored_By() {
			return this.Monitored_By;
		}

		public String Nom_Template;

		public String getNom_Template() {
			return this.Nom_Template;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Requete_SQL = readString(dis);

					this.UserName_DB_Name = readString(dis);

					this.Resultat_Attendu_de_la_requete = readString(dis);

					this.Perform_action = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Ref__Service = readString(dis);

					this.Objet = readString(dis);

					this.Monitored_By = readString(dis);

					this.Nom_Template = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Requete_SQL = readString(dis);

					this.UserName_DB_Name = readString(dis);

					this.Resultat_Attendu_de_la_requete = readString(dis);

					this.Perform_action = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Ref__Service = readString(dis);

					this.Objet = readString(dis);

					this.Monitored_By = readString(dis);

					this.Nom_Template = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Requete_SQL, dos);

				// String

				writeString(this.UserName_DB_Name, dos);

				// String

				writeString(this.Resultat_Attendu_de_la_requete, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Nom_Template, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Requete_SQL, dos);

				// String

				writeString(this.UserName_DB_Name, dos);

				// String

				writeString(this.Resultat_Attendu_de_la_requete, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Nom_Template, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref__des_composants=" + Ref__des_composants);
			sb.append(",RG_SG_si_Cluster=" + RG_SG_si_Cluster);
			sb.append(",Requete_SQL=" + Requete_SQL);
			sb.append(",UserName_DB_Name=" + UserName_DB_Name);
			sb.append(",Resultat_Attendu_de_la_requete=" + Resultat_Attendu_de_la_requete);
			sb.append(",Perform_action=" + Perform_action);
			sb.append(",Criticite=" + Criticite);
			sb.append(",Message_d_alarme=" + Message_d_alarme);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Intervalle_de_polling=" + Intervalle_de_polling);
			sb.append(",Ref__Service=" + Ref__Service);
			sb.append(",Objet=" + Objet);
			sb.append(",Monitored_By=" + Monitored_By);
			sb.append(",Nom_Template=" + Nom_Template);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class OnSubjobOkStructtIterateToFlow_1
			implements routines.system.IPersistableRow<OnSubjobOkStructtIterateToFlow_1> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String filepath;

		public String getFilepath() {
			return this.filepath;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.filepath = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.filepath = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.filepath, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.filepath, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("filepath=" + filepath);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnSubjobOkStructtIterateToFlow_1 other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileList_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileList_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;
		String currentVirtualComponent = null;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();
				row10Struct row10 = new row10Struct();
				row19Struct row19 = new row19Struct();
				insertclustersStruct insertclusters = new insertclustersStruct();
				row2Struct row2 = new row2Struct();
				row11Struct row11 = new row11Struct();
				row20Struct row20 = new row20Struct();
				insertprocessStruct insertprocess = new insertprocessStruct();
				row3Struct row3 = new row3Struct();
				row12Struct row12 = new row12Struct();
				row21Struct row21 = new row21Struct();
				insertrequetesStruct insertrequetes = new insertrequetesStruct();

				/**
				 * [tFileList_1 begin ] start
				 */

				int NB_ITERATE_tFileInputExcel_18 = 0; // for statistics

				int NB_ITERATE_tFileInputExcel_1 = 0; // for statistics

				int NB_ITERATE_tIterateToFlow_1_ITFO = 0; // for statistics

				int NB_ITERATE_tFileInputExcel_13 = 0; // for statistics

				ok_Hash.put("tFileList_1", false);
				start_Hash.put("tFileList_1", System.currentTimeMillis());

				currentComponent = "tFileList_1";

				int tos_count_tFileList_1 = 0;

				String directory_tFileList_1 = "C:/Users/Majdi/Downloads/pasfait";
				final java.util.List<String> maskList_tFileList_1 = new java.util.ArrayList<String>();
				final java.util.List<java.util.regex.Pattern> patternList_tFileList_1 = new java.util.ArrayList<java.util.regex.Pattern>();
				maskList_tFileList_1.add("*.xlsx");
				for (final String filemask_tFileList_1 : maskList_tFileList_1) {
					String filemask_compile_tFileList_1 = filemask_tFileList_1;

					filemask_compile_tFileList_1 = org.apache.oro.text.GlobCompiler.globToPerl5(
							filemask_tFileList_1.toCharArray(), org.apache.oro.text.GlobCompiler.DEFAULT_MASK);

					java.util.regex.Pattern fileNamePattern_tFileList_1 = java.util.regex.Pattern
							.compile(filemask_compile_tFileList_1);
					patternList_tFileList_1.add(fileNamePattern_tFileList_1);
				}
				int NB_FILEtFileList_1 = 0;

				final boolean case_sensitive_tFileList_1 = true;

				final java.util.List<java.io.File> list_tFileList_1 = new java.util.ArrayList<java.io.File>();
				final java.util.Set<String> filePath_tFileList_1 = new java.util.HashSet<String>();
				java.io.File file_tFileList_1 = new java.io.File(directory_tFileList_1);

				file_tFileList_1.listFiles(new java.io.FilenameFilter() {
					public boolean accept(java.io.File dir, String name) {
						java.io.File file = new java.io.File(dir, name);
						if (!file.isDirectory()) {

							String fileName_tFileList_1 = file.getName();
							for (final java.util.regex.Pattern fileNamePattern_tFileList_1 : patternList_tFileList_1) {
								if (fileNamePattern_tFileList_1.matcher(fileName_tFileList_1).matches()) {
									if (!filePath_tFileList_1.contains(file.getAbsolutePath())) {
										list_tFileList_1.add(file);
										filePath_tFileList_1.add(file.getAbsolutePath());
									}
								}
							}
						}
						return true;
					}
				});
				java.util.Collections.sort(list_tFileList_1);

				for (int i_tFileList_1 = 0; i_tFileList_1 < list_tFileList_1.size(); i_tFileList_1++) {
					java.io.File files_tFileList_1 = list_tFileList_1.get(i_tFileList_1);
					String fileName_tFileList_1 = files_tFileList_1.getName();

					String currentFileName_tFileList_1 = files_tFileList_1.getName();
					String currentFilePath_tFileList_1 = files_tFileList_1.getAbsolutePath();
					String currentFileDirectory_tFileList_1 = files_tFileList_1.getParent();
					String currentFileExtension_tFileList_1 = null;

					if (files_tFileList_1.getName().contains(".") && files_tFileList_1.isFile()) {
						currentFileExtension_tFileList_1 = files_tFileList_1.getName()
								.substring(files_tFileList_1.getName().lastIndexOf(".") + 1);
					} else {
						currentFileExtension_tFileList_1 = "";
					}

					NB_FILEtFileList_1++;
					globalMap.put("tFileList_1_CURRENT_FILE", currentFileName_tFileList_1);
					globalMap.put("tFileList_1_CURRENT_FILEPATH", currentFilePath_tFileList_1);
					globalMap.put("tFileList_1_CURRENT_FILEDIRECTORY", currentFileDirectory_tFileList_1);
					globalMap.put("tFileList_1_CURRENT_FILEEXTENSION", currentFileExtension_tFileList_1);
					globalMap.put("tFileList_1_NB_FILE", NB_FILEtFileList_1);

					/**
					 * [tFileList_1 begin ] stop
					 */

					/**
					 * [tFileList_1 main ] start
					 */

					currentComponent = "tFileList_1";

					tos_count_tFileList_1++;

					/**
					 * [tFileList_1 main ] stop
					 */

					/**
					 * [tFileList_1 process_data_begin ] start
					 */

					currentComponent = "tFileList_1";

					/**
					 * [tFileList_1 process_data_begin ] stop
					 */
					NB_ITERATE_tIterateToFlow_1_ITFO++;

					if (execStat) {
						runStat.updateStatOnConnection("iterate1", 1, "exec" + NB_ITERATE_tIterateToFlow_1_ITFO);
						// Thread.sleep(1000);
					}

					/**
					 * [tIterateToFlow_1_ITFO begin ] start
					 */

					ok_Hash.put("tIterateToFlow_1_ITFO", false);
					start_Hash.put("tIterateToFlow_1_ITFO", System.currentTimeMillis());

					currentVirtualComponent = "tIterateToFlow_1";

					currentComponent = "tIterateToFlow_1_ITFO";

					int tos_count_tIterateToFlow_1_ITFO = 0;

					OnSubjobOkStructtIterateToFlow_1 struct_tIterateToFlow_1_ITFO = new OnSubjobOkStructtIterateToFlow_1();
					struct_tIterateToFlow_1_ITFO.filepath = ((String) globalMap.get("tFileList_1_CURRENT_FILEPATH"));

					if (globalMap.get("tIterateToFlow_1") != null) {
						java.util.List<OnSubjobOkStructtIterateToFlow_1> list_tIterateToFlow_1_ITFO = (java.util.List<OnSubjobOkStructtIterateToFlow_1>) globalMap
								.get("tIterateToFlow_1");
						list_tIterateToFlow_1_ITFO.add(struct_tIterateToFlow_1_ITFO);
					} else {
						java.util.List<OnSubjobOkStructtIterateToFlow_1> list_tIterateToFlow_1_ITFO = new java.util.ArrayList<OnSubjobOkStructtIterateToFlow_1>();
						list_tIterateToFlow_1_ITFO.add(struct_tIterateToFlow_1_ITFO);
						globalMap.put("tIterateToFlow_1", list_tIterateToFlow_1_ITFO);
					}

					/**
					 * [tIterateToFlow_1_ITFO begin ] stop
					 */

					/**
					 * [tIterateToFlow_1_ITFO main ] start
					 */

					currentVirtualComponent = "tIterateToFlow_1";

					currentComponent = "tIterateToFlow_1_ITFO";

					tos_count_tIterateToFlow_1_ITFO++;

					/**
					 * [tIterateToFlow_1_ITFO main ] stop
					 */

					/**
					 * [tIterateToFlow_1_ITFO process_data_begin ] start
					 */

					currentVirtualComponent = "tIterateToFlow_1";

					currentComponent = "tIterateToFlow_1_ITFO";

					/**
					 * [tIterateToFlow_1_ITFO process_data_begin ] stop
					 */

					/**
					 * [tIterateToFlow_1_ITFO process_data_end ] start
					 */

					currentVirtualComponent = "tIterateToFlow_1";

					currentComponent = "tIterateToFlow_1_ITFO";

					/**
					 * [tIterateToFlow_1_ITFO process_data_end ] stop
					 */

					/**
					 * [tIterateToFlow_1_ITFO end ] start
					 */

					currentVirtualComponent = "tIterateToFlow_1";

					currentComponent = "tIterateToFlow_1_ITFO";

					ok_Hash.put("tIterateToFlow_1_ITFO", true);
					end_Hash.put("tIterateToFlow_1_ITFO", System.currentTimeMillis());

					/**
					 * [tIterateToFlow_1_ITFO end ] stop
					 */
					if (execStat) {
						runStat.updateStatOnConnection("iterate1", 2, "exec" + NB_ITERATE_tIterateToFlow_1_ITFO);
					}

					NB_ITERATE_tFileInputExcel_1++;

					if (execStat) {
						runStat.updateStatOnConnection("row19", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("insertclusters", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row1", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row10", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate2", 1, "exec" + NB_ITERATE_tFileInputExcel_1);
						// Thread.sleep(1000);
					}

					/**
					 * [tDBOutput_1 begin ] start
					 */

					ok_Hash.put("tDBOutput_1", false);
					start_Hash.put("tDBOutput_1", System.currentTimeMillis());

					currentComponent = "tDBOutput_1";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "insertclusters");
					}

					int tos_count_tDBOutput_1 = 0;

					int nb_line_tDBOutput_1 = 0;
					int nb_line_update_tDBOutput_1 = 0;
					int nb_line_inserted_tDBOutput_1 = 0;
					int nb_line_deleted_tDBOutput_1 = 0;
					int nb_line_rejected_tDBOutput_1 = 0;

					int deletedCount_tDBOutput_1 = 0;
					int updatedCount_tDBOutput_1 = 0;
					int insertedCount_tDBOutput_1 = 0;
					int rowsToCommitCount_tDBOutput_1 = 0;
					int rejectedCount_tDBOutput_1 = 0;

					String tableName_tDBOutput_1 = "cluster";
					boolean whetherReject_tDBOutput_1 = false;

					java.util.Calendar calendar_tDBOutput_1 = java.util.Calendar.getInstance();
					calendar_tDBOutput_1.set(1, 0, 1, 0, 0, 0);
					long year1_tDBOutput_1 = calendar_tDBOutput_1.getTime().getTime();
					calendar_tDBOutput_1.set(10000, 0, 1, 0, 0, 0);
					long year10000_tDBOutput_1 = calendar_tDBOutput_1.getTime().getTime();
					long date_tDBOutput_1;

					java.sql.Connection conn_tDBOutput_1 = null;

					String properties_tDBOutput_1 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
					if (properties_tDBOutput_1 == null || properties_tDBOutput_1.trim().length() == 0) {
						properties_tDBOutput_1 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
					} else {
						if (!properties_tDBOutput_1.contains("rewriteBatchedStatements=")) {
							properties_tDBOutput_1 += "&rewriteBatchedStatements=true";
						}

						if (!properties_tDBOutput_1.contains("allowLoadLocalInfile=")) {
							properties_tDBOutput_1 += "&allowLoadLocalInfile=true";
						}
					}

					String url_tDBOutput_1 = "jdbc:mariadb://" + "127.0.0.1" + ":" + "3306" + "/" + "ooredoo" + "?"
							+ properties_tDBOutput_1;

					String driverClass_tDBOutput_1 = "org.mariadb.jdbc.Driver";

					String dbUser_tDBOutput_1 = "root";

					final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:d983d9SCX2OirX/XLBVeDnaSfc4QHZnR431njg==");

					String dbPwd_tDBOutput_1 = decryptedPassword_tDBOutput_1;
					java.lang.Class.forName(driverClass_tDBOutput_1);

					conn_tDBOutput_1 = java.sql.DriverManager.getConnection(url_tDBOutput_1, dbUser_tDBOutput_1,
							dbPwd_tDBOutput_1);

					resourceMap.put("conn_tDBOutput_1", conn_tDBOutput_1);
					conn_tDBOutput_1.setAutoCommit(false);
					int commitEvery_tDBOutput_1 = 10000;
					int commitCounter_tDBOutput_1 = 0;

					int count_tDBOutput_1 = 0;

					String insert_tDBOutput_1 = "INSERT IGNORE INTO `" + "cluster"
							+ "` (`id`,`Ref`,`etat`,`Nom_du_Ressource_Group/Package_Service_Guard`,`Adresse_IP`,`Liste_des_serveurs_concernés`,`Logiciel_Cluster`,`Version`,`Mode`,`Serveur_actif`,`Compléments_d'informations`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

					java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1
							.prepareStatement(insert_tDBOutput_1);
					resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);

					/**
					 * [tDBOutput_1 begin ] stop
					 */

					/**
					 * [tMap_2 begin ] start
					 */

					ok_Hash.put("tMap_2", false);
					start_Hash.put("tMap_2", System.currentTimeMillis());

					currentComponent = "tMap_2";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row19");
					}

					int tos_count_tMap_2 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
					class Var__tMap_2__Struct {
					}
					Var__tMap_2__Struct Var__tMap_2 = new Var__tMap_2__Struct();
// ###############################

// ###############################
// # Outputs initialization
					insertclustersStruct insertclusters_tmp = new insertclustersStruct();
// ###############################

					/**
					 * [tMap_2 begin ] stop
					 */

					/**
					 * [tJavaRow_2 begin ] start
					 */

					ok_Hash.put("tJavaRow_2", false);
					start_Hash.put("tJavaRow_2", System.currentTimeMillis());

					currentComponent = "tJavaRow_2";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row10");
					}

					int tos_count_tJavaRow_2 = 0;

					int nb_line_tJavaRow_2 = 0;

					/**
					 * [tJavaRow_2 begin ] stop
					 */

					/**
					 * [tFilterRow_1 begin ] start
					 */

					ok_Hash.put("tFilterRow_1", false);
					start_Hash.put("tFilterRow_1", System.currentTimeMillis());

					currentComponent = "tFilterRow_1";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
					}

					int tos_count_tFilterRow_1 = 0;

					int nb_line_tFilterRow_1 = 0;
					int nb_line_ok_tFilterRow_1 = 0;
					int nb_line_reject_tFilterRow_1 = 0;

					class Operator_tFilterRow_1 {
						private String sErrorMsg = "";
						private boolean bMatchFlag = true;
						private String sUnionFlag = "&&";

						public Operator_tFilterRow_1(String unionFlag) {
							sUnionFlag = unionFlag;
							bMatchFlag = "||".equals(unionFlag) ? false : true;
						}

						public String getErrorMsg() {
							if (sErrorMsg != null && sErrorMsg.length() > 1)
								return sErrorMsg.substring(1);
							else
								return null;
						}

						public boolean getMatchFlag() {
							return bMatchFlag;
						}

						public void matches(boolean partMatched, String reason) {
							// no need to care about the next judgement
							if ("||".equals(sUnionFlag) && bMatchFlag) {
								return;
							}

							if (!partMatched) {
								sErrorMsg += "|" + reason;
							}

							if ("||".equals(sUnionFlag))
								bMatchFlag = bMatchFlag || partMatched;
							else
								bMatchFlag = bMatchFlag && partMatched;
						}
					}

					/**
					 * [tFilterRow_1 begin ] stop
					 */

					/**
					 * [tFileInputExcel_1 begin ] start
					 */

					ok_Hash.put("tFileInputExcel_1", false);
					start_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

					currentComponent = "tFileInputExcel_1";

					int tos_count_tFileInputExcel_1 = 0;

					final String decryptedPassword_tFileInputExcel_1 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:4Ce3FFaTYNI5+HP+LdhTPeUA/Futg4dcUNO+WQ==");
					String password_tFileInputExcel_1 = decryptedPassword_tFileInputExcel_1;
					if (password_tFileInputExcel_1.isEmpty()) {
						password_tFileInputExcel_1 = null;
					}
					class RegexUtil_tFileInputExcel_1 {

						public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
								org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, String oneSheetName,
								boolean useRegex) {

							java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();

							if (useRegex) {// this part process the regex issue

								java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
								for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
									String sheetName = sheet.getSheetName();
									java.util.regex.Matcher matcher = pattern.matcher(sheetName);
									if (matcher.matches()) {
										if (sheet != null) {
											list.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet);
										}
									}
								}

							} else {
								org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
										.getSheet(oneSheetName);
								if (sheet != null) {
									list.add(sheet);
								}

							}

							return list;
						}

						public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
								org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, int index, boolean useRegex) {
							java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
							org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
									.getSheetAt(index);
							if (sheet != null) {
								list.add(sheet);
							}
							return list;
						}

					}
					RegexUtil_tFileInputExcel_1 regexUtil_tFileInputExcel_1 = new RegexUtil_tFileInputExcel_1();

					Object source_tFileInputExcel_1 = ((String) globalMap.get("tFileList_1_CURRENT_FILEPATH"));
					org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_1 = null;

					if (source_tFileInputExcel_1 instanceof String) {
						workbook_tFileInputExcel_1 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
								.create(new java.io.File((String) source_tFileInputExcel_1), password_tFileInputExcel_1,
										true);
					} else if (source_tFileInputExcel_1 instanceof java.io.InputStream) {
						workbook_tFileInputExcel_1 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
								.create((java.io.InputStream) source_tFileInputExcel_1, password_tFileInputExcel_1);
					} else {
						workbook_tFileInputExcel_1 = null;
						throw new java.lang.Exception(
								"The data source should be specified as Inputstream or File Path!");
					}
					try {

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_1 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						sheetList_tFileInputExcel_1.addAll(
								regexUtil_tFileInputExcel_1.getSheets(workbook_tFileInputExcel_1, "Cluster ", false));
						if (sheetList_tFileInputExcel_1.size() <= 0) {
							throw new RuntimeException("Special sheets not exist!");
						}

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_1 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
							if (sheet_FilterNull_tFileInputExcel_1 != null
									&& sheetList_FilterNull_tFileInputExcel_1.iterator() != null
									&& sheet_FilterNull_tFileInputExcel_1.iterator().hasNext()) {
								sheetList_FilterNull_tFileInputExcel_1.add(sheet_FilterNull_tFileInputExcel_1);
							}
						}
						sheetList_tFileInputExcel_1 = sheetList_FilterNull_tFileInputExcel_1;
						if (sheetList_tFileInputExcel_1.size() > 0) {
							int nb_line_tFileInputExcel_1 = 0;

							int begin_line_tFileInputExcel_1 = 1;

							int footer_input_tFileInputExcel_1 = 0;

							int end_line_tFileInputExcel_1 = 0;
							for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
								end_line_tFileInputExcel_1 += (sheet_tFileInputExcel_1.getLastRowNum() + 1);
							}
							end_line_tFileInputExcel_1 -= footer_input_tFileInputExcel_1;
							int limit_tFileInputExcel_1 = -1;
							int start_column_tFileInputExcel_1 = 1 - 1;
							int end_column_tFileInputExcel_1 = -1;

							org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_1 = null;
							org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1
									.get(0);
							int rowCount_tFileInputExcel_1 = 0;
							int sheetIndex_tFileInputExcel_1 = 0;
							int currentRows_tFileInputExcel_1 = (sheetList_tFileInputExcel_1.get(0).getLastRowNum()
									+ 1);

							// for the number format
							java.text.DecimalFormat df_tFileInputExcel_1 = new java.text.DecimalFormat(
									"#.####################################");
							char decimalChar_tFileInputExcel_1 = df_tFileInputExcel_1.getDecimalFormatSymbols()
									.getDecimalSeparator();

							for (int i_tFileInputExcel_1 = begin_line_tFileInputExcel_1; i_tFileInputExcel_1 < end_line_tFileInputExcel_1; i_tFileInputExcel_1++) {

								int emptyColumnCount_tFileInputExcel_1 = 0;

								if (limit_tFileInputExcel_1 != -1
										&& nb_line_tFileInputExcel_1 >= limit_tFileInputExcel_1) {
									break;
								}

								while (i_tFileInputExcel_1 >= rowCount_tFileInputExcel_1
										+ currentRows_tFileInputExcel_1) {
									rowCount_tFileInputExcel_1 += currentRows_tFileInputExcel_1;
									sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1
											.get(++sheetIndex_tFileInputExcel_1);
									currentRows_tFileInputExcel_1 = (sheet_tFileInputExcel_1.getLastRowNum() + 1);
								}
								globalMap.put("tFileInputExcel_1_CURRENT_SHEET",
										sheet_tFileInputExcel_1.getSheetName());
								if (rowCount_tFileInputExcel_1 <= i_tFileInputExcel_1) {
									row_tFileInputExcel_1 = sheet_tFileInputExcel_1
											.getRow(i_tFileInputExcel_1 - rowCount_tFileInputExcel_1);
								}
								row1 = null;
								int tempRowLength_tFileInputExcel_1 = 11;

								int columnIndex_tFileInputExcel_1 = 0;

								String[] temp_row_tFileInputExcel_1 = new String[tempRowLength_tFileInputExcel_1];
								int excel_end_column_tFileInputExcel_1;
								if (row_tFileInputExcel_1 == null) {
									excel_end_column_tFileInputExcel_1 = 0;
								} else {
									excel_end_column_tFileInputExcel_1 = row_tFileInputExcel_1.getLastCellNum();
								}
								int actual_end_column_tFileInputExcel_1;
								if (end_column_tFileInputExcel_1 == -1) {
									actual_end_column_tFileInputExcel_1 = excel_end_column_tFileInputExcel_1;
								} else {
									actual_end_column_tFileInputExcel_1 = end_column_tFileInputExcel_1 > excel_end_column_tFileInputExcel_1
											? excel_end_column_tFileInputExcel_1
											: end_column_tFileInputExcel_1;
								}
								org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_1 = null;
								for (int i = 0; i < tempRowLength_tFileInputExcel_1; i++) {
									if (i + start_column_tFileInputExcel_1 < actual_end_column_tFileInputExcel_1) {
										org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_1 = row_tFileInputExcel_1
												.getCell(i + start_column_tFileInputExcel_1);
										if (cell_tFileInputExcel_1 != null) {
											switch (cell_tFileInputExcel_1.getCellType()) {
											case STRING:
												temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
														.getRichStringCellValue().getString();
												break;
											case NUMERIC:
												if (org.apache.poi.ss.usermodel.DateUtil
														.isCellDateFormatted(cell_tFileInputExcel_1)) {
													temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
															.getDateCellValue().toString();
												} else {
													temp_row_tFileInputExcel_1[i] = df_tFileInputExcel_1
															.format(cell_tFileInputExcel_1.getNumericCellValue());
												}
												break;
											case BOOLEAN:
												temp_row_tFileInputExcel_1[i] = String
														.valueOf(cell_tFileInputExcel_1.getBooleanCellValue());
												break;
											case FORMULA:
												switch (cell_tFileInputExcel_1.getCachedFormulaResultType()) {
												case STRING:
													temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
															.getRichStringCellValue().getString();
													break;
												case NUMERIC:
													if (org.apache.poi.ss.usermodel.DateUtil
															.isCellDateFormatted(cell_tFileInputExcel_1)) {
														temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
																.getDateCellValue().toString();
													} else {
														ne_tFileInputExcel_1 = new org.apache.poi.ss.formula.eval.NumberEval(
																cell_tFileInputExcel_1.getNumericCellValue());
														temp_row_tFileInputExcel_1[i] = ne_tFileInputExcel_1
																.getStringValue();
													}
													break;
												case BOOLEAN:
													temp_row_tFileInputExcel_1[i] = String
															.valueOf(cell_tFileInputExcel_1.getBooleanCellValue());
													break;
												default:
													temp_row_tFileInputExcel_1[i] = "";
												}
												break;
											default:
												temp_row_tFileInputExcel_1[i] = "";
											}
										} else {
											temp_row_tFileInputExcel_1[i] = "";
										}

									} else {
										temp_row_tFileInputExcel_1[i] = "";
									}
								}
								boolean whetherReject_tFileInputExcel_1 = false;
								row1 = new row1Struct();
								int curColNum_tFileInputExcel_1 = -1;
								String curColName_tFileInputExcel_1 = "";
								try {
									columnIndex_tFileInputExcel_1 = 0;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "N__Ref";

										row1.N__Ref = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.N__Ref = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 1;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Ref";

										row1.Ref = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Ref = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 2;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Etat";

										row1.Etat = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Etat = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 3;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Nom_du_Ressource_Group___Package_Service_Guard";

										row1.Nom_du_Ressource_Group___Package_Service_Guard = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Nom_du_Ressource_Group___Package_Service_Guard = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 4;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Adresse_IP";

										row1.Adresse_IP = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Adresse_IP = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 5;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Liste_des_serveurs_concernes";

										row1.Liste_des_serveurs_concernes = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Liste_des_serveurs_concernes = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 6;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Logiciel_Cluster";

										row1.Logiciel_Cluster = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Logiciel_Cluster = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 7;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Version";

										row1.Version = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Version = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 8;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Mode";

										row1.Mode = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Mode = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 9;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Serveur_Actif";

										row1.Serveur_Actif = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Serveur_Actif = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 10;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Complements_d_informations";

										row1.Complements_d_informations = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Complements_d_informations = null;
										emptyColumnCount_tFileInputExcel_1++;
									}

									nb_line_tFileInputExcel_1++;

								} catch (java.lang.Exception e) {
									globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());
									whetherReject_tFileInputExcel_1 = true;
									System.err.println(e.getMessage());
									row1 = null;
								}

								/**
								 * [tFileInputExcel_1 begin ] stop
								 */

								/**
								 * [tFileInputExcel_1 main ] start
								 */

								currentComponent = "tFileInputExcel_1";

								tos_count_tFileInputExcel_1++;

								/**
								 * [tFileInputExcel_1 main ] stop
								 */

								/**
								 * [tFileInputExcel_1 process_data_begin ] start
								 */

								currentComponent = "tFileInputExcel_1";

								/**
								 * [tFileInputExcel_1 process_data_begin ] stop
								 */
// Start of branch "row1"
								if (row1 != null) {

									/**
									 * [tFilterRow_1 main ] start
									 */

									currentComponent = "tFilterRow_1";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "row1"

										);
									}

									row10 = null;
									Operator_tFilterRow_1 ope_tFilterRow_1 = new Operator_tFilterRow_1("&&");
									ope_tFilterRow_1.matches((row1.Ref != null), "Ref!=null failed");

									if (ope_tFilterRow_1.getMatchFlag()) {
										if (row10 == null) {
											row10 = new row10Struct();
										}
										row10.N__Ref = row1.N__Ref;
										row10.Ref = row1.Ref;
										row10.Etat = row1.Etat;
										row10.Nom_du_Ressource_Group___Package_Service_Guard = row1.Nom_du_Ressource_Group___Package_Service_Guard;
										row10.Adresse_IP = row1.Adresse_IP;
										row10.Liste_des_serveurs_concernes = row1.Liste_des_serveurs_concernes;
										row10.Logiciel_Cluster = row1.Logiciel_Cluster;
										row10.Version = row1.Version;
										row10.Mode = row1.Mode;
										row10.Serveur_Actif = row1.Serveur_Actif;
										row10.Complements_d_informations = row1.Complements_d_informations;
										nb_line_ok_tFilterRow_1++;
									} else {
										nb_line_reject_tFilterRow_1++;
									}

									nb_line_tFilterRow_1++;

									tos_count_tFilterRow_1++;

									/**
									 * [tFilterRow_1 main ] stop
									 */

									/**
									 * [tFilterRow_1 process_data_begin ] start
									 */

									currentComponent = "tFilterRow_1";

									/**
									 * [tFilterRow_1 process_data_begin ] stop
									 */
// Start of branch "row10"
									if (row10 != null) {

										/**
										 * [tJavaRow_2 main ] start
										 */

										currentComponent = "tJavaRow_2";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row10"

											);
										}

										String fileName = (String) source_tFileInputExcel_1;

// Extraire la partie "POSANET" du nom du fichier Excel
										String[] parts = fileName.split("_");
										String fileWord = parts[6];

// Récupérer la valeur de "N__Ref" et la convertir en String
										String rowRef = row10.N__Ref;

// Générer l'ID "EDB_POSANET_X"
										String id1 = "EDB_" + fileWord + "_Cluster_" + rowRef;
//Code généré selon les schémas d'entrée et de sortie
										row19.N__Ref = id1;
										row19.Ref = row10.Ref;
										row19.Etat = row10.Etat;
										row19.Nom_du_Ressource_Group___Package_Service_Guard = row10.Nom_du_Ressource_Group___Package_Service_Guard;
										row19.Adresse_IP = row10.Adresse_IP;
										row19.Liste_des_serveurs_concernes = row10.Liste_des_serveurs_concernes;
										row19.Logiciel_Cluster = row10.Logiciel_Cluster;
										row19.Version = row10.Version;
										row19.Mode = row10.Mode;
										row19.Serveur_Actif = row10.Serveur_Actif;
										row19.Complements_d_informations = row10.Complements_d_informations;

										nb_line_tJavaRow_2++;

										tos_count_tJavaRow_2++;

										/**
										 * [tJavaRow_2 main ] stop
										 */

										/**
										 * [tJavaRow_2 process_data_begin ] start
										 */

										currentComponent = "tJavaRow_2";

										/**
										 * [tJavaRow_2 process_data_begin ] stop
										 */

										/**
										 * [tMap_2 main ] start
										 */

										currentComponent = "tMap_2";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row19"

											);
										}

										boolean hasCasePrimitiveKeyWithNull_tMap_2 = false;

										// ###############################
										// # Input tables (lookups)
										boolean rejectedInnerJoin_tMap_2 = false;
										boolean mainRowRejected_tMap_2 = false;

										// ###############################
										{ // start of Var scope

											// ###############################
											// # Vars tables

											Var__tMap_2__Struct Var = Var__tMap_2;// ###############################
											// ###############################
											// # Output tables

											insertclusters = null;

// # Output table : 'insertclusters'
											insertclusters_tmp.id = row19.N__Ref;
											insertclusters_tmp.Ref = row19.Ref;
											insertclusters_tmp.etat = row19.Etat;
											insertclusters_tmp.Nom_du_Ressource_Group_Package_Service_Guard = row19.Nom_du_Ressource_Group___Package_Service_Guard;
											insertclusters_tmp.Adresse_IP = row19.Adresse_IP;
											insertclusters_tmp.Liste_des_serveurs_concernes = row19.Liste_des_serveurs_concernes;
											insertclusters_tmp.Logiciel_Cluster = row19.Logiciel_Cluster;
											insertclusters_tmp.Version = row19.Version;
											insertclusters_tmp.Mode = row19.Mode;
											insertclusters_tmp.Serveur_actif = row19.Serveur_Actif;
											insertclusters_tmp.Complements_d_informations = row19.Complements_d_informations;
											insertclusters = insertclusters_tmp;
// ###############################

										} // end of Var scope

										rejectedInnerJoin_tMap_2 = false;

										tos_count_tMap_2++;

										/**
										 * [tMap_2 main ] stop
										 */

										/**
										 * [tMap_2 process_data_begin ] start
										 */

										currentComponent = "tMap_2";

										/**
										 * [tMap_2 process_data_begin ] stop
										 */
// Start of branch "insertclusters"
										if (insertclusters != null) {

											/**
											 * [tDBOutput_1 main ] start
											 */

											currentComponent = "tDBOutput_1";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "insertclusters"

												);
											}

											whetherReject_tDBOutput_1 = false;
											if (insertclusters.id == null) {
												pstmt_tDBOutput_1.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(1, insertclusters.id);
											}

											if (insertclusters.Ref == null) {
												pstmt_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(2, insertclusters.Ref);
											}

											if (insertclusters.etat == null) {
												pstmt_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(3, insertclusters.etat);
											}

											if (insertclusters.Nom_du_Ressource_Group_Package_Service_Guard == null) {
												pstmt_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(4,
														insertclusters.Nom_du_Ressource_Group_Package_Service_Guard);
											}

											if (insertclusters.Adresse_IP == null) {
												pstmt_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(5, insertclusters.Adresse_IP);
											}

											if (insertclusters.Liste_des_serveurs_concernes == null) {
												pstmt_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(6,
														insertclusters.Liste_des_serveurs_concernes);
											}

											if (insertclusters.Logiciel_Cluster == null) {
												pstmt_tDBOutput_1.setNull(7, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(7, insertclusters.Logiciel_Cluster);
											}

											if (insertclusters.Version == null) {
												pstmt_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(8, insertclusters.Version);
											}

											if (insertclusters.Mode == null) {
												pstmt_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(9, insertclusters.Mode);
											}

											if (insertclusters.Serveur_actif == null) {
												pstmt_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(10, insertclusters.Serveur_actif);
											}

											if (insertclusters.Complements_d_informations == null) {
												pstmt_tDBOutput_1.setNull(11, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(11,
														insertclusters.Complements_d_informations);
											}

											try {
												nb_line_tDBOutput_1++;
												int processedCount_tDBOutput_1 = pstmt_tDBOutput_1.executeUpdate();
												insertedCount_tDBOutput_1 += processedCount_tDBOutput_1;
												rowsToCommitCount_tDBOutput_1 += processedCount_tDBOutput_1;
											} catch (java.lang.Exception e) {
												globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
												whetherReject_tDBOutput_1 = true;
												System.err.print(e.getMessage());
											}
											commitCounter_tDBOutput_1++;

											if (commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {

												if (rowsToCommitCount_tDBOutput_1 != 0) {
												}
												conn_tDBOutput_1.commit();
												if (rowsToCommitCount_tDBOutput_1 != 0) {
													rowsToCommitCount_tDBOutput_1 = 0;
												}
												commitCounter_tDBOutput_1 = 0;

											}

											tos_count_tDBOutput_1++;

											/**
											 * [tDBOutput_1 main ] stop
											 */

											/**
											 * [tDBOutput_1 process_data_begin ] start
											 */

											currentComponent = "tDBOutput_1";

											/**
											 * [tDBOutput_1 process_data_begin ] stop
											 */

											/**
											 * [tDBOutput_1 process_data_end ] start
											 */

											currentComponent = "tDBOutput_1";

											/**
											 * [tDBOutput_1 process_data_end ] stop
											 */

										} // End of branch "insertclusters"

										/**
										 * [tMap_2 process_data_end ] start
										 */

										currentComponent = "tMap_2";

										/**
										 * [tMap_2 process_data_end ] stop
										 */

										/**
										 * [tJavaRow_2 process_data_end ] start
										 */

										currentComponent = "tJavaRow_2";

										/**
										 * [tJavaRow_2 process_data_end ] stop
										 */

									} // End of branch "row10"

									/**
									 * [tFilterRow_1 process_data_end ] start
									 */

									currentComponent = "tFilterRow_1";

									/**
									 * [tFilterRow_1 process_data_end ] stop
									 */

								} // End of branch "row1"

								/**
								 * [tFileInputExcel_1 process_data_end ] start
								 */

								currentComponent = "tFileInputExcel_1";

								/**
								 * [tFileInputExcel_1 process_data_end ] stop
								 */

								/**
								 * [tFileInputExcel_1 end ] start
								 */

								currentComponent = "tFileInputExcel_1";

							}

							globalMap.put("tFileInputExcel_1_NB_LINE", nb_line_tFileInputExcel_1);

						}

					} finally {

						if (!(source_tFileInputExcel_1 instanceof java.io.InputStream)) {
							workbook_tFileInputExcel_1.getPackage().revert();
						}

					}

					ok_Hash.put("tFileInputExcel_1", true);
					end_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

					/**
					 * [tFileInputExcel_1 end ] stop
					 */

					/**
					 * [tFilterRow_1 end ] start
					 */

					currentComponent = "tFilterRow_1";

					globalMap.put("tFilterRow_1_NB_LINE", nb_line_tFilterRow_1);
					globalMap.put("tFilterRow_1_NB_LINE_OK", nb_line_ok_tFilterRow_1);
					globalMap.put("tFilterRow_1_NB_LINE_REJECT", nb_line_reject_tFilterRow_1);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
					}

					ok_Hash.put("tFilterRow_1", true);
					end_Hash.put("tFilterRow_1", System.currentTimeMillis());

					/**
					 * [tFilterRow_1 end ] stop
					 */

					/**
					 * [tJavaRow_2 end ] start
					 */

					currentComponent = "tJavaRow_2";

					globalMap.put("tJavaRow_2_NB_LINE", nb_line_tJavaRow_2);
					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row10");
					}

					ok_Hash.put("tJavaRow_2", true);
					end_Hash.put("tJavaRow_2", System.currentTimeMillis());

					/**
					 * [tJavaRow_2 end ] stop
					 */

					/**
					 * [tMap_2 end ] start
					 */

					currentComponent = "tMap_2";

// ###############################
// # Lookup hashes releasing
// ###############################      

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row19");
					}

					ok_Hash.put("tMap_2", true);
					end_Hash.put("tMap_2", System.currentTimeMillis());

					/**
					 * [tMap_2 end ] stop
					 */

					/**
					 * [tDBOutput_1 end ] start
					 */

					currentComponent = "tDBOutput_1";

					if (pstmt_tDBOutput_1 != null) {

						pstmt_tDBOutput_1.close();
						resourceMap.remove("pstmt_tDBOutput_1");

					}
					resourceMap.put("statementClosed_tDBOutput_1", true);
					if (commitCounter_tDBOutput_1 > 0 && rowsToCommitCount_tDBOutput_1 != 0) {

					}
					conn_tDBOutput_1.commit();
					if (commitCounter_tDBOutput_1 > 0 && rowsToCommitCount_tDBOutput_1 != 0) {

						rowsToCommitCount_tDBOutput_1 = 0;
					}
					commitCounter_tDBOutput_1 = 0;

					conn_tDBOutput_1.close();

					resourceMap.put("finish_tDBOutput_1", true);

					nb_line_deleted_tDBOutput_1 = nb_line_deleted_tDBOutput_1 + deletedCount_tDBOutput_1;
					nb_line_update_tDBOutput_1 = nb_line_update_tDBOutput_1 + updatedCount_tDBOutput_1;
					nb_line_inserted_tDBOutput_1 = nb_line_inserted_tDBOutput_1 + insertedCount_tDBOutput_1;
					nb_line_rejected_tDBOutput_1 = nb_line_rejected_tDBOutput_1 + rejectedCount_tDBOutput_1;

					globalMap.put("tDBOutput_1_NB_LINE", nb_line_tDBOutput_1);
					globalMap.put("tDBOutput_1_NB_LINE_UPDATED", nb_line_update_tDBOutput_1);
					globalMap.put("tDBOutput_1_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_1);
					globalMap.put("tDBOutput_1_NB_LINE_DELETED", nb_line_deleted_tDBOutput_1);
					globalMap.put("tDBOutput_1_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_1);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "insertclusters");
					}

					ok_Hash.put("tDBOutput_1", true);
					end_Hash.put("tDBOutput_1", System.currentTimeMillis());

					/**
					 * [tDBOutput_1 end ] stop
					 */

					if (execStat) {
						runStat.updateStatOnConnection("iterate2", 2, "exec" + NB_ITERATE_tFileInputExcel_1);
					}

					NB_ITERATE_tFileInputExcel_13++;

					if (execStat) {
						runStat.updateStatOnConnection("row20", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("insertprocess", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row11", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row2", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate3", 1, "exec" + NB_ITERATE_tFileInputExcel_13);
						// Thread.sleep(1000);
					}

					/**
					 * [tDBOutput_10 begin ] start
					 */

					ok_Hash.put("tDBOutput_10", false);
					start_Hash.put("tDBOutput_10", System.currentTimeMillis());

					currentComponent = "tDBOutput_10";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "insertprocess");
					}

					int tos_count_tDBOutput_10 = 0;

					int nb_line_tDBOutput_10 = 0;
					int nb_line_update_tDBOutput_10 = 0;
					int nb_line_inserted_tDBOutput_10 = 0;
					int nb_line_deleted_tDBOutput_10 = 0;
					int nb_line_rejected_tDBOutput_10 = 0;

					int deletedCount_tDBOutput_10 = 0;
					int updatedCount_tDBOutput_10 = 0;
					int insertedCount_tDBOutput_10 = 0;
					int rowsToCommitCount_tDBOutput_10 = 0;
					int rejectedCount_tDBOutput_10 = 0;

					String tableName_tDBOutput_10 = "process";
					boolean whetherReject_tDBOutput_10 = false;

					java.util.Calendar calendar_tDBOutput_10 = java.util.Calendar.getInstance();
					calendar_tDBOutput_10.set(1, 0, 1, 0, 0, 0);
					long year1_tDBOutput_10 = calendar_tDBOutput_10.getTime().getTime();
					calendar_tDBOutput_10.set(10000, 0, 1, 0, 0, 0);
					long year10000_tDBOutput_10 = calendar_tDBOutput_10.getTime().getTime();
					long date_tDBOutput_10;

					java.sql.Connection conn_tDBOutput_10 = null;

					String properties_tDBOutput_10 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
					if (properties_tDBOutput_10 == null || properties_tDBOutput_10.trim().length() == 0) {
						properties_tDBOutput_10 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
					} else {
						if (!properties_tDBOutput_10.contains("rewriteBatchedStatements=")) {
							properties_tDBOutput_10 += "&rewriteBatchedStatements=true";
						}

						if (!properties_tDBOutput_10.contains("allowLoadLocalInfile=")) {
							properties_tDBOutput_10 += "&allowLoadLocalInfile=true";
						}
					}

					String url_tDBOutput_10 = "jdbc:mariadb://" + "127.0.0.1" + ":" + "3306" + "/" + "ooredoo" + "?"
							+ properties_tDBOutput_10;

					String driverClass_tDBOutput_10 = "org.mariadb.jdbc.Driver";

					String dbUser_tDBOutput_10 = "root";

					final String decryptedPassword_tDBOutput_10 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:aMI1QRksEra2ZG2jePtEMDo2HfS7QIuFF883vQ==");

					String dbPwd_tDBOutput_10 = decryptedPassword_tDBOutput_10;
					java.lang.Class.forName(driverClass_tDBOutput_10);

					conn_tDBOutput_10 = java.sql.DriverManager.getConnection(url_tDBOutput_10, dbUser_tDBOutput_10,
							dbPwd_tDBOutput_10);

					resourceMap.put("conn_tDBOutput_10", conn_tDBOutput_10);
					conn_tDBOutput_10.setAutoCommit(false);
					int commitEvery_tDBOutput_10 = 10000;
					int commitCounter_tDBOutput_10 = 0;

					int count_tDBOutput_10 = 0;

					String insert_tDBOutput_10 = "INSERT IGNORE INTO `" + "process"
							+ "` (`id`,`Ref`,`Etat`,`Ref_composant`,`Process`,`Cricite`,`Message_alarme`,`Intervalle_de_polling`,`Objet`,`Nom_Template`,`Monitored_By`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

					java.sql.PreparedStatement pstmt_tDBOutput_10 = conn_tDBOutput_10
							.prepareStatement(insert_tDBOutput_10);
					resourceMap.put("pstmt_tDBOutput_10", pstmt_tDBOutput_10);

					/**
					 * [tDBOutput_10 begin ] stop
					 */

					/**
					 * [tMap_3 begin ] start
					 */

					ok_Hash.put("tMap_3", false);
					start_Hash.put("tMap_3", System.currentTimeMillis());

					currentComponent = "tMap_3";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row20");
					}

					int tos_count_tMap_3 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
					class Var__tMap_3__Struct {
					}
					Var__tMap_3__Struct Var__tMap_3 = new Var__tMap_3__Struct();
// ###############################

// ###############################
// # Outputs initialization
					insertprocessStruct insertprocess_tmp = new insertprocessStruct();
// ###############################

					/**
					 * [tMap_3 begin ] stop
					 */

					/**
					 * [tJavaRow_3 begin ] start
					 */

					ok_Hash.put("tJavaRow_3", false);
					start_Hash.put("tJavaRow_3", System.currentTimeMillis());

					currentComponent = "tJavaRow_3";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row11");
					}

					int tos_count_tJavaRow_3 = 0;

					int nb_line_tJavaRow_3 = 0;

					/**
					 * [tJavaRow_3 begin ] stop
					 */

					/**
					 * [tFilterRow_2 begin ] start
					 */

					ok_Hash.put("tFilterRow_2", false);
					start_Hash.put("tFilterRow_2", System.currentTimeMillis());

					currentComponent = "tFilterRow_2";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
					}

					int tos_count_tFilterRow_2 = 0;

					int nb_line_tFilterRow_2 = 0;
					int nb_line_ok_tFilterRow_2 = 0;
					int nb_line_reject_tFilterRow_2 = 0;

					class Operator_tFilterRow_2 {
						private String sErrorMsg = "";
						private boolean bMatchFlag = true;
						private String sUnionFlag = "&&";

						public Operator_tFilterRow_2(String unionFlag) {
							sUnionFlag = unionFlag;
							bMatchFlag = "||".equals(unionFlag) ? false : true;
						}

						public String getErrorMsg() {
							if (sErrorMsg != null && sErrorMsg.length() > 1)
								return sErrorMsg.substring(1);
							else
								return null;
						}

						public boolean getMatchFlag() {
							return bMatchFlag;
						}

						public void matches(boolean partMatched, String reason) {
							// no need to care about the next judgement
							if ("||".equals(sUnionFlag) && bMatchFlag) {
								return;
							}

							if (!partMatched) {
								sErrorMsg += "|" + reason;
							}

							if ("||".equals(sUnionFlag))
								bMatchFlag = bMatchFlag || partMatched;
							else
								bMatchFlag = bMatchFlag && partMatched;
						}
					}

					/**
					 * [tFilterRow_2 begin ] stop
					 */

					/**
					 * [tFileInputExcel_13 begin ] start
					 */

					ok_Hash.put("tFileInputExcel_13", false);
					start_Hash.put("tFileInputExcel_13", System.currentTimeMillis());

					currentComponent = "tFileInputExcel_13";

					int tos_count_tFileInputExcel_13 = 0;

					final String decryptedPassword_tFileInputExcel_13 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:iwemjqsOqYGuCuvO26xQ1Az1KA+sQJgVbPbkkA==");
					String password_tFileInputExcel_13 = decryptedPassword_tFileInputExcel_13;
					if (password_tFileInputExcel_13.isEmpty()) {
						password_tFileInputExcel_13 = null;
					}
					class RegexUtil_tFileInputExcel_13 {

						public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
								org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, String oneSheetName,
								boolean useRegex) {

							java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();

							if (useRegex) {// this part process the regex issue

								java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
								for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
									String sheetName = sheet.getSheetName();
									java.util.regex.Matcher matcher = pattern.matcher(sheetName);
									if (matcher.matches()) {
										if (sheet != null) {
											list.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet);
										}
									}
								}

							} else {
								org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
										.getSheet(oneSheetName);
								if (sheet != null) {
									list.add(sheet);
								}

							}

							return list;
						}

						public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
								org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, int index, boolean useRegex) {
							java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
							org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
									.getSheetAt(index);
							if (sheet != null) {
								list.add(sheet);
							}
							return list;
						}

					}
					RegexUtil_tFileInputExcel_13 regexUtil_tFileInputExcel_13 = new RegexUtil_tFileInputExcel_13();

					Object source_tFileInputExcel_13 = ((String) globalMap.get("tFileList_1_CURRENT_FILEPATH"));
					org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_13 = null;

					if (source_tFileInputExcel_13 instanceof String) {
						workbook_tFileInputExcel_13 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
								.create(new java.io.File((String) source_tFileInputExcel_13),
										password_tFileInputExcel_13, true);
					} else if (source_tFileInputExcel_13 instanceof java.io.InputStream) {
						workbook_tFileInputExcel_13 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
								.create((java.io.InputStream) source_tFileInputExcel_13, password_tFileInputExcel_13);
					} else {
						workbook_tFileInputExcel_13 = null;
						throw new java.lang.Exception(
								"The data source should be specified as Inputstream or File Path!");
					}
					try {

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_13 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						sheetList_tFileInputExcel_13.addAll(
								regexUtil_tFileInputExcel_13.getSheets(workbook_tFileInputExcel_13, "Process ", false));
						if (sheetList_tFileInputExcel_13.size() <= 0) {
							throw new RuntimeException("Special sheets not exist!");
						}

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_13 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_13 : sheetList_tFileInputExcel_13) {
							if (sheet_FilterNull_tFileInputExcel_13 != null
									&& sheetList_FilterNull_tFileInputExcel_13.iterator() != null
									&& sheet_FilterNull_tFileInputExcel_13.iterator().hasNext()) {
								sheetList_FilterNull_tFileInputExcel_13.add(sheet_FilterNull_tFileInputExcel_13);
							}
						}
						sheetList_tFileInputExcel_13 = sheetList_FilterNull_tFileInputExcel_13;
						if (sheetList_tFileInputExcel_13.size() > 0) {
							int nb_line_tFileInputExcel_13 = 0;

							int begin_line_tFileInputExcel_13 = 1;

							int footer_input_tFileInputExcel_13 = 0;

							int end_line_tFileInputExcel_13 = 0;
							for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_13 : sheetList_tFileInputExcel_13) {
								end_line_tFileInputExcel_13 += (sheet_tFileInputExcel_13.getLastRowNum() + 1);
							}
							end_line_tFileInputExcel_13 -= footer_input_tFileInputExcel_13;
							int limit_tFileInputExcel_13 = -1;
							int start_column_tFileInputExcel_13 = 1 - 1;
							int end_column_tFileInputExcel_13 = -1;

							org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_13 = null;
							org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_13 = sheetList_tFileInputExcel_13
									.get(0);
							int rowCount_tFileInputExcel_13 = 0;
							int sheetIndex_tFileInputExcel_13 = 0;
							int currentRows_tFileInputExcel_13 = (sheetList_tFileInputExcel_13.get(0).getLastRowNum()
									+ 1);

							// for the number format
							java.text.DecimalFormat df_tFileInputExcel_13 = new java.text.DecimalFormat(
									"#.####################################");
							char decimalChar_tFileInputExcel_13 = df_tFileInputExcel_13.getDecimalFormatSymbols()
									.getDecimalSeparator();

							for (int i_tFileInputExcel_13 = begin_line_tFileInputExcel_13; i_tFileInputExcel_13 < end_line_tFileInputExcel_13; i_tFileInputExcel_13++) {

								int emptyColumnCount_tFileInputExcel_13 = 0;

								if (limit_tFileInputExcel_13 != -1
										&& nb_line_tFileInputExcel_13 >= limit_tFileInputExcel_13) {
									break;
								}

								while (i_tFileInputExcel_13 >= rowCount_tFileInputExcel_13
										+ currentRows_tFileInputExcel_13) {
									rowCount_tFileInputExcel_13 += currentRows_tFileInputExcel_13;
									sheet_tFileInputExcel_13 = sheetList_tFileInputExcel_13
											.get(++sheetIndex_tFileInputExcel_13);
									currentRows_tFileInputExcel_13 = (sheet_tFileInputExcel_13.getLastRowNum() + 1);
								}
								globalMap.put("tFileInputExcel_13_CURRENT_SHEET",
										sheet_tFileInputExcel_13.getSheetName());
								if (rowCount_tFileInputExcel_13 <= i_tFileInputExcel_13) {
									row_tFileInputExcel_13 = sheet_tFileInputExcel_13
											.getRow(i_tFileInputExcel_13 - rowCount_tFileInputExcel_13);
								}
								row2 = null;
								int tempRowLength_tFileInputExcel_13 = 11;

								int columnIndex_tFileInputExcel_13 = 0;

								String[] temp_row_tFileInputExcel_13 = new String[tempRowLength_tFileInputExcel_13];
								int excel_end_column_tFileInputExcel_13;
								if (row_tFileInputExcel_13 == null) {
									excel_end_column_tFileInputExcel_13 = 0;
								} else {
									excel_end_column_tFileInputExcel_13 = row_tFileInputExcel_13.getLastCellNum();
								}
								int actual_end_column_tFileInputExcel_13;
								if (end_column_tFileInputExcel_13 == -1) {
									actual_end_column_tFileInputExcel_13 = excel_end_column_tFileInputExcel_13;
								} else {
									actual_end_column_tFileInputExcel_13 = end_column_tFileInputExcel_13 > excel_end_column_tFileInputExcel_13
											? excel_end_column_tFileInputExcel_13
											: end_column_tFileInputExcel_13;
								}
								org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_13 = null;
								for (int i = 0; i < tempRowLength_tFileInputExcel_13; i++) {
									if (i + start_column_tFileInputExcel_13 < actual_end_column_tFileInputExcel_13) {
										org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_13 = row_tFileInputExcel_13
												.getCell(i + start_column_tFileInputExcel_13);
										if (cell_tFileInputExcel_13 != null) {
											switch (cell_tFileInputExcel_13.getCellType()) {
											case STRING:
												temp_row_tFileInputExcel_13[i] = cell_tFileInputExcel_13
														.getRichStringCellValue().getString();
												break;
											case NUMERIC:
												if (org.apache.poi.ss.usermodel.DateUtil
														.isCellDateFormatted(cell_tFileInputExcel_13)) {
													temp_row_tFileInputExcel_13[i] = cell_tFileInputExcel_13
															.getDateCellValue().toString();
												} else {
													temp_row_tFileInputExcel_13[i] = df_tFileInputExcel_13
															.format(cell_tFileInputExcel_13.getNumericCellValue());
												}
												break;
											case BOOLEAN:
												temp_row_tFileInputExcel_13[i] = String
														.valueOf(cell_tFileInputExcel_13.getBooleanCellValue());
												break;
											case FORMULA:
												switch (cell_tFileInputExcel_13.getCachedFormulaResultType()) {
												case STRING:
													temp_row_tFileInputExcel_13[i] = cell_tFileInputExcel_13
															.getRichStringCellValue().getString();
													break;
												case NUMERIC:
													if (org.apache.poi.ss.usermodel.DateUtil
															.isCellDateFormatted(cell_tFileInputExcel_13)) {
														temp_row_tFileInputExcel_13[i] = cell_tFileInputExcel_13
																.getDateCellValue().toString();
													} else {
														ne_tFileInputExcel_13 = new org.apache.poi.ss.formula.eval.NumberEval(
																cell_tFileInputExcel_13.getNumericCellValue());
														temp_row_tFileInputExcel_13[i] = ne_tFileInputExcel_13
																.getStringValue();
													}
													break;
												case BOOLEAN:
													temp_row_tFileInputExcel_13[i] = String
															.valueOf(cell_tFileInputExcel_13.getBooleanCellValue());
													break;
												default:
													temp_row_tFileInputExcel_13[i] = "";
												}
												break;
											default:
												temp_row_tFileInputExcel_13[i] = "";
											}
										} else {
											temp_row_tFileInputExcel_13[i] = "";
										}

									} else {
										temp_row_tFileInputExcel_13[i] = "";
									}
								}
								boolean whetherReject_tFileInputExcel_13 = false;
								row2 = new row2Struct();
								int curColNum_tFileInputExcel_13 = -1;
								String curColName_tFileInputExcel_13 = "";
								try {
									columnIndex_tFileInputExcel_13 = 0;

									if (temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13].length() > 0) {
										curColNum_tFileInputExcel_13 = columnIndex_tFileInputExcel_13
												+ start_column_tFileInputExcel_13 + 1;
										curColName_tFileInputExcel_13 = "N__Ref";

										row2.N__Ref = temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13];
									} else {
										row2.N__Ref = null;
										emptyColumnCount_tFileInputExcel_13++;
									}
									columnIndex_tFileInputExcel_13 = 1;

									if (temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13].length() > 0) {
										curColNum_tFileInputExcel_13 = columnIndex_tFileInputExcel_13
												+ start_column_tFileInputExcel_13 + 1;
										curColName_tFileInputExcel_13 = "Ref";

										row2.Ref = temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13];
									} else {
										row2.Ref = null;
										emptyColumnCount_tFileInputExcel_13++;
									}
									columnIndex_tFileInputExcel_13 = 2;

									if (temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13].length() > 0) {
										curColNum_tFileInputExcel_13 = columnIndex_tFileInputExcel_13
												+ start_column_tFileInputExcel_13 + 1;
										curColName_tFileInputExcel_13 = "Etat";

										row2.Etat = temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13];
									} else {
										row2.Etat = null;
										emptyColumnCount_tFileInputExcel_13++;
									}
									columnIndex_tFileInputExcel_13 = 3;

									if (temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13].length() > 0) {
										curColNum_tFileInputExcel_13 = columnIndex_tFileInputExcel_13
												+ start_column_tFileInputExcel_13 + 1;
										curColName_tFileInputExcel_13 = "Ref__des_composants";

										row2.Ref__des_composants = temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13];
									} else {
										row2.Ref__des_composants = null;
										emptyColumnCount_tFileInputExcel_13++;
									}
									columnIndex_tFileInputExcel_13 = 4;

									if (temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13].length() > 0) {
										curColNum_tFileInputExcel_13 = columnIndex_tFileInputExcel_13
												+ start_column_tFileInputExcel_13 + 1;
										curColName_tFileInputExcel_13 = "Process";

										row2.Process = temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13];
									} else {
										row2.Process = null;
										emptyColumnCount_tFileInputExcel_13++;
									}
									columnIndex_tFileInputExcel_13 = 5;

									if (temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13].length() > 0) {
										curColNum_tFileInputExcel_13 = columnIndex_tFileInputExcel_13
												+ start_column_tFileInputExcel_13 + 1;
										curColName_tFileInputExcel_13 = "Criticite";

										row2.Criticite = temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13];
									} else {
										row2.Criticite = null;
										emptyColumnCount_tFileInputExcel_13++;
									}
									columnIndex_tFileInputExcel_13 = 6;

									if (temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13].length() > 0) {
										curColNum_tFileInputExcel_13 = columnIndex_tFileInputExcel_13
												+ start_column_tFileInputExcel_13 + 1;
										curColName_tFileInputExcel_13 = "Message_d_alarme";

										row2.Message_d_alarme = temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13];
									} else {
										row2.Message_d_alarme = null;
										emptyColumnCount_tFileInputExcel_13++;
									}
									columnIndex_tFileInputExcel_13 = 7;

									if (temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13].length() > 0) {
										curColNum_tFileInputExcel_13 = columnIndex_tFileInputExcel_13
												+ start_column_tFileInputExcel_13 + 1;
										curColName_tFileInputExcel_13 = "Intervalle_de_polling";

										row2.Intervalle_de_polling = temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13];
									} else {
										row2.Intervalle_de_polling = null;
										emptyColumnCount_tFileInputExcel_13++;
									}
									columnIndex_tFileInputExcel_13 = 8;

									if (temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13].length() > 0) {
										curColNum_tFileInputExcel_13 = columnIndex_tFileInputExcel_13
												+ start_column_tFileInputExcel_13 + 1;
										curColName_tFileInputExcel_13 = "Objet";

										row2.Objet = temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13];
									} else {
										row2.Objet = null;
										emptyColumnCount_tFileInputExcel_13++;
									}
									columnIndex_tFileInputExcel_13 = 9;

									if (temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13].length() > 0) {
										curColNum_tFileInputExcel_13 = columnIndex_tFileInputExcel_13
												+ start_column_tFileInputExcel_13 + 1;
										curColName_tFileInputExcel_13 = "Nom_Template";

										row2.Nom_Template = temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13];
									} else {
										row2.Nom_Template = null;
										emptyColumnCount_tFileInputExcel_13++;
									}
									columnIndex_tFileInputExcel_13 = 10;

									if (temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13].length() > 0) {
										curColNum_tFileInputExcel_13 = columnIndex_tFileInputExcel_13
												+ start_column_tFileInputExcel_13 + 1;
										curColName_tFileInputExcel_13 = "Monitored_By";

										row2.Monitored_By = temp_row_tFileInputExcel_13[columnIndex_tFileInputExcel_13];
									} else {
										row2.Monitored_By = null;
										emptyColumnCount_tFileInputExcel_13++;
									}

									nb_line_tFileInputExcel_13++;

								} catch (java.lang.Exception e) {
									globalMap.put("tFileInputExcel_13_ERROR_MESSAGE", e.getMessage());
									whetherReject_tFileInputExcel_13 = true;
									System.err.println(e.getMessage());
									row2 = null;
								}

								/**
								 * [tFileInputExcel_13 begin ] stop
								 */

								/**
								 * [tFileInputExcel_13 main ] start
								 */

								currentComponent = "tFileInputExcel_13";

								tos_count_tFileInputExcel_13++;

								/**
								 * [tFileInputExcel_13 main ] stop
								 */

								/**
								 * [tFileInputExcel_13 process_data_begin ] start
								 */

								currentComponent = "tFileInputExcel_13";

								/**
								 * [tFileInputExcel_13 process_data_begin ] stop
								 */
// Start of branch "row2"
								if (row2 != null) {

									/**
									 * [tFilterRow_2 main ] start
									 */

									currentComponent = "tFilterRow_2";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "row2"

										);
									}

									row11 = null;
									Operator_tFilterRow_2 ope_tFilterRow_2 = new Operator_tFilterRow_2("&&");
									ope_tFilterRow_2.matches((row2.Ref != null), "Ref!=null failed");

									if (ope_tFilterRow_2.getMatchFlag()) {
										if (row11 == null) {
											row11 = new row11Struct();
										}
										row11.N__Ref = row2.N__Ref;
										row11.Ref = row2.Ref;
										row11.Etat = row2.Etat;
										row11.Ref__des_composants = row2.Ref__des_composants;
										row11.Process = row2.Process;
										row11.Criticite = row2.Criticite;
										row11.Message_d_alarme = row2.Message_d_alarme;
										row11.Intervalle_de_polling = row2.Intervalle_de_polling;
										row11.Objet = row2.Objet;
										row11.Nom_Template = row2.Nom_Template;
										row11.Monitored_By = row2.Monitored_By;
										nb_line_ok_tFilterRow_2++;
									} else {
										nb_line_reject_tFilterRow_2++;
									}

									nb_line_tFilterRow_2++;

									tos_count_tFilterRow_2++;

									/**
									 * [tFilterRow_2 main ] stop
									 */

									/**
									 * [tFilterRow_2 process_data_begin ] start
									 */

									currentComponent = "tFilterRow_2";

									/**
									 * [tFilterRow_2 process_data_begin ] stop
									 */
// Start of branch "row11"
									if (row11 != null) {

										/**
										 * [tJavaRow_3 main ] start
										 */

										currentComponent = "tJavaRow_3";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row11"

											);
										}

										String fileName = (String) source_tFileInputExcel_13;

// Extraire la partie "POSANET" du nom du fichier Excel
										String[] parts = fileName.split("_");
										String fileWord = parts[6];

// Récupérer la valeur de "N__Ref" et la convertir en String
										String rowRef = row11.N__Ref;

// Générer l'ID "EDB_POSANET_X"
										String id = "EDB_" + fileWord + "_Process_" + rowRef;
//Code généré selon les schémas d'entrée et de sortie
										row20.N__Ref = id;
										row20.Ref = row11.Ref;
										row20.Etat = row11.Etat;
										row20.Ref__des_composants = row11.Ref__des_composants;
										row20.Process = row11.Process;
										row20.Criticite = row11.Criticite;
										row20.Message_d_alarme = row11.Message_d_alarme;
										row20.Intervalle_de_polling = row11.Intervalle_de_polling;
										row20.Objet = row11.Objet;
										row20.Nom_Template = row11.Nom_Template;
										row20.Monitored_By = row11.Monitored_By;

										nb_line_tJavaRow_3++;

										tos_count_tJavaRow_3++;

										/**
										 * [tJavaRow_3 main ] stop
										 */

										/**
										 * [tJavaRow_3 process_data_begin ] start
										 */

										currentComponent = "tJavaRow_3";

										/**
										 * [tJavaRow_3 process_data_begin ] stop
										 */

										/**
										 * [tMap_3 main ] start
										 */

										currentComponent = "tMap_3";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row20"

											);
										}

										boolean hasCasePrimitiveKeyWithNull_tMap_3 = false;

										// ###############################
										// # Input tables (lookups)
										boolean rejectedInnerJoin_tMap_3 = false;
										boolean mainRowRejected_tMap_3 = false;

										// ###############################
										{ // start of Var scope

											// ###############################
											// # Vars tables

											Var__tMap_3__Struct Var = Var__tMap_3;// ###############################
											// ###############################
											// # Output tables

											insertprocess = null;

// # Output table : 'insertprocess'
											insertprocess_tmp.id = row20.N__Ref;
											insertprocess_tmp.Ref = row20.Ref;
											insertprocess_tmp.Etat = row20.Etat;
											insertprocess_tmp.Ref_composant = row20.Ref__des_composants;
											insertprocess_tmp.Process = row20.Process;
											insertprocess_tmp.Cricite = row20.Criticite;
											insertprocess_tmp.Message_alarme = row20.Message_d_alarme;
											insertprocess_tmp.Intervalle_de_polling = row20.Intervalle_de_polling;
											insertprocess_tmp.Objet = row20.Objet;
											insertprocess_tmp.Nom_Template = row20.Nom_Template;
											insertprocess_tmp.Monitored_By = row20.Monitored_By;
											insertprocess = insertprocess_tmp;
// ###############################

										} // end of Var scope

										rejectedInnerJoin_tMap_3 = false;

										tos_count_tMap_3++;

										/**
										 * [tMap_3 main ] stop
										 */

										/**
										 * [tMap_3 process_data_begin ] start
										 */

										currentComponent = "tMap_3";

										/**
										 * [tMap_3 process_data_begin ] stop
										 */
// Start of branch "insertprocess"
										if (insertprocess != null) {

											/**
											 * [tDBOutput_10 main ] start
											 */

											currentComponent = "tDBOutput_10";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "insertprocess"

												);
											}

											whetherReject_tDBOutput_10 = false;
											if (insertprocess.id == null) {
												pstmt_tDBOutput_10.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_10.setString(1, insertprocess.id);
											}

											if (insertprocess.Ref == null) {
												pstmt_tDBOutput_10.setNull(2, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_10.setString(2, insertprocess.Ref);
											}

											if (insertprocess.Etat == null) {
												pstmt_tDBOutput_10.setNull(3, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_10.setString(3, insertprocess.Etat);
											}

											if (insertprocess.Ref_composant == null) {
												pstmt_tDBOutput_10.setNull(4, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_10.setString(4, insertprocess.Ref_composant);
											}

											if (insertprocess.Process == null) {
												pstmt_tDBOutput_10.setNull(5, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_10.setString(5, insertprocess.Process);
											}

											if (insertprocess.Cricite == null) {
												pstmt_tDBOutput_10.setNull(6, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_10.setString(6, insertprocess.Cricite);
											}

											if (insertprocess.Message_alarme == null) {
												pstmt_tDBOutput_10.setNull(7, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_10.setString(7, insertprocess.Message_alarme);
											}

											if (insertprocess.Intervalle_de_polling == null) {
												pstmt_tDBOutput_10.setNull(8, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_10.setString(8, insertprocess.Intervalle_de_polling);
											}

											if (insertprocess.Objet == null) {
												pstmt_tDBOutput_10.setNull(9, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_10.setString(9, insertprocess.Objet);
											}

											if (insertprocess.Nom_Template == null) {
												pstmt_tDBOutput_10.setNull(10, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_10.setString(10, insertprocess.Nom_Template);
											}

											if (insertprocess.Monitored_By == null) {
												pstmt_tDBOutput_10.setNull(11, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_10.setString(11, insertprocess.Monitored_By);
											}

											try {
												nb_line_tDBOutput_10++;
												int processedCount_tDBOutput_10 = pstmt_tDBOutput_10.executeUpdate();
												insertedCount_tDBOutput_10 += processedCount_tDBOutput_10;
												rowsToCommitCount_tDBOutput_10 += processedCount_tDBOutput_10;
											} catch (java.lang.Exception e) {
												globalMap.put("tDBOutput_10_ERROR_MESSAGE", e.getMessage());
												whetherReject_tDBOutput_10 = true;
												System.err.print(e.getMessage());
											}
											commitCounter_tDBOutput_10++;

											if (commitEvery_tDBOutput_10 <= commitCounter_tDBOutput_10) {

												if (rowsToCommitCount_tDBOutput_10 != 0) {
												}
												conn_tDBOutput_10.commit();
												if (rowsToCommitCount_tDBOutput_10 != 0) {
													rowsToCommitCount_tDBOutput_10 = 0;
												}
												commitCounter_tDBOutput_10 = 0;

											}

											tos_count_tDBOutput_10++;

											/**
											 * [tDBOutput_10 main ] stop
											 */

											/**
											 * [tDBOutput_10 process_data_begin ] start
											 */

											currentComponent = "tDBOutput_10";

											/**
											 * [tDBOutput_10 process_data_begin ] stop
											 */

											/**
											 * [tDBOutput_10 process_data_end ] start
											 */

											currentComponent = "tDBOutput_10";

											/**
											 * [tDBOutput_10 process_data_end ] stop
											 */

										} // End of branch "insertprocess"

										/**
										 * [tMap_3 process_data_end ] start
										 */

										currentComponent = "tMap_3";

										/**
										 * [tMap_3 process_data_end ] stop
										 */

										/**
										 * [tJavaRow_3 process_data_end ] start
										 */

										currentComponent = "tJavaRow_3";

										/**
										 * [tJavaRow_3 process_data_end ] stop
										 */

									} // End of branch "row11"

									/**
									 * [tFilterRow_2 process_data_end ] start
									 */

									currentComponent = "tFilterRow_2";

									/**
									 * [tFilterRow_2 process_data_end ] stop
									 */

								} // End of branch "row2"

								/**
								 * [tFileInputExcel_13 process_data_end ] start
								 */

								currentComponent = "tFileInputExcel_13";

								/**
								 * [tFileInputExcel_13 process_data_end ] stop
								 */

								/**
								 * [tFileInputExcel_13 end ] start
								 */

								currentComponent = "tFileInputExcel_13";

							}

							globalMap.put("tFileInputExcel_13_NB_LINE", nb_line_tFileInputExcel_13);

						}

					} finally {

						if (!(source_tFileInputExcel_13 instanceof java.io.InputStream)) {
							workbook_tFileInputExcel_13.getPackage().revert();
						}

					}

					ok_Hash.put("tFileInputExcel_13", true);
					end_Hash.put("tFileInputExcel_13", System.currentTimeMillis());

					/**
					 * [tFileInputExcel_13 end ] stop
					 */

					/**
					 * [tFilterRow_2 end ] start
					 */

					currentComponent = "tFilterRow_2";

					globalMap.put("tFilterRow_2_NB_LINE", nb_line_tFilterRow_2);
					globalMap.put("tFilterRow_2_NB_LINE_OK", nb_line_ok_tFilterRow_2);
					globalMap.put("tFilterRow_2_NB_LINE_REJECT", nb_line_reject_tFilterRow_2);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
					}

					ok_Hash.put("tFilterRow_2", true);
					end_Hash.put("tFilterRow_2", System.currentTimeMillis());

					/**
					 * [tFilterRow_2 end ] stop
					 */

					/**
					 * [tJavaRow_3 end ] start
					 */

					currentComponent = "tJavaRow_3";

					globalMap.put("tJavaRow_3_NB_LINE", nb_line_tJavaRow_3);
					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row11");
					}

					ok_Hash.put("tJavaRow_3", true);
					end_Hash.put("tJavaRow_3", System.currentTimeMillis());

					/**
					 * [tJavaRow_3 end ] stop
					 */

					/**
					 * [tMap_3 end ] start
					 */

					currentComponent = "tMap_3";

// ###############################
// # Lookup hashes releasing
// ###############################      

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row20");
					}

					ok_Hash.put("tMap_3", true);
					end_Hash.put("tMap_3", System.currentTimeMillis());

					/**
					 * [tMap_3 end ] stop
					 */

					/**
					 * [tDBOutput_10 end ] start
					 */

					currentComponent = "tDBOutput_10";

					if (pstmt_tDBOutput_10 != null) {

						pstmt_tDBOutput_10.close();
						resourceMap.remove("pstmt_tDBOutput_10");

					}
					resourceMap.put("statementClosed_tDBOutput_10", true);
					if (commitCounter_tDBOutput_10 > 0 && rowsToCommitCount_tDBOutput_10 != 0) {

					}
					conn_tDBOutput_10.commit();
					if (commitCounter_tDBOutput_10 > 0 && rowsToCommitCount_tDBOutput_10 != 0) {

						rowsToCommitCount_tDBOutput_10 = 0;
					}
					commitCounter_tDBOutput_10 = 0;

					conn_tDBOutput_10.close();

					resourceMap.put("finish_tDBOutput_10", true);

					nb_line_deleted_tDBOutput_10 = nb_line_deleted_tDBOutput_10 + deletedCount_tDBOutput_10;
					nb_line_update_tDBOutput_10 = nb_line_update_tDBOutput_10 + updatedCount_tDBOutput_10;
					nb_line_inserted_tDBOutput_10 = nb_line_inserted_tDBOutput_10 + insertedCount_tDBOutput_10;
					nb_line_rejected_tDBOutput_10 = nb_line_rejected_tDBOutput_10 + rejectedCount_tDBOutput_10;

					globalMap.put("tDBOutput_10_NB_LINE", nb_line_tDBOutput_10);
					globalMap.put("tDBOutput_10_NB_LINE_UPDATED", nb_line_update_tDBOutput_10);
					globalMap.put("tDBOutput_10_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_10);
					globalMap.put("tDBOutput_10_NB_LINE_DELETED", nb_line_deleted_tDBOutput_10);
					globalMap.put("tDBOutput_10_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_10);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "insertprocess");
					}

					ok_Hash.put("tDBOutput_10", true);
					end_Hash.put("tDBOutput_10", System.currentTimeMillis());

					/**
					 * [tDBOutput_10 end ] stop
					 */

					if (execStat) {
						runStat.updateStatOnConnection("iterate3", 2, "exec" + NB_ITERATE_tFileInputExcel_13);
					}

					NB_ITERATE_tFileInputExcel_18++;

					if (execStat) {
						runStat.updateStatOnConnection("row21", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("insertrequetes", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row12", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row3", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate4", 1, "exec" + NB_ITERATE_tFileInputExcel_18);
						// Thread.sleep(1000);
					}

					/**
					 * [tDBOutput_15 begin ] start
					 */

					ok_Hash.put("tDBOutput_15", false);
					start_Hash.put("tDBOutput_15", System.currentTimeMillis());

					currentComponent = "tDBOutput_15";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "insertrequetes");
					}

					int tos_count_tDBOutput_15 = 0;

					int nb_line_tDBOutput_15 = 0;
					int nb_line_update_tDBOutput_15 = 0;
					int nb_line_inserted_tDBOutput_15 = 0;
					int nb_line_deleted_tDBOutput_15 = 0;
					int nb_line_rejected_tDBOutput_15 = 0;

					int deletedCount_tDBOutput_15 = 0;
					int updatedCount_tDBOutput_15 = 0;
					int insertedCount_tDBOutput_15 = 0;
					int rowsToCommitCount_tDBOutput_15 = 0;
					int rejectedCount_tDBOutput_15 = 0;

					String tableName_tDBOutput_15 = "requetessql";
					boolean whetherReject_tDBOutput_15 = false;

					java.util.Calendar calendar_tDBOutput_15 = java.util.Calendar.getInstance();
					calendar_tDBOutput_15.set(1, 0, 1, 0, 0, 0);
					long year1_tDBOutput_15 = calendar_tDBOutput_15.getTime().getTime();
					calendar_tDBOutput_15.set(10000, 0, 1, 0, 0, 0);
					long year10000_tDBOutput_15 = calendar_tDBOutput_15.getTime().getTime();
					long date_tDBOutput_15;

					java.sql.Connection conn_tDBOutput_15 = null;

					String properties_tDBOutput_15 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
					if (properties_tDBOutput_15 == null || properties_tDBOutput_15.trim().length() == 0) {
						properties_tDBOutput_15 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
					} else {
						if (!properties_tDBOutput_15.contains("rewriteBatchedStatements=")) {
							properties_tDBOutput_15 += "&rewriteBatchedStatements=true";
						}

						if (!properties_tDBOutput_15.contains("allowLoadLocalInfile=")) {
							properties_tDBOutput_15 += "&allowLoadLocalInfile=true";
						}
					}

					String url_tDBOutput_15 = "jdbc:mariadb://" + "127.0.0.1" + ":" + "3306" + "/" + "ooredoo" + "?"
							+ properties_tDBOutput_15;

					String driverClass_tDBOutput_15 = "org.mariadb.jdbc.Driver";

					String dbUser_tDBOutput_15 = "root";

					final String decryptedPassword_tDBOutput_15 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:NJWm10N5IaUdZUsgCvXYvXBnRT9JUJfxf4oGLw==");

					String dbPwd_tDBOutput_15 = decryptedPassword_tDBOutput_15;
					java.lang.Class.forName(driverClass_tDBOutput_15);

					conn_tDBOutput_15 = java.sql.DriverManager.getConnection(url_tDBOutput_15, dbUser_tDBOutput_15,
							dbPwd_tDBOutput_15);

					resourceMap.put("conn_tDBOutput_15", conn_tDBOutput_15);
					conn_tDBOutput_15.setAutoCommit(false);
					int commitEvery_tDBOutput_15 = 10000;
					int commitCounter_tDBOutput_15 = 0;

					int count_tDBOutput_15 = 0;

					String insert_tDBOutput_15 = "INSERT IGNORE INTO `" + "requetessql"
							+ "` (`id`,`Ref`,`Etat`,`Ref_composant`,`RG/SG_si_Cluster`,`Requete_SQL`,`UserName/DB_Name`,`Resultat_Attendu_de_la_requete`,`Perform_action`,`Criticite`,`Message_alarme`,`Instructions`,`Intervalle_de_polling`,`Ref_Service`,`Objet`,`Monitored_By`,`Nom_Template`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

					java.sql.PreparedStatement pstmt_tDBOutput_15 = conn_tDBOutput_15
							.prepareStatement(insert_tDBOutput_15);
					resourceMap.put("pstmt_tDBOutput_15", pstmt_tDBOutput_15);

					/**
					 * [tDBOutput_15 begin ] stop
					 */

					/**
					 * [tMap_4 begin ] start
					 */

					ok_Hash.put("tMap_4", false);
					start_Hash.put("tMap_4", System.currentTimeMillis());

					currentComponent = "tMap_4";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row21");
					}

					int tos_count_tMap_4 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
					class Var__tMap_4__Struct {
					}
					Var__tMap_4__Struct Var__tMap_4 = new Var__tMap_4__Struct();
// ###############################

// ###############################
// # Outputs initialization
					insertrequetesStruct insertrequetes_tmp = new insertrequetesStruct();
// ###############################

					/**
					 * [tMap_4 begin ] stop
					 */

					/**
					 * [tJavaRow_4 begin ] start
					 */

					ok_Hash.put("tJavaRow_4", false);
					start_Hash.put("tJavaRow_4", System.currentTimeMillis());

					currentComponent = "tJavaRow_4";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row12");
					}

					int tos_count_tJavaRow_4 = 0;

					int nb_line_tJavaRow_4 = 0;

					/**
					 * [tJavaRow_4 begin ] stop
					 */

					/**
					 * [tFilterRow_3 begin ] start
					 */

					ok_Hash.put("tFilterRow_3", false);
					start_Hash.put("tFilterRow_3", System.currentTimeMillis());

					currentComponent = "tFilterRow_3";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
					}

					int tos_count_tFilterRow_3 = 0;

					int nb_line_tFilterRow_3 = 0;
					int nb_line_ok_tFilterRow_3 = 0;
					int nb_line_reject_tFilterRow_3 = 0;

					class Operator_tFilterRow_3 {
						private String sErrorMsg = "";
						private boolean bMatchFlag = true;
						private String sUnionFlag = "&&";

						public Operator_tFilterRow_3(String unionFlag) {
							sUnionFlag = unionFlag;
							bMatchFlag = "||".equals(unionFlag) ? false : true;
						}

						public String getErrorMsg() {
							if (sErrorMsg != null && sErrorMsg.length() > 1)
								return sErrorMsg.substring(1);
							else
								return null;
						}

						public boolean getMatchFlag() {
							return bMatchFlag;
						}

						public void matches(boolean partMatched, String reason) {
							// no need to care about the next judgement
							if ("||".equals(sUnionFlag) && bMatchFlag) {
								return;
							}

							if (!partMatched) {
								sErrorMsg += "|" + reason;
							}

							if ("||".equals(sUnionFlag))
								bMatchFlag = bMatchFlag || partMatched;
							else
								bMatchFlag = bMatchFlag && partMatched;
						}
					}

					/**
					 * [tFilterRow_3 begin ] stop
					 */

					/**
					 * [tFileInputExcel_18 begin ] start
					 */

					ok_Hash.put("tFileInputExcel_18", false);
					start_Hash.put("tFileInputExcel_18", System.currentTimeMillis());

					currentComponent = "tFileInputExcel_18";

					int tos_count_tFileInputExcel_18 = 0;

					final String decryptedPassword_tFileInputExcel_18 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:FdLUIkwGVLS9+nVjMJ9XWNDM8Q2HLV+fkZCjfQ==");
					String password_tFileInputExcel_18 = decryptedPassword_tFileInputExcel_18;
					if (password_tFileInputExcel_18.isEmpty()) {
						password_tFileInputExcel_18 = null;
					}
					class RegexUtil_tFileInputExcel_18 {

						public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
								org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, String oneSheetName,
								boolean useRegex) {

							java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();

							if (useRegex) {// this part process the regex issue

								java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
								for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
									String sheetName = sheet.getSheetName();
									java.util.regex.Matcher matcher = pattern.matcher(sheetName);
									if (matcher.matches()) {
										if (sheet != null) {
											list.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet);
										}
									}
								}

							} else {
								org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
										.getSheet(oneSheetName);
								if (sheet != null) {
									list.add(sheet);
								}

							}

							return list;
						}

						public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
								org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, int index, boolean useRegex) {
							java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
							org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
									.getSheetAt(index);
							if (sheet != null) {
								list.add(sheet);
							}
							return list;
						}

					}
					RegexUtil_tFileInputExcel_18 regexUtil_tFileInputExcel_18 = new RegexUtil_tFileInputExcel_18();

					Object source_tFileInputExcel_18 = ((String) globalMap.get("tFileList_1_CURRENT_FILEPATH"));
					org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_18 = null;

					if (source_tFileInputExcel_18 instanceof String) {
						workbook_tFileInputExcel_18 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
								.create(new java.io.File((String) source_tFileInputExcel_18),
										password_tFileInputExcel_18, true);
					} else if (source_tFileInputExcel_18 instanceof java.io.InputStream) {
						workbook_tFileInputExcel_18 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
								.create((java.io.InputStream) source_tFileInputExcel_18, password_tFileInputExcel_18);
					} else {
						workbook_tFileInputExcel_18 = null;
						throw new java.lang.Exception(
								"The data source should be specified as Inputstream or File Path!");
					}
					try {

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_18 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						sheetList_tFileInputExcel_18.addAll(regexUtil_tFileInputExcel_18
								.getSheets(workbook_tFileInputExcel_18, "RequêtesSQL", false));
						if (sheetList_tFileInputExcel_18.size() <= 0) {
							throw new RuntimeException("Special sheets not exist!");
						}

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_18 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_18 : sheetList_tFileInputExcel_18) {
							if (sheet_FilterNull_tFileInputExcel_18 != null
									&& sheetList_FilterNull_tFileInputExcel_18.iterator() != null
									&& sheet_FilterNull_tFileInputExcel_18.iterator().hasNext()) {
								sheetList_FilterNull_tFileInputExcel_18.add(sheet_FilterNull_tFileInputExcel_18);
							}
						}
						sheetList_tFileInputExcel_18 = sheetList_FilterNull_tFileInputExcel_18;
						if (sheetList_tFileInputExcel_18.size() > 0) {
							int nb_line_tFileInputExcel_18 = 0;

							int begin_line_tFileInputExcel_18 = 1;

							int footer_input_tFileInputExcel_18 = 0;

							int end_line_tFileInputExcel_18 = 0;
							for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_18 : sheetList_tFileInputExcel_18) {
								end_line_tFileInputExcel_18 += (sheet_tFileInputExcel_18.getLastRowNum() + 1);
							}
							end_line_tFileInputExcel_18 -= footer_input_tFileInputExcel_18;
							int limit_tFileInputExcel_18 = -1;
							int start_column_tFileInputExcel_18 = 1 - 1;
							int end_column_tFileInputExcel_18 = -1;

							org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_18 = null;
							org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_18 = sheetList_tFileInputExcel_18
									.get(0);
							int rowCount_tFileInputExcel_18 = 0;
							int sheetIndex_tFileInputExcel_18 = 0;
							int currentRows_tFileInputExcel_18 = (sheetList_tFileInputExcel_18.get(0).getLastRowNum()
									+ 1);

							// for the number format
							java.text.DecimalFormat df_tFileInputExcel_18 = new java.text.DecimalFormat(
									"#.####################################");
							char decimalChar_tFileInputExcel_18 = df_tFileInputExcel_18.getDecimalFormatSymbols()
									.getDecimalSeparator();

							for (int i_tFileInputExcel_18 = begin_line_tFileInputExcel_18; i_tFileInputExcel_18 < end_line_tFileInputExcel_18; i_tFileInputExcel_18++) {

								int emptyColumnCount_tFileInputExcel_18 = 0;

								if (limit_tFileInputExcel_18 != -1
										&& nb_line_tFileInputExcel_18 >= limit_tFileInputExcel_18) {
									break;
								}

								while (i_tFileInputExcel_18 >= rowCount_tFileInputExcel_18
										+ currentRows_tFileInputExcel_18) {
									rowCount_tFileInputExcel_18 += currentRows_tFileInputExcel_18;
									sheet_tFileInputExcel_18 = sheetList_tFileInputExcel_18
											.get(++sheetIndex_tFileInputExcel_18);
									currentRows_tFileInputExcel_18 = (sheet_tFileInputExcel_18.getLastRowNum() + 1);
								}
								globalMap.put("tFileInputExcel_18_CURRENT_SHEET",
										sheet_tFileInputExcel_18.getSheetName());
								if (rowCount_tFileInputExcel_18 <= i_tFileInputExcel_18) {
									row_tFileInputExcel_18 = sheet_tFileInputExcel_18
											.getRow(i_tFileInputExcel_18 - rowCount_tFileInputExcel_18);
								}
								row3 = null;
								int tempRowLength_tFileInputExcel_18 = 17;

								int columnIndex_tFileInputExcel_18 = 0;

								String[] temp_row_tFileInputExcel_18 = new String[tempRowLength_tFileInputExcel_18];
								int excel_end_column_tFileInputExcel_18;
								if (row_tFileInputExcel_18 == null) {
									excel_end_column_tFileInputExcel_18 = 0;
								} else {
									excel_end_column_tFileInputExcel_18 = row_tFileInputExcel_18.getLastCellNum();
								}
								int actual_end_column_tFileInputExcel_18;
								if (end_column_tFileInputExcel_18 == -1) {
									actual_end_column_tFileInputExcel_18 = excel_end_column_tFileInputExcel_18;
								} else {
									actual_end_column_tFileInputExcel_18 = end_column_tFileInputExcel_18 > excel_end_column_tFileInputExcel_18
											? excel_end_column_tFileInputExcel_18
											: end_column_tFileInputExcel_18;
								}
								org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_18 = null;
								for (int i = 0; i < tempRowLength_tFileInputExcel_18; i++) {
									if (i + start_column_tFileInputExcel_18 < actual_end_column_tFileInputExcel_18) {
										org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_18 = row_tFileInputExcel_18
												.getCell(i + start_column_tFileInputExcel_18);
										if (cell_tFileInputExcel_18 != null) {
											switch (cell_tFileInputExcel_18.getCellType()) {
											case STRING:
												temp_row_tFileInputExcel_18[i] = cell_tFileInputExcel_18
														.getRichStringCellValue().getString();
												break;
											case NUMERIC:
												if (org.apache.poi.ss.usermodel.DateUtil
														.isCellDateFormatted(cell_tFileInputExcel_18)) {
													temp_row_tFileInputExcel_18[i] = cell_tFileInputExcel_18
															.getDateCellValue().toString();
												} else {
													temp_row_tFileInputExcel_18[i] = df_tFileInputExcel_18
															.format(cell_tFileInputExcel_18.getNumericCellValue());
												}
												break;
											case BOOLEAN:
												temp_row_tFileInputExcel_18[i] = String
														.valueOf(cell_tFileInputExcel_18.getBooleanCellValue());
												break;
											case FORMULA:
												switch (cell_tFileInputExcel_18.getCachedFormulaResultType()) {
												case STRING:
													temp_row_tFileInputExcel_18[i] = cell_tFileInputExcel_18
															.getRichStringCellValue().getString();
													break;
												case NUMERIC:
													if (org.apache.poi.ss.usermodel.DateUtil
															.isCellDateFormatted(cell_tFileInputExcel_18)) {
														temp_row_tFileInputExcel_18[i] = cell_tFileInputExcel_18
																.getDateCellValue().toString();
													} else {
														ne_tFileInputExcel_18 = new org.apache.poi.ss.formula.eval.NumberEval(
																cell_tFileInputExcel_18.getNumericCellValue());
														temp_row_tFileInputExcel_18[i] = ne_tFileInputExcel_18
																.getStringValue();
													}
													break;
												case BOOLEAN:
													temp_row_tFileInputExcel_18[i] = String
															.valueOf(cell_tFileInputExcel_18.getBooleanCellValue());
													break;
												default:
													temp_row_tFileInputExcel_18[i] = "";
												}
												break;
											default:
												temp_row_tFileInputExcel_18[i] = "";
											}
										} else {
											temp_row_tFileInputExcel_18[i] = "";
										}

									} else {
										temp_row_tFileInputExcel_18[i] = "";
									}
								}
								boolean whetherReject_tFileInputExcel_18 = false;
								row3 = new row3Struct();
								int curColNum_tFileInputExcel_18 = -1;
								String curColName_tFileInputExcel_18 = "";
								try {
									columnIndex_tFileInputExcel_18 = 0;

									if (temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18].length() > 0) {
										curColNum_tFileInputExcel_18 = columnIndex_tFileInputExcel_18
												+ start_column_tFileInputExcel_18 + 1;
										curColName_tFileInputExcel_18 = "N__Ref";

										row3.N__Ref = temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18];
									} else {
										row3.N__Ref = null;
										emptyColumnCount_tFileInputExcel_18++;
									}
									columnIndex_tFileInputExcel_18 = 1;

									if (temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18].length() > 0) {
										curColNum_tFileInputExcel_18 = columnIndex_tFileInputExcel_18
												+ start_column_tFileInputExcel_18 + 1;
										curColName_tFileInputExcel_18 = "Ref";

										row3.Ref = temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18];
									} else {
										row3.Ref = null;
										emptyColumnCount_tFileInputExcel_18++;
									}
									columnIndex_tFileInputExcel_18 = 2;

									if (temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18].length() > 0) {
										curColNum_tFileInputExcel_18 = columnIndex_tFileInputExcel_18
												+ start_column_tFileInputExcel_18 + 1;
										curColName_tFileInputExcel_18 = "Etat";

										row3.Etat = temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18];
									} else {
										row3.Etat = null;
										emptyColumnCount_tFileInputExcel_18++;
									}
									columnIndex_tFileInputExcel_18 = 3;

									if (temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18].length() > 0) {
										curColNum_tFileInputExcel_18 = columnIndex_tFileInputExcel_18
												+ start_column_tFileInputExcel_18 + 1;
										curColName_tFileInputExcel_18 = "Ref__des_composants";

										row3.Ref__des_composants = temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18];
									} else {
										row3.Ref__des_composants = null;
										emptyColumnCount_tFileInputExcel_18++;
									}
									columnIndex_tFileInputExcel_18 = 4;

									if (temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18].length() > 0) {
										curColNum_tFileInputExcel_18 = columnIndex_tFileInputExcel_18
												+ start_column_tFileInputExcel_18 + 1;
										curColName_tFileInputExcel_18 = "RG_SG_si_Cluster";

										row3.RG_SG_si_Cluster = temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18];
									} else {
										row3.RG_SG_si_Cluster = null;
										emptyColumnCount_tFileInputExcel_18++;
									}
									columnIndex_tFileInputExcel_18 = 5;

									if (temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18].length() > 0) {
										curColNum_tFileInputExcel_18 = columnIndex_tFileInputExcel_18
												+ start_column_tFileInputExcel_18 + 1;
										curColName_tFileInputExcel_18 = "Requete_SQL";

										row3.Requete_SQL = temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18];
									} else {
										row3.Requete_SQL = null;
										emptyColumnCount_tFileInputExcel_18++;
									}
									columnIndex_tFileInputExcel_18 = 6;

									if (temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18].length() > 0) {
										curColNum_tFileInputExcel_18 = columnIndex_tFileInputExcel_18
												+ start_column_tFileInputExcel_18 + 1;
										curColName_tFileInputExcel_18 = "UserName_DB_Name";

										row3.UserName_DB_Name = temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18];
									} else {
										row3.UserName_DB_Name = null;
										emptyColumnCount_tFileInputExcel_18++;
									}
									columnIndex_tFileInputExcel_18 = 7;

									if (temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18].length() > 0) {
										curColNum_tFileInputExcel_18 = columnIndex_tFileInputExcel_18
												+ start_column_tFileInputExcel_18 + 1;
										curColName_tFileInputExcel_18 = "Resultat_Attendu_de_la_requete";

										row3.Resultat_Attendu_de_la_requete = temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18];
									} else {
										row3.Resultat_Attendu_de_la_requete = null;
										emptyColumnCount_tFileInputExcel_18++;
									}
									columnIndex_tFileInputExcel_18 = 8;

									if (temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18].length() > 0) {
										curColNum_tFileInputExcel_18 = columnIndex_tFileInputExcel_18
												+ start_column_tFileInputExcel_18 + 1;
										curColName_tFileInputExcel_18 = "Perform_action";

										row3.Perform_action = temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18];
									} else {
										row3.Perform_action = null;
										emptyColumnCount_tFileInputExcel_18++;
									}
									columnIndex_tFileInputExcel_18 = 9;

									if (temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18].length() > 0) {
										curColNum_tFileInputExcel_18 = columnIndex_tFileInputExcel_18
												+ start_column_tFileInputExcel_18 + 1;
										curColName_tFileInputExcel_18 = "Criticite";

										row3.Criticite = temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18];
									} else {
										row3.Criticite = null;
										emptyColumnCount_tFileInputExcel_18++;
									}
									columnIndex_tFileInputExcel_18 = 10;

									if (temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18].length() > 0) {
										curColNum_tFileInputExcel_18 = columnIndex_tFileInputExcel_18
												+ start_column_tFileInputExcel_18 + 1;
										curColName_tFileInputExcel_18 = "Message_d_alarme";

										row3.Message_d_alarme = temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18];
									} else {
										row3.Message_d_alarme = null;
										emptyColumnCount_tFileInputExcel_18++;
									}
									columnIndex_tFileInputExcel_18 = 11;

									if (temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18].length() > 0) {
										curColNum_tFileInputExcel_18 = columnIndex_tFileInputExcel_18
												+ start_column_tFileInputExcel_18 + 1;
										curColName_tFileInputExcel_18 = "Instructions";

										row3.Instructions = temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18];
									} else {
										row3.Instructions = null;
										emptyColumnCount_tFileInputExcel_18++;
									}
									columnIndex_tFileInputExcel_18 = 12;

									if (temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18].length() > 0) {
										curColNum_tFileInputExcel_18 = columnIndex_tFileInputExcel_18
												+ start_column_tFileInputExcel_18 + 1;
										curColName_tFileInputExcel_18 = "Intervalle_de_polling";

										row3.Intervalle_de_polling = temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18];
									} else {
										row3.Intervalle_de_polling = null;
										emptyColumnCount_tFileInputExcel_18++;
									}
									columnIndex_tFileInputExcel_18 = 13;

									if (temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18].length() > 0) {
										curColNum_tFileInputExcel_18 = columnIndex_tFileInputExcel_18
												+ start_column_tFileInputExcel_18 + 1;
										curColName_tFileInputExcel_18 = "Ref__Service";

										row3.Ref__Service = temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18];
									} else {
										row3.Ref__Service = null;
										emptyColumnCount_tFileInputExcel_18++;
									}
									columnIndex_tFileInputExcel_18 = 14;

									if (temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18].length() > 0) {
										curColNum_tFileInputExcel_18 = columnIndex_tFileInputExcel_18
												+ start_column_tFileInputExcel_18 + 1;
										curColName_tFileInputExcel_18 = "Objet";

										row3.Objet = temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18];
									} else {
										row3.Objet = null;
										emptyColumnCount_tFileInputExcel_18++;
									}
									columnIndex_tFileInputExcel_18 = 15;

									if (temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18].length() > 0) {
										curColNum_tFileInputExcel_18 = columnIndex_tFileInputExcel_18
												+ start_column_tFileInputExcel_18 + 1;
										curColName_tFileInputExcel_18 = "Monitored_By";

										row3.Monitored_By = temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18];
									} else {
										row3.Monitored_By = null;
										emptyColumnCount_tFileInputExcel_18++;
									}
									columnIndex_tFileInputExcel_18 = 16;

									if (temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18].length() > 0) {
										curColNum_tFileInputExcel_18 = columnIndex_tFileInputExcel_18
												+ start_column_tFileInputExcel_18 + 1;
										curColName_tFileInputExcel_18 = "Nom_Template";

										row3.Nom_Template = temp_row_tFileInputExcel_18[columnIndex_tFileInputExcel_18];
									} else {
										row3.Nom_Template = null;
										emptyColumnCount_tFileInputExcel_18++;
									}

									nb_line_tFileInputExcel_18++;

								} catch (java.lang.Exception e) {
									globalMap.put("tFileInputExcel_18_ERROR_MESSAGE", e.getMessage());
									whetherReject_tFileInputExcel_18 = true;
									System.err.println(e.getMessage());
									row3 = null;
								}

								/**
								 * [tFileInputExcel_18 begin ] stop
								 */

								/**
								 * [tFileInputExcel_18 main ] start
								 */

								currentComponent = "tFileInputExcel_18";

								tos_count_tFileInputExcel_18++;

								/**
								 * [tFileInputExcel_18 main ] stop
								 */

								/**
								 * [tFileInputExcel_18 process_data_begin ] start
								 */

								currentComponent = "tFileInputExcel_18";

								/**
								 * [tFileInputExcel_18 process_data_begin ] stop
								 */
// Start of branch "row3"
								if (row3 != null) {

									/**
									 * [tFilterRow_3 main ] start
									 */

									currentComponent = "tFilterRow_3";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "row3"

										);
									}

									row12 = null;
									Operator_tFilterRow_3 ope_tFilterRow_3 = new Operator_tFilterRow_3("&&");
									ope_tFilterRow_3.matches((row3.Ref != null), "Ref!=null failed");

									if (ope_tFilterRow_3.getMatchFlag()) {
										if (row12 == null) {
											row12 = new row12Struct();
										}
										row12.N__Ref = row3.N__Ref;
										row12.Ref = row3.Ref;
										row12.Etat = row3.Etat;
										row12.Ref__des_composants = row3.Ref__des_composants;
										row12.RG_SG_si_Cluster = row3.RG_SG_si_Cluster;
										row12.Requete_SQL = row3.Requete_SQL;
										row12.UserName_DB_Name = row3.UserName_DB_Name;
										row12.Resultat_Attendu_de_la_requete = row3.Resultat_Attendu_de_la_requete;
										row12.Perform_action = row3.Perform_action;
										row12.Criticite = row3.Criticite;
										row12.Message_d_alarme = row3.Message_d_alarme;
										row12.Instructions = row3.Instructions;
										row12.Intervalle_de_polling = row3.Intervalle_de_polling;
										row12.Ref__Service = row3.Ref__Service;
										row12.Objet = row3.Objet;
										row12.Monitored_By = row3.Monitored_By;
										row12.Nom_Template = row3.Nom_Template;
										nb_line_ok_tFilterRow_3++;
									} else {
										nb_line_reject_tFilterRow_3++;
									}

									nb_line_tFilterRow_3++;

									tos_count_tFilterRow_3++;

									/**
									 * [tFilterRow_3 main ] stop
									 */

									/**
									 * [tFilterRow_3 process_data_begin ] start
									 */

									currentComponent = "tFilterRow_3";

									/**
									 * [tFilterRow_3 process_data_begin ] stop
									 */
// Start of branch "row12"
									if (row12 != null) {

										/**
										 * [tJavaRow_4 main ] start
										 */

										currentComponent = "tJavaRow_4";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row12"

											);
										}

										String fileName = (String) source_tFileInputExcel_18;

// Extraire la partie "POSANET" du nom du fichier Excel
										String[] parts = fileName.split("_");
										String fileWord = parts[6];

// Récupérer la valeur de "N__Ref" et la convertir en String
										String rowRef = row12.N__Ref;

// Générer l'ID "EDB_POSANET_X"
										String id = "EDB_" + fileWord + "_RequetesSql_" + rowRef;
//Code généré selon les schémas d'entrée et de sortie
										row21.N__Ref = id;
										row21.Ref = row12.Ref;
										row21.Etat = row12.Etat;
										row21.Ref__des_composants = row12.Ref__des_composants;
										row21.RG_SG_si_Cluster = row12.RG_SG_si_Cluster;
										row21.Requete_SQL = row12.Requete_SQL;
										row21.UserName_DB_Name = row12.UserName_DB_Name;
										row21.Resultat_Attendu_de_la_requete = row12.Resultat_Attendu_de_la_requete;
										row21.Perform_action = row12.Perform_action;
										row21.Criticite = row12.Criticite;
										row21.Message_d_alarme = row12.Message_d_alarme;
										row21.Instructions = row12.Instructions;
										row21.Intervalle_de_polling = row12.Intervalle_de_polling;
										row21.Ref__Service = row12.Ref__Service;
										row21.Objet = row12.Objet;
										row21.Monitored_By = row12.Monitored_By;
										row21.Nom_Template = row12.Nom_Template;

										nb_line_tJavaRow_4++;

										tos_count_tJavaRow_4++;

										/**
										 * [tJavaRow_4 main ] stop
										 */

										/**
										 * [tJavaRow_4 process_data_begin ] start
										 */

										currentComponent = "tJavaRow_4";

										/**
										 * [tJavaRow_4 process_data_begin ] stop
										 */

										/**
										 * [tMap_4 main ] start
										 */

										currentComponent = "tMap_4";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row21"

											);
										}

										boolean hasCasePrimitiveKeyWithNull_tMap_4 = false;

										// ###############################
										// # Input tables (lookups)
										boolean rejectedInnerJoin_tMap_4 = false;
										boolean mainRowRejected_tMap_4 = false;

										// ###############################
										{ // start of Var scope

											// ###############################
											// # Vars tables

											Var__tMap_4__Struct Var = Var__tMap_4;// ###############################
											// ###############################
											// # Output tables

											insertrequetes = null;

// # Output table : 'insertrequetes'
											insertrequetes_tmp.id = row21.N__Ref;
											insertrequetes_tmp.Ref = row21.Ref;
											insertrequetes_tmp.Etat = row21.Etat;
											insertrequetes_tmp.Ref_composant = row21.Ref__des_composants;
											insertrequetes_tmp.RG_SG_si_Cluster = row21.RG_SG_si_Cluster;
											insertrequetes_tmp.Requete_SQL = row21.Requete_SQL;
											insertrequetes_tmp.UserName_DB_Name = row21.UserName_DB_Name;
											insertrequetes_tmp.Resultat_Attendu_de_la_requete = row21.Resultat_Attendu_de_la_requete;
											insertrequetes_tmp.Perform_action = row21.Perform_action;
											insertrequetes_tmp.Criticite = row21.Criticite;
											insertrequetes_tmp.Message_alarme = row21.Message_d_alarme;
											insertrequetes_tmp.Instructions = row21.Instructions;
											insertrequetes_tmp.Intervalle_de_polling = row21.Intervalle_de_polling;
											insertrequetes_tmp.Ref_Service = row21.Ref__Service;
											insertrequetes_tmp.Objet = row21.Objet;
											insertrequetes_tmp.Monitored_By = row21.Monitored_By;
											insertrequetes_tmp.Nom_Template = row21.Nom_Template;
											insertrequetes = insertrequetes_tmp;
// ###############################

										} // end of Var scope

										rejectedInnerJoin_tMap_4 = false;

										tos_count_tMap_4++;

										/**
										 * [tMap_4 main ] stop
										 */

										/**
										 * [tMap_4 process_data_begin ] start
										 */

										currentComponent = "tMap_4";

										/**
										 * [tMap_4 process_data_begin ] stop
										 */
// Start of branch "insertrequetes"
										if (insertrequetes != null) {

											/**
											 * [tDBOutput_15 main ] start
											 */

											currentComponent = "tDBOutput_15";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "insertrequetes"

												);
											}

											whetherReject_tDBOutput_15 = false;
											if (insertrequetes.id == null) {
												pstmt_tDBOutput_15.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_15.setString(1, insertrequetes.id);
											}

											if (insertrequetes.Ref == null) {
												pstmt_tDBOutput_15.setNull(2, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_15.setString(2, insertrequetes.Ref);
											}

											if (insertrequetes.Etat == null) {
												pstmt_tDBOutput_15.setNull(3, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_15.setString(3, insertrequetes.Etat);
											}

											if (insertrequetes.Ref_composant == null) {
												pstmt_tDBOutput_15.setNull(4, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_15.setString(4, insertrequetes.Ref_composant);
											}

											if (insertrequetes.RG_SG_si_Cluster == null) {
												pstmt_tDBOutput_15.setNull(5, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_15.setString(5, insertrequetes.RG_SG_si_Cluster);
											}

											if (insertrequetes.Requete_SQL == null) {
												pstmt_tDBOutput_15.setNull(6, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_15.setString(6, insertrequetes.Requete_SQL);
											}

											if (insertrequetes.UserName_DB_Name == null) {
												pstmt_tDBOutput_15.setNull(7, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_15.setString(7, insertrequetes.UserName_DB_Name);
											}

											if (insertrequetes.Resultat_Attendu_de_la_requete == null) {
												pstmt_tDBOutput_15.setNull(8, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_15.setString(8,
														insertrequetes.Resultat_Attendu_de_la_requete);
											}

											if (insertrequetes.Perform_action == null) {
												pstmt_tDBOutput_15.setNull(9, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_15.setString(9, insertrequetes.Perform_action);
											}

											if (insertrequetes.Criticite == null) {
												pstmt_tDBOutput_15.setNull(10, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_15.setString(10, insertrequetes.Criticite);
											}

											if (insertrequetes.Message_alarme == null) {
												pstmt_tDBOutput_15.setNull(11, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_15.setString(11, insertrequetes.Message_alarme);
											}

											if (insertrequetes.Instructions == null) {
												pstmt_tDBOutput_15.setNull(12, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_15.setString(12, insertrequetes.Instructions);
											}

											if (insertrequetes.Intervalle_de_polling == null) {
												pstmt_tDBOutput_15.setNull(13, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_15.setString(13, insertrequetes.Intervalle_de_polling);
											}

											if (insertrequetes.Ref_Service == null) {
												pstmt_tDBOutput_15.setNull(14, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_15.setString(14, insertrequetes.Ref_Service);
											}

											if (insertrequetes.Objet == null) {
												pstmt_tDBOutput_15.setNull(15, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_15.setString(15, insertrequetes.Objet);
											}

											if (insertrequetes.Monitored_By == null) {
												pstmt_tDBOutput_15.setNull(16, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_15.setString(16, insertrequetes.Monitored_By);
											}

											if (insertrequetes.Nom_Template == null) {
												pstmt_tDBOutput_15.setNull(17, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_15.setString(17, insertrequetes.Nom_Template);
											}

											try {
												nb_line_tDBOutput_15++;
												int processedCount_tDBOutput_15 = pstmt_tDBOutput_15.executeUpdate();
												insertedCount_tDBOutput_15 += processedCount_tDBOutput_15;
												rowsToCommitCount_tDBOutput_15 += processedCount_tDBOutput_15;
											} catch (java.lang.Exception e) {
												globalMap.put("tDBOutput_15_ERROR_MESSAGE", e.getMessage());
												whetherReject_tDBOutput_15 = true;
												System.err.print(e.getMessage());
											}
											commitCounter_tDBOutput_15++;

											if (commitEvery_tDBOutput_15 <= commitCounter_tDBOutput_15) {

												if (rowsToCommitCount_tDBOutput_15 != 0) {
												}
												conn_tDBOutput_15.commit();
												if (rowsToCommitCount_tDBOutput_15 != 0) {
													rowsToCommitCount_tDBOutput_15 = 0;
												}
												commitCounter_tDBOutput_15 = 0;

											}

											tos_count_tDBOutput_15++;

											/**
											 * [tDBOutput_15 main ] stop
											 */

											/**
											 * [tDBOutput_15 process_data_begin ] start
											 */

											currentComponent = "tDBOutput_15";

											/**
											 * [tDBOutput_15 process_data_begin ] stop
											 */

											/**
											 * [tDBOutput_15 process_data_end ] start
											 */

											currentComponent = "tDBOutput_15";

											/**
											 * [tDBOutput_15 process_data_end ] stop
											 */

										} // End of branch "insertrequetes"

										/**
										 * [tMap_4 process_data_end ] start
										 */

										currentComponent = "tMap_4";

										/**
										 * [tMap_4 process_data_end ] stop
										 */

										/**
										 * [tJavaRow_4 process_data_end ] start
										 */

										currentComponent = "tJavaRow_4";

										/**
										 * [tJavaRow_4 process_data_end ] stop
										 */

									} // End of branch "row12"

									/**
									 * [tFilterRow_3 process_data_end ] start
									 */

									currentComponent = "tFilterRow_3";

									/**
									 * [tFilterRow_3 process_data_end ] stop
									 */

								} // End of branch "row3"

								/**
								 * [tFileInputExcel_18 process_data_end ] start
								 */

								currentComponent = "tFileInputExcel_18";

								/**
								 * [tFileInputExcel_18 process_data_end ] stop
								 */

								/**
								 * [tFileInputExcel_18 end ] start
								 */

								currentComponent = "tFileInputExcel_18";

							}

							globalMap.put("tFileInputExcel_18_NB_LINE", nb_line_tFileInputExcel_18);

						}

					} finally {

						if (!(source_tFileInputExcel_18 instanceof java.io.InputStream)) {
							workbook_tFileInputExcel_18.getPackage().revert();
						}

					}

					ok_Hash.put("tFileInputExcel_18", true);
					end_Hash.put("tFileInputExcel_18", System.currentTimeMillis());

					/**
					 * [tFileInputExcel_18 end ] stop
					 */

					/**
					 * [tFilterRow_3 end ] start
					 */

					currentComponent = "tFilterRow_3";

					globalMap.put("tFilterRow_3_NB_LINE", nb_line_tFilterRow_3);
					globalMap.put("tFilterRow_3_NB_LINE_OK", nb_line_ok_tFilterRow_3);
					globalMap.put("tFilterRow_3_NB_LINE_REJECT", nb_line_reject_tFilterRow_3);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
					}

					ok_Hash.put("tFilterRow_3", true);
					end_Hash.put("tFilterRow_3", System.currentTimeMillis());

					/**
					 * [tFilterRow_3 end ] stop
					 */

					/**
					 * [tJavaRow_4 end ] start
					 */

					currentComponent = "tJavaRow_4";

					globalMap.put("tJavaRow_4_NB_LINE", nb_line_tJavaRow_4);
					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row12");
					}

					ok_Hash.put("tJavaRow_4", true);
					end_Hash.put("tJavaRow_4", System.currentTimeMillis());

					/**
					 * [tJavaRow_4 end ] stop
					 */

					/**
					 * [tMap_4 end ] start
					 */

					currentComponent = "tMap_4";

// ###############################
// # Lookup hashes releasing
// ###############################      

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row21");
					}

					ok_Hash.put("tMap_4", true);
					end_Hash.put("tMap_4", System.currentTimeMillis());

					/**
					 * [tMap_4 end ] stop
					 */

					/**
					 * [tDBOutput_15 end ] start
					 */

					currentComponent = "tDBOutput_15";

					if (pstmt_tDBOutput_15 != null) {

						pstmt_tDBOutput_15.close();
						resourceMap.remove("pstmt_tDBOutput_15");

					}
					resourceMap.put("statementClosed_tDBOutput_15", true);
					if (commitCounter_tDBOutput_15 > 0 && rowsToCommitCount_tDBOutput_15 != 0) {

					}
					conn_tDBOutput_15.commit();
					if (commitCounter_tDBOutput_15 > 0 && rowsToCommitCount_tDBOutput_15 != 0) {

						rowsToCommitCount_tDBOutput_15 = 0;
					}
					commitCounter_tDBOutput_15 = 0;

					conn_tDBOutput_15.close();

					resourceMap.put("finish_tDBOutput_15", true);

					nb_line_deleted_tDBOutput_15 = nb_line_deleted_tDBOutput_15 + deletedCount_tDBOutput_15;
					nb_line_update_tDBOutput_15 = nb_line_update_tDBOutput_15 + updatedCount_tDBOutput_15;
					nb_line_inserted_tDBOutput_15 = nb_line_inserted_tDBOutput_15 + insertedCount_tDBOutput_15;
					nb_line_rejected_tDBOutput_15 = nb_line_rejected_tDBOutput_15 + rejectedCount_tDBOutput_15;

					globalMap.put("tDBOutput_15_NB_LINE", nb_line_tDBOutput_15);
					globalMap.put("tDBOutput_15_NB_LINE_UPDATED", nb_line_update_tDBOutput_15);
					globalMap.put("tDBOutput_15_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_15);
					globalMap.put("tDBOutput_15_NB_LINE_DELETED", nb_line_deleted_tDBOutput_15);
					globalMap.put("tDBOutput_15_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_15);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "insertrequetes");
					}

					ok_Hash.put("tDBOutput_15", true);
					end_Hash.put("tDBOutput_15", System.currentTimeMillis());

					/**
					 * [tDBOutput_15 end ] stop
					 */

					if (execStat) {
						runStat.updateStatOnConnection("iterate4", 2, "exec" + NB_ITERATE_tFileInputExcel_18);
					}

					/**
					 * [tFileList_1 process_data_end ] start
					 */

					currentComponent = "tFileList_1";

					/**
					 * [tFileList_1 process_data_end ] stop
					 */

					/**
					 * [tFileList_1 end ] start
					 */

					currentComponent = "tFileList_1";

				}
				globalMap.put("tFileList_1_NB_FILE", NB_FILEtFileList_1);

				ok_Hash.put("tFileList_1", true);
				end_Hash.put("tFileList_1", System.currentTimeMillis());

				/**
				 * [tFileList_1 end ] stop
				 */
			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tFileList_1:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk", 0, "ok");
			}

			tIterateToFlow_1_AIProcess(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileList_1 finally ] start
				 */

				currentComponent = "tFileList_1";

				/**
				 * [tFileList_1 finally ] stop
				 */

				/**
				 * [tIterateToFlow_1_ITFO finally ] start
				 */

				currentVirtualComponent = "tIterateToFlow_1";

				currentComponent = "tIterateToFlow_1_ITFO";

				/**
				 * [tIterateToFlow_1_ITFO finally ] stop
				 */

				/**
				 * [tFileInputExcel_1 finally ] start
				 */

				currentComponent = "tFileInputExcel_1";

				/**
				 * [tFileInputExcel_1 finally ] stop
				 */

				/**
				 * [tFilterRow_1 finally ] start
				 */

				currentComponent = "tFilterRow_1";

				/**
				 * [tFilterRow_1 finally ] stop
				 */

				/**
				 * [tJavaRow_2 finally ] start
				 */

				currentComponent = "tJavaRow_2";

				/**
				 * [tJavaRow_2 finally ] stop
				 */

				/**
				 * [tMap_2 finally ] start
				 */

				currentComponent = "tMap_2";

				/**
				 * [tMap_2 finally ] stop
				 */

				/**
				 * [tDBOutput_1 finally ] start
				 */

				currentComponent = "tDBOutput_1";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_1") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_1 = null;
						if ((pstmtToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_1")) != null) {
							pstmtToClose_tDBOutput_1.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_1") == null) {
						java.sql.Connection ctn_tDBOutput_1 = null;
						if ((ctn_tDBOutput_1 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_1")) != null) {
							try {
								ctn_tDBOutput_1.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_1) {
								String errorMessage_tDBOutput_1 = "failed to close the connection in tDBOutput_1 :"
										+ sqlEx_tDBOutput_1.getMessage();
								System.err.println(errorMessage_tDBOutput_1);
							}
						}
					}
				}

				/**
				 * [tDBOutput_1 finally ] stop
				 */

				/**
				 * [tFileInputExcel_13 finally ] start
				 */

				currentComponent = "tFileInputExcel_13";

				/**
				 * [tFileInputExcel_13 finally ] stop
				 */

				/**
				 * [tFilterRow_2 finally ] start
				 */

				currentComponent = "tFilterRow_2";

				/**
				 * [tFilterRow_2 finally ] stop
				 */

				/**
				 * [tJavaRow_3 finally ] start
				 */

				currentComponent = "tJavaRow_3";

				/**
				 * [tJavaRow_3 finally ] stop
				 */

				/**
				 * [tMap_3 finally ] start
				 */

				currentComponent = "tMap_3";

				/**
				 * [tMap_3 finally ] stop
				 */

				/**
				 * [tDBOutput_10 finally ] start
				 */

				currentComponent = "tDBOutput_10";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_10") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_10 = null;
						if ((pstmtToClose_tDBOutput_10 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_10")) != null) {
							pstmtToClose_tDBOutput_10.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_10") == null) {
						java.sql.Connection ctn_tDBOutput_10 = null;
						if ((ctn_tDBOutput_10 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_10")) != null) {
							try {
								ctn_tDBOutput_10.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_10) {
								String errorMessage_tDBOutput_10 = "failed to close the connection in tDBOutput_10 :"
										+ sqlEx_tDBOutput_10.getMessage();
								System.err.println(errorMessage_tDBOutput_10);
							}
						}
					}
				}

				/**
				 * [tDBOutput_10 finally ] stop
				 */

				/**
				 * [tFileInputExcel_18 finally ] start
				 */

				currentComponent = "tFileInputExcel_18";

				/**
				 * [tFileInputExcel_18 finally ] stop
				 */

				/**
				 * [tFilterRow_3 finally ] start
				 */

				currentComponent = "tFilterRow_3";

				/**
				 * [tFilterRow_3 finally ] stop
				 */

				/**
				 * [tJavaRow_4 finally ] start
				 */

				currentComponent = "tJavaRow_4";

				/**
				 * [tJavaRow_4 finally ] stop
				 */

				/**
				 * [tMap_4 finally ] start
				 */

				currentComponent = "tMap_4";

				/**
				 * [tMap_4 finally ] stop
				 */

				/**
				 * [tDBOutput_15 finally ] start
				 */

				currentComponent = "tDBOutput_15";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_15") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_15 = null;
						if ((pstmtToClose_tDBOutput_15 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_15")) != null) {
							pstmtToClose_tDBOutput_15.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_15") == null) {
						java.sql.Connection ctn_tDBOutput_15 = null;
						if ((ctn_tDBOutput_15 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_15")) != null) {
							try {
								ctn_tDBOutput_15.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_15) {
								String errorMessage_tDBOutput_15 = "failed to close the connection in tDBOutput_15 :"
										+ sqlEx_tDBOutput_15.getMessage();
								System.err.println(errorMessage_tDBOutput_15);
							}
						}
					}
				}

				/**
				 * [tDBOutput_15 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileList_1_SUBPROCESS_STATE", 1);
	}

	public static class insertlogfilesStruct implements routines.system.IPersistableRow<insertlogfilesStruct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref_composant;

		public String getRef_composant() {
			return this.Ref_composant;
		}

		public String RG_SG_si_Cluster;

		public String getRG_SG_si_Cluster() {
			return this.RG_SG_si_Cluster;
		}

		public String Logfile;

		public String getLogfile() {
			return this.Logfile;
		}

		public String Localisation;

		public String getLocalisation() {
			return this.Localisation;
		}

		public String Description;

		public String getDescription() {
			return this.Description;
		}

		public String Format_logfile;

		public String getFormat_logfile() {
			return this.Format_logfile;
		}

		public String Separateur;

		public String getSeparateur() {
			return this.Separateur;
		}

		public String Intervalle_de_polling;

		public String getIntervalle_de_polling() {
			return this.Intervalle_de_polling;
		}

		public String Monitored_By;

		public String getMonitored_By() {
			return this.Monitored_By;
		}

		public String Fourni_en_annexe;

		public String getFourni_en_annexe() {
			return this.Fourni_en_annexe;
		}

		public String Ref_Service;

		public String getRef_Service() {
			return this.Ref_Service;
		}

		public String Nom_Template;

		public String getNom_Template() {
			return this.Nom_Template;
		}

		public String Log_Conditions;

		public String getLog_Conditions() {
			return this.Log_Conditions;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final insertlogfilesStruct other = (insertlogfilesStruct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(insertlogfilesStruct other) {

			other.id = this.id;
			other.Ref = this.Ref;
			other.Etat = this.Etat;
			other.Ref_composant = this.Ref_composant;
			other.RG_SG_si_Cluster = this.RG_SG_si_Cluster;
			other.Logfile = this.Logfile;
			other.Localisation = this.Localisation;
			other.Description = this.Description;
			other.Format_logfile = this.Format_logfile;
			other.Separateur = this.Separateur;
			other.Intervalle_de_polling = this.Intervalle_de_polling;
			other.Monitored_By = this.Monitored_By;
			other.Fourni_en_annexe = this.Fourni_en_annexe;
			other.Ref_Service = this.Ref_Service;
			other.Nom_Template = this.Nom_Template;
			other.Log_Conditions = this.Log_Conditions;

		}

		public void copyKeysDataTo(insertlogfilesStruct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref_composant = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Logfile = readString(dis);

					this.Localisation = readString(dis);

					this.Description = readString(dis);

					this.Format_logfile = readString(dis);

					this.Separateur = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Monitored_By = readString(dis);

					this.Fourni_en_annexe = readString(dis);

					this.Ref_Service = readString(dis);

					this.Nom_Template = readString(dis);

					this.Log_Conditions = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref_composant = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Logfile = readString(dis);

					this.Localisation = readString(dis);

					this.Description = readString(dis);

					this.Format_logfile = readString(dis);

					this.Separateur = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Monitored_By = readString(dis);

					this.Fourni_en_annexe = readString(dis);

					this.Ref_Service = readString(dis);

					this.Nom_Template = readString(dis);

					this.Log_Conditions = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref_composant, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Logfile, dos);

				// String

				writeString(this.Localisation, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Format_logfile, dos);

				// String

				writeString(this.Separateur, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Fourni_en_annexe, dos);

				// String

				writeString(this.Ref_Service, dos);

				// String

				writeString(this.Nom_Template, dos);

				// String

				writeString(this.Log_Conditions, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref_composant, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Logfile, dos);

				// String

				writeString(this.Localisation, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Format_logfile, dos);

				// String

				writeString(this.Separateur, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Fourni_en_annexe, dos);

				// String

				writeString(this.Ref_Service, dos);

				// String

				writeString(this.Nom_Template, dos);

				// String

				writeString(this.Log_Conditions, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref_composant=" + Ref_composant);
			sb.append(",RG_SG_si_Cluster=" + RG_SG_si_Cluster);
			sb.append(",Logfile=" + Logfile);
			sb.append(",Localisation=" + Localisation);
			sb.append(",Description=" + Description);
			sb.append(",Format_logfile=" + Format_logfile);
			sb.append(",Separateur=" + Separateur);
			sb.append(",Intervalle_de_polling=" + Intervalle_de_polling);
			sb.append(",Monitored_By=" + Monitored_By);
			sb.append(",Fourni_en_annexe=" + Fourni_en_annexe);
			sb.append(",Ref_Service=" + Ref_Service);
			sb.append(",Nom_Template=" + Nom_Template);
			sb.append(",Log_Conditions=" + Log_Conditions);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(insertlogfilesStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id, other.id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row22Struct implements routines.system.IPersistableRow<row22Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref__des_composants;

		public String getRef__des_composants() {
			return this.Ref__des_composants;
		}

		public String RG_SG_si_Cluster;

		public String getRG_SG_si_Cluster() {
			return this.RG_SG_si_Cluster;
		}

		public String Logfile;

		public String getLogfile() {
			return this.Logfile;
		}

		public String Localisation;

		public String getLocalisation() {
			return this.Localisation;
		}

		public String Description;

		public String getDescription() {
			return this.Description;
		}

		public String Format_logfile;

		public String getFormat_logfile() {
			return this.Format_logfile;
		}

		public String Separateur;

		public String getSeparateur() {
			return this.Separateur;
		}

		public String Intervalle_de_polling;

		public String getIntervalle_de_polling() {
			return this.Intervalle_de_polling;
		}

		public String Monitored_By;

		public String getMonitored_By() {
			return this.Monitored_By;
		}

		public String Fourni_en_annexe;

		public String getFourni_en_annexe() {
			return this.Fourni_en_annexe;
		}

		public String Ref__Service;

		public String getRef__Service() {
			return this.Ref__Service;
		}

		public String Nom_Template;

		public String getNom_Template() {
			return this.Nom_Template;
		}

		public String Log_Conditions;

		public String getLog_Conditions() {
			return this.Log_Conditions;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Logfile = readString(dis);

					this.Localisation = readString(dis);

					this.Description = readString(dis);

					this.Format_logfile = readString(dis);

					this.Separateur = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Monitored_By = readString(dis);

					this.Fourni_en_annexe = readString(dis);

					this.Ref__Service = readString(dis);

					this.Nom_Template = readString(dis);

					this.Log_Conditions = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Logfile = readString(dis);

					this.Localisation = readString(dis);

					this.Description = readString(dis);

					this.Format_logfile = readString(dis);

					this.Separateur = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Monitored_By = readString(dis);

					this.Fourni_en_annexe = readString(dis);

					this.Ref__Service = readString(dis);

					this.Nom_Template = readString(dis);

					this.Log_Conditions = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Logfile, dos);

				// String

				writeString(this.Localisation, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Format_logfile, dos);

				// String

				writeString(this.Separateur, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Fourni_en_annexe, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Nom_Template, dos);

				// String

				writeString(this.Log_Conditions, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Logfile, dos);

				// String

				writeString(this.Localisation, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Format_logfile, dos);

				// String

				writeString(this.Separateur, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Fourni_en_annexe, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Nom_Template, dos);

				// String

				writeString(this.Log_Conditions, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref__des_composants=" + Ref__des_composants);
			sb.append(",RG_SG_si_Cluster=" + RG_SG_si_Cluster);
			sb.append(",Logfile=" + Logfile);
			sb.append(",Localisation=" + Localisation);
			sb.append(",Description=" + Description);
			sb.append(",Format_logfile=" + Format_logfile);
			sb.append(",Separateur=" + Separateur);
			sb.append(",Intervalle_de_polling=" + Intervalle_de_polling);
			sb.append(",Monitored_By=" + Monitored_By);
			sb.append(",Fourni_en_annexe=" + Fourni_en_annexe);
			sb.append(",Ref__Service=" + Ref__Service);
			sb.append(",Nom_Template=" + Nom_Template);
			sb.append(",Log_Conditions=" + Log_Conditions);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row22Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row13Struct implements routines.system.IPersistableRow<row13Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref__des_composants;

		public String getRef__des_composants() {
			return this.Ref__des_composants;
		}

		public String RG_SG_si_Cluster;

		public String getRG_SG_si_Cluster() {
			return this.RG_SG_si_Cluster;
		}

		public String Logfile;

		public String getLogfile() {
			return this.Logfile;
		}

		public String Localisation;

		public String getLocalisation() {
			return this.Localisation;
		}

		public String Description;

		public String getDescription() {
			return this.Description;
		}

		public String Format_logfile;

		public String getFormat_logfile() {
			return this.Format_logfile;
		}

		public String Separateur;

		public String getSeparateur() {
			return this.Separateur;
		}

		public String Intervalle_de_polling;

		public String getIntervalle_de_polling() {
			return this.Intervalle_de_polling;
		}

		public String Monitored_By;

		public String getMonitored_By() {
			return this.Monitored_By;
		}

		public String Fourni_en_annexe;

		public String getFourni_en_annexe() {
			return this.Fourni_en_annexe;
		}

		public String Ref__Service;

		public String getRef__Service() {
			return this.Ref__Service;
		}

		public String Nom_Template;

		public String getNom_Template() {
			return this.Nom_Template;
		}

		public String Log_Conditions;

		public String getLog_Conditions() {
			return this.Log_Conditions;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Logfile = readString(dis);

					this.Localisation = readString(dis);

					this.Description = readString(dis);

					this.Format_logfile = readString(dis);

					this.Separateur = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Monitored_By = readString(dis);

					this.Fourni_en_annexe = readString(dis);

					this.Ref__Service = readString(dis);

					this.Nom_Template = readString(dis);

					this.Log_Conditions = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Logfile = readString(dis);

					this.Localisation = readString(dis);

					this.Description = readString(dis);

					this.Format_logfile = readString(dis);

					this.Separateur = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Monitored_By = readString(dis);

					this.Fourni_en_annexe = readString(dis);

					this.Ref__Service = readString(dis);

					this.Nom_Template = readString(dis);

					this.Log_Conditions = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Logfile, dos);

				// String

				writeString(this.Localisation, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Format_logfile, dos);

				// String

				writeString(this.Separateur, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Fourni_en_annexe, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Nom_Template, dos);

				// String

				writeString(this.Log_Conditions, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Logfile, dos);

				// String

				writeString(this.Localisation, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Format_logfile, dos);

				// String

				writeString(this.Separateur, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Fourni_en_annexe, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Nom_Template, dos);

				// String

				writeString(this.Log_Conditions, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref__des_composants=" + Ref__des_composants);
			sb.append(",RG_SG_si_Cluster=" + RG_SG_si_Cluster);
			sb.append(",Logfile=" + Logfile);
			sb.append(",Localisation=" + Localisation);
			sb.append(",Description=" + Description);
			sb.append(",Format_logfile=" + Format_logfile);
			sb.append(",Separateur=" + Separateur);
			sb.append(",Intervalle_de_polling=" + Intervalle_de_polling);
			sb.append(",Monitored_By=" + Monitored_By);
			sb.append(",Fourni_en_annexe=" + Fourni_en_annexe);
			sb.append(",Ref__Service=" + Ref__Service);
			sb.append(",Nom_Template=" + Nom_Template);
			sb.append(",Log_Conditions=" + Log_Conditions);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row13Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref__des_composants;

		public String getRef__des_composants() {
			return this.Ref__des_composants;
		}

		public String RG_SG_si_Cluster;

		public String getRG_SG_si_Cluster() {
			return this.RG_SG_si_Cluster;
		}

		public String Logfile;

		public String getLogfile() {
			return this.Logfile;
		}

		public String Localisation;

		public String getLocalisation() {
			return this.Localisation;
		}

		public String Description;

		public String getDescription() {
			return this.Description;
		}

		public String Format_logfile;

		public String getFormat_logfile() {
			return this.Format_logfile;
		}

		public String Separateur;

		public String getSeparateur() {
			return this.Separateur;
		}

		public String Intervalle_de_polling;

		public String getIntervalle_de_polling() {
			return this.Intervalle_de_polling;
		}

		public String Monitored_By;

		public String getMonitored_By() {
			return this.Monitored_By;
		}

		public String Fourni_en_annexe;

		public String getFourni_en_annexe() {
			return this.Fourni_en_annexe;
		}

		public String Ref__Service;

		public String getRef__Service() {
			return this.Ref__Service;
		}

		public String Nom_Template;

		public String getNom_Template() {
			return this.Nom_Template;
		}

		public String Log_Conditions;

		public String getLog_Conditions() {
			return this.Log_Conditions;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Logfile = readString(dis);

					this.Localisation = readString(dis);

					this.Description = readString(dis);

					this.Format_logfile = readString(dis);

					this.Separateur = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Monitored_By = readString(dis);

					this.Fourni_en_annexe = readString(dis);

					this.Ref__Service = readString(dis);

					this.Nom_Template = readString(dis);

					this.Log_Conditions = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Logfile = readString(dis);

					this.Localisation = readString(dis);

					this.Description = readString(dis);

					this.Format_logfile = readString(dis);

					this.Separateur = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Monitored_By = readString(dis);

					this.Fourni_en_annexe = readString(dis);

					this.Ref__Service = readString(dis);

					this.Nom_Template = readString(dis);

					this.Log_Conditions = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Logfile, dos);

				// String

				writeString(this.Localisation, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Format_logfile, dos);

				// String

				writeString(this.Separateur, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Fourni_en_annexe, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Nom_Template, dos);

				// String

				writeString(this.Log_Conditions, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Logfile, dos);

				// String

				writeString(this.Localisation, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Format_logfile, dos);

				// String

				writeString(this.Separateur, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Fourni_en_annexe, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Nom_Template, dos);

				// String

				writeString(this.Log_Conditions, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref__des_composants=" + Ref__des_composants);
			sb.append(",RG_SG_si_Cluster=" + RG_SG_si_Cluster);
			sb.append(",Logfile=" + Logfile);
			sb.append(",Localisation=" + Localisation);
			sb.append(",Description=" + Description);
			sb.append(",Format_logfile=" + Format_logfile);
			sb.append(",Separateur=" + Separateur);
			sb.append(",Intervalle_de_polling=" + Intervalle_de_polling);
			sb.append(",Monitored_By=" + Monitored_By);
			sb.append(",Fourni_en_annexe=" + Fourni_en_annexe);
			sb.append(",Ref__Service=" + Ref__Service);
			sb.append(",Nom_Template=" + Nom_Template);
			sb.append(",Log_Conditions=" + Log_Conditions);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class insertscriptsStruct implements routines.system.IPersistableRow<insertscriptsStruct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref_composant;

		public String getRef_composant() {
			return this.Ref_composant;
		}

		public String RG_SG_si_Cluster;

		public String getRG_SG_si_Cluster() {
			return this.RG_SG_si_Cluster;
		}

		public String script;

		public String getScript() {
			return this.script;
		}

		public String Code_erreur;

		public String getCode_erreur() {
			return this.Code_erreur;
		}

		public String Cricite;

		public String getCricite() {
			return this.Cricite;
		}

		public String Description;

		public String getDescription() {
			return this.Description;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Monitored_By;

		public String getMonitored_By() {
			return this.Monitored_By;
		}

		public String Ref_Service;

		public String getRef_Service() {
			return this.Ref_Service;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final insertscriptsStruct other = (insertscriptsStruct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(insertscriptsStruct other) {

			other.id = this.id;
			other.Ref = this.Ref;
			other.Etat = this.Etat;
			other.Ref_composant = this.Ref_composant;
			other.RG_SG_si_Cluster = this.RG_SG_si_Cluster;
			other.script = this.script;
			other.Code_erreur = this.Code_erreur;
			other.Cricite = this.Cricite;
			other.Description = this.Description;
			other.Instructions = this.Instructions;
			other.Monitored_By = this.Monitored_By;
			other.Ref_Service = this.Ref_Service;

		}

		public void copyKeysDataTo(insertscriptsStruct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref_composant = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.script = readString(dis);

					this.Code_erreur = readString(dis);

					this.Cricite = readString(dis);

					this.Description = readString(dis);

					this.Instructions = readString(dis);

					this.Monitored_By = readString(dis);

					this.Ref_Service = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref_composant = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.script = readString(dis);

					this.Code_erreur = readString(dis);

					this.Cricite = readString(dis);

					this.Description = readString(dis);

					this.Instructions = readString(dis);

					this.Monitored_By = readString(dis);

					this.Ref_Service = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref_composant, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.script, dos);

				// String

				writeString(this.Code_erreur, dos);

				// String

				writeString(this.Cricite, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Ref_Service, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref_composant, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.script, dos);

				// String

				writeString(this.Code_erreur, dos);

				// String

				writeString(this.Cricite, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Ref_Service, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref_composant=" + Ref_composant);
			sb.append(",RG_SG_si_Cluster=" + RG_SG_si_Cluster);
			sb.append(",script=" + script);
			sb.append(",Code_erreur=" + Code_erreur);
			sb.append(",Cricite=" + Cricite);
			sb.append(",Description=" + Description);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Monitored_By=" + Monitored_By);
			sb.append(",Ref_Service=" + Ref_Service);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(insertscriptsStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id, other.id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row23Struct implements routines.system.IPersistableRow<row23Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref__des_composants;

		public String getRef__des_composants() {
			return this.Ref__des_composants;
		}

		public String RG_SG_si_Cluster;

		public String getRG_SG_si_Cluster() {
			return this.RG_SG_si_Cluster;
		}

		public String Script;

		public String getScript() {
			return this.Script;
		}

		public String Code_erreur;

		public String getCode_erreur() {
			return this.Code_erreur;
		}

		public String Criticite;

		public String getCriticite() {
			return this.Criticite;
		}

		public String Description;

		public String getDescription() {
			return this.Description;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Monitored_By;

		public String getMonitored_By() {
			return this.Monitored_By;
		}

		public String Ref__Service;

		public String getRef__Service() {
			return this.Ref__Service;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Script = readString(dis);

					this.Code_erreur = readString(dis);

					this.Criticite = readString(dis);

					this.Description = readString(dis);

					this.Instructions = readString(dis);

					this.Monitored_By = readString(dis);

					this.Ref__Service = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Script = readString(dis);

					this.Code_erreur = readString(dis);

					this.Criticite = readString(dis);

					this.Description = readString(dis);

					this.Instructions = readString(dis);

					this.Monitored_By = readString(dis);

					this.Ref__Service = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Script, dos);

				// String

				writeString(this.Code_erreur, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Ref__Service, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Script, dos);

				// String

				writeString(this.Code_erreur, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Ref__Service, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref__des_composants=" + Ref__des_composants);
			sb.append(",RG_SG_si_Cluster=" + RG_SG_si_Cluster);
			sb.append(",Script=" + Script);
			sb.append(",Code_erreur=" + Code_erreur);
			sb.append(",Criticite=" + Criticite);
			sb.append(",Description=" + Description);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Monitored_By=" + Monitored_By);
			sb.append(",Ref__Service=" + Ref__Service);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row23Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row14Struct implements routines.system.IPersistableRow<row14Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref__des_composants;

		public String getRef__des_composants() {
			return this.Ref__des_composants;
		}

		public String RG_SG_si_Cluster;

		public String getRG_SG_si_Cluster() {
			return this.RG_SG_si_Cluster;
		}

		public String Script;

		public String getScript() {
			return this.Script;
		}

		public String Code_erreur;

		public String getCode_erreur() {
			return this.Code_erreur;
		}

		public String Criticite;

		public String getCriticite() {
			return this.Criticite;
		}

		public String Description;

		public String getDescription() {
			return this.Description;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Monitored_By;

		public String getMonitored_By() {
			return this.Monitored_By;
		}

		public String Ref__Service;

		public String getRef__Service() {
			return this.Ref__Service;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Script = readString(dis);

					this.Code_erreur = readString(dis);

					this.Criticite = readString(dis);

					this.Description = readString(dis);

					this.Instructions = readString(dis);

					this.Monitored_By = readString(dis);

					this.Ref__Service = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Script = readString(dis);

					this.Code_erreur = readString(dis);

					this.Criticite = readString(dis);

					this.Description = readString(dis);

					this.Instructions = readString(dis);

					this.Monitored_By = readString(dis);

					this.Ref__Service = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Script, dos);

				// String

				writeString(this.Code_erreur, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Ref__Service, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Script, dos);

				// String

				writeString(this.Code_erreur, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Ref__Service, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref__des_composants=" + Ref__des_composants);
			sb.append(",RG_SG_si_Cluster=" + RG_SG_si_Cluster);
			sb.append(",Script=" + Script);
			sb.append(",Code_erreur=" + Code_erreur);
			sb.append(",Criticite=" + Criticite);
			sb.append(",Description=" + Description);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Monitored_By=" + Monitored_By);
			sb.append(",Ref__Service=" + Ref__Service);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row14Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref__des_composants;

		public String getRef__des_composants() {
			return this.Ref__des_composants;
		}

		public String RG_SG_si_Cluster;

		public String getRG_SG_si_Cluster() {
			return this.RG_SG_si_Cluster;
		}

		public String Script;

		public String getScript() {
			return this.Script;
		}

		public String Code_erreur;

		public String getCode_erreur() {
			return this.Code_erreur;
		}

		public String Criticite;

		public String getCriticite() {
			return this.Criticite;
		}

		public String Description;

		public String getDescription() {
			return this.Description;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Monitored_By;

		public String getMonitored_By() {
			return this.Monitored_By;
		}

		public String Ref__Service;

		public String getRef__Service() {
			return this.Ref__Service;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Script = readString(dis);

					this.Code_erreur = readString(dis);

					this.Criticite = readString(dis);

					this.Description = readString(dis);

					this.Instructions = readString(dis);

					this.Monitored_By = readString(dis);

					this.Ref__Service = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.Script = readString(dis);

					this.Code_erreur = readString(dis);

					this.Criticite = readString(dis);

					this.Description = readString(dis);

					this.Instructions = readString(dis);

					this.Monitored_By = readString(dis);

					this.Ref__Service = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Script, dos);

				// String

				writeString(this.Code_erreur, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Ref__Service, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.Script, dos);

				// String

				writeString(this.Code_erreur, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Ref__Service, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref__des_composants=" + Ref__des_composants);
			sb.append(",RG_SG_si_Cluster=" + RG_SG_si_Cluster);
			sb.append(",Script=" + Script);
			sb.append(",Code_erreur=" + Code_erreur);
			sb.append(",Criticite=" + Criticite);
			sb.append(",Description=" + Description);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Monitored_By=" + Monitored_By);
			sb.append(",Ref__Service=" + Ref__Service);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class insrturlsStruct implements routines.system.IPersistableRow<insrturlsStruct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref_composant;

		public String getRef_composant() {
			return this.Ref_composant;
		}

		public String RG_SG_si_Cluster;

		public String getRG_SG_si_Cluster() {
			return this.RG_SG_si_Cluster;
		}

		public String URL;

		public String getURL() {
			return this.URL;
		}

		public String Perform_action;

		public String getPerform_action() {
			return this.Perform_action;
		}

		public String Cricite;

		public String getCricite() {
			return this.Cricite;
		}

		public String Message_alarme;

		public String getMessage_alarme() {
			return this.Message_alarme;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Intervalle_de_polling;

		public String getIntervalle_de_polling() {
			return this.Intervalle_de_polling;
		}

		public String Ref_Service;

		public String getRef_Service() {
			return this.Ref_Service;
		}

		public String Objet;

		public String getObjet() {
			return this.Objet;
		}

		public String Monitored_by;

		public String getMonitored_by() {
			return this.Monitored_by;
		}

		public String Nom_Template;

		public String getNom_Template() {
			return this.Nom_Template;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final insrturlsStruct other = (insrturlsStruct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(insrturlsStruct other) {

			other.id = this.id;
			other.Ref = this.Ref;
			other.Etat = this.Etat;
			other.Ref_composant = this.Ref_composant;
			other.RG_SG_si_Cluster = this.RG_SG_si_Cluster;
			other.URL = this.URL;
			other.Perform_action = this.Perform_action;
			other.Cricite = this.Cricite;
			other.Message_alarme = this.Message_alarme;
			other.Instructions = this.Instructions;
			other.Intervalle_de_polling = this.Intervalle_de_polling;
			other.Ref_Service = this.Ref_Service;
			other.Objet = this.Objet;
			other.Monitored_by = this.Monitored_by;
			other.Nom_Template = this.Nom_Template;

		}

		public void copyKeysDataTo(insrturlsStruct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref_composant = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.URL = readString(dis);

					this.Perform_action = readString(dis);

					this.Cricite = readString(dis);

					this.Message_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Ref_Service = readString(dis);

					this.Objet = readString(dis);

					this.Monitored_by = readString(dis);

					this.Nom_Template = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref_composant = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.URL = readString(dis);

					this.Perform_action = readString(dis);

					this.Cricite = readString(dis);

					this.Message_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Ref_Service = readString(dis);

					this.Objet = readString(dis);

					this.Monitored_by = readString(dis);

					this.Nom_Template = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref_composant, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.URL, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Cricite, dos);

				// String

				writeString(this.Message_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Ref_Service, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Monitored_by, dos);

				// String

				writeString(this.Nom_Template, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref_composant, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.URL, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Cricite, dos);

				// String

				writeString(this.Message_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Ref_Service, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Monitored_by, dos);

				// String

				writeString(this.Nom_Template, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref_composant=" + Ref_composant);
			sb.append(",RG_SG_si_Cluster=" + RG_SG_si_Cluster);
			sb.append(",URL=" + URL);
			sb.append(",Perform_action=" + Perform_action);
			sb.append(",Cricite=" + Cricite);
			sb.append(",Message_alarme=" + Message_alarme);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Intervalle_de_polling=" + Intervalle_de_polling);
			sb.append(",Ref_Service=" + Ref_Service);
			sb.append(",Objet=" + Objet);
			sb.append(",Monitored_by=" + Monitored_by);
			sb.append(",Nom_Template=" + Nom_Template);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(insrturlsStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id, other.id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row24Struct implements routines.system.IPersistableRow<row24Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref__des_composants;

		public String getRef__des_composants() {
			return this.Ref__des_composants;
		}

		public String RG_SG_si_Cluster;

		public String getRG_SG_si_Cluster() {
			return this.RG_SG_si_Cluster;
		}

		public String URL;

		public String getURL() {
			return this.URL;
		}

		public String Perform_action;

		public String getPerform_action() {
			return this.Perform_action;
		}

		public String Criticite;

		public String getCriticite() {
			return this.Criticite;
		}

		public String Message_d_alarme;

		public String getMessage_d_alarme() {
			return this.Message_d_alarme;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Intervalle_de_polling;

		public String getIntervalle_de_polling() {
			return this.Intervalle_de_polling;
		}

		public String Ref__Service;

		public String getRef__Service() {
			return this.Ref__Service;
		}

		public String Objet;

		public String getObjet() {
			return this.Objet;
		}

		public String Monitored_By;

		public String getMonitored_By() {
			return this.Monitored_By;
		}

		public String Nom_Template;

		public String getNom_Template() {
			return this.Nom_Template;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.URL = readString(dis);

					this.Perform_action = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Ref__Service = readString(dis);

					this.Objet = readString(dis);

					this.Monitored_By = readString(dis);

					this.Nom_Template = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.URL = readString(dis);

					this.Perform_action = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Ref__Service = readString(dis);

					this.Objet = readString(dis);

					this.Monitored_By = readString(dis);

					this.Nom_Template = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.URL, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Nom_Template, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.URL, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Nom_Template, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref__des_composants=" + Ref__des_composants);
			sb.append(",RG_SG_si_Cluster=" + RG_SG_si_Cluster);
			sb.append(",URL=" + URL);
			sb.append(",Perform_action=" + Perform_action);
			sb.append(",Criticite=" + Criticite);
			sb.append(",Message_d_alarme=" + Message_d_alarme);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Intervalle_de_polling=" + Intervalle_de_polling);
			sb.append(",Ref__Service=" + Ref__Service);
			sb.append(",Objet=" + Objet);
			sb.append(",Monitored_By=" + Monitored_By);
			sb.append(",Nom_Template=" + Nom_Template);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row24Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row15Struct implements routines.system.IPersistableRow<row15Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref__des_composants;

		public String getRef__des_composants() {
			return this.Ref__des_composants;
		}

		public String RG_SG_si_Cluster;

		public String getRG_SG_si_Cluster() {
			return this.RG_SG_si_Cluster;
		}

		public String URL;

		public String getURL() {
			return this.URL;
		}

		public String Perform_action;

		public String getPerform_action() {
			return this.Perform_action;
		}

		public String Criticite;

		public String getCriticite() {
			return this.Criticite;
		}

		public String Message_d_alarme;

		public String getMessage_d_alarme() {
			return this.Message_d_alarme;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Intervalle_de_polling;

		public String getIntervalle_de_polling() {
			return this.Intervalle_de_polling;
		}

		public String Ref__Service;

		public String getRef__Service() {
			return this.Ref__Service;
		}

		public String Objet;

		public String getObjet() {
			return this.Objet;
		}

		public String Monitored_By;

		public String getMonitored_By() {
			return this.Monitored_By;
		}

		public String Nom_Template;

		public String getNom_Template() {
			return this.Nom_Template;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.URL = readString(dis);

					this.Perform_action = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Ref__Service = readString(dis);

					this.Objet = readString(dis);

					this.Monitored_By = readString(dis);

					this.Nom_Template = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.URL = readString(dis);

					this.Perform_action = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Ref__Service = readString(dis);

					this.Objet = readString(dis);

					this.Monitored_By = readString(dis);

					this.Nom_Template = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.URL, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Nom_Template, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.URL, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Nom_Template, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref__des_composants=" + Ref__des_composants);
			sb.append(",RG_SG_si_Cluster=" + RG_SG_si_Cluster);
			sb.append(",URL=" + URL);
			sb.append(",Perform_action=" + Perform_action);
			sb.append(",Criticite=" + Criticite);
			sb.append(",Message_d_alarme=" + Message_d_alarme);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Intervalle_de_polling=" + Intervalle_de_polling);
			sb.append(",Ref__Service=" + Ref__Service);
			sb.append(",Objet=" + Objet);
			sb.append(",Monitored_By=" + Monitored_By);
			sb.append(",Nom_Template=" + Nom_Template);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row15Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row6Struct implements routines.system.IPersistableRow<row6Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref__des_composants;

		public String getRef__des_composants() {
			return this.Ref__des_composants;
		}

		public String RG_SG_si_Cluster;

		public String getRG_SG_si_Cluster() {
			return this.RG_SG_si_Cluster;
		}

		public String URL;

		public String getURL() {
			return this.URL;
		}

		public String Perform_action;

		public String getPerform_action() {
			return this.Perform_action;
		}

		public String Criticite;

		public String getCriticite() {
			return this.Criticite;
		}

		public String Message_d_alarme;

		public String getMessage_d_alarme() {
			return this.Message_d_alarme;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Intervalle_de_polling;

		public String getIntervalle_de_polling() {
			return this.Intervalle_de_polling;
		}

		public String Ref__Service;

		public String getRef__Service() {
			return this.Ref__Service;
		}

		public String Objet;

		public String getObjet() {
			return this.Objet;
		}

		public String Monitored_By;

		public String getMonitored_By() {
			return this.Monitored_By;
		}

		public String Nom_Template;

		public String getNom_Template() {
			return this.Nom_Template;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.URL = readString(dis);

					this.Perform_action = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Ref__Service = readString(dis);

					this.Objet = readString(dis);

					this.Monitored_By = readString(dis);

					this.Nom_Template = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.RG_SG_si_Cluster = readString(dis);

					this.URL = readString(dis);

					this.Perform_action = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Intervalle_de_polling = readString(dis);

					this.Ref__Service = readString(dis);

					this.Objet = readString(dis);

					this.Monitored_By = readString(dis);

					this.Nom_Template = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.URL, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Nom_Template, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.RG_SG_si_Cluster, dos);

				// String

				writeString(this.URL, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Intervalle_de_polling, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Monitored_By, dos);

				// String

				writeString(this.Nom_Template, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref__des_composants=" + Ref__des_composants);
			sb.append(",RG_SG_si_Cluster=" + RG_SG_si_Cluster);
			sb.append(",URL=" + URL);
			sb.append(",Perform_action=" + Perform_action);
			sb.append(",Criticite=" + Criticite);
			sb.append(",Message_d_alarme=" + Message_d_alarme);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Intervalle_de_polling=" + Intervalle_de_polling);
			sb.append(",Ref__Service=" + Ref__Service);
			sb.append(",Objet=" + Objet);
			sb.append(",Monitored_By=" + Monitored_By);
			sb.append(",Nom_Template=" + Nom_Template);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row6Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class OnSubjobOkStructtIterateToFlow_2
			implements routines.system.IPersistableRow<OnSubjobOkStructtIterateToFlow_2> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String filepath2;

		public String getFilepath2() {
			return this.filepath2;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.filepath2 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.filepath2 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.filepath2, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.filepath2, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("filepath2=" + filepath2);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnSubjobOkStructtIterateToFlow_2 other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileList_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileList_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;
		String currentVirtualComponent = null;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row4Struct row4 = new row4Struct();
				row13Struct row13 = new row13Struct();
				row22Struct row22 = new row22Struct();
				insertlogfilesStruct insertlogfiles = new insertlogfilesStruct();
				row5Struct row5 = new row5Struct();
				row14Struct row14 = new row14Struct();
				row23Struct row23 = new row23Struct();
				insertscriptsStruct insertscripts = new insertscriptsStruct();
				row6Struct row6 = new row6Struct();
				row15Struct row15 = new row15Struct();
				row24Struct row24 = new row24Struct();
				insrturlsStruct insrturls = new insrturlsStruct();

				/**
				 * [tFileList_2 begin ] start
				 */

				int NB_ITERATE_tFileInputExcel_48 = 0; // for statistics

				int NB_ITERATE_tFileInputExcel_6 = 0; // for statistics

				int NB_ITERATE_tFileInputExcel_24 = 0; // for statistics

				int NB_ITERATE_tIterateToFlow_2_ITFO = 0; // for statistics

				ok_Hash.put("tFileList_2", false);
				start_Hash.put("tFileList_2", System.currentTimeMillis());

				currentComponent = "tFileList_2";

				int tos_count_tFileList_2 = 0;

				String directory_tFileList_2 = "C:/Users/Majdi/Downloads/pasfait";
				final java.util.List<String> maskList_tFileList_2 = new java.util.ArrayList<String>();
				final java.util.List<java.util.regex.Pattern> patternList_tFileList_2 = new java.util.ArrayList<java.util.regex.Pattern>();
				maskList_tFileList_2.add("*.xlsx");
				for (final String filemask_tFileList_2 : maskList_tFileList_2) {
					String filemask_compile_tFileList_2 = filemask_tFileList_2;

					filemask_compile_tFileList_2 = org.apache.oro.text.GlobCompiler.globToPerl5(
							filemask_tFileList_2.toCharArray(), org.apache.oro.text.GlobCompiler.DEFAULT_MASK);

					java.util.regex.Pattern fileNamePattern_tFileList_2 = java.util.regex.Pattern
							.compile(filemask_compile_tFileList_2);
					patternList_tFileList_2.add(fileNamePattern_tFileList_2);
				}
				int NB_FILEtFileList_2 = 0;

				final boolean case_sensitive_tFileList_2 = true;

				final java.util.List<java.io.File> list_tFileList_2 = new java.util.ArrayList<java.io.File>();
				final java.util.Set<String> filePath_tFileList_2 = new java.util.HashSet<String>();
				java.io.File file_tFileList_2 = new java.io.File(directory_tFileList_2);

				file_tFileList_2.listFiles(new java.io.FilenameFilter() {
					public boolean accept(java.io.File dir, String name) {
						java.io.File file = new java.io.File(dir, name);
						if (!file.isDirectory()) {

							String fileName_tFileList_2 = file.getName();
							for (final java.util.regex.Pattern fileNamePattern_tFileList_2 : patternList_tFileList_2) {
								if (fileNamePattern_tFileList_2.matcher(fileName_tFileList_2).matches()) {
									if (!filePath_tFileList_2.contains(file.getAbsolutePath())) {
										list_tFileList_2.add(file);
										filePath_tFileList_2.add(file.getAbsolutePath());
									}
								}
							}
						}
						return true;
					}
				});
				java.util.Collections.sort(list_tFileList_2);

				for (int i_tFileList_2 = 0; i_tFileList_2 < list_tFileList_2.size(); i_tFileList_2++) {
					java.io.File files_tFileList_2 = list_tFileList_2.get(i_tFileList_2);
					String fileName_tFileList_2 = files_tFileList_2.getName();

					String currentFileName_tFileList_2 = files_tFileList_2.getName();
					String currentFilePath_tFileList_2 = files_tFileList_2.getAbsolutePath();
					String currentFileDirectory_tFileList_2 = files_tFileList_2.getParent();
					String currentFileExtension_tFileList_2 = null;

					if (files_tFileList_2.getName().contains(".") && files_tFileList_2.isFile()) {
						currentFileExtension_tFileList_2 = files_tFileList_2.getName()
								.substring(files_tFileList_2.getName().lastIndexOf(".") + 1);
					} else {
						currentFileExtension_tFileList_2 = "";
					}

					NB_FILEtFileList_2++;
					globalMap.put("tFileList_2_CURRENT_FILE", currentFileName_tFileList_2);
					globalMap.put("tFileList_2_CURRENT_FILEPATH", currentFilePath_tFileList_2);
					globalMap.put("tFileList_2_CURRENT_FILEDIRECTORY", currentFileDirectory_tFileList_2);
					globalMap.put("tFileList_2_CURRENT_FILEEXTENSION", currentFileExtension_tFileList_2);
					globalMap.put("tFileList_2_NB_FILE", NB_FILEtFileList_2);

					/**
					 * [tFileList_2 begin ] stop
					 */

					/**
					 * [tFileList_2 main ] start
					 */

					currentComponent = "tFileList_2";

					tos_count_tFileList_2++;

					/**
					 * [tFileList_2 main ] stop
					 */

					/**
					 * [tFileList_2 process_data_begin ] start
					 */

					currentComponent = "tFileList_2";

					/**
					 * [tFileList_2 process_data_begin ] stop
					 */
					NB_ITERATE_tFileInputExcel_6++;

					if (execStat) {
						runStat.updateStatOnConnection("row22", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("insertlogfiles", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row4", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row13", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate5", 1, "exec" + NB_ITERATE_tFileInputExcel_6);
						// Thread.sleep(1000);
					}

					/**
					 * [tDBOutput_2 begin ] start
					 */

					ok_Hash.put("tDBOutput_2", false);
					start_Hash.put("tDBOutput_2", System.currentTimeMillis());

					currentComponent = "tDBOutput_2";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "insertlogfiles");
					}

					int tos_count_tDBOutput_2 = 0;

					int nb_line_tDBOutput_2 = 0;
					int nb_line_update_tDBOutput_2 = 0;
					int nb_line_inserted_tDBOutput_2 = 0;
					int nb_line_deleted_tDBOutput_2 = 0;
					int nb_line_rejected_tDBOutput_2 = 0;

					int deletedCount_tDBOutput_2 = 0;
					int updatedCount_tDBOutput_2 = 0;
					int insertedCount_tDBOutput_2 = 0;
					int rowsToCommitCount_tDBOutput_2 = 0;
					int rejectedCount_tDBOutput_2 = 0;

					String tableName_tDBOutput_2 = "log_files";
					boolean whetherReject_tDBOutput_2 = false;

					java.util.Calendar calendar_tDBOutput_2 = java.util.Calendar.getInstance();
					calendar_tDBOutput_2.set(1, 0, 1, 0, 0, 0);
					long year1_tDBOutput_2 = calendar_tDBOutput_2.getTime().getTime();
					calendar_tDBOutput_2.set(10000, 0, 1, 0, 0, 0);
					long year10000_tDBOutput_2 = calendar_tDBOutput_2.getTime().getTime();
					long date_tDBOutput_2;

					java.sql.Connection conn_tDBOutput_2 = null;

					String properties_tDBOutput_2 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
					if (properties_tDBOutput_2 == null || properties_tDBOutput_2.trim().length() == 0) {
						properties_tDBOutput_2 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
					} else {
						if (!properties_tDBOutput_2.contains("rewriteBatchedStatements=")) {
							properties_tDBOutput_2 += "&rewriteBatchedStatements=true";
						}

						if (!properties_tDBOutput_2.contains("allowLoadLocalInfile=")) {
							properties_tDBOutput_2 += "&allowLoadLocalInfile=true";
						}
					}

					String url_tDBOutput_2 = "jdbc:mariadb://" + "127.0.0.1" + ":" + "3306" + "/" + "ooredoo" + "?"
							+ properties_tDBOutput_2;

					String driverClass_tDBOutput_2 = "org.mariadb.jdbc.Driver";

					String dbUser_tDBOutput_2 = "root";

					final String decryptedPassword_tDBOutput_2 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:WY8jlQKhn8WigbJyg3eN9dL3AbCSe+4mzTAq3w==");

					String dbPwd_tDBOutput_2 = decryptedPassword_tDBOutput_2;
					java.lang.Class.forName(driverClass_tDBOutput_2);

					conn_tDBOutput_2 = java.sql.DriverManager.getConnection(url_tDBOutput_2, dbUser_tDBOutput_2,
							dbPwd_tDBOutput_2);

					resourceMap.put("conn_tDBOutput_2", conn_tDBOutput_2);
					conn_tDBOutput_2.setAutoCommit(false);
					int commitEvery_tDBOutput_2 = 10000;
					int commitCounter_tDBOutput_2 = 0;

					int count_tDBOutput_2 = 0;

					String insert_tDBOutput_2 = "INSERT IGNORE INTO `" + "log_files"
							+ "` (`id`,`Ref`,`Etat`,`Ref_composant`,`RG/SG_si_Cluster`,`Logfile`,`Localisation`,`Description`,`Format_logfile`,`Separateur`,`Intervalle_de_polling`,`Monitored_By`,`Fourni_en_annexe`,`Ref_Service`,`Nom_Template`,`Log_Conditions`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

					java.sql.PreparedStatement pstmt_tDBOutput_2 = conn_tDBOutput_2
							.prepareStatement(insert_tDBOutput_2);
					resourceMap.put("pstmt_tDBOutput_2", pstmt_tDBOutput_2);

					/**
					 * [tDBOutput_2 begin ] stop
					 */

					/**
					 * [tMap_5 begin ] start
					 */

					ok_Hash.put("tMap_5", false);
					start_Hash.put("tMap_5", System.currentTimeMillis());

					currentComponent = "tMap_5";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row22");
					}

					int tos_count_tMap_5 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
					class Var__tMap_5__Struct {
					}
					Var__tMap_5__Struct Var__tMap_5 = new Var__tMap_5__Struct();
// ###############################

// ###############################
// # Outputs initialization
					insertlogfilesStruct insertlogfiles_tmp = new insertlogfilesStruct();
// ###############################

					/**
					 * [tMap_5 begin ] stop
					 */

					/**
					 * [tJavaRow_5 begin ] start
					 */

					ok_Hash.put("tJavaRow_5", false);
					start_Hash.put("tJavaRow_5", System.currentTimeMillis());

					currentComponent = "tJavaRow_5";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row13");
					}

					int tos_count_tJavaRow_5 = 0;

					int nb_line_tJavaRow_5 = 0;

					/**
					 * [tJavaRow_5 begin ] stop
					 */

					/**
					 * [tFilterRow_4 begin ] start
					 */

					ok_Hash.put("tFilterRow_4", false);
					start_Hash.put("tFilterRow_4", System.currentTimeMillis());

					currentComponent = "tFilterRow_4";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
					}

					int tos_count_tFilterRow_4 = 0;

					int nb_line_tFilterRow_4 = 0;
					int nb_line_ok_tFilterRow_4 = 0;
					int nb_line_reject_tFilterRow_4 = 0;

					class Operator_tFilterRow_4 {
						private String sErrorMsg = "";
						private boolean bMatchFlag = true;
						private String sUnionFlag = "&&";

						public Operator_tFilterRow_4(String unionFlag) {
							sUnionFlag = unionFlag;
							bMatchFlag = "||".equals(unionFlag) ? false : true;
						}

						public String getErrorMsg() {
							if (sErrorMsg != null && sErrorMsg.length() > 1)
								return sErrorMsg.substring(1);
							else
								return null;
						}

						public boolean getMatchFlag() {
							return bMatchFlag;
						}

						public void matches(boolean partMatched, String reason) {
							// no need to care about the next judgement
							if ("||".equals(sUnionFlag) && bMatchFlag) {
								return;
							}

							if (!partMatched) {
								sErrorMsg += "|" + reason;
							}

							if ("||".equals(sUnionFlag))
								bMatchFlag = bMatchFlag || partMatched;
							else
								bMatchFlag = bMatchFlag && partMatched;
						}
					}

					/**
					 * [tFilterRow_4 begin ] stop
					 */

					/**
					 * [tFileInputExcel_6 begin ] start
					 */

					ok_Hash.put("tFileInputExcel_6", false);
					start_Hash.put("tFileInputExcel_6", System.currentTimeMillis());

					currentComponent = "tFileInputExcel_6";

					int tos_count_tFileInputExcel_6 = 0;

					final String decryptedPassword_tFileInputExcel_6 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:4zJiHhocwc7naxEUgs1+Z3J1BsngUtXNbjB3GA==");
					String password_tFileInputExcel_6 = decryptedPassword_tFileInputExcel_6;
					if (password_tFileInputExcel_6.isEmpty()) {
						password_tFileInputExcel_6 = null;
					}
					class RegexUtil_tFileInputExcel_6 {

						public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
								org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, String oneSheetName,
								boolean useRegex) {

							java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();

							if (useRegex) {// this part process the regex issue

								java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
								for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
									String sheetName = sheet.getSheetName();
									java.util.regex.Matcher matcher = pattern.matcher(sheetName);
									if (matcher.matches()) {
										if (sheet != null) {
											list.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet);
										}
									}
								}

							} else {
								org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
										.getSheet(oneSheetName);
								if (sheet != null) {
									list.add(sheet);
								}

							}

							return list;
						}

						public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
								org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, int index, boolean useRegex) {
							java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
							org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
									.getSheetAt(index);
							if (sheet != null) {
								list.add(sheet);
							}
							return list;
						}

					}
					RegexUtil_tFileInputExcel_6 regexUtil_tFileInputExcel_6 = new RegexUtil_tFileInputExcel_6();

					Object source_tFileInputExcel_6 = ((String) globalMap.get("tFileList_2_CURRENT_FILEPATH"));
					org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_6 = null;

					if (source_tFileInputExcel_6 instanceof String) {
						workbook_tFileInputExcel_6 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
								.create(new java.io.File((String) source_tFileInputExcel_6), password_tFileInputExcel_6,
										true);
					} else if (source_tFileInputExcel_6 instanceof java.io.InputStream) {
						workbook_tFileInputExcel_6 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
								.create((java.io.InputStream) source_tFileInputExcel_6, password_tFileInputExcel_6);
					} else {
						workbook_tFileInputExcel_6 = null;
						throw new java.lang.Exception(
								"The data source should be specified as Inputstream or File Path!");
					}
					try {

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_6 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						sheetList_tFileInputExcel_6.addAll(
								regexUtil_tFileInputExcel_6.getSheets(workbook_tFileInputExcel_6, "Log Files", false));
						if (sheetList_tFileInputExcel_6.size() <= 0) {
							throw new RuntimeException("Special sheets not exist!");
						}

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_6 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_6 : sheetList_tFileInputExcel_6) {
							if (sheet_FilterNull_tFileInputExcel_6 != null
									&& sheetList_FilterNull_tFileInputExcel_6.iterator() != null
									&& sheet_FilterNull_tFileInputExcel_6.iterator().hasNext()) {
								sheetList_FilterNull_tFileInputExcel_6.add(sheet_FilterNull_tFileInputExcel_6);
							}
						}
						sheetList_tFileInputExcel_6 = sheetList_FilterNull_tFileInputExcel_6;
						if (sheetList_tFileInputExcel_6.size() > 0) {
							int nb_line_tFileInputExcel_6 = 0;

							int begin_line_tFileInputExcel_6 = 1;

							int footer_input_tFileInputExcel_6 = 0;

							int end_line_tFileInputExcel_6 = 0;
							for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_6 : sheetList_tFileInputExcel_6) {
								end_line_tFileInputExcel_6 += (sheet_tFileInputExcel_6.getLastRowNum() + 1);
							}
							end_line_tFileInputExcel_6 -= footer_input_tFileInputExcel_6;
							int limit_tFileInputExcel_6 = -1;
							int start_column_tFileInputExcel_6 = 1 - 1;
							int end_column_tFileInputExcel_6 = -1;

							org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_6 = null;
							org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_6 = sheetList_tFileInputExcel_6
									.get(0);
							int rowCount_tFileInputExcel_6 = 0;
							int sheetIndex_tFileInputExcel_6 = 0;
							int currentRows_tFileInputExcel_6 = (sheetList_tFileInputExcel_6.get(0).getLastRowNum()
									+ 1);

							// for the number format
							java.text.DecimalFormat df_tFileInputExcel_6 = new java.text.DecimalFormat(
									"#.####################################");
							char decimalChar_tFileInputExcel_6 = df_tFileInputExcel_6.getDecimalFormatSymbols()
									.getDecimalSeparator();

							for (int i_tFileInputExcel_6 = begin_line_tFileInputExcel_6; i_tFileInputExcel_6 < end_line_tFileInputExcel_6; i_tFileInputExcel_6++) {

								int emptyColumnCount_tFileInputExcel_6 = 0;

								if (limit_tFileInputExcel_6 != -1
										&& nb_line_tFileInputExcel_6 >= limit_tFileInputExcel_6) {
									break;
								}

								while (i_tFileInputExcel_6 >= rowCount_tFileInputExcel_6
										+ currentRows_tFileInputExcel_6) {
									rowCount_tFileInputExcel_6 += currentRows_tFileInputExcel_6;
									sheet_tFileInputExcel_6 = sheetList_tFileInputExcel_6
											.get(++sheetIndex_tFileInputExcel_6);
									currentRows_tFileInputExcel_6 = (sheet_tFileInputExcel_6.getLastRowNum() + 1);
								}
								globalMap.put("tFileInputExcel_6_CURRENT_SHEET",
										sheet_tFileInputExcel_6.getSheetName());
								if (rowCount_tFileInputExcel_6 <= i_tFileInputExcel_6) {
									row_tFileInputExcel_6 = sheet_tFileInputExcel_6
											.getRow(i_tFileInputExcel_6 - rowCount_tFileInputExcel_6);
								}
								row4 = null;
								int tempRowLength_tFileInputExcel_6 = 16;

								int columnIndex_tFileInputExcel_6 = 0;

								String[] temp_row_tFileInputExcel_6 = new String[tempRowLength_tFileInputExcel_6];
								int excel_end_column_tFileInputExcel_6;
								if (row_tFileInputExcel_6 == null) {
									excel_end_column_tFileInputExcel_6 = 0;
								} else {
									excel_end_column_tFileInputExcel_6 = row_tFileInputExcel_6.getLastCellNum();
								}
								int actual_end_column_tFileInputExcel_6;
								if (end_column_tFileInputExcel_6 == -1) {
									actual_end_column_tFileInputExcel_6 = excel_end_column_tFileInputExcel_6;
								} else {
									actual_end_column_tFileInputExcel_6 = end_column_tFileInputExcel_6 > excel_end_column_tFileInputExcel_6
											? excel_end_column_tFileInputExcel_6
											: end_column_tFileInputExcel_6;
								}
								org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_6 = null;
								for (int i = 0; i < tempRowLength_tFileInputExcel_6; i++) {
									if (i + start_column_tFileInputExcel_6 < actual_end_column_tFileInputExcel_6) {
										org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_6 = row_tFileInputExcel_6
												.getCell(i + start_column_tFileInputExcel_6);
										if (cell_tFileInputExcel_6 != null) {
											switch (cell_tFileInputExcel_6.getCellType()) {
											case STRING:
												temp_row_tFileInputExcel_6[i] = cell_tFileInputExcel_6
														.getRichStringCellValue().getString();
												break;
											case NUMERIC:
												if (org.apache.poi.ss.usermodel.DateUtil
														.isCellDateFormatted(cell_tFileInputExcel_6)) {
													temp_row_tFileInputExcel_6[i] = cell_tFileInputExcel_6
															.getDateCellValue().toString();
												} else {
													temp_row_tFileInputExcel_6[i] = df_tFileInputExcel_6
															.format(cell_tFileInputExcel_6.getNumericCellValue());
												}
												break;
											case BOOLEAN:
												temp_row_tFileInputExcel_6[i] = String
														.valueOf(cell_tFileInputExcel_6.getBooleanCellValue());
												break;
											case FORMULA:
												switch (cell_tFileInputExcel_6.getCachedFormulaResultType()) {
												case STRING:
													temp_row_tFileInputExcel_6[i] = cell_tFileInputExcel_6
															.getRichStringCellValue().getString();
													break;
												case NUMERIC:
													if (org.apache.poi.ss.usermodel.DateUtil
															.isCellDateFormatted(cell_tFileInputExcel_6)) {
														temp_row_tFileInputExcel_6[i] = cell_tFileInputExcel_6
																.getDateCellValue().toString();
													} else {
														ne_tFileInputExcel_6 = new org.apache.poi.ss.formula.eval.NumberEval(
																cell_tFileInputExcel_6.getNumericCellValue());
														temp_row_tFileInputExcel_6[i] = ne_tFileInputExcel_6
																.getStringValue();
													}
													break;
												case BOOLEAN:
													temp_row_tFileInputExcel_6[i] = String
															.valueOf(cell_tFileInputExcel_6.getBooleanCellValue());
													break;
												default:
													temp_row_tFileInputExcel_6[i] = "";
												}
												break;
											default:
												temp_row_tFileInputExcel_6[i] = "";
											}
										} else {
											temp_row_tFileInputExcel_6[i] = "";
										}

									} else {
										temp_row_tFileInputExcel_6[i] = "";
									}
								}
								boolean whetherReject_tFileInputExcel_6 = false;
								row4 = new row4Struct();
								int curColNum_tFileInputExcel_6 = -1;
								String curColName_tFileInputExcel_6 = "";
								try {
									columnIndex_tFileInputExcel_6 = 0;

									if (temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6].length() > 0) {
										curColNum_tFileInputExcel_6 = columnIndex_tFileInputExcel_6
												+ start_column_tFileInputExcel_6 + 1;
										curColName_tFileInputExcel_6 = "N__Ref";

										row4.N__Ref = temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6];
									} else {
										row4.N__Ref = null;
										emptyColumnCount_tFileInputExcel_6++;
									}
									columnIndex_tFileInputExcel_6 = 1;

									if (temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6].length() > 0) {
										curColNum_tFileInputExcel_6 = columnIndex_tFileInputExcel_6
												+ start_column_tFileInputExcel_6 + 1;
										curColName_tFileInputExcel_6 = "Ref";

										row4.Ref = temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6];
									} else {
										row4.Ref = null;
										emptyColumnCount_tFileInputExcel_6++;
									}
									columnIndex_tFileInputExcel_6 = 2;

									if (temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6].length() > 0) {
										curColNum_tFileInputExcel_6 = columnIndex_tFileInputExcel_6
												+ start_column_tFileInputExcel_6 + 1;
										curColName_tFileInputExcel_6 = "Etat";

										row4.Etat = temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6];
									} else {
										row4.Etat = null;
										emptyColumnCount_tFileInputExcel_6++;
									}
									columnIndex_tFileInputExcel_6 = 3;

									if (temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6].length() > 0) {
										curColNum_tFileInputExcel_6 = columnIndex_tFileInputExcel_6
												+ start_column_tFileInputExcel_6 + 1;
										curColName_tFileInputExcel_6 = "Ref__des_composants";

										row4.Ref__des_composants = temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6];
									} else {
										row4.Ref__des_composants = null;
										emptyColumnCount_tFileInputExcel_6++;
									}
									columnIndex_tFileInputExcel_6 = 4;

									if (temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6].length() > 0) {
										curColNum_tFileInputExcel_6 = columnIndex_tFileInputExcel_6
												+ start_column_tFileInputExcel_6 + 1;
										curColName_tFileInputExcel_6 = "RG_SG_si_Cluster";

										row4.RG_SG_si_Cluster = temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6];
									} else {
										row4.RG_SG_si_Cluster = null;
										emptyColumnCount_tFileInputExcel_6++;
									}
									columnIndex_tFileInputExcel_6 = 5;

									if (temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6].length() > 0) {
										curColNum_tFileInputExcel_6 = columnIndex_tFileInputExcel_6
												+ start_column_tFileInputExcel_6 + 1;
										curColName_tFileInputExcel_6 = "Logfile";

										row4.Logfile = temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6];
									} else {
										row4.Logfile = null;
										emptyColumnCount_tFileInputExcel_6++;
									}
									columnIndex_tFileInputExcel_6 = 6;

									if (temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6].length() > 0) {
										curColNum_tFileInputExcel_6 = columnIndex_tFileInputExcel_6
												+ start_column_tFileInputExcel_6 + 1;
										curColName_tFileInputExcel_6 = "Localisation";

										row4.Localisation = temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6];
									} else {
										row4.Localisation = null;
										emptyColumnCount_tFileInputExcel_6++;
									}
									columnIndex_tFileInputExcel_6 = 7;

									if (temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6].length() > 0) {
										curColNum_tFileInputExcel_6 = columnIndex_tFileInputExcel_6
												+ start_column_tFileInputExcel_6 + 1;
										curColName_tFileInputExcel_6 = "Description";

										row4.Description = temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6];
									} else {
										row4.Description = null;
										emptyColumnCount_tFileInputExcel_6++;
									}
									columnIndex_tFileInputExcel_6 = 8;

									if (temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6].length() > 0) {
										curColNum_tFileInputExcel_6 = columnIndex_tFileInputExcel_6
												+ start_column_tFileInputExcel_6 + 1;
										curColName_tFileInputExcel_6 = "Format_logfile";

										row4.Format_logfile = temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6];
									} else {
										row4.Format_logfile = null;
										emptyColumnCount_tFileInputExcel_6++;
									}
									columnIndex_tFileInputExcel_6 = 9;

									if (temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6].length() > 0) {
										curColNum_tFileInputExcel_6 = columnIndex_tFileInputExcel_6
												+ start_column_tFileInputExcel_6 + 1;
										curColName_tFileInputExcel_6 = "Separateur";

										row4.Separateur = temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6];
									} else {
										row4.Separateur = null;
										emptyColumnCount_tFileInputExcel_6++;
									}
									columnIndex_tFileInputExcel_6 = 10;

									if (temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6].length() > 0) {
										curColNum_tFileInputExcel_6 = columnIndex_tFileInputExcel_6
												+ start_column_tFileInputExcel_6 + 1;
										curColName_tFileInputExcel_6 = "Intervalle_de_polling";

										row4.Intervalle_de_polling = temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6];
									} else {
										row4.Intervalle_de_polling = null;
										emptyColumnCount_tFileInputExcel_6++;
									}
									columnIndex_tFileInputExcel_6 = 11;

									if (temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6].length() > 0) {
										curColNum_tFileInputExcel_6 = columnIndex_tFileInputExcel_6
												+ start_column_tFileInputExcel_6 + 1;
										curColName_tFileInputExcel_6 = "Monitored_By";

										row4.Monitored_By = temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6];
									} else {
										row4.Monitored_By = null;
										emptyColumnCount_tFileInputExcel_6++;
									}
									columnIndex_tFileInputExcel_6 = 12;

									if (temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6].length() > 0) {
										curColNum_tFileInputExcel_6 = columnIndex_tFileInputExcel_6
												+ start_column_tFileInputExcel_6 + 1;
										curColName_tFileInputExcel_6 = "Fourni_en_annexe";

										row4.Fourni_en_annexe = temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6];
									} else {
										row4.Fourni_en_annexe = null;
										emptyColumnCount_tFileInputExcel_6++;
									}
									columnIndex_tFileInputExcel_6 = 13;

									if (temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6].length() > 0) {
										curColNum_tFileInputExcel_6 = columnIndex_tFileInputExcel_6
												+ start_column_tFileInputExcel_6 + 1;
										curColName_tFileInputExcel_6 = "Ref__Service";

										row4.Ref__Service = temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6];
									} else {
										row4.Ref__Service = null;
										emptyColumnCount_tFileInputExcel_6++;
									}
									columnIndex_tFileInputExcel_6 = 14;

									if (temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6].length() > 0) {
										curColNum_tFileInputExcel_6 = columnIndex_tFileInputExcel_6
												+ start_column_tFileInputExcel_6 + 1;
										curColName_tFileInputExcel_6 = "Nom_Template";

										row4.Nom_Template = temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6];
									} else {
										row4.Nom_Template = null;
										emptyColumnCount_tFileInputExcel_6++;
									}
									columnIndex_tFileInputExcel_6 = 15;

									if (temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6].length() > 0) {
										curColNum_tFileInputExcel_6 = columnIndex_tFileInputExcel_6
												+ start_column_tFileInputExcel_6 + 1;
										curColName_tFileInputExcel_6 = "Log_Conditions";

										row4.Log_Conditions = temp_row_tFileInputExcel_6[columnIndex_tFileInputExcel_6];
									} else {
										row4.Log_Conditions = null;
										emptyColumnCount_tFileInputExcel_6++;
									}

									nb_line_tFileInputExcel_6++;

								} catch (java.lang.Exception e) {
									globalMap.put("tFileInputExcel_6_ERROR_MESSAGE", e.getMessage());
									whetherReject_tFileInputExcel_6 = true;
									System.err.println(e.getMessage());
									row4 = null;
								}

								/**
								 * [tFileInputExcel_6 begin ] stop
								 */

								/**
								 * [tFileInputExcel_6 main ] start
								 */

								currentComponent = "tFileInputExcel_6";

								tos_count_tFileInputExcel_6++;

								/**
								 * [tFileInputExcel_6 main ] stop
								 */

								/**
								 * [tFileInputExcel_6 process_data_begin ] start
								 */

								currentComponent = "tFileInputExcel_6";

								/**
								 * [tFileInputExcel_6 process_data_begin ] stop
								 */
// Start of branch "row4"
								if (row4 != null) {

									/**
									 * [tFilterRow_4 main ] start
									 */

									currentComponent = "tFilterRow_4";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "row4"

										);
									}

									row13 = null;
									Operator_tFilterRow_4 ope_tFilterRow_4 = new Operator_tFilterRow_4("&&");
									ope_tFilterRow_4.matches((row4.Ref != null), "Ref!=null failed");

									if (ope_tFilterRow_4.getMatchFlag()) {
										if (row13 == null) {
											row13 = new row13Struct();
										}
										row13.N__Ref = row4.N__Ref;
										row13.Ref = row4.Ref;
										row13.Etat = row4.Etat;
										row13.Ref__des_composants = row4.Ref__des_composants;
										row13.RG_SG_si_Cluster = row4.RG_SG_si_Cluster;
										row13.Logfile = row4.Logfile;
										row13.Localisation = row4.Localisation;
										row13.Description = row4.Description;
										row13.Format_logfile = row4.Format_logfile;
										row13.Separateur = row4.Separateur;
										row13.Intervalle_de_polling = row4.Intervalle_de_polling;
										row13.Monitored_By = row4.Monitored_By;
										row13.Fourni_en_annexe = row4.Fourni_en_annexe;
										row13.Ref__Service = row4.Ref__Service;
										row13.Nom_Template = row4.Nom_Template;
										row13.Log_Conditions = row4.Log_Conditions;
										nb_line_ok_tFilterRow_4++;
									} else {
										nb_line_reject_tFilterRow_4++;
									}

									nb_line_tFilterRow_4++;

									tos_count_tFilterRow_4++;

									/**
									 * [tFilterRow_4 main ] stop
									 */

									/**
									 * [tFilterRow_4 process_data_begin ] start
									 */

									currentComponent = "tFilterRow_4";

									/**
									 * [tFilterRow_4 process_data_begin ] stop
									 */
// Start of branch "row13"
									if (row13 != null) {

										/**
										 * [tJavaRow_5 main ] start
										 */

										currentComponent = "tJavaRow_5";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row13"

											);
										}

										String fileName = (String) source_tFileInputExcel_6;

// Extraire la partie "POSANET" du nom du fichier Excel
										String[] parts = fileName.split("_");
										String fileWord = parts[6];

// Récupérer la valeur de "N__Ref" et la convertir en String
										String rowRef = row13.N__Ref;

// Générer l'ID "EDB_POSANET_X"
										String id = "EDB_" + fileWord + "_LogFiles_" + rowRef;
//Code généré selon les schémas d'entrée et de sortie
										row22.N__Ref = id;
										row22.Ref = row13.Ref;
										row22.Etat = row13.Etat;
										row22.Ref__des_composants = row13.Ref__des_composants;
										row22.RG_SG_si_Cluster = row13.RG_SG_si_Cluster;
										row22.Logfile = row13.Logfile;
										row22.Localisation = row13.Localisation;
										row22.Description = row13.Description;
										row22.Format_logfile = row13.Format_logfile;
										row22.Separateur = row13.Separateur;
										row22.Intervalle_de_polling = row13.Intervalle_de_polling;
										row22.Monitored_By = row13.Monitored_By;
										row22.Fourni_en_annexe = row13.Fourni_en_annexe;
										row22.Ref__Service = row13.Ref__Service;
										row22.Nom_Template = row13.Nom_Template;
										row22.Log_Conditions = row13.Log_Conditions;

										nb_line_tJavaRow_5++;

										tos_count_tJavaRow_5++;

										/**
										 * [tJavaRow_5 main ] stop
										 */

										/**
										 * [tJavaRow_5 process_data_begin ] start
										 */

										currentComponent = "tJavaRow_5";

										/**
										 * [tJavaRow_5 process_data_begin ] stop
										 */

										/**
										 * [tMap_5 main ] start
										 */

										currentComponent = "tMap_5";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row22"

											);
										}

										boolean hasCasePrimitiveKeyWithNull_tMap_5 = false;

										// ###############################
										// # Input tables (lookups)
										boolean rejectedInnerJoin_tMap_5 = false;
										boolean mainRowRejected_tMap_5 = false;

										// ###############################
										{ // start of Var scope

											// ###############################
											// # Vars tables

											Var__tMap_5__Struct Var = Var__tMap_5;// ###############################
											// ###############################
											// # Output tables

											insertlogfiles = null;

// # Output table : 'insertlogfiles'
											insertlogfiles_tmp.id = row22.N__Ref;
											insertlogfiles_tmp.Ref = row22.Ref;
											insertlogfiles_tmp.Etat = row22.Etat;
											insertlogfiles_tmp.Ref_composant = row22.Ref__des_composants;
											insertlogfiles_tmp.RG_SG_si_Cluster = row22.RG_SG_si_Cluster;
											insertlogfiles_tmp.Logfile = row22.Logfile;
											insertlogfiles_tmp.Localisation = row22.Localisation;
											insertlogfiles_tmp.Description = row22.Description;
											insertlogfiles_tmp.Format_logfile = row22.Format_logfile;
											insertlogfiles_tmp.Separateur = row22.Separateur;
											insertlogfiles_tmp.Intervalle_de_polling = row22.Intervalle_de_polling;
											insertlogfiles_tmp.Monitored_By = row22.Monitored_By;
											insertlogfiles_tmp.Fourni_en_annexe = row22.Fourni_en_annexe;
											insertlogfiles_tmp.Ref_Service = row22.Ref__Service;
											insertlogfiles_tmp.Nom_Template = row22.Nom_Template;
											insertlogfiles_tmp.Log_Conditions = row22.Log_Conditions;
											insertlogfiles = insertlogfiles_tmp;
// ###############################

										} // end of Var scope

										rejectedInnerJoin_tMap_5 = false;

										tos_count_tMap_5++;

										/**
										 * [tMap_5 main ] stop
										 */

										/**
										 * [tMap_5 process_data_begin ] start
										 */

										currentComponent = "tMap_5";

										/**
										 * [tMap_5 process_data_begin ] stop
										 */
// Start of branch "insertlogfiles"
										if (insertlogfiles != null) {

											/**
											 * [tDBOutput_2 main ] start
											 */

											currentComponent = "tDBOutput_2";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "insertlogfiles"

												);
											}

											whetherReject_tDBOutput_2 = false;
											if (insertlogfiles.id == null) {
												pstmt_tDBOutput_2.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(1, insertlogfiles.id);
											}

											if (insertlogfiles.Ref == null) {
												pstmt_tDBOutput_2.setNull(2, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(2, insertlogfiles.Ref);
											}

											if (insertlogfiles.Etat == null) {
												pstmt_tDBOutput_2.setNull(3, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(3, insertlogfiles.Etat);
											}

											if (insertlogfiles.Ref_composant == null) {
												pstmt_tDBOutput_2.setNull(4, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(4, insertlogfiles.Ref_composant);
											}

											if (insertlogfiles.RG_SG_si_Cluster == null) {
												pstmt_tDBOutput_2.setNull(5, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(5, insertlogfiles.RG_SG_si_Cluster);
											}

											if (insertlogfiles.Logfile == null) {
												pstmt_tDBOutput_2.setNull(6, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(6, insertlogfiles.Logfile);
											}

											if (insertlogfiles.Localisation == null) {
												pstmt_tDBOutput_2.setNull(7, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(7, insertlogfiles.Localisation);
											}

											if (insertlogfiles.Description == null) {
												pstmt_tDBOutput_2.setNull(8, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(8, insertlogfiles.Description);
											}

											if (insertlogfiles.Format_logfile == null) {
												pstmt_tDBOutput_2.setNull(9, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(9, insertlogfiles.Format_logfile);
											}

											if (insertlogfiles.Separateur == null) {
												pstmt_tDBOutput_2.setNull(10, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(10, insertlogfiles.Separateur);
											}

											if (insertlogfiles.Intervalle_de_polling == null) {
												pstmt_tDBOutput_2.setNull(11, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(11, insertlogfiles.Intervalle_de_polling);
											}

											if (insertlogfiles.Monitored_By == null) {
												pstmt_tDBOutput_2.setNull(12, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(12, insertlogfiles.Monitored_By);
											}

											if (insertlogfiles.Fourni_en_annexe == null) {
												pstmt_tDBOutput_2.setNull(13, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(13, insertlogfiles.Fourni_en_annexe);
											}

											if (insertlogfiles.Ref_Service == null) {
												pstmt_tDBOutput_2.setNull(14, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(14, insertlogfiles.Ref_Service);
											}

											if (insertlogfiles.Nom_Template == null) {
												pstmt_tDBOutput_2.setNull(15, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(15, insertlogfiles.Nom_Template);
											}

											if (insertlogfiles.Log_Conditions == null) {
												pstmt_tDBOutput_2.setNull(16, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(16, insertlogfiles.Log_Conditions);
											}

											try {
												nb_line_tDBOutput_2++;
												int processedCount_tDBOutput_2 = pstmt_tDBOutput_2.executeUpdate();
												insertedCount_tDBOutput_2 += processedCount_tDBOutput_2;
												rowsToCommitCount_tDBOutput_2 += processedCount_tDBOutput_2;
											} catch (java.lang.Exception e) {
												globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());
												whetherReject_tDBOutput_2 = true;
												System.err.print(e.getMessage());
											}
											commitCounter_tDBOutput_2++;

											if (commitEvery_tDBOutput_2 <= commitCounter_tDBOutput_2) {

												if (rowsToCommitCount_tDBOutput_2 != 0) {
												}
												conn_tDBOutput_2.commit();
												if (rowsToCommitCount_tDBOutput_2 != 0) {
													rowsToCommitCount_tDBOutput_2 = 0;
												}
												commitCounter_tDBOutput_2 = 0;

											}

											tos_count_tDBOutput_2++;

											/**
											 * [tDBOutput_2 main ] stop
											 */

											/**
											 * [tDBOutput_2 process_data_begin ] start
											 */

											currentComponent = "tDBOutput_2";

											/**
											 * [tDBOutput_2 process_data_begin ] stop
											 */

											/**
											 * [tDBOutput_2 process_data_end ] start
											 */

											currentComponent = "tDBOutput_2";

											/**
											 * [tDBOutput_2 process_data_end ] stop
											 */

										} // End of branch "insertlogfiles"

										/**
										 * [tMap_5 process_data_end ] start
										 */

										currentComponent = "tMap_5";

										/**
										 * [tMap_5 process_data_end ] stop
										 */

										/**
										 * [tJavaRow_5 process_data_end ] start
										 */

										currentComponent = "tJavaRow_5";

										/**
										 * [tJavaRow_5 process_data_end ] stop
										 */

									} // End of branch "row13"

									/**
									 * [tFilterRow_4 process_data_end ] start
									 */

									currentComponent = "tFilterRow_4";

									/**
									 * [tFilterRow_4 process_data_end ] stop
									 */

								} // End of branch "row4"

								/**
								 * [tFileInputExcel_6 process_data_end ] start
								 */

								currentComponent = "tFileInputExcel_6";

								/**
								 * [tFileInputExcel_6 process_data_end ] stop
								 */

								/**
								 * [tFileInputExcel_6 end ] start
								 */

								currentComponent = "tFileInputExcel_6";

							}

							globalMap.put("tFileInputExcel_6_NB_LINE", nb_line_tFileInputExcel_6);

						}

					} finally {

						if (!(source_tFileInputExcel_6 instanceof java.io.InputStream)) {
							workbook_tFileInputExcel_6.getPackage().revert();
						}

					}

					ok_Hash.put("tFileInputExcel_6", true);
					end_Hash.put("tFileInputExcel_6", System.currentTimeMillis());

					/**
					 * [tFileInputExcel_6 end ] stop
					 */

					/**
					 * [tFilterRow_4 end ] start
					 */

					currentComponent = "tFilterRow_4";

					globalMap.put("tFilterRow_4_NB_LINE", nb_line_tFilterRow_4);
					globalMap.put("tFilterRow_4_NB_LINE_OK", nb_line_ok_tFilterRow_4);
					globalMap.put("tFilterRow_4_NB_LINE_REJECT", nb_line_reject_tFilterRow_4);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
					}

					ok_Hash.put("tFilterRow_4", true);
					end_Hash.put("tFilterRow_4", System.currentTimeMillis());

					/**
					 * [tFilterRow_4 end ] stop
					 */

					/**
					 * [tJavaRow_5 end ] start
					 */

					currentComponent = "tJavaRow_5";

					globalMap.put("tJavaRow_5_NB_LINE", nb_line_tJavaRow_5);
					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row13");
					}

					ok_Hash.put("tJavaRow_5", true);
					end_Hash.put("tJavaRow_5", System.currentTimeMillis());

					/**
					 * [tJavaRow_5 end ] stop
					 */

					/**
					 * [tMap_5 end ] start
					 */

					currentComponent = "tMap_5";

// ###############################
// # Lookup hashes releasing
// ###############################      

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row22");
					}

					ok_Hash.put("tMap_5", true);
					end_Hash.put("tMap_5", System.currentTimeMillis());

					/**
					 * [tMap_5 end ] stop
					 */

					/**
					 * [tDBOutput_2 end ] start
					 */

					currentComponent = "tDBOutput_2";

					if (pstmt_tDBOutput_2 != null) {

						pstmt_tDBOutput_2.close();
						resourceMap.remove("pstmt_tDBOutput_2");

					}
					resourceMap.put("statementClosed_tDBOutput_2", true);
					if (commitCounter_tDBOutput_2 > 0 && rowsToCommitCount_tDBOutput_2 != 0) {

					}
					conn_tDBOutput_2.commit();
					if (commitCounter_tDBOutput_2 > 0 && rowsToCommitCount_tDBOutput_2 != 0) {

						rowsToCommitCount_tDBOutput_2 = 0;
					}
					commitCounter_tDBOutput_2 = 0;

					conn_tDBOutput_2.close();

					resourceMap.put("finish_tDBOutput_2", true);

					nb_line_deleted_tDBOutput_2 = nb_line_deleted_tDBOutput_2 + deletedCount_tDBOutput_2;
					nb_line_update_tDBOutput_2 = nb_line_update_tDBOutput_2 + updatedCount_tDBOutput_2;
					nb_line_inserted_tDBOutput_2 = nb_line_inserted_tDBOutput_2 + insertedCount_tDBOutput_2;
					nb_line_rejected_tDBOutput_2 = nb_line_rejected_tDBOutput_2 + rejectedCount_tDBOutput_2;

					globalMap.put("tDBOutput_2_NB_LINE", nb_line_tDBOutput_2);
					globalMap.put("tDBOutput_2_NB_LINE_UPDATED", nb_line_update_tDBOutput_2);
					globalMap.put("tDBOutput_2_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_2);
					globalMap.put("tDBOutput_2_NB_LINE_DELETED", nb_line_deleted_tDBOutput_2);
					globalMap.put("tDBOutput_2_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_2);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "insertlogfiles");
					}

					ok_Hash.put("tDBOutput_2", true);
					end_Hash.put("tDBOutput_2", System.currentTimeMillis());

					/**
					 * [tDBOutput_2 end ] stop
					 */

					if (execStat) {
						runStat.updateStatOnConnection("iterate5", 2, "exec" + NB_ITERATE_tFileInputExcel_6);
					}

					NB_ITERATE_tFileInputExcel_24++;

					if (execStat) {
						runStat.updateStatOnConnection("row14", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("insertscripts", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row23", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row5", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate6", 1, "exec" + NB_ITERATE_tFileInputExcel_24);
						// Thread.sleep(1000);
					}

					/**
					 * [tDBOutput_21 begin ] start
					 */

					ok_Hash.put("tDBOutput_21", false);
					start_Hash.put("tDBOutput_21", System.currentTimeMillis());

					currentComponent = "tDBOutput_21";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "insertscripts");
					}

					int tos_count_tDBOutput_21 = 0;

					int nb_line_tDBOutput_21 = 0;
					int nb_line_update_tDBOutput_21 = 0;
					int nb_line_inserted_tDBOutput_21 = 0;
					int nb_line_deleted_tDBOutput_21 = 0;
					int nb_line_rejected_tDBOutput_21 = 0;

					int deletedCount_tDBOutput_21 = 0;
					int updatedCount_tDBOutput_21 = 0;
					int insertedCount_tDBOutput_21 = 0;
					int rowsToCommitCount_tDBOutput_21 = 0;
					int rejectedCount_tDBOutput_21 = 0;

					String tableName_tDBOutput_21 = "scripts";
					boolean whetherReject_tDBOutput_21 = false;

					java.util.Calendar calendar_tDBOutput_21 = java.util.Calendar.getInstance();
					calendar_tDBOutput_21.set(1, 0, 1, 0, 0, 0);
					long year1_tDBOutput_21 = calendar_tDBOutput_21.getTime().getTime();
					calendar_tDBOutput_21.set(10000, 0, 1, 0, 0, 0);
					long year10000_tDBOutput_21 = calendar_tDBOutput_21.getTime().getTime();
					long date_tDBOutput_21;

					java.sql.Connection conn_tDBOutput_21 = null;

					String properties_tDBOutput_21 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
					if (properties_tDBOutput_21 == null || properties_tDBOutput_21.trim().length() == 0) {
						properties_tDBOutput_21 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
					} else {
						if (!properties_tDBOutput_21.contains("rewriteBatchedStatements=")) {
							properties_tDBOutput_21 += "&rewriteBatchedStatements=true";
						}

						if (!properties_tDBOutput_21.contains("allowLoadLocalInfile=")) {
							properties_tDBOutput_21 += "&allowLoadLocalInfile=true";
						}
					}

					String url_tDBOutput_21 = "jdbc:mariadb://" + "127.0.0.1" + ":" + "3306" + "/" + "ooredoo" + "?"
							+ properties_tDBOutput_21;

					String driverClass_tDBOutput_21 = "org.mariadb.jdbc.Driver";

					String dbUser_tDBOutput_21 = "root";

					final String decryptedPassword_tDBOutput_21 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:ZE93RO57Kz9X1ceVoUZ2p7N6dzTn/1OCui4TQQ==");

					String dbPwd_tDBOutput_21 = decryptedPassword_tDBOutput_21;
					java.lang.Class.forName(driverClass_tDBOutput_21);

					conn_tDBOutput_21 = java.sql.DriverManager.getConnection(url_tDBOutput_21, dbUser_tDBOutput_21,
							dbPwd_tDBOutput_21);

					resourceMap.put("conn_tDBOutput_21", conn_tDBOutput_21);
					conn_tDBOutput_21.setAutoCommit(false);
					int commitEvery_tDBOutput_21 = 10000;
					int commitCounter_tDBOutput_21 = 0;

					int count_tDBOutput_21 = 0;

					String insert_tDBOutput_21 = "INSERT IGNORE INTO `" + "scripts"
							+ "` (`id`,`Ref`,`Etat`,`Ref_composant`,`RG/SG_si_Cluster`,`script`,`Code_erreur`,`Cricite`,`Description`,`Instructions`,`Monitored_By`,`Ref_Service`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

					java.sql.PreparedStatement pstmt_tDBOutput_21 = conn_tDBOutput_21
							.prepareStatement(insert_tDBOutput_21);
					resourceMap.put("pstmt_tDBOutput_21", pstmt_tDBOutput_21);

					/**
					 * [tDBOutput_21 begin ] stop
					 */

					/**
					 * [tMap_6 begin ] start
					 */

					ok_Hash.put("tMap_6", false);
					start_Hash.put("tMap_6", System.currentTimeMillis());

					currentComponent = "tMap_6";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row23");
					}

					int tos_count_tMap_6 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
					class Var__tMap_6__Struct {
					}
					Var__tMap_6__Struct Var__tMap_6 = new Var__tMap_6__Struct();
// ###############################

// ###############################
// # Outputs initialization
					insertscriptsStruct insertscripts_tmp = new insertscriptsStruct();
// ###############################

					/**
					 * [tMap_6 begin ] stop
					 */

					/**
					 * [tJavaRow_6 begin ] start
					 */

					ok_Hash.put("tJavaRow_6", false);
					start_Hash.put("tJavaRow_6", System.currentTimeMillis());

					currentComponent = "tJavaRow_6";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row14");
					}

					int tos_count_tJavaRow_6 = 0;

					int nb_line_tJavaRow_6 = 0;

					/**
					 * [tJavaRow_6 begin ] stop
					 */

					/**
					 * [tFilterRow_5 begin ] start
					 */

					ok_Hash.put("tFilterRow_5", false);
					start_Hash.put("tFilterRow_5", System.currentTimeMillis());

					currentComponent = "tFilterRow_5";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row5");
					}

					int tos_count_tFilterRow_5 = 0;

					int nb_line_tFilterRow_5 = 0;
					int nb_line_ok_tFilterRow_5 = 0;
					int nb_line_reject_tFilterRow_5 = 0;

					class Operator_tFilterRow_5 {
						private String sErrorMsg = "";
						private boolean bMatchFlag = true;
						private String sUnionFlag = "&&";

						public Operator_tFilterRow_5(String unionFlag) {
							sUnionFlag = unionFlag;
							bMatchFlag = "||".equals(unionFlag) ? false : true;
						}

						public String getErrorMsg() {
							if (sErrorMsg != null && sErrorMsg.length() > 1)
								return sErrorMsg.substring(1);
							else
								return null;
						}

						public boolean getMatchFlag() {
							return bMatchFlag;
						}

						public void matches(boolean partMatched, String reason) {
							// no need to care about the next judgement
							if ("||".equals(sUnionFlag) && bMatchFlag) {
								return;
							}

							if (!partMatched) {
								sErrorMsg += "|" + reason;
							}

							if ("||".equals(sUnionFlag))
								bMatchFlag = bMatchFlag || partMatched;
							else
								bMatchFlag = bMatchFlag && partMatched;
						}
					}

					/**
					 * [tFilterRow_5 begin ] stop
					 */

					/**
					 * [tFileInputExcel_24 begin ] start
					 */

					ok_Hash.put("tFileInputExcel_24", false);
					start_Hash.put("tFileInputExcel_24", System.currentTimeMillis());

					currentComponent = "tFileInputExcel_24";

					int tos_count_tFileInputExcel_24 = 0;

					final String decryptedPassword_tFileInputExcel_24 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:u51VEBlsJR+RdRYyexgQiAXWnwM3MlnyJzVcdA==");
					String password_tFileInputExcel_24 = decryptedPassword_tFileInputExcel_24;
					if (password_tFileInputExcel_24.isEmpty()) {
						password_tFileInputExcel_24 = null;
					}
					class RegexUtil_tFileInputExcel_24 {

						public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
								org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, String oneSheetName,
								boolean useRegex) {

							java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();

							if (useRegex) {// this part process the regex issue

								java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
								for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
									String sheetName = sheet.getSheetName();
									java.util.regex.Matcher matcher = pattern.matcher(sheetName);
									if (matcher.matches()) {
										if (sheet != null) {
											list.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet);
										}
									}
								}

							} else {
								org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
										.getSheet(oneSheetName);
								if (sheet != null) {
									list.add(sheet);
								}

							}

							return list;
						}

						public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
								org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, int index, boolean useRegex) {
							java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
							org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
									.getSheetAt(index);
							if (sheet != null) {
								list.add(sheet);
							}
							return list;
						}

					}
					RegexUtil_tFileInputExcel_24 regexUtil_tFileInputExcel_24 = new RegexUtil_tFileInputExcel_24();

					Object source_tFileInputExcel_24 = ((String) globalMap.get("tFileList_2_CURRENT_FILEPATH"));
					org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_24 = null;

					if (source_tFileInputExcel_24 instanceof String) {
						workbook_tFileInputExcel_24 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
								.create(new java.io.File((String) source_tFileInputExcel_24),
										password_tFileInputExcel_24, true);
					} else if (source_tFileInputExcel_24 instanceof java.io.InputStream) {
						workbook_tFileInputExcel_24 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
								.create((java.io.InputStream) source_tFileInputExcel_24, password_tFileInputExcel_24);
					} else {
						workbook_tFileInputExcel_24 = null;
						throw new java.lang.Exception(
								"The data source should be specified as Inputstream or File Path!");
					}
					try {

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_24 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						sheetList_tFileInputExcel_24.addAll(
								regexUtil_tFileInputExcel_24.getSheets(workbook_tFileInputExcel_24, "Scripts", false));
						if (sheetList_tFileInputExcel_24.size() <= 0) {
							throw new RuntimeException("Special sheets not exist!");
						}

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_24 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_24 : sheetList_tFileInputExcel_24) {
							if (sheet_FilterNull_tFileInputExcel_24 != null
									&& sheetList_FilterNull_tFileInputExcel_24.iterator() != null
									&& sheet_FilterNull_tFileInputExcel_24.iterator().hasNext()) {
								sheetList_FilterNull_tFileInputExcel_24.add(sheet_FilterNull_tFileInputExcel_24);
							}
						}
						sheetList_tFileInputExcel_24 = sheetList_FilterNull_tFileInputExcel_24;
						if (sheetList_tFileInputExcel_24.size() > 0) {
							int nb_line_tFileInputExcel_24 = 0;

							int begin_line_tFileInputExcel_24 = 1;

							int footer_input_tFileInputExcel_24 = 0;

							int end_line_tFileInputExcel_24 = 0;
							for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_24 : sheetList_tFileInputExcel_24) {
								end_line_tFileInputExcel_24 += (sheet_tFileInputExcel_24.getLastRowNum() + 1);
							}
							end_line_tFileInputExcel_24 -= footer_input_tFileInputExcel_24;
							int limit_tFileInputExcel_24 = -1;
							int start_column_tFileInputExcel_24 = 1 - 1;
							int end_column_tFileInputExcel_24 = -1;

							org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_24 = null;
							org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_24 = sheetList_tFileInputExcel_24
									.get(0);
							int rowCount_tFileInputExcel_24 = 0;
							int sheetIndex_tFileInputExcel_24 = 0;
							int currentRows_tFileInputExcel_24 = (sheetList_tFileInputExcel_24.get(0).getLastRowNum()
									+ 1);

							// for the number format
							java.text.DecimalFormat df_tFileInputExcel_24 = new java.text.DecimalFormat(
									"#.####################################");
							char decimalChar_tFileInputExcel_24 = df_tFileInputExcel_24.getDecimalFormatSymbols()
									.getDecimalSeparator();

							for (int i_tFileInputExcel_24 = begin_line_tFileInputExcel_24; i_tFileInputExcel_24 < end_line_tFileInputExcel_24; i_tFileInputExcel_24++) {

								int emptyColumnCount_tFileInputExcel_24 = 0;

								if (limit_tFileInputExcel_24 != -1
										&& nb_line_tFileInputExcel_24 >= limit_tFileInputExcel_24) {
									break;
								}

								while (i_tFileInputExcel_24 >= rowCount_tFileInputExcel_24
										+ currentRows_tFileInputExcel_24) {
									rowCount_tFileInputExcel_24 += currentRows_tFileInputExcel_24;
									sheet_tFileInputExcel_24 = sheetList_tFileInputExcel_24
											.get(++sheetIndex_tFileInputExcel_24);
									currentRows_tFileInputExcel_24 = (sheet_tFileInputExcel_24.getLastRowNum() + 1);
								}
								globalMap.put("tFileInputExcel_24_CURRENT_SHEET",
										sheet_tFileInputExcel_24.getSheetName());
								if (rowCount_tFileInputExcel_24 <= i_tFileInputExcel_24) {
									row_tFileInputExcel_24 = sheet_tFileInputExcel_24
											.getRow(i_tFileInputExcel_24 - rowCount_tFileInputExcel_24);
								}
								row5 = null;
								int tempRowLength_tFileInputExcel_24 = 12;

								int columnIndex_tFileInputExcel_24 = 0;

								String[] temp_row_tFileInputExcel_24 = new String[tempRowLength_tFileInputExcel_24];
								int excel_end_column_tFileInputExcel_24;
								if (row_tFileInputExcel_24 == null) {
									excel_end_column_tFileInputExcel_24 = 0;
								} else {
									excel_end_column_tFileInputExcel_24 = row_tFileInputExcel_24.getLastCellNum();
								}
								int actual_end_column_tFileInputExcel_24;
								if (end_column_tFileInputExcel_24 == -1) {
									actual_end_column_tFileInputExcel_24 = excel_end_column_tFileInputExcel_24;
								} else {
									actual_end_column_tFileInputExcel_24 = end_column_tFileInputExcel_24 > excel_end_column_tFileInputExcel_24
											? excel_end_column_tFileInputExcel_24
											: end_column_tFileInputExcel_24;
								}
								org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_24 = null;
								for (int i = 0; i < tempRowLength_tFileInputExcel_24; i++) {
									if (i + start_column_tFileInputExcel_24 < actual_end_column_tFileInputExcel_24) {
										org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_24 = row_tFileInputExcel_24
												.getCell(i + start_column_tFileInputExcel_24);
										if (cell_tFileInputExcel_24 != null) {
											switch (cell_tFileInputExcel_24.getCellType()) {
											case STRING:
												temp_row_tFileInputExcel_24[i] = cell_tFileInputExcel_24
														.getRichStringCellValue().getString();
												break;
											case NUMERIC:
												if (org.apache.poi.ss.usermodel.DateUtil
														.isCellDateFormatted(cell_tFileInputExcel_24)) {
													temp_row_tFileInputExcel_24[i] = cell_tFileInputExcel_24
															.getDateCellValue().toString();
												} else {
													temp_row_tFileInputExcel_24[i] = df_tFileInputExcel_24
															.format(cell_tFileInputExcel_24.getNumericCellValue());
												}
												break;
											case BOOLEAN:
												temp_row_tFileInputExcel_24[i] = String
														.valueOf(cell_tFileInputExcel_24.getBooleanCellValue());
												break;
											case FORMULA:
												switch (cell_tFileInputExcel_24.getCachedFormulaResultType()) {
												case STRING:
													temp_row_tFileInputExcel_24[i] = cell_tFileInputExcel_24
															.getRichStringCellValue().getString();
													break;
												case NUMERIC:
													if (org.apache.poi.ss.usermodel.DateUtil
															.isCellDateFormatted(cell_tFileInputExcel_24)) {
														temp_row_tFileInputExcel_24[i] = cell_tFileInputExcel_24
																.getDateCellValue().toString();
													} else {
														ne_tFileInputExcel_24 = new org.apache.poi.ss.formula.eval.NumberEval(
																cell_tFileInputExcel_24.getNumericCellValue());
														temp_row_tFileInputExcel_24[i] = ne_tFileInputExcel_24
																.getStringValue();
													}
													break;
												case BOOLEAN:
													temp_row_tFileInputExcel_24[i] = String
															.valueOf(cell_tFileInputExcel_24.getBooleanCellValue());
													break;
												default:
													temp_row_tFileInputExcel_24[i] = "";
												}
												break;
											default:
												temp_row_tFileInputExcel_24[i] = "";
											}
										} else {
											temp_row_tFileInputExcel_24[i] = "";
										}

									} else {
										temp_row_tFileInputExcel_24[i] = "";
									}
								}
								boolean whetherReject_tFileInputExcel_24 = false;
								row5 = new row5Struct();
								int curColNum_tFileInputExcel_24 = -1;
								String curColName_tFileInputExcel_24 = "";
								try {
									columnIndex_tFileInputExcel_24 = 0;

									if (temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24].length() > 0) {
										curColNum_tFileInputExcel_24 = columnIndex_tFileInputExcel_24
												+ start_column_tFileInputExcel_24 + 1;
										curColName_tFileInputExcel_24 = "N__Ref";

										row5.N__Ref = temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24];
									} else {
										row5.N__Ref = null;
										emptyColumnCount_tFileInputExcel_24++;
									}
									columnIndex_tFileInputExcel_24 = 1;

									if (temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24].length() > 0) {
										curColNum_tFileInputExcel_24 = columnIndex_tFileInputExcel_24
												+ start_column_tFileInputExcel_24 + 1;
										curColName_tFileInputExcel_24 = "Ref";

										row5.Ref = temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24];
									} else {
										row5.Ref = null;
										emptyColumnCount_tFileInputExcel_24++;
									}
									columnIndex_tFileInputExcel_24 = 2;

									if (temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24].length() > 0) {
										curColNum_tFileInputExcel_24 = columnIndex_tFileInputExcel_24
												+ start_column_tFileInputExcel_24 + 1;
										curColName_tFileInputExcel_24 = "Etat";

										row5.Etat = temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24];
									} else {
										row5.Etat = null;
										emptyColumnCount_tFileInputExcel_24++;
									}
									columnIndex_tFileInputExcel_24 = 3;

									if (temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24].length() > 0) {
										curColNum_tFileInputExcel_24 = columnIndex_tFileInputExcel_24
												+ start_column_tFileInputExcel_24 + 1;
										curColName_tFileInputExcel_24 = "Ref__des_composants";

										row5.Ref__des_composants = temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24];
									} else {
										row5.Ref__des_composants = null;
										emptyColumnCount_tFileInputExcel_24++;
									}
									columnIndex_tFileInputExcel_24 = 4;

									if (temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24].length() > 0) {
										curColNum_tFileInputExcel_24 = columnIndex_tFileInputExcel_24
												+ start_column_tFileInputExcel_24 + 1;
										curColName_tFileInputExcel_24 = "RG_SG_si_Cluster";

										row5.RG_SG_si_Cluster = temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24];
									} else {
										row5.RG_SG_si_Cluster = null;
										emptyColumnCount_tFileInputExcel_24++;
									}
									columnIndex_tFileInputExcel_24 = 5;

									if (temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24].length() > 0) {
										curColNum_tFileInputExcel_24 = columnIndex_tFileInputExcel_24
												+ start_column_tFileInputExcel_24 + 1;
										curColName_tFileInputExcel_24 = "Script";

										row5.Script = temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24];
									} else {
										row5.Script = null;
										emptyColumnCount_tFileInputExcel_24++;
									}
									columnIndex_tFileInputExcel_24 = 6;

									if (temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24].length() > 0) {
										curColNum_tFileInputExcel_24 = columnIndex_tFileInputExcel_24
												+ start_column_tFileInputExcel_24 + 1;
										curColName_tFileInputExcel_24 = "Code_erreur";

										row5.Code_erreur = temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24];
									} else {
										row5.Code_erreur = null;
										emptyColumnCount_tFileInputExcel_24++;
									}
									columnIndex_tFileInputExcel_24 = 7;

									if (temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24].length() > 0) {
										curColNum_tFileInputExcel_24 = columnIndex_tFileInputExcel_24
												+ start_column_tFileInputExcel_24 + 1;
										curColName_tFileInputExcel_24 = "Criticite";

										row5.Criticite = temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24];
									} else {
										row5.Criticite = null;
										emptyColumnCount_tFileInputExcel_24++;
									}
									columnIndex_tFileInputExcel_24 = 8;

									if (temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24].length() > 0) {
										curColNum_tFileInputExcel_24 = columnIndex_tFileInputExcel_24
												+ start_column_tFileInputExcel_24 + 1;
										curColName_tFileInputExcel_24 = "Description";

										row5.Description = temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24];
									} else {
										row5.Description = null;
										emptyColumnCount_tFileInputExcel_24++;
									}
									columnIndex_tFileInputExcel_24 = 9;

									if (temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24].length() > 0) {
										curColNum_tFileInputExcel_24 = columnIndex_tFileInputExcel_24
												+ start_column_tFileInputExcel_24 + 1;
										curColName_tFileInputExcel_24 = "Instructions";

										row5.Instructions = temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24];
									} else {
										row5.Instructions = null;
										emptyColumnCount_tFileInputExcel_24++;
									}
									columnIndex_tFileInputExcel_24 = 10;

									if (temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24].length() > 0) {
										curColNum_tFileInputExcel_24 = columnIndex_tFileInputExcel_24
												+ start_column_tFileInputExcel_24 + 1;
										curColName_tFileInputExcel_24 = "Monitored_By";

										row5.Monitored_By = temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24];
									} else {
										row5.Monitored_By = null;
										emptyColumnCount_tFileInputExcel_24++;
									}
									columnIndex_tFileInputExcel_24 = 11;

									if (temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24].length() > 0) {
										curColNum_tFileInputExcel_24 = columnIndex_tFileInputExcel_24
												+ start_column_tFileInputExcel_24 + 1;
										curColName_tFileInputExcel_24 = "Ref__Service";

										row5.Ref__Service = temp_row_tFileInputExcel_24[columnIndex_tFileInputExcel_24];
									} else {
										row5.Ref__Service = null;
										emptyColumnCount_tFileInputExcel_24++;
									}

									nb_line_tFileInputExcel_24++;

								} catch (java.lang.Exception e) {
									globalMap.put("tFileInputExcel_24_ERROR_MESSAGE", e.getMessage());
									whetherReject_tFileInputExcel_24 = true;
									System.err.println(e.getMessage());
									row5 = null;
								}

								/**
								 * [tFileInputExcel_24 begin ] stop
								 */

								/**
								 * [tFileInputExcel_24 main ] start
								 */

								currentComponent = "tFileInputExcel_24";

								tos_count_tFileInputExcel_24++;

								/**
								 * [tFileInputExcel_24 main ] stop
								 */

								/**
								 * [tFileInputExcel_24 process_data_begin ] start
								 */

								currentComponent = "tFileInputExcel_24";

								/**
								 * [tFileInputExcel_24 process_data_begin ] stop
								 */
// Start of branch "row5"
								if (row5 != null) {

									/**
									 * [tFilterRow_5 main ] start
									 */

									currentComponent = "tFilterRow_5";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "row5"

										);
									}

									row14 = null;
									Operator_tFilterRow_5 ope_tFilterRow_5 = new Operator_tFilterRow_5("&&");
									ope_tFilterRow_5.matches((row5.Ref != null), "Ref!=null failed");

									if (ope_tFilterRow_5.getMatchFlag()) {
										if (row14 == null) {
											row14 = new row14Struct();
										}
										row14.N__Ref = row5.N__Ref;
										row14.Ref = row5.Ref;
										row14.Etat = row5.Etat;
										row14.Ref__des_composants = row5.Ref__des_composants;
										row14.RG_SG_si_Cluster = row5.RG_SG_si_Cluster;
										row14.Script = row5.Script;
										row14.Code_erreur = row5.Code_erreur;
										row14.Criticite = row5.Criticite;
										row14.Description = row5.Description;
										row14.Instructions = row5.Instructions;
										row14.Monitored_By = row5.Monitored_By;
										row14.Ref__Service = row5.Ref__Service;
										nb_line_ok_tFilterRow_5++;
									} else {
										nb_line_reject_tFilterRow_5++;
									}

									nb_line_tFilterRow_5++;

									tos_count_tFilterRow_5++;

									/**
									 * [tFilterRow_5 main ] stop
									 */

									/**
									 * [tFilterRow_5 process_data_begin ] start
									 */

									currentComponent = "tFilterRow_5";

									/**
									 * [tFilterRow_5 process_data_begin ] stop
									 */
// Start of branch "row14"
									if (row14 != null) {

										/**
										 * [tJavaRow_6 main ] start
										 */

										currentComponent = "tJavaRow_6";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row14"

											);
										}

										String fileName = (String) source_tFileInputExcel_24;

// Extraire la partie "POSANET" du nom du fichier Excel
										String[] parts = fileName.split("_");
										String fileWord = parts[6];

// Récupérer la valeur de "N__Ref" et la convertir en String
										String rowRef = row14.N__Ref;

// Générer l'ID "EDB_POSANET_X"
										String id = "EDB_" + fileWord + "_Scripts_" + rowRef;
//Code généré selon les schémas d'entrée et de sortie
										row23.N__Ref = id;
										row23.Ref = row14.Ref;
										row23.Etat = row14.Etat;
										row23.Ref__des_composants = row14.Ref__des_composants;
										row23.RG_SG_si_Cluster = row14.RG_SG_si_Cluster;
										row23.Script = row14.Script;
										row23.Code_erreur = row14.Code_erreur;
										row23.Criticite = row14.Criticite;
										row23.Description = row14.Description;
										row23.Instructions = row14.Instructions;
										row23.Monitored_By = row14.Monitored_By;
										row23.Ref__Service = row14.Ref__Service;

										nb_line_tJavaRow_6++;

										tos_count_tJavaRow_6++;

										/**
										 * [tJavaRow_6 main ] stop
										 */

										/**
										 * [tJavaRow_6 process_data_begin ] start
										 */

										currentComponent = "tJavaRow_6";

										/**
										 * [tJavaRow_6 process_data_begin ] stop
										 */

										/**
										 * [tMap_6 main ] start
										 */

										currentComponent = "tMap_6";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row23"

											);
										}

										boolean hasCasePrimitiveKeyWithNull_tMap_6 = false;

										// ###############################
										// # Input tables (lookups)
										boolean rejectedInnerJoin_tMap_6 = false;
										boolean mainRowRejected_tMap_6 = false;

										// ###############################
										{ // start of Var scope

											// ###############################
											// # Vars tables

											Var__tMap_6__Struct Var = Var__tMap_6;// ###############################
											// ###############################
											// # Output tables

											insertscripts = null;

// # Output table : 'insertscripts'
											insertscripts_tmp.id = row23.N__Ref;
											insertscripts_tmp.Ref = row23.Ref;
											insertscripts_tmp.Etat = row23.Etat;
											insertscripts_tmp.Ref_composant = row23.Ref__des_composants;
											insertscripts_tmp.RG_SG_si_Cluster = row23.RG_SG_si_Cluster;
											insertscripts_tmp.script = row23.Script;
											insertscripts_tmp.Code_erreur = row23.Code_erreur;
											insertscripts_tmp.Cricite = row23.Criticite;
											insertscripts_tmp.Description = row23.Description;
											insertscripts_tmp.Instructions = row23.Instructions;
											insertscripts_tmp.Monitored_By = row23.Monitored_By;
											insertscripts_tmp.Ref_Service = row23.Ref__Service;
											insertscripts = insertscripts_tmp;
// ###############################

										} // end of Var scope

										rejectedInnerJoin_tMap_6 = false;

										tos_count_tMap_6++;

										/**
										 * [tMap_6 main ] stop
										 */

										/**
										 * [tMap_6 process_data_begin ] start
										 */

										currentComponent = "tMap_6";

										/**
										 * [tMap_6 process_data_begin ] stop
										 */
// Start of branch "insertscripts"
										if (insertscripts != null) {

											/**
											 * [tDBOutput_21 main ] start
											 */

											currentComponent = "tDBOutput_21";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "insertscripts"

												);
											}

											whetherReject_tDBOutput_21 = false;
											if (insertscripts.id == null) {
												pstmt_tDBOutput_21.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_21.setString(1, insertscripts.id);
											}

											if (insertscripts.Ref == null) {
												pstmt_tDBOutput_21.setNull(2, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_21.setString(2, insertscripts.Ref);
											}

											if (insertscripts.Etat == null) {
												pstmt_tDBOutput_21.setNull(3, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_21.setString(3, insertscripts.Etat);
											}

											if (insertscripts.Ref_composant == null) {
												pstmt_tDBOutput_21.setNull(4, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_21.setString(4, insertscripts.Ref_composant);
											}

											if (insertscripts.RG_SG_si_Cluster == null) {
												pstmt_tDBOutput_21.setNull(5, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_21.setString(5, insertscripts.RG_SG_si_Cluster);
											}

											if (insertscripts.script == null) {
												pstmt_tDBOutput_21.setNull(6, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_21.setString(6, insertscripts.script);
											}

											if (insertscripts.Code_erreur == null) {
												pstmt_tDBOutput_21.setNull(7, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_21.setString(7, insertscripts.Code_erreur);
											}

											if (insertscripts.Cricite == null) {
												pstmt_tDBOutput_21.setNull(8, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_21.setString(8, insertscripts.Cricite);
											}

											if (insertscripts.Description == null) {
												pstmt_tDBOutput_21.setNull(9, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_21.setString(9, insertscripts.Description);
											}

											if (insertscripts.Instructions == null) {
												pstmt_tDBOutput_21.setNull(10, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_21.setString(10, insertscripts.Instructions);
											}

											if (insertscripts.Monitored_By == null) {
												pstmt_tDBOutput_21.setNull(11, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_21.setString(11, insertscripts.Monitored_By);
											}

											if (insertscripts.Ref_Service == null) {
												pstmt_tDBOutput_21.setNull(12, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_21.setString(12, insertscripts.Ref_Service);
											}

											try {
												nb_line_tDBOutput_21++;
												int processedCount_tDBOutput_21 = pstmt_tDBOutput_21.executeUpdate();
												insertedCount_tDBOutput_21 += processedCount_tDBOutput_21;
												rowsToCommitCount_tDBOutput_21 += processedCount_tDBOutput_21;
											} catch (java.lang.Exception e) {
												globalMap.put("tDBOutput_21_ERROR_MESSAGE", e.getMessage());
												whetherReject_tDBOutput_21 = true;
												System.err.print(e.getMessage());
											}
											commitCounter_tDBOutput_21++;

											if (commitEvery_tDBOutput_21 <= commitCounter_tDBOutput_21) {

												if (rowsToCommitCount_tDBOutput_21 != 0) {
												}
												conn_tDBOutput_21.commit();
												if (rowsToCommitCount_tDBOutput_21 != 0) {
													rowsToCommitCount_tDBOutput_21 = 0;
												}
												commitCounter_tDBOutput_21 = 0;

											}

											tos_count_tDBOutput_21++;

											/**
											 * [tDBOutput_21 main ] stop
											 */

											/**
											 * [tDBOutput_21 process_data_begin ] start
											 */

											currentComponent = "tDBOutput_21";

											/**
											 * [tDBOutput_21 process_data_begin ] stop
											 */

											/**
											 * [tDBOutput_21 process_data_end ] start
											 */

											currentComponent = "tDBOutput_21";

											/**
											 * [tDBOutput_21 process_data_end ] stop
											 */

										} // End of branch "insertscripts"

										/**
										 * [tMap_6 process_data_end ] start
										 */

										currentComponent = "tMap_6";

										/**
										 * [tMap_6 process_data_end ] stop
										 */

										/**
										 * [tJavaRow_6 process_data_end ] start
										 */

										currentComponent = "tJavaRow_6";

										/**
										 * [tJavaRow_6 process_data_end ] stop
										 */

									} // End of branch "row14"

									/**
									 * [tFilterRow_5 process_data_end ] start
									 */

									currentComponent = "tFilterRow_5";

									/**
									 * [tFilterRow_5 process_data_end ] stop
									 */

								} // End of branch "row5"

								/**
								 * [tFileInputExcel_24 process_data_end ] start
								 */

								currentComponent = "tFileInputExcel_24";

								/**
								 * [tFileInputExcel_24 process_data_end ] stop
								 */

								/**
								 * [tFileInputExcel_24 end ] start
								 */

								currentComponent = "tFileInputExcel_24";

							}

							globalMap.put("tFileInputExcel_24_NB_LINE", nb_line_tFileInputExcel_24);

						}

					} finally {

						if (!(source_tFileInputExcel_24 instanceof java.io.InputStream)) {
							workbook_tFileInputExcel_24.getPackage().revert();
						}

					}

					ok_Hash.put("tFileInputExcel_24", true);
					end_Hash.put("tFileInputExcel_24", System.currentTimeMillis());

					/**
					 * [tFileInputExcel_24 end ] stop
					 */

					/**
					 * [tFilterRow_5 end ] start
					 */

					currentComponent = "tFilterRow_5";

					globalMap.put("tFilterRow_5_NB_LINE", nb_line_tFilterRow_5);
					globalMap.put("tFilterRow_5_NB_LINE_OK", nb_line_ok_tFilterRow_5);
					globalMap.put("tFilterRow_5_NB_LINE_REJECT", nb_line_reject_tFilterRow_5);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row5");
					}

					ok_Hash.put("tFilterRow_5", true);
					end_Hash.put("tFilterRow_5", System.currentTimeMillis());

					/**
					 * [tFilterRow_5 end ] stop
					 */

					/**
					 * [tJavaRow_6 end ] start
					 */

					currentComponent = "tJavaRow_6";

					globalMap.put("tJavaRow_6_NB_LINE", nb_line_tJavaRow_6);
					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row14");
					}

					ok_Hash.put("tJavaRow_6", true);
					end_Hash.put("tJavaRow_6", System.currentTimeMillis());

					/**
					 * [tJavaRow_6 end ] stop
					 */

					/**
					 * [tMap_6 end ] start
					 */

					currentComponent = "tMap_6";

// ###############################
// # Lookup hashes releasing
// ###############################      

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row23");
					}

					ok_Hash.put("tMap_6", true);
					end_Hash.put("tMap_6", System.currentTimeMillis());

					/**
					 * [tMap_6 end ] stop
					 */

					/**
					 * [tDBOutput_21 end ] start
					 */

					currentComponent = "tDBOutput_21";

					if (pstmt_tDBOutput_21 != null) {

						pstmt_tDBOutput_21.close();
						resourceMap.remove("pstmt_tDBOutput_21");

					}
					resourceMap.put("statementClosed_tDBOutput_21", true);
					if (commitCounter_tDBOutput_21 > 0 && rowsToCommitCount_tDBOutput_21 != 0) {

					}
					conn_tDBOutput_21.commit();
					if (commitCounter_tDBOutput_21 > 0 && rowsToCommitCount_tDBOutput_21 != 0) {

						rowsToCommitCount_tDBOutput_21 = 0;
					}
					commitCounter_tDBOutput_21 = 0;

					conn_tDBOutput_21.close();

					resourceMap.put("finish_tDBOutput_21", true);

					nb_line_deleted_tDBOutput_21 = nb_line_deleted_tDBOutput_21 + deletedCount_tDBOutput_21;
					nb_line_update_tDBOutput_21 = nb_line_update_tDBOutput_21 + updatedCount_tDBOutput_21;
					nb_line_inserted_tDBOutput_21 = nb_line_inserted_tDBOutput_21 + insertedCount_tDBOutput_21;
					nb_line_rejected_tDBOutput_21 = nb_line_rejected_tDBOutput_21 + rejectedCount_tDBOutput_21;

					globalMap.put("tDBOutput_21_NB_LINE", nb_line_tDBOutput_21);
					globalMap.put("tDBOutput_21_NB_LINE_UPDATED", nb_line_update_tDBOutput_21);
					globalMap.put("tDBOutput_21_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_21);
					globalMap.put("tDBOutput_21_NB_LINE_DELETED", nb_line_deleted_tDBOutput_21);
					globalMap.put("tDBOutput_21_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_21);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "insertscripts");
					}

					ok_Hash.put("tDBOutput_21", true);
					end_Hash.put("tDBOutput_21", System.currentTimeMillis());

					/**
					 * [tDBOutput_21 end ] stop
					 */

					if (execStat) {
						runStat.updateStatOnConnection("iterate6", 2, "exec" + NB_ITERATE_tFileInputExcel_24);
					}

					NB_ITERATE_tFileInputExcel_48++;

					if (execStat) {
						runStat.updateStatOnConnection("row24", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row6", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("insrturls", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row15", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate7", 1, "exec" + NB_ITERATE_tFileInputExcel_48);
						// Thread.sleep(1000);
					}

					/**
					 * [tDBOutput_3 begin ] start
					 */

					ok_Hash.put("tDBOutput_3", false);
					start_Hash.put("tDBOutput_3", System.currentTimeMillis());

					currentComponent = "tDBOutput_3";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "insrturls");
					}

					int tos_count_tDBOutput_3 = 0;

					int nb_line_tDBOutput_3 = 0;
					int nb_line_update_tDBOutput_3 = 0;
					int nb_line_inserted_tDBOutput_3 = 0;
					int nb_line_deleted_tDBOutput_3 = 0;
					int nb_line_rejected_tDBOutput_3 = 0;

					int deletedCount_tDBOutput_3 = 0;
					int updatedCount_tDBOutput_3 = 0;
					int insertedCount_tDBOutput_3 = 0;
					int rowsToCommitCount_tDBOutput_3 = 0;
					int rejectedCount_tDBOutput_3 = 0;

					String tableName_tDBOutput_3 = "url";
					boolean whetherReject_tDBOutput_3 = false;

					java.util.Calendar calendar_tDBOutput_3 = java.util.Calendar.getInstance();
					calendar_tDBOutput_3.set(1, 0, 1, 0, 0, 0);
					long year1_tDBOutput_3 = calendar_tDBOutput_3.getTime().getTime();
					calendar_tDBOutput_3.set(10000, 0, 1, 0, 0, 0);
					long year10000_tDBOutput_3 = calendar_tDBOutput_3.getTime().getTime();
					long date_tDBOutput_3;

					java.sql.Connection conn_tDBOutput_3 = null;

					String properties_tDBOutput_3 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
					if (properties_tDBOutput_3 == null || properties_tDBOutput_3.trim().length() == 0) {
						properties_tDBOutput_3 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
					} else {
						if (!properties_tDBOutput_3.contains("rewriteBatchedStatements=")) {
							properties_tDBOutput_3 += "&rewriteBatchedStatements=true";
						}

						if (!properties_tDBOutput_3.contains("allowLoadLocalInfile=")) {
							properties_tDBOutput_3 += "&allowLoadLocalInfile=true";
						}
					}

					String url_tDBOutput_3 = "jdbc:mariadb://" + "127.0.0.1" + ":" + "3306" + "/" + "ooredoo" + "?"
							+ properties_tDBOutput_3;

					String driverClass_tDBOutput_3 = "org.mariadb.jdbc.Driver";

					String dbUser_tDBOutput_3 = "root";

					final String decryptedPassword_tDBOutput_3 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:f6kjo1K469rJKxW4TcjXfN/srzx/PQR8r/aWng==");

					String dbPwd_tDBOutput_3 = decryptedPassword_tDBOutput_3;
					java.lang.Class.forName(driverClass_tDBOutput_3);

					conn_tDBOutput_3 = java.sql.DriverManager.getConnection(url_tDBOutput_3, dbUser_tDBOutput_3,
							dbPwd_tDBOutput_3);

					resourceMap.put("conn_tDBOutput_3", conn_tDBOutput_3);
					conn_tDBOutput_3.setAutoCommit(false);
					int commitEvery_tDBOutput_3 = 10000;
					int commitCounter_tDBOutput_3 = 0;

					int count_tDBOutput_3 = 0;

					String insert_tDBOutput_3 = "INSERT IGNORE INTO `" + "url"
							+ "` (`id`,`Ref`,`Etat`,`Ref_composant`,`RG/SG_si_Cluster`,`URL`,`Perform_action`,`Cricite`,`Message_alarme`,`Instructions`,`Intervalle_de_polling`,`Ref_Service`,`Objet`,`Monitored_by`,`Nom_Template`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

					java.sql.PreparedStatement pstmt_tDBOutput_3 = conn_tDBOutput_3
							.prepareStatement(insert_tDBOutput_3);
					resourceMap.put("pstmt_tDBOutput_3", pstmt_tDBOutput_3);

					/**
					 * [tDBOutput_3 begin ] stop
					 */

					/**
					 * [tMap_7 begin ] start
					 */

					ok_Hash.put("tMap_7", false);
					start_Hash.put("tMap_7", System.currentTimeMillis());

					currentComponent = "tMap_7";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row24");
					}

					int tos_count_tMap_7 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
					class Var__tMap_7__Struct {
					}
					Var__tMap_7__Struct Var__tMap_7 = new Var__tMap_7__Struct();
// ###############################

// ###############################
// # Outputs initialization
					insrturlsStruct insrturls_tmp = new insrturlsStruct();
// ###############################

					/**
					 * [tMap_7 begin ] stop
					 */

					/**
					 * [tJavaRow_7 begin ] start
					 */

					ok_Hash.put("tJavaRow_7", false);
					start_Hash.put("tJavaRow_7", System.currentTimeMillis());

					currentComponent = "tJavaRow_7";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row15");
					}

					int tos_count_tJavaRow_7 = 0;

					int nb_line_tJavaRow_7 = 0;

					/**
					 * [tJavaRow_7 begin ] stop
					 */

					/**
					 * [tFilterRow_6 begin ] start
					 */

					ok_Hash.put("tFilterRow_6", false);
					start_Hash.put("tFilterRow_6", System.currentTimeMillis());

					currentComponent = "tFilterRow_6";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row6");
					}

					int tos_count_tFilterRow_6 = 0;

					int nb_line_tFilterRow_6 = 0;
					int nb_line_ok_tFilterRow_6 = 0;
					int nb_line_reject_tFilterRow_6 = 0;

					class Operator_tFilterRow_6 {
						private String sErrorMsg = "";
						private boolean bMatchFlag = true;
						private String sUnionFlag = "&&";

						public Operator_tFilterRow_6(String unionFlag) {
							sUnionFlag = unionFlag;
							bMatchFlag = "||".equals(unionFlag) ? false : true;
						}

						public String getErrorMsg() {
							if (sErrorMsg != null && sErrorMsg.length() > 1)
								return sErrorMsg.substring(1);
							else
								return null;
						}

						public boolean getMatchFlag() {
							return bMatchFlag;
						}

						public void matches(boolean partMatched, String reason) {
							// no need to care about the next judgement
							if ("||".equals(sUnionFlag) && bMatchFlag) {
								return;
							}

							if (!partMatched) {
								sErrorMsg += "|" + reason;
							}

							if ("||".equals(sUnionFlag))
								bMatchFlag = bMatchFlag || partMatched;
							else
								bMatchFlag = bMatchFlag && partMatched;
						}
					}

					/**
					 * [tFilterRow_6 begin ] stop
					 */

					/**
					 * [tFileInputExcel_48 begin ] start
					 */

					ok_Hash.put("tFileInputExcel_48", false);
					start_Hash.put("tFileInputExcel_48", System.currentTimeMillis());

					currentComponent = "tFileInputExcel_48";

					int tos_count_tFileInputExcel_48 = 0;

					final String decryptedPassword_tFileInputExcel_48 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:3ya6GHFWFPw4x1xImtZdpbTsFNt+1+TSSxq4Vw==");
					String password_tFileInputExcel_48 = decryptedPassword_tFileInputExcel_48;
					if (password_tFileInputExcel_48.isEmpty()) {
						password_tFileInputExcel_48 = null;
					}
					class RegexUtil_tFileInputExcel_48 {

						public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
								org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, String oneSheetName,
								boolean useRegex) {

							java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();

							if (useRegex) {// this part process the regex issue

								java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
								for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
									String sheetName = sheet.getSheetName();
									java.util.regex.Matcher matcher = pattern.matcher(sheetName);
									if (matcher.matches()) {
										if (sheet != null) {
											list.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet);
										}
									}
								}

							} else {
								org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
										.getSheet(oneSheetName);
								if (sheet != null) {
									list.add(sheet);
								}

							}

							return list;
						}

						public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
								org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, int index, boolean useRegex) {
							java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
							org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
									.getSheetAt(index);
							if (sheet != null) {
								list.add(sheet);
							}
							return list;
						}

					}
					RegexUtil_tFileInputExcel_48 regexUtil_tFileInputExcel_48 = new RegexUtil_tFileInputExcel_48();

					Object source_tFileInputExcel_48 = ((String) globalMap.get("tFileList_2_CURRENT_FILEPATH"));
					org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_48 = null;

					if (source_tFileInputExcel_48 instanceof String) {
						workbook_tFileInputExcel_48 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
								.create(new java.io.File((String) source_tFileInputExcel_48),
										password_tFileInputExcel_48, true);
					} else if (source_tFileInputExcel_48 instanceof java.io.InputStream) {
						workbook_tFileInputExcel_48 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
								.create((java.io.InputStream) source_tFileInputExcel_48, password_tFileInputExcel_48);
					} else {
						workbook_tFileInputExcel_48 = null;
						throw new java.lang.Exception(
								"The data source should be specified as Inputstream or File Path!");
					}
					try {

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_48 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						sheetList_tFileInputExcel_48.addAll(
								regexUtil_tFileInputExcel_48.getSheets(workbook_tFileInputExcel_48, "URL", false));
						if (sheetList_tFileInputExcel_48.size() <= 0) {
							throw new RuntimeException("Special sheets not exist!");
						}

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_48 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_48 : sheetList_tFileInputExcel_48) {
							if (sheet_FilterNull_tFileInputExcel_48 != null
									&& sheetList_FilterNull_tFileInputExcel_48.iterator() != null
									&& sheet_FilterNull_tFileInputExcel_48.iterator().hasNext()) {
								sheetList_FilterNull_tFileInputExcel_48.add(sheet_FilterNull_tFileInputExcel_48);
							}
						}
						sheetList_tFileInputExcel_48 = sheetList_FilterNull_tFileInputExcel_48;
						if (sheetList_tFileInputExcel_48.size() > 0) {
							int nb_line_tFileInputExcel_48 = 0;

							int begin_line_tFileInputExcel_48 = 1;

							int footer_input_tFileInputExcel_48 = 0;

							int end_line_tFileInputExcel_48 = 0;
							for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_48 : sheetList_tFileInputExcel_48) {
								end_line_tFileInputExcel_48 += (sheet_tFileInputExcel_48.getLastRowNum() + 1);
							}
							end_line_tFileInputExcel_48 -= footer_input_tFileInputExcel_48;
							int limit_tFileInputExcel_48 = -1;
							int start_column_tFileInputExcel_48 = 1 - 1;
							int end_column_tFileInputExcel_48 = -1;

							org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_48 = null;
							org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_48 = sheetList_tFileInputExcel_48
									.get(0);
							int rowCount_tFileInputExcel_48 = 0;
							int sheetIndex_tFileInputExcel_48 = 0;
							int currentRows_tFileInputExcel_48 = (sheetList_tFileInputExcel_48.get(0).getLastRowNum()
									+ 1);

							// for the number format
							java.text.DecimalFormat df_tFileInputExcel_48 = new java.text.DecimalFormat(
									"#.####################################");
							char decimalChar_tFileInputExcel_48 = df_tFileInputExcel_48.getDecimalFormatSymbols()
									.getDecimalSeparator();

							for (int i_tFileInputExcel_48 = begin_line_tFileInputExcel_48; i_tFileInputExcel_48 < end_line_tFileInputExcel_48; i_tFileInputExcel_48++) {

								int emptyColumnCount_tFileInputExcel_48 = 0;

								if (limit_tFileInputExcel_48 != -1
										&& nb_line_tFileInputExcel_48 >= limit_tFileInputExcel_48) {
									break;
								}

								while (i_tFileInputExcel_48 >= rowCount_tFileInputExcel_48
										+ currentRows_tFileInputExcel_48) {
									rowCount_tFileInputExcel_48 += currentRows_tFileInputExcel_48;
									sheet_tFileInputExcel_48 = sheetList_tFileInputExcel_48
											.get(++sheetIndex_tFileInputExcel_48);
									currentRows_tFileInputExcel_48 = (sheet_tFileInputExcel_48.getLastRowNum() + 1);
								}
								globalMap.put("tFileInputExcel_48_CURRENT_SHEET",
										sheet_tFileInputExcel_48.getSheetName());
								if (rowCount_tFileInputExcel_48 <= i_tFileInputExcel_48) {
									row_tFileInputExcel_48 = sheet_tFileInputExcel_48
											.getRow(i_tFileInputExcel_48 - rowCount_tFileInputExcel_48);
								}
								row6 = null;
								int tempRowLength_tFileInputExcel_48 = 15;

								int columnIndex_tFileInputExcel_48 = 0;

								String[] temp_row_tFileInputExcel_48 = new String[tempRowLength_tFileInputExcel_48];
								int excel_end_column_tFileInputExcel_48;
								if (row_tFileInputExcel_48 == null) {
									excel_end_column_tFileInputExcel_48 = 0;
								} else {
									excel_end_column_tFileInputExcel_48 = row_tFileInputExcel_48.getLastCellNum();
								}
								int actual_end_column_tFileInputExcel_48;
								if (end_column_tFileInputExcel_48 == -1) {
									actual_end_column_tFileInputExcel_48 = excel_end_column_tFileInputExcel_48;
								} else {
									actual_end_column_tFileInputExcel_48 = end_column_tFileInputExcel_48 > excel_end_column_tFileInputExcel_48
											? excel_end_column_tFileInputExcel_48
											: end_column_tFileInputExcel_48;
								}
								org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_48 = null;
								for (int i = 0; i < tempRowLength_tFileInputExcel_48; i++) {
									if (i + start_column_tFileInputExcel_48 < actual_end_column_tFileInputExcel_48) {
										org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_48 = row_tFileInputExcel_48
												.getCell(i + start_column_tFileInputExcel_48);
										if (cell_tFileInputExcel_48 != null) {
											switch (cell_tFileInputExcel_48.getCellType()) {
											case STRING:
												temp_row_tFileInputExcel_48[i] = cell_tFileInputExcel_48
														.getRichStringCellValue().getString();
												break;
											case NUMERIC:
												if (org.apache.poi.ss.usermodel.DateUtil
														.isCellDateFormatted(cell_tFileInputExcel_48)) {
													temp_row_tFileInputExcel_48[i] = cell_tFileInputExcel_48
															.getDateCellValue().toString();
												} else {
													temp_row_tFileInputExcel_48[i] = df_tFileInputExcel_48
															.format(cell_tFileInputExcel_48.getNumericCellValue());
												}
												break;
											case BOOLEAN:
												temp_row_tFileInputExcel_48[i] = String
														.valueOf(cell_tFileInputExcel_48.getBooleanCellValue());
												break;
											case FORMULA:
												switch (cell_tFileInputExcel_48.getCachedFormulaResultType()) {
												case STRING:
													temp_row_tFileInputExcel_48[i] = cell_tFileInputExcel_48
															.getRichStringCellValue().getString();
													break;
												case NUMERIC:
													if (org.apache.poi.ss.usermodel.DateUtil
															.isCellDateFormatted(cell_tFileInputExcel_48)) {
														temp_row_tFileInputExcel_48[i] = cell_tFileInputExcel_48
																.getDateCellValue().toString();
													} else {
														ne_tFileInputExcel_48 = new org.apache.poi.ss.formula.eval.NumberEval(
																cell_tFileInputExcel_48.getNumericCellValue());
														temp_row_tFileInputExcel_48[i] = ne_tFileInputExcel_48
																.getStringValue();
													}
													break;
												case BOOLEAN:
													temp_row_tFileInputExcel_48[i] = String
															.valueOf(cell_tFileInputExcel_48.getBooleanCellValue());
													break;
												default:
													temp_row_tFileInputExcel_48[i] = "";
												}
												break;
											default:
												temp_row_tFileInputExcel_48[i] = "";
											}
										} else {
											temp_row_tFileInputExcel_48[i] = "";
										}

									} else {
										temp_row_tFileInputExcel_48[i] = "";
									}
								}
								boolean whetherReject_tFileInputExcel_48 = false;
								row6 = new row6Struct();
								int curColNum_tFileInputExcel_48 = -1;
								String curColName_tFileInputExcel_48 = "";
								try {
									columnIndex_tFileInputExcel_48 = 0;

									if (temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48].length() > 0) {
										curColNum_tFileInputExcel_48 = columnIndex_tFileInputExcel_48
												+ start_column_tFileInputExcel_48 + 1;
										curColName_tFileInputExcel_48 = "N__Ref";

										row6.N__Ref = temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48];
									} else {
										row6.N__Ref = null;
										emptyColumnCount_tFileInputExcel_48++;
									}
									columnIndex_tFileInputExcel_48 = 1;

									if (temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48].length() > 0) {
										curColNum_tFileInputExcel_48 = columnIndex_tFileInputExcel_48
												+ start_column_tFileInputExcel_48 + 1;
										curColName_tFileInputExcel_48 = "Ref";

										row6.Ref = temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48];
									} else {
										row6.Ref = null;
										emptyColumnCount_tFileInputExcel_48++;
									}
									columnIndex_tFileInputExcel_48 = 2;

									if (temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48].length() > 0) {
										curColNum_tFileInputExcel_48 = columnIndex_tFileInputExcel_48
												+ start_column_tFileInputExcel_48 + 1;
										curColName_tFileInputExcel_48 = "Etat";

										row6.Etat = temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48];
									} else {
										row6.Etat = null;
										emptyColumnCount_tFileInputExcel_48++;
									}
									columnIndex_tFileInputExcel_48 = 3;

									if (temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48].length() > 0) {
										curColNum_tFileInputExcel_48 = columnIndex_tFileInputExcel_48
												+ start_column_tFileInputExcel_48 + 1;
										curColName_tFileInputExcel_48 = "Ref__des_composants";

										row6.Ref__des_composants = temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48];
									} else {
										row6.Ref__des_composants = null;
										emptyColumnCount_tFileInputExcel_48++;
									}
									columnIndex_tFileInputExcel_48 = 4;

									if (temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48].length() > 0) {
										curColNum_tFileInputExcel_48 = columnIndex_tFileInputExcel_48
												+ start_column_tFileInputExcel_48 + 1;
										curColName_tFileInputExcel_48 = "RG_SG_si_Cluster";

										row6.RG_SG_si_Cluster = temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48];
									} else {
										row6.RG_SG_si_Cluster = null;
										emptyColumnCount_tFileInputExcel_48++;
									}
									columnIndex_tFileInputExcel_48 = 5;

									if (temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48].length() > 0) {
										curColNum_tFileInputExcel_48 = columnIndex_tFileInputExcel_48
												+ start_column_tFileInputExcel_48 + 1;
										curColName_tFileInputExcel_48 = "URL";

										row6.URL = temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48];
									} else {
										row6.URL = null;
										emptyColumnCount_tFileInputExcel_48++;
									}
									columnIndex_tFileInputExcel_48 = 6;

									if (temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48].length() > 0) {
										curColNum_tFileInputExcel_48 = columnIndex_tFileInputExcel_48
												+ start_column_tFileInputExcel_48 + 1;
										curColName_tFileInputExcel_48 = "Perform_action";

										row6.Perform_action = temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48];
									} else {
										row6.Perform_action = null;
										emptyColumnCount_tFileInputExcel_48++;
									}
									columnIndex_tFileInputExcel_48 = 7;

									if (temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48].length() > 0) {
										curColNum_tFileInputExcel_48 = columnIndex_tFileInputExcel_48
												+ start_column_tFileInputExcel_48 + 1;
										curColName_tFileInputExcel_48 = "Criticite";

										row6.Criticite = temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48];
									} else {
										row6.Criticite = null;
										emptyColumnCount_tFileInputExcel_48++;
									}
									columnIndex_tFileInputExcel_48 = 8;

									if (temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48].length() > 0) {
										curColNum_tFileInputExcel_48 = columnIndex_tFileInputExcel_48
												+ start_column_tFileInputExcel_48 + 1;
										curColName_tFileInputExcel_48 = "Message_d_alarme";

										row6.Message_d_alarme = temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48];
									} else {
										row6.Message_d_alarme = null;
										emptyColumnCount_tFileInputExcel_48++;
									}
									columnIndex_tFileInputExcel_48 = 9;

									if (temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48].length() > 0) {
										curColNum_tFileInputExcel_48 = columnIndex_tFileInputExcel_48
												+ start_column_tFileInputExcel_48 + 1;
										curColName_tFileInputExcel_48 = "Instructions";

										row6.Instructions = temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48];
									} else {
										row6.Instructions = null;
										emptyColumnCount_tFileInputExcel_48++;
									}
									columnIndex_tFileInputExcel_48 = 10;

									if (temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48].length() > 0) {
										curColNum_tFileInputExcel_48 = columnIndex_tFileInputExcel_48
												+ start_column_tFileInputExcel_48 + 1;
										curColName_tFileInputExcel_48 = "Intervalle_de_polling";

										row6.Intervalle_de_polling = temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48];
									} else {
										row6.Intervalle_de_polling = null;
										emptyColumnCount_tFileInputExcel_48++;
									}
									columnIndex_tFileInputExcel_48 = 11;

									if (temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48].length() > 0) {
										curColNum_tFileInputExcel_48 = columnIndex_tFileInputExcel_48
												+ start_column_tFileInputExcel_48 + 1;
										curColName_tFileInputExcel_48 = "Ref__Service";

										row6.Ref__Service = temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48];
									} else {
										row6.Ref__Service = null;
										emptyColumnCount_tFileInputExcel_48++;
									}
									columnIndex_tFileInputExcel_48 = 12;

									if (temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48].length() > 0) {
										curColNum_tFileInputExcel_48 = columnIndex_tFileInputExcel_48
												+ start_column_tFileInputExcel_48 + 1;
										curColName_tFileInputExcel_48 = "Objet";

										row6.Objet = temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48];
									} else {
										row6.Objet = null;
										emptyColumnCount_tFileInputExcel_48++;
									}
									columnIndex_tFileInputExcel_48 = 13;

									if (temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48].length() > 0) {
										curColNum_tFileInputExcel_48 = columnIndex_tFileInputExcel_48
												+ start_column_tFileInputExcel_48 + 1;
										curColName_tFileInputExcel_48 = "Monitored_By";

										row6.Monitored_By = temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48];
									} else {
										row6.Monitored_By = null;
										emptyColumnCount_tFileInputExcel_48++;
									}
									columnIndex_tFileInputExcel_48 = 14;

									if (temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48].length() > 0) {
										curColNum_tFileInputExcel_48 = columnIndex_tFileInputExcel_48
												+ start_column_tFileInputExcel_48 + 1;
										curColName_tFileInputExcel_48 = "Nom_Template";

										row6.Nom_Template = temp_row_tFileInputExcel_48[columnIndex_tFileInputExcel_48];
									} else {
										row6.Nom_Template = null;
										emptyColumnCount_tFileInputExcel_48++;
									}

									nb_line_tFileInputExcel_48++;

								} catch (java.lang.Exception e) {
									globalMap.put("tFileInputExcel_48_ERROR_MESSAGE", e.getMessage());
									whetherReject_tFileInputExcel_48 = true;
									System.err.println(e.getMessage());
									row6 = null;
								}

								/**
								 * [tFileInputExcel_48 begin ] stop
								 */

								/**
								 * [tFileInputExcel_48 main ] start
								 */

								currentComponent = "tFileInputExcel_48";

								tos_count_tFileInputExcel_48++;

								/**
								 * [tFileInputExcel_48 main ] stop
								 */

								/**
								 * [tFileInputExcel_48 process_data_begin ] start
								 */

								currentComponent = "tFileInputExcel_48";

								/**
								 * [tFileInputExcel_48 process_data_begin ] stop
								 */
// Start of branch "row6"
								if (row6 != null) {

									/**
									 * [tFilterRow_6 main ] start
									 */

									currentComponent = "tFilterRow_6";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "row6"

										);
									}

									row15 = null;
									Operator_tFilterRow_6 ope_tFilterRow_6 = new Operator_tFilterRow_6("&&");
									ope_tFilterRow_6.matches((row6.Ref != null), "Ref!=null failed");

									if (ope_tFilterRow_6.getMatchFlag()) {
										if (row15 == null) {
											row15 = new row15Struct();
										}
										row15.N__Ref = row6.N__Ref;
										row15.Ref = row6.Ref;
										row15.Etat = row6.Etat;
										row15.Ref__des_composants = row6.Ref__des_composants;
										row15.RG_SG_si_Cluster = row6.RG_SG_si_Cluster;
										row15.URL = row6.URL;
										row15.Perform_action = row6.Perform_action;
										row15.Criticite = row6.Criticite;
										row15.Message_d_alarme = row6.Message_d_alarme;
										row15.Instructions = row6.Instructions;
										row15.Intervalle_de_polling = row6.Intervalle_de_polling;
										row15.Ref__Service = row6.Ref__Service;
										row15.Objet = row6.Objet;
										row15.Monitored_By = row6.Monitored_By;
										row15.Nom_Template = row6.Nom_Template;
										nb_line_ok_tFilterRow_6++;
									} else {
										nb_line_reject_tFilterRow_6++;
									}

									nb_line_tFilterRow_6++;

									tos_count_tFilterRow_6++;

									/**
									 * [tFilterRow_6 main ] stop
									 */

									/**
									 * [tFilterRow_6 process_data_begin ] start
									 */

									currentComponent = "tFilterRow_6";

									/**
									 * [tFilterRow_6 process_data_begin ] stop
									 */
// Start of branch "row15"
									if (row15 != null) {

										/**
										 * [tJavaRow_7 main ] start
										 */

										currentComponent = "tJavaRow_7";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row15"

											);
										}

										String fileName = (String) source_tFileInputExcel_48;

// Extraire la partie "POSANET" du nom du fichier Excel
										String[] parts = fileName.split("_");
										String fileWord = parts[6];

// Récupérer la valeur de "N__Ref" et la convertir en String
										String rowRef = row15.N__Ref;

// Générer l'ID "EDB_POSANET_X"
										String id = "EDB_" + fileWord + "_Url_" + rowRef;
//Code généré selon les schémas d'entrée et de sortie
										row24.N__Ref = id;
										row24.Ref = row15.Ref;
										row24.Etat = row15.Etat;
										row24.Ref__des_composants = row15.Ref__des_composants;
										row24.RG_SG_si_Cluster = row15.RG_SG_si_Cluster;
										row24.URL = row15.URL;
										row24.Perform_action = row15.Perform_action;
										row24.Criticite = row15.Criticite;
										row24.Message_d_alarme = row15.Message_d_alarme;
										row24.Instructions = row15.Instructions;
										row24.Intervalle_de_polling = row15.Intervalle_de_polling;
										row24.Ref__Service = row15.Ref__Service;
										row24.Objet = row15.Objet;
										row24.Monitored_By = row15.Monitored_By;
										row24.Nom_Template = row15.Nom_Template;

										nb_line_tJavaRow_7++;

										tos_count_tJavaRow_7++;

										/**
										 * [tJavaRow_7 main ] stop
										 */

										/**
										 * [tJavaRow_7 process_data_begin ] start
										 */

										currentComponent = "tJavaRow_7";

										/**
										 * [tJavaRow_7 process_data_begin ] stop
										 */

										/**
										 * [tMap_7 main ] start
										 */

										currentComponent = "tMap_7";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row24"

											);
										}

										boolean hasCasePrimitiveKeyWithNull_tMap_7 = false;

										// ###############################
										// # Input tables (lookups)
										boolean rejectedInnerJoin_tMap_7 = false;
										boolean mainRowRejected_tMap_7 = false;

										// ###############################
										{ // start of Var scope

											// ###############################
											// # Vars tables

											Var__tMap_7__Struct Var = Var__tMap_7;// ###############################
											// ###############################
											// # Output tables

											insrturls = null;

// # Output table : 'insrturls'
											insrturls_tmp.id = row24.N__Ref;
											insrturls_tmp.Ref = row24.Ref;
											insrturls_tmp.Etat = row24.Etat;
											insrturls_tmp.Ref_composant = row24.Ref__des_composants;
											insrturls_tmp.RG_SG_si_Cluster = row24.RG_SG_si_Cluster;
											insrturls_tmp.URL = row24.URL;
											insrturls_tmp.Perform_action = row24.Perform_action;
											insrturls_tmp.Cricite = row24.Criticite;
											insrturls_tmp.Message_alarme = row24.Message_d_alarme;
											insrturls_tmp.Instructions = row24.Instructions;
											insrturls_tmp.Intervalle_de_polling = row24.Intervalle_de_polling;
											insrturls_tmp.Ref_Service = row24.Ref__Service;
											insrturls_tmp.Objet = row24.Objet;
											insrturls_tmp.Monitored_by = row24.Monitored_By;
											insrturls_tmp.Nom_Template = row24.Nom_Template;
											insrturls = insrturls_tmp;
// ###############################

										} // end of Var scope

										rejectedInnerJoin_tMap_7 = false;

										tos_count_tMap_7++;

										/**
										 * [tMap_7 main ] stop
										 */

										/**
										 * [tMap_7 process_data_begin ] start
										 */

										currentComponent = "tMap_7";

										/**
										 * [tMap_7 process_data_begin ] stop
										 */
// Start of branch "insrturls"
										if (insrturls != null) {

											/**
											 * [tDBOutput_3 main ] start
											 */

											currentComponent = "tDBOutput_3";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "insrturls"

												);
											}

											whetherReject_tDBOutput_3 = false;
											if (insrturls.id == null) {
												pstmt_tDBOutput_3.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(1, insrturls.id);
											}

											if (insrturls.Ref == null) {
												pstmt_tDBOutput_3.setNull(2, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(2, insrturls.Ref);
											}

											if (insrturls.Etat == null) {
												pstmt_tDBOutput_3.setNull(3, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(3, insrturls.Etat);
											}

											if (insrturls.Ref_composant == null) {
												pstmt_tDBOutput_3.setNull(4, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(4, insrturls.Ref_composant);
											}

											if (insrturls.RG_SG_si_Cluster == null) {
												pstmt_tDBOutput_3.setNull(5, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(5, insrturls.RG_SG_si_Cluster);
											}

											if (insrturls.URL == null) {
												pstmt_tDBOutput_3.setNull(6, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(6, insrturls.URL);
											}

											if (insrturls.Perform_action == null) {
												pstmt_tDBOutput_3.setNull(7, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(7, insrturls.Perform_action);
											}

											if (insrturls.Cricite == null) {
												pstmt_tDBOutput_3.setNull(8, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(8, insrturls.Cricite);
											}

											if (insrturls.Message_alarme == null) {
												pstmt_tDBOutput_3.setNull(9, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(9, insrturls.Message_alarme);
											}

											if (insrturls.Instructions == null) {
												pstmt_tDBOutput_3.setNull(10, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(10, insrturls.Instructions);
											}

											if (insrturls.Intervalle_de_polling == null) {
												pstmt_tDBOutput_3.setNull(11, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(11, insrturls.Intervalle_de_polling);
											}

											if (insrturls.Ref_Service == null) {
												pstmt_tDBOutput_3.setNull(12, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(12, insrturls.Ref_Service);
											}

											if (insrturls.Objet == null) {
												pstmt_tDBOutput_3.setNull(13, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(13, insrturls.Objet);
											}

											if (insrturls.Monitored_by == null) {
												pstmt_tDBOutput_3.setNull(14, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(14, insrturls.Monitored_by);
											}

											if (insrturls.Nom_Template == null) {
												pstmt_tDBOutput_3.setNull(15, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(15, insrturls.Nom_Template);
											}

											try {
												nb_line_tDBOutput_3++;
												int processedCount_tDBOutput_3 = pstmt_tDBOutput_3.executeUpdate();
												insertedCount_tDBOutput_3 += processedCount_tDBOutput_3;
												rowsToCommitCount_tDBOutput_3 += processedCount_tDBOutput_3;
											} catch (java.lang.Exception e) {
												globalMap.put("tDBOutput_3_ERROR_MESSAGE", e.getMessage());
												whetherReject_tDBOutput_3 = true;
												System.err.print(e.getMessage());
											}
											commitCounter_tDBOutput_3++;

											if (commitEvery_tDBOutput_3 <= commitCounter_tDBOutput_3) {

												if (rowsToCommitCount_tDBOutput_3 != 0) {
												}
												conn_tDBOutput_3.commit();
												if (rowsToCommitCount_tDBOutput_3 != 0) {
													rowsToCommitCount_tDBOutput_3 = 0;
												}
												commitCounter_tDBOutput_3 = 0;

											}

											tos_count_tDBOutput_3++;

											/**
											 * [tDBOutput_3 main ] stop
											 */

											/**
											 * [tDBOutput_3 process_data_begin ] start
											 */

											currentComponent = "tDBOutput_3";

											/**
											 * [tDBOutput_3 process_data_begin ] stop
											 */

											/**
											 * [tDBOutput_3 process_data_end ] start
											 */

											currentComponent = "tDBOutput_3";

											/**
											 * [tDBOutput_3 process_data_end ] stop
											 */

										} // End of branch "insrturls"

										/**
										 * [tMap_7 process_data_end ] start
										 */

										currentComponent = "tMap_7";

										/**
										 * [tMap_7 process_data_end ] stop
										 */

										/**
										 * [tJavaRow_7 process_data_end ] start
										 */

										currentComponent = "tJavaRow_7";

										/**
										 * [tJavaRow_7 process_data_end ] stop
										 */

									} // End of branch "row15"

									/**
									 * [tFilterRow_6 process_data_end ] start
									 */

									currentComponent = "tFilterRow_6";

									/**
									 * [tFilterRow_6 process_data_end ] stop
									 */

								} // End of branch "row6"

								/**
								 * [tFileInputExcel_48 process_data_end ] start
								 */

								currentComponent = "tFileInputExcel_48";

								/**
								 * [tFileInputExcel_48 process_data_end ] stop
								 */

								/**
								 * [tFileInputExcel_48 end ] start
								 */

								currentComponent = "tFileInputExcel_48";

							}

							globalMap.put("tFileInputExcel_48_NB_LINE", nb_line_tFileInputExcel_48);

						}

					} finally {

						if (!(source_tFileInputExcel_48 instanceof java.io.InputStream)) {
							workbook_tFileInputExcel_48.getPackage().revert();
						}

					}

					ok_Hash.put("tFileInputExcel_48", true);
					end_Hash.put("tFileInputExcel_48", System.currentTimeMillis());

					/**
					 * [tFileInputExcel_48 end ] stop
					 */

					/**
					 * [tFilterRow_6 end ] start
					 */

					currentComponent = "tFilterRow_6";

					globalMap.put("tFilterRow_6_NB_LINE", nb_line_tFilterRow_6);
					globalMap.put("tFilterRow_6_NB_LINE_OK", nb_line_ok_tFilterRow_6);
					globalMap.put("tFilterRow_6_NB_LINE_REJECT", nb_line_reject_tFilterRow_6);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row6");
					}

					ok_Hash.put("tFilterRow_6", true);
					end_Hash.put("tFilterRow_6", System.currentTimeMillis());

					/**
					 * [tFilterRow_6 end ] stop
					 */

					/**
					 * [tJavaRow_7 end ] start
					 */

					currentComponent = "tJavaRow_7";

					globalMap.put("tJavaRow_7_NB_LINE", nb_line_tJavaRow_7);
					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row15");
					}

					ok_Hash.put("tJavaRow_7", true);
					end_Hash.put("tJavaRow_7", System.currentTimeMillis());

					/**
					 * [tJavaRow_7 end ] stop
					 */

					/**
					 * [tMap_7 end ] start
					 */

					currentComponent = "tMap_7";

// ###############################
// # Lookup hashes releasing
// ###############################      

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row24");
					}

					ok_Hash.put("tMap_7", true);
					end_Hash.put("tMap_7", System.currentTimeMillis());

					/**
					 * [tMap_7 end ] stop
					 */

					/**
					 * [tDBOutput_3 end ] start
					 */

					currentComponent = "tDBOutput_3";

					if (pstmt_tDBOutput_3 != null) {

						pstmt_tDBOutput_3.close();
						resourceMap.remove("pstmt_tDBOutput_3");

					}
					resourceMap.put("statementClosed_tDBOutput_3", true);
					if (commitCounter_tDBOutput_3 > 0 && rowsToCommitCount_tDBOutput_3 != 0) {

					}
					conn_tDBOutput_3.commit();
					if (commitCounter_tDBOutput_3 > 0 && rowsToCommitCount_tDBOutput_3 != 0) {

						rowsToCommitCount_tDBOutput_3 = 0;
					}
					commitCounter_tDBOutput_3 = 0;

					conn_tDBOutput_3.close();

					resourceMap.put("finish_tDBOutput_3", true);

					nb_line_deleted_tDBOutput_3 = nb_line_deleted_tDBOutput_3 + deletedCount_tDBOutput_3;
					nb_line_update_tDBOutput_3 = nb_line_update_tDBOutput_3 + updatedCount_tDBOutput_3;
					nb_line_inserted_tDBOutput_3 = nb_line_inserted_tDBOutput_3 + insertedCount_tDBOutput_3;
					nb_line_rejected_tDBOutput_3 = nb_line_rejected_tDBOutput_3 + rejectedCount_tDBOutput_3;

					globalMap.put("tDBOutput_3_NB_LINE", nb_line_tDBOutput_3);
					globalMap.put("tDBOutput_3_NB_LINE_UPDATED", nb_line_update_tDBOutput_3);
					globalMap.put("tDBOutput_3_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_3);
					globalMap.put("tDBOutput_3_NB_LINE_DELETED", nb_line_deleted_tDBOutput_3);
					globalMap.put("tDBOutput_3_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_3);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "insrturls");
					}

					ok_Hash.put("tDBOutput_3", true);
					end_Hash.put("tDBOutput_3", System.currentTimeMillis());

					/**
					 * [tDBOutput_3 end ] stop
					 */

					if (execStat) {
						runStat.updateStatOnConnection("iterate7", 2, "exec" + NB_ITERATE_tFileInputExcel_48);
					}

					NB_ITERATE_tIterateToFlow_2_ITFO++;

					if (execStat) {
						runStat.updateStatOnConnection("iterate11", 1, "exec" + NB_ITERATE_tIterateToFlow_2_ITFO);
						// Thread.sleep(1000);
					}

					/**
					 * [tIterateToFlow_2_ITFO begin ] start
					 */

					ok_Hash.put("tIterateToFlow_2_ITFO", false);
					start_Hash.put("tIterateToFlow_2_ITFO", System.currentTimeMillis());

					currentVirtualComponent = "tIterateToFlow_2";

					currentComponent = "tIterateToFlow_2_ITFO";

					int tos_count_tIterateToFlow_2_ITFO = 0;

					OnSubjobOkStructtIterateToFlow_2 struct_tIterateToFlow_2_ITFO = new OnSubjobOkStructtIterateToFlow_2();
					struct_tIterateToFlow_2_ITFO.filepath2 = ((String) globalMap.get("tFileList_2_CURRENT_FILEPATH"));

					if (globalMap.get("tIterateToFlow_2") != null) {
						java.util.List<OnSubjobOkStructtIterateToFlow_2> list_tIterateToFlow_2_ITFO = (java.util.List<OnSubjobOkStructtIterateToFlow_2>) globalMap
								.get("tIterateToFlow_2");
						list_tIterateToFlow_2_ITFO.add(struct_tIterateToFlow_2_ITFO);
					} else {
						java.util.List<OnSubjobOkStructtIterateToFlow_2> list_tIterateToFlow_2_ITFO = new java.util.ArrayList<OnSubjobOkStructtIterateToFlow_2>();
						list_tIterateToFlow_2_ITFO.add(struct_tIterateToFlow_2_ITFO);
						globalMap.put("tIterateToFlow_2", list_tIterateToFlow_2_ITFO);
					}

					/**
					 * [tIterateToFlow_2_ITFO begin ] stop
					 */

					/**
					 * [tIterateToFlow_2_ITFO main ] start
					 */

					currentVirtualComponent = "tIterateToFlow_2";

					currentComponent = "tIterateToFlow_2_ITFO";

					tos_count_tIterateToFlow_2_ITFO++;

					/**
					 * [tIterateToFlow_2_ITFO main ] stop
					 */

					/**
					 * [tIterateToFlow_2_ITFO process_data_begin ] start
					 */

					currentVirtualComponent = "tIterateToFlow_2";

					currentComponent = "tIterateToFlow_2_ITFO";

					/**
					 * [tIterateToFlow_2_ITFO process_data_begin ] stop
					 */

					/**
					 * [tIterateToFlow_2_ITFO process_data_end ] start
					 */

					currentVirtualComponent = "tIterateToFlow_2";

					currentComponent = "tIterateToFlow_2_ITFO";

					/**
					 * [tIterateToFlow_2_ITFO process_data_end ] stop
					 */

					/**
					 * [tIterateToFlow_2_ITFO end ] start
					 */

					currentVirtualComponent = "tIterateToFlow_2";

					currentComponent = "tIterateToFlow_2_ITFO";

					ok_Hash.put("tIterateToFlow_2_ITFO", true);
					end_Hash.put("tIterateToFlow_2_ITFO", System.currentTimeMillis());

					/**
					 * [tIterateToFlow_2_ITFO end ] stop
					 */
					if (execStat) {
						runStat.updateStatOnConnection("iterate11", 2, "exec" + NB_ITERATE_tIterateToFlow_2_ITFO);
					}

					/**
					 * [tFileList_2 process_data_end ] start
					 */

					currentComponent = "tFileList_2";

					/**
					 * [tFileList_2 process_data_end ] stop
					 */

					/**
					 * [tFileList_2 end ] start
					 */

					currentComponent = "tFileList_2";

				}
				globalMap.put("tFileList_2_NB_FILE", NB_FILEtFileList_2);

				ok_Hash.put("tFileList_2", true);
				end_Hash.put("tFileList_2", System.currentTimeMillis());

				/**
				 * [tFileList_2 end ] stop
				 */
			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tFileList_2:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk", 0, "ok");
			}

			tIterateToFlow_2_AIProcess(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileList_2 finally ] start
				 */

				currentComponent = "tFileList_2";

				/**
				 * [tFileList_2 finally ] stop
				 */

				/**
				 * [tFileInputExcel_6 finally ] start
				 */

				currentComponent = "tFileInputExcel_6";

				/**
				 * [tFileInputExcel_6 finally ] stop
				 */

				/**
				 * [tFilterRow_4 finally ] start
				 */

				currentComponent = "tFilterRow_4";

				/**
				 * [tFilterRow_4 finally ] stop
				 */

				/**
				 * [tJavaRow_5 finally ] start
				 */

				currentComponent = "tJavaRow_5";

				/**
				 * [tJavaRow_5 finally ] stop
				 */

				/**
				 * [tMap_5 finally ] start
				 */

				currentComponent = "tMap_5";

				/**
				 * [tMap_5 finally ] stop
				 */

				/**
				 * [tDBOutput_2 finally ] start
				 */

				currentComponent = "tDBOutput_2";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_2") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_2 = null;
						if ((pstmtToClose_tDBOutput_2 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_2")) != null) {
							pstmtToClose_tDBOutput_2.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_2") == null) {
						java.sql.Connection ctn_tDBOutput_2 = null;
						if ((ctn_tDBOutput_2 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_2")) != null) {
							try {
								ctn_tDBOutput_2.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_2) {
								String errorMessage_tDBOutput_2 = "failed to close the connection in tDBOutput_2 :"
										+ sqlEx_tDBOutput_2.getMessage();
								System.err.println(errorMessage_tDBOutput_2);
							}
						}
					}
				}

				/**
				 * [tDBOutput_2 finally ] stop
				 */

				/**
				 * [tFileInputExcel_24 finally ] start
				 */

				currentComponent = "tFileInputExcel_24";

				/**
				 * [tFileInputExcel_24 finally ] stop
				 */

				/**
				 * [tFilterRow_5 finally ] start
				 */

				currentComponent = "tFilterRow_5";

				/**
				 * [tFilterRow_5 finally ] stop
				 */

				/**
				 * [tJavaRow_6 finally ] start
				 */

				currentComponent = "tJavaRow_6";

				/**
				 * [tJavaRow_6 finally ] stop
				 */

				/**
				 * [tMap_6 finally ] start
				 */

				currentComponent = "tMap_6";

				/**
				 * [tMap_6 finally ] stop
				 */

				/**
				 * [tDBOutput_21 finally ] start
				 */

				currentComponent = "tDBOutput_21";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_21") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_21 = null;
						if ((pstmtToClose_tDBOutput_21 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_21")) != null) {
							pstmtToClose_tDBOutput_21.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_21") == null) {
						java.sql.Connection ctn_tDBOutput_21 = null;
						if ((ctn_tDBOutput_21 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_21")) != null) {
							try {
								ctn_tDBOutput_21.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_21) {
								String errorMessage_tDBOutput_21 = "failed to close the connection in tDBOutput_21 :"
										+ sqlEx_tDBOutput_21.getMessage();
								System.err.println(errorMessage_tDBOutput_21);
							}
						}
					}
				}

				/**
				 * [tDBOutput_21 finally ] stop
				 */

				/**
				 * [tFileInputExcel_48 finally ] start
				 */

				currentComponent = "tFileInputExcel_48";

				/**
				 * [tFileInputExcel_48 finally ] stop
				 */

				/**
				 * [tFilterRow_6 finally ] start
				 */

				currentComponent = "tFilterRow_6";

				/**
				 * [tFilterRow_6 finally ] stop
				 */

				/**
				 * [tJavaRow_7 finally ] start
				 */

				currentComponent = "tJavaRow_7";

				/**
				 * [tJavaRow_7 finally ] stop
				 */

				/**
				 * [tMap_7 finally ] start
				 */

				currentComponent = "tMap_7";

				/**
				 * [tMap_7 finally ] stop
				 */

				/**
				 * [tDBOutput_3 finally ] start
				 */

				currentComponent = "tDBOutput_3";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_3") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_3 = null;
						if ((pstmtToClose_tDBOutput_3 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_3")) != null) {
							pstmtToClose_tDBOutput_3.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_3") == null) {
						java.sql.Connection ctn_tDBOutput_3 = null;
						if ((ctn_tDBOutput_3 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_3")) != null) {
							try {
								ctn_tDBOutput_3.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_3) {
								String errorMessage_tDBOutput_3 = "failed to close the connection in tDBOutput_3 :"
										+ sqlEx_tDBOutput_3.getMessage();
								System.err.println(errorMessage_tDBOutput_3);
							}
						}
					}
				}

				/**
				 * [tDBOutput_3 finally ] stop
				 */

				/**
				 * [tIterateToFlow_2_ITFO finally ] start
				 */

				currentVirtualComponent = "tIterateToFlow_2";

				currentComponent = "tIterateToFlow_2_ITFO";

				/**
				 * [tIterateToFlow_2_ITFO finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileList_2_SUBPROCESS_STATE", 1);
	}

	public static class inserttraps_snmpStruct implements routines.system.IPersistableRow<inserttraps_snmpStruct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref_composant;

		public String getRef_composant() {
			return this.Ref_composant;
		}

		public String Signification;

		public String getSignification() {
			return this.Signification;
		}

		public String Version_SNMP;

		public String getVersion_SNMP() {
			return this.Version_SNMP;
		}

		public String OID;

		public String getOID() {
			return this.OID;
		}

		public String Specific_Trap;

		public String getSpecific_Trap() {
			return this.Specific_Trap;
		}

		public String Variable_binding;

		public String getVariable_binding() {
			return this.Variable_binding;
		}

		public String Cricite;

		public String getCricite() {
			return this.Cricite;
		}

		public String Message_alarme;

		public String getMessage_alarme() {
			return this.Message_alarme;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String acquittement;

		public String getAcquittement() {
			return this.acquittement;
		}

		public String MIB_associe;

		public String getMIB_associe() {
			return this.MIB_associe;
		}

		public String Objet;

		public String getObjet() {
			return this.Objet;
		}

		public String Compelement_information;

		public String getCompelement_information() {
			return this.Compelement_information;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final inserttraps_snmpStruct other = (inserttraps_snmpStruct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(inserttraps_snmpStruct other) {

			other.id = this.id;
			other.Ref = this.Ref;
			other.Etat = this.Etat;
			other.Ref_composant = this.Ref_composant;
			other.Signification = this.Signification;
			other.Version_SNMP = this.Version_SNMP;
			other.OID = this.OID;
			other.Specific_Trap = this.Specific_Trap;
			other.Variable_binding = this.Variable_binding;
			other.Cricite = this.Cricite;
			other.Message_alarme = this.Message_alarme;
			other.Instructions = this.Instructions;
			other.acquittement = this.acquittement;
			other.MIB_associe = this.MIB_associe;
			other.Objet = this.Objet;
			other.Compelement_information = this.Compelement_information;

		}

		public void copyKeysDataTo(inserttraps_snmpStruct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref_composant = readString(dis);

					this.Signification = readString(dis);

					this.Version_SNMP = readString(dis);

					this.OID = readString(dis);

					this.Specific_Trap = readString(dis);

					this.Variable_binding = readString(dis);

					this.Cricite = readString(dis);

					this.Message_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.acquittement = readString(dis);

					this.MIB_associe = readString(dis);

					this.Objet = readString(dis);

					this.Compelement_information = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref_composant = readString(dis);

					this.Signification = readString(dis);

					this.Version_SNMP = readString(dis);

					this.OID = readString(dis);

					this.Specific_Trap = readString(dis);

					this.Variable_binding = readString(dis);

					this.Cricite = readString(dis);

					this.Message_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.acquittement = readString(dis);

					this.MIB_associe = readString(dis);

					this.Objet = readString(dis);

					this.Compelement_information = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref_composant, dos);

				// String

				writeString(this.Signification, dos);

				// String

				writeString(this.Version_SNMP, dos);

				// String

				writeString(this.OID, dos);

				// String

				writeString(this.Specific_Trap, dos);

				// String

				writeString(this.Variable_binding, dos);

				// String

				writeString(this.Cricite, dos);

				// String

				writeString(this.Message_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.acquittement, dos);

				// String

				writeString(this.MIB_associe, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Compelement_information, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref_composant, dos);

				// String

				writeString(this.Signification, dos);

				// String

				writeString(this.Version_SNMP, dos);

				// String

				writeString(this.OID, dos);

				// String

				writeString(this.Specific_Trap, dos);

				// String

				writeString(this.Variable_binding, dos);

				// String

				writeString(this.Cricite, dos);

				// String

				writeString(this.Message_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.acquittement, dos);

				// String

				writeString(this.MIB_associe, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Compelement_information, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref_composant=" + Ref_composant);
			sb.append(",Signification=" + Signification);
			sb.append(",Version_SNMP=" + Version_SNMP);
			sb.append(",OID=" + OID);
			sb.append(",Specific_Trap=" + Specific_Trap);
			sb.append(",Variable_binding=" + Variable_binding);
			sb.append(",Cricite=" + Cricite);
			sb.append(",Message_alarme=" + Message_alarme);
			sb.append(",Instructions=" + Instructions);
			sb.append(",acquittement=" + acquittement);
			sb.append(",MIB_associe=" + MIB_associe);
			sb.append(",Objet=" + Objet);
			sb.append(",Compelement_information=" + Compelement_information);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(inserttraps_snmpStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id, other.id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row25Struct implements routines.system.IPersistableRow<row25Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref__des_composants;

		public String getRef__des_composants() {
			return this.Ref__des_composants;
		}

		public String Signification;

		public String getSignification() {
			return this.Signification;
		}

		public String Version_SNMP;

		public String getVersion_SNMP() {
			return this.Version_SNMP;
		}

		public String OID;

		public String getOID() {
			return this.OID;
		}

		public String Specific_trap;

		public String getSpecific_trap() {
			return this.Specific_trap;
		}

		public String Variable_s__binding;

		public String getVariable_s__binding() {
			return this.Variable_s__binding;
		}

		public String Criticite;

		public String getCriticite() {
			return this.Criticite;
		}

		public String Message_d_alarme;

		public String getMessage_d_alarme() {
			return this.Message_d_alarme;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Acquittement;

		public String getAcquittement() {
			return this.Acquittement;
		}

		public String MIB_associee_s;

		public String getMIB_associee_s() {
			return this.MIB_associee_s;
		}

		public String Objet;

		public String getObjet() {
			return this.Objet;
		}

		public String Complements_d_informations;

		public String getComplements_d_informations() {
			return this.Complements_d_informations;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.Signification = readString(dis);

					this.Version_SNMP = readString(dis);

					this.OID = readString(dis);

					this.Specific_trap = readString(dis);

					this.Variable_s__binding = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Acquittement = readString(dis);

					this.MIB_associee_s = readString(dis);

					this.Objet = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.Signification = readString(dis);

					this.Version_SNMP = readString(dis);

					this.OID = readString(dis);

					this.Specific_trap = readString(dis);

					this.Variable_s__binding = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Acquittement = readString(dis);

					this.MIB_associee_s = readString(dis);

					this.Objet = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.Signification, dos);

				// String

				writeString(this.Version_SNMP, dos);

				// String

				writeString(this.OID, dos);

				// String

				writeString(this.Specific_trap, dos);

				// String

				writeString(this.Variable_s__binding, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Acquittement, dos);

				// String

				writeString(this.MIB_associee_s, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.Signification, dos);

				// String

				writeString(this.Version_SNMP, dos);

				// String

				writeString(this.OID, dos);

				// String

				writeString(this.Specific_trap, dos);

				// String

				writeString(this.Variable_s__binding, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Acquittement, dos);

				// String

				writeString(this.MIB_associee_s, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref__des_composants=" + Ref__des_composants);
			sb.append(",Signification=" + Signification);
			sb.append(",Version_SNMP=" + Version_SNMP);
			sb.append(",OID=" + OID);
			sb.append(",Specific_trap=" + Specific_trap);
			sb.append(",Variable_s__binding=" + Variable_s__binding);
			sb.append(",Criticite=" + Criticite);
			sb.append(",Message_d_alarme=" + Message_d_alarme);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Acquittement=" + Acquittement);
			sb.append(",MIB_associee_s=" + MIB_associee_s);
			sb.append(",Objet=" + Objet);
			sb.append(",Complements_d_informations=" + Complements_d_informations);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row25Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row16Struct implements routines.system.IPersistableRow<row16Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref__des_composants;

		public String getRef__des_composants() {
			return this.Ref__des_composants;
		}

		public String Signification;

		public String getSignification() {
			return this.Signification;
		}

		public String Version_SNMP;

		public String getVersion_SNMP() {
			return this.Version_SNMP;
		}

		public String OID;

		public String getOID() {
			return this.OID;
		}

		public String Specific_trap;

		public String getSpecific_trap() {
			return this.Specific_trap;
		}

		public String Variable_s__binding;

		public String getVariable_s__binding() {
			return this.Variable_s__binding;
		}

		public String Criticite;

		public String getCriticite() {
			return this.Criticite;
		}

		public String Message_d_alarme;

		public String getMessage_d_alarme() {
			return this.Message_d_alarme;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Acquittement;

		public String getAcquittement() {
			return this.Acquittement;
		}

		public String MIB_associee_s;

		public String getMIB_associee_s() {
			return this.MIB_associee_s;
		}

		public String Objet;

		public String getObjet() {
			return this.Objet;
		}

		public String Complements_d_informations;

		public String getComplements_d_informations() {
			return this.Complements_d_informations;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.Signification = readString(dis);

					this.Version_SNMP = readString(dis);

					this.OID = readString(dis);

					this.Specific_trap = readString(dis);

					this.Variable_s__binding = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Acquittement = readString(dis);

					this.MIB_associee_s = readString(dis);

					this.Objet = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.Signification = readString(dis);

					this.Version_SNMP = readString(dis);

					this.OID = readString(dis);

					this.Specific_trap = readString(dis);

					this.Variable_s__binding = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Acquittement = readString(dis);

					this.MIB_associee_s = readString(dis);

					this.Objet = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.Signification, dos);

				// String

				writeString(this.Version_SNMP, dos);

				// String

				writeString(this.OID, dos);

				// String

				writeString(this.Specific_trap, dos);

				// String

				writeString(this.Variable_s__binding, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Acquittement, dos);

				// String

				writeString(this.MIB_associee_s, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.Signification, dos);

				// String

				writeString(this.Version_SNMP, dos);

				// String

				writeString(this.OID, dos);

				// String

				writeString(this.Specific_trap, dos);

				// String

				writeString(this.Variable_s__binding, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Acquittement, dos);

				// String

				writeString(this.MIB_associee_s, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref__des_composants=" + Ref__des_composants);
			sb.append(",Signification=" + Signification);
			sb.append(",Version_SNMP=" + Version_SNMP);
			sb.append(",OID=" + OID);
			sb.append(",Specific_trap=" + Specific_trap);
			sb.append(",Variable_s__binding=" + Variable_s__binding);
			sb.append(",Criticite=" + Criticite);
			sb.append(",Message_d_alarme=" + Message_d_alarme);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Acquittement=" + Acquittement);
			sb.append(",MIB_associee_s=" + MIB_associee_s);
			sb.append(",Objet=" + Objet);
			sb.append(",Complements_d_informations=" + Complements_d_informations);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row16Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row7Struct implements routines.system.IPersistableRow<row7Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Ref__des_composants;

		public String getRef__des_composants() {
			return this.Ref__des_composants;
		}

		public String Signification;

		public String getSignification() {
			return this.Signification;
		}

		public String Version_SNMP;

		public String getVersion_SNMP() {
			return this.Version_SNMP;
		}

		public String OID;

		public String getOID() {
			return this.OID;
		}

		public String Specific_trap;

		public String getSpecific_trap() {
			return this.Specific_trap;
		}

		public String Variable_s__binding;

		public String getVariable_s__binding() {
			return this.Variable_s__binding;
		}

		public String Criticite;

		public String getCriticite() {
			return this.Criticite;
		}

		public String Message_d_alarme;

		public String getMessage_d_alarme() {
			return this.Message_d_alarme;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Acquittement;

		public String getAcquittement() {
			return this.Acquittement;
		}

		public String MIB_associee_s;

		public String getMIB_associee_s() {
			return this.MIB_associee_s;
		}

		public String Objet;

		public String getObjet() {
			return this.Objet;
		}

		public String Complements_d_informations;

		public String getComplements_d_informations() {
			return this.Complements_d_informations;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.Signification = readString(dis);

					this.Version_SNMP = readString(dis);

					this.OID = readString(dis);

					this.Specific_trap = readString(dis);

					this.Variable_s__binding = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Acquittement = readString(dis);

					this.MIB_associee_s = readString(dis);

					this.Objet = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Ref__des_composants = readString(dis);

					this.Signification = readString(dis);

					this.Version_SNMP = readString(dis);

					this.OID = readString(dis);

					this.Specific_trap = readString(dis);

					this.Variable_s__binding = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Acquittement = readString(dis);

					this.MIB_associee_s = readString(dis);

					this.Objet = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.Signification, dos);

				// String

				writeString(this.Version_SNMP, dos);

				// String

				writeString(this.OID, dos);

				// String

				writeString(this.Specific_trap, dos);

				// String

				writeString(this.Variable_s__binding, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Acquittement, dos);

				// String

				writeString(this.MIB_associee_s, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Ref__des_composants, dos);

				// String

				writeString(this.Signification, dos);

				// String

				writeString(this.Version_SNMP, dos);

				// String

				writeString(this.OID, dos);

				// String

				writeString(this.Specific_trap, dos);

				// String

				writeString(this.Variable_s__binding, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Acquittement, dos);

				// String

				writeString(this.MIB_associee_s, dos);

				// String

				writeString(this.Objet, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Ref__des_composants=" + Ref__des_composants);
			sb.append(",Signification=" + Signification);
			sb.append(",Version_SNMP=" + Version_SNMP);
			sb.append(",OID=" + OID);
			sb.append(",Specific_trap=" + Specific_trap);
			sb.append(",Variable_s__binding=" + Variable_s__binding);
			sb.append(",Criticite=" + Criticite);
			sb.append(",Message_d_alarme=" + Message_d_alarme);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Acquittement=" + Acquittement);
			sb.append(",MIB_associee_s=" + MIB_associee_s);
			sb.append(",Objet=" + Objet);
			sb.append(",Complements_d_informations=" + Complements_d_informations);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row7Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class insertserveursStruct implements routines.system.IPersistableRow<insertserveursStruct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Platforme;

		public String getPlatforme() {
			return this.Platforme;
		}

		public String Hostname;

		public String getHostname() {
			return this.Hostname;
		}

		public String FQDN;

		public String getFQDN() {
			return this.FQDN;
		}

		public String Type;

		public String getType() {
			return this.Type;
		}

		public String Modele;

		public String getModele() {
			return this.Modele;
		}

		public String OS;

		public String getOS() {
			return this.OS;
		}

		public String Ver_tech__Firmware;

		public String getVer_tech__Firmware() {
			return this.Ver_tech__Firmware;
		}

		public String Cluster;

		public String getCluster() {
			return this.Cluster;
		}

		public String Ip_source;

		public String getIp_source() {
			return this.Ip_source;
		}

		public String Description;

		public String getDescription() {
			return this.Description;
		}

		public String Socle_Standard_OMU;

		public String getSocle_Standard_OMU() {
			return this.Socle_Standard_OMU;
		}

		public String Complements_d_informations;

		public String getComplements_d_informations() {
			return this.Complements_d_informations;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final insertserveursStruct other = (insertserveursStruct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(insertserveursStruct other) {

			other.id = this.id;
			other.Ref = this.Ref;
			other.Etat = this.Etat;
			other.Platforme = this.Platforme;
			other.Hostname = this.Hostname;
			other.FQDN = this.FQDN;
			other.Type = this.Type;
			other.Modele = this.Modele;
			other.OS = this.OS;
			other.Ver_tech__Firmware = this.Ver_tech__Firmware;
			other.Cluster = this.Cluster;
			other.Ip_source = this.Ip_source;
			other.Description = this.Description;
			other.Socle_Standard_OMU = this.Socle_Standard_OMU;
			other.Complements_d_informations = this.Complements_d_informations;

		}

		public void copyKeysDataTo(insertserveursStruct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Platforme = readString(dis);

					this.Hostname = readString(dis);

					this.FQDN = readString(dis);

					this.Type = readString(dis);

					this.Modele = readString(dis);

					this.OS = readString(dis);

					this.Ver_tech__Firmware = readString(dis);

					this.Cluster = readString(dis);

					this.Ip_source = readString(dis);

					this.Description = readString(dis);

					this.Socle_Standard_OMU = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Platforme = readString(dis);

					this.Hostname = readString(dis);

					this.FQDN = readString(dis);

					this.Type = readString(dis);

					this.Modele = readString(dis);

					this.OS = readString(dis);

					this.Ver_tech__Firmware = readString(dis);

					this.Cluster = readString(dis);

					this.Ip_source = readString(dis);

					this.Description = readString(dis);

					this.Socle_Standard_OMU = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Platforme, dos);

				// String

				writeString(this.Hostname, dos);

				// String

				writeString(this.FQDN, dos);

				// String

				writeString(this.Type, dos);

				// String

				writeString(this.Modele, dos);

				// String

				writeString(this.OS, dos);

				// String

				writeString(this.Ver_tech__Firmware, dos);

				// String

				writeString(this.Cluster, dos);

				// String

				writeString(this.Ip_source, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Socle_Standard_OMU, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Platforme, dos);

				// String

				writeString(this.Hostname, dos);

				// String

				writeString(this.FQDN, dos);

				// String

				writeString(this.Type, dos);

				// String

				writeString(this.Modele, dos);

				// String

				writeString(this.OS, dos);

				// String

				writeString(this.Ver_tech__Firmware, dos);

				// String

				writeString(this.Cluster, dos);

				// String

				writeString(this.Ip_source, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Socle_Standard_OMU, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Platforme=" + Platforme);
			sb.append(",Hostname=" + Hostname);
			sb.append(",FQDN=" + FQDN);
			sb.append(",Type=" + Type);
			sb.append(",Modele=" + Modele);
			sb.append(",OS=" + OS);
			sb.append(",Ver_tech__Firmware=" + Ver_tech__Firmware);
			sb.append(",Cluster=" + Cluster);
			sb.append(",Ip_source=" + Ip_source);
			sb.append(",Description=" + Description);
			sb.append(",Socle_Standard_OMU=" + Socle_Standard_OMU);
			sb.append(",Complements_d_informations=" + Complements_d_informations);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(insertserveursStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id, other.id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row26Struct implements routines.system.IPersistableRow<row26Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Plateforme;

		public String getPlateforme() {
			return this.Plateforme;
		}

		public String Hostname;

		public String getHostname() {
			return this.Hostname;
		}

		public String FQDN;

		public String getFQDN() {
			return this.FQDN;
		}

		public String Type;

		public String getType() {
			return this.Type;
		}

		public String Modele;

		public String getModele() {
			return this.Modele;
		}

		public String OS;

		public String getOS() {
			return this.OS;
		}

		public String Ver__tech____Firmware;

		public String getVer__tech____Firmware() {
			return this.Ver__tech____Firmware;
		}

		public String Cluster;

		public String getCluster() {
			return this.Cluster;
		}

		public String IP_source;

		public String getIP_source() {
			return this.IP_source;
		}

		public String Description;

		public String getDescription() {
			return this.Description;
		}

		public String Socle_Standard_OMU;

		public String getSocle_Standard_OMU() {
			return this.Socle_Standard_OMU;
		}

		public String Complements_d_informations;

		public String getComplements_d_informations() {
			return this.Complements_d_informations;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Plateforme = readString(dis);

					this.Hostname = readString(dis);

					this.FQDN = readString(dis);

					this.Type = readString(dis);

					this.Modele = readString(dis);

					this.OS = readString(dis);

					this.Ver__tech____Firmware = readString(dis);

					this.Cluster = readString(dis);

					this.IP_source = readString(dis);

					this.Description = readString(dis);

					this.Socle_Standard_OMU = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Plateforme = readString(dis);

					this.Hostname = readString(dis);

					this.FQDN = readString(dis);

					this.Type = readString(dis);

					this.Modele = readString(dis);

					this.OS = readString(dis);

					this.Ver__tech____Firmware = readString(dis);

					this.Cluster = readString(dis);

					this.IP_source = readString(dis);

					this.Description = readString(dis);

					this.Socle_Standard_OMU = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Plateforme, dos);

				// String

				writeString(this.Hostname, dos);

				// String

				writeString(this.FQDN, dos);

				// String

				writeString(this.Type, dos);

				// String

				writeString(this.Modele, dos);

				// String

				writeString(this.OS, dos);

				// String

				writeString(this.Ver__tech____Firmware, dos);

				// String

				writeString(this.Cluster, dos);

				// String

				writeString(this.IP_source, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Socle_Standard_OMU, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Plateforme, dos);

				// String

				writeString(this.Hostname, dos);

				// String

				writeString(this.FQDN, dos);

				// String

				writeString(this.Type, dos);

				// String

				writeString(this.Modele, dos);

				// String

				writeString(this.OS, dos);

				// String

				writeString(this.Ver__tech____Firmware, dos);

				// String

				writeString(this.Cluster, dos);

				// String

				writeString(this.IP_source, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Socle_Standard_OMU, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Plateforme=" + Plateforme);
			sb.append(",Hostname=" + Hostname);
			sb.append(",FQDN=" + FQDN);
			sb.append(",Type=" + Type);
			sb.append(",Modele=" + Modele);
			sb.append(",OS=" + OS);
			sb.append(",Ver__tech____Firmware=" + Ver__tech____Firmware);
			sb.append(",Cluster=" + Cluster);
			sb.append(",IP_source=" + IP_source);
			sb.append(",Description=" + Description);
			sb.append(",Socle_Standard_OMU=" + Socle_Standard_OMU);
			sb.append(",Complements_d_informations=" + Complements_d_informations);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row26Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row17Struct implements routines.system.IPersistableRow<row17Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Plateforme;

		public String getPlateforme() {
			return this.Plateforme;
		}

		public String Hostname;

		public String getHostname() {
			return this.Hostname;
		}

		public String FQDN;

		public String getFQDN() {
			return this.FQDN;
		}

		public String Type;

		public String getType() {
			return this.Type;
		}

		public String Modele;

		public String getModele() {
			return this.Modele;
		}

		public String OS;

		public String getOS() {
			return this.OS;
		}

		public String Ver__tech____Firmware;

		public String getVer__tech____Firmware() {
			return this.Ver__tech____Firmware;
		}

		public String Cluster;

		public String getCluster() {
			return this.Cluster;
		}

		public String IP_source;

		public String getIP_source() {
			return this.IP_source;
		}

		public String Description;

		public String getDescription() {
			return this.Description;
		}

		public String Socle_Standard_OMU;

		public String getSocle_Standard_OMU() {
			return this.Socle_Standard_OMU;
		}

		public String Complements_d_informations;

		public String getComplements_d_informations() {
			return this.Complements_d_informations;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Plateforme = readString(dis);

					this.Hostname = readString(dis);

					this.FQDN = readString(dis);

					this.Type = readString(dis);

					this.Modele = readString(dis);

					this.OS = readString(dis);

					this.Ver__tech____Firmware = readString(dis);

					this.Cluster = readString(dis);

					this.IP_source = readString(dis);

					this.Description = readString(dis);

					this.Socle_Standard_OMU = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Plateforme = readString(dis);

					this.Hostname = readString(dis);

					this.FQDN = readString(dis);

					this.Type = readString(dis);

					this.Modele = readString(dis);

					this.OS = readString(dis);

					this.Ver__tech____Firmware = readString(dis);

					this.Cluster = readString(dis);

					this.IP_source = readString(dis);

					this.Description = readString(dis);

					this.Socle_Standard_OMU = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Plateforme, dos);

				// String

				writeString(this.Hostname, dos);

				// String

				writeString(this.FQDN, dos);

				// String

				writeString(this.Type, dos);

				// String

				writeString(this.Modele, dos);

				// String

				writeString(this.OS, dos);

				// String

				writeString(this.Ver__tech____Firmware, dos);

				// String

				writeString(this.Cluster, dos);

				// String

				writeString(this.IP_source, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Socle_Standard_OMU, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Plateforme, dos);

				// String

				writeString(this.Hostname, dos);

				// String

				writeString(this.FQDN, dos);

				// String

				writeString(this.Type, dos);

				// String

				writeString(this.Modele, dos);

				// String

				writeString(this.OS, dos);

				// String

				writeString(this.Ver__tech____Firmware, dos);

				// String

				writeString(this.Cluster, dos);

				// String

				writeString(this.IP_source, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Socle_Standard_OMU, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Plateforme=" + Plateforme);
			sb.append(",Hostname=" + Hostname);
			sb.append(",FQDN=" + FQDN);
			sb.append(",Type=" + Type);
			sb.append(",Modele=" + Modele);
			sb.append(",OS=" + OS);
			sb.append(",Ver__tech____Firmware=" + Ver__tech____Firmware);
			sb.append(",Cluster=" + Cluster);
			sb.append(",IP_source=" + IP_source);
			sb.append(",Description=" + Description);
			sb.append(",Socle_Standard_OMU=" + Socle_Standard_OMU);
			sb.append(",Complements_d_informations=" + Complements_d_informations);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row17Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row8Struct implements routines.system.IPersistableRow<row8Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String N__Ref;

		public String getN__Ref() {
			return this.N__Ref;
		}

		public String Ref;

		public String getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Plateforme;

		public String getPlateforme() {
			return this.Plateforme;
		}

		public String Hostname;

		public String getHostname() {
			return this.Hostname;
		}

		public String FQDN;

		public String getFQDN() {
			return this.FQDN;
		}

		public String Type;

		public String getType() {
			return this.Type;
		}

		public String Modele;

		public String getModele() {
			return this.Modele;
		}

		public String OS;

		public String getOS() {
			return this.OS;
		}

		public String Ver__tech____Firmware;

		public String getVer__tech____Firmware() {
			return this.Ver__tech____Firmware;
		}

		public String Cluster;

		public String getCluster() {
			return this.Cluster;
		}

		public String IP_source;

		public String getIP_source() {
			return this.IP_source;
		}

		public String Description;

		public String getDescription() {
			return this.Description;
		}

		public String Socle_Standard_OMU;

		public String getSocle_Standard_OMU() {
			return this.Socle_Standard_OMU;
		}

		public String Complements_d_informations;

		public String getComplements_d_informations() {
			return this.Complements_d_informations;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Plateforme = readString(dis);

					this.Hostname = readString(dis);

					this.FQDN = readString(dis);

					this.Type = readString(dis);

					this.Modele = readString(dis);

					this.OS = readString(dis);

					this.Ver__tech____Firmware = readString(dis);

					this.Cluster = readString(dis);

					this.IP_source = readString(dis);

					this.Description = readString(dis);

					this.Socle_Standard_OMU = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.N__Ref = readString(dis);

					this.Ref = readString(dis);

					this.Etat = readString(dis);

					this.Plateforme = readString(dis);

					this.Hostname = readString(dis);

					this.FQDN = readString(dis);

					this.Type = readString(dis);

					this.Modele = readString(dis);

					this.OS = readString(dis);

					this.Ver__tech____Firmware = readString(dis);

					this.Cluster = readString(dis);

					this.IP_source = readString(dis);

					this.Description = readString(dis);

					this.Socle_Standard_OMU = readString(dis);

					this.Complements_d_informations = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Plateforme, dos);

				// String

				writeString(this.Hostname, dos);

				// String

				writeString(this.FQDN, dos);

				// String

				writeString(this.Type, dos);

				// String

				writeString(this.Modele, dos);

				// String

				writeString(this.OS, dos);

				// String

				writeString(this.Ver__tech____Firmware, dos);

				// String

				writeString(this.Cluster, dos);

				// String

				writeString(this.IP_source, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Socle_Standard_OMU, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.N__Ref, dos);

				// String

				writeString(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Plateforme, dos);

				// String

				writeString(this.Hostname, dos);

				// String

				writeString(this.FQDN, dos);

				// String

				writeString(this.Type, dos);

				// String

				writeString(this.Modele, dos);

				// String

				writeString(this.OS, dos);

				// String

				writeString(this.Ver__tech____Firmware, dos);

				// String

				writeString(this.Cluster, dos);

				// String

				writeString(this.IP_source, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Socle_Standard_OMU, dos);

				// String

				writeString(this.Complements_d_informations, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("N__Ref=" + N__Ref);
			sb.append(",Ref=" + Ref);
			sb.append(",Etat=" + Etat);
			sb.append(",Plateforme=" + Plateforme);
			sb.append(",Hostname=" + Hostname);
			sb.append(",FQDN=" + FQDN);
			sb.append(",Type=" + Type);
			sb.append(",Modele=" + Modele);
			sb.append(",OS=" + OS);
			sb.append(",Ver__tech____Firmware=" + Ver__tech____Firmware);
			sb.append(",Cluster=" + Cluster);
			sb.append(",IP_source=" + IP_source);
			sb.append(",Description=" + Description);
			sb.append(",Socle_Standard_OMU=" + Socle_Standard_OMU);
			sb.append(",Complements_d_informations=" + Complements_d_informations);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row8Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class insertlog_files_patternsStruct
			implements routines.system.IPersistableRow<insertlog_files_patternsStruct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		public Integer N_Ref;

		public Integer getN_Ref() {
			return this.N_Ref;
		}

		public Integer Ref;

		public Integer getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Signification;

		public String getSignification() {
			return this.Signification;
		}

		public String Identification;

		public String getIdentification() {
			return this.Identification;
		}

		public String Criticitee;

		public String getCriticitee() {
			return this.Criticitee;
		}

		public String Message_alarme;

		public String getMessage_alarme() {
			return this.Message_alarme;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Perform_action;

		public String getPerform_action() {
			return this.Perform_action;
		}

		public String Acquittement;

		public String getAcquittement() {
			return this.Acquittement;
		}

		public String Complements_d_informations;

		public String getComplements_d_informations() {
			return this.Complements_d_informations;
		}

		public String Ref_Service;

		public String getRef_Service() {
			return this.Ref_Service;
		}

		public String Objet;

		public String getObjet() {
			return this.Objet;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final insertlog_files_patternsStruct other = (insertlog_files_patternsStruct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(insertlog_files_patternsStruct other) {

			other.id = this.id;
			other.N_Ref = this.N_Ref;
			other.Ref = this.Ref;
			other.Etat = this.Etat;
			other.Signification = this.Signification;
			other.Identification = this.Identification;
			other.Criticitee = this.Criticitee;
			other.Message_alarme = this.Message_alarme;
			other.Instructions = this.Instructions;
			other.Perform_action = this.Perform_action;
			other.Acquittement = this.Acquittement;
			other.Complements_d_informations = this.Complements_d_informations;
			other.Ref_Service = this.Ref_Service;
			other.Objet = this.Objet;

		}

		public void copyKeysDataTo(insertlog_files_patternsStruct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.N_Ref = readInteger(dis);

					this.Ref = readInteger(dis);

					this.Etat = readString(dis);

					this.Signification = readString(dis);

					this.Identification = readString(dis);

					this.Criticitee = readString(dis);

					this.Message_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Perform_action = readString(dis);

					this.Acquittement = readString(dis);

					this.Complements_d_informations = readString(dis);

					this.Ref_Service = readString(dis);

					this.Objet = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.N_Ref = readInteger(dis);

					this.Ref = readInteger(dis);

					this.Etat = readString(dis);

					this.Signification = readString(dis);

					this.Identification = readString(dis);

					this.Criticitee = readString(dis);

					this.Message_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Perform_action = readString(dis);

					this.Acquittement = readString(dis);

					this.Complements_d_informations = readString(dis);

					this.Ref_Service = readString(dis);

					this.Objet = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.id, dos);

				// Integer

				writeInteger(this.N_Ref, dos);

				// Integer

				writeInteger(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Signification, dos);

				// String

				writeString(this.Identification, dos);

				// String

				writeString(this.Criticitee, dos);

				// String

				writeString(this.Message_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Acquittement, dos);

				// String

				writeString(this.Complements_d_informations, dos);

				// String

				writeString(this.Ref_Service, dos);

				// String

				writeString(this.Objet, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// Integer

				writeInteger(this.N_Ref, dos);

				// Integer

				writeInteger(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Signification, dos);

				// String

				writeString(this.Identification, dos);

				// String

				writeString(this.Criticitee, dos);

				// String

				writeString(this.Message_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Acquittement, dos);

				// String

				writeString(this.Complements_d_informations, dos);

				// String

				writeString(this.Ref_Service, dos);

				// String

				writeString(this.Objet, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",N_Ref=" + String.valueOf(N_Ref));
			sb.append(",Ref=" + String.valueOf(Ref));
			sb.append(",Etat=" + Etat);
			sb.append(",Signification=" + Signification);
			sb.append(",Identification=" + Identification);
			sb.append(",Criticitee=" + Criticitee);
			sb.append(",Message_alarme=" + Message_alarme);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Perform_action=" + Perform_action);
			sb.append(",Acquittement=" + Acquittement);
			sb.append(",Complements_d_informations=" + Complements_d_informations);
			sb.append(",Ref_Service=" + Ref_Service);
			sb.append(",Objet=" + Objet);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(insertlog_files_patternsStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id, other.id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row27Struct implements routines.system.IPersistableRow<row27Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String Log_Ref;

		public String getLog_Ref() {
			return this.Log_Ref;
		}

		public Integer N__Ref;

		public Integer getN__Ref() {
			return this.N__Ref;
		}

		public Integer Ref;

		public Integer getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Signification;

		public String getSignification() {
			return this.Signification;
		}

		public String Identification;

		public String getIdentification() {
			return this.Identification;
		}

		public String Criticite;

		public String getCriticite() {
			return this.Criticite;
		}

		public String Message_d_alarme;

		public String getMessage_d_alarme() {
			return this.Message_d_alarme;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Perform_action;

		public String getPerform_action() {
			return this.Perform_action;
		}

		public String Acquittement;

		public String getAcquittement() {
			return this.Acquittement;
		}

		public String Complements_d_informations;

		public String getComplements_d_informations() {
			return this.Complements_d_informations;
		}

		public String Ref__Service;

		public String getRef__Service() {
			return this.Ref__Service;
		}

		public String Objet;

		public String getObjet() {
			return this.Objet;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.Log_Ref = readString(dis);

					this.N__Ref = readInteger(dis);

					this.Ref = readInteger(dis);

					this.Etat = readString(dis);

					this.Signification = readString(dis);

					this.Identification = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Perform_action = readString(dis);

					this.Acquittement = readString(dis);

					this.Complements_d_informations = readString(dis);

					this.Ref__Service = readString(dis);

					this.Objet = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.Log_Ref = readString(dis);

					this.N__Ref = readInteger(dis);

					this.Ref = readInteger(dis);

					this.Etat = readString(dis);

					this.Signification = readString(dis);

					this.Identification = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Perform_action = readString(dis);

					this.Acquittement = readString(dis);

					this.Complements_d_informations = readString(dis);

					this.Ref__Service = readString(dis);

					this.Objet = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Log_Ref, dos);

				// Integer

				writeInteger(this.N__Ref, dos);

				// Integer

				writeInteger(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Signification, dos);

				// String

				writeString(this.Identification, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Acquittement, dos);

				// String

				writeString(this.Complements_d_informations, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Objet, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Log_Ref, dos);

				// Integer

				writeInteger(this.N__Ref, dos);

				// Integer

				writeInteger(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Signification, dos);

				// String

				writeString(this.Identification, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Acquittement, dos);

				// String

				writeString(this.Complements_d_informations, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Objet, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Log_Ref=" + Log_Ref);
			sb.append(",N__Ref=" + String.valueOf(N__Ref));
			sb.append(",Ref=" + String.valueOf(Ref));
			sb.append(",Etat=" + Etat);
			sb.append(",Signification=" + Signification);
			sb.append(",Identification=" + Identification);
			sb.append(",Criticite=" + Criticite);
			sb.append(",Message_d_alarme=" + Message_d_alarme);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Perform_action=" + Perform_action);
			sb.append(",Acquittement=" + Acquittement);
			sb.append(",Complements_d_informations=" + Complements_d_informations);
			sb.append(",Ref__Service=" + Ref__Service);
			sb.append(",Objet=" + Objet);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row27Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row18Struct implements routines.system.IPersistableRow<row18Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String Log_Ref;

		public String getLog_Ref() {
			return this.Log_Ref;
		}

		public Integer N__Ref;

		public Integer getN__Ref() {
			return this.N__Ref;
		}

		public Integer Ref;

		public Integer getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Signification;

		public String getSignification() {
			return this.Signification;
		}

		public String Identification;

		public String getIdentification() {
			return this.Identification;
		}

		public String Criticite;

		public String getCriticite() {
			return this.Criticite;
		}

		public String Message_d_alarme;

		public String getMessage_d_alarme() {
			return this.Message_d_alarme;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Perform_action;

		public String getPerform_action() {
			return this.Perform_action;
		}

		public String Acquittement;

		public String getAcquittement() {
			return this.Acquittement;
		}

		public String Complements_d_informations;

		public String getComplements_d_informations() {
			return this.Complements_d_informations;
		}

		public String Ref__Service;

		public String getRef__Service() {
			return this.Ref__Service;
		}

		public String Objet;

		public String getObjet() {
			return this.Objet;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.Log_Ref = readString(dis);

					this.N__Ref = readInteger(dis);

					this.Ref = readInteger(dis);

					this.Etat = readString(dis);

					this.Signification = readString(dis);

					this.Identification = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Perform_action = readString(dis);

					this.Acquittement = readString(dis);

					this.Complements_d_informations = readString(dis);

					this.Ref__Service = readString(dis);

					this.Objet = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.Log_Ref = readString(dis);

					this.N__Ref = readInteger(dis);

					this.Ref = readInteger(dis);

					this.Etat = readString(dis);

					this.Signification = readString(dis);

					this.Identification = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Perform_action = readString(dis);

					this.Acquittement = readString(dis);

					this.Complements_d_informations = readString(dis);

					this.Ref__Service = readString(dis);

					this.Objet = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Log_Ref, dos);

				// Integer

				writeInteger(this.N__Ref, dos);

				// Integer

				writeInteger(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Signification, dos);

				// String

				writeString(this.Identification, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Acquittement, dos);

				// String

				writeString(this.Complements_d_informations, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Objet, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Log_Ref, dos);

				// Integer

				writeInteger(this.N__Ref, dos);

				// Integer

				writeInteger(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Signification, dos);

				// String

				writeString(this.Identification, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Acquittement, dos);

				// String

				writeString(this.Complements_d_informations, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Objet, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Log_Ref=" + Log_Ref);
			sb.append(",N__Ref=" + String.valueOf(N__Ref));
			sb.append(",Ref=" + String.valueOf(Ref));
			sb.append(",Etat=" + Etat);
			sb.append(",Signification=" + Signification);
			sb.append(",Identification=" + Identification);
			sb.append(",Criticite=" + Criticite);
			sb.append(",Message_d_alarme=" + Message_d_alarme);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Perform_action=" + Perform_action);
			sb.append(",Acquittement=" + Acquittement);
			sb.append(",Complements_d_informations=" + Complements_d_informations);
			sb.append(",Ref__Service=" + Ref__Service);
			sb.append(",Objet=" + Objet);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row18Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row9Struct implements routines.system.IPersistableRow<row9Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String Log_Ref;

		public String getLog_Ref() {
			return this.Log_Ref;
		}

		public Integer N__Ref;

		public Integer getN__Ref() {
			return this.N__Ref;
		}

		public Integer Ref;

		public Integer getRef() {
			return this.Ref;
		}

		public String Etat;

		public String getEtat() {
			return this.Etat;
		}

		public String Signification;

		public String getSignification() {
			return this.Signification;
		}

		public String Identification;

		public String getIdentification() {
			return this.Identification;
		}

		public String Criticite;

		public String getCriticite() {
			return this.Criticite;
		}

		public String Message_d_alarme;

		public String getMessage_d_alarme() {
			return this.Message_d_alarme;
		}

		public String Instructions;

		public String getInstructions() {
			return this.Instructions;
		}

		public String Perform_action;

		public String getPerform_action() {
			return this.Perform_action;
		}

		public String Acquittement;

		public String getAcquittement() {
			return this.Acquittement;
		}

		public String Complements_d_informations;

		public String getComplements_d_informations() {
			return this.Complements_d_informations;
		}

		public String Ref__Service;

		public String getRef__Service() {
			return this.Ref__Service;
		}

		public String Objet;

		public String getObjet() {
			return this.Objet;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.Log_Ref = readString(dis);

					this.N__Ref = readInteger(dis);

					this.Ref = readInteger(dis);

					this.Etat = readString(dis);

					this.Signification = readString(dis);

					this.Identification = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Perform_action = readString(dis);

					this.Acquittement = readString(dis);

					this.Complements_d_informations = readString(dis);

					this.Ref__Service = readString(dis);

					this.Objet = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.Log_Ref = readString(dis);

					this.N__Ref = readInteger(dis);

					this.Ref = readInteger(dis);

					this.Etat = readString(dis);

					this.Signification = readString(dis);

					this.Identification = readString(dis);

					this.Criticite = readString(dis);

					this.Message_d_alarme = readString(dis);

					this.Instructions = readString(dis);

					this.Perform_action = readString(dis);

					this.Acquittement = readString(dis);

					this.Complements_d_informations = readString(dis);

					this.Ref__Service = readString(dis);

					this.Objet = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Log_Ref, dos);

				// Integer

				writeInteger(this.N__Ref, dos);

				// Integer

				writeInteger(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Signification, dos);

				// String

				writeString(this.Identification, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Acquittement, dos);

				// String

				writeString(this.Complements_d_informations, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Objet, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Log_Ref, dos);

				// Integer

				writeInteger(this.N__Ref, dos);

				// Integer

				writeInteger(this.Ref, dos);

				// String

				writeString(this.Etat, dos);

				// String

				writeString(this.Signification, dos);

				// String

				writeString(this.Identification, dos);

				// String

				writeString(this.Criticite, dos);

				// String

				writeString(this.Message_d_alarme, dos);

				// String

				writeString(this.Instructions, dos);

				// String

				writeString(this.Perform_action, dos);

				// String

				writeString(this.Acquittement, dos);

				// String

				writeString(this.Complements_d_informations, dos);

				// String

				writeString(this.Ref__Service, dos);

				// String

				writeString(this.Objet, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Log_Ref=" + Log_Ref);
			sb.append(",N__Ref=" + String.valueOf(N__Ref));
			sb.append(",Ref=" + String.valueOf(Ref));
			sb.append(",Etat=" + Etat);
			sb.append(",Signification=" + Signification);
			sb.append(",Identification=" + Identification);
			sb.append(",Criticite=" + Criticite);
			sb.append(",Message_d_alarme=" + Message_d_alarme);
			sb.append(",Instructions=" + Instructions);
			sb.append(",Perform_action=" + Perform_action);
			sb.append(",Acquittement=" + Acquittement);
			sb.append(",Complements_d_informations=" + Complements_d_informations);
			sb.append(",Ref__Service=" + Ref__Service);
			sb.append(",Objet=" + Objet);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row9Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class OnSubjobOkStructtIterateToFlow_3
			implements routines.system.IPersistableRow<OnSubjobOkStructtIterateToFlow_3> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String filepath3;

		public String getFilepath3() {
			return this.filepath3;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.filepath3 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.filepath3 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.filepath3, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.filepath3, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("filepath3=" + filepath3);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnSubjobOkStructtIterateToFlow_3 other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileList_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileList_3_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;
		String currentVirtualComponent = null;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row7Struct row7 = new row7Struct();
				row16Struct row16 = new row16Struct();
				row25Struct row25 = new row25Struct();
				inserttraps_snmpStruct inserttraps_snmp = new inserttraps_snmpStruct();
				row8Struct row8 = new row8Struct();
				row17Struct row17 = new row17Struct();
				row26Struct row26 = new row26Struct();
				insertserveursStruct insertserveurs = new insertserveursStruct();
				row9Struct row9 = new row9Struct();
				row18Struct row18 = new row18Struct();
				row27Struct row27 = new row27Struct();
				insertlog_files_patternsStruct insertlog_files_patterns = new insertlog_files_patternsStruct();

				/**
				 * [tFileList_3 begin ] start
				 */

				int NB_ITERATE_tFileInputExcel_3 = 0; // for statistics

				int NB_ITERATE_tFileInputExcel_39 = 0; // for statistics

				int NB_ITERATE_tFileInputExcel_31 = 0; // for statistics

				int NB_ITERATE_tIterateToFlow_3_ITFO = 0; // for statistics

				ok_Hash.put("tFileList_3", false);
				start_Hash.put("tFileList_3", System.currentTimeMillis());

				currentComponent = "tFileList_3";

				int tos_count_tFileList_3 = 0;

				String directory_tFileList_3 = "C:/Users/Majdi/Downloads/pasfait";
				final java.util.List<String> maskList_tFileList_3 = new java.util.ArrayList<String>();
				final java.util.List<java.util.regex.Pattern> patternList_tFileList_3 = new java.util.ArrayList<java.util.regex.Pattern>();
				maskList_tFileList_3.add("*.xlsx");
				for (final String filemask_tFileList_3 : maskList_tFileList_3) {
					String filemask_compile_tFileList_3 = filemask_tFileList_3;

					filemask_compile_tFileList_3 = org.apache.oro.text.GlobCompiler.globToPerl5(
							filemask_tFileList_3.toCharArray(), org.apache.oro.text.GlobCompiler.DEFAULT_MASK);

					java.util.regex.Pattern fileNamePattern_tFileList_3 = java.util.regex.Pattern
							.compile(filemask_compile_tFileList_3);
					patternList_tFileList_3.add(fileNamePattern_tFileList_3);
				}
				int NB_FILEtFileList_3 = 0;

				final boolean case_sensitive_tFileList_3 = true;

				final java.util.List<java.io.File> list_tFileList_3 = new java.util.ArrayList<java.io.File>();
				final java.util.Set<String> filePath_tFileList_3 = new java.util.HashSet<String>();
				java.io.File file_tFileList_3 = new java.io.File(directory_tFileList_3);

				file_tFileList_3.listFiles(new java.io.FilenameFilter() {
					public boolean accept(java.io.File dir, String name) {
						java.io.File file = new java.io.File(dir, name);
						if (!file.isDirectory()) {

							String fileName_tFileList_3 = file.getName();
							for (final java.util.regex.Pattern fileNamePattern_tFileList_3 : patternList_tFileList_3) {
								if (fileNamePattern_tFileList_3.matcher(fileName_tFileList_3).matches()) {
									if (!filePath_tFileList_3.contains(file.getAbsolutePath())) {
										list_tFileList_3.add(file);
										filePath_tFileList_3.add(file.getAbsolutePath());
									}
								}
							}
						}
						return true;
					}
				});
				java.util.Collections.sort(list_tFileList_3);

				for (int i_tFileList_3 = 0; i_tFileList_3 < list_tFileList_3.size(); i_tFileList_3++) {
					java.io.File files_tFileList_3 = list_tFileList_3.get(i_tFileList_3);
					String fileName_tFileList_3 = files_tFileList_3.getName();

					String currentFileName_tFileList_3 = files_tFileList_3.getName();
					String currentFilePath_tFileList_3 = files_tFileList_3.getAbsolutePath();
					String currentFileDirectory_tFileList_3 = files_tFileList_3.getParent();
					String currentFileExtension_tFileList_3 = null;

					if (files_tFileList_3.getName().contains(".") && files_tFileList_3.isFile()) {
						currentFileExtension_tFileList_3 = files_tFileList_3.getName()
								.substring(files_tFileList_3.getName().lastIndexOf(".") + 1);
					} else {
						currentFileExtension_tFileList_3 = "";
					}

					NB_FILEtFileList_3++;
					globalMap.put("tFileList_3_CURRENT_FILE", currentFileName_tFileList_3);
					globalMap.put("tFileList_3_CURRENT_FILEPATH", currentFilePath_tFileList_3);
					globalMap.put("tFileList_3_CURRENT_FILEDIRECTORY", currentFileDirectory_tFileList_3);
					globalMap.put("tFileList_3_CURRENT_FILEEXTENSION", currentFileExtension_tFileList_3);
					globalMap.put("tFileList_3_NB_FILE", NB_FILEtFileList_3);

					/**
					 * [tFileList_3 begin ] stop
					 */

					/**
					 * [tFileList_3 main ] start
					 */

					currentComponent = "tFileList_3";

					tos_count_tFileList_3++;

					/**
					 * [tFileList_3 main ] stop
					 */

					/**
					 * [tFileList_3 process_data_begin ] start
					 */

					currentComponent = "tFileList_3";

					/**
					 * [tFileList_3 process_data_begin ] stop
					 */
					NB_ITERATE_tFileInputExcel_39++;

					if (execStat) {
						runStat.updateStatOnConnection("inserttraps_snmp", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row25", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row16", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row7", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate8", 1, "exec" + NB_ITERATE_tFileInputExcel_39);
						// Thread.sleep(1000);
					}

					/**
					 * [tDBOutput_36 begin ] start
					 */

					ok_Hash.put("tDBOutput_36", false);
					start_Hash.put("tDBOutput_36", System.currentTimeMillis());

					currentComponent = "tDBOutput_36";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "inserttraps_snmp");
					}

					int tos_count_tDBOutput_36 = 0;

					int nb_line_tDBOutput_36 = 0;
					int nb_line_update_tDBOutput_36 = 0;
					int nb_line_inserted_tDBOutput_36 = 0;
					int nb_line_deleted_tDBOutput_36 = 0;
					int nb_line_rejected_tDBOutput_36 = 0;

					int deletedCount_tDBOutput_36 = 0;
					int updatedCount_tDBOutput_36 = 0;
					int insertedCount_tDBOutput_36 = 0;
					int rowsToCommitCount_tDBOutput_36 = 0;
					int rejectedCount_tDBOutput_36 = 0;

					String tableName_tDBOutput_36 = "traps_snmp";
					boolean whetherReject_tDBOutput_36 = false;

					java.util.Calendar calendar_tDBOutput_36 = java.util.Calendar.getInstance();
					calendar_tDBOutput_36.set(1, 0, 1, 0, 0, 0);
					long year1_tDBOutput_36 = calendar_tDBOutput_36.getTime().getTime();
					calendar_tDBOutput_36.set(10000, 0, 1, 0, 0, 0);
					long year10000_tDBOutput_36 = calendar_tDBOutput_36.getTime().getTime();
					long date_tDBOutput_36;

					java.sql.Connection conn_tDBOutput_36 = null;

					String properties_tDBOutput_36 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
					if (properties_tDBOutput_36 == null || properties_tDBOutput_36.trim().length() == 0) {
						properties_tDBOutput_36 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
					} else {
						if (!properties_tDBOutput_36.contains("rewriteBatchedStatements=")) {
							properties_tDBOutput_36 += "&rewriteBatchedStatements=true";
						}

						if (!properties_tDBOutput_36.contains("allowLoadLocalInfile=")) {
							properties_tDBOutput_36 += "&allowLoadLocalInfile=true";
						}
					}

					String url_tDBOutput_36 = "jdbc:mariadb://" + "127.0.0.1" + ":" + "3306" + "/" + "ooredoo" + "?"
							+ properties_tDBOutput_36;

					String driverClass_tDBOutput_36 = "org.mariadb.jdbc.Driver";

					String dbUser_tDBOutput_36 = "root";

					final String decryptedPassword_tDBOutput_36 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:EJfRGrwLU4xVTHhNd0E+9jCFATi6Na8Ib896Dg==");

					String dbPwd_tDBOutput_36 = decryptedPassword_tDBOutput_36;
					java.lang.Class.forName(driverClass_tDBOutput_36);

					conn_tDBOutput_36 = java.sql.DriverManager.getConnection(url_tDBOutput_36, dbUser_tDBOutput_36,
							dbPwd_tDBOutput_36);

					resourceMap.put("conn_tDBOutput_36", conn_tDBOutput_36);
					conn_tDBOutput_36.setAutoCommit(false);
					int commitEvery_tDBOutput_36 = 10000;
					int commitCounter_tDBOutput_36 = 0;

					int count_tDBOutput_36 = 0;

					String insert_tDBOutput_36 = "INSERT IGNORE INTO `" + "traps_snmp"
							+ "` (`id`,`Ref`,`Etat`,`Ref_composant`,`Signification`,`Version_SNMP`,`OID`,`Specific_Trap`,`Variable_binding`,`Cricite`,`Message_alarme`,`Instructions`,`acquittement`,`MIB_associe`,`Objet`,`Compelement_information`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

					java.sql.PreparedStatement pstmt_tDBOutput_36 = conn_tDBOutput_36
							.prepareStatement(insert_tDBOutput_36);
					resourceMap.put("pstmt_tDBOutput_36", pstmt_tDBOutput_36);

					/**
					 * [tDBOutput_36 begin ] stop
					 */

					/**
					 * [tMap_8 begin ] start
					 */

					ok_Hash.put("tMap_8", false);
					start_Hash.put("tMap_8", System.currentTimeMillis());

					currentComponent = "tMap_8";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row25");
					}

					int tos_count_tMap_8 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
					class Var__tMap_8__Struct {
					}
					Var__tMap_8__Struct Var__tMap_8 = new Var__tMap_8__Struct();
// ###############################

// ###############################
// # Outputs initialization
					inserttraps_snmpStruct inserttraps_snmp_tmp = new inserttraps_snmpStruct();
// ###############################

					/**
					 * [tMap_8 begin ] stop
					 */

					/**
					 * [tJavaRow_8 begin ] start
					 */

					ok_Hash.put("tJavaRow_8", false);
					start_Hash.put("tJavaRow_8", System.currentTimeMillis());

					currentComponent = "tJavaRow_8";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row16");
					}

					int tos_count_tJavaRow_8 = 0;

					int nb_line_tJavaRow_8 = 0;

					/**
					 * [tJavaRow_8 begin ] stop
					 */

					/**
					 * [tFilterRow_7 begin ] start
					 */

					ok_Hash.put("tFilterRow_7", false);
					start_Hash.put("tFilterRow_7", System.currentTimeMillis());

					currentComponent = "tFilterRow_7";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row7");
					}

					int tos_count_tFilterRow_7 = 0;

					int nb_line_tFilterRow_7 = 0;
					int nb_line_ok_tFilterRow_7 = 0;
					int nb_line_reject_tFilterRow_7 = 0;

					class Operator_tFilterRow_7 {
						private String sErrorMsg = "";
						private boolean bMatchFlag = true;
						private String sUnionFlag = "&&";

						public Operator_tFilterRow_7(String unionFlag) {
							sUnionFlag = unionFlag;
							bMatchFlag = "||".equals(unionFlag) ? false : true;
						}

						public String getErrorMsg() {
							if (sErrorMsg != null && sErrorMsg.length() > 1)
								return sErrorMsg.substring(1);
							else
								return null;
						}

						public boolean getMatchFlag() {
							return bMatchFlag;
						}

						public void matches(boolean partMatched, String reason) {
							// no need to care about the next judgement
							if ("||".equals(sUnionFlag) && bMatchFlag) {
								return;
							}

							if (!partMatched) {
								sErrorMsg += "|" + reason;
							}

							if ("||".equals(sUnionFlag))
								bMatchFlag = bMatchFlag || partMatched;
							else
								bMatchFlag = bMatchFlag && partMatched;
						}
					}

					/**
					 * [tFilterRow_7 begin ] stop
					 */

					/**
					 * [tFileInputExcel_39 begin ] start
					 */

					ok_Hash.put("tFileInputExcel_39", false);
					start_Hash.put("tFileInputExcel_39", System.currentTimeMillis());

					currentComponent = "tFileInputExcel_39";

					int tos_count_tFileInputExcel_39 = 0;

					final String decryptedPassword_tFileInputExcel_39 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:uEyVz6DJTjw9GLW8j974ClFNAAmz9ueshUAMYA==");
					String password_tFileInputExcel_39 = decryptedPassword_tFileInputExcel_39;
					if (password_tFileInputExcel_39.isEmpty()) {
						password_tFileInputExcel_39 = null;
					}
					class RegexUtil_tFileInputExcel_39 {

						public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
								org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, String oneSheetName,
								boolean useRegex) {

							java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();

							if (useRegex) {// this part process the regex issue

								java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
								for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
									String sheetName = sheet.getSheetName();
									java.util.regex.Matcher matcher = pattern.matcher(sheetName);
									if (matcher.matches()) {
										if (sheet != null) {
											list.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet);
										}
									}
								}

							} else {
								org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
										.getSheet(oneSheetName);
								if (sheet != null) {
									list.add(sheet);
								}

							}

							return list;
						}

						public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
								org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, int index, boolean useRegex) {
							java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
							org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
									.getSheetAt(index);
							if (sheet != null) {
								list.add(sheet);
							}
							return list;
						}

					}
					RegexUtil_tFileInputExcel_39 regexUtil_tFileInputExcel_39 = new RegexUtil_tFileInputExcel_39();

					Object source_tFileInputExcel_39 = ((String) globalMap.get("tFileList_3_CURRENT_FILEPATH"));
					org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_39 = null;

					if (source_tFileInputExcel_39 instanceof String) {
						workbook_tFileInputExcel_39 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
								.create(new java.io.File((String) source_tFileInputExcel_39),
										password_tFileInputExcel_39, true);
					} else if (source_tFileInputExcel_39 instanceof java.io.InputStream) {
						workbook_tFileInputExcel_39 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
								.create((java.io.InputStream) source_tFileInputExcel_39, password_tFileInputExcel_39);
					} else {
						workbook_tFileInputExcel_39 = null;
						throw new java.lang.Exception(
								"The data source should be specified as Inputstream or File Path!");
					}
					try {

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_39 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						sheetList_tFileInputExcel_39.addAll(regexUtil_tFileInputExcel_39
								.getSheets(workbook_tFileInputExcel_39, "Traps SNMP", false));
						if (sheetList_tFileInputExcel_39.size() <= 0) {
							throw new RuntimeException("Special sheets not exist!");
						}

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_39 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_39 : sheetList_tFileInputExcel_39) {
							if (sheet_FilterNull_tFileInputExcel_39 != null
									&& sheetList_FilterNull_tFileInputExcel_39.iterator() != null
									&& sheet_FilterNull_tFileInputExcel_39.iterator().hasNext()) {
								sheetList_FilterNull_tFileInputExcel_39.add(sheet_FilterNull_tFileInputExcel_39);
							}
						}
						sheetList_tFileInputExcel_39 = sheetList_FilterNull_tFileInputExcel_39;
						if (sheetList_tFileInputExcel_39.size() > 0) {
							int nb_line_tFileInputExcel_39 = 0;

							int begin_line_tFileInputExcel_39 = 1;

							int footer_input_tFileInputExcel_39 = 0;

							int end_line_tFileInputExcel_39 = 0;
							for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_39 : sheetList_tFileInputExcel_39) {
								end_line_tFileInputExcel_39 += (sheet_tFileInputExcel_39.getLastRowNum() + 1);
							}
							end_line_tFileInputExcel_39 -= footer_input_tFileInputExcel_39;
							int limit_tFileInputExcel_39 = -1;
							int start_column_tFileInputExcel_39 = 1 - 1;
							int end_column_tFileInputExcel_39 = -1;

							org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_39 = null;
							org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_39 = sheetList_tFileInputExcel_39
									.get(0);
							int rowCount_tFileInputExcel_39 = 0;
							int sheetIndex_tFileInputExcel_39 = 0;
							int currentRows_tFileInputExcel_39 = (sheetList_tFileInputExcel_39.get(0).getLastRowNum()
									+ 1);

							// for the number format
							java.text.DecimalFormat df_tFileInputExcel_39 = new java.text.DecimalFormat(
									"#.####################################");
							char decimalChar_tFileInputExcel_39 = df_tFileInputExcel_39.getDecimalFormatSymbols()
									.getDecimalSeparator();

							for (int i_tFileInputExcel_39 = begin_line_tFileInputExcel_39; i_tFileInputExcel_39 < end_line_tFileInputExcel_39; i_tFileInputExcel_39++) {

								int emptyColumnCount_tFileInputExcel_39 = 0;

								if (limit_tFileInputExcel_39 != -1
										&& nb_line_tFileInputExcel_39 >= limit_tFileInputExcel_39) {
									break;
								}

								while (i_tFileInputExcel_39 >= rowCount_tFileInputExcel_39
										+ currentRows_tFileInputExcel_39) {
									rowCount_tFileInputExcel_39 += currentRows_tFileInputExcel_39;
									sheet_tFileInputExcel_39 = sheetList_tFileInputExcel_39
											.get(++sheetIndex_tFileInputExcel_39);
									currentRows_tFileInputExcel_39 = (sheet_tFileInputExcel_39.getLastRowNum() + 1);
								}
								globalMap.put("tFileInputExcel_39_CURRENT_SHEET",
										sheet_tFileInputExcel_39.getSheetName());
								if (rowCount_tFileInputExcel_39 <= i_tFileInputExcel_39) {
									row_tFileInputExcel_39 = sheet_tFileInputExcel_39
											.getRow(i_tFileInputExcel_39 - rowCount_tFileInputExcel_39);
								}
								row7 = null;
								int tempRowLength_tFileInputExcel_39 = 16;

								int columnIndex_tFileInputExcel_39 = 0;

								String[] temp_row_tFileInputExcel_39 = new String[tempRowLength_tFileInputExcel_39];
								int excel_end_column_tFileInputExcel_39;
								if (row_tFileInputExcel_39 == null) {
									excel_end_column_tFileInputExcel_39 = 0;
								} else {
									excel_end_column_tFileInputExcel_39 = row_tFileInputExcel_39.getLastCellNum();
								}
								int actual_end_column_tFileInputExcel_39;
								if (end_column_tFileInputExcel_39 == -1) {
									actual_end_column_tFileInputExcel_39 = excel_end_column_tFileInputExcel_39;
								} else {
									actual_end_column_tFileInputExcel_39 = end_column_tFileInputExcel_39 > excel_end_column_tFileInputExcel_39
											? excel_end_column_tFileInputExcel_39
											: end_column_tFileInputExcel_39;
								}
								org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_39 = null;
								for (int i = 0; i < tempRowLength_tFileInputExcel_39; i++) {
									if (i + start_column_tFileInputExcel_39 < actual_end_column_tFileInputExcel_39) {
										org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_39 = row_tFileInputExcel_39
												.getCell(i + start_column_tFileInputExcel_39);
										if (cell_tFileInputExcel_39 != null) {
											switch (cell_tFileInputExcel_39.getCellType()) {
											case STRING:
												temp_row_tFileInputExcel_39[i] = cell_tFileInputExcel_39
														.getRichStringCellValue().getString();
												break;
											case NUMERIC:
												if (org.apache.poi.ss.usermodel.DateUtil
														.isCellDateFormatted(cell_tFileInputExcel_39)) {
													temp_row_tFileInputExcel_39[i] = cell_tFileInputExcel_39
															.getDateCellValue().toString();
												} else {
													temp_row_tFileInputExcel_39[i] = df_tFileInputExcel_39
															.format(cell_tFileInputExcel_39.getNumericCellValue());
												}
												break;
											case BOOLEAN:
												temp_row_tFileInputExcel_39[i] = String
														.valueOf(cell_tFileInputExcel_39.getBooleanCellValue());
												break;
											case FORMULA:
												switch (cell_tFileInputExcel_39.getCachedFormulaResultType()) {
												case STRING:
													temp_row_tFileInputExcel_39[i] = cell_tFileInputExcel_39
															.getRichStringCellValue().getString();
													break;
												case NUMERIC:
													if (org.apache.poi.ss.usermodel.DateUtil
															.isCellDateFormatted(cell_tFileInputExcel_39)) {
														temp_row_tFileInputExcel_39[i] = cell_tFileInputExcel_39
																.getDateCellValue().toString();
													} else {
														ne_tFileInputExcel_39 = new org.apache.poi.ss.formula.eval.NumberEval(
																cell_tFileInputExcel_39.getNumericCellValue());
														temp_row_tFileInputExcel_39[i] = ne_tFileInputExcel_39
																.getStringValue();
													}
													break;
												case BOOLEAN:
													temp_row_tFileInputExcel_39[i] = String
															.valueOf(cell_tFileInputExcel_39.getBooleanCellValue());
													break;
												default:
													temp_row_tFileInputExcel_39[i] = "";
												}
												break;
											default:
												temp_row_tFileInputExcel_39[i] = "";
											}
										} else {
											temp_row_tFileInputExcel_39[i] = "";
										}

									} else {
										temp_row_tFileInputExcel_39[i] = "";
									}
								}
								boolean whetherReject_tFileInputExcel_39 = false;
								row7 = new row7Struct();
								int curColNum_tFileInputExcel_39 = -1;
								String curColName_tFileInputExcel_39 = "";
								try {
									columnIndex_tFileInputExcel_39 = 0;

									if (temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39].length() > 0) {
										curColNum_tFileInputExcel_39 = columnIndex_tFileInputExcel_39
												+ start_column_tFileInputExcel_39 + 1;
										curColName_tFileInputExcel_39 = "N__Ref";

										row7.N__Ref = temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39];
									} else {
										row7.N__Ref = null;
										emptyColumnCount_tFileInputExcel_39++;
									}
									columnIndex_tFileInputExcel_39 = 1;

									if (temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39].length() > 0) {
										curColNum_tFileInputExcel_39 = columnIndex_tFileInputExcel_39
												+ start_column_tFileInputExcel_39 + 1;
										curColName_tFileInputExcel_39 = "Ref";

										row7.Ref = temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39];
									} else {
										row7.Ref = null;
										emptyColumnCount_tFileInputExcel_39++;
									}
									columnIndex_tFileInputExcel_39 = 2;

									if (temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39].length() > 0) {
										curColNum_tFileInputExcel_39 = columnIndex_tFileInputExcel_39
												+ start_column_tFileInputExcel_39 + 1;
										curColName_tFileInputExcel_39 = "Etat";

										row7.Etat = temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39];
									} else {
										row7.Etat = null;
										emptyColumnCount_tFileInputExcel_39++;
									}
									columnIndex_tFileInputExcel_39 = 3;

									if (temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39].length() > 0) {
										curColNum_tFileInputExcel_39 = columnIndex_tFileInputExcel_39
												+ start_column_tFileInputExcel_39 + 1;
										curColName_tFileInputExcel_39 = "Ref__des_composants";

										row7.Ref__des_composants = temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39];
									} else {
										row7.Ref__des_composants = null;
										emptyColumnCount_tFileInputExcel_39++;
									}
									columnIndex_tFileInputExcel_39 = 4;

									if (temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39].length() > 0) {
										curColNum_tFileInputExcel_39 = columnIndex_tFileInputExcel_39
												+ start_column_tFileInputExcel_39 + 1;
										curColName_tFileInputExcel_39 = "Signification";

										row7.Signification = temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39];
									} else {
										row7.Signification = null;
										emptyColumnCount_tFileInputExcel_39++;
									}
									columnIndex_tFileInputExcel_39 = 5;

									if (temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39].length() > 0) {
										curColNum_tFileInputExcel_39 = columnIndex_tFileInputExcel_39
												+ start_column_tFileInputExcel_39 + 1;
										curColName_tFileInputExcel_39 = "Version_SNMP";

										row7.Version_SNMP = temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39];
									} else {
										row7.Version_SNMP = null;
										emptyColumnCount_tFileInputExcel_39++;
									}
									columnIndex_tFileInputExcel_39 = 6;

									if (temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39].length() > 0) {
										curColNum_tFileInputExcel_39 = columnIndex_tFileInputExcel_39
												+ start_column_tFileInputExcel_39 + 1;
										curColName_tFileInputExcel_39 = "OID";

										row7.OID = temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39];
									} else {
										row7.OID = null;
										emptyColumnCount_tFileInputExcel_39++;
									}
									columnIndex_tFileInputExcel_39 = 7;

									if (temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39].length() > 0) {
										curColNum_tFileInputExcel_39 = columnIndex_tFileInputExcel_39
												+ start_column_tFileInputExcel_39 + 1;
										curColName_tFileInputExcel_39 = "Specific_trap";

										row7.Specific_trap = temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39];
									} else {
										row7.Specific_trap = null;
										emptyColumnCount_tFileInputExcel_39++;
									}
									columnIndex_tFileInputExcel_39 = 8;

									if (temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39].length() > 0) {
										curColNum_tFileInputExcel_39 = columnIndex_tFileInputExcel_39
												+ start_column_tFileInputExcel_39 + 1;
										curColName_tFileInputExcel_39 = "Variable_s__binding";

										row7.Variable_s__binding = temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39];
									} else {
										row7.Variable_s__binding = null;
										emptyColumnCount_tFileInputExcel_39++;
									}
									columnIndex_tFileInputExcel_39 = 9;

									if (temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39].length() > 0) {
										curColNum_tFileInputExcel_39 = columnIndex_tFileInputExcel_39
												+ start_column_tFileInputExcel_39 + 1;
										curColName_tFileInputExcel_39 = "Criticite";

										row7.Criticite = temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39];
									} else {
										row7.Criticite = null;
										emptyColumnCount_tFileInputExcel_39++;
									}
									columnIndex_tFileInputExcel_39 = 10;

									if (temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39].length() > 0) {
										curColNum_tFileInputExcel_39 = columnIndex_tFileInputExcel_39
												+ start_column_tFileInputExcel_39 + 1;
										curColName_tFileInputExcel_39 = "Message_d_alarme";

										row7.Message_d_alarme = temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39];
									} else {
										row7.Message_d_alarme = null;
										emptyColumnCount_tFileInputExcel_39++;
									}
									columnIndex_tFileInputExcel_39 = 11;

									if (temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39].length() > 0) {
										curColNum_tFileInputExcel_39 = columnIndex_tFileInputExcel_39
												+ start_column_tFileInputExcel_39 + 1;
										curColName_tFileInputExcel_39 = "Instructions";

										row7.Instructions = temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39];
									} else {
										row7.Instructions = null;
										emptyColumnCount_tFileInputExcel_39++;
									}
									columnIndex_tFileInputExcel_39 = 12;

									if (temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39].length() > 0) {
										curColNum_tFileInputExcel_39 = columnIndex_tFileInputExcel_39
												+ start_column_tFileInputExcel_39 + 1;
										curColName_tFileInputExcel_39 = "Acquittement";

										row7.Acquittement = temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39];
									} else {
										row7.Acquittement = null;
										emptyColumnCount_tFileInputExcel_39++;
									}
									columnIndex_tFileInputExcel_39 = 13;

									if (temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39].length() > 0) {
										curColNum_tFileInputExcel_39 = columnIndex_tFileInputExcel_39
												+ start_column_tFileInputExcel_39 + 1;
										curColName_tFileInputExcel_39 = "MIB_associee_s";

										row7.MIB_associee_s = temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39];
									} else {
										row7.MIB_associee_s = null;
										emptyColumnCount_tFileInputExcel_39++;
									}
									columnIndex_tFileInputExcel_39 = 14;

									if (temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39].length() > 0) {
										curColNum_tFileInputExcel_39 = columnIndex_tFileInputExcel_39
												+ start_column_tFileInputExcel_39 + 1;
										curColName_tFileInputExcel_39 = "Objet";

										row7.Objet = temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39];
									} else {
										row7.Objet = null;
										emptyColumnCount_tFileInputExcel_39++;
									}
									columnIndex_tFileInputExcel_39 = 15;

									if (temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39].length() > 0) {
										curColNum_tFileInputExcel_39 = columnIndex_tFileInputExcel_39
												+ start_column_tFileInputExcel_39 + 1;
										curColName_tFileInputExcel_39 = "Complements_d_informations";

										row7.Complements_d_informations = temp_row_tFileInputExcel_39[columnIndex_tFileInputExcel_39];
									} else {
										row7.Complements_d_informations = null;
										emptyColumnCount_tFileInputExcel_39++;
									}

									nb_line_tFileInputExcel_39++;

								} catch (java.lang.Exception e) {
									globalMap.put("tFileInputExcel_39_ERROR_MESSAGE", e.getMessage());
									whetherReject_tFileInputExcel_39 = true;
									System.err.println(e.getMessage());
									row7 = null;
								}

								/**
								 * [tFileInputExcel_39 begin ] stop
								 */

								/**
								 * [tFileInputExcel_39 main ] start
								 */

								currentComponent = "tFileInputExcel_39";

								tos_count_tFileInputExcel_39++;

								/**
								 * [tFileInputExcel_39 main ] stop
								 */

								/**
								 * [tFileInputExcel_39 process_data_begin ] start
								 */

								currentComponent = "tFileInputExcel_39";

								/**
								 * [tFileInputExcel_39 process_data_begin ] stop
								 */
// Start of branch "row7"
								if (row7 != null) {

									/**
									 * [tFilterRow_7 main ] start
									 */

									currentComponent = "tFilterRow_7";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "row7"

										);
									}

									row16 = null;
									Operator_tFilterRow_7 ope_tFilterRow_7 = new Operator_tFilterRow_7("&&");
									ope_tFilterRow_7.matches((row7.Ref != null), "Ref!=null failed");

									if (ope_tFilterRow_7.getMatchFlag()) {
										if (row16 == null) {
											row16 = new row16Struct();
										}
										row16.N__Ref = row7.N__Ref;
										row16.Ref = row7.Ref;
										row16.Etat = row7.Etat;
										row16.Ref__des_composants = row7.Ref__des_composants;
										row16.Signification = row7.Signification;
										row16.Version_SNMP = row7.Version_SNMP;
										row16.OID = row7.OID;
										row16.Specific_trap = row7.Specific_trap;
										row16.Variable_s__binding = row7.Variable_s__binding;
										row16.Criticite = row7.Criticite;
										row16.Message_d_alarme = row7.Message_d_alarme;
										row16.Instructions = row7.Instructions;
										row16.Acquittement = row7.Acquittement;
										row16.MIB_associee_s = row7.MIB_associee_s;
										row16.Objet = row7.Objet;
										row16.Complements_d_informations = row7.Complements_d_informations;
										nb_line_ok_tFilterRow_7++;
									} else {
										nb_line_reject_tFilterRow_7++;
									}

									nb_line_tFilterRow_7++;

									tos_count_tFilterRow_7++;

									/**
									 * [tFilterRow_7 main ] stop
									 */

									/**
									 * [tFilterRow_7 process_data_begin ] start
									 */

									currentComponent = "tFilterRow_7";

									/**
									 * [tFilterRow_7 process_data_begin ] stop
									 */
// Start of branch "row16"
									if (row16 != null) {

										/**
										 * [tJavaRow_8 main ] start
										 */

										currentComponent = "tJavaRow_8";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row16"

											);
										}

										String fileName = (String) source_tFileInputExcel_39;

// Extraire la partie "POSANET" du nom du fichier Excel
										String[] parts = fileName.split("_");
										String fileWord = parts[6];

// Récupérer la valeur de "N__Ref" et la convertir en String
										String rowRef = row16.N__Ref;

// Générer l'ID "EDB_POSANET_X"
										String id = "EDB_" + fileWord + "_TrapsSNMP_" + rowRef;
//Code généré selon les schémas d'entrée et de sortie
										row25.N__Ref = id;
										row25.Ref = row16.Ref;
										row25.Etat = row16.Etat;
										row25.Ref__des_composants = row16.Ref__des_composants;
										row25.Signification = row16.Signification;
										row25.Version_SNMP = row16.Version_SNMP;
										row25.OID = row16.OID;
										row25.Specific_trap = row16.Specific_trap;
										row25.Variable_s__binding = row16.Variable_s__binding;
										row25.Criticite = row16.Criticite;
										row25.Message_d_alarme = row16.Message_d_alarme;
										row25.Instructions = row16.Instructions;
										row25.Acquittement = row16.Acquittement;
										row25.MIB_associee_s = row16.MIB_associee_s;
										row25.Objet = row16.Objet;
										row25.Complements_d_informations = row16.Complements_d_informations;

										nb_line_tJavaRow_8++;

										tos_count_tJavaRow_8++;

										/**
										 * [tJavaRow_8 main ] stop
										 */

										/**
										 * [tJavaRow_8 process_data_begin ] start
										 */

										currentComponent = "tJavaRow_8";

										/**
										 * [tJavaRow_8 process_data_begin ] stop
										 */

										/**
										 * [tMap_8 main ] start
										 */

										currentComponent = "tMap_8";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row25"

											);
										}

										boolean hasCasePrimitiveKeyWithNull_tMap_8 = false;

										// ###############################
										// # Input tables (lookups)
										boolean rejectedInnerJoin_tMap_8 = false;
										boolean mainRowRejected_tMap_8 = false;

										// ###############################
										{ // start of Var scope

											// ###############################
											// # Vars tables

											Var__tMap_8__Struct Var = Var__tMap_8;// ###############################
											// ###############################
											// # Output tables

											inserttraps_snmp = null;

// # Output table : 'inserttraps_snmp'
											inserttraps_snmp_tmp.id = row25.N__Ref;
											inserttraps_snmp_tmp.Ref = row25.Ref;
											inserttraps_snmp_tmp.Etat = row25.Etat;
											inserttraps_snmp_tmp.Ref_composant = row25.Ref__des_composants;
											inserttraps_snmp_tmp.Signification = row25.Signification;
											inserttraps_snmp_tmp.Version_SNMP = row25.Version_SNMP;
											inserttraps_snmp_tmp.OID = row25.OID;
											inserttraps_snmp_tmp.Specific_Trap = row25.Specific_trap;
											inserttraps_snmp_tmp.Variable_binding = row25.Variable_s__binding;
											inserttraps_snmp_tmp.Cricite = row25.Criticite;
											inserttraps_snmp_tmp.Message_alarme = row25.Message_d_alarme;
											inserttraps_snmp_tmp.Instructions = row25.Instructions;
											inserttraps_snmp_tmp.acquittement = row25.Acquittement;
											inserttraps_snmp_tmp.MIB_associe = row25.MIB_associee_s;
											inserttraps_snmp_tmp.Objet = row25.Objet;
											inserttraps_snmp_tmp.Compelement_information = row25.Complements_d_informations;
											inserttraps_snmp = inserttraps_snmp_tmp;
// ###############################

										} // end of Var scope

										rejectedInnerJoin_tMap_8 = false;

										tos_count_tMap_8++;

										/**
										 * [tMap_8 main ] stop
										 */

										/**
										 * [tMap_8 process_data_begin ] start
										 */

										currentComponent = "tMap_8";

										/**
										 * [tMap_8 process_data_begin ] stop
										 */
// Start of branch "inserttraps_snmp"
										if (inserttraps_snmp != null) {

											/**
											 * [tDBOutput_36 main ] start
											 */

											currentComponent = "tDBOutput_36";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "inserttraps_snmp"

												);
											}

											whetherReject_tDBOutput_36 = false;
											if (inserttraps_snmp.id == null) {
												pstmt_tDBOutput_36.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_36.setString(1, inserttraps_snmp.id);
											}

											if (inserttraps_snmp.Ref == null) {
												pstmt_tDBOutput_36.setNull(2, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_36.setString(2, inserttraps_snmp.Ref);
											}

											if (inserttraps_snmp.Etat == null) {
												pstmt_tDBOutput_36.setNull(3, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_36.setString(3, inserttraps_snmp.Etat);
											}

											if (inserttraps_snmp.Ref_composant == null) {
												pstmt_tDBOutput_36.setNull(4, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_36.setString(4, inserttraps_snmp.Ref_composant);
											}

											if (inserttraps_snmp.Signification == null) {
												pstmt_tDBOutput_36.setNull(5, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_36.setString(5, inserttraps_snmp.Signification);
											}

											if (inserttraps_snmp.Version_SNMP == null) {
												pstmt_tDBOutput_36.setNull(6, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_36.setString(6, inserttraps_snmp.Version_SNMP);
											}

											if (inserttraps_snmp.OID == null) {
												pstmt_tDBOutput_36.setNull(7, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_36.setString(7, inserttraps_snmp.OID);
											}

											if (inserttraps_snmp.Specific_Trap == null) {
												pstmt_tDBOutput_36.setNull(8, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_36.setString(8, inserttraps_snmp.Specific_Trap);
											}

											if (inserttraps_snmp.Variable_binding == null) {
												pstmt_tDBOutput_36.setNull(9, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_36.setString(9, inserttraps_snmp.Variable_binding);
											}

											if (inserttraps_snmp.Cricite == null) {
												pstmt_tDBOutput_36.setNull(10, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_36.setString(10, inserttraps_snmp.Cricite);
											}

											if (inserttraps_snmp.Message_alarme == null) {
												pstmt_tDBOutput_36.setNull(11, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_36.setString(11, inserttraps_snmp.Message_alarme);
											}

											if (inserttraps_snmp.Instructions == null) {
												pstmt_tDBOutput_36.setNull(12, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_36.setString(12, inserttraps_snmp.Instructions);
											}

											if (inserttraps_snmp.acquittement == null) {
												pstmt_tDBOutput_36.setNull(13, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_36.setString(13, inserttraps_snmp.acquittement);
											}

											if (inserttraps_snmp.MIB_associe == null) {
												pstmt_tDBOutput_36.setNull(14, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_36.setString(14, inserttraps_snmp.MIB_associe);
											}

											if (inserttraps_snmp.Objet == null) {
												pstmt_tDBOutput_36.setNull(15, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_36.setString(15, inserttraps_snmp.Objet);
											}

											if (inserttraps_snmp.Compelement_information == null) {
												pstmt_tDBOutput_36.setNull(16, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_36.setString(16,
														inserttraps_snmp.Compelement_information);
											}

											try {
												nb_line_tDBOutput_36++;
												int processedCount_tDBOutput_36 = pstmt_tDBOutput_36.executeUpdate();
												insertedCount_tDBOutput_36 += processedCount_tDBOutput_36;
												rowsToCommitCount_tDBOutput_36 += processedCount_tDBOutput_36;
											} catch (java.lang.Exception e) {
												globalMap.put("tDBOutput_36_ERROR_MESSAGE", e.getMessage());
												whetherReject_tDBOutput_36 = true;
												System.err.print(e.getMessage());
											}
											commitCounter_tDBOutput_36++;

											if (commitEvery_tDBOutput_36 <= commitCounter_tDBOutput_36) {

												if (rowsToCommitCount_tDBOutput_36 != 0) {
												}
												conn_tDBOutput_36.commit();
												if (rowsToCommitCount_tDBOutput_36 != 0) {
													rowsToCommitCount_tDBOutput_36 = 0;
												}
												commitCounter_tDBOutput_36 = 0;

											}

											tos_count_tDBOutput_36++;

											/**
											 * [tDBOutput_36 main ] stop
											 */

											/**
											 * [tDBOutput_36 process_data_begin ] start
											 */

											currentComponent = "tDBOutput_36";

											/**
											 * [tDBOutput_36 process_data_begin ] stop
											 */

											/**
											 * [tDBOutput_36 process_data_end ] start
											 */

											currentComponent = "tDBOutput_36";

											/**
											 * [tDBOutput_36 process_data_end ] stop
											 */

										} // End of branch "inserttraps_snmp"

										/**
										 * [tMap_8 process_data_end ] start
										 */

										currentComponent = "tMap_8";

										/**
										 * [tMap_8 process_data_end ] stop
										 */

										/**
										 * [tJavaRow_8 process_data_end ] start
										 */

										currentComponent = "tJavaRow_8";

										/**
										 * [tJavaRow_8 process_data_end ] stop
										 */

									} // End of branch "row16"

									/**
									 * [tFilterRow_7 process_data_end ] start
									 */

									currentComponent = "tFilterRow_7";

									/**
									 * [tFilterRow_7 process_data_end ] stop
									 */

								} // End of branch "row7"

								/**
								 * [tFileInputExcel_39 process_data_end ] start
								 */

								currentComponent = "tFileInputExcel_39";

								/**
								 * [tFileInputExcel_39 process_data_end ] stop
								 */

								/**
								 * [tFileInputExcel_39 end ] start
								 */

								currentComponent = "tFileInputExcel_39";

							}

							globalMap.put("tFileInputExcel_39_NB_LINE", nb_line_tFileInputExcel_39);

						}

					} finally {

						if (!(source_tFileInputExcel_39 instanceof java.io.InputStream)) {
							workbook_tFileInputExcel_39.getPackage().revert();
						}

					}

					ok_Hash.put("tFileInputExcel_39", true);
					end_Hash.put("tFileInputExcel_39", System.currentTimeMillis());

					/**
					 * [tFileInputExcel_39 end ] stop
					 */

					/**
					 * [tFilterRow_7 end ] start
					 */

					currentComponent = "tFilterRow_7";

					globalMap.put("tFilterRow_7_NB_LINE", nb_line_tFilterRow_7);
					globalMap.put("tFilterRow_7_NB_LINE_OK", nb_line_ok_tFilterRow_7);
					globalMap.put("tFilterRow_7_NB_LINE_REJECT", nb_line_reject_tFilterRow_7);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row7");
					}

					ok_Hash.put("tFilterRow_7", true);
					end_Hash.put("tFilterRow_7", System.currentTimeMillis());

					/**
					 * [tFilterRow_7 end ] stop
					 */

					/**
					 * [tJavaRow_8 end ] start
					 */

					currentComponent = "tJavaRow_8";

					globalMap.put("tJavaRow_8_NB_LINE", nb_line_tJavaRow_8);
					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row16");
					}

					ok_Hash.put("tJavaRow_8", true);
					end_Hash.put("tJavaRow_8", System.currentTimeMillis());

					/**
					 * [tJavaRow_8 end ] stop
					 */

					/**
					 * [tMap_8 end ] start
					 */

					currentComponent = "tMap_8";

// ###############################
// # Lookup hashes releasing
// ###############################      

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row25");
					}

					ok_Hash.put("tMap_8", true);
					end_Hash.put("tMap_8", System.currentTimeMillis());

					/**
					 * [tMap_8 end ] stop
					 */

					/**
					 * [tDBOutput_36 end ] start
					 */

					currentComponent = "tDBOutput_36";

					if (pstmt_tDBOutput_36 != null) {

						pstmt_tDBOutput_36.close();
						resourceMap.remove("pstmt_tDBOutput_36");

					}
					resourceMap.put("statementClosed_tDBOutput_36", true);
					if (commitCounter_tDBOutput_36 > 0 && rowsToCommitCount_tDBOutput_36 != 0) {

					}
					conn_tDBOutput_36.commit();
					if (commitCounter_tDBOutput_36 > 0 && rowsToCommitCount_tDBOutput_36 != 0) {

						rowsToCommitCount_tDBOutput_36 = 0;
					}
					commitCounter_tDBOutput_36 = 0;

					conn_tDBOutput_36.close();

					resourceMap.put("finish_tDBOutput_36", true);

					nb_line_deleted_tDBOutput_36 = nb_line_deleted_tDBOutput_36 + deletedCount_tDBOutput_36;
					nb_line_update_tDBOutput_36 = nb_line_update_tDBOutput_36 + updatedCount_tDBOutput_36;
					nb_line_inserted_tDBOutput_36 = nb_line_inserted_tDBOutput_36 + insertedCount_tDBOutput_36;
					nb_line_rejected_tDBOutput_36 = nb_line_rejected_tDBOutput_36 + rejectedCount_tDBOutput_36;

					globalMap.put("tDBOutput_36_NB_LINE", nb_line_tDBOutput_36);
					globalMap.put("tDBOutput_36_NB_LINE_UPDATED", nb_line_update_tDBOutput_36);
					globalMap.put("tDBOutput_36_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_36);
					globalMap.put("tDBOutput_36_NB_LINE_DELETED", nb_line_deleted_tDBOutput_36);
					globalMap.put("tDBOutput_36_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_36);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "inserttraps_snmp");
					}

					ok_Hash.put("tDBOutput_36", true);
					end_Hash.put("tDBOutput_36", System.currentTimeMillis());

					/**
					 * [tDBOutput_36 end ] stop
					 */

					if (execStat) {
						runStat.updateStatOnConnection("iterate8", 2, "exec" + NB_ITERATE_tFileInputExcel_39);
					}

					NB_ITERATE_tFileInputExcel_31++;

					if (execStat) {
						runStat.updateStatOnConnection("row26", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("insertserveurs", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row8", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row17", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate9", 1, "exec" + NB_ITERATE_tFileInputExcel_31);
						// Thread.sleep(1000);
					}

					/**
					 * [tDBOutput_28 begin ] start
					 */

					ok_Hash.put("tDBOutput_28", false);
					start_Hash.put("tDBOutput_28", System.currentTimeMillis());

					currentComponent = "tDBOutput_28";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "insertserveurs");
					}

					int tos_count_tDBOutput_28 = 0;

					int nb_line_tDBOutput_28 = 0;
					int nb_line_update_tDBOutput_28 = 0;
					int nb_line_inserted_tDBOutput_28 = 0;
					int nb_line_deleted_tDBOutput_28 = 0;
					int nb_line_rejected_tDBOutput_28 = 0;

					int deletedCount_tDBOutput_28 = 0;
					int updatedCount_tDBOutput_28 = 0;
					int insertedCount_tDBOutput_28 = 0;
					int rowsToCommitCount_tDBOutput_28 = 0;
					int rejectedCount_tDBOutput_28 = 0;

					String tableName_tDBOutput_28 = "serveurs";
					boolean whetherReject_tDBOutput_28 = false;

					java.util.Calendar calendar_tDBOutput_28 = java.util.Calendar.getInstance();
					calendar_tDBOutput_28.set(1, 0, 1, 0, 0, 0);
					long year1_tDBOutput_28 = calendar_tDBOutput_28.getTime().getTime();
					calendar_tDBOutput_28.set(10000, 0, 1, 0, 0, 0);
					long year10000_tDBOutput_28 = calendar_tDBOutput_28.getTime().getTime();
					long date_tDBOutput_28;

					java.sql.Connection conn_tDBOutput_28 = null;

					String properties_tDBOutput_28 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
					if (properties_tDBOutput_28 == null || properties_tDBOutput_28.trim().length() == 0) {
						properties_tDBOutput_28 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
					} else {
						if (!properties_tDBOutput_28.contains("rewriteBatchedStatements=")) {
							properties_tDBOutput_28 += "&rewriteBatchedStatements=true";
						}

						if (!properties_tDBOutput_28.contains("allowLoadLocalInfile=")) {
							properties_tDBOutput_28 += "&allowLoadLocalInfile=true";
						}
					}

					String url_tDBOutput_28 = "jdbc:mariadb://" + "127.0.0.1" + ":" + "3306" + "/" + "ooredoo" + "?"
							+ properties_tDBOutput_28;

					String driverClass_tDBOutput_28 = "org.mariadb.jdbc.Driver";

					String dbUser_tDBOutput_28 = "root";

					final String decryptedPassword_tDBOutput_28 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:ufU3SNeFA+b15gOW8MapGN9ePBY5PpcfXdqPGA==");

					String dbPwd_tDBOutput_28 = decryptedPassword_tDBOutput_28;
					java.lang.Class.forName(driverClass_tDBOutput_28);

					conn_tDBOutput_28 = java.sql.DriverManager.getConnection(url_tDBOutput_28, dbUser_tDBOutput_28,
							dbPwd_tDBOutput_28);

					resourceMap.put("conn_tDBOutput_28", conn_tDBOutput_28);
					conn_tDBOutput_28.setAutoCommit(false);
					int commitEvery_tDBOutput_28 = 10000;
					int commitCounter_tDBOutput_28 = 0;

					int count_tDBOutput_28 = 0;

					String insert_tDBOutput_28 = "INSERT IGNORE INTO `" + "serveurs"
							+ "` (`id`,`Ref`,`Etat`,`Platforme`,`Hostname`,`FQDN`,`Type`,`Modele`,`OS`,`Ver.tech./Firmware`,`Cluster`,`Ip source`,`Description`,`Socle Standard OMU`,`Compléments d'informations`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

					java.sql.PreparedStatement pstmt_tDBOutput_28 = conn_tDBOutput_28
							.prepareStatement(insert_tDBOutput_28);
					resourceMap.put("pstmt_tDBOutput_28", pstmt_tDBOutput_28);

					/**
					 * [tDBOutput_28 begin ] stop
					 */

					/**
					 * [tMap_9 begin ] start
					 */

					ok_Hash.put("tMap_9", false);
					start_Hash.put("tMap_9", System.currentTimeMillis());

					currentComponent = "tMap_9";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row26");
					}

					int tos_count_tMap_9 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
					class Var__tMap_9__Struct {
					}
					Var__tMap_9__Struct Var__tMap_9 = new Var__tMap_9__Struct();
// ###############################

// ###############################
// # Outputs initialization
					insertserveursStruct insertserveurs_tmp = new insertserveursStruct();
// ###############################

					/**
					 * [tMap_9 begin ] stop
					 */

					/**
					 * [tJavaRow_9 begin ] start
					 */

					ok_Hash.put("tJavaRow_9", false);
					start_Hash.put("tJavaRow_9", System.currentTimeMillis());

					currentComponent = "tJavaRow_9";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row17");
					}

					int tos_count_tJavaRow_9 = 0;

					int nb_line_tJavaRow_9 = 0;

					/**
					 * [tJavaRow_9 begin ] stop
					 */

					/**
					 * [tFilterRow_8 begin ] start
					 */

					ok_Hash.put("tFilterRow_8", false);
					start_Hash.put("tFilterRow_8", System.currentTimeMillis());

					currentComponent = "tFilterRow_8";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row8");
					}

					int tos_count_tFilterRow_8 = 0;

					int nb_line_tFilterRow_8 = 0;
					int nb_line_ok_tFilterRow_8 = 0;
					int nb_line_reject_tFilterRow_8 = 0;

					class Operator_tFilterRow_8 {
						private String sErrorMsg = "";
						private boolean bMatchFlag = true;
						private String sUnionFlag = "&&";

						public Operator_tFilterRow_8(String unionFlag) {
							sUnionFlag = unionFlag;
							bMatchFlag = "||".equals(unionFlag) ? false : true;
						}

						public String getErrorMsg() {
							if (sErrorMsg != null && sErrorMsg.length() > 1)
								return sErrorMsg.substring(1);
							else
								return null;
						}

						public boolean getMatchFlag() {
							return bMatchFlag;
						}

						public void matches(boolean partMatched, String reason) {
							// no need to care about the next judgement
							if ("||".equals(sUnionFlag) && bMatchFlag) {
								return;
							}

							if (!partMatched) {
								sErrorMsg += "|" + reason;
							}

							if ("||".equals(sUnionFlag))
								bMatchFlag = bMatchFlag || partMatched;
							else
								bMatchFlag = bMatchFlag && partMatched;
						}
					}

					/**
					 * [tFilterRow_8 begin ] stop
					 */

					/**
					 * [tFileInputExcel_31 begin ] start
					 */

					ok_Hash.put("tFileInputExcel_31", false);
					start_Hash.put("tFileInputExcel_31", System.currentTimeMillis());

					currentComponent = "tFileInputExcel_31";

					int tos_count_tFileInputExcel_31 = 0;

					final String decryptedPassword_tFileInputExcel_31 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:kquQSqoywYTbf5TBC5Ifywv9MBc9wJbcGfU6AA==");
					String password_tFileInputExcel_31 = decryptedPassword_tFileInputExcel_31;
					if (password_tFileInputExcel_31.isEmpty()) {
						password_tFileInputExcel_31 = null;
					}
					class RegexUtil_tFileInputExcel_31 {

						public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
								org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, String oneSheetName,
								boolean useRegex) {

							java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();

							if (useRegex) {// this part process the regex issue

								java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
								for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
									String sheetName = sheet.getSheetName();
									java.util.regex.Matcher matcher = pattern.matcher(sheetName);
									if (matcher.matches()) {
										if (sheet != null) {
											list.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet);
										}
									}
								}

							} else {
								org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
										.getSheet(oneSheetName);
								if (sheet != null) {
									list.add(sheet);
								}

							}

							return list;
						}

						public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
								org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, int index, boolean useRegex) {
							java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
							org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
									.getSheetAt(index);
							if (sheet != null) {
								list.add(sheet);
							}
							return list;
						}

					}
					RegexUtil_tFileInputExcel_31 regexUtil_tFileInputExcel_31 = new RegexUtil_tFileInputExcel_31();

					Object source_tFileInputExcel_31 = ((String) globalMap.get("tFileList_3_CURRENT_FILEPATH"));
					org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_31 = null;

					if (source_tFileInputExcel_31 instanceof String) {
						workbook_tFileInputExcel_31 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
								.create(new java.io.File((String) source_tFileInputExcel_31),
										password_tFileInputExcel_31, true);
					} else if (source_tFileInputExcel_31 instanceof java.io.InputStream) {
						workbook_tFileInputExcel_31 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
								.create((java.io.InputStream) source_tFileInputExcel_31, password_tFileInputExcel_31);
					} else {
						workbook_tFileInputExcel_31 = null;
						throw new java.lang.Exception(
								"The data source should be specified as Inputstream or File Path!");
					}
					try {

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_31 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						sheetList_tFileInputExcel_31.addAll(
								regexUtil_tFileInputExcel_31.getSheets(workbook_tFileInputExcel_31, "Serveurs", false));
						if (sheetList_tFileInputExcel_31.size() <= 0) {
							throw new RuntimeException("Special sheets not exist!");
						}

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_31 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_31 : sheetList_tFileInputExcel_31) {
							if (sheet_FilterNull_tFileInputExcel_31 != null
									&& sheetList_FilterNull_tFileInputExcel_31.iterator() != null
									&& sheet_FilterNull_tFileInputExcel_31.iterator().hasNext()) {
								sheetList_FilterNull_tFileInputExcel_31.add(sheet_FilterNull_tFileInputExcel_31);
							}
						}
						sheetList_tFileInputExcel_31 = sheetList_FilterNull_tFileInputExcel_31;
						if (sheetList_tFileInputExcel_31.size() > 0) {
							int nb_line_tFileInputExcel_31 = 0;

							int begin_line_tFileInputExcel_31 = 1;

							int footer_input_tFileInputExcel_31 = 0;

							int end_line_tFileInputExcel_31 = 0;
							for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_31 : sheetList_tFileInputExcel_31) {
								end_line_tFileInputExcel_31 += (sheet_tFileInputExcel_31.getLastRowNum() + 1);
							}
							end_line_tFileInputExcel_31 -= footer_input_tFileInputExcel_31;
							int limit_tFileInputExcel_31 = -1;
							int start_column_tFileInputExcel_31 = 1 - 1;
							int end_column_tFileInputExcel_31 = -1;

							org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_31 = null;
							org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_31 = sheetList_tFileInputExcel_31
									.get(0);
							int rowCount_tFileInputExcel_31 = 0;
							int sheetIndex_tFileInputExcel_31 = 0;
							int currentRows_tFileInputExcel_31 = (sheetList_tFileInputExcel_31.get(0).getLastRowNum()
									+ 1);

							// for the number format
							java.text.DecimalFormat df_tFileInputExcel_31 = new java.text.DecimalFormat(
									"#.####################################");
							char decimalChar_tFileInputExcel_31 = df_tFileInputExcel_31.getDecimalFormatSymbols()
									.getDecimalSeparator();

							for (int i_tFileInputExcel_31 = begin_line_tFileInputExcel_31; i_tFileInputExcel_31 < end_line_tFileInputExcel_31; i_tFileInputExcel_31++) {

								int emptyColumnCount_tFileInputExcel_31 = 0;

								if (limit_tFileInputExcel_31 != -1
										&& nb_line_tFileInputExcel_31 >= limit_tFileInputExcel_31) {
									break;
								}

								while (i_tFileInputExcel_31 >= rowCount_tFileInputExcel_31
										+ currentRows_tFileInputExcel_31) {
									rowCount_tFileInputExcel_31 += currentRows_tFileInputExcel_31;
									sheet_tFileInputExcel_31 = sheetList_tFileInputExcel_31
											.get(++sheetIndex_tFileInputExcel_31);
									currentRows_tFileInputExcel_31 = (sheet_tFileInputExcel_31.getLastRowNum() + 1);
								}
								globalMap.put("tFileInputExcel_31_CURRENT_SHEET",
										sheet_tFileInputExcel_31.getSheetName());
								if (rowCount_tFileInputExcel_31 <= i_tFileInputExcel_31) {
									row_tFileInputExcel_31 = sheet_tFileInputExcel_31
											.getRow(i_tFileInputExcel_31 - rowCount_tFileInputExcel_31);
								}
								row8 = null;
								int tempRowLength_tFileInputExcel_31 = 15;

								int columnIndex_tFileInputExcel_31 = 0;

								String[] temp_row_tFileInputExcel_31 = new String[tempRowLength_tFileInputExcel_31];
								int excel_end_column_tFileInputExcel_31;
								if (row_tFileInputExcel_31 == null) {
									excel_end_column_tFileInputExcel_31 = 0;
								} else {
									excel_end_column_tFileInputExcel_31 = row_tFileInputExcel_31.getLastCellNum();
								}
								int actual_end_column_tFileInputExcel_31;
								if (end_column_tFileInputExcel_31 == -1) {
									actual_end_column_tFileInputExcel_31 = excel_end_column_tFileInputExcel_31;
								} else {
									actual_end_column_tFileInputExcel_31 = end_column_tFileInputExcel_31 > excel_end_column_tFileInputExcel_31
											? excel_end_column_tFileInputExcel_31
											: end_column_tFileInputExcel_31;
								}
								org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_31 = null;
								for (int i = 0; i < tempRowLength_tFileInputExcel_31; i++) {
									if (i + start_column_tFileInputExcel_31 < actual_end_column_tFileInputExcel_31) {
										org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_31 = row_tFileInputExcel_31
												.getCell(i + start_column_tFileInputExcel_31);
										if (cell_tFileInputExcel_31 != null) {
											switch (cell_tFileInputExcel_31.getCellType()) {
											case STRING:
												temp_row_tFileInputExcel_31[i] = cell_tFileInputExcel_31
														.getRichStringCellValue().getString();
												break;
											case NUMERIC:
												if (org.apache.poi.ss.usermodel.DateUtil
														.isCellDateFormatted(cell_tFileInputExcel_31)) {
													temp_row_tFileInputExcel_31[i] = cell_tFileInputExcel_31
															.getDateCellValue().toString();
												} else {
													temp_row_tFileInputExcel_31[i] = df_tFileInputExcel_31
															.format(cell_tFileInputExcel_31.getNumericCellValue());
												}
												break;
											case BOOLEAN:
												temp_row_tFileInputExcel_31[i] = String
														.valueOf(cell_tFileInputExcel_31.getBooleanCellValue());
												break;
											case FORMULA:
												switch (cell_tFileInputExcel_31.getCachedFormulaResultType()) {
												case STRING:
													temp_row_tFileInputExcel_31[i] = cell_tFileInputExcel_31
															.getRichStringCellValue().getString();
													break;
												case NUMERIC:
													if (org.apache.poi.ss.usermodel.DateUtil
															.isCellDateFormatted(cell_tFileInputExcel_31)) {
														temp_row_tFileInputExcel_31[i] = cell_tFileInputExcel_31
																.getDateCellValue().toString();
													} else {
														ne_tFileInputExcel_31 = new org.apache.poi.ss.formula.eval.NumberEval(
																cell_tFileInputExcel_31.getNumericCellValue());
														temp_row_tFileInputExcel_31[i] = ne_tFileInputExcel_31
																.getStringValue();
													}
													break;
												case BOOLEAN:
													temp_row_tFileInputExcel_31[i] = String
															.valueOf(cell_tFileInputExcel_31.getBooleanCellValue());
													break;
												default:
													temp_row_tFileInputExcel_31[i] = "";
												}
												break;
											default:
												temp_row_tFileInputExcel_31[i] = "";
											}
										} else {
											temp_row_tFileInputExcel_31[i] = "";
										}

									} else {
										temp_row_tFileInputExcel_31[i] = "";
									}
								}
								boolean whetherReject_tFileInputExcel_31 = false;
								row8 = new row8Struct();
								int curColNum_tFileInputExcel_31 = -1;
								String curColName_tFileInputExcel_31 = "";
								try {
									columnIndex_tFileInputExcel_31 = 0;

									if (temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31].length() > 0) {
										curColNum_tFileInputExcel_31 = columnIndex_tFileInputExcel_31
												+ start_column_tFileInputExcel_31 + 1;
										curColName_tFileInputExcel_31 = "N__Ref";

										row8.N__Ref = temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31];
									} else {
										row8.N__Ref = null;
										emptyColumnCount_tFileInputExcel_31++;
									}
									columnIndex_tFileInputExcel_31 = 1;

									if (temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31].length() > 0) {
										curColNum_tFileInputExcel_31 = columnIndex_tFileInputExcel_31
												+ start_column_tFileInputExcel_31 + 1;
										curColName_tFileInputExcel_31 = "Ref";

										row8.Ref = temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31];
									} else {
										row8.Ref = null;
										emptyColumnCount_tFileInputExcel_31++;
									}
									columnIndex_tFileInputExcel_31 = 2;

									if (temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31].length() > 0) {
										curColNum_tFileInputExcel_31 = columnIndex_tFileInputExcel_31
												+ start_column_tFileInputExcel_31 + 1;
										curColName_tFileInputExcel_31 = "Etat";

										row8.Etat = temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31];
									} else {
										row8.Etat = null;
										emptyColumnCount_tFileInputExcel_31++;
									}
									columnIndex_tFileInputExcel_31 = 3;

									if (temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31].length() > 0) {
										curColNum_tFileInputExcel_31 = columnIndex_tFileInputExcel_31
												+ start_column_tFileInputExcel_31 + 1;
										curColName_tFileInputExcel_31 = "Plateforme";

										row8.Plateforme = temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31];
									} else {
										row8.Plateforme = null;
										emptyColumnCount_tFileInputExcel_31++;
									}
									columnIndex_tFileInputExcel_31 = 4;

									if (temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31].length() > 0) {
										curColNum_tFileInputExcel_31 = columnIndex_tFileInputExcel_31
												+ start_column_tFileInputExcel_31 + 1;
										curColName_tFileInputExcel_31 = "Hostname";

										row8.Hostname = temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31];
									} else {
										row8.Hostname = null;
										emptyColumnCount_tFileInputExcel_31++;
									}
									columnIndex_tFileInputExcel_31 = 5;

									if (temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31].length() > 0) {
										curColNum_tFileInputExcel_31 = columnIndex_tFileInputExcel_31
												+ start_column_tFileInputExcel_31 + 1;
										curColName_tFileInputExcel_31 = "FQDN";

										row8.FQDN = temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31];
									} else {
										row8.FQDN = null;
										emptyColumnCount_tFileInputExcel_31++;
									}
									columnIndex_tFileInputExcel_31 = 6;

									if (temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31].length() > 0) {
										curColNum_tFileInputExcel_31 = columnIndex_tFileInputExcel_31
												+ start_column_tFileInputExcel_31 + 1;
										curColName_tFileInputExcel_31 = "Type";

										row8.Type = temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31];
									} else {
										row8.Type = null;
										emptyColumnCount_tFileInputExcel_31++;
									}
									columnIndex_tFileInputExcel_31 = 7;

									if (temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31].length() > 0) {
										curColNum_tFileInputExcel_31 = columnIndex_tFileInputExcel_31
												+ start_column_tFileInputExcel_31 + 1;
										curColName_tFileInputExcel_31 = "Modele";

										row8.Modele = temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31];
									} else {
										row8.Modele = null;
										emptyColumnCount_tFileInputExcel_31++;
									}
									columnIndex_tFileInputExcel_31 = 8;

									if (temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31].length() > 0) {
										curColNum_tFileInputExcel_31 = columnIndex_tFileInputExcel_31
												+ start_column_tFileInputExcel_31 + 1;
										curColName_tFileInputExcel_31 = "OS";

										row8.OS = temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31];
									} else {
										row8.OS = null;
										emptyColumnCount_tFileInputExcel_31++;
									}
									columnIndex_tFileInputExcel_31 = 9;

									if (temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31].length() > 0) {
										curColNum_tFileInputExcel_31 = columnIndex_tFileInputExcel_31
												+ start_column_tFileInputExcel_31 + 1;
										curColName_tFileInputExcel_31 = "Ver__tech____Firmware";

										row8.Ver__tech____Firmware = temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31];
									} else {
										row8.Ver__tech____Firmware = null;
										emptyColumnCount_tFileInputExcel_31++;
									}
									columnIndex_tFileInputExcel_31 = 10;

									if (temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31].length() > 0) {
										curColNum_tFileInputExcel_31 = columnIndex_tFileInputExcel_31
												+ start_column_tFileInputExcel_31 + 1;
										curColName_tFileInputExcel_31 = "Cluster";

										row8.Cluster = temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31];
									} else {
										row8.Cluster = null;
										emptyColumnCount_tFileInputExcel_31++;
									}
									columnIndex_tFileInputExcel_31 = 11;

									if (temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31].length() > 0) {
										curColNum_tFileInputExcel_31 = columnIndex_tFileInputExcel_31
												+ start_column_tFileInputExcel_31 + 1;
										curColName_tFileInputExcel_31 = "IP_source";

										row8.IP_source = temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31];
									} else {
										row8.IP_source = null;
										emptyColumnCount_tFileInputExcel_31++;
									}
									columnIndex_tFileInputExcel_31 = 12;

									if (temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31].length() > 0) {
										curColNum_tFileInputExcel_31 = columnIndex_tFileInputExcel_31
												+ start_column_tFileInputExcel_31 + 1;
										curColName_tFileInputExcel_31 = "Description";

										row8.Description = temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31];
									} else {
										row8.Description = null;
										emptyColumnCount_tFileInputExcel_31++;
									}
									columnIndex_tFileInputExcel_31 = 13;

									if (temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31].length() > 0) {
										curColNum_tFileInputExcel_31 = columnIndex_tFileInputExcel_31
												+ start_column_tFileInputExcel_31 + 1;
										curColName_tFileInputExcel_31 = "Socle_Standard_OMU";

										row8.Socle_Standard_OMU = temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31];
									} else {
										row8.Socle_Standard_OMU = null;
										emptyColumnCount_tFileInputExcel_31++;
									}
									columnIndex_tFileInputExcel_31 = 14;

									if (temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31].length() > 0) {
										curColNum_tFileInputExcel_31 = columnIndex_tFileInputExcel_31
												+ start_column_tFileInputExcel_31 + 1;
										curColName_tFileInputExcel_31 = "Complements_d_informations";

										row8.Complements_d_informations = temp_row_tFileInputExcel_31[columnIndex_tFileInputExcel_31];
									} else {
										row8.Complements_d_informations = null;
										emptyColumnCount_tFileInputExcel_31++;
									}

									nb_line_tFileInputExcel_31++;

								} catch (java.lang.Exception e) {
									globalMap.put("tFileInputExcel_31_ERROR_MESSAGE", e.getMessage());
									whetherReject_tFileInputExcel_31 = true;
									System.err.println(e.getMessage());
									row8 = null;
								}

								/**
								 * [tFileInputExcel_31 begin ] stop
								 */

								/**
								 * [tFileInputExcel_31 main ] start
								 */

								currentComponent = "tFileInputExcel_31";

								tos_count_tFileInputExcel_31++;

								/**
								 * [tFileInputExcel_31 main ] stop
								 */

								/**
								 * [tFileInputExcel_31 process_data_begin ] start
								 */

								currentComponent = "tFileInputExcel_31";

								/**
								 * [tFileInputExcel_31 process_data_begin ] stop
								 */
// Start of branch "row8"
								if (row8 != null) {

									/**
									 * [tFilterRow_8 main ] start
									 */

									currentComponent = "tFilterRow_8";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "row8"

										);
									}

									row17 = null;
									Operator_tFilterRow_8 ope_tFilterRow_8 = new Operator_tFilterRow_8("&&");
									ope_tFilterRow_8.matches((row8.Ref != null), "Ref!=null failed");

									if (ope_tFilterRow_8.getMatchFlag()) {
										if (row17 == null) {
											row17 = new row17Struct();
										}
										row17.N__Ref = row8.N__Ref;
										row17.Ref = row8.Ref;
										row17.Etat = row8.Etat;
										row17.Plateforme = row8.Plateforme;
										row17.Hostname = row8.Hostname;
										row17.FQDN = row8.FQDN;
										row17.Type = row8.Type;
										row17.Modele = row8.Modele;
										row17.OS = row8.OS;
										row17.Ver__tech____Firmware = row8.Ver__tech____Firmware;
										row17.Cluster = row8.Cluster;
										row17.IP_source = row8.IP_source;
										row17.Description = row8.Description;
										row17.Socle_Standard_OMU = row8.Socle_Standard_OMU;
										row17.Complements_d_informations = row8.Complements_d_informations;
										nb_line_ok_tFilterRow_8++;
									} else {
										nb_line_reject_tFilterRow_8++;
									}

									nb_line_tFilterRow_8++;

									tos_count_tFilterRow_8++;

									/**
									 * [tFilterRow_8 main ] stop
									 */

									/**
									 * [tFilterRow_8 process_data_begin ] start
									 */

									currentComponent = "tFilterRow_8";

									/**
									 * [tFilterRow_8 process_data_begin ] stop
									 */
// Start of branch "row17"
									if (row17 != null) {

										/**
										 * [tJavaRow_9 main ] start
										 */

										currentComponent = "tJavaRow_9";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row17"

											);
										}

										String fileName = (String) source_tFileInputExcel_31;

// Extraire la partie "POSANET" du nom du fichier Excel
										String[] parts = fileName.split("_");
										String fileWord = parts[6];

// Récupérer la valeur de "N__Ref" et la convertir en String
										String rowRef = row17.N__Ref;

// Générer l'ID "EDB_POSANET_X"
										String id = "EDB_" + fileWord + "_Serveurs_" + rowRef;
//Code généré selon les schémas d'entrée et de sortie
										row26.N__Ref = id;
										row26.Ref = row17.Ref;
										row26.Etat = row17.Etat;
										row26.Plateforme = row17.Plateforme;
										row26.Hostname = row17.Hostname;
										row26.FQDN = row17.FQDN;
										row26.Type = row17.Type;
										row26.Modele = row17.Modele;
										row26.OS = row17.OS;
										row26.Ver__tech____Firmware = row17.Ver__tech____Firmware;
										row26.Cluster = row17.Cluster;
										row26.IP_source = row17.IP_source;
										row26.Description = row17.Description;
										row26.Socle_Standard_OMU = row17.Socle_Standard_OMU;
										row26.Complements_d_informations = row17.Complements_d_informations;

										nb_line_tJavaRow_9++;

										tos_count_tJavaRow_9++;

										/**
										 * [tJavaRow_9 main ] stop
										 */

										/**
										 * [tJavaRow_9 process_data_begin ] start
										 */

										currentComponent = "tJavaRow_9";

										/**
										 * [tJavaRow_9 process_data_begin ] stop
										 */

										/**
										 * [tMap_9 main ] start
										 */

										currentComponent = "tMap_9";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row26"

											);
										}

										boolean hasCasePrimitiveKeyWithNull_tMap_9 = false;

										// ###############################
										// # Input tables (lookups)
										boolean rejectedInnerJoin_tMap_9 = false;
										boolean mainRowRejected_tMap_9 = false;

										// ###############################
										{ // start of Var scope

											// ###############################
											// # Vars tables

											Var__tMap_9__Struct Var = Var__tMap_9;// ###############################
											// ###############################
											// # Output tables

											insertserveurs = null;

// # Output table : 'insertserveurs'
											insertserveurs_tmp.id = row26.N__Ref;
											insertserveurs_tmp.Ref = row26.Ref;
											insertserveurs_tmp.Etat = row26.Etat;
											insertserveurs_tmp.Platforme = row26.Plateforme;
											insertserveurs_tmp.Hostname = row26.Hostname;
											insertserveurs_tmp.FQDN = row26.FQDN;
											insertserveurs_tmp.Type = row26.Type;
											insertserveurs_tmp.Modele = row26.Modele;
											insertserveurs_tmp.OS = row26.OS;
											insertserveurs_tmp.Ver_tech__Firmware = row26.Ver__tech____Firmware;
											insertserveurs_tmp.Cluster = row26.Cluster;
											insertserveurs_tmp.Ip_source = row26.IP_source;
											insertserveurs_tmp.Description = row26.Description;
											insertserveurs_tmp.Socle_Standard_OMU = row26.Socle_Standard_OMU;
											insertserveurs_tmp.Complements_d_informations = row26.Complements_d_informations;
											insertserveurs = insertserveurs_tmp;
// ###############################

										} // end of Var scope

										rejectedInnerJoin_tMap_9 = false;

										tos_count_tMap_9++;

										/**
										 * [tMap_9 main ] stop
										 */

										/**
										 * [tMap_9 process_data_begin ] start
										 */

										currentComponent = "tMap_9";

										/**
										 * [tMap_9 process_data_begin ] stop
										 */
// Start of branch "insertserveurs"
										if (insertserveurs != null) {

											/**
											 * [tDBOutput_28 main ] start
											 */

											currentComponent = "tDBOutput_28";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "insertserveurs"

												);
											}

											whetherReject_tDBOutput_28 = false;
											if (insertserveurs.id == null) {
												pstmt_tDBOutput_28.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_28.setString(1, insertserveurs.id);
											}

											if (insertserveurs.Ref == null) {
												pstmt_tDBOutput_28.setNull(2, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_28.setString(2, insertserveurs.Ref);
											}

											if (insertserveurs.Etat == null) {
												pstmt_tDBOutput_28.setNull(3, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_28.setString(3, insertserveurs.Etat);
											}

											if (insertserveurs.Platforme == null) {
												pstmt_tDBOutput_28.setNull(4, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_28.setString(4, insertserveurs.Platforme);
											}

											if (insertserveurs.Hostname == null) {
												pstmt_tDBOutput_28.setNull(5, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_28.setString(5, insertserveurs.Hostname);
											}

											if (insertserveurs.FQDN == null) {
												pstmt_tDBOutput_28.setNull(6, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_28.setString(6, insertserveurs.FQDN);
											}

											if (insertserveurs.Type == null) {
												pstmt_tDBOutput_28.setNull(7, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_28.setString(7, insertserveurs.Type);
											}

											if (insertserveurs.Modele == null) {
												pstmt_tDBOutput_28.setNull(8, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_28.setString(8, insertserveurs.Modele);
											}

											if (insertserveurs.OS == null) {
												pstmt_tDBOutput_28.setNull(9, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_28.setString(9, insertserveurs.OS);
											}

											if (insertserveurs.Ver_tech__Firmware == null) {
												pstmt_tDBOutput_28.setNull(10, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_28.setString(10, insertserveurs.Ver_tech__Firmware);
											}

											if (insertserveurs.Cluster == null) {
												pstmt_tDBOutput_28.setNull(11, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_28.setString(11, insertserveurs.Cluster);
											}

											if (insertserveurs.Ip_source == null) {
												pstmt_tDBOutput_28.setNull(12, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_28.setString(12, insertserveurs.Ip_source);
											}

											if (insertserveurs.Description == null) {
												pstmt_tDBOutput_28.setNull(13, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_28.setString(13, insertserveurs.Description);
											}

											if (insertserveurs.Socle_Standard_OMU == null) {
												pstmt_tDBOutput_28.setNull(14, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_28.setString(14, insertserveurs.Socle_Standard_OMU);
											}

											if (insertserveurs.Complements_d_informations == null) {
												pstmt_tDBOutput_28.setNull(15, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_28.setString(15,
														insertserveurs.Complements_d_informations);
											}

											try {
												nb_line_tDBOutput_28++;
												int processedCount_tDBOutput_28 = pstmt_tDBOutput_28.executeUpdate();
												insertedCount_tDBOutput_28 += processedCount_tDBOutput_28;
												rowsToCommitCount_tDBOutput_28 += processedCount_tDBOutput_28;
											} catch (java.lang.Exception e) {
												globalMap.put("tDBOutput_28_ERROR_MESSAGE", e.getMessage());
												whetherReject_tDBOutput_28 = true;
												System.err.print(e.getMessage());
											}
											commitCounter_tDBOutput_28++;

											if (commitEvery_tDBOutput_28 <= commitCounter_tDBOutput_28) {

												if (rowsToCommitCount_tDBOutput_28 != 0) {
												}
												conn_tDBOutput_28.commit();
												if (rowsToCommitCount_tDBOutput_28 != 0) {
													rowsToCommitCount_tDBOutput_28 = 0;
												}
												commitCounter_tDBOutput_28 = 0;

											}

											tos_count_tDBOutput_28++;

											/**
											 * [tDBOutput_28 main ] stop
											 */

											/**
											 * [tDBOutput_28 process_data_begin ] start
											 */

											currentComponent = "tDBOutput_28";

											/**
											 * [tDBOutput_28 process_data_begin ] stop
											 */

											/**
											 * [tDBOutput_28 process_data_end ] start
											 */

											currentComponent = "tDBOutput_28";

											/**
											 * [tDBOutput_28 process_data_end ] stop
											 */

										} // End of branch "insertserveurs"

										/**
										 * [tMap_9 process_data_end ] start
										 */

										currentComponent = "tMap_9";

										/**
										 * [tMap_9 process_data_end ] stop
										 */

										/**
										 * [tJavaRow_9 process_data_end ] start
										 */

										currentComponent = "tJavaRow_9";

										/**
										 * [tJavaRow_9 process_data_end ] stop
										 */

									} // End of branch "row17"

									/**
									 * [tFilterRow_8 process_data_end ] start
									 */

									currentComponent = "tFilterRow_8";

									/**
									 * [tFilterRow_8 process_data_end ] stop
									 */

								} // End of branch "row8"

								/**
								 * [tFileInputExcel_31 process_data_end ] start
								 */

								currentComponent = "tFileInputExcel_31";

								/**
								 * [tFileInputExcel_31 process_data_end ] stop
								 */

								/**
								 * [tFileInputExcel_31 end ] start
								 */

								currentComponent = "tFileInputExcel_31";

							}

							globalMap.put("tFileInputExcel_31_NB_LINE", nb_line_tFileInputExcel_31);

						}

					} finally {

						if (!(source_tFileInputExcel_31 instanceof java.io.InputStream)) {
							workbook_tFileInputExcel_31.getPackage().revert();
						}

					}

					ok_Hash.put("tFileInputExcel_31", true);
					end_Hash.put("tFileInputExcel_31", System.currentTimeMillis());

					/**
					 * [tFileInputExcel_31 end ] stop
					 */

					/**
					 * [tFilterRow_8 end ] start
					 */

					currentComponent = "tFilterRow_8";

					globalMap.put("tFilterRow_8_NB_LINE", nb_line_tFilterRow_8);
					globalMap.put("tFilterRow_8_NB_LINE_OK", nb_line_ok_tFilterRow_8);
					globalMap.put("tFilterRow_8_NB_LINE_REJECT", nb_line_reject_tFilterRow_8);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row8");
					}

					ok_Hash.put("tFilterRow_8", true);
					end_Hash.put("tFilterRow_8", System.currentTimeMillis());

					/**
					 * [tFilterRow_8 end ] stop
					 */

					/**
					 * [tJavaRow_9 end ] start
					 */

					currentComponent = "tJavaRow_9";

					globalMap.put("tJavaRow_9_NB_LINE", nb_line_tJavaRow_9);
					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row17");
					}

					ok_Hash.put("tJavaRow_9", true);
					end_Hash.put("tJavaRow_9", System.currentTimeMillis());

					/**
					 * [tJavaRow_9 end ] stop
					 */

					/**
					 * [tMap_9 end ] start
					 */

					currentComponent = "tMap_9";

// ###############################
// # Lookup hashes releasing
// ###############################      

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row26");
					}

					ok_Hash.put("tMap_9", true);
					end_Hash.put("tMap_9", System.currentTimeMillis());

					/**
					 * [tMap_9 end ] stop
					 */

					/**
					 * [tDBOutput_28 end ] start
					 */

					currentComponent = "tDBOutput_28";

					if (pstmt_tDBOutput_28 != null) {

						pstmt_tDBOutput_28.close();
						resourceMap.remove("pstmt_tDBOutput_28");

					}
					resourceMap.put("statementClosed_tDBOutput_28", true);
					if (commitCounter_tDBOutput_28 > 0 && rowsToCommitCount_tDBOutput_28 != 0) {

					}
					conn_tDBOutput_28.commit();
					if (commitCounter_tDBOutput_28 > 0 && rowsToCommitCount_tDBOutput_28 != 0) {

						rowsToCommitCount_tDBOutput_28 = 0;
					}
					commitCounter_tDBOutput_28 = 0;

					conn_tDBOutput_28.close();

					resourceMap.put("finish_tDBOutput_28", true);

					nb_line_deleted_tDBOutput_28 = nb_line_deleted_tDBOutput_28 + deletedCount_tDBOutput_28;
					nb_line_update_tDBOutput_28 = nb_line_update_tDBOutput_28 + updatedCount_tDBOutput_28;
					nb_line_inserted_tDBOutput_28 = nb_line_inserted_tDBOutput_28 + insertedCount_tDBOutput_28;
					nb_line_rejected_tDBOutput_28 = nb_line_rejected_tDBOutput_28 + rejectedCount_tDBOutput_28;

					globalMap.put("tDBOutput_28_NB_LINE", nb_line_tDBOutput_28);
					globalMap.put("tDBOutput_28_NB_LINE_UPDATED", nb_line_update_tDBOutput_28);
					globalMap.put("tDBOutput_28_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_28);
					globalMap.put("tDBOutput_28_NB_LINE_DELETED", nb_line_deleted_tDBOutput_28);
					globalMap.put("tDBOutput_28_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_28);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "insertserveurs");
					}

					ok_Hash.put("tDBOutput_28", true);
					end_Hash.put("tDBOutput_28", System.currentTimeMillis());

					/**
					 * [tDBOutput_28 end ] stop
					 */

					if (execStat) {
						runStat.updateStatOnConnection("iterate9", 2, "exec" + NB_ITERATE_tFileInputExcel_31);
					}

					NB_ITERATE_tFileInputExcel_3++;

					if (execStat) {
						runStat.updateStatOnConnection("row18", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row9", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("insertlog_files_patterns", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row27", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate10", 1, "exec" + NB_ITERATE_tFileInputExcel_3);
						// Thread.sleep(1000);
					}

					/**
					 * [tDBOutput_6 begin ] start
					 */

					ok_Hash.put("tDBOutput_6", false);
					start_Hash.put("tDBOutput_6", System.currentTimeMillis());

					currentComponent = "tDBOutput_6";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "insertlog_files_patterns");
					}

					int tos_count_tDBOutput_6 = 0;

					int nb_line_tDBOutput_6 = 0;
					int nb_line_update_tDBOutput_6 = 0;
					int nb_line_inserted_tDBOutput_6 = 0;
					int nb_line_deleted_tDBOutput_6 = 0;
					int nb_line_rejected_tDBOutput_6 = 0;

					int deletedCount_tDBOutput_6 = 0;
					int updatedCount_tDBOutput_6 = 0;
					int insertedCount_tDBOutput_6 = 0;
					int rowsToCommitCount_tDBOutput_6 = 0;
					int rejectedCount_tDBOutput_6 = 0;

					String tableName_tDBOutput_6 = "log_files_patterns";
					boolean whetherReject_tDBOutput_6 = false;

					java.util.Calendar calendar_tDBOutput_6 = java.util.Calendar.getInstance();
					calendar_tDBOutput_6.set(1, 0, 1, 0, 0, 0);
					long year1_tDBOutput_6 = calendar_tDBOutput_6.getTime().getTime();
					calendar_tDBOutput_6.set(10000, 0, 1, 0, 0, 0);
					long year10000_tDBOutput_6 = calendar_tDBOutput_6.getTime().getTime();
					long date_tDBOutput_6;

					java.sql.Connection conn_tDBOutput_6 = null;

					String properties_tDBOutput_6 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
					if (properties_tDBOutput_6 == null || properties_tDBOutput_6.trim().length() == 0) {
						properties_tDBOutput_6 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
					} else {
						if (!properties_tDBOutput_6.contains("rewriteBatchedStatements=")) {
							properties_tDBOutput_6 += "&rewriteBatchedStatements=true";
						}

						if (!properties_tDBOutput_6.contains("allowLoadLocalInfile=")) {
							properties_tDBOutput_6 += "&allowLoadLocalInfile=true";
						}
					}

					String url_tDBOutput_6 = "jdbc:mariadb://" + "127.0.0.1" + ":" + "3306" + "/" + "ooredoo" + "?"
							+ properties_tDBOutput_6;

					String driverClass_tDBOutput_6 = "org.mariadb.jdbc.Driver";

					String dbUser_tDBOutput_6 = "root";

					final String decryptedPassword_tDBOutput_6 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:D4/H6FXLSRz2Wu6zTPXTtOPLkwzbWwbCN/H6+w==");

					String dbPwd_tDBOutput_6 = decryptedPassword_tDBOutput_6;
					java.lang.Class.forName(driverClass_tDBOutput_6);

					conn_tDBOutput_6 = java.sql.DriverManager.getConnection(url_tDBOutput_6, dbUser_tDBOutput_6,
							dbPwd_tDBOutput_6);

					resourceMap.put("conn_tDBOutput_6", conn_tDBOutput_6);
					conn_tDBOutput_6.setAutoCommit(false);
					int commitEvery_tDBOutput_6 = 10000;
					int commitCounter_tDBOutput_6 = 0;

					int count_tDBOutput_6 = 0;

					String insert_tDBOutput_6 = "INSERT IGNORE INTO `" + "log_files_patterns"
							+ "` (`id`,`N_Ref`,`Ref`,`Etat`,`Signification`,`Identification`,`Criticitée`,`Message_alarme`,`Instructions`,`Perform_action`,`Acquittement`,`Complements_d'informations`,`Ref_Service`,`Objet`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

					java.sql.PreparedStatement pstmt_tDBOutput_6 = conn_tDBOutput_6
							.prepareStatement(insert_tDBOutput_6);
					resourceMap.put("pstmt_tDBOutput_6", pstmt_tDBOutput_6);

					/**
					 * [tDBOutput_6 begin ] stop
					 */

					/**
					 * [tMap_10 begin ] start
					 */

					ok_Hash.put("tMap_10", false);
					start_Hash.put("tMap_10", System.currentTimeMillis());

					currentComponent = "tMap_10";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row27");
					}

					int tos_count_tMap_10 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
					class Var__tMap_10__Struct {
					}
					Var__tMap_10__Struct Var__tMap_10 = new Var__tMap_10__Struct();
// ###############################

// ###############################
// # Outputs initialization
					insertlog_files_patternsStruct insertlog_files_patterns_tmp = new insertlog_files_patternsStruct();
// ###############################

					/**
					 * [tMap_10 begin ] stop
					 */

					/**
					 * [tJavaRow_10 begin ] start
					 */

					ok_Hash.put("tJavaRow_10", false);
					start_Hash.put("tJavaRow_10", System.currentTimeMillis());

					currentComponent = "tJavaRow_10";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row18");
					}

					int tos_count_tJavaRow_10 = 0;

					int nb_line_tJavaRow_10 = 0;

					/**
					 * [tJavaRow_10 begin ] stop
					 */

					/**
					 * [tFilterRow_9 begin ] start
					 */

					ok_Hash.put("tFilterRow_9", false);
					start_Hash.put("tFilterRow_9", System.currentTimeMillis());

					currentComponent = "tFilterRow_9";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row9");
					}

					int tos_count_tFilterRow_9 = 0;

					int nb_line_tFilterRow_9 = 0;
					int nb_line_ok_tFilterRow_9 = 0;
					int nb_line_reject_tFilterRow_9 = 0;

					class Operator_tFilterRow_9 {
						private String sErrorMsg = "";
						private boolean bMatchFlag = true;
						private String sUnionFlag = "&&";

						public Operator_tFilterRow_9(String unionFlag) {
							sUnionFlag = unionFlag;
							bMatchFlag = "||".equals(unionFlag) ? false : true;
						}

						public String getErrorMsg() {
							if (sErrorMsg != null && sErrorMsg.length() > 1)
								return sErrorMsg.substring(1);
							else
								return null;
						}

						public boolean getMatchFlag() {
							return bMatchFlag;
						}

						public void matches(boolean partMatched, String reason) {
							// no need to care about the next judgement
							if ("||".equals(sUnionFlag) && bMatchFlag) {
								return;
							}

							if (!partMatched) {
								sErrorMsg += "|" + reason;
							}

							if ("||".equals(sUnionFlag))
								bMatchFlag = bMatchFlag || partMatched;
							else
								bMatchFlag = bMatchFlag && partMatched;
						}
					}

					/**
					 * [tFilterRow_9 begin ] stop
					 */

					/**
					 * [tFileInputExcel_3 begin ] start
					 */

					ok_Hash.put("tFileInputExcel_3", false);
					start_Hash.put("tFileInputExcel_3", System.currentTimeMillis());

					currentComponent = "tFileInputExcel_3";

					int tos_count_tFileInputExcel_3 = 0;

					final String decryptedPassword_tFileInputExcel_3 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:Wv9RgcFQDpskn8JAgEswtmCJD6aOAFrGB7qm1w==");
					String password_tFileInputExcel_3 = decryptedPassword_tFileInputExcel_3;
					if (password_tFileInputExcel_3.isEmpty()) {
						password_tFileInputExcel_3 = null;
					}
					class RegexUtil_tFileInputExcel_3 {

						public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
								org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, String oneSheetName,
								boolean useRegex) {

							java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();

							if (useRegex) {// this part process the regex issue

								java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
								for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
									String sheetName = sheet.getSheetName();
									java.util.regex.Matcher matcher = pattern.matcher(sheetName);
									if (matcher.matches()) {
										if (sheet != null) {
											list.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet);
										}
									}
								}

							} else {
								org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
										.getSheet(oneSheetName);
								if (sheet != null) {
									list.add(sheet);
								}

							}

							return list;
						}

						public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
								org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, int index, boolean useRegex) {
							java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
							org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
									.getSheetAt(index);
							if (sheet != null) {
								list.add(sheet);
							}
							return list;
						}

					}
					RegexUtil_tFileInputExcel_3 regexUtil_tFileInputExcel_3 = new RegexUtil_tFileInputExcel_3();

					Object source_tFileInputExcel_3 = ((String) globalMap.get("tFileList_3_CURRENT_FILEPATH"));
					org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_3 = null;

					if (source_tFileInputExcel_3 instanceof String) {
						workbook_tFileInputExcel_3 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
								.create(new java.io.File((String) source_tFileInputExcel_3), password_tFileInputExcel_3,
										true);
					} else if (source_tFileInputExcel_3 instanceof java.io.InputStream) {
						workbook_tFileInputExcel_3 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
								.create((java.io.InputStream) source_tFileInputExcel_3, password_tFileInputExcel_3);
					} else {
						workbook_tFileInputExcel_3 = null;
						throw new java.lang.Exception(
								"The data source should be specified as Inputstream or File Path!");
					}
					try {

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_3 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						sheetList_tFileInputExcel_3.addAll(regexUtil_tFileInputExcel_3
								.getSheets(workbook_tFileInputExcel_3, "Log Files Patterns", false));
						if (sheetList_tFileInputExcel_3.size() <= 0) {
							throw new RuntimeException("Special sheets not exist!");
						}

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_3 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_3 : sheetList_tFileInputExcel_3) {
							if (sheet_FilterNull_tFileInputExcel_3 != null
									&& sheetList_FilterNull_tFileInputExcel_3.iterator() != null
									&& sheet_FilterNull_tFileInputExcel_3.iterator().hasNext()) {
								sheetList_FilterNull_tFileInputExcel_3.add(sheet_FilterNull_tFileInputExcel_3);
							}
						}
						sheetList_tFileInputExcel_3 = sheetList_FilterNull_tFileInputExcel_3;
						if (sheetList_tFileInputExcel_3.size() > 0) {
							int nb_line_tFileInputExcel_3 = 0;

							int begin_line_tFileInputExcel_3 = 1;

							int footer_input_tFileInputExcel_3 = 0;

							int end_line_tFileInputExcel_3 = 0;
							for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_3 : sheetList_tFileInputExcel_3) {
								end_line_tFileInputExcel_3 += (sheet_tFileInputExcel_3.getLastRowNum() + 1);
							}
							end_line_tFileInputExcel_3 -= footer_input_tFileInputExcel_3;
							int limit_tFileInputExcel_3 = -1;
							int start_column_tFileInputExcel_3 = 1 - 1;
							int end_column_tFileInputExcel_3 = -1;

							org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_3 = null;
							org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_3 = sheetList_tFileInputExcel_3
									.get(0);
							int rowCount_tFileInputExcel_3 = 0;
							int sheetIndex_tFileInputExcel_3 = 0;
							int currentRows_tFileInputExcel_3 = (sheetList_tFileInputExcel_3.get(0).getLastRowNum()
									+ 1);

							// for the number format
							java.text.DecimalFormat df_tFileInputExcel_3 = new java.text.DecimalFormat(
									"#.####################################");
							char decimalChar_tFileInputExcel_3 = df_tFileInputExcel_3.getDecimalFormatSymbols()
									.getDecimalSeparator();

							for (int i_tFileInputExcel_3 = begin_line_tFileInputExcel_3; i_tFileInputExcel_3 < end_line_tFileInputExcel_3; i_tFileInputExcel_3++) {

								int emptyColumnCount_tFileInputExcel_3 = 0;

								if (limit_tFileInputExcel_3 != -1
										&& nb_line_tFileInputExcel_3 >= limit_tFileInputExcel_3) {
									break;
								}

								while (i_tFileInputExcel_3 >= rowCount_tFileInputExcel_3
										+ currentRows_tFileInputExcel_3) {
									rowCount_tFileInputExcel_3 += currentRows_tFileInputExcel_3;
									sheet_tFileInputExcel_3 = sheetList_tFileInputExcel_3
											.get(++sheetIndex_tFileInputExcel_3);
									currentRows_tFileInputExcel_3 = (sheet_tFileInputExcel_3.getLastRowNum() + 1);
								}
								globalMap.put("tFileInputExcel_3_CURRENT_SHEET",
										sheet_tFileInputExcel_3.getSheetName());
								if (rowCount_tFileInputExcel_3 <= i_tFileInputExcel_3) {
									row_tFileInputExcel_3 = sheet_tFileInputExcel_3
											.getRow(i_tFileInputExcel_3 - rowCount_tFileInputExcel_3);
								}
								row9 = null;
								int tempRowLength_tFileInputExcel_3 = 14;

								int columnIndex_tFileInputExcel_3 = 0;

								String[] temp_row_tFileInputExcel_3 = new String[tempRowLength_tFileInputExcel_3];
								int excel_end_column_tFileInputExcel_3;
								if (row_tFileInputExcel_3 == null) {
									excel_end_column_tFileInputExcel_3 = 0;
								} else {
									excel_end_column_tFileInputExcel_3 = row_tFileInputExcel_3.getLastCellNum();
								}
								int actual_end_column_tFileInputExcel_3;
								if (end_column_tFileInputExcel_3 == -1) {
									actual_end_column_tFileInputExcel_3 = excel_end_column_tFileInputExcel_3;
								} else {
									actual_end_column_tFileInputExcel_3 = end_column_tFileInputExcel_3 > excel_end_column_tFileInputExcel_3
											? excel_end_column_tFileInputExcel_3
											: end_column_tFileInputExcel_3;
								}
								org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_3 = null;
								for (int i = 0; i < tempRowLength_tFileInputExcel_3; i++) {
									if (i + start_column_tFileInputExcel_3 < actual_end_column_tFileInputExcel_3) {
										org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_3 = row_tFileInputExcel_3
												.getCell(i + start_column_tFileInputExcel_3);
										if (cell_tFileInputExcel_3 != null) {
											switch (cell_tFileInputExcel_3.getCellType()) {
											case STRING:
												temp_row_tFileInputExcel_3[i] = cell_tFileInputExcel_3
														.getRichStringCellValue().getString();
												break;
											case NUMERIC:
												if (org.apache.poi.ss.usermodel.DateUtil
														.isCellDateFormatted(cell_tFileInputExcel_3)) {
													temp_row_tFileInputExcel_3[i] = cell_tFileInputExcel_3
															.getDateCellValue().toString();
												} else {
													temp_row_tFileInputExcel_3[i] = df_tFileInputExcel_3
															.format(cell_tFileInputExcel_3.getNumericCellValue());
												}
												break;
											case BOOLEAN:
												temp_row_tFileInputExcel_3[i] = String
														.valueOf(cell_tFileInputExcel_3.getBooleanCellValue());
												break;
											case FORMULA:
												switch (cell_tFileInputExcel_3.getCachedFormulaResultType()) {
												case STRING:
													temp_row_tFileInputExcel_3[i] = cell_tFileInputExcel_3
															.getRichStringCellValue().getString();
													break;
												case NUMERIC:
													if (org.apache.poi.ss.usermodel.DateUtil
															.isCellDateFormatted(cell_tFileInputExcel_3)) {
														temp_row_tFileInputExcel_3[i] = cell_tFileInputExcel_3
																.getDateCellValue().toString();
													} else {
														ne_tFileInputExcel_3 = new org.apache.poi.ss.formula.eval.NumberEval(
																cell_tFileInputExcel_3.getNumericCellValue());
														temp_row_tFileInputExcel_3[i] = ne_tFileInputExcel_3
																.getStringValue();
													}
													break;
												case BOOLEAN:
													temp_row_tFileInputExcel_3[i] = String
															.valueOf(cell_tFileInputExcel_3.getBooleanCellValue());
													break;
												default:
													temp_row_tFileInputExcel_3[i] = "";
												}
												break;
											default:
												temp_row_tFileInputExcel_3[i] = "";
											}
										} else {
											temp_row_tFileInputExcel_3[i] = "";
										}

									} else {
										temp_row_tFileInputExcel_3[i] = "";
									}
								}
								boolean whetherReject_tFileInputExcel_3 = false;
								row9 = new row9Struct();
								int curColNum_tFileInputExcel_3 = -1;
								String curColName_tFileInputExcel_3 = "";
								try {
									columnIndex_tFileInputExcel_3 = 0;

									if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
										curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
												+ start_column_tFileInputExcel_3 + 1;
										curColName_tFileInputExcel_3 = "Log_Ref";

										row9.Log_Ref = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
									} else {
										row9.Log_Ref = null;
										emptyColumnCount_tFileInputExcel_3++;
									}
									columnIndex_tFileInputExcel_3 = 1;

									if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
										curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
												+ start_column_tFileInputExcel_3 + 1;
										curColName_tFileInputExcel_3 = "N__Ref";

										row9.N__Ref = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
												temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3], null,
												'.' == decimalChar_tFileInputExcel_3 ? null
														: decimalChar_tFileInputExcel_3));
									} else {
										row9.N__Ref = null;
										emptyColumnCount_tFileInputExcel_3++;
									}
									columnIndex_tFileInputExcel_3 = 2;

									if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
										curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
												+ start_column_tFileInputExcel_3 + 1;
										curColName_tFileInputExcel_3 = "Ref";

										row9.Ref = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
												temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3], null,
												'.' == decimalChar_tFileInputExcel_3 ? null
														: decimalChar_tFileInputExcel_3));
									} else {
										row9.Ref = null;
										emptyColumnCount_tFileInputExcel_3++;
									}
									columnIndex_tFileInputExcel_3 = 3;

									if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
										curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
												+ start_column_tFileInputExcel_3 + 1;
										curColName_tFileInputExcel_3 = "Etat";

										row9.Etat = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
									} else {
										row9.Etat = null;
										emptyColumnCount_tFileInputExcel_3++;
									}
									columnIndex_tFileInputExcel_3 = 4;

									if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
										curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
												+ start_column_tFileInputExcel_3 + 1;
										curColName_tFileInputExcel_3 = "Signification";

										row9.Signification = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
									} else {
										row9.Signification = null;
										emptyColumnCount_tFileInputExcel_3++;
									}
									columnIndex_tFileInputExcel_3 = 5;

									if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
										curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
												+ start_column_tFileInputExcel_3 + 1;
										curColName_tFileInputExcel_3 = "Identification";

										row9.Identification = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
									} else {
										row9.Identification = null;
										emptyColumnCount_tFileInputExcel_3++;
									}
									columnIndex_tFileInputExcel_3 = 6;

									if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
										curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
												+ start_column_tFileInputExcel_3 + 1;
										curColName_tFileInputExcel_3 = "Criticite";

										row9.Criticite = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
									} else {
										row9.Criticite = null;
										emptyColumnCount_tFileInputExcel_3++;
									}
									columnIndex_tFileInputExcel_3 = 7;

									if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
										curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
												+ start_column_tFileInputExcel_3 + 1;
										curColName_tFileInputExcel_3 = "Message_d_alarme";

										row9.Message_d_alarme = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
									} else {
										row9.Message_d_alarme = null;
										emptyColumnCount_tFileInputExcel_3++;
									}
									columnIndex_tFileInputExcel_3 = 8;

									if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
										curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
												+ start_column_tFileInputExcel_3 + 1;
										curColName_tFileInputExcel_3 = "Instructions";

										row9.Instructions = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
									} else {
										row9.Instructions = null;
										emptyColumnCount_tFileInputExcel_3++;
									}
									columnIndex_tFileInputExcel_3 = 9;

									if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
										curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
												+ start_column_tFileInputExcel_3 + 1;
										curColName_tFileInputExcel_3 = "Perform_action";

										row9.Perform_action = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
									} else {
										row9.Perform_action = null;
										emptyColumnCount_tFileInputExcel_3++;
									}
									columnIndex_tFileInputExcel_3 = 10;

									if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
										curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
												+ start_column_tFileInputExcel_3 + 1;
										curColName_tFileInputExcel_3 = "Acquittement";

										row9.Acquittement = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
									} else {
										row9.Acquittement = null;
										emptyColumnCount_tFileInputExcel_3++;
									}
									columnIndex_tFileInputExcel_3 = 11;

									if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
										curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
												+ start_column_tFileInputExcel_3 + 1;
										curColName_tFileInputExcel_3 = "Complements_d_informations";

										row9.Complements_d_informations = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
									} else {
										row9.Complements_d_informations = null;
										emptyColumnCount_tFileInputExcel_3++;
									}
									columnIndex_tFileInputExcel_3 = 12;

									if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
										curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
												+ start_column_tFileInputExcel_3 + 1;
										curColName_tFileInputExcel_3 = "Ref__Service";

										row9.Ref__Service = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
									} else {
										row9.Ref__Service = null;
										emptyColumnCount_tFileInputExcel_3++;
									}
									columnIndex_tFileInputExcel_3 = 13;

									if (temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3].length() > 0) {
										curColNum_tFileInputExcel_3 = columnIndex_tFileInputExcel_3
												+ start_column_tFileInputExcel_3 + 1;
										curColName_tFileInputExcel_3 = "Objet";

										row9.Objet = temp_row_tFileInputExcel_3[columnIndex_tFileInputExcel_3];
									} else {
										row9.Objet = null;
										emptyColumnCount_tFileInputExcel_3++;
									}

									nb_line_tFileInputExcel_3++;

								} catch (java.lang.Exception e) {
									globalMap.put("tFileInputExcel_3_ERROR_MESSAGE", e.getMessage());
									whetherReject_tFileInputExcel_3 = true;
									System.err.println(e.getMessage());
									row9 = null;
								}

								/**
								 * [tFileInputExcel_3 begin ] stop
								 */

								/**
								 * [tFileInputExcel_3 main ] start
								 */

								currentComponent = "tFileInputExcel_3";

								tos_count_tFileInputExcel_3++;

								/**
								 * [tFileInputExcel_3 main ] stop
								 */

								/**
								 * [tFileInputExcel_3 process_data_begin ] start
								 */

								currentComponent = "tFileInputExcel_3";

								/**
								 * [tFileInputExcel_3 process_data_begin ] stop
								 */
// Start of branch "row9"
								if (row9 != null) {

									/**
									 * [tFilterRow_9 main ] start
									 */

									currentComponent = "tFilterRow_9";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "row9"

										);
									}

									row18 = null;
									Operator_tFilterRow_9 ope_tFilterRow_9 = new Operator_tFilterRow_9("&&");
									ope_tFilterRow_9.matches((row9.Log_Ref != null), "Log_Ref!=null failed");

									if (ope_tFilterRow_9.getMatchFlag()) {
										if (row18 == null) {
											row18 = new row18Struct();
										}
										row18.Log_Ref = row9.Log_Ref;
										row18.N__Ref = row9.N__Ref;
										row18.Ref = row9.Ref;
										row18.Etat = row9.Etat;
										row18.Signification = row9.Signification;
										row18.Identification = row9.Identification;
										row18.Criticite = row9.Criticite;
										row18.Message_d_alarme = row9.Message_d_alarme;
										row18.Instructions = row9.Instructions;
										row18.Perform_action = row9.Perform_action;
										row18.Acquittement = row9.Acquittement;
										row18.Complements_d_informations = row9.Complements_d_informations;
										row18.Ref__Service = row9.Ref__Service;
										row18.Objet = row9.Objet;
										nb_line_ok_tFilterRow_9++;
									} else {
										nb_line_reject_tFilterRow_9++;
									}

									nb_line_tFilterRow_9++;

									tos_count_tFilterRow_9++;

									/**
									 * [tFilterRow_9 main ] stop
									 */

									/**
									 * [tFilterRow_9 process_data_begin ] start
									 */

									currentComponent = "tFilterRow_9";

									/**
									 * [tFilterRow_9 process_data_begin ] stop
									 */
// Start of branch "row18"
									if (row18 != null) {

										/**
										 * [tJavaRow_10 main ] start
										 */

										currentComponent = "tJavaRow_10";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row18"

											);
										}

										String fileName = (String) source_tFileInputExcel_3;

// Extraire la partie "POSANET" du nom du fichier Excel
										String[] parts = fileName.split("_");
										String fileWord = parts[6];

// Récupérer la valeur de "N__Ref" et la convertir en String
										String rowRef = row18.Log_Ref;

// Générer l'ID "EDB_POSANET_X"
										String id = "EDB_" + fileWord + "_LogFilesPatterns_" + rowRef;
//Code généré selon les schémas d'entrée et de sortie
										row27.Log_Ref = id;
										row27.N__Ref = row18.N__Ref;
										row27.Ref = row18.Ref;
										row27.Etat = row18.Etat;
										row27.Signification = row18.Signification;
										row27.Identification = row18.Identification;
										row27.Criticite = row18.Criticite;
										row27.Message_d_alarme = row18.Message_d_alarme;
										row27.Instructions = row18.Instructions;
										row27.Perform_action = row18.Perform_action;
										row27.Acquittement = row18.Acquittement;
										row27.Complements_d_informations = row18.Complements_d_informations;
										row27.Ref__Service = row18.Ref__Service;
										row27.Objet = row18.Objet;

										nb_line_tJavaRow_10++;

										tos_count_tJavaRow_10++;

										/**
										 * [tJavaRow_10 main ] stop
										 */

										/**
										 * [tJavaRow_10 process_data_begin ] start
										 */

										currentComponent = "tJavaRow_10";

										/**
										 * [tJavaRow_10 process_data_begin ] stop
										 */

										/**
										 * [tMap_10 main ] start
										 */

										currentComponent = "tMap_10";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row27"

											);
										}

										boolean hasCasePrimitiveKeyWithNull_tMap_10 = false;

										// ###############################
										// # Input tables (lookups)
										boolean rejectedInnerJoin_tMap_10 = false;
										boolean mainRowRejected_tMap_10 = false;

										// ###############################
										{ // start of Var scope

											// ###############################
											// # Vars tables

											Var__tMap_10__Struct Var = Var__tMap_10;// ###############################
											// ###############################
											// # Output tables

											insertlog_files_patterns = null;

// # Output table : 'insertlog_files_patterns'
											insertlog_files_patterns_tmp.id = row27.Log_Ref;
											insertlog_files_patterns_tmp.N_Ref = row27.N__Ref;
											insertlog_files_patterns_tmp.Ref = row27.Ref;
											insertlog_files_patterns_tmp.Etat = row27.Etat;
											insertlog_files_patterns_tmp.Signification = row27.Signification;
											insertlog_files_patterns_tmp.Identification = row27.Identification;
											insertlog_files_patterns_tmp.Criticitee = row27.Criticite;
											insertlog_files_patterns_tmp.Message_alarme = row27.Message_d_alarme;
											insertlog_files_patterns_tmp.Instructions = row27.Instructions;
											insertlog_files_patterns_tmp.Perform_action = row27.Perform_action;
											insertlog_files_patterns_tmp.Acquittement = row27.Acquittement;
											insertlog_files_patterns_tmp.Complements_d_informations = row27.Complements_d_informations;
											insertlog_files_patterns_tmp.Ref_Service = row27.Ref__Service;
											insertlog_files_patterns_tmp.Objet = row27.Objet;
											insertlog_files_patterns = insertlog_files_patterns_tmp;
// ###############################

										} // end of Var scope

										rejectedInnerJoin_tMap_10 = false;

										tos_count_tMap_10++;

										/**
										 * [tMap_10 main ] stop
										 */

										/**
										 * [tMap_10 process_data_begin ] start
										 */

										currentComponent = "tMap_10";

										/**
										 * [tMap_10 process_data_begin ] stop
										 */
// Start of branch "insertlog_files_patterns"
										if (insertlog_files_patterns != null) {

											/**
											 * [tDBOutput_6 main ] start
											 */

											currentComponent = "tDBOutput_6";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "insertlog_files_patterns"

												);
											}

											whetherReject_tDBOutput_6 = false;
											if (insertlog_files_patterns.id == null) {
												pstmt_tDBOutput_6.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(1, insertlog_files_patterns.id);
											}

											if (insertlog_files_patterns.N_Ref == null) {
												pstmt_tDBOutput_6.setNull(2, java.sql.Types.INTEGER);
											} else {
												pstmt_tDBOutput_6.setInt(2, insertlog_files_patterns.N_Ref);
											}

											if (insertlog_files_patterns.Ref == null) {
												pstmt_tDBOutput_6.setNull(3, java.sql.Types.INTEGER);
											} else {
												pstmt_tDBOutput_6.setInt(3, insertlog_files_patterns.Ref);
											}

											if (insertlog_files_patterns.Etat == null) {
												pstmt_tDBOutput_6.setNull(4, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(4, insertlog_files_patterns.Etat);
											}

											if (insertlog_files_patterns.Signification == null) {
												pstmt_tDBOutput_6.setNull(5, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(5, insertlog_files_patterns.Signification);
											}

											if (insertlog_files_patterns.Identification == null) {
												pstmt_tDBOutput_6.setNull(6, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(6, insertlog_files_patterns.Identification);
											}

											if (insertlog_files_patterns.Criticitee == null) {
												pstmt_tDBOutput_6.setNull(7, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(7, insertlog_files_patterns.Criticitee);
											}

											if (insertlog_files_patterns.Message_alarme == null) {
												pstmt_tDBOutput_6.setNull(8, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(8, insertlog_files_patterns.Message_alarme);
											}

											if (insertlog_files_patterns.Instructions == null) {
												pstmt_tDBOutput_6.setNull(9, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(9, insertlog_files_patterns.Instructions);
											}

											if (insertlog_files_patterns.Perform_action == null) {
												pstmt_tDBOutput_6.setNull(10, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(10,
														insertlog_files_patterns.Perform_action);
											}

											if (insertlog_files_patterns.Acquittement == null) {
												pstmt_tDBOutput_6.setNull(11, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(11, insertlog_files_patterns.Acquittement);
											}

											if (insertlog_files_patterns.Complements_d_informations == null) {
												pstmt_tDBOutput_6.setNull(12, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(12,
														insertlog_files_patterns.Complements_d_informations);
											}

											if (insertlog_files_patterns.Ref_Service == null) {
												pstmt_tDBOutput_6.setNull(13, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(13, insertlog_files_patterns.Ref_Service);
											}

											if (insertlog_files_patterns.Objet == null) {
												pstmt_tDBOutput_6.setNull(14, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(14, insertlog_files_patterns.Objet);
											}

											try {
												nb_line_tDBOutput_6++;
												int processedCount_tDBOutput_6 = pstmt_tDBOutput_6.executeUpdate();
												insertedCount_tDBOutput_6 += processedCount_tDBOutput_6;
												rowsToCommitCount_tDBOutput_6 += processedCount_tDBOutput_6;
											} catch (java.lang.Exception e) {
												globalMap.put("tDBOutput_6_ERROR_MESSAGE", e.getMessage());
												whetherReject_tDBOutput_6 = true;
												System.err.print(e.getMessage());
											}
											commitCounter_tDBOutput_6++;

											if (commitEvery_tDBOutput_6 <= commitCounter_tDBOutput_6) {

												if (rowsToCommitCount_tDBOutput_6 != 0) {
												}
												conn_tDBOutput_6.commit();
												if (rowsToCommitCount_tDBOutput_6 != 0) {
													rowsToCommitCount_tDBOutput_6 = 0;
												}
												commitCounter_tDBOutput_6 = 0;

											}

											tos_count_tDBOutput_6++;

											/**
											 * [tDBOutput_6 main ] stop
											 */

											/**
											 * [tDBOutput_6 process_data_begin ] start
											 */

											currentComponent = "tDBOutput_6";

											/**
											 * [tDBOutput_6 process_data_begin ] stop
											 */

											/**
											 * [tDBOutput_6 process_data_end ] start
											 */

											currentComponent = "tDBOutput_6";

											/**
											 * [tDBOutput_6 process_data_end ] stop
											 */

										} // End of branch "insertlog_files_patterns"

										/**
										 * [tMap_10 process_data_end ] start
										 */

										currentComponent = "tMap_10";

										/**
										 * [tMap_10 process_data_end ] stop
										 */

										/**
										 * [tJavaRow_10 process_data_end ] start
										 */

										currentComponent = "tJavaRow_10";

										/**
										 * [tJavaRow_10 process_data_end ] stop
										 */

									} // End of branch "row18"

									/**
									 * [tFilterRow_9 process_data_end ] start
									 */

									currentComponent = "tFilterRow_9";

									/**
									 * [tFilterRow_9 process_data_end ] stop
									 */

								} // End of branch "row9"

								/**
								 * [tFileInputExcel_3 process_data_end ] start
								 */

								currentComponent = "tFileInputExcel_3";

								/**
								 * [tFileInputExcel_3 process_data_end ] stop
								 */

								/**
								 * [tFileInputExcel_3 end ] start
								 */

								currentComponent = "tFileInputExcel_3";

							}

							globalMap.put("tFileInputExcel_3_NB_LINE", nb_line_tFileInputExcel_3);

						}

					} finally {

						if (!(source_tFileInputExcel_3 instanceof java.io.InputStream)) {
							workbook_tFileInputExcel_3.getPackage().revert();
						}

					}

					ok_Hash.put("tFileInputExcel_3", true);
					end_Hash.put("tFileInputExcel_3", System.currentTimeMillis());

					/**
					 * [tFileInputExcel_3 end ] stop
					 */

					/**
					 * [tFilterRow_9 end ] start
					 */

					currentComponent = "tFilterRow_9";

					globalMap.put("tFilterRow_9_NB_LINE", nb_line_tFilterRow_9);
					globalMap.put("tFilterRow_9_NB_LINE_OK", nb_line_ok_tFilterRow_9);
					globalMap.put("tFilterRow_9_NB_LINE_REJECT", nb_line_reject_tFilterRow_9);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row9");
					}

					ok_Hash.put("tFilterRow_9", true);
					end_Hash.put("tFilterRow_9", System.currentTimeMillis());

					/**
					 * [tFilterRow_9 end ] stop
					 */

					/**
					 * [tJavaRow_10 end ] start
					 */

					currentComponent = "tJavaRow_10";

					globalMap.put("tJavaRow_10_NB_LINE", nb_line_tJavaRow_10);
					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row18");
					}

					ok_Hash.put("tJavaRow_10", true);
					end_Hash.put("tJavaRow_10", System.currentTimeMillis());

					/**
					 * [tJavaRow_10 end ] stop
					 */

					/**
					 * [tMap_10 end ] start
					 */

					currentComponent = "tMap_10";

// ###############################
// # Lookup hashes releasing
// ###############################      

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row27");
					}

					ok_Hash.put("tMap_10", true);
					end_Hash.put("tMap_10", System.currentTimeMillis());

					/**
					 * [tMap_10 end ] stop
					 */

					/**
					 * [tDBOutput_6 end ] start
					 */

					currentComponent = "tDBOutput_6";

					if (pstmt_tDBOutput_6 != null) {

						pstmt_tDBOutput_6.close();
						resourceMap.remove("pstmt_tDBOutput_6");

					}
					resourceMap.put("statementClosed_tDBOutput_6", true);
					if (commitCounter_tDBOutput_6 > 0 && rowsToCommitCount_tDBOutput_6 != 0) {

					}
					conn_tDBOutput_6.commit();
					if (commitCounter_tDBOutput_6 > 0 && rowsToCommitCount_tDBOutput_6 != 0) {

						rowsToCommitCount_tDBOutput_6 = 0;
					}
					commitCounter_tDBOutput_6 = 0;

					conn_tDBOutput_6.close();

					resourceMap.put("finish_tDBOutput_6", true);

					nb_line_deleted_tDBOutput_6 = nb_line_deleted_tDBOutput_6 + deletedCount_tDBOutput_6;
					nb_line_update_tDBOutput_6 = nb_line_update_tDBOutput_6 + updatedCount_tDBOutput_6;
					nb_line_inserted_tDBOutput_6 = nb_line_inserted_tDBOutput_6 + insertedCount_tDBOutput_6;
					nb_line_rejected_tDBOutput_6 = nb_line_rejected_tDBOutput_6 + rejectedCount_tDBOutput_6;

					globalMap.put("tDBOutput_6_NB_LINE", nb_line_tDBOutput_6);
					globalMap.put("tDBOutput_6_NB_LINE_UPDATED", nb_line_update_tDBOutput_6);
					globalMap.put("tDBOutput_6_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_6);
					globalMap.put("tDBOutput_6_NB_LINE_DELETED", nb_line_deleted_tDBOutput_6);
					globalMap.put("tDBOutput_6_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_6);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "insertlog_files_patterns");
					}

					ok_Hash.put("tDBOutput_6", true);
					end_Hash.put("tDBOutput_6", System.currentTimeMillis());

					/**
					 * [tDBOutput_6 end ] stop
					 */

					if (execStat) {
						runStat.updateStatOnConnection("iterate10", 2, "exec" + NB_ITERATE_tFileInputExcel_3);
					}

					NB_ITERATE_tIterateToFlow_3_ITFO++;

					if (execStat) {
						runStat.updateStatOnConnection("iterate12", 1, "exec" + NB_ITERATE_tIterateToFlow_3_ITFO);
						// Thread.sleep(1000);
					}

					/**
					 * [tIterateToFlow_3_ITFO begin ] start
					 */

					ok_Hash.put("tIterateToFlow_3_ITFO", false);
					start_Hash.put("tIterateToFlow_3_ITFO", System.currentTimeMillis());

					currentVirtualComponent = "tIterateToFlow_3";

					currentComponent = "tIterateToFlow_3_ITFO";

					int tos_count_tIterateToFlow_3_ITFO = 0;

					OnSubjobOkStructtIterateToFlow_3 struct_tIterateToFlow_3_ITFO = new OnSubjobOkStructtIterateToFlow_3();
					struct_tIterateToFlow_3_ITFO.filepath3 = ((String) globalMap.get("tFileList_3_CURRENT_FILEPATH"));

					if (globalMap.get("tIterateToFlow_3") != null) {
						java.util.List<OnSubjobOkStructtIterateToFlow_3> list_tIterateToFlow_3_ITFO = (java.util.List<OnSubjobOkStructtIterateToFlow_3>) globalMap
								.get("tIterateToFlow_3");
						list_tIterateToFlow_3_ITFO.add(struct_tIterateToFlow_3_ITFO);
					} else {
						java.util.List<OnSubjobOkStructtIterateToFlow_3> list_tIterateToFlow_3_ITFO = new java.util.ArrayList<OnSubjobOkStructtIterateToFlow_3>();
						list_tIterateToFlow_3_ITFO.add(struct_tIterateToFlow_3_ITFO);
						globalMap.put("tIterateToFlow_3", list_tIterateToFlow_3_ITFO);
					}

					/**
					 * [tIterateToFlow_3_ITFO begin ] stop
					 */

					/**
					 * [tIterateToFlow_3_ITFO main ] start
					 */

					currentVirtualComponent = "tIterateToFlow_3";

					currentComponent = "tIterateToFlow_3_ITFO";

					tos_count_tIterateToFlow_3_ITFO++;

					/**
					 * [tIterateToFlow_3_ITFO main ] stop
					 */

					/**
					 * [tIterateToFlow_3_ITFO process_data_begin ] start
					 */

					currentVirtualComponent = "tIterateToFlow_3";

					currentComponent = "tIterateToFlow_3_ITFO";

					/**
					 * [tIterateToFlow_3_ITFO process_data_begin ] stop
					 */

					/**
					 * [tIterateToFlow_3_ITFO process_data_end ] start
					 */

					currentVirtualComponent = "tIterateToFlow_3";

					currentComponent = "tIterateToFlow_3_ITFO";

					/**
					 * [tIterateToFlow_3_ITFO process_data_end ] stop
					 */

					/**
					 * [tIterateToFlow_3_ITFO end ] start
					 */

					currentVirtualComponent = "tIterateToFlow_3";

					currentComponent = "tIterateToFlow_3_ITFO";

					ok_Hash.put("tIterateToFlow_3_ITFO", true);
					end_Hash.put("tIterateToFlow_3_ITFO", System.currentTimeMillis());

					/**
					 * [tIterateToFlow_3_ITFO end ] stop
					 */
					if (execStat) {
						runStat.updateStatOnConnection("iterate12", 2, "exec" + NB_ITERATE_tIterateToFlow_3_ITFO);
					}

					/**
					 * [tFileList_3 process_data_end ] start
					 */

					currentComponent = "tFileList_3";

					/**
					 * [tFileList_3 process_data_end ] stop
					 */

					/**
					 * [tFileList_3 end ] start
					 */

					currentComponent = "tFileList_3";

				}
				globalMap.put("tFileList_3_NB_FILE", NB_FILEtFileList_3);

				ok_Hash.put("tFileList_3", true);
				end_Hash.put("tFileList_3", System.currentTimeMillis());

				/**
				 * [tFileList_3 end ] stop
				 */
			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tFileList_3:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk", 0, "ok");
			}

			tIterateToFlow_3_AIProcess(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileList_3 finally ] start
				 */

				currentComponent = "tFileList_3";

				/**
				 * [tFileList_3 finally ] stop
				 */

				/**
				 * [tFileInputExcel_39 finally ] start
				 */

				currentComponent = "tFileInputExcel_39";

				/**
				 * [tFileInputExcel_39 finally ] stop
				 */

				/**
				 * [tFilterRow_7 finally ] start
				 */

				currentComponent = "tFilterRow_7";

				/**
				 * [tFilterRow_7 finally ] stop
				 */

				/**
				 * [tJavaRow_8 finally ] start
				 */

				currentComponent = "tJavaRow_8";

				/**
				 * [tJavaRow_8 finally ] stop
				 */

				/**
				 * [tMap_8 finally ] start
				 */

				currentComponent = "tMap_8";

				/**
				 * [tMap_8 finally ] stop
				 */

				/**
				 * [tDBOutput_36 finally ] start
				 */

				currentComponent = "tDBOutput_36";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_36") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_36 = null;
						if ((pstmtToClose_tDBOutput_36 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_36")) != null) {
							pstmtToClose_tDBOutput_36.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_36") == null) {
						java.sql.Connection ctn_tDBOutput_36 = null;
						if ((ctn_tDBOutput_36 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_36")) != null) {
							try {
								ctn_tDBOutput_36.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_36) {
								String errorMessage_tDBOutput_36 = "failed to close the connection in tDBOutput_36 :"
										+ sqlEx_tDBOutput_36.getMessage();
								System.err.println(errorMessage_tDBOutput_36);
							}
						}
					}
				}

				/**
				 * [tDBOutput_36 finally ] stop
				 */

				/**
				 * [tFileInputExcel_31 finally ] start
				 */

				currentComponent = "tFileInputExcel_31";

				/**
				 * [tFileInputExcel_31 finally ] stop
				 */

				/**
				 * [tFilterRow_8 finally ] start
				 */

				currentComponent = "tFilterRow_8";

				/**
				 * [tFilterRow_8 finally ] stop
				 */

				/**
				 * [tJavaRow_9 finally ] start
				 */

				currentComponent = "tJavaRow_9";

				/**
				 * [tJavaRow_9 finally ] stop
				 */

				/**
				 * [tMap_9 finally ] start
				 */

				currentComponent = "tMap_9";

				/**
				 * [tMap_9 finally ] stop
				 */

				/**
				 * [tDBOutput_28 finally ] start
				 */

				currentComponent = "tDBOutput_28";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_28") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_28 = null;
						if ((pstmtToClose_tDBOutput_28 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_28")) != null) {
							pstmtToClose_tDBOutput_28.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_28") == null) {
						java.sql.Connection ctn_tDBOutput_28 = null;
						if ((ctn_tDBOutput_28 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_28")) != null) {
							try {
								ctn_tDBOutput_28.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_28) {
								String errorMessage_tDBOutput_28 = "failed to close the connection in tDBOutput_28 :"
										+ sqlEx_tDBOutput_28.getMessage();
								System.err.println(errorMessage_tDBOutput_28);
							}
						}
					}
				}

				/**
				 * [tDBOutput_28 finally ] stop
				 */

				/**
				 * [tFileInputExcel_3 finally ] start
				 */

				currentComponent = "tFileInputExcel_3";

				/**
				 * [tFileInputExcel_3 finally ] stop
				 */

				/**
				 * [tFilterRow_9 finally ] start
				 */

				currentComponent = "tFilterRow_9";

				/**
				 * [tFilterRow_9 finally ] stop
				 */

				/**
				 * [tJavaRow_10 finally ] start
				 */

				currentComponent = "tJavaRow_10";

				/**
				 * [tJavaRow_10 finally ] stop
				 */

				/**
				 * [tMap_10 finally ] start
				 */

				currentComponent = "tMap_10";

				/**
				 * [tMap_10 finally ] stop
				 */

				/**
				 * [tDBOutput_6 finally ] start
				 */

				currentComponent = "tDBOutput_6";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_6") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_6 = null;
						if ((pstmtToClose_tDBOutput_6 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_6")) != null) {
							pstmtToClose_tDBOutput_6.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_6") == null) {
						java.sql.Connection ctn_tDBOutput_6 = null;
						if ((ctn_tDBOutput_6 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_6")) != null) {
							try {
								ctn_tDBOutput_6.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_6) {
								String errorMessage_tDBOutput_6 = "failed to close the connection in tDBOutput_6 :"
										+ sqlEx_tDBOutput_6.getMessage();
								System.err.println(errorMessage_tDBOutput_6);
							}
						}
					}
				}

				/**
				 * [tDBOutput_6 finally ] stop
				 */

				/**
				 * [tIterateToFlow_3_ITFO finally ] start
				 */

				currentVirtualComponent = "tIterateToFlow_3";

				currentComponent = "tIterateToFlow_3_ITFO";

				/**
				 * [tIterateToFlow_3_ITFO finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileList_3_SUBPROCESS_STATE", 1);
	}

	public static class row28Struct implements routines.system.IPersistableRow<row28Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String filepath;

		public String getFilepath() {
			return this.filepath;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.filepath = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.filepath = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.filepath, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.filepath, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("filepath=" + filepath);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row28Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tIterateToFlow_1_AIProcess(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tIterateToFlow_1_AI_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;
		String currentVirtualComponent = null;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row28Struct row28 = new row28Struct();

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row28");
				}

				int tos_count_tLogRow_1 = 0;

				///////////////////////

				class Util_tLogRow_1 {

					String[] des_top = { ".", ".", "-", "+" };

					String[] des_head = { "|=", "=|", "-", "+" };

					String[] des_bottom = { "'", "'", "-", "+" };

					String name = "";

					java.util.List<String[]> list = new java.util.ArrayList<String[]>();

					int[] colLengths = new int[1];

					public void addRow(String[] row) {

						for (int i = 0; i < 1; i++) {
							if (row[i] != null) {
								colLengths[i] = Math.max(colLengths[i], row[i].length());
							}
						}
						list.add(row);
					}

					public void setTableName(String name) {

						this.name = name;
					}

					public StringBuilder format() {

						StringBuilder sb = new StringBuilder();

						sb.append(print(des_top));

						int totals = 0;
						for (int i = 0; i < colLengths.length; i++) {
							totals = totals + colLengths[i];
						}

						// name
						sb.append("|");
						int k = 0;
						for (k = 0; k < (totals + 0 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 0 - name.length() - k; i++) {
							sb.append(' ');
						}
						sb.append("|\n");

						// head and rows
						sb.append(print(des_head));
						for (int i = 0; i < list.size(); i++) {

							String[] row = list.get(i);

							java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());

							StringBuilder sbformat = new StringBuilder();
							sbformat.append("|%1$-");
							sbformat.append(colLengths[0]);
							sbformat.append("s");

							sbformat.append("|\n");

							formatter.format(sbformat.toString(), (Object[]) row);

							sb.append(formatter.toString());
							if (i == 0)
								sb.append(print(des_head)); // print the head
						}

						// end
						sb.append(print(des_bottom));
						return sb;
					}

					private StringBuilder print(String[] fillChars) {
						StringBuilder sb = new StringBuilder();
						// first column
						sb.append(fillChars[0]);

						// last column
						for (int i = 0; i < colLengths[0] - fillChars[0].length() - fillChars[1].length() + 2; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[1]);
						sb.append("\n");
						return sb;
					}

					public boolean isTableEmpty() {
						if (list.size() > 1)
							return false;
						return true;
					}
				}
				Util_tLogRow_1 util_tLogRow_1 = new Util_tLogRow_1();
				util_tLogRow_1.setTableName("tLogRow_1");
				util_tLogRow_1.addRow(new String[] { "filepath", });
				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tIterateToFlow_1_AI begin ] start
				 */

				ok_Hash.put("tIterateToFlow_1_AI", false);
				start_Hash.put("tIterateToFlow_1_AI", System.currentTimeMillis());

				currentVirtualComponent = "tIterateToFlow_1";

				currentComponent = "tIterateToFlow_1_AI";

				int tos_count_tIterateToFlow_1_AI = 0;

				int nb_line_tIterateToFlow_1_AI = 0;
				java.util.List<OnSubjobOkStructtIterateToFlow_1> list_tIterateToFlow_1_AI = (java.util.List<OnSubjobOkStructtIterateToFlow_1>) globalMap
						.get("tIterateToFlow_1");
				if (list_tIterateToFlow_1_AI == null) {
					list_tIterateToFlow_1_AI = new java.util.ArrayList<OnSubjobOkStructtIterateToFlow_1>();
				}
				for (OnSubjobOkStructtIterateToFlow_1 row_tIterateToFlow_1_AI : list_tIterateToFlow_1_AI) {

					row28.filepath = row_tIterateToFlow_1_AI.filepath;

					/**
					 * [tIterateToFlow_1_AI begin ] stop
					 */

					/**
					 * [tIterateToFlow_1_AI main ] start
					 */

					currentVirtualComponent = "tIterateToFlow_1";

					currentComponent = "tIterateToFlow_1_AI";

					tos_count_tIterateToFlow_1_AI++;

					/**
					 * [tIterateToFlow_1_AI main ] stop
					 */

					/**
					 * [tIterateToFlow_1_AI process_data_begin ] start
					 */

					currentVirtualComponent = "tIterateToFlow_1";

					currentComponent = "tIterateToFlow_1_AI";

					/**
					 * [tIterateToFlow_1_AI process_data_begin ] stop
					 */

					/**
					 * [tLogRow_1 main ] start
					 */

					currentComponent = "tLogRow_1";

					if (execStat) {
						runStat.updateStatOnConnection(iterateId, 1, 1

								, "row28"

						);
					}

///////////////////////		

					String[] row_tLogRow_1 = new String[1];

					if (row28.filepath != null) { //
						row_tLogRow_1[0] = String.valueOf(row28.filepath);

					} //

					util_tLogRow_1.addRow(row_tLogRow_1);
					nb_line_tLogRow_1++;
//////

//////                    

///////////////////////    			

					tos_count_tLogRow_1++;

					/**
					 * [tLogRow_1 main ] stop
					 */

					/**
					 * [tLogRow_1 process_data_begin ] start
					 */

					currentComponent = "tLogRow_1";

					/**
					 * [tLogRow_1 process_data_begin ] stop
					 */

					/**
					 * [tLogRow_1 process_data_end ] start
					 */

					currentComponent = "tLogRow_1";

					/**
					 * [tLogRow_1 process_data_end ] stop
					 */

					/**
					 * [tIterateToFlow_1_AI process_data_end ] start
					 */

					currentVirtualComponent = "tIterateToFlow_1";

					currentComponent = "tIterateToFlow_1_AI";

					/**
					 * [tIterateToFlow_1_AI process_data_end ] stop
					 */

					/**
					 * [tIterateToFlow_1_AI end ] start
					 */

					currentVirtualComponent = "tIterateToFlow_1";

					currentComponent = "tIterateToFlow_1_AI";

					nb_line_tIterateToFlow_1_AI++;
				}
				globalMap.put("tIterateToFlow_1_AI_NB_LINE", nb_line_tIterateToFlow_1_AI);

				ok_Hash.put("tIterateToFlow_1_AI", true);
				end_Hash.put("tIterateToFlow_1_AI", System.currentTimeMillis());

				/**
				 * [tIterateToFlow_1_AI end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				currentComponent = "tLogRow_1";

//////

				java.io.PrintStream consoleOut_tLogRow_1 = null;
				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_1 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
				}

				consoleOut_tLogRow_1.println(util_tLogRow_1.format().toString());
				consoleOut_tLogRow_1.flush();
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row28");
				}

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tIterateToFlow_1_AI"
			globalMap.remove("tIterateToFlow_1");

			try {

				/**
				 * [tIterateToFlow_1_AI finally ] start
				 */

				currentVirtualComponent = "tIterateToFlow_1";

				currentComponent = "tIterateToFlow_1_AI";

				/**
				 * [tIterateToFlow_1_AI finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tIterateToFlow_1_AI_SUBPROCESS_STATE", 1);
	}

	public static class row30Struct implements routines.system.IPersistableRow<row30Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String filepath2;

		public String getFilepath2() {
			return this.filepath2;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.filepath2 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.filepath2 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.filepath2, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.filepath2, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("filepath2=" + filepath2);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row30Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tIterateToFlow_2_AIProcess(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tIterateToFlow_2_AI_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;
		String currentVirtualComponent = null;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row30Struct row30 = new row30Struct();

				/**
				 * [tLogRow_3 begin ] start
				 */

				ok_Hash.put("tLogRow_3", false);
				start_Hash.put("tLogRow_3", System.currentTimeMillis());

				currentComponent = "tLogRow_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row30");
				}

				int tos_count_tLogRow_3 = 0;

				///////////////////////

				class Util_tLogRow_3 {

					String[] des_top = { ".", ".", "-", "+" };

					String[] des_head = { "|=", "=|", "-", "+" };

					String[] des_bottom = { "'", "'", "-", "+" };

					String name = "";

					java.util.List<String[]> list = new java.util.ArrayList<String[]>();

					int[] colLengths = new int[1];

					public void addRow(String[] row) {

						for (int i = 0; i < 1; i++) {
							if (row[i] != null) {
								colLengths[i] = Math.max(colLengths[i], row[i].length());
							}
						}
						list.add(row);
					}

					public void setTableName(String name) {

						this.name = name;
					}

					public StringBuilder format() {

						StringBuilder sb = new StringBuilder();

						sb.append(print(des_top));

						int totals = 0;
						for (int i = 0; i < colLengths.length; i++) {
							totals = totals + colLengths[i];
						}

						// name
						sb.append("|");
						int k = 0;
						for (k = 0; k < (totals + 0 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 0 - name.length() - k; i++) {
							sb.append(' ');
						}
						sb.append("|\n");

						// head and rows
						sb.append(print(des_head));
						for (int i = 0; i < list.size(); i++) {

							String[] row = list.get(i);

							java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());

							StringBuilder sbformat = new StringBuilder();
							sbformat.append("|%1$-");
							sbformat.append(colLengths[0]);
							sbformat.append("s");

							sbformat.append("|\n");

							formatter.format(sbformat.toString(), (Object[]) row);

							sb.append(formatter.toString());
							if (i == 0)
								sb.append(print(des_head)); // print the head
						}

						// end
						sb.append(print(des_bottom));
						return sb;
					}

					private StringBuilder print(String[] fillChars) {
						StringBuilder sb = new StringBuilder();
						// first column
						sb.append(fillChars[0]);

						// last column
						for (int i = 0; i < colLengths[0] - fillChars[0].length() - fillChars[1].length() + 2; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[1]);
						sb.append("\n");
						return sb;
					}

					public boolean isTableEmpty() {
						if (list.size() > 1)
							return false;
						return true;
					}
				}
				Util_tLogRow_3 util_tLogRow_3 = new Util_tLogRow_3();
				util_tLogRow_3.setTableName("tLogRow_3");
				util_tLogRow_3.addRow(new String[] { "filepath2", });
				StringBuilder strBuffer_tLogRow_3 = null;
				int nb_line_tLogRow_3 = 0;
///////////////////////    			

				/**
				 * [tLogRow_3 begin ] stop
				 */

				/**
				 * [tIterateToFlow_2_AI begin ] start
				 */

				ok_Hash.put("tIterateToFlow_2_AI", false);
				start_Hash.put("tIterateToFlow_2_AI", System.currentTimeMillis());

				currentVirtualComponent = "tIterateToFlow_2";

				currentComponent = "tIterateToFlow_2_AI";

				int tos_count_tIterateToFlow_2_AI = 0;

				int nb_line_tIterateToFlow_2_AI = 0;
				java.util.List<OnSubjobOkStructtIterateToFlow_2> list_tIterateToFlow_2_AI = (java.util.List<OnSubjobOkStructtIterateToFlow_2>) globalMap
						.get("tIterateToFlow_2");
				if (list_tIterateToFlow_2_AI == null) {
					list_tIterateToFlow_2_AI = new java.util.ArrayList<OnSubjobOkStructtIterateToFlow_2>();
				}
				for (OnSubjobOkStructtIterateToFlow_2 row_tIterateToFlow_2_AI : list_tIterateToFlow_2_AI) {

					row30.filepath2 = row_tIterateToFlow_2_AI.filepath2;

					/**
					 * [tIterateToFlow_2_AI begin ] stop
					 */

					/**
					 * [tIterateToFlow_2_AI main ] start
					 */

					currentVirtualComponent = "tIterateToFlow_2";

					currentComponent = "tIterateToFlow_2_AI";

					tos_count_tIterateToFlow_2_AI++;

					/**
					 * [tIterateToFlow_2_AI main ] stop
					 */

					/**
					 * [tIterateToFlow_2_AI process_data_begin ] start
					 */

					currentVirtualComponent = "tIterateToFlow_2";

					currentComponent = "tIterateToFlow_2_AI";

					/**
					 * [tIterateToFlow_2_AI process_data_begin ] stop
					 */

					/**
					 * [tLogRow_3 main ] start
					 */

					currentComponent = "tLogRow_3";

					if (execStat) {
						runStat.updateStatOnConnection(iterateId, 1, 1

								, "row30"

						);
					}

///////////////////////		

					String[] row_tLogRow_3 = new String[1];

					if (row30.filepath2 != null) { //
						row_tLogRow_3[0] = String.valueOf(row30.filepath2);

					} //

					util_tLogRow_3.addRow(row_tLogRow_3);
					nb_line_tLogRow_3++;
//////

//////                    

///////////////////////    			

					tos_count_tLogRow_3++;

					/**
					 * [tLogRow_3 main ] stop
					 */

					/**
					 * [tLogRow_3 process_data_begin ] start
					 */

					currentComponent = "tLogRow_3";

					/**
					 * [tLogRow_3 process_data_begin ] stop
					 */

					/**
					 * [tLogRow_3 process_data_end ] start
					 */

					currentComponent = "tLogRow_3";

					/**
					 * [tLogRow_3 process_data_end ] stop
					 */

					/**
					 * [tIterateToFlow_2_AI process_data_end ] start
					 */

					currentVirtualComponent = "tIterateToFlow_2";

					currentComponent = "tIterateToFlow_2_AI";

					/**
					 * [tIterateToFlow_2_AI process_data_end ] stop
					 */

					/**
					 * [tIterateToFlow_2_AI end ] start
					 */

					currentVirtualComponent = "tIterateToFlow_2";

					currentComponent = "tIterateToFlow_2_AI";

					nb_line_tIterateToFlow_2_AI++;
				}
				globalMap.put("tIterateToFlow_2_AI_NB_LINE", nb_line_tIterateToFlow_2_AI);

				ok_Hash.put("tIterateToFlow_2_AI", true);
				end_Hash.put("tIterateToFlow_2_AI", System.currentTimeMillis());

				/**
				 * [tIterateToFlow_2_AI end ] stop
				 */

				/**
				 * [tLogRow_3 end ] start
				 */

				currentComponent = "tLogRow_3";

//////

				java.io.PrintStream consoleOut_tLogRow_3 = null;
				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_3 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_3 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_3);
				}

				consoleOut_tLogRow_3.println(util_tLogRow_3.format().toString());
				consoleOut_tLogRow_3.flush();
//////
				globalMap.put("tLogRow_3_NB_LINE", nb_line_tLogRow_3);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row30");
				}

				ok_Hash.put("tLogRow_3", true);
				end_Hash.put("tLogRow_3", System.currentTimeMillis());

				/**
				 * [tLogRow_3 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tIterateToFlow_2_AI"
			globalMap.remove("tIterateToFlow_2");

			try {

				/**
				 * [tIterateToFlow_2_AI finally ] start
				 */

				currentVirtualComponent = "tIterateToFlow_2";

				currentComponent = "tIterateToFlow_2_AI";

				/**
				 * [tIterateToFlow_2_AI finally ] stop
				 */

				/**
				 * [tLogRow_3 finally ] start
				 */

				currentComponent = "tLogRow_3";

				/**
				 * [tLogRow_3 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tIterateToFlow_2_AI_SUBPROCESS_STATE", 1);
	}

	public static class row29Struct implements routines.system.IPersistableRow<row29Struct> {
		final static byte[] commonByteArrayLock_EBDS_MONITORING_WEB_monitoring = new byte[0];
		static byte[] commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[0];

		public String filepath3;

		public String getFilepath3() {
			return this.filepath3;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBDS_MONITORING_WEB_monitoring.length) {
					if (length < 1024 && commonByteArray_EBDS_MONITORING_WEB_monitoring.length == 0) {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[1024];
					} else {
						commonByteArray_EBDS_MONITORING_WEB_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBDS_MONITORING_WEB_monitoring, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.filepath3 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBDS_MONITORING_WEB_monitoring) {

				try {

					int length = 0;

					this.filepath3 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.filepath3, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.filepath3, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("filepath3=" + filepath3);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row29Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tIterateToFlow_3_AIProcess(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tIterateToFlow_3_AI_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;
		String currentVirtualComponent = null;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row29Struct row29 = new row29Struct();

				/**
				 * [tLogRow_2 begin ] start
				 */

				ok_Hash.put("tLogRow_2", false);
				start_Hash.put("tLogRow_2", System.currentTimeMillis());

				currentComponent = "tLogRow_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row29");
				}

				int tos_count_tLogRow_2 = 0;

				///////////////////////

				final String OUTPUT_FIELD_SEPARATOR_tLogRow_2 = "|";
				java.io.PrintStream consoleOut_tLogRow_2 = null;

				StringBuilder strBuffer_tLogRow_2 = null;
				int nb_line_tLogRow_2 = 0;
///////////////////////    			

				/**
				 * [tLogRow_2 begin ] stop
				 */

				/**
				 * [tIterateToFlow_3_AI begin ] start
				 */

				ok_Hash.put("tIterateToFlow_3_AI", false);
				start_Hash.put("tIterateToFlow_3_AI", System.currentTimeMillis());

				currentVirtualComponent = "tIterateToFlow_3";

				currentComponent = "tIterateToFlow_3_AI";

				int tos_count_tIterateToFlow_3_AI = 0;

				int nb_line_tIterateToFlow_3_AI = 0;
				java.util.List<OnSubjobOkStructtIterateToFlow_3> list_tIterateToFlow_3_AI = (java.util.List<OnSubjobOkStructtIterateToFlow_3>) globalMap
						.get("tIterateToFlow_3");
				if (list_tIterateToFlow_3_AI == null) {
					list_tIterateToFlow_3_AI = new java.util.ArrayList<OnSubjobOkStructtIterateToFlow_3>();
				}
				for (OnSubjobOkStructtIterateToFlow_3 row_tIterateToFlow_3_AI : list_tIterateToFlow_3_AI) {

					row29.filepath3 = row_tIterateToFlow_3_AI.filepath3;

					/**
					 * [tIterateToFlow_3_AI begin ] stop
					 */

					/**
					 * [tIterateToFlow_3_AI main ] start
					 */

					currentVirtualComponent = "tIterateToFlow_3";

					currentComponent = "tIterateToFlow_3_AI";

					tos_count_tIterateToFlow_3_AI++;

					/**
					 * [tIterateToFlow_3_AI main ] stop
					 */

					/**
					 * [tIterateToFlow_3_AI process_data_begin ] start
					 */

					currentVirtualComponent = "tIterateToFlow_3";

					currentComponent = "tIterateToFlow_3_AI";

					/**
					 * [tIterateToFlow_3_AI process_data_begin ] stop
					 */

					/**
					 * [tLogRow_2 main ] start
					 */

					currentComponent = "tLogRow_2";

					if (execStat) {
						runStat.updateStatOnConnection(iterateId, 1, 1

								, "row29"

						);
					}

///////////////////////		

					strBuffer_tLogRow_2 = new StringBuilder();

					if (row29.filepath3 != null) { //

						strBuffer_tLogRow_2.append(String.valueOf(row29.filepath3));

					} //

					if (globalMap.get("tLogRow_CONSOLE") != null) {
						consoleOut_tLogRow_2 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
					} else {
						consoleOut_tLogRow_2 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
						globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_2);
					}
					consoleOut_tLogRow_2.println(strBuffer_tLogRow_2.toString());
					consoleOut_tLogRow_2.flush();
					nb_line_tLogRow_2++;
//////

//////                    

///////////////////////    			

					tos_count_tLogRow_2++;

					/**
					 * [tLogRow_2 main ] stop
					 */

					/**
					 * [tLogRow_2 process_data_begin ] start
					 */

					currentComponent = "tLogRow_2";

					/**
					 * [tLogRow_2 process_data_begin ] stop
					 */

					/**
					 * [tLogRow_2 process_data_end ] start
					 */

					currentComponent = "tLogRow_2";

					/**
					 * [tLogRow_2 process_data_end ] stop
					 */

					/**
					 * [tIterateToFlow_3_AI process_data_end ] start
					 */

					currentVirtualComponent = "tIterateToFlow_3";

					currentComponent = "tIterateToFlow_3_AI";

					/**
					 * [tIterateToFlow_3_AI process_data_end ] stop
					 */

					/**
					 * [tIterateToFlow_3_AI end ] start
					 */

					currentVirtualComponent = "tIterateToFlow_3";

					currentComponent = "tIterateToFlow_3_AI";

					nb_line_tIterateToFlow_3_AI++;
				}
				globalMap.put("tIterateToFlow_3_AI_NB_LINE", nb_line_tIterateToFlow_3_AI);

				ok_Hash.put("tIterateToFlow_3_AI", true);
				end_Hash.put("tIterateToFlow_3_AI", System.currentTimeMillis());

				/**
				 * [tIterateToFlow_3_AI end ] stop
				 */

				/**
				 * [tLogRow_2 end ] start
				 */

				currentComponent = "tLogRow_2";

//////
//////
				globalMap.put("tLogRow_2_NB_LINE", nb_line_tLogRow_2);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row29");
				}

				ok_Hash.put("tLogRow_2", true);
				end_Hash.put("tLogRow_2", System.currentTimeMillis());

				/**
				 * [tLogRow_2 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tIterateToFlow_3_AI"
			globalMap.remove("tIterateToFlow_3");

			try {

				/**
				 * [tIterateToFlow_3_AI finally ] start
				 */

				currentVirtualComponent = "tIterateToFlow_3";

				currentComponent = "tIterateToFlow_3_AI";

				/**
				 * [tIterateToFlow_3_AI finally ] stop
				 */

				/**
				 * [tLogRow_2 finally ] start
				 */

				currentComponent = "tLogRow_2";

				/**
				 * [tLogRow_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tIterateToFlow_3_AI_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final monitoring monitoringClass = new monitoring();

		int exitCode = monitoringClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = monitoring.class.getClassLoader()
					.getResourceAsStream("ebds_monitoring_web/monitoring_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = monitoring.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileList_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileList_1) {
			globalMap.put("tFileList_1_SUBPROCESS_STATE", -1);

			e_tFileList_1.printStackTrace();

		}
		try {
			errorCode = null;
			tFileList_2Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileList_2) {
			globalMap.put("tFileList_2_SUBPROCESS_STATE", -1);

			e_tFileList_2.printStackTrace();

		}
		try {
			errorCode = null;
			tFileList_3Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileList_3) {
			globalMap.put("tFileList_3_SUBPROCESS_STATE", -1);

			e_tFileList_3.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : monitoring");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 905153 characters generated by Talend Open Studio for Data Integration on the
 * 31 juillet 2023 à 3:13:24 PM WAT
 ************************************************************************************************/
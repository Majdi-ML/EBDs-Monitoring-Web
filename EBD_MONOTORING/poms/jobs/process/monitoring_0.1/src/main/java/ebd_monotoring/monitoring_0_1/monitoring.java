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

package ebd_monotoring.monitoring_0_1;

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
	private final String projectName = "EBD_MONOTORING";
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

	public void tDBOutput_2_error(Exception exception, String errorComponent,
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

	public void tDBOutput_3_error(Exception exception, String errorComponent,
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

	public void tDBOutput_4_error(Exception exception, String errorComponent,
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

	public void tDBOutput_5_error(Exception exception, String errorComponent,
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

	public void tDBOutput_6_error(Exception exception, String errorComponent,
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

	public void tDBOutput_7_error(Exception exception, String errorComponent,
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

	public void tDBOutput_8_error(Exception exception, String errorComponent,
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

	public void tDBOutput_9_error(Exception exception, String errorComponent,
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

	public static class insertclusterStruct implements routines.system.IPersistableRow<insertclusterStruct> {
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		public String ref;

		public String getRef() {
			return this.ref;
		}

		public String etat;

		public String getEtat() {
			return this.etat;
		}

		public String nom_du_ressource_group_package_service_guard;

		public String getNom_du_ressource_group_package_service_guard() {
			return this.nom_du_ressource_group_package_service_guard;
		}

		public String adresse_ip;

		public String getAdresse_ip() {
			return this.adresse_ip;
		}

		public String liste_des_serveurs_concernes;

		public String getListe_des_serveurs_concernes() {
			return this.liste_des_serveurs_concernes;
		}

		public String logiciel_cluster;

		public String getLogiciel_cluster() {
			return this.logiciel_cluster;
		}

		public String version;

		public String getVersion() {
			return this.version;
		}

		public String mode;

		public String getMode() {
			return this.mode;
		}

		public String serveur_actif;

		public String getServeur_actif() {
			return this.serveur_actif;
		}

		public String complements_informations;

		public String getComplements_informations() {
			return this.complements_informations;
		}

		public String support;

		public String getSupport() {
			return this.support;
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
			final insertclusterStruct other = (insertclusterStruct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(insertclusterStruct other) {

			other.id = this.id;
			other.ref = this.ref;
			other.etat = this.etat;
			other.nom_du_ressource_group_package_service_guard = this.nom_du_ressource_group_package_service_guard;
			other.adresse_ip = this.adresse_ip;
			other.liste_des_serveurs_concernes = this.liste_des_serveurs_concernes;
			other.logiciel_cluster = this.logiciel_cluster;
			other.version = this.version;
			other.mode = this.mode;
			other.serveur_actif = this.serveur_actif;
			other.complements_informations = this.complements_informations;
			other.support = this.support;

		}

		public void copyKeysDataTo(insertclusterStruct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.ref = readString(dis);

					this.etat = readString(dis);

					this.nom_du_ressource_group_package_service_guard = readString(dis);

					this.adresse_ip = readString(dis);

					this.liste_des_serveurs_concernes = readString(dis);

					this.logiciel_cluster = readString(dis);

					this.version = readString(dis);

					this.mode = readString(dis);

					this.serveur_actif = readString(dis);

					this.complements_informations = readString(dis);

					this.support = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.ref = readString(dis);

					this.etat = readString(dis);

					this.nom_du_ressource_group_package_service_guard = readString(dis);

					this.adresse_ip = readString(dis);

					this.liste_des_serveurs_concernes = readString(dis);

					this.logiciel_cluster = readString(dis);

					this.version = readString(dis);

					this.mode = readString(dis);

					this.serveur_actif = readString(dis);

					this.complements_informations = readString(dis);

					this.support = readString(dis);

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

				writeString(this.ref, dos);

				// String

				writeString(this.etat, dos);

				// String

				writeString(this.nom_du_ressource_group_package_service_guard, dos);

				// String

				writeString(this.adresse_ip, dos);

				// String

				writeString(this.liste_des_serveurs_concernes, dos);

				// String

				writeString(this.logiciel_cluster, dos);

				// String

				writeString(this.version, dos);

				// String

				writeString(this.mode, dos);

				// String

				writeString(this.serveur_actif, dos);

				// String

				writeString(this.complements_informations, dos);

				// String

				writeString(this.support, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.ref, dos);

				// String

				writeString(this.etat, dos);

				// String

				writeString(this.nom_du_ressource_group_package_service_guard, dos);

				// String

				writeString(this.adresse_ip, dos);

				// String

				writeString(this.liste_des_serveurs_concernes, dos);

				// String

				writeString(this.logiciel_cluster, dos);

				// String

				writeString(this.version, dos);

				// String

				writeString(this.mode, dos);

				// String

				writeString(this.serveur_actif, dos);

				// String

				writeString(this.complements_informations, dos);

				// String

				writeString(this.support, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",ref=" + ref);
			sb.append(",etat=" + etat);
			sb.append(",nom_du_ressource_group_package_service_guard=" + nom_du_ressource_group_package_service_guard);
			sb.append(",adresse_ip=" + adresse_ip);
			sb.append(",liste_des_serveurs_concernes=" + liste_des_serveurs_concernes);
			sb.append(",logiciel_cluster=" + logiciel_cluster);
			sb.append(",version=" + version);
			sb.append(",mode=" + mode);
			sb.append(",serveur_actif=" + serveur_actif);
			sb.append(",complements_informations=" + complements_informations);
			sb.append(",support=" + support);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(insertclusterStruct other) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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

		public String Support;

		public String getSupport() {
			return this.Support;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

					this.Support = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

					this.Support = readString(dis);

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

				// String

				writeString(this.Support, dos);

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

				// String

				writeString(this.Support, dos);

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
			sb.append(",Support=" + Support);
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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

	public static class insertProcessStruct implements routines.system.IPersistableRow<insertProcessStruct> {
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		public String ref;

		public String getRef() {
			return this.ref;
		}

		public String etat;

		public String getEtat() {
			return this.etat;
		}

		public String ref_composant;

		public String getRef_composant() {
			return this.ref_composant;
		}

		public String process;

		public String getProcess() {
			return this.process;
		}

		public String criticite;

		public String getCriticite() {
			return this.criticite;
		}

		public String message_alarme;

		public String getMessage_alarme() {
			return this.message_alarme;
		}

		public String intervalle_de_polling;

		public String getIntervalle_de_polling() {
			return this.intervalle_de_polling;
		}

		public String objet;

		public String getObjet() {
			return this.objet;
		}

		public String nom_template;

		public String getNom_template() {
			return this.nom_template;
		}

		public String monitored_by;

		public String getMonitored_by() {
			return this.monitored_by;
		}

		public String support;

		public String getSupport() {
			return this.support;
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
			final insertProcessStruct other = (insertProcessStruct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(insertProcessStruct other) {

			other.id = this.id;
			other.ref = this.ref;
			other.etat = this.etat;
			other.ref_composant = this.ref_composant;
			other.process = this.process;
			other.criticite = this.criticite;
			other.message_alarme = this.message_alarme;
			other.intervalle_de_polling = this.intervalle_de_polling;
			other.objet = this.objet;
			other.nom_template = this.nom_template;
			other.monitored_by = this.monitored_by;
			other.support = this.support;

		}

		public void copyKeysDataTo(insertProcessStruct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.ref = readString(dis);

					this.etat = readString(dis);

					this.ref_composant = readString(dis);

					this.process = readString(dis);

					this.criticite = readString(dis);

					this.message_alarme = readString(dis);

					this.intervalle_de_polling = readString(dis);

					this.objet = readString(dis);

					this.nom_template = readString(dis);

					this.monitored_by = readString(dis);

					this.support = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.ref = readString(dis);

					this.etat = readString(dis);

					this.ref_composant = readString(dis);

					this.process = readString(dis);

					this.criticite = readString(dis);

					this.message_alarme = readString(dis);

					this.intervalle_de_polling = readString(dis);

					this.objet = readString(dis);

					this.nom_template = readString(dis);

					this.monitored_by = readString(dis);

					this.support = readString(dis);

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

				writeString(this.ref, dos);

				// String

				writeString(this.etat, dos);

				// String

				writeString(this.ref_composant, dos);

				// String

				writeString(this.process, dos);

				// String

				writeString(this.criticite, dos);

				// String

				writeString(this.message_alarme, dos);

				// String

				writeString(this.intervalle_de_polling, dos);

				// String

				writeString(this.objet, dos);

				// String

				writeString(this.nom_template, dos);

				// String

				writeString(this.monitored_by, dos);

				// String

				writeString(this.support, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.ref, dos);

				// String

				writeString(this.etat, dos);

				// String

				writeString(this.ref_composant, dos);

				// String

				writeString(this.process, dos);

				// String

				writeString(this.criticite, dos);

				// String

				writeString(this.message_alarme, dos);

				// String

				writeString(this.intervalle_de_polling, dos);

				// String

				writeString(this.objet, dos);

				// String

				writeString(this.nom_template, dos);

				// String

				writeString(this.monitored_by, dos);

				// String

				writeString(this.support, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",ref=" + ref);
			sb.append(",etat=" + etat);
			sb.append(",ref_composant=" + ref_composant);
			sb.append(",process=" + process);
			sb.append(",criticite=" + criticite);
			sb.append(",message_alarme=" + message_alarme);
			sb.append(",intervalle_de_polling=" + intervalle_de_polling);
			sb.append(",objet=" + objet);
			sb.append(",nom_template=" + nom_template);
			sb.append(",monitored_by=" + monitored_by);
			sb.append(",support=" + support);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(insertProcessStruct other) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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

		public String Support;

		public String getSupport() {
			return this.Support;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

					this.Support = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

					this.Support = readString(dis);

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

				// String

				writeString(this.Support, dos);

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

				// String

				writeString(this.Support, dos);

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
			sb.append(",Support=" + Support);
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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

	public static class insertrqueteStruct implements routines.system.IPersistableRow<insertrqueteStruct> {
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		public String ref;

		public String getRef() {
			return this.ref;
		}

		public String etat;

		public String getEtat() {
			return this.etat;
		}

		public String ref_composant;

		public String getRef_composant() {
			return this.ref_composant;
		}

		public String rg_sg_si_cluster;

		public String getRg_sg_si_cluster() {
			return this.rg_sg_si_cluster;
		}

		public String requete_sql;

		public String getRequete_sql() {
			return this.requete_sql;
		}

		public String username_db_name;

		public String getUsername_db_name() {
			return this.username_db_name;
		}

		public String resultat_attendu_de_la_requete;

		public String getResultat_attendu_de_la_requete() {
			return this.resultat_attendu_de_la_requete;
		}

		public String perform_action;

		public String getPerform_action() {
			return this.perform_action;
		}

		public String criticite;

		public String getCriticite() {
			return this.criticite;
		}

		public String message_alarme;

		public String getMessage_alarme() {
			return this.message_alarme;
		}

		public String instructions;

		public String getInstructions() {
			return this.instructions;
		}

		public String intervalle_de_polling;

		public String getIntervalle_de_polling() {
			return this.intervalle_de_polling;
		}

		public String ref_service;

		public String getRef_service() {
			return this.ref_service;
		}

		public String objet;

		public String getObjet() {
			return this.objet;
		}

		public String monitored_by;

		public String getMonitored_by() {
			return this.monitored_by;
		}

		public String nom_template;

		public String getNom_template() {
			return this.nom_template;
		}

		public String support;

		public String getSupport() {
			return this.support;
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
			final insertrqueteStruct other = (insertrqueteStruct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(insertrqueteStruct other) {

			other.id = this.id;
			other.ref = this.ref;
			other.etat = this.etat;
			other.ref_composant = this.ref_composant;
			other.rg_sg_si_cluster = this.rg_sg_si_cluster;
			other.requete_sql = this.requete_sql;
			other.username_db_name = this.username_db_name;
			other.resultat_attendu_de_la_requete = this.resultat_attendu_de_la_requete;
			other.perform_action = this.perform_action;
			other.criticite = this.criticite;
			other.message_alarme = this.message_alarme;
			other.instructions = this.instructions;
			other.intervalle_de_polling = this.intervalle_de_polling;
			other.ref_service = this.ref_service;
			other.objet = this.objet;
			other.monitored_by = this.monitored_by;
			other.nom_template = this.nom_template;
			other.support = this.support;

		}

		public void copyKeysDataTo(insertrqueteStruct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.ref = readString(dis);

					this.etat = readString(dis);

					this.ref_composant = readString(dis);

					this.rg_sg_si_cluster = readString(dis);

					this.requete_sql = readString(dis);

					this.username_db_name = readString(dis);

					this.resultat_attendu_de_la_requete = readString(dis);

					this.perform_action = readString(dis);

					this.criticite = readString(dis);

					this.message_alarme = readString(dis);

					this.instructions = readString(dis);

					this.intervalle_de_polling = readString(dis);

					this.ref_service = readString(dis);

					this.objet = readString(dis);

					this.monitored_by = readString(dis);

					this.nom_template = readString(dis);

					this.support = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.ref = readString(dis);

					this.etat = readString(dis);

					this.ref_composant = readString(dis);

					this.rg_sg_si_cluster = readString(dis);

					this.requete_sql = readString(dis);

					this.username_db_name = readString(dis);

					this.resultat_attendu_de_la_requete = readString(dis);

					this.perform_action = readString(dis);

					this.criticite = readString(dis);

					this.message_alarme = readString(dis);

					this.instructions = readString(dis);

					this.intervalle_de_polling = readString(dis);

					this.ref_service = readString(dis);

					this.objet = readString(dis);

					this.monitored_by = readString(dis);

					this.nom_template = readString(dis);

					this.support = readString(dis);

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

				writeString(this.ref, dos);

				// String

				writeString(this.etat, dos);

				// String

				writeString(this.ref_composant, dos);

				// String

				writeString(this.rg_sg_si_cluster, dos);

				// String

				writeString(this.requete_sql, dos);

				// String

				writeString(this.username_db_name, dos);

				// String

				writeString(this.resultat_attendu_de_la_requete, dos);

				// String

				writeString(this.perform_action, dos);

				// String

				writeString(this.criticite, dos);

				// String

				writeString(this.message_alarme, dos);

				// String

				writeString(this.instructions, dos);

				// String

				writeString(this.intervalle_de_polling, dos);

				// String

				writeString(this.ref_service, dos);

				// String

				writeString(this.objet, dos);

				// String

				writeString(this.monitored_by, dos);

				// String

				writeString(this.nom_template, dos);

				// String

				writeString(this.support, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.ref, dos);

				// String

				writeString(this.etat, dos);

				// String

				writeString(this.ref_composant, dos);

				// String

				writeString(this.rg_sg_si_cluster, dos);

				// String

				writeString(this.requete_sql, dos);

				// String

				writeString(this.username_db_name, dos);

				// String

				writeString(this.resultat_attendu_de_la_requete, dos);

				// String

				writeString(this.perform_action, dos);

				// String

				writeString(this.criticite, dos);

				// String

				writeString(this.message_alarme, dos);

				// String

				writeString(this.instructions, dos);

				// String

				writeString(this.intervalle_de_polling, dos);

				// String

				writeString(this.ref_service, dos);

				// String

				writeString(this.objet, dos);

				// String

				writeString(this.monitored_by, dos);

				// String

				writeString(this.nom_template, dos);

				// String

				writeString(this.support, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",ref=" + ref);
			sb.append(",etat=" + etat);
			sb.append(",ref_composant=" + ref_composant);
			sb.append(",rg_sg_si_cluster=" + rg_sg_si_cluster);
			sb.append(",requete_sql=" + requete_sql);
			sb.append(",username_db_name=" + username_db_name);
			sb.append(",resultat_attendu_de_la_requete=" + resultat_attendu_de_la_requete);
			sb.append(",perform_action=" + perform_action);
			sb.append(",criticite=" + criticite);
			sb.append(",message_alarme=" + message_alarme);
			sb.append(",instructions=" + instructions);
			sb.append(",intervalle_de_polling=" + intervalle_de_polling);
			sb.append(",ref_service=" + ref_service);
			sb.append(",objet=" + objet);
			sb.append(",monitored_by=" + monitored_by);
			sb.append(",nom_template=" + nom_template);
			sb.append(",support=" + support);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(insertrqueteStruct other) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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

		public String Support;

		public String getSupport() {
			return this.Support;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

					this.Support = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

					this.Support = readString(dis);

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

				// String

				writeString(this.Support, dos);

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

				// String

				writeString(this.Support, dos);

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
			sb.append(",Support=" + Support);
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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.filepath = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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
				insertclusterStruct insertcluster = new insertclusterStruct();
				row2Struct row2 = new row2Struct();
				row11Struct row11 = new row11Struct();
				row20Struct row20 = new row20Struct();
				insertProcessStruct insertProcess = new insertProcessStruct();
				row3Struct row3 = new row3Struct();
				row12Struct row12 = new row12Struct();
				row21Struct row21 = new row21Struct();
				insertrqueteStruct insertrquete = new insertrqueteStruct();

				/**
				 * [tFileList_1 begin ] start
				 */

				int NB_ITERATE_tFileInputExcel_1 = 0; // for statistics

				int NB_ITERATE_tFileInputExcel_13 = 0; // for statistics

				int NB_ITERATE_tIterateToFlow_1_ITFO = 0; // for statistics

				int NB_ITERATE_tFileInputExcel_18 = 0; // for statistics

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
						runStat.updateStatOnConnection("row1", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("insertcluster", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row19", 3, 0);
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
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "insertcluster");
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

					String url_tDBOutput_1 = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "ooredoo" + "?"
							+ properties_tDBOutput_1;

					String driverClass_tDBOutput_1 = "com.mysql.cj.jdbc.Driver";

					String dbUser_tDBOutput_1 = "root";

					final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:WYy58hi1PY5IbX+f5CDHwRB5qJ4x+KvlHgsoQA==");

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
							+ "` (`id`,`ref`,`etat`,`nom_du_ressource_group_package_service_guard`,`adresse_ip`,`liste_des_serveurs_concernes`,`logiciel_cluster`,`version`,`mode`,`serveur_actif`,`complements_informations`,`support`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

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
					insertclusterStruct insertcluster_tmp = new insertclusterStruct();
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
							.decryptPassword("enc:routine.encryption.key.v1:GdAqf9PCJgjapiWM7Wbc/OSdlqGwurpXh1DHOw==");
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
										boolean startAdding = false;
										StringBuilder supportBuilder = new StringBuilder();

										for (String part : parts) {
											if (startAdding) {
												supportBuilder.append(part).append("_");
											}
											if (part.equals("Support")) {
												startAdding = true;
											}
										}

										String supportPart = supportBuilder.substring(0, supportBuilder.length() - 1);

										String support = supportPart;

										String equipe;

										if ("CLOUD_et_APP_IT.xlsx".equals(support)) {
											equipe = "cloud";
										} else if ("ASE_VAS.xlsx".equals(support)) {
											equipe = "ASEVAS";
										} else if ("BILLING.xlsx".equals(support)) {
											equipe = "BILLING";
										} else if ("ASE_IN.xlsx".equals(support)) {
											equipe = "ASEIN";
										} else if ("Backup.xlsx".equals(support)) {
											equipe = "Backup";
										} else if ("ASE_GPRS.xlsx".equals(support)) {
											equipe = "ASEGPRS";
										} else {
											// Valeur par défaut si aucune des conditions n'est satisfaite
											equipe = "Autre";
										}

// Récupérer la valeur de "N__Ref" et la convertir en String
										String rowRef = row10.N__Ref;

// Générer l'ID "EDB_POSANET_X"
										String id1 = "EBD_" + fileWord + "_Cluster_" + rowRef;

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
										row19.Support = equipe;

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

											insertcluster = null;

// # Output table : 'insertcluster'
											insertcluster_tmp.id = row19.N__Ref;
											insertcluster_tmp.ref = row19.Ref;
											insertcluster_tmp.etat = row19.Etat;
											insertcluster_tmp.nom_du_ressource_group_package_service_guard = row19.Nom_du_Ressource_Group___Package_Service_Guard;
											insertcluster_tmp.adresse_ip = row19.Adresse_IP;
											insertcluster_tmp.liste_des_serveurs_concernes = row19.Liste_des_serveurs_concernes;
											insertcluster_tmp.logiciel_cluster = row19.Logiciel_Cluster;
											insertcluster_tmp.version = row19.Version;
											insertcluster_tmp.mode = row19.Mode;
											insertcluster_tmp.serveur_actif = row19.Serveur_Actif;
											insertcluster_tmp.complements_informations = row19.Complements_d_informations;
											insertcluster_tmp.support = row19.Support;
											insertcluster = insertcluster_tmp;
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
// Start of branch "insertcluster"
										if (insertcluster != null) {

											/**
											 * [tDBOutput_1 main ] start
											 */

											currentComponent = "tDBOutput_1";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "insertcluster"

												);
											}

											whetherReject_tDBOutput_1 = false;
											if (insertcluster.id == null) {
												pstmt_tDBOutput_1.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(1, insertcluster.id);
											}

											if (insertcluster.ref == null) {
												pstmt_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(2, insertcluster.ref);
											}

											if (insertcluster.etat == null) {
												pstmt_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(3, insertcluster.etat);
											}

											if (insertcluster.nom_du_ressource_group_package_service_guard == null) {
												pstmt_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(4,
														insertcluster.nom_du_ressource_group_package_service_guard);
											}

											if (insertcluster.adresse_ip == null) {
												pstmt_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(5, insertcluster.adresse_ip);
											}

											if (insertcluster.liste_des_serveurs_concernes == null) {
												pstmt_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(6,
														insertcluster.liste_des_serveurs_concernes);
											}

											if (insertcluster.logiciel_cluster == null) {
												pstmt_tDBOutput_1.setNull(7, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(7, insertcluster.logiciel_cluster);
											}

											if (insertcluster.version == null) {
												pstmt_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(8, insertcluster.version);
											}

											if (insertcluster.mode == null) {
												pstmt_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(9, insertcluster.mode);
											}

											if (insertcluster.serveur_actif == null) {
												pstmt_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(10, insertcluster.serveur_actif);
											}

											if (insertcluster.complements_informations == null) {
												pstmt_tDBOutput_1.setNull(11, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(11, insertcluster.complements_informations);
											}

											if (insertcluster.support == null) {
												pstmt_tDBOutput_1.setNull(12, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(12, insertcluster.support);
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

										} // End of branch "insertcluster"

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
						runStat.updateStat(resourceMap, iterateId, 2, 0, "insertcluster");
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
						runStat.updateStatOnConnection("row11", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row20", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row2", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("insertProcess", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate3", 1, "exec" + NB_ITERATE_tFileInputExcel_13);
						// Thread.sleep(1000);
					}

					/**
					 * [tDBOutput_2 begin ] start
					 */

					ok_Hash.put("tDBOutput_2", false);
					start_Hash.put("tDBOutput_2", System.currentTimeMillis());

					currentComponent = "tDBOutput_2";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "insertProcess");
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

					String tableName_tDBOutput_2 = "process";
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

					String url_tDBOutput_2 = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "ooredoo" + "?"
							+ properties_tDBOutput_2;

					String driverClass_tDBOutput_2 = "com.mysql.cj.jdbc.Driver";

					String dbUser_tDBOutput_2 = "root";

					final String decryptedPassword_tDBOutput_2 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:Xsi19sknir3bQcn8olvtGfjWZryFdD0WUrSyhQ==");

					String dbPwd_tDBOutput_2 = decryptedPassword_tDBOutput_2;
					java.lang.Class.forName(driverClass_tDBOutput_2);

					conn_tDBOutput_2 = java.sql.DriverManager.getConnection(url_tDBOutput_2, dbUser_tDBOutput_2,
							dbPwd_tDBOutput_2);

					resourceMap.put("conn_tDBOutput_2", conn_tDBOutput_2);
					conn_tDBOutput_2.setAutoCommit(false);
					int commitEvery_tDBOutput_2 = 10000;
					int commitCounter_tDBOutput_2 = 0;

					int count_tDBOutput_2 = 0;

					String insert_tDBOutput_2 = "INSERT IGNORE INTO `" + "process"
							+ "` (`id`,`ref`,`etat`,`ref_composant`,`process`,`criticite`,`message_alarme`,`intervalle_de_polling`,`objet`,`nom_template`,`monitored_by`,`support`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

					java.sql.PreparedStatement pstmt_tDBOutput_2 = conn_tDBOutput_2
							.prepareStatement(insert_tDBOutput_2);
					resourceMap.put("pstmt_tDBOutput_2", pstmt_tDBOutput_2);

					/**
					 * [tDBOutput_2 begin ] stop
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
					insertProcessStruct insertProcess_tmp = new insertProcessStruct();
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
							.decryptPassword("enc:routine.encryption.key.v1:+WttqSYhTv+vzZ0lZ97YeS19mweK+n77JvR7mw==");
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
										boolean startAdding = false;
										StringBuilder supportBuilder = new StringBuilder();

										for (String part : parts) {
											if (startAdding) {
												supportBuilder.append(part).append("_");
											}
											if (part.equals("Support")) {
												startAdding = true;
											}
										}

										String supportPart = supportBuilder.substring(0, supportBuilder.length() - 1);

										String support = supportPart;

										String equipe;

										if ("CLOUD_et_APP_IT.xlsx".equals(support)) {
											equipe = "cloud";
										} else if ("ASE_VAS.xlsx".equals(support)) {
											equipe = "ASEVAS";
										} else if ("BILLING.xlsx".equals(support)) {
											equipe = "BILLING";
										} else if ("ASE_IN.xlsx".equals(support)) {
											equipe = "ASEIN";
										} else if ("Backup.xlsx".equals(support)) {
											equipe = "Backup";
										} else if ("ASE_GPRS.xlsx".equals(support)) {
											equipe = "ASEGPRS";
										} else {
											// Valeur par défaut si aucune des conditions n'est satisfaite
											equipe = "Autre";
										}
// Récupérer la valeur de "N__Ref" et la convertir en String
										String rowRef = row11.N__Ref;

// Générer l'ID "EDB_POSANET_X"
										String id = "EBD_" + fileWord + "_Process_" + rowRef;
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
										row20.Support = equipe;

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

											insertProcess = null;

// # Output table : 'insertProcess'
											insertProcess_tmp.id = row20.N__Ref;
											insertProcess_tmp.ref = row20.Ref;
											insertProcess_tmp.etat = row20.Etat;
											insertProcess_tmp.ref_composant = row20.Ref__des_composants;
											insertProcess_tmp.process = row20.Process;
											insertProcess_tmp.criticite = row20.Criticite;
											insertProcess_tmp.message_alarme = row20.Message_d_alarme;
											insertProcess_tmp.intervalle_de_polling = row20.Intervalle_de_polling;
											insertProcess_tmp.objet = row20.Objet;
											insertProcess_tmp.nom_template = row20.Nom_Template;
											insertProcess_tmp.monitored_by = row20.Monitored_By;
											insertProcess_tmp.support = row20.Support;
											insertProcess = insertProcess_tmp;
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
// Start of branch "insertProcess"
										if (insertProcess != null) {

											/**
											 * [tDBOutput_2 main ] start
											 */

											currentComponent = "tDBOutput_2";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "insertProcess"

												);
											}

											whetherReject_tDBOutput_2 = false;
											if (insertProcess.id == null) {
												pstmt_tDBOutput_2.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(1, insertProcess.id);
											}

											if (insertProcess.ref == null) {
												pstmt_tDBOutput_2.setNull(2, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(2, insertProcess.ref);
											}

											if (insertProcess.etat == null) {
												pstmt_tDBOutput_2.setNull(3, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(3, insertProcess.etat);
											}

											if (insertProcess.ref_composant == null) {
												pstmt_tDBOutput_2.setNull(4, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(4, insertProcess.ref_composant);
											}

											if (insertProcess.process == null) {
												pstmt_tDBOutput_2.setNull(5, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(5, insertProcess.process);
											}

											if (insertProcess.criticite == null) {
												pstmt_tDBOutput_2.setNull(6, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(6, insertProcess.criticite);
											}

											if (insertProcess.message_alarme == null) {
												pstmt_tDBOutput_2.setNull(7, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(7, insertProcess.message_alarme);
											}

											if (insertProcess.intervalle_de_polling == null) {
												pstmt_tDBOutput_2.setNull(8, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(8, insertProcess.intervalle_de_polling);
											}

											if (insertProcess.objet == null) {
												pstmt_tDBOutput_2.setNull(9, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(9, insertProcess.objet);
											}

											if (insertProcess.nom_template == null) {
												pstmt_tDBOutput_2.setNull(10, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(10, insertProcess.nom_template);
											}

											if (insertProcess.monitored_by == null) {
												pstmt_tDBOutput_2.setNull(11, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(11, insertProcess.monitored_by);
											}

											if (insertProcess.support == null) {
												pstmt_tDBOutput_2.setNull(12, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_2.setString(12, insertProcess.support);
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

										} // End of branch "insertProcess"

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
						runStat.updateStat(resourceMap, iterateId, 2, 0, "insertProcess");
					}

					ok_Hash.put("tDBOutput_2", true);
					end_Hash.put("tDBOutput_2", System.currentTimeMillis());

					/**
					 * [tDBOutput_2 end ] stop
					 */

					if (execStat) {
						runStat.updateStatOnConnection("iterate3", 2, "exec" + NB_ITERATE_tFileInputExcel_13);
					}

					NB_ITERATE_tFileInputExcel_18++;

					if (execStat) {
						runStat.updateStatOnConnection("insertrquete", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row3", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row12", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row21", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate4", 1, "exec" + NB_ITERATE_tFileInputExcel_18);
						// Thread.sleep(1000);
					}

					/**
					 * [tDBOutput_3 begin ] start
					 */

					ok_Hash.put("tDBOutput_3", false);
					start_Hash.put("tDBOutput_3", System.currentTimeMillis());

					currentComponent = "tDBOutput_3";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "insertrquete");
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

					String tableName_tDBOutput_3 = "requetessql";
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

					String url_tDBOutput_3 = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "ooredoo" + "?"
							+ properties_tDBOutput_3;

					String driverClass_tDBOutput_3 = "com.mysql.cj.jdbc.Driver";

					String dbUser_tDBOutput_3 = "root";

					final String decryptedPassword_tDBOutput_3 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:3o1QtdMN4MmifaCGi5C48kmYPNn3p1qfaZ+kRA==");

					String dbPwd_tDBOutput_3 = decryptedPassword_tDBOutput_3;
					java.lang.Class.forName(driverClass_tDBOutput_3);

					conn_tDBOutput_3 = java.sql.DriverManager.getConnection(url_tDBOutput_3, dbUser_tDBOutput_3,
							dbPwd_tDBOutput_3);

					resourceMap.put("conn_tDBOutput_3", conn_tDBOutput_3);
					conn_tDBOutput_3.setAutoCommit(false);
					int commitEvery_tDBOutput_3 = 10000;
					int commitCounter_tDBOutput_3 = 0;

					int count_tDBOutput_3 = 0;

					String insert_tDBOutput_3 = "INSERT IGNORE INTO `" + "requetessql"
							+ "` (`id`,`ref`,`etat`,`ref_composant`,`rg_sg_si_cluster`,`requete_sql`,`username_db_name`,`resultat_attendu_de_la_requete`,`perform_action`,`criticite`,`message_alarme`,`instructions`,`intervalle_de_polling`,`ref_service`,`objet`,`monitored_by`,`nom_template`,`support`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

					java.sql.PreparedStatement pstmt_tDBOutput_3 = conn_tDBOutput_3
							.prepareStatement(insert_tDBOutput_3);
					resourceMap.put("pstmt_tDBOutput_3", pstmt_tDBOutput_3);

					/**
					 * [tDBOutput_3 begin ] stop
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
					insertrqueteStruct insertrquete_tmp = new insertrqueteStruct();
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
							.decryptPassword("enc:routine.encryption.key.v1:yunu9rBvAHmuuI8iIOODkjAtbIfIHgxDu7Tsow==");
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
										boolean startAdding = false;
										StringBuilder supportBuilder = new StringBuilder();

										for (String part : parts) {
											if (startAdding) {
												supportBuilder.append(part).append("_");
											}
											if (part.equals("Support")) {
												startAdding = true;
											}
										}

										String supportPart = supportBuilder.substring(0, supportBuilder.length() - 1);

										String support = supportPart;

										String equipe;

										if ("CLOUD_et_APP_IT.xlsx".equals(support)) {
											equipe = "cloud";
										} else if ("ASE_VAS.xlsx".equals(support)) {
											equipe = "ASEVAS";
										} else if ("BILLING.xlsx".equals(support)) {
											equipe = "BILLING";
										} else if ("ASE_IN.xlsx".equals(support)) {
											equipe = "ASEIN";
										} else if ("Backup.xlsx".equals(support)) {
											equipe = "Backup";
										} else if ("ASE_GPRS.xlsx".equals(support)) {
											equipe = "ASEGPRS";
										} else {
											// Valeur par défaut si aucune des conditions n'est satisfaite
											equipe = "Autre";
										}

// Récupérer la valeur de "N__Ref" et la convertir en String
										String rowRef = row12.N__Ref;

// Générer l'ID "EDB_POSANET_X"
										String id = "EBD_" + fileWord + "_RequetesSql_" + rowRef;
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
										row21.Support = equipe;
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

											insertrquete = null;

// # Output table : 'insertrquete'
											insertrquete_tmp.id = row21.N__Ref;
											insertrquete_tmp.ref = row21.Ref;
											insertrquete_tmp.etat = row21.Etat;
											insertrquete_tmp.ref_composant = row21.Ref__des_composants;
											insertrquete_tmp.rg_sg_si_cluster = row21.RG_SG_si_Cluster;
											insertrquete_tmp.requete_sql = row21.Requete_SQL;
											insertrquete_tmp.username_db_name = row21.UserName_DB_Name;
											insertrquete_tmp.resultat_attendu_de_la_requete = row21.Resultat_Attendu_de_la_requete;
											insertrquete_tmp.perform_action = row21.Perform_action;
											insertrquete_tmp.criticite = row21.Criticite;
											insertrquete_tmp.message_alarme = row21.Message_d_alarme;
											insertrquete_tmp.instructions = row21.Instructions;
											insertrquete_tmp.intervalle_de_polling = row21.Intervalle_de_polling;
											insertrquete_tmp.ref_service = row21.Ref__Service;
											insertrquete_tmp.objet = row21.Objet;
											insertrquete_tmp.monitored_by = row21.Monitored_By;
											insertrquete_tmp.nom_template = row21.Nom_Template;
											insertrquete_tmp.support = row21.Support;
											insertrquete = insertrquete_tmp;
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
// Start of branch "insertrquete"
										if (insertrquete != null) {

											/**
											 * [tDBOutput_3 main ] start
											 */

											currentComponent = "tDBOutput_3";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "insertrquete"

												);
											}

											whetherReject_tDBOutput_3 = false;
											if (insertrquete.id == null) {
												pstmt_tDBOutput_3.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(1, insertrquete.id);
											}

											if (insertrquete.ref == null) {
												pstmt_tDBOutput_3.setNull(2, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(2, insertrquete.ref);
											}

											if (insertrquete.etat == null) {
												pstmt_tDBOutput_3.setNull(3, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(3, insertrquete.etat);
											}

											if (insertrquete.ref_composant == null) {
												pstmt_tDBOutput_3.setNull(4, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(4, insertrquete.ref_composant);
											}

											if (insertrquete.rg_sg_si_cluster == null) {
												pstmt_tDBOutput_3.setNull(5, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(5, insertrquete.rg_sg_si_cluster);
											}

											if (insertrquete.requete_sql == null) {
												pstmt_tDBOutput_3.setNull(6, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(6, insertrquete.requete_sql);
											}

											if (insertrquete.username_db_name == null) {
												pstmt_tDBOutput_3.setNull(7, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(7, insertrquete.username_db_name);
											}

											if (insertrquete.resultat_attendu_de_la_requete == null) {
												pstmt_tDBOutput_3.setNull(8, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(8,
														insertrquete.resultat_attendu_de_la_requete);
											}

											if (insertrquete.perform_action == null) {
												pstmt_tDBOutput_3.setNull(9, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(9, insertrquete.perform_action);
											}

											if (insertrquete.criticite == null) {
												pstmt_tDBOutput_3.setNull(10, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(10, insertrquete.criticite);
											}

											if (insertrquete.message_alarme == null) {
												pstmt_tDBOutput_3.setNull(11, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(11, insertrquete.message_alarme);
											}

											if (insertrquete.instructions == null) {
												pstmt_tDBOutput_3.setNull(12, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(12, insertrquete.instructions);
											}

											if (insertrquete.intervalle_de_polling == null) {
												pstmt_tDBOutput_3.setNull(13, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(13, insertrquete.intervalle_de_polling);
											}

											if (insertrquete.ref_service == null) {
												pstmt_tDBOutput_3.setNull(14, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(14, insertrquete.ref_service);
											}

											if (insertrquete.objet == null) {
												pstmt_tDBOutput_3.setNull(15, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(15, insertrquete.objet);
											}

											if (insertrquete.monitored_by == null) {
												pstmt_tDBOutput_3.setNull(16, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(16, insertrquete.monitored_by);
											}

											if (insertrquete.nom_template == null) {
												pstmt_tDBOutput_3.setNull(17, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(17, insertrquete.nom_template);
											}

											if (insertrquete.support == null) {
												pstmt_tDBOutput_3.setNull(18, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_3.setString(18, insertrquete.support);
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

										} // End of branch "insertrquete"

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
						runStat.updateStat(resourceMap, iterateId, 2, 0, "insertrquete");
					}

					ok_Hash.put("tDBOutput_3", true);
					end_Hash.put("tDBOutput_3", System.currentTimeMillis());

					/**
					 * [tDBOutput_3 end ] stop
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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		public String ref;

		public String getRef() {
			return this.ref;
		}

		public String etat;

		public String getEtat() {
			return this.etat;
		}

		public String ref_composant;

		public String getRef_composant() {
			return this.ref_composant;
		}

		public String rg_sg_si_cluster;

		public String getRg_sg_si_cluster() {
			return this.rg_sg_si_cluster;
		}

		public String logfile;

		public String getLogfile() {
			return this.logfile;
		}

		public String localisation;

		public String getLocalisation() {
			return this.localisation;
		}

		public String description;

		public String getDescription() {
			return this.description;
		}

		public String format_logfile;

		public String getFormat_logfile() {
			return this.format_logfile;
		}

		public String separateur;

		public String getSeparateur() {
			return this.separateur;
		}

		public String intervalle_de_polling;

		public String getIntervalle_de_polling() {
			return this.intervalle_de_polling;
		}

		public String monitored_by;

		public String getMonitored_by() {
			return this.monitored_by;
		}

		public String fourni_en_annexe;

		public String getFourni_en_annexe() {
			return this.fourni_en_annexe;
		}

		public String ref_service;

		public String getRef_service() {
			return this.ref_service;
		}

		public String nom_template;

		public String getNom_template() {
			return this.nom_template;
		}

		public String log_conditions;

		public String getLog_conditions() {
			return this.log_conditions;
		}

		public String support;

		public String getSupport() {
			return this.support;
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
			other.ref = this.ref;
			other.etat = this.etat;
			other.ref_composant = this.ref_composant;
			other.rg_sg_si_cluster = this.rg_sg_si_cluster;
			other.logfile = this.logfile;
			other.localisation = this.localisation;
			other.description = this.description;
			other.format_logfile = this.format_logfile;
			other.separateur = this.separateur;
			other.intervalle_de_polling = this.intervalle_de_polling;
			other.monitored_by = this.monitored_by;
			other.fourni_en_annexe = this.fourni_en_annexe;
			other.ref_service = this.ref_service;
			other.nom_template = this.nom_template;
			other.log_conditions = this.log_conditions;
			other.support = this.support;

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.ref = readString(dis);

					this.etat = readString(dis);

					this.ref_composant = readString(dis);

					this.rg_sg_si_cluster = readString(dis);

					this.logfile = readString(dis);

					this.localisation = readString(dis);

					this.description = readString(dis);

					this.format_logfile = readString(dis);

					this.separateur = readString(dis);

					this.intervalle_de_polling = readString(dis);

					this.monitored_by = readString(dis);

					this.fourni_en_annexe = readString(dis);

					this.ref_service = readString(dis);

					this.nom_template = readString(dis);

					this.log_conditions = readString(dis);

					this.support = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.ref = readString(dis);

					this.etat = readString(dis);

					this.ref_composant = readString(dis);

					this.rg_sg_si_cluster = readString(dis);

					this.logfile = readString(dis);

					this.localisation = readString(dis);

					this.description = readString(dis);

					this.format_logfile = readString(dis);

					this.separateur = readString(dis);

					this.intervalle_de_polling = readString(dis);

					this.monitored_by = readString(dis);

					this.fourni_en_annexe = readString(dis);

					this.ref_service = readString(dis);

					this.nom_template = readString(dis);

					this.log_conditions = readString(dis);

					this.support = readString(dis);

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

				writeString(this.ref, dos);

				// String

				writeString(this.etat, dos);

				// String

				writeString(this.ref_composant, dos);

				// String

				writeString(this.rg_sg_si_cluster, dos);

				// String

				writeString(this.logfile, dos);

				// String

				writeString(this.localisation, dos);

				// String

				writeString(this.description, dos);

				// String

				writeString(this.format_logfile, dos);

				// String

				writeString(this.separateur, dos);

				// String

				writeString(this.intervalle_de_polling, dos);

				// String

				writeString(this.monitored_by, dos);

				// String

				writeString(this.fourni_en_annexe, dos);

				// String

				writeString(this.ref_service, dos);

				// String

				writeString(this.nom_template, dos);

				// String

				writeString(this.log_conditions, dos);

				// String

				writeString(this.support, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.ref, dos);

				// String

				writeString(this.etat, dos);

				// String

				writeString(this.ref_composant, dos);

				// String

				writeString(this.rg_sg_si_cluster, dos);

				// String

				writeString(this.logfile, dos);

				// String

				writeString(this.localisation, dos);

				// String

				writeString(this.description, dos);

				// String

				writeString(this.format_logfile, dos);

				// String

				writeString(this.separateur, dos);

				// String

				writeString(this.intervalle_de_polling, dos);

				// String

				writeString(this.monitored_by, dos);

				// String

				writeString(this.fourni_en_annexe, dos);

				// String

				writeString(this.ref_service, dos);

				// String

				writeString(this.nom_template, dos);

				// String

				writeString(this.log_conditions, dos);

				// String

				writeString(this.support, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",ref=" + ref);
			sb.append(",etat=" + etat);
			sb.append(",ref_composant=" + ref_composant);
			sb.append(",rg_sg_si_cluster=" + rg_sg_si_cluster);
			sb.append(",logfile=" + logfile);
			sb.append(",localisation=" + localisation);
			sb.append(",description=" + description);
			sb.append(",format_logfile=" + format_logfile);
			sb.append(",separateur=" + separateur);
			sb.append(",intervalle_de_polling=" + intervalle_de_polling);
			sb.append(",monitored_by=" + monitored_by);
			sb.append(",fourni_en_annexe=" + fourni_en_annexe);
			sb.append(",ref_service=" + ref_service);
			sb.append(",nom_template=" + nom_template);
			sb.append(",log_conditions=" + log_conditions);
			sb.append(",support=" + support);
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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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

		public String Support;

		public String getSupport() {
			return this.Support;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

					this.Support = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

					this.Support = readString(dis);

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

				// String

				writeString(this.Support, dos);

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

				// String

				writeString(this.Support, dos);

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
			sb.append(",Support=" + Support);
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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		public String ref;

		public String getRef() {
			return this.ref;
		}

		public String etat;

		public String getEtat() {
			return this.etat;
		}

		public String ref_composant;

		public String getRef_composant() {
			return this.ref_composant;
		}

		public String rg_sg_si_cluster;

		public String getRg_sg_si_cluster() {
			return this.rg_sg_si_cluster;
		}

		public String script;

		public String getScript() {
			return this.script;
		}

		public String code_erreur;

		public String getCode_erreur() {
			return this.code_erreur;
		}

		public String criticite;

		public String getCriticite() {
			return this.criticite;
		}

		public String description;

		public String getDescription() {
			return this.description;
		}

		public String instructions;

		public String getInstructions() {
			return this.instructions;
		}

		public String monitored_by;

		public String getMonitored_by() {
			return this.monitored_by;
		}

		public String ref_service;

		public String getRef_service() {
			return this.ref_service;
		}

		public String support;

		public String getSupport() {
			return this.support;
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
			other.ref = this.ref;
			other.etat = this.etat;
			other.ref_composant = this.ref_composant;
			other.rg_sg_si_cluster = this.rg_sg_si_cluster;
			other.script = this.script;
			other.code_erreur = this.code_erreur;
			other.criticite = this.criticite;
			other.description = this.description;
			other.instructions = this.instructions;
			other.monitored_by = this.monitored_by;
			other.ref_service = this.ref_service;
			other.support = this.support;

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.ref = readString(dis);

					this.etat = readString(dis);

					this.ref_composant = readString(dis);

					this.rg_sg_si_cluster = readString(dis);

					this.script = readString(dis);

					this.code_erreur = readString(dis);

					this.criticite = readString(dis);

					this.description = readString(dis);

					this.instructions = readString(dis);

					this.monitored_by = readString(dis);

					this.ref_service = readString(dis);

					this.support = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.ref = readString(dis);

					this.etat = readString(dis);

					this.ref_composant = readString(dis);

					this.rg_sg_si_cluster = readString(dis);

					this.script = readString(dis);

					this.code_erreur = readString(dis);

					this.criticite = readString(dis);

					this.description = readString(dis);

					this.instructions = readString(dis);

					this.monitored_by = readString(dis);

					this.ref_service = readString(dis);

					this.support = readString(dis);

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

				writeString(this.ref, dos);

				// String

				writeString(this.etat, dos);

				// String

				writeString(this.ref_composant, dos);

				// String

				writeString(this.rg_sg_si_cluster, dos);

				// String

				writeString(this.script, dos);

				// String

				writeString(this.code_erreur, dos);

				// String

				writeString(this.criticite, dos);

				// String

				writeString(this.description, dos);

				// String

				writeString(this.instructions, dos);

				// String

				writeString(this.monitored_by, dos);

				// String

				writeString(this.ref_service, dos);

				// String

				writeString(this.support, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.ref, dos);

				// String

				writeString(this.etat, dos);

				// String

				writeString(this.ref_composant, dos);

				// String

				writeString(this.rg_sg_si_cluster, dos);

				// String

				writeString(this.script, dos);

				// String

				writeString(this.code_erreur, dos);

				// String

				writeString(this.criticite, dos);

				// String

				writeString(this.description, dos);

				// String

				writeString(this.instructions, dos);

				// String

				writeString(this.monitored_by, dos);

				// String

				writeString(this.ref_service, dos);

				// String

				writeString(this.support, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",ref=" + ref);
			sb.append(",etat=" + etat);
			sb.append(",ref_composant=" + ref_composant);
			sb.append(",rg_sg_si_cluster=" + rg_sg_si_cluster);
			sb.append(",script=" + script);
			sb.append(",code_erreur=" + code_erreur);
			sb.append(",criticite=" + criticite);
			sb.append(",description=" + description);
			sb.append(",instructions=" + instructions);
			sb.append(",monitored_by=" + monitored_by);
			sb.append(",ref_service=" + ref_service);
			sb.append(",support=" + support);
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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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

		public String Support;

		public String getSupport() {
			return this.Support;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

					this.Support = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

					this.Support = readString(dis);

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

				// String

				writeString(this.Support, dos);

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

				// String

				writeString(this.Support, dos);

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
			sb.append(",Support=" + Support);
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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

	public static class inserturlsStruct implements routines.system.IPersistableRow<inserturlsStruct> {
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		public String ref;

		public String getRef() {
			return this.ref;
		}

		public String etat;

		public String getEtat() {
			return this.etat;
		}

		public String ref_composant;

		public String getRef_composant() {
			return this.ref_composant;
		}

		public String rg_sg_si_cluster;

		public String getRg_sg_si_cluster() {
			return this.rg_sg_si_cluster;
		}

		public String url;

		public String getUrl() {
			return this.url;
		}

		public String perform_action;

		public String getPerform_action() {
			return this.perform_action;
		}

		public String criticite;

		public String getCriticite() {
			return this.criticite;
		}

		public String message_alarme;

		public String getMessage_alarme() {
			return this.message_alarme;
		}

		public String instructions;

		public String getInstructions() {
			return this.instructions;
		}

		public String intervalle_de_polling;

		public String getIntervalle_de_polling() {
			return this.intervalle_de_polling;
		}

		public String ref_service;

		public String getRef_service() {
			return this.ref_service;
		}

		public String objet;

		public String getObjet() {
			return this.objet;
		}

		public String monitored_by;

		public String getMonitored_by() {
			return this.monitored_by;
		}

		public String nom_template;

		public String getNom_template() {
			return this.nom_template;
		}

		public String support;

		public String getSupport() {
			return this.support;
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
			final inserturlsStruct other = (inserturlsStruct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(inserturlsStruct other) {

			other.id = this.id;
			other.ref = this.ref;
			other.etat = this.etat;
			other.ref_composant = this.ref_composant;
			other.rg_sg_si_cluster = this.rg_sg_si_cluster;
			other.url = this.url;
			other.perform_action = this.perform_action;
			other.criticite = this.criticite;
			other.message_alarme = this.message_alarme;
			other.instructions = this.instructions;
			other.intervalle_de_polling = this.intervalle_de_polling;
			other.ref_service = this.ref_service;
			other.objet = this.objet;
			other.monitored_by = this.monitored_by;
			other.nom_template = this.nom_template;
			other.support = this.support;

		}

		public void copyKeysDataTo(inserturlsStruct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.ref = readString(dis);

					this.etat = readString(dis);

					this.ref_composant = readString(dis);

					this.rg_sg_si_cluster = readString(dis);

					this.url = readString(dis);

					this.perform_action = readString(dis);

					this.criticite = readString(dis);

					this.message_alarme = readString(dis);

					this.instructions = readString(dis);

					this.intervalle_de_polling = readString(dis);

					this.ref_service = readString(dis);

					this.objet = readString(dis);

					this.monitored_by = readString(dis);

					this.nom_template = readString(dis);

					this.support = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.ref = readString(dis);

					this.etat = readString(dis);

					this.ref_composant = readString(dis);

					this.rg_sg_si_cluster = readString(dis);

					this.url = readString(dis);

					this.perform_action = readString(dis);

					this.criticite = readString(dis);

					this.message_alarme = readString(dis);

					this.instructions = readString(dis);

					this.intervalle_de_polling = readString(dis);

					this.ref_service = readString(dis);

					this.objet = readString(dis);

					this.monitored_by = readString(dis);

					this.nom_template = readString(dis);

					this.support = readString(dis);

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

				writeString(this.ref, dos);

				// String

				writeString(this.etat, dos);

				// String

				writeString(this.ref_composant, dos);

				// String

				writeString(this.rg_sg_si_cluster, dos);

				// String

				writeString(this.url, dos);

				// String

				writeString(this.perform_action, dos);

				// String

				writeString(this.criticite, dos);

				// String

				writeString(this.message_alarme, dos);

				// String

				writeString(this.instructions, dos);

				// String

				writeString(this.intervalle_de_polling, dos);

				// String

				writeString(this.ref_service, dos);

				// String

				writeString(this.objet, dos);

				// String

				writeString(this.monitored_by, dos);

				// String

				writeString(this.nom_template, dos);

				// String

				writeString(this.support, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.ref, dos);

				// String

				writeString(this.etat, dos);

				// String

				writeString(this.ref_composant, dos);

				// String

				writeString(this.rg_sg_si_cluster, dos);

				// String

				writeString(this.url, dos);

				// String

				writeString(this.perform_action, dos);

				// String

				writeString(this.criticite, dos);

				// String

				writeString(this.message_alarme, dos);

				// String

				writeString(this.instructions, dos);

				// String

				writeString(this.intervalle_de_polling, dos);

				// String

				writeString(this.ref_service, dos);

				// String

				writeString(this.objet, dos);

				// String

				writeString(this.monitored_by, dos);

				// String

				writeString(this.nom_template, dos);

				// String

				writeString(this.support, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",ref=" + ref);
			sb.append(",etat=" + etat);
			sb.append(",ref_composant=" + ref_composant);
			sb.append(",rg_sg_si_cluster=" + rg_sg_si_cluster);
			sb.append(",url=" + url);
			sb.append(",perform_action=" + perform_action);
			sb.append(",criticite=" + criticite);
			sb.append(",message_alarme=" + message_alarme);
			sb.append(",instructions=" + instructions);
			sb.append(",intervalle_de_polling=" + intervalle_de_polling);
			sb.append(",ref_service=" + ref_service);
			sb.append(",objet=" + objet);
			sb.append(",monitored_by=" + monitored_by);
			sb.append(",nom_template=" + nom_template);
			sb.append(",support=" + support);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(inserturlsStruct other) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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

		public String Support;

		public String getSupport() {
			return this.Support;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

					this.Support = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

					this.Support = readString(dis);

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

				// String

				writeString(this.Support, dos);

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

				// String

				writeString(this.Support, dos);

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
			sb.append(",Support=" + Support);
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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.filepath2 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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
				inserturlsStruct inserturls = new inserturlsStruct();

				/**
				 * [tFileList_2 begin ] start
				 */

				int NB_ITERATE_tFileInputExcel_6 = 0; // for statistics

				int NB_ITERATE_tIterateToFlow_2_ITFO = 0; // for statistics

				int NB_ITERATE_tFileInputExcel_48 = 0; // for statistics

				int NB_ITERATE_tFileInputExcel_24 = 0; // for statistics

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
						runStat.updateStatOnConnection("row4", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row22", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("insertlogfiles", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row13", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate5", 1, "exec" + NB_ITERATE_tFileInputExcel_6);
						// Thread.sleep(1000);
					}

					/**
					 * [tDBOutput_4 begin ] start
					 */

					ok_Hash.put("tDBOutput_4", false);
					start_Hash.put("tDBOutput_4", System.currentTimeMillis());

					currentComponent = "tDBOutput_4";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "insertlogfiles");
					}

					int tos_count_tDBOutput_4 = 0;

					int nb_line_tDBOutput_4 = 0;
					int nb_line_update_tDBOutput_4 = 0;
					int nb_line_inserted_tDBOutput_4 = 0;
					int nb_line_deleted_tDBOutput_4 = 0;
					int nb_line_rejected_tDBOutput_4 = 0;

					int deletedCount_tDBOutput_4 = 0;
					int updatedCount_tDBOutput_4 = 0;
					int insertedCount_tDBOutput_4 = 0;
					int rowsToCommitCount_tDBOutput_4 = 0;
					int rejectedCount_tDBOutput_4 = 0;

					String tableName_tDBOutput_4 = "log_files";
					boolean whetherReject_tDBOutput_4 = false;

					java.util.Calendar calendar_tDBOutput_4 = java.util.Calendar.getInstance();
					calendar_tDBOutput_4.set(1, 0, 1, 0, 0, 0);
					long year1_tDBOutput_4 = calendar_tDBOutput_4.getTime().getTime();
					calendar_tDBOutput_4.set(10000, 0, 1, 0, 0, 0);
					long year10000_tDBOutput_4 = calendar_tDBOutput_4.getTime().getTime();
					long date_tDBOutput_4;

					java.sql.Connection conn_tDBOutput_4 = null;

					String properties_tDBOutput_4 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
					if (properties_tDBOutput_4 == null || properties_tDBOutput_4.trim().length() == 0) {
						properties_tDBOutput_4 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
					} else {
						if (!properties_tDBOutput_4.contains("rewriteBatchedStatements=")) {
							properties_tDBOutput_4 += "&rewriteBatchedStatements=true";
						}

						if (!properties_tDBOutput_4.contains("allowLoadLocalInfile=")) {
							properties_tDBOutput_4 += "&allowLoadLocalInfile=true";
						}
					}

					String url_tDBOutput_4 = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "ooredoo" + "?"
							+ properties_tDBOutput_4;

					String driverClass_tDBOutput_4 = "com.mysql.cj.jdbc.Driver";

					String dbUser_tDBOutput_4 = "root";

					final String decryptedPassword_tDBOutput_4 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:RRstLrJT3zuGXgMiupKV8mY8uhOIGxjyUb7yjw==");

					String dbPwd_tDBOutput_4 = decryptedPassword_tDBOutput_4;
					java.lang.Class.forName(driverClass_tDBOutput_4);

					conn_tDBOutput_4 = java.sql.DriverManager.getConnection(url_tDBOutput_4, dbUser_tDBOutput_4,
							dbPwd_tDBOutput_4);

					resourceMap.put("conn_tDBOutput_4", conn_tDBOutput_4);
					conn_tDBOutput_4.setAutoCommit(false);
					int commitEvery_tDBOutput_4 = 10000;
					int commitCounter_tDBOutput_4 = 0;

					int count_tDBOutput_4 = 0;

					String insert_tDBOutput_4 = "INSERT IGNORE INTO `" + "log_files"
							+ "` (`id`,`ref`,`etat`,`ref_composant`,`rg_sg_si_cluster`,`logfile`,`localisation`,`description`,`format_logfile`,`separateur`,`intervalle_de_polling`,`monitored_by`,`fourni_en_annexe`,`ref_service`,`nom_template`,`log_conditions`,`support`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

					java.sql.PreparedStatement pstmt_tDBOutput_4 = conn_tDBOutput_4
							.prepareStatement(insert_tDBOutput_4);
					resourceMap.put("pstmt_tDBOutput_4", pstmt_tDBOutput_4);

					/**
					 * [tDBOutput_4 begin ] stop
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
							.decryptPassword("enc:routine.encryption.key.v1:SL/JJcLNDJc5JM2XxLcJtW8fv7MaZiE2/hyEOA==");
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
										boolean startAdding = false;
										StringBuilder supportBuilder = new StringBuilder();

										for (String part : parts) {
											if (startAdding) {
												supportBuilder.append(part).append("_");
											}
											if (part.equals("Support")) {
												startAdding = true;
											}
										}

										String supportPart = supportBuilder.substring(0, supportBuilder.length() - 1);

										String support = supportPart;

										String equipe;

										if ("CLOUD_et_APP_IT.xlsx".equals(support)) {
											equipe = "cloud";
										} else if ("ASE_VAS.xlsx".equals(support)) {
											equipe = "ASEVAS";
										} else if ("BILLING.xlsx".equals(support)) {
											equipe = "BILLING";
										} else if ("ASE_IN.xlsx".equals(support)) {
											equipe = "ASEIN";
										} else if ("Backup.xlsx".equals(support)) {
											equipe = "Backup";
										} else if ("ASE_GPRS.xlsx".equals(support)) {
											equipe = "ASEGPRS";
										} else {
											// Valeur par défaut si aucune des conditions n'est satisfaite
											equipe = "Autre";
										}

// Récupérer la valeur de "N__Ref" et la convertir en String
										String rowRef = row13.N__Ref;

// Générer l'ID "EDB_POSANET_X"
										String id = "EBD_" + fileWord + "_LogFiles_" + rowRef;
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
										row22.Support = equipe;
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
											insertlogfiles_tmp.ref = row22.Ref;
											insertlogfiles_tmp.etat = row22.Etat;
											insertlogfiles_tmp.ref_composant = row22.Ref__des_composants;
											insertlogfiles_tmp.rg_sg_si_cluster = row22.RG_SG_si_Cluster;
											insertlogfiles_tmp.logfile = row22.Logfile;
											insertlogfiles_tmp.localisation = row22.Localisation;
											insertlogfiles_tmp.description = row22.Description;
											insertlogfiles_tmp.format_logfile = row22.Format_logfile;
											insertlogfiles_tmp.separateur = row22.Separateur;
											insertlogfiles_tmp.intervalle_de_polling = row22.Intervalle_de_polling;
											insertlogfiles_tmp.monitored_by = row22.Monitored_By;
											insertlogfiles_tmp.fourni_en_annexe = row22.Fourni_en_annexe;
											insertlogfiles_tmp.ref_service = row22.Ref__Service;
											insertlogfiles_tmp.nom_template = row22.Nom_Template;
											insertlogfiles_tmp.log_conditions = row22.Log_Conditions;
											insertlogfiles_tmp.support = row22.Support;
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
											 * [tDBOutput_4 main ] start
											 */

											currentComponent = "tDBOutput_4";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "insertlogfiles"

												);
											}

											whetherReject_tDBOutput_4 = false;
											if (insertlogfiles.id == null) {
												pstmt_tDBOutput_4.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_4.setString(1, insertlogfiles.id);
											}

											if (insertlogfiles.ref == null) {
												pstmt_tDBOutput_4.setNull(2, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_4.setString(2, insertlogfiles.ref);
											}

											if (insertlogfiles.etat == null) {
												pstmt_tDBOutput_4.setNull(3, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_4.setString(3, insertlogfiles.etat);
											}

											if (insertlogfiles.ref_composant == null) {
												pstmt_tDBOutput_4.setNull(4, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_4.setString(4, insertlogfiles.ref_composant);
											}

											if (insertlogfiles.rg_sg_si_cluster == null) {
												pstmt_tDBOutput_4.setNull(5, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_4.setString(5, insertlogfiles.rg_sg_si_cluster);
											}

											if (insertlogfiles.logfile == null) {
												pstmt_tDBOutput_4.setNull(6, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_4.setString(6, insertlogfiles.logfile);
											}

											if (insertlogfiles.localisation == null) {
												pstmt_tDBOutput_4.setNull(7, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_4.setString(7, insertlogfiles.localisation);
											}

											if (insertlogfiles.description == null) {
												pstmt_tDBOutput_4.setNull(8, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_4.setString(8, insertlogfiles.description);
											}

											if (insertlogfiles.format_logfile == null) {
												pstmt_tDBOutput_4.setNull(9, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_4.setString(9, insertlogfiles.format_logfile);
											}

											if (insertlogfiles.separateur == null) {
												pstmt_tDBOutput_4.setNull(10, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_4.setString(10, insertlogfiles.separateur);
											}

											if (insertlogfiles.intervalle_de_polling == null) {
												pstmt_tDBOutput_4.setNull(11, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_4.setString(11, insertlogfiles.intervalle_de_polling);
											}

											if (insertlogfiles.monitored_by == null) {
												pstmt_tDBOutput_4.setNull(12, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_4.setString(12, insertlogfiles.monitored_by);
											}

											if (insertlogfiles.fourni_en_annexe == null) {
												pstmt_tDBOutput_4.setNull(13, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_4.setString(13, insertlogfiles.fourni_en_annexe);
											}

											if (insertlogfiles.ref_service == null) {
												pstmt_tDBOutput_4.setNull(14, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_4.setString(14, insertlogfiles.ref_service);
											}

											if (insertlogfiles.nom_template == null) {
												pstmt_tDBOutput_4.setNull(15, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_4.setString(15, insertlogfiles.nom_template);
											}

											if (insertlogfiles.log_conditions == null) {
												pstmt_tDBOutput_4.setNull(16, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_4.setString(16, insertlogfiles.log_conditions);
											}

											if (insertlogfiles.support == null) {
												pstmt_tDBOutput_4.setNull(17, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_4.setString(17, insertlogfiles.support);
											}

											try {
												nb_line_tDBOutput_4++;
												int processedCount_tDBOutput_4 = pstmt_tDBOutput_4.executeUpdate();
												insertedCount_tDBOutput_4 += processedCount_tDBOutput_4;
												rowsToCommitCount_tDBOutput_4 += processedCount_tDBOutput_4;
											} catch (java.lang.Exception e) {
												globalMap.put("tDBOutput_4_ERROR_MESSAGE", e.getMessage());
												whetherReject_tDBOutput_4 = true;
												System.err.print(e.getMessage());
											}
											commitCounter_tDBOutput_4++;

											if (commitEvery_tDBOutput_4 <= commitCounter_tDBOutput_4) {

												if (rowsToCommitCount_tDBOutput_4 != 0) {
												}
												conn_tDBOutput_4.commit();
												if (rowsToCommitCount_tDBOutput_4 != 0) {
													rowsToCommitCount_tDBOutput_4 = 0;
												}
												commitCounter_tDBOutput_4 = 0;

											}

											tos_count_tDBOutput_4++;

											/**
											 * [tDBOutput_4 main ] stop
											 */

											/**
											 * [tDBOutput_4 process_data_begin ] start
											 */

											currentComponent = "tDBOutput_4";

											/**
											 * [tDBOutput_4 process_data_begin ] stop
											 */

											/**
											 * [tDBOutput_4 process_data_end ] start
											 */

											currentComponent = "tDBOutput_4";

											/**
											 * [tDBOutput_4 process_data_end ] stop
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
					 * [tDBOutput_4 end ] start
					 */

					currentComponent = "tDBOutput_4";

					if (pstmt_tDBOutput_4 != null) {

						pstmt_tDBOutput_4.close();
						resourceMap.remove("pstmt_tDBOutput_4");

					}
					resourceMap.put("statementClosed_tDBOutput_4", true);
					if (commitCounter_tDBOutput_4 > 0 && rowsToCommitCount_tDBOutput_4 != 0) {

					}
					conn_tDBOutput_4.commit();
					if (commitCounter_tDBOutput_4 > 0 && rowsToCommitCount_tDBOutput_4 != 0) {

						rowsToCommitCount_tDBOutput_4 = 0;
					}
					commitCounter_tDBOutput_4 = 0;

					conn_tDBOutput_4.close();

					resourceMap.put("finish_tDBOutput_4", true);

					nb_line_deleted_tDBOutput_4 = nb_line_deleted_tDBOutput_4 + deletedCount_tDBOutput_4;
					nb_line_update_tDBOutput_4 = nb_line_update_tDBOutput_4 + updatedCount_tDBOutput_4;
					nb_line_inserted_tDBOutput_4 = nb_line_inserted_tDBOutput_4 + insertedCount_tDBOutput_4;
					nb_line_rejected_tDBOutput_4 = nb_line_rejected_tDBOutput_4 + rejectedCount_tDBOutput_4;

					globalMap.put("tDBOutput_4_NB_LINE", nb_line_tDBOutput_4);
					globalMap.put("tDBOutput_4_NB_LINE_UPDATED", nb_line_update_tDBOutput_4);
					globalMap.put("tDBOutput_4_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_4);
					globalMap.put("tDBOutput_4_NB_LINE_DELETED", nb_line_deleted_tDBOutput_4);
					globalMap.put("tDBOutput_4_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_4);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "insertlogfiles");
					}

					ok_Hash.put("tDBOutput_4", true);
					end_Hash.put("tDBOutput_4", System.currentTimeMillis());

					/**
					 * [tDBOutput_4 end ] stop
					 */

					if (execStat) {
						runStat.updateStatOnConnection("iterate5", 2, "exec" + NB_ITERATE_tFileInputExcel_6);
					}

					NB_ITERATE_tFileInputExcel_24++;

					if (execStat) {
						runStat.updateStatOnConnection("row23", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row14", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row5", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("insertscripts", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate6", 1, "exec" + NB_ITERATE_tFileInputExcel_24);
						// Thread.sleep(1000);
					}

					/**
					 * [tDBOutput_5 begin ] start
					 */

					ok_Hash.put("tDBOutput_5", false);
					start_Hash.put("tDBOutput_5", System.currentTimeMillis());

					currentComponent = "tDBOutput_5";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "insertscripts");
					}

					int tos_count_tDBOutput_5 = 0;

					int nb_line_tDBOutput_5 = 0;
					int nb_line_update_tDBOutput_5 = 0;
					int nb_line_inserted_tDBOutput_5 = 0;
					int nb_line_deleted_tDBOutput_5 = 0;
					int nb_line_rejected_tDBOutput_5 = 0;

					int deletedCount_tDBOutput_5 = 0;
					int updatedCount_tDBOutput_5 = 0;
					int insertedCount_tDBOutput_5 = 0;
					int rowsToCommitCount_tDBOutput_5 = 0;
					int rejectedCount_tDBOutput_5 = 0;

					String tableName_tDBOutput_5 = "scripts";
					boolean whetherReject_tDBOutput_5 = false;

					java.util.Calendar calendar_tDBOutput_5 = java.util.Calendar.getInstance();
					calendar_tDBOutput_5.set(1, 0, 1, 0, 0, 0);
					long year1_tDBOutput_5 = calendar_tDBOutput_5.getTime().getTime();
					calendar_tDBOutput_5.set(10000, 0, 1, 0, 0, 0);
					long year10000_tDBOutput_5 = calendar_tDBOutput_5.getTime().getTime();
					long date_tDBOutput_5;

					java.sql.Connection conn_tDBOutput_5 = null;

					String properties_tDBOutput_5 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
					if (properties_tDBOutput_5 == null || properties_tDBOutput_5.trim().length() == 0) {
						properties_tDBOutput_5 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
					} else {
						if (!properties_tDBOutput_5.contains("rewriteBatchedStatements=")) {
							properties_tDBOutput_5 += "&rewriteBatchedStatements=true";
						}

						if (!properties_tDBOutput_5.contains("allowLoadLocalInfile=")) {
							properties_tDBOutput_5 += "&allowLoadLocalInfile=true";
						}
					}

					String url_tDBOutput_5 = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "ooredoo" + "?"
							+ properties_tDBOutput_5;

					String driverClass_tDBOutput_5 = "com.mysql.cj.jdbc.Driver";

					String dbUser_tDBOutput_5 = "root";

					final String decryptedPassword_tDBOutput_5 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:FZvyve+Vi5NBCv66jFtfXWTMgiPCpbom/copuQ==");

					String dbPwd_tDBOutput_5 = decryptedPassword_tDBOutput_5;
					java.lang.Class.forName(driverClass_tDBOutput_5);

					conn_tDBOutput_5 = java.sql.DriverManager.getConnection(url_tDBOutput_5, dbUser_tDBOutput_5,
							dbPwd_tDBOutput_5);

					resourceMap.put("conn_tDBOutput_5", conn_tDBOutput_5);
					conn_tDBOutput_5.setAutoCommit(false);
					int commitEvery_tDBOutput_5 = 10000;
					int commitCounter_tDBOutput_5 = 0;

					int count_tDBOutput_5 = 0;

					String insert_tDBOutput_5 = "INSERT IGNORE INTO `" + "scripts"
							+ "` (`id`,`ref`,`etat`,`ref_composant`,`rg_sg_si_cluster`,`script`,`code_erreur`,`criticite`,`description`,`instructions`,`monitored_by`,`ref_service`,`support`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

					java.sql.PreparedStatement pstmt_tDBOutput_5 = conn_tDBOutput_5
							.prepareStatement(insert_tDBOutput_5);
					resourceMap.put("pstmt_tDBOutput_5", pstmt_tDBOutput_5);

					/**
					 * [tDBOutput_5 begin ] stop
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
							.decryptPassword("enc:routine.encryption.key.v1:/5Y/dV2TVCsiEmv1MLlJYHqeNgawZryAMzmmPg==");
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
										boolean startAdding = false;
										StringBuilder supportBuilder = new StringBuilder();

										for (String part : parts) {
											if (startAdding) {
												supportBuilder.append(part).append("_");
											}
											if (part.equals("Support")) {
												startAdding = true;
											}
										}

										String supportPart = supportBuilder.substring(0, supportBuilder.length() - 1);

										String support = supportPart;

										String equipe;

										if ("CLOUD_et_APP_IT.xlsx".equals(support)) {
											equipe = "cloud";
										} else if ("ASE_VAS.xlsx".equals(support)) {
											equipe = "ASEVAS";
										} else if ("BILLING.xlsx".equals(support)) {
											equipe = "BILLING";
										} else if ("ASE_IN.xlsx".equals(support)) {
											equipe = "ASEIN";
										} else if ("Backup.xlsx".equals(support)) {
											equipe = "Backup";
										} else if ("ASE_GPRS.xlsx".equals(support)) {
											equipe = "ASEGPRS";
										} else {
											// Valeur par défaut si aucune des conditions n'est satisfaite
											equipe = "Autre";
										}

// Récupérer la valeur de "N__Ref" et la convertir en String
										String rowRef = row14.N__Ref;

// Générer l'ID "EDB_POSANET_X"
										String id = "EBD_" + fileWord + "_Scripts_" + rowRef;
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
										row23.Support = equipe;
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
											insertscripts_tmp.ref = row23.Ref;
											insertscripts_tmp.etat = row23.Etat;
											insertscripts_tmp.ref_composant = row23.Ref__des_composants;
											insertscripts_tmp.rg_sg_si_cluster = row23.RG_SG_si_Cluster;
											insertscripts_tmp.script = row23.Script;
											insertscripts_tmp.code_erreur = row23.Code_erreur;
											insertscripts_tmp.criticite = row23.Criticite;
											insertscripts_tmp.description = row23.Description;
											insertscripts_tmp.instructions = row23.Instructions;
											insertscripts_tmp.monitored_by = row23.Monitored_By;
											insertscripts_tmp.ref_service = row23.Ref__Service;
											insertscripts_tmp.support = row23.Support;
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
											 * [tDBOutput_5 main ] start
											 */

											currentComponent = "tDBOutput_5";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "insertscripts"

												);
											}

											whetherReject_tDBOutput_5 = false;
											if (insertscripts.id == null) {
												pstmt_tDBOutput_5.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_5.setString(1, insertscripts.id);
											}

											if (insertscripts.ref == null) {
												pstmt_tDBOutput_5.setNull(2, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_5.setString(2, insertscripts.ref);
											}

											if (insertscripts.etat == null) {
												pstmt_tDBOutput_5.setNull(3, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_5.setString(3, insertscripts.etat);
											}

											if (insertscripts.ref_composant == null) {
												pstmt_tDBOutput_5.setNull(4, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_5.setString(4, insertscripts.ref_composant);
											}

											if (insertscripts.rg_sg_si_cluster == null) {
												pstmt_tDBOutput_5.setNull(5, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_5.setString(5, insertscripts.rg_sg_si_cluster);
											}

											if (insertscripts.script == null) {
												pstmt_tDBOutput_5.setNull(6, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_5.setString(6, insertscripts.script);
											}

											if (insertscripts.code_erreur == null) {
												pstmt_tDBOutput_5.setNull(7, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_5.setString(7, insertscripts.code_erreur);
											}

											if (insertscripts.criticite == null) {
												pstmt_tDBOutput_5.setNull(8, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_5.setString(8, insertscripts.criticite);
											}

											if (insertscripts.description == null) {
												pstmt_tDBOutput_5.setNull(9, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_5.setString(9, insertscripts.description);
											}

											if (insertscripts.instructions == null) {
												pstmt_tDBOutput_5.setNull(10, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_5.setString(10, insertscripts.instructions);
											}

											if (insertscripts.monitored_by == null) {
												pstmt_tDBOutput_5.setNull(11, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_5.setString(11, insertscripts.monitored_by);
											}

											if (insertscripts.ref_service == null) {
												pstmt_tDBOutput_5.setNull(12, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_5.setString(12, insertscripts.ref_service);
											}

											if (insertscripts.support == null) {
												pstmt_tDBOutput_5.setNull(13, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_5.setString(13, insertscripts.support);
											}

											try {
												nb_line_tDBOutput_5++;
												int processedCount_tDBOutput_5 = pstmt_tDBOutput_5.executeUpdate();
												insertedCount_tDBOutput_5 += processedCount_tDBOutput_5;
												rowsToCommitCount_tDBOutput_5 += processedCount_tDBOutput_5;
											} catch (java.lang.Exception e) {
												globalMap.put("tDBOutput_5_ERROR_MESSAGE", e.getMessage());
												whetherReject_tDBOutput_5 = true;
												System.err.print(e.getMessage());
											}
											commitCounter_tDBOutput_5++;

											if (commitEvery_tDBOutput_5 <= commitCounter_tDBOutput_5) {

												if (rowsToCommitCount_tDBOutput_5 != 0) {
												}
												conn_tDBOutput_5.commit();
												if (rowsToCommitCount_tDBOutput_5 != 0) {
													rowsToCommitCount_tDBOutput_5 = 0;
												}
												commitCounter_tDBOutput_5 = 0;

											}

											tos_count_tDBOutput_5++;

											/**
											 * [tDBOutput_5 main ] stop
											 */

											/**
											 * [tDBOutput_5 process_data_begin ] start
											 */

											currentComponent = "tDBOutput_5";

											/**
											 * [tDBOutput_5 process_data_begin ] stop
											 */

											/**
											 * [tDBOutput_5 process_data_end ] start
											 */

											currentComponent = "tDBOutput_5";

											/**
											 * [tDBOutput_5 process_data_end ] stop
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
					 * [tDBOutput_5 end ] start
					 */

					currentComponent = "tDBOutput_5";

					if (pstmt_tDBOutput_5 != null) {

						pstmt_tDBOutput_5.close();
						resourceMap.remove("pstmt_tDBOutput_5");

					}
					resourceMap.put("statementClosed_tDBOutput_5", true);
					if (commitCounter_tDBOutput_5 > 0 && rowsToCommitCount_tDBOutput_5 != 0) {

					}
					conn_tDBOutput_5.commit();
					if (commitCounter_tDBOutput_5 > 0 && rowsToCommitCount_tDBOutput_5 != 0) {

						rowsToCommitCount_tDBOutput_5 = 0;
					}
					commitCounter_tDBOutput_5 = 0;

					conn_tDBOutput_5.close();

					resourceMap.put("finish_tDBOutput_5", true);

					nb_line_deleted_tDBOutput_5 = nb_line_deleted_tDBOutput_5 + deletedCount_tDBOutput_5;
					nb_line_update_tDBOutput_5 = nb_line_update_tDBOutput_5 + updatedCount_tDBOutput_5;
					nb_line_inserted_tDBOutput_5 = nb_line_inserted_tDBOutput_5 + insertedCount_tDBOutput_5;
					nb_line_rejected_tDBOutput_5 = nb_line_rejected_tDBOutput_5 + rejectedCount_tDBOutput_5;

					globalMap.put("tDBOutput_5_NB_LINE", nb_line_tDBOutput_5);
					globalMap.put("tDBOutput_5_NB_LINE_UPDATED", nb_line_update_tDBOutput_5);
					globalMap.put("tDBOutput_5_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_5);
					globalMap.put("tDBOutput_5_NB_LINE_DELETED", nb_line_deleted_tDBOutput_5);
					globalMap.put("tDBOutput_5_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_5);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "insertscripts");
					}

					ok_Hash.put("tDBOutput_5", true);
					end_Hash.put("tDBOutput_5", System.currentTimeMillis());

					/**
					 * [tDBOutput_5 end ] stop
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
						runStat.updateStatOnConnection("inserturls", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row15", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate7", 1, "exec" + NB_ITERATE_tFileInputExcel_48);
						// Thread.sleep(1000);
					}

					/**
					 * [tDBOutput_6 begin ] start
					 */

					ok_Hash.put("tDBOutput_6", false);
					start_Hash.put("tDBOutput_6", System.currentTimeMillis());

					currentComponent = "tDBOutput_6";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "inserturls");
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

					String tableName_tDBOutput_6 = "url";
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

					String url_tDBOutput_6 = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "ooredoo" + "?"
							+ properties_tDBOutput_6;

					String driverClass_tDBOutput_6 = "com.mysql.cj.jdbc.Driver";

					String dbUser_tDBOutput_6 = "root";

					final String decryptedPassword_tDBOutput_6 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:xZZUyUN8B2RxL7uKkVz1vpmS4RGSwaECEL10bA==");

					String dbPwd_tDBOutput_6 = decryptedPassword_tDBOutput_6;
					java.lang.Class.forName(driverClass_tDBOutput_6);

					conn_tDBOutput_6 = java.sql.DriverManager.getConnection(url_tDBOutput_6, dbUser_tDBOutput_6,
							dbPwd_tDBOutput_6);

					resourceMap.put("conn_tDBOutput_6", conn_tDBOutput_6);
					conn_tDBOutput_6.setAutoCommit(false);
					int commitEvery_tDBOutput_6 = 10000;
					int commitCounter_tDBOutput_6 = 0;

					int count_tDBOutput_6 = 0;

					String insert_tDBOutput_6 = "INSERT IGNORE INTO `" + "url"
							+ "` (`id`,`ref`,`etat`,`ref_composant`,`rg_sg_si_cluster`,`url`,`perform_action`,`criticite`,`message_alarme`,`instructions`,`intervalle_de_polling`,`ref_service`,`objet`,`monitored_by`,`nom_template`,`support`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

					java.sql.PreparedStatement pstmt_tDBOutput_6 = conn_tDBOutput_6
							.prepareStatement(insert_tDBOutput_6);
					resourceMap.put("pstmt_tDBOutput_6", pstmt_tDBOutput_6);

					/**
					 * [tDBOutput_6 begin ] stop
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
					inserturlsStruct inserturls_tmp = new inserturlsStruct();
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
							.decryptPassword("enc:routine.encryption.key.v1:qsRvD2vkrFKNNHznKvR8TbWU7g1hB4h2nqBpNg==");
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
										boolean startAdding = false;
										StringBuilder supportBuilder = new StringBuilder();

										for (String part : parts) {
											if (startAdding) {
												supportBuilder.append(part).append("_");
											}
											if (part.equals("Support")) {
												startAdding = true;
											}
										}

										String supportPart = supportBuilder.substring(0, supportBuilder.length() - 1);

										String support = supportPart;

										String equipe;

										if ("CLOUD_et_APP_IT.xlsx".equals(support)) {
											equipe = "cloud";
										} else if ("ASE_VAS.xlsx".equals(support)) {
											equipe = "ASEVAS";
										} else if ("BILLING.xlsx".equals(support)) {
											equipe = "BILLING";
										} else if ("ASE_IN.xlsx".equals(support)) {
											equipe = "ASEIN";
										} else if ("Backup.xlsx".equals(support)) {
											equipe = "Backup";
										} else if ("ASE_GPRS.xlsx".equals(support)) {
											equipe = "ASEGPRS";
										} else {
											// Valeur par défaut si aucune des conditions n'est satisfaite
											equipe = "Autre";
										}

// Récupérer la valeur de "N__Ref" et la convertir en String
										String rowRef = row15.N__Ref;

// Générer l'ID "EDB_POSANET_X"
										String id = "EBD_" + fileWord + "_Url_" + rowRef;
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
										row24.Support = equipe;

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

											inserturls = null;

// # Output table : 'inserturls'
											inserturls_tmp.id = row24.N__Ref;
											inserturls_tmp.ref = row24.Ref;
											inserturls_tmp.etat = row24.Etat;
											inserturls_tmp.ref_composant = row24.Ref__des_composants;
											inserturls_tmp.rg_sg_si_cluster = row24.RG_SG_si_Cluster;
											inserturls_tmp.url = row24.URL;
											inserturls_tmp.perform_action = row24.Perform_action;
											inserturls_tmp.criticite = row24.Criticite;
											inserturls_tmp.message_alarme = row24.Message_d_alarme;
											inserturls_tmp.instructions = row24.Instructions;
											inserturls_tmp.intervalle_de_polling = row24.Intervalle_de_polling;
											inserturls_tmp.ref_service = row24.Ref__Service;
											inserturls_tmp.objet = row24.Objet;
											inserturls_tmp.monitored_by = row24.Monitored_By;
											inserturls_tmp.nom_template = row24.Nom_Template;
											inserturls_tmp.support = row24.Support;
											inserturls = inserturls_tmp;
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
// Start of branch "inserturls"
										if (inserturls != null) {

											/**
											 * [tDBOutput_6 main ] start
											 */

											currentComponent = "tDBOutput_6";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "inserturls"

												);
											}

											whetherReject_tDBOutput_6 = false;
											if (inserturls.id == null) {
												pstmt_tDBOutput_6.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(1, inserturls.id);
											}

											if (inserturls.ref == null) {
												pstmt_tDBOutput_6.setNull(2, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(2, inserturls.ref);
											}

											if (inserturls.etat == null) {
												pstmt_tDBOutput_6.setNull(3, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(3, inserturls.etat);
											}

											if (inserturls.ref_composant == null) {
												pstmt_tDBOutput_6.setNull(4, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(4, inserturls.ref_composant);
											}

											if (inserturls.rg_sg_si_cluster == null) {
												pstmt_tDBOutput_6.setNull(5, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(5, inserturls.rg_sg_si_cluster);
											}

											if (inserturls.url == null) {
												pstmt_tDBOutput_6.setNull(6, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(6, inserturls.url);
											}

											if (inserturls.perform_action == null) {
												pstmt_tDBOutput_6.setNull(7, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(7, inserturls.perform_action);
											}

											if (inserturls.criticite == null) {
												pstmt_tDBOutput_6.setNull(8, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(8, inserturls.criticite);
											}

											if (inserturls.message_alarme == null) {
												pstmt_tDBOutput_6.setNull(9, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(9, inserturls.message_alarme);
											}

											if (inserturls.instructions == null) {
												pstmt_tDBOutput_6.setNull(10, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(10, inserturls.instructions);
											}

											if (inserturls.intervalle_de_polling == null) {
												pstmt_tDBOutput_6.setNull(11, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(11, inserturls.intervalle_de_polling);
											}

											if (inserturls.ref_service == null) {
												pstmt_tDBOutput_6.setNull(12, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(12, inserturls.ref_service);
											}

											if (inserturls.objet == null) {
												pstmt_tDBOutput_6.setNull(13, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(13, inserturls.objet);
											}

											if (inserturls.monitored_by == null) {
												pstmt_tDBOutput_6.setNull(14, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(14, inserturls.monitored_by);
											}

											if (inserturls.nom_template == null) {
												pstmt_tDBOutput_6.setNull(15, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(15, inserturls.nom_template);
											}

											if (inserturls.support == null) {
												pstmt_tDBOutput_6.setNull(16, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_6.setString(16, inserturls.support);
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

										} // End of branch "inserturls"

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
						runStat.updateStat(resourceMap, iterateId, 2, 0, "inserturls");
					}

					ok_Hash.put("tDBOutput_6", true);
					end_Hash.put("tDBOutput_6", System.currentTimeMillis());

					/**
					 * [tDBOutput_6 end ] stop
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
				 * [tDBOutput_4 finally ] start
				 */

				currentComponent = "tDBOutput_4";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_4") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_4 = null;
						if ((pstmtToClose_tDBOutput_4 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_4")) != null) {
							pstmtToClose_tDBOutput_4.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_4") == null) {
						java.sql.Connection ctn_tDBOutput_4 = null;
						if ((ctn_tDBOutput_4 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_4")) != null) {
							try {
								ctn_tDBOutput_4.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_4) {
								String errorMessage_tDBOutput_4 = "failed to close the connection in tDBOutput_4 :"
										+ sqlEx_tDBOutput_4.getMessage();
								System.err.println(errorMessage_tDBOutput_4);
							}
						}
					}
				}

				/**
				 * [tDBOutput_4 finally ] stop
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
				 * [tDBOutput_5 finally ] start
				 */

				currentComponent = "tDBOutput_5";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_5") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_5 = null;
						if ((pstmtToClose_tDBOutput_5 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_5")) != null) {
							pstmtToClose_tDBOutput_5.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_5") == null) {
						java.sql.Connection ctn_tDBOutput_5 = null;
						if ((ctn_tDBOutput_5 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_5")) != null) {
							try {
								ctn_tDBOutput_5.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_5) {
								String errorMessage_tDBOutput_5 = "failed to close the connection in tDBOutput_5 :"
										+ sqlEx_tDBOutput_5.getMessage();
								System.err.println(errorMessage_tDBOutput_5);
							}
						}
					}
				}

				/**
				 * [tDBOutput_5 finally ] stop
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

	public static class inserttrapsStruct implements routines.system.IPersistableRow<inserttrapsStruct> {
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		public String ref;

		public String getRef() {
			return this.ref;
		}

		public String etat;

		public String getEtat() {
			return this.etat;
		}

		public String ref_composant;

		public String getRef_composant() {
			return this.ref_composant;
		}

		public String signification;

		public String getSignification() {
			return this.signification;
		}

		public String version_snmp;

		public String getVersion_snmp() {
			return this.version_snmp;
		}

		public String oid;

		public String getOid() {
			return this.oid;
		}

		public String specific_trap;

		public String getSpecific_trap() {
			return this.specific_trap;
		}

		public String variable_binding;

		public String getVariable_binding() {
			return this.variable_binding;
		}

		public String criticite;

		public String getCriticite() {
			return this.criticite;
		}

		public String message_alarme;

		public String getMessage_alarme() {
			return this.message_alarme;
		}

		public String instructions;

		public String getInstructions() {
			return this.instructions;
		}

		public String acquittement;

		public String getAcquittement() {
			return this.acquittement;
		}

		public String mib_associe;

		public String getMib_associe() {
			return this.mib_associe;
		}

		public String objet;

		public String getObjet() {
			return this.objet;
		}

		public String compelement_information;

		public String getCompelement_information() {
			return this.compelement_information;
		}

		public String support;

		public String getSupport() {
			return this.support;
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
			final inserttrapsStruct other = (inserttrapsStruct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(inserttrapsStruct other) {

			other.id = this.id;
			other.ref = this.ref;
			other.etat = this.etat;
			other.ref_composant = this.ref_composant;
			other.signification = this.signification;
			other.version_snmp = this.version_snmp;
			other.oid = this.oid;
			other.specific_trap = this.specific_trap;
			other.variable_binding = this.variable_binding;
			other.criticite = this.criticite;
			other.message_alarme = this.message_alarme;
			other.instructions = this.instructions;
			other.acquittement = this.acquittement;
			other.mib_associe = this.mib_associe;
			other.objet = this.objet;
			other.compelement_information = this.compelement_information;
			other.support = this.support;

		}

		public void copyKeysDataTo(inserttrapsStruct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.ref = readString(dis);

					this.etat = readString(dis);

					this.ref_composant = readString(dis);

					this.signification = readString(dis);

					this.version_snmp = readString(dis);

					this.oid = readString(dis);

					this.specific_trap = readString(dis);

					this.variable_binding = readString(dis);

					this.criticite = readString(dis);

					this.message_alarme = readString(dis);

					this.instructions = readString(dis);

					this.acquittement = readString(dis);

					this.mib_associe = readString(dis);

					this.objet = readString(dis);

					this.compelement_information = readString(dis);

					this.support = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.ref = readString(dis);

					this.etat = readString(dis);

					this.ref_composant = readString(dis);

					this.signification = readString(dis);

					this.version_snmp = readString(dis);

					this.oid = readString(dis);

					this.specific_trap = readString(dis);

					this.variable_binding = readString(dis);

					this.criticite = readString(dis);

					this.message_alarme = readString(dis);

					this.instructions = readString(dis);

					this.acquittement = readString(dis);

					this.mib_associe = readString(dis);

					this.objet = readString(dis);

					this.compelement_information = readString(dis);

					this.support = readString(dis);

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

				writeString(this.ref, dos);

				// String

				writeString(this.etat, dos);

				// String

				writeString(this.ref_composant, dos);

				// String

				writeString(this.signification, dos);

				// String

				writeString(this.version_snmp, dos);

				// String

				writeString(this.oid, dos);

				// String

				writeString(this.specific_trap, dos);

				// String

				writeString(this.variable_binding, dos);

				// String

				writeString(this.criticite, dos);

				// String

				writeString(this.message_alarme, dos);

				// String

				writeString(this.instructions, dos);

				// String

				writeString(this.acquittement, dos);

				// String

				writeString(this.mib_associe, dos);

				// String

				writeString(this.objet, dos);

				// String

				writeString(this.compelement_information, dos);

				// String

				writeString(this.support, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.ref, dos);

				// String

				writeString(this.etat, dos);

				// String

				writeString(this.ref_composant, dos);

				// String

				writeString(this.signification, dos);

				// String

				writeString(this.version_snmp, dos);

				// String

				writeString(this.oid, dos);

				// String

				writeString(this.specific_trap, dos);

				// String

				writeString(this.variable_binding, dos);

				// String

				writeString(this.criticite, dos);

				// String

				writeString(this.message_alarme, dos);

				// String

				writeString(this.instructions, dos);

				// String

				writeString(this.acquittement, dos);

				// String

				writeString(this.mib_associe, dos);

				// String

				writeString(this.objet, dos);

				// String

				writeString(this.compelement_information, dos);

				// String

				writeString(this.support, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",ref=" + ref);
			sb.append(",etat=" + etat);
			sb.append(",ref_composant=" + ref_composant);
			sb.append(",signification=" + signification);
			sb.append(",version_snmp=" + version_snmp);
			sb.append(",oid=" + oid);
			sb.append(",specific_trap=" + specific_trap);
			sb.append(",variable_binding=" + variable_binding);
			sb.append(",criticite=" + criticite);
			sb.append(",message_alarme=" + message_alarme);
			sb.append(",instructions=" + instructions);
			sb.append(",acquittement=" + acquittement);
			sb.append(",mib_associe=" + mib_associe);
			sb.append(",objet=" + objet);
			sb.append(",compelement_information=" + compelement_information);
			sb.append(",support=" + support);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(inserttrapsStruct other) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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

		public String Support;

		public String getSupport() {
			return this.Support;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

					this.Support = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

					this.Support = readString(dis);

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

				// String

				writeString(this.Support, dos);

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

				// String

				writeString(this.Support, dos);

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
			sb.append(",Support=" + Support);
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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		public String ref;

		public String getRef() {
			return this.ref;
		}

		public String etat;

		public String getEtat() {
			return this.etat;
		}

		public String platforme;

		public String getPlatforme() {
			return this.platforme;
		}

		public String hostname;

		public String getHostname() {
			return this.hostname;
		}

		public String fqdn;

		public String getFqdn() {
			return this.fqdn;
		}

		public String type;

		public String getType() {
			return this.type;
		}

		public String modele;

		public String getModele() {
			return this.modele;
		}

		public String os;

		public String getOs() {
			return this.os;
		}

		public String ver_tech_firmware;

		public String getVer_tech_firmware() {
			return this.ver_tech_firmware;
		}

		public String cluster;

		public String getCluster() {
			return this.cluster;
		}

		public String ip_source;

		public String getIp_source() {
			return this.ip_source;
		}

		public String description;

		public String getDescription() {
			return this.description;
		}

		public String socle_standard_omu;

		public String getSocle_standard_omu() {
			return this.socle_standard_omu;
		}

		public String complements_informations;

		public String getComplements_informations() {
			return this.complements_informations;
		}

		public String support;

		public String getSupport() {
			return this.support;
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
			other.ref = this.ref;
			other.etat = this.etat;
			other.platforme = this.platforme;
			other.hostname = this.hostname;
			other.fqdn = this.fqdn;
			other.type = this.type;
			other.modele = this.modele;
			other.os = this.os;
			other.ver_tech_firmware = this.ver_tech_firmware;
			other.cluster = this.cluster;
			other.ip_source = this.ip_source;
			other.description = this.description;
			other.socle_standard_omu = this.socle_standard_omu;
			other.complements_informations = this.complements_informations;
			other.support = this.support;

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.ref = readString(dis);

					this.etat = readString(dis);

					this.platforme = readString(dis);

					this.hostname = readString(dis);

					this.fqdn = readString(dis);

					this.type = readString(dis);

					this.modele = readString(dis);

					this.os = readString(dis);

					this.ver_tech_firmware = readString(dis);

					this.cluster = readString(dis);

					this.ip_source = readString(dis);

					this.description = readString(dis);

					this.socle_standard_omu = readString(dis);

					this.complements_informations = readString(dis);

					this.support = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.ref = readString(dis);

					this.etat = readString(dis);

					this.platforme = readString(dis);

					this.hostname = readString(dis);

					this.fqdn = readString(dis);

					this.type = readString(dis);

					this.modele = readString(dis);

					this.os = readString(dis);

					this.ver_tech_firmware = readString(dis);

					this.cluster = readString(dis);

					this.ip_source = readString(dis);

					this.description = readString(dis);

					this.socle_standard_omu = readString(dis);

					this.complements_informations = readString(dis);

					this.support = readString(dis);

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

				writeString(this.ref, dos);

				// String

				writeString(this.etat, dos);

				// String

				writeString(this.platforme, dos);

				// String

				writeString(this.hostname, dos);

				// String

				writeString(this.fqdn, dos);

				// String

				writeString(this.type, dos);

				// String

				writeString(this.modele, dos);

				// String

				writeString(this.os, dos);

				// String

				writeString(this.ver_tech_firmware, dos);

				// String

				writeString(this.cluster, dos);

				// String

				writeString(this.ip_source, dos);

				// String

				writeString(this.description, dos);

				// String

				writeString(this.socle_standard_omu, dos);

				// String

				writeString(this.complements_informations, dos);

				// String

				writeString(this.support, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.ref, dos);

				// String

				writeString(this.etat, dos);

				// String

				writeString(this.platforme, dos);

				// String

				writeString(this.hostname, dos);

				// String

				writeString(this.fqdn, dos);

				// String

				writeString(this.type, dos);

				// String

				writeString(this.modele, dos);

				// String

				writeString(this.os, dos);

				// String

				writeString(this.ver_tech_firmware, dos);

				// String

				writeString(this.cluster, dos);

				// String

				writeString(this.ip_source, dos);

				// String

				writeString(this.description, dos);

				// String

				writeString(this.socle_standard_omu, dos);

				// String

				writeString(this.complements_informations, dos);

				// String

				writeString(this.support, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",ref=" + ref);
			sb.append(",etat=" + etat);
			sb.append(",platforme=" + platforme);
			sb.append(",hostname=" + hostname);
			sb.append(",fqdn=" + fqdn);
			sb.append(",type=" + type);
			sb.append(",modele=" + modele);
			sb.append(",os=" + os);
			sb.append(",ver_tech_firmware=" + ver_tech_firmware);
			sb.append(",cluster=" + cluster);
			sb.append(",ip_source=" + ip_source);
			sb.append(",description=" + description);
			sb.append(",socle_standard_omu=" + socle_standard_omu);
			sb.append(",complements_informations=" + complements_informations);
			sb.append(",support=" + support);
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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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

		public String Support;

		public String getSupport() {
			return this.Support;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

					this.Support = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

					this.Support = readString(dis);

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

				// String

				writeString(this.Support, dos);

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

				// String

				writeString(this.Support, dos);

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
			sb.append(",Support=" + Support);
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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

	public static class insertlogfilespatternsStruct
			implements routines.system.IPersistableRow<insertlogfilespatternsStruct> {
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		public int n_ref;

		public int getN_ref() {
			return this.n_ref;
		}

		public int ref;

		public int getRef() {
			return this.ref;
		}

		public String etat;

		public String getEtat() {
			return this.etat;
		}

		public String signification;

		public String getSignification() {
			return this.signification;
		}

		public String identification;

		public String getIdentification() {
			return this.identification;
		}

		public String criticite;

		public String getCriticite() {
			return this.criticite;
		}

		public String message_alarme;

		public String getMessage_alarme() {
			return this.message_alarme;
		}

		public String instructions;

		public String getInstructions() {
			return this.instructions;
		}

		public String perform_action;

		public String getPerform_action() {
			return this.perform_action;
		}

		public String acquittement;

		public String getAcquittement() {
			return this.acquittement;
		}

		public String complements_informations;

		public String getComplements_informations() {
			return this.complements_informations;
		}

		public String ref_service;

		public String getRef_service() {
			return this.ref_service;
		}

		public String objet;

		public String getObjet() {
			return this.objet;
		}

		public String support;

		public String getSupport() {
			return this.support;
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
			final insertlogfilespatternsStruct other = (insertlogfilespatternsStruct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(insertlogfilespatternsStruct other) {

			other.id = this.id;
			other.n_ref = this.n_ref;
			other.ref = this.ref;
			other.etat = this.etat;
			other.signification = this.signification;
			other.identification = this.identification;
			other.criticite = this.criticite;
			other.message_alarme = this.message_alarme;
			other.instructions = this.instructions;
			other.perform_action = this.perform_action;
			other.acquittement = this.acquittement;
			other.complements_informations = this.complements_informations;
			other.ref_service = this.ref_service;
			other.objet = this.objet;
			other.support = this.support;

		}

		public void copyKeysDataTo(insertlogfilespatternsStruct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.n_ref = dis.readInt();

					this.ref = dis.readInt();

					this.etat = readString(dis);

					this.signification = readString(dis);

					this.identification = readString(dis);

					this.criticite = readString(dis);

					this.message_alarme = readString(dis);

					this.instructions = readString(dis);

					this.perform_action = readString(dis);

					this.acquittement = readString(dis);

					this.complements_informations = readString(dis);

					this.ref_service = readString(dis);

					this.objet = readString(dis);

					this.support = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.n_ref = dis.readInt();

					this.ref = dis.readInt();

					this.etat = readString(dis);

					this.signification = readString(dis);

					this.identification = readString(dis);

					this.criticite = readString(dis);

					this.message_alarme = readString(dis);

					this.instructions = readString(dis);

					this.perform_action = readString(dis);

					this.acquittement = readString(dis);

					this.complements_informations = readString(dis);

					this.ref_service = readString(dis);

					this.objet = readString(dis);

					this.support = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.id, dos);

				// int

				dos.writeInt(this.n_ref);

				// int

				dos.writeInt(this.ref);

				// String

				writeString(this.etat, dos);

				// String

				writeString(this.signification, dos);

				// String

				writeString(this.identification, dos);

				// String

				writeString(this.criticite, dos);

				// String

				writeString(this.message_alarme, dos);

				// String

				writeString(this.instructions, dos);

				// String

				writeString(this.perform_action, dos);

				// String

				writeString(this.acquittement, dos);

				// String

				writeString(this.complements_informations, dos);

				// String

				writeString(this.ref_service, dos);

				// String

				writeString(this.objet, dos);

				// String

				writeString(this.support, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// int

				dos.writeInt(this.n_ref);

				// int

				dos.writeInt(this.ref);

				// String

				writeString(this.etat, dos);

				// String

				writeString(this.signification, dos);

				// String

				writeString(this.identification, dos);

				// String

				writeString(this.criticite, dos);

				// String

				writeString(this.message_alarme, dos);

				// String

				writeString(this.instructions, dos);

				// String

				writeString(this.perform_action, dos);

				// String

				writeString(this.acquittement, dos);

				// String

				writeString(this.complements_informations, dos);

				// String

				writeString(this.ref_service, dos);

				// String

				writeString(this.objet, dos);

				// String

				writeString(this.support, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",n_ref=" + String.valueOf(n_ref));
			sb.append(",ref=" + String.valueOf(ref));
			sb.append(",etat=" + etat);
			sb.append(",signification=" + signification);
			sb.append(",identification=" + identification);
			sb.append(",criticite=" + criticite);
			sb.append(",message_alarme=" + message_alarme);
			sb.append(",instructions=" + instructions);
			sb.append(",perform_action=" + perform_action);
			sb.append(",acquittement=" + acquittement);
			sb.append(",complements_informations=" + complements_informations);
			sb.append(",ref_service=" + ref_service);
			sb.append(",objet=" + objet);
			sb.append(",support=" + support);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(insertlogfilespatternsStruct other) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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

		public String Support;

		public String getSupport() {
			return this.Support;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

					this.Support = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

					this.Support = readString(dis);

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

				// String

				writeString(this.Support, dos);

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

				// String

				writeString(this.Support, dos);

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
			sb.append(",Support=" + Support);
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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.filepath3 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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
				inserttrapsStruct inserttraps = new inserttrapsStruct();
				row8Struct row8 = new row8Struct();
				row17Struct row17 = new row17Struct();
				row26Struct row26 = new row26Struct();
				insertserveursStruct insertserveurs = new insertserveursStruct();
				row9Struct row9 = new row9Struct();
				row18Struct row18 = new row18Struct();
				row27Struct row27 = new row27Struct();
				insertlogfilespatternsStruct insertlogfilespatterns = new insertlogfilespatternsStruct();

				/**
				 * [tFileList_3 begin ] start
				 */

				int NB_ITERATE_tFileInputExcel_31 = 0; // for statistics

				int NB_ITERATE_tFileInputExcel_39 = 0; // for statistics

				int NB_ITERATE_tIterateToFlow_3_ITFO = 0; // for statistics

				int NB_ITERATE_tFileInputExcel_3 = 0; // for statistics

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
						runStat.updateStatOnConnection("row16", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("inserttraps", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row7", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row25", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate8", 1, "exec" + NB_ITERATE_tFileInputExcel_39);
						// Thread.sleep(1000);
					}

					/**
					 * [tDBOutput_7 begin ] start
					 */

					ok_Hash.put("tDBOutput_7", false);
					start_Hash.put("tDBOutput_7", System.currentTimeMillis());

					currentComponent = "tDBOutput_7";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "inserttraps");
					}

					int tos_count_tDBOutput_7 = 0;

					int nb_line_tDBOutput_7 = 0;
					int nb_line_update_tDBOutput_7 = 0;
					int nb_line_inserted_tDBOutput_7 = 0;
					int nb_line_deleted_tDBOutput_7 = 0;
					int nb_line_rejected_tDBOutput_7 = 0;

					int deletedCount_tDBOutput_7 = 0;
					int updatedCount_tDBOutput_7 = 0;
					int insertedCount_tDBOutput_7 = 0;
					int rowsToCommitCount_tDBOutput_7 = 0;
					int rejectedCount_tDBOutput_7 = 0;

					String tableName_tDBOutput_7 = "traps_snmp";
					boolean whetherReject_tDBOutput_7 = false;

					java.util.Calendar calendar_tDBOutput_7 = java.util.Calendar.getInstance();
					calendar_tDBOutput_7.set(1, 0, 1, 0, 0, 0);
					long year1_tDBOutput_7 = calendar_tDBOutput_7.getTime().getTime();
					calendar_tDBOutput_7.set(10000, 0, 1, 0, 0, 0);
					long year10000_tDBOutput_7 = calendar_tDBOutput_7.getTime().getTime();
					long date_tDBOutput_7;

					java.sql.Connection conn_tDBOutput_7 = null;

					String properties_tDBOutput_7 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
					if (properties_tDBOutput_7 == null || properties_tDBOutput_7.trim().length() == 0) {
						properties_tDBOutput_7 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
					} else {
						if (!properties_tDBOutput_7.contains("rewriteBatchedStatements=")) {
							properties_tDBOutput_7 += "&rewriteBatchedStatements=true";
						}

						if (!properties_tDBOutput_7.contains("allowLoadLocalInfile=")) {
							properties_tDBOutput_7 += "&allowLoadLocalInfile=true";
						}
					}

					String url_tDBOutput_7 = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "ooredoo" + "?"
							+ properties_tDBOutput_7;

					String driverClass_tDBOutput_7 = "com.mysql.cj.jdbc.Driver";

					String dbUser_tDBOutput_7 = "root";

					final String decryptedPassword_tDBOutput_7 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:Vqs7MXCZVjwoFO1P6hA/XPrvL57EnHl9PbpCLg==");

					String dbPwd_tDBOutput_7 = decryptedPassword_tDBOutput_7;
					java.lang.Class.forName(driverClass_tDBOutput_7);

					conn_tDBOutput_7 = java.sql.DriverManager.getConnection(url_tDBOutput_7, dbUser_tDBOutput_7,
							dbPwd_tDBOutput_7);

					resourceMap.put("conn_tDBOutput_7", conn_tDBOutput_7);
					conn_tDBOutput_7.setAutoCommit(false);
					int commitEvery_tDBOutput_7 = 10000;
					int commitCounter_tDBOutput_7 = 0;

					int count_tDBOutput_7 = 0;

					String insert_tDBOutput_7 = "INSERT IGNORE INTO `" + "traps_snmp"
							+ "` (`id`,`ref`,`etat`,`ref_composant`,`signification`,`version_snmp`,`oid`,`specific_trap`,`variable_binding`,`criticite`,`message_alarme`,`instructions`,`acquittement`,`mib_associe`,`objet`,`compelement_information`,`support`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

					java.sql.PreparedStatement pstmt_tDBOutput_7 = conn_tDBOutput_7
							.prepareStatement(insert_tDBOutput_7);
					resourceMap.put("pstmt_tDBOutput_7", pstmt_tDBOutput_7);

					/**
					 * [tDBOutput_7 begin ] stop
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
					inserttrapsStruct inserttraps_tmp = new inserttrapsStruct();
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
							.decryptPassword("enc:routine.encryption.key.v1:ml3rfpEFhwSoSgVCBgDpJX4eYJSaz2LQK49Ovw==");
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
										boolean startAdding = false;
										StringBuilder supportBuilder = new StringBuilder();

										for (String part : parts) {
											if (startAdding) {
												supportBuilder.append(part).append("_");
											}
											if (part.equals("Support")) {
												startAdding = true;
											}
										}

										String supportPart = supportBuilder.substring(0, supportBuilder.length() - 1);

										String support = supportPart;

										String equipe;

										if ("CLOUD_et_APP_IT.xlsx".equals(support)) {
											equipe = "cloud";
										} else if ("ASE_VAS.xlsx".equals(support)) {
											equipe = "ASEVAS";
										} else if ("BILLING.xlsx".equals(support)) {
											equipe = "BILLING";
										} else if ("ASE_IN.xlsx".equals(support)) {
											equipe = "ASEIN";
										} else if ("Backup.xlsx".equals(support)) {
											equipe = "Backup";
										} else if ("ASE_GPRS.xlsx".equals(support)) {
											equipe = "ASEGPRS";
										} else {
											// Valeur par défaut si aucune des conditions n'est satisfaite
											equipe = "Autre";
										}

// Récupérer la valeur de "N__Ref" et la convertir en String
										String rowRef = row16.N__Ref;

// Générer l'ID "EDB_POSANET_X"
										String id = "EBD_" + fileWord + "_TrapsSNMP_" + rowRef;
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
										row25.Support = equipe;
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

											inserttraps = null;

// # Output table : 'inserttraps'
											inserttraps_tmp.id = row25.N__Ref;
											inserttraps_tmp.ref = row25.Ref;
											inserttraps_tmp.etat = row25.Etat;
											inserttraps_tmp.ref_composant = row25.Ref__des_composants;
											inserttraps_tmp.signification = row25.Signification;
											inserttraps_tmp.version_snmp = row25.Version_SNMP;
											inserttraps_tmp.oid = row25.OID;
											inserttraps_tmp.specific_trap = row25.Specific_trap;
											inserttraps_tmp.variable_binding = row25.Variable_s__binding;
											inserttraps_tmp.criticite = row25.Criticite;
											inserttraps_tmp.message_alarme = row25.Message_d_alarme;
											inserttraps_tmp.instructions = row25.Instructions;
											inserttraps_tmp.acquittement = row25.Acquittement;
											inserttraps_tmp.mib_associe = row25.MIB_associee_s;
											inserttraps_tmp.objet = row25.Objet;
											inserttraps_tmp.compelement_information = row25.Complements_d_informations;
											inserttraps_tmp.support = row25.Support;
											inserttraps = inserttraps_tmp;
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
// Start of branch "inserttraps"
										if (inserttraps != null) {

											/**
											 * [tDBOutput_7 main ] start
											 */

											currentComponent = "tDBOutput_7";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "inserttraps"

												);
											}

											whetherReject_tDBOutput_7 = false;
											if (inserttraps.id == null) {
												pstmt_tDBOutput_7.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_7.setString(1, inserttraps.id);
											}

											if (inserttraps.ref == null) {
												pstmt_tDBOutput_7.setNull(2, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_7.setString(2, inserttraps.ref);
											}

											if (inserttraps.etat == null) {
												pstmt_tDBOutput_7.setNull(3, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_7.setString(3, inserttraps.etat);
											}

											if (inserttraps.ref_composant == null) {
												pstmt_tDBOutput_7.setNull(4, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_7.setString(4, inserttraps.ref_composant);
											}

											if (inserttraps.signification == null) {
												pstmt_tDBOutput_7.setNull(5, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_7.setString(5, inserttraps.signification);
											}

											if (inserttraps.version_snmp == null) {
												pstmt_tDBOutput_7.setNull(6, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_7.setString(6, inserttraps.version_snmp);
											}

											if (inserttraps.oid == null) {
												pstmt_tDBOutput_7.setNull(7, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_7.setString(7, inserttraps.oid);
											}

											if (inserttraps.specific_trap == null) {
												pstmt_tDBOutput_7.setNull(8, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_7.setString(8, inserttraps.specific_trap);
											}

											if (inserttraps.variable_binding == null) {
												pstmt_tDBOutput_7.setNull(9, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_7.setString(9, inserttraps.variable_binding);
											}

											if (inserttraps.criticite == null) {
												pstmt_tDBOutput_7.setNull(10, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_7.setString(10, inserttraps.criticite);
											}

											if (inserttraps.message_alarme == null) {
												pstmt_tDBOutput_7.setNull(11, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_7.setString(11, inserttraps.message_alarme);
											}

											if (inserttraps.instructions == null) {
												pstmt_tDBOutput_7.setNull(12, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_7.setString(12, inserttraps.instructions);
											}

											if (inserttraps.acquittement == null) {
												pstmt_tDBOutput_7.setNull(13, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_7.setString(13, inserttraps.acquittement);
											}

											if (inserttraps.mib_associe == null) {
												pstmt_tDBOutput_7.setNull(14, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_7.setString(14, inserttraps.mib_associe);
											}

											if (inserttraps.objet == null) {
												pstmt_tDBOutput_7.setNull(15, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_7.setString(15, inserttraps.objet);
											}

											if (inserttraps.compelement_information == null) {
												pstmt_tDBOutput_7.setNull(16, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_7.setString(16, inserttraps.compelement_information);
											}

											if (inserttraps.support == null) {
												pstmt_tDBOutput_7.setNull(17, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_7.setString(17, inserttraps.support);
											}

											try {
												nb_line_tDBOutput_7++;
												int processedCount_tDBOutput_7 = pstmt_tDBOutput_7.executeUpdate();
												insertedCount_tDBOutput_7 += processedCount_tDBOutput_7;
												rowsToCommitCount_tDBOutput_7 += processedCount_tDBOutput_7;
											} catch (java.lang.Exception e) {
												globalMap.put("tDBOutput_7_ERROR_MESSAGE", e.getMessage());
												whetherReject_tDBOutput_7 = true;
												System.err.print(e.getMessage());
											}
											commitCounter_tDBOutput_7++;

											if (commitEvery_tDBOutput_7 <= commitCounter_tDBOutput_7) {

												if (rowsToCommitCount_tDBOutput_7 != 0) {
												}
												conn_tDBOutput_7.commit();
												if (rowsToCommitCount_tDBOutput_7 != 0) {
													rowsToCommitCount_tDBOutput_7 = 0;
												}
												commitCounter_tDBOutput_7 = 0;

											}

											tos_count_tDBOutput_7++;

											/**
											 * [tDBOutput_7 main ] stop
											 */

											/**
											 * [tDBOutput_7 process_data_begin ] start
											 */

											currentComponent = "tDBOutput_7";

											/**
											 * [tDBOutput_7 process_data_begin ] stop
											 */

											/**
											 * [tDBOutput_7 process_data_end ] start
											 */

											currentComponent = "tDBOutput_7";

											/**
											 * [tDBOutput_7 process_data_end ] stop
											 */

										} // End of branch "inserttraps"

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
					 * [tDBOutput_7 end ] start
					 */

					currentComponent = "tDBOutput_7";

					if (pstmt_tDBOutput_7 != null) {

						pstmt_tDBOutput_7.close();
						resourceMap.remove("pstmt_tDBOutput_7");

					}
					resourceMap.put("statementClosed_tDBOutput_7", true);
					if (commitCounter_tDBOutput_7 > 0 && rowsToCommitCount_tDBOutput_7 != 0) {

					}
					conn_tDBOutput_7.commit();
					if (commitCounter_tDBOutput_7 > 0 && rowsToCommitCount_tDBOutput_7 != 0) {

						rowsToCommitCount_tDBOutput_7 = 0;
					}
					commitCounter_tDBOutput_7 = 0;

					conn_tDBOutput_7.close();

					resourceMap.put("finish_tDBOutput_7", true);

					nb_line_deleted_tDBOutput_7 = nb_line_deleted_tDBOutput_7 + deletedCount_tDBOutput_7;
					nb_line_update_tDBOutput_7 = nb_line_update_tDBOutput_7 + updatedCount_tDBOutput_7;
					nb_line_inserted_tDBOutput_7 = nb_line_inserted_tDBOutput_7 + insertedCount_tDBOutput_7;
					nb_line_rejected_tDBOutput_7 = nb_line_rejected_tDBOutput_7 + rejectedCount_tDBOutput_7;

					globalMap.put("tDBOutput_7_NB_LINE", nb_line_tDBOutput_7);
					globalMap.put("tDBOutput_7_NB_LINE_UPDATED", nb_line_update_tDBOutput_7);
					globalMap.put("tDBOutput_7_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_7);
					globalMap.put("tDBOutput_7_NB_LINE_DELETED", nb_line_deleted_tDBOutput_7);
					globalMap.put("tDBOutput_7_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_7);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "inserttraps");
					}

					ok_Hash.put("tDBOutput_7", true);
					end_Hash.put("tDBOutput_7", System.currentTimeMillis());

					/**
					 * [tDBOutput_7 end ] stop
					 */

					if (execStat) {
						runStat.updateStatOnConnection("iterate8", 2, "exec" + NB_ITERATE_tFileInputExcel_39);
					}

					NB_ITERATE_tFileInputExcel_31++;

					if (execStat) {
						runStat.updateStatOnConnection("row26", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row17", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row8", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("insertserveurs", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate9", 1, "exec" + NB_ITERATE_tFileInputExcel_31);
						// Thread.sleep(1000);
					}

					/**
					 * [tDBOutput_8 begin ] start
					 */

					ok_Hash.put("tDBOutput_8", false);
					start_Hash.put("tDBOutput_8", System.currentTimeMillis());

					currentComponent = "tDBOutput_8";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "insertserveurs");
					}

					int tos_count_tDBOutput_8 = 0;

					int nb_line_tDBOutput_8 = 0;
					int nb_line_update_tDBOutput_8 = 0;
					int nb_line_inserted_tDBOutput_8 = 0;
					int nb_line_deleted_tDBOutput_8 = 0;
					int nb_line_rejected_tDBOutput_8 = 0;

					int deletedCount_tDBOutput_8 = 0;
					int updatedCount_tDBOutput_8 = 0;
					int insertedCount_tDBOutput_8 = 0;
					int rowsToCommitCount_tDBOutput_8 = 0;
					int rejectedCount_tDBOutput_8 = 0;

					String tableName_tDBOutput_8 = "serveurs";
					boolean whetherReject_tDBOutput_8 = false;

					java.util.Calendar calendar_tDBOutput_8 = java.util.Calendar.getInstance();
					calendar_tDBOutput_8.set(1, 0, 1, 0, 0, 0);
					long year1_tDBOutput_8 = calendar_tDBOutput_8.getTime().getTime();
					calendar_tDBOutput_8.set(10000, 0, 1, 0, 0, 0);
					long year10000_tDBOutput_8 = calendar_tDBOutput_8.getTime().getTime();
					long date_tDBOutput_8;

					java.sql.Connection conn_tDBOutput_8 = null;

					String properties_tDBOutput_8 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
					if (properties_tDBOutput_8 == null || properties_tDBOutput_8.trim().length() == 0) {
						properties_tDBOutput_8 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
					} else {
						if (!properties_tDBOutput_8.contains("rewriteBatchedStatements=")) {
							properties_tDBOutput_8 += "&rewriteBatchedStatements=true";
						}

						if (!properties_tDBOutput_8.contains("allowLoadLocalInfile=")) {
							properties_tDBOutput_8 += "&allowLoadLocalInfile=true";
						}
					}

					String url_tDBOutput_8 = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "ooredoo" + "?"
							+ properties_tDBOutput_8;

					String driverClass_tDBOutput_8 = "com.mysql.cj.jdbc.Driver";

					String dbUser_tDBOutput_8 = "root";

					final String decryptedPassword_tDBOutput_8 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:GwKL7B1dLygh2GEJdnb4LFWkBpYWfGjYx2SHqQ==");

					String dbPwd_tDBOutput_8 = decryptedPassword_tDBOutput_8;
					java.lang.Class.forName(driverClass_tDBOutput_8);

					conn_tDBOutput_8 = java.sql.DriverManager.getConnection(url_tDBOutput_8, dbUser_tDBOutput_8,
							dbPwd_tDBOutput_8);

					resourceMap.put("conn_tDBOutput_8", conn_tDBOutput_8);
					conn_tDBOutput_8.setAutoCommit(false);
					int commitEvery_tDBOutput_8 = 10000;
					int commitCounter_tDBOutput_8 = 0;

					int count_tDBOutput_8 = 0;

					String insert_tDBOutput_8 = "INSERT IGNORE INTO `" + "serveurs"
							+ "` (`id`,`ref`,`etat`,`platforme`,`hostname`,`fqdn`,`type`,`modele`,`os`,`ver_tech_firmware`,`cluster`,`ip_source`,`description`,`socle_standard_omu`,`complements_informations`,`support`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

					java.sql.PreparedStatement pstmt_tDBOutput_8 = conn_tDBOutput_8
							.prepareStatement(insert_tDBOutput_8);
					resourceMap.put("pstmt_tDBOutput_8", pstmt_tDBOutput_8);

					/**
					 * [tDBOutput_8 begin ] stop
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
							.decryptPassword("enc:routine.encryption.key.v1:D3z0SaJZ2uHc6w1PmCnaB5hbdYG0rBwjxYhOAw==");
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
										boolean startAdding = false;
										StringBuilder supportBuilder = new StringBuilder();

										for (String part : parts) {
											if (startAdding) {
												supportBuilder.append(part).append("_");
											}
											if (part.equals("Support")) {
												startAdding = true;
											}
										}

										String supportPart = supportBuilder.substring(0, supportBuilder.length() - 1);

										String support = supportPart;

										String equipe;

										if ("CLOUD_et_APP_IT.xlsx".equals(support)) {
											equipe = "cloud";
										} else if ("ASE_VAS.xlsx".equals(support)) {
											equipe = "ASEVAS";
										} else if ("BILLING.xlsx".equals(support)) {
											equipe = "BILLING";
										} else if ("ASE_IN.xlsx".equals(support)) {
											equipe = "ASEIN";
										} else if ("Backup.xlsx".equals(support)) {
											equipe = "Backup";
										} else if ("ASE_GPRS.xlsx".equals(support)) {
											equipe = "ASEGPRS";
										} else {
											// Valeur par défaut si aucune des conditions n'est satisfaite
											equipe = "Autre";
										}
// Récupérer la valeur de "N__Ref" et la convertir en String
										String rowRef = row17.N__Ref;

// Générer l'ID "EDB_POSANET_X"
										String id = "EBD_" + fileWord + "_Serveurs_" + rowRef;
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
										row26.Support = equipe;
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
											insertserveurs_tmp.ref = row26.Ref;
											insertserveurs_tmp.etat = row26.Etat;
											insertserveurs_tmp.platforme = row26.Plateforme;
											insertserveurs_tmp.hostname = row26.Hostname;
											insertserveurs_tmp.fqdn = row26.FQDN;
											insertserveurs_tmp.type = row26.Type;
											insertserveurs_tmp.modele = row26.Modele;
											insertserveurs_tmp.os = row26.OS;
											insertserveurs_tmp.ver_tech_firmware = row26.Ver__tech____Firmware;
											insertserveurs_tmp.cluster = row26.Cluster;
											insertserveurs_tmp.ip_source = row26.IP_source;
											insertserveurs_tmp.description = row26.Description;
											insertserveurs_tmp.socle_standard_omu = row26.Socle_Standard_OMU;
											insertserveurs_tmp.complements_informations = row26.Complements_d_informations;
											insertserveurs_tmp.support = row26.Support;
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
											 * [tDBOutput_8 main ] start
											 */

											currentComponent = "tDBOutput_8";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "insertserveurs"

												);
											}

											whetherReject_tDBOutput_8 = false;
											if (insertserveurs.id == null) {
												pstmt_tDBOutput_8.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_8.setString(1, insertserveurs.id);
											}

											if (insertserveurs.ref == null) {
												pstmt_tDBOutput_8.setNull(2, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_8.setString(2, insertserveurs.ref);
											}

											if (insertserveurs.etat == null) {
												pstmt_tDBOutput_8.setNull(3, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_8.setString(3, insertserveurs.etat);
											}

											if (insertserveurs.platforme == null) {
												pstmt_tDBOutput_8.setNull(4, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_8.setString(4, insertserveurs.platforme);
											}

											if (insertserveurs.hostname == null) {
												pstmt_tDBOutput_8.setNull(5, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_8.setString(5, insertserveurs.hostname);
											}

											if (insertserveurs.fqdn == null) {
												pstmt_tDBOutput_8.setNull(6, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_8.setString(6, insertserveurs.fqdn);
											}

											if (insertserveurs.type == null) {
												pstmt_tDBOutput_8.setNull(7, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_8.setString(7, insertserveurs.type);
											}

											if (insertserveurs.modele == null) {
												pstmt_tDBOutput_8.setNull(8, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_8.setString(8, insertserveurs.modele);
											}

											if (insertserveurs.os == null) {
												pstmt_tDBOutput_8.setNull(9, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_8.setString(9, insertserveurs.os);
											}

											if (insertserveurs.ver_tech_firmware == null) {
												pstmt_tDBOutput_8.setNull(10, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_8.setString(10, insertserveurs.ver_tech_firmware);
											}

											if (insertserveurs.cluster == null) {
												pstmt_tDBOutput_8.setNull(11, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_8.setString(11, insertserveurs.cluster);
											}

											if (insertserveurs.ip_source == null) {
												pstmt_tDBOutput_8.setNull(12, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_8.setString(12, insertserveurs.ip_source);
											}

											if (insertserveurs.description == null) {
												pstmt_tDBOutput_8.setNull(13, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_8.setString(13, insertserveurs.description);
											}

											if (insertserveurs.socle_standard_omu == null) {
												pstmt_tDBOutput_8.setNull(14, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_8.setString(14, insertserveurs.socle_standard_omu);
											}

											if (insertserveurs.complements_informations == null) {
												pstmt_tDBOutput_8.setNull(15, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_8.setString(15,
														insertserveurs.complements_informations);
											}

											if (insertserveurs.support == null) {
												pstmt_tDBOutput_8.setNull(16, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_8.setString(16, insertserveurs.support);
											}

											try {
												nb_line_tDBOutput_8++;
												int processedCount_tDBOutput_8 = pstmt_tDBOutput_8.executeUpdate();
												insertedCount_tDBOutput_8 += processedCount_tDBOutput_8;
												rowsToCommitCount_tDBOutput_8 += processedCount_tDBOutput_8;
											} catch (java.lang.Exception e) {
												globalMap.put("tDBOutput_8_ERROR_MESSAGE", e.getMessage());
												whetherReject_tDBOutput_8 = true;
												System.err.print(e.getMessage());
											}
											commitCounter_tDBOutput_8++;

											if (commitEvery_tDBOutput_8 <= commitCounter_tDBOutput_8) {

												if (rowsToCommitCount_tDBOutput_8 != 0) {
												}
												conn_tDBOutput_8.commit();
												if (rowsToCommitCount_tDBOutput_8 != 0) {
													rowsToCommitCount_tDBOutput_8 = 0;
												}
												commitCounter_tDBOutput_8 = 0;

											}

											tos_count_tDBOutput_8++;

											/**
											 * [tDBOutput_8 main ] stop
											 */

											/**
											 * [tDBOutput_8 process_data_begin ] start
											 */

											currentComponent = "tDBOutput_8";

											/**
											 * [tDBOutput_8 process_data_begin ] stop
											 */

											/**
											 * [tDBOutput_8 process_data_end ] start
											 */

											currentComponent = "tDBOutput_8";

											/**
											 * [tDBOutput_8 process_data_end ] stop
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
					 * [tDBOutput_8 end ] start
					 */

					currentComponent = "tDBOutput_8";

					if (pstmt_tDBOutput_8 != null) {

						pstmt_tDBOutput_8.close();
						resourceMap.remove("pstmt_tDBOutput_8");

					}
					resourceMap.put("statementClosed_tDBOutput_8", true);
					if (commitCounter_tDBOutput_8 > 0 && rowsToCommitCount_tDBOutput_8 != 0) {

					}
					conn_tDBOutput_8.commit();
					if (commitCounter_tDBOutput_8 > 0 && rowsToCommitCount_tDBOutput_8 != 0) {

						rowsToCommitCount_tDBOutput_8 = 0;
					}
					commitCounter_tDBOutput_8 = 0;

					conn_tDBOutput_8.close();

					resourceMap.put("finish_tDBOutput_8", true);

					nb_line_deleted_tDBOutput_8 = nb_line_deleted_tDBOutput_8 + deletedCount_tDBOutput_8;
					nb_line_update_tDBOutput_8 = nb_line_update_tDBOutput_8 + updatedCount_tDBOutput_8;
					nb_line_inserted_tDBOutput_8 = nb_line_inserted_tDBOutput_8 + insertedCount_tDBOutput_8;
					nb_line_rejected_tDBOutput_8 = nb_line_rejected_tDBOutput_8 + rejectedCount_tDBOutput_8;

					globalMap.put("tDBOutput_8_NB_LINE", nb_line_tDBOutput_8);
					globalMap.put("tDBOutput_8_NB_LINE_UPDATED", nb_line_update_tDBOutput_8);
					globalMap.put("tDBOutput_8_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_8);
					globalMap.put("tDBOutput_8_NB_LINE_DELETED", nb_line_deleted_tDBOutput_8);
					globalMap.put("tDBOutput_8_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_8);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "insertserveurs");
					}

					ok_Hash.put("tDBOutput_8", true);
					end_Hash.put("tDBOutput_8", System.currentTimeMillis());

					/**
					 * [tDBOutput_8 end ] stop
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
						runStat.updateStatOnConnection("row27", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("insertlogfilespatterns", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate10", 1, "exec" + NB_ITERATE_tFileInputExcel_3);
						// Thread.sleep(1000);
					}

					/**
					 * [tDBOutput_9 begin ] start
					 */

					ok_Hash.put("tDBOutput_9", false);
					start_Hash.put("tDBOutput_9", System.currentTimeMillis());

					currentComponent = "tDBOutput_9";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "insertlogfilespatterns");
					}

					int tos_count_tDBOutput_9 = 0;

					int nb_line_tDBOutput_9 = 0;
					int nb_line_update_tDBOutput_9 = 0;
					int nb_line_inserted_tDBOutput_9 = 0;
					int nb_line_deleted_tDBOutput_9 = 0;
					int nb_line_rejected_tDBOutput_9 = 0;

					int deletedCount_tDBOutput_9 = 0;
					int updatedCount_tDBOutput_9 = 0;
					int insertedCount_tDBOutput_9 = 0;
					int rowsToCommitCount_tDBOutput_9 = 0;
					int rejectedCount_tDBOutput_9 = 0;

					String tableName_tDBOutput_9 = "log_files_patterns";
					boolean whetherReject_tDBOutput_9 = false;

					java.util.Calendar calendar_tDBOutput_9 = java.util.Calendar.getInstance();
					calendar_tDBOutput_9.set(1, 0, 1, 0, 0, 0);
					long year1_tDBOutput_9 = calendar_tDBOutput_9.getTime().getTime();
					calendar_tDBOutput_9.set(10000, 0, 1, 0, 0, 0);
					long year10000_tDBOutput_9 = calendar_tDBOutput_9.getTime().getTime();
					long date_tDBOutput_9;

					java.sql.Connection conn_tDBOutput_9 = null;

					String properties_tDBOutput_9 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
					if (properties_tDBOutput_9 == null || properties_tDBOutput_9.trim().length() == 0) {
						properties_tDBOutput_9 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
					} else {
						if (!properties_tDBOutput_9.contains("rewriteBatchedStatements=")) {
							properties_tDBOutput_9 += "&rewriteBatchedStatements=true";
						}

						if (!properties_tDBOutput_9.contains("allowLoadLocalInfile=")) {
							properties_tDBOutput_9 += "&allowLoadLocalInfile=true";
						}
					}

					String url_tDBOutput_9 = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "ooredoo" + "?"
							+ properties_tDBOutput_9;

					String driverClass_tDBOutput_9 = "com.mysql.cj.jdbc.Driver";

					String dbUser_tDBOutput_9 = "root";

					final String decryptedPassword_tDBOutput_9 = routines.system.PasswordEncryptUtil
							.decryptPassword("enc:routine.encryption.key.v1:MLMhnaxFIa6+rtoo0iEhoFpRZPKdoS91jWJR5Q==");

					String dbPwd_tDBOutput_9 = decryptedPassword_tDBOutput_9;
					java.lang.Class.forName(driverClass_tDBOutput_9);

					conn_tDBOutput_9 = java.sql.DriverManager.getConnection(url_tDBOutput_9, dbUser_tDBOutput_9,
							dbPwd_tDBOutput_9);

					resourceMap.put("conn_tDBOutput_9", conn_tDBOutput_9);
					conn_tDBOutput_9.setAutoCommit(false);
					int commitEvery_tDBOutput_9 = 10000;
					int commitCounter_tDBOutput_9 = 0;

					int count_tDBOutput_9 = 0;

					String insert_tDBOutput_9 = "INSERT IGNORE INTO `" + "log_files_patterns"
							+ "` (`id`,`n_ref`,`ref`,`etat`,`signification`,`identification`,`criticite`,`message_alarme`,`instructions`,`perform_action`,`acquittement`,`complements_informations`,`ref_service`,`objet`,`support`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

					java.sql.PreparedStatement pstmt_tDBOutput_9 = conn_tDBOutput_9
							.prepareStatement(insert_tDBOutput_9);
					resourceMap.put("pstmt_tDBOutput_9", pstmt_tDBOutput_9);

					/**
					 * [tDBOutput_9 begin ] stop
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
					insertlogfilespatternsStruct insertlogfilespatterns_tmp = new insertlogfilespatternsStruct();
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
							.decryptPassword("enc:routine.encryption.key.v1:o4WlKW1BNEavwNb+p0O5g2x9GeXrKabr4H6Vqg==");
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
										boolean startAdding = false;
										StringBuilder supportBuilder = new StringBuilder();

										for (String part : parts) {
											if (startAdding) {
												supportBuilder.append(part).append("_");
											}
											if (part.equals("Support")) {
												startAdding = true;
											}
										}

										String supportPart = supportBuilder.substring(0, supportBuilder.length() - 1);

										String support = supportPart;

										String equipe;

										if ("CLOUD_et_APP_IT.xlsx".equals(support)) {
											equipe = "cloud";
										} else if ("ASE_VAS.xlsx".equals(support)) {
											equipe = "ASEVAS";
										} else if ("BILLING.xlsx".equals(support)) {
											equipe = "BILLING";
										} else if ("ASE_IN.xlsx".equals(support)) {
											equipe = "ASEIN";
										} else if ("Backup.xlsx".equals(support)) {
											equipe = "Backup";
										} else if ("ASE_GPRS.xlsx".equals(support)) {
											equipe = "ASEGPRS";
										} else {
											// Valeur par défaut si aucune des conditions n'est satisfaite
											equipe = "Autre";
										}
// Récupérer la valeur de "N__Ref" et la convertir en String
										String rowRef = row18.Log_Ref;

// Générer l'ID "EDB_POSANET_X"
										String id = "EBD_" + fileWord + "_LogFilesPatterns_" + rowRef;
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
										row27.Support = equipe;

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

											insertlogfilespatterns = null;

// # Output table : 'insertlogfilespatterns'
											insertlogfilespatterns_tmp.id = row27.Log_Ref;
											insertlogfilespatterns_tmp.n_ref = row27.N__Ref;
											insertlogfilespatterns_tmp.ref = row27.Ref;
											insertlogfilespatterns_tmp.etat = row27.Etat;
											insertlogfilespatterns_tmp.signification = row27.Signification;
											insertlogfilespatterns_tmp.identification = row27.Identification;
											insertlogfilespatterns_tmp.criticite = row27.Criticite;
											insertlogfilespatterns_tmp.message_alarme = row27.Message_d_alarme;
											insertlogfilespatterns_tmp.instructions = row27.Instructions;
											insertlogfilespatterns_tmp.perform_action = row27.Perform_action;
											insertlogfilespatterns_tmp.acquittement = row27.Acquittement;
											insertlogfilespatterns_tmp.complements_informations = row27.Complements_d_informations;
											insertlogfilespatterns_tmp.ref_service = row27.Ref__Service;
											insertlogfilespatterns_tmp.objet = row27.Objet;
											insertlogfilespatterns_tmp.support = row27.Support;
											insertlogfilespatterns = insertlogfilespatterns_tmp;
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
// Start of branch "insertlogfilespatterns"
										if (insertlogfilespatterns != null) {

											/**
											 * [tDBOutput_9 main ] start
											 */

											currentComponent = "tDBOutput_9";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "insertlogfilespatterns"

												);
											}

											whetherReject_tDBOutput_9 = false;
											if (insertlogfilespatterns.id == null) {
												pstmt_tDBOutput_9.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_9.setString(1, insertlogfilespatterns.id);
											}

											pstmt_tDBOutput_9.setInt(2, insertlogfilespatterns.n_ref);

											pstmt_tDBOutput_9.setInt(3, insertlogfilespatterns.ref);

											if (insertlogfilespatterns.etat == null) {
												pstmt_tDBOutput_9.setNull(4, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_9.setString(4, insertlogfilespatterns.etat);
											}

											if (insertlogfilespatterns.signification == null) {
												pstmt_tDBOutput_9.setNull(5, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_9.setString(5, insertlogfilespatterns.signification);
											}

											if (insertlogfilespatterns.identification == null) {
												pstmt_tDBOutput_9.setNull(6, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_9.setString(6, insertlogfilespatterns.identification);
											}

											if (insertlogfilespatterns.criticite == null) {
												pstmt_tDBOutput_9.setNull(7, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_9.setString(7, insertlogfilespatterns.criticite);
											}

											if (insertlogfilespatterns.message_alarme == null) {
												pstmt_tDBOutput_9.setNull(8, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_9.setString(8, insertlogfilespatterns.message_alarme);
											}

											if (insertlogfilespatterns.instructions == null) {
												pstmt_tDBOutput_9.setNull(9, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_9.setString(9, insertlogfilespatterns.instructions);
											}

											if (insertlogfilespatterns.perform_action == null) {
												pstmt_tDBOutput_9.setNull(10, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_9.setString(10, insertlogfilespatterns.perform_action);
											}

											if (insertlogfilespatterns.acquittement == null) {
												pstmt_tDBOutput_9.setNull(11, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_9.setString(11, insertlogfilespatterns.acquittement);
											}

											if (insertlogfilespatterns.complements_informations == null) {
												pstmt_tDBOutput_9.setNull(12, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_9.setString(12,
														insertlogfilespatterns.complements_informations);
											}

											if (insertlogfilespatterns.ref_service == null) {
												pstmt_tDBOutput_9.setNull(13, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_9.setString(13, insertlogfilespatterns.ref_service);
											}

											if (insertlogfilespatterns.objet == null) {
												pstmt_tDBOutput_9.setNull(14, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_9.setString(14, insertlogfilespatterns.objet);
											}

											if (insertlogfilespatterns.support == null) {
												pstmt_tDBOutput_9.setNull(15, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_9.setString(15, insertlogfilespatterns.support);
											}

											try {
												nb_line_tDBOutput_9++;
												int processedCount_tDBOutput_9 = pstmt_tDBOutput_9.executeUpdate();
												insertedCount_tDBOutput_9 += processedCount_tDBOutput_9;
												rowsToCommitCount_tDBOutput_9 += processedCount_tDBOutput_9;
											} catch (java.lang.Exception e) {
												globalMap.put("tDBOutput_9_ERROR_MESSAGE", e.getMessage());
												whetherReject_tDBOutput_9 = true;
												System.err.print(e.getMessage());
											}
											commitCounter_tDBOutput_9++;

											if (commitEvery_tDBOutput_9 <= commitCounter_tDBOutput_9) {

												if (rowsToCommitCount_tDBOutput_9 != 0) {
												}
												conn_tDBOutput_9.commit();
												if (rowsToCommitCount_tDBOutput_9 != 0) {
													rowsToCommitCount_tDBOutput_9 = 0;
												}
												commitCounter_tDBOutput_9 = 0;

											}

											tos_count_tDBOutput_9++;

											/**
											 * [tDBOutput_9 main ] stop
											 */

											/**
											 * [tDBOutput_9 process_data_begin ] start
											 */

											currentComponent = "tDBOutput_9";

											/**
											 * [tDBOutput_9 process_data_begin ] stop
											 */

											/**
											 * [tDBOutput_9 process_data_end ] start
											 */

											currentComponent = "tDBOutput_9";

											/**
											 * [tDBOutput_9 process_data_end ] stop
											 */

										} // End of branch "insertlogfilespatterns"

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
					 * [tDBOutput_9 end ] start
					 */

					currentComponent = "tDBOutput_9";

					if (pstmt_tDBOutput_9 != null) {

						pstmt_tDBOutput_9.close();
						resourceMap.remove("pstmt_tDBOutput_9");

					}
					resourceMap.put("statementClosed_tDBOutput_9", true);
					if (commitCounter_tDBOutput_9 > 0 && rowsToCommitCount_tDBOutput_9 != 0) {

					}
					conn_tDBOutput_9.commit();
					if (commitCounter_tDBOutput_9 > 0 && rowsToCommitCount_tDBOutput_9 != 0) {

						rowsToCommitCount_tDBOutput_9 = 0;
					}
					commitCounter_tDBOutput_9 = 0;

					conn_tDBOutput_9.close();

					resourceMap.put("finish_tDBOutput_9", true);

					nb_line_deleted_tDBOutput_9 = nb_line_deleted_tDBOutput_9 + deletedCount_tDBOutput_9;
					nb_line_update_tDBOutput_9 = nb_line_update_tDBOutput_9 + updatedCount_tDBOutput_9;
					nb_line_inserted_tDBOutput_9 = nb_line_inserted_tDBOutput_9 + insertedCount_tDBOutput_9;
					nb_line_rejected_tDBOutput_9 = nb_line_rejected_tDBOutput_9 + rejectedCount_tDBOutput_9;

					globalMap.put("tDBOutput_9_NB_LINE", nb_line_tDBOutput_9);
					globalMap.put("tDBOutput_9_NB_LINE_UPDATED", nb_line_update_tDBOutput_9);
					globalMap.put("tDBOutput_9_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_9);
					globalMap.put("tDBOutput_9_NB_LINE_DELETED", nb_line_deleted_tDBOutput_9);
					globalMap.put("tDBOutput_9_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_9);

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "insertlogfilespatterns");
					}

					ok_Hash.put("tDBOutput_9", true);
					end_Hash.put("tDBOutput_9", System.currentTimeMillis());

					/**
					 * [tDBOutput_9 end ] stop
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
				 * [tDBOutput_7 finally ] start
				 */

				currentComponent = "tDBOutput_7";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_7") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_7 = null;
						if ((pstmtToClose_tDBOutput_7 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_7")) != null) {
							pstmtToClose_tDBOutput_7.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_7") == null) {
						java.sql.Connection ctn_tDBOutput_7 = null;
						if ((ctn_tDBOutput_7 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_7")) != null) {
							try {
								ctn_tDBOutput_7.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_7) {
								String errorMessage_tDBOutput_7 = "failed to close the connection in tDBOutput_7 :"
										+ sqlEx_tDBOutput_7.getMessage();
								System.err.println(errorMessage_tDBOutput_7);
							}
						}
					}
				}

				/**
				 * [tDBOutput_7 finally ] stop
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
				 * [tDBOutput_8 finally ] start
				 */

				currentComponent = "tDBOutput_8";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_8") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_8 = null;
						if ((pstmtToClose_tDBOutput_8 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_8")) != null) {
							pstmtToClose_tDBOutput_8.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_8") == null) {
						java.sql.Connection ctn_tDBOutput_8 = null;
						if ((ctn_tDBOutput_8 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_8")) != null) {
							try {
								ctn_tDBOutput_8.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_8) {
								String errorMessage_tDBOutput_8 = "failed to close the connection in tDBOutput_8 :"
										+ sqlEx_tDBOutput_8.getMessage();
								System.err.println(errorMessage_tDBOutput_8);
							}
						}
					}
				}

				/**
				 * [tDBOutput_8 finally ] stop
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
				 * [tDBOutput_9 finally ] start
				 */

				currentComponent = "tDBOutput_9";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_9") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_9 = null;
						if ((pstmtToClose_tDBOutput_9 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_9")) != null) {
							pstmtToClose_tDBOutput_9.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_9") == null) {
						java.sql.Connection ctn_tDBOutput_9 = null;
						if ((ctn_tDBOutput_9 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_9")) != null) {
							try {
								ctn_tDBOutput_9.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_9) {
								String errorMessage_tDBOutput_9 = "failed to close the connection in tDBOutput_9 :"
										+ sqlEx_tDBOutput_9.getMessage();
								System.err.println(errorMessage_tDBOutput_9);
							}
						}
					}
				}

				/**
				 * [tDBOutput_9 finally ] stop
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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.filepath = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.filepath2 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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
		final static byte[] commonByteArrayLock_EBD_MONOTORING_monitoring = new byte[0];
		static byte[] commonByteArray_EBD_MONOTORING_monitoring = new byte[0];

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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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
				if (length > commonByteArray_EBD_MONOTORING_monitoring.length) {
					if (length < 1024 && commonByteArray_EBD_MONOTORING_monitoring.length == 0) {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[1024];
					} else {
						commonByteArray_EBD_MONOTORING_monitoring = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_EBD_MONOTORING_monitoring, 0, length);
				strReturn = new String(commonByteArray_EBD_MONOTORING_monitoring, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

				try {

					int length = 0;

					this.filepath3 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_EBD_MONOTORING_monitoring) {

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
					.getResourceAsStream("ebd_monotoring/monitoring_0_1/contexts/" + contextStr + ".properties");
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
 * 917250 characters generated by Talend Open Studio for Data Integration on the
 * 16 août 2023 à 9:29:54 PM WAT
 ************************************************************************************************/
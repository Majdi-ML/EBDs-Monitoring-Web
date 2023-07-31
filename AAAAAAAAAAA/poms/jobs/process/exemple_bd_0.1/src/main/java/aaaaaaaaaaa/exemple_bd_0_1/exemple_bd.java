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

package aaaaaaaaaaa.exemple_bd_0_1;

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

@SuppressWarnings("unused")

/**
 * Job: exemple_bd Purpose: importer des fichiers excels vers la base de
 * données<br>
 * Description: je veux importer un fichier Excel dans une base de données où
 * les noms des feuilles correspondent aux noms des tables et contient des
 * identifiants avec des nombres croissants <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class exemple_bd implements TalendJob {

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
	private final String jobName = "exemple_bd";
	private final String projectName = "AAAAAAAAAAA";
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
					exemple_bd.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(exemple_bd.this, new Object[] { e, currentComponent, globalMap });
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

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
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

	public void tFileCopy_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileCopy_1_onSubJobError(exception, errorComponent, globalMap);
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

	public void tFileList_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileCopy_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tIterateToFlow_1_AI_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class insertttStruct implements routines.system.IPersistableRow<insertttStruct> {
		final static byte[] commonByteArrayLock_AAAAAAAAAAA_exemple_bd = new byte[0];
		static byte[] commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[0];
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
			final insertttStruct other = (insertttStruct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(insertttStruct other) {

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

		public void copyKeysDataTo(insertttStruct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_AAAAAAAAAAA_exemple_bd.length) {
					if (length < 1024 && commonByteArray_AAAAAAAAAAA_exemple_bd.length == 0) {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[1024];
					} else {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length);
				strReturn = new String(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length, utf8Charset);
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
				if (length > commonByteArray_AAAAAAAAAAA_exemple_bd.length) {
					if (length < 1024 && commonByteArray_AAAAAAAAAAA_exemple_bd.length == 0) {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[1024];
					} else {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length);
				strReturn = new String(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_AAAAAAAAAAA_exemple_bd) {

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

			synchronized (commonByteArrayLock_AAAAAAAAAAA_exemple_bd) {

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
		public int compareTo(insertttStruct other) {

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

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_AAAAAAAAAAA_exemple_bd = new byte[0];
		static byte[] commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[0];

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
				if (length > commonByteArray_AAAAAAAAAAA_exemple_bd.length) {
					if (length < 1024 && commonByteArray_AAAAAAAAAAA_exemple_bd.length == 0) {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[1024];
					} else {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length);
				strReturn = new String(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length, utf8Charset);
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
				if (length > commonByteArray_AAAAAAAAAAA_exemple_bd.length) {
					if (length < 1024 && commonByteArray_AAAAAAAAAAA_exemple_bd.length == 0) {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[1024];
					} else {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length);
				strReturn = new String(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_AAAAAAAAAAA_exemple_bd) {

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

			synchronized (commonByteArrayLock_AAAAAAAAAAA_exemple_bd) {

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

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_AAAAAAAAAAA_exemple_bd = new byte[0];
		static byte[] commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[0];

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
				if (length > commonByteArray_AAAAAAAAAAA_exemple_bd.length) {
					if (length < 1024 && commonByteArray_AAAAAAAAAAA_exemple_bd.length == 0) {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[1024];
					} else {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length);
				strReturn = new String(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length, utf8Charset);
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
				if (length > commonByteArray_AAAAAAAAAAA_exemple_bd.length) {
					if (length < 1024 && commonByteArray_AAAAAAAAAAA_exemple_bd.length == 0) {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[1024];
					} else {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length);
				strReturn = new String(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_AAAAAAAAAAA_exemple_bd) {

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

			synchronized (commonByteArrayLock_AAAAAAAAAAA_exemple_bd) {

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

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_AAAAAAAAAAA_exemple_bd = new byte[0];
		static byte[] commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[0];

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
				if (length > commonByteArray_AAAAAAAAAAA_exemple_bd.length) {
					if (length < 1024 && commonByteArray_AAAAAAAAAAA_exemple_bd.length == 0) {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[1024];
					} else {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length);
				strReturn = new String(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length, utf8Charset);
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
				if (length > commonByteArray_AAAAAAAAAAA_exemple_bd.length) {
					if (length < 1024 && commonByteArray_AAAAAAAAAAA_exemple_bd.length == 0) {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[1024];
					} else {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length);
				strReturn = new String(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_AAAAAAAAAAA_exemple_bd) {

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

			synchronized (commonByteArrayLock_AAAAAAAAAAA_exemple_bd) {

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

	public static class OnSubjobOkStructtIterateToFlow_1
			implements routines.system.IPersistableRow<OnSubjobOkStructtIterateToFlow_1> {
		final static byte[] commonByteArrayLock_AAAAAAAAAAA_exemple_bd = new byte[0];
		static byte[] commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[0];

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
				if (length > commonByteArray_AAAAAAAAAAA_exemple_bd.length) {
					if (length < 1024 && commonByteArray_AAAAAAAAAAA_exemple_bd.length == 0) {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[1024];
					} else {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length);
				strReturn = new String(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length, utf8Charset);
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
				if (length > commonByteArray_AAAAAAAAAAA_exemple_bd.length) {
					if (length < 1024 && commonByteArray_AAAAAAAAAAA_exemple_bd.length == 0) {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[1024];
					} else {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length);
				strReturn = new String(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_AAAAAAAAAAA_exemple_bd) {

				try {

					int length = 0;

					this.filepath = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_AAAAAAAAAAA_exemple_bd) {

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
				row2Struct row2 = new row2Struct();
				row3Struct row3 = new row3Struct();
				insertttStruct inserttt = new insertttStruct();

				/**
				 * [tFileList_1 begin ] start
				 */

				int NB_ITERATE_tIterateToFlow_1_ITFO = 0; // for statistics

				int NB_ITERATE_tFileInputExcel_1 = 0; // for statistics

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
						runStat.updateStatOnConnection("OnComponentOk1", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row3", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("inserttt", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row2", 3, 0);
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
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "inserttt");
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

					String tableName_tDBOutput_1 = "requetessql";
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
							.decryptPassword("enc:routine.encryption.key.v1:E5LZhgKiZdL1+2vc2larYhp9aLoyh3lAzlcjFA==");

					String dbPwd_tDBOutput_1 = decryptedPassword_tDBOutput_1;
					java.lang.Class.forName(driverClass_tDBOutput_1);

					conn_tDBOutput_1 = java.sql.DriverManager.getConnection(url_tDBOutput_1, dbUser_tDBOutput_1,
							dbPwd_tDBOutput_1);

					resourceMap.put("conn_tDBOutput_1", conn_tDBOutput_1);
					conn_tDBOutput_1.setAutoCommit(false);
					int commitEvery_tDBOutput_1 = 10000;
					int commitCounter_tDBOutput_1 = 0;

					int count_tDBOutput_1 = 0;

					String insert_tDBOutput_1 = "INSERT INTO `" + "requetessql"
							+ "` (`id`,`Ref`,`Etat`,`Ref_composant`,`RG/SG_si_Cluster`,`Requete_SQL`,`UserName/DB_Name`,`Resultat_Attendu_de_la_requete`,`Perform_action`,`Criticite`,`Message_alarme`,`Instructions`,`Intervalle_de_polling`,`Ref_Service`,`Objet`,`Monitored_By`,`Nom_Template`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					int batchSize_tDBOutput_1 = 100;
					int batchSizeCounter_tDBOutput_1 = 0;

					java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1
							.prepareStatement(insert_tDBOutput_1);
					resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);

					/**
					 * [tDBOutput_1 begin ] stop
					 */

					/**
					 * [tMap_1 begin ] start
					 */

					ok_Hash.put("tMap_1", false);
					start_Hash.put("tMap_1", System.currentTimeMillis());

					currentComponent = "tMap_1";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
					}

					int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
					class Var__tMap_1__Struct {
					}
					Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
					insertttStruct inserttt_tmp = new insertttStruct();
// ###############################

					/**
					 * [tMap_1 begin ] stop
					 */

					/**
					 * [tJavaRow_2 begin ] start
					 */

					ok_Hash.put("tJavaRow_2", false);
					start_Hash.put("tJavaRow_2", System.currentTimeMillis());

					currentComponent = "tJavaRow_2";

					if (execStat) {
						runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
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
							.decryptPassword("enc:routine.encryption.key.v1:j/0d2/u4c1dXt1GsXqAGueHxdXjsm/Ub42wkcA==");
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
						sheetList_tFileInputExcel_1.addAll(regexUtil_tFileInputExcel_1
								.getSheets(workbook_tFileInputExcel_1, "RequêtesSQL", false));
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
								int tempRowLength_tFileInputExcel_1 = 17;

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
										curColName_tFileInputExcel_1 = "Ref__des_composants";

										row1.Ref__des_composants = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Ref__des_composants = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 4;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "RG_SG_si_Cluster";

										row1.RG_SG_si_Cluster = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.RG_SG_si_Cluster = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 5;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Requete_SQL";

										row1.Requete_SQL = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Requete_SQL = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 6;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "UserName_DB_Name";

										row1.UserName_DB_Name = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.UserName_DB_Name = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 7;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Resultat_Attendu_de_la_requete";

										row1.Resultat_Attendu_de_la_requete = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Resultat_Attendu_de_la_requete = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 8;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Perform_action";

										row1.Perform_action = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Perform_action = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 9;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Criticite";

										row1.Criticite = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Criticite = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 10;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Message_d_alarme";

										row1.Message_d_alarme = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Message_d_alarme = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 11;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Instructions";

										row1.Instructions = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Instructions = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 12;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Intervalle_de_polling";

										row1.Intervalle_de_polling = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Intervalle_de_polling = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 13;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Ref__Service";

										row1.Ref__Service = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Ref__Service = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 14;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Objet";

										row1.Objet = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Objet = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 15;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Monitored_By";

										row1.Monitored_By = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Monitored_By = null;
										emptyColumnCount_tFileInputExcel_1++;
									}
									columnIndex_tFileInputExcel_1 = 16;

									if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
										curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
												+ start_column_tFileInputExcel_1 + 1;
										curColName_tFileInputExcel_1 = "Nom_Template";

										row1.Nom_Template = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
									} else {
										row1.Nom_Template = null;
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

									row2 = null;
									Operator_tFilterRow_1 ope_tFilterRow_1 = new Operator_tFilterRow_1("&&");
									ope_tFilterRow_1.matches((row1.N__Ref != null), "N__Ref!=null failed");

									if (ope_tFilterRow_1.getMatchFlag()) {
										if (row2 == null) {
											row2 = new row2Struct();
										}
										row2.N__Ref = row1.N__Ref;
										row2.Ref = row1.Ref;
										row2.Etat = row1.Etat;
										row2.Ref__des_composants = row1.Ref__des_composants;
										row2.RG_SG_si_Cluster = row1.RG_SG_si_Cluster;
										row2.Requete_SQL = row1.Requete_SQL;
										row2.UserName_DB_Name = row1.UserName_DB_Name;
										row2.Resultat_Attendu_de_la_requete = row1.Resultat_Attendu_de_la_requete;
										row2.Perform_action = row1.Perform_action;
										row2.Criticite = row1.Criticite;
										row2.Message_d_alarme = row1.Message_d_alarme;
										row2.Instructions = row1.Instructions;
										row2.Intervalle_de_polling = row1.Intervalle_de_polling;
										row2.Ref__Service = row1.Ref__Service;
										row2.Objet = row1.Objet;
										row2.Monitored_By = row1.Monitored_By;
										row2.Nom_Template = row1.Nom_Template;
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
// Start of branch "row2"
									if (row2 != null) {

										/**
										 * [tJavaRow_2 main ] start
										 */

										currentComponent = "tJavaRow_2";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row2"

											);
										}

										String fileName = (String) source_tFileInputExcel_1;

// Extraire la partie "POSANET" du nom du fichier Excel
										String[] parts = fileName.split("_");
										String fileWord = parts[6];

// Récupérer la valeur de "N__Ref" et la convertir en String
										String rowRef = String.valueOf(row2.N__Ref);

// Générer l'ID "EDB_POSANET_X"
										String id = "EDB_" + fileWord + "_" + rowRef;

// Copier les valeurs des différentes colonnes de row2 vers row3
										row3.N__Ref = id;
										row3.Ref = row2.Ref;
										row3.Etat = row2.Etat;
										row3.Ref__des_composants = row2.Ref__des_composants;
										row3.RG_SG_si_Cluster = row2.RG_SG_si_Cluster;
										row3.Requete_SQL = row2.Requete_SQL;
										row3.UserName_DB_Name = row2.UserName_DB_Name;
										row3.Resultat_Attendu_de_la_requete = row2.Resultat_Attendu_de_la_requete;
										row3.Perform_action = row2.Perform_action;
										row3.Criticite = row2.Criticite;
										row3.Message_d_alarme = row2.Message_d_alarme;
										row3.Instructions = row2.Instructions;
										row3.Intervalle_de_polling = row2.Intervalle_de_polling;
										row3.Ref__Service = row2.Ref__Service;
										row3.Objet = row2.Objet;
										row3.Monitored_By = row2.Monitored_By;
										row3.Nom_Template = row2.Nom_Template;

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
										 * [tMap_1 main ] start
										 */

										currentComponent = "tMap_1";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "row3"

											);
										}

										boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

										// ###############################
										// # Input tables (lookups)
										boolean rejectedInnerJoin_tMap_1 = false;
										boolean mainRowRejected_tMap_1 = false;

										// ###############################
										{ // start of Var scope

											// ###############################
											// # Vars tables

											Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
											// ###############################
											// # Output tables

											inserttt = null;

// # Output table : 'inserttt'
											inserttt_tmp.id = row3.N__Ref;
											inserttt_tmp.Ref = row3.Ref;
											inserttt_tmp.Etat = row3.Etat;
											inserttt_tmp.Ref_composant = row3.Ref__des_composants;
											inserttt_tmp.RG_SG_si_Cluster = row3.RG_SG_si_Cluster;
											inserttt_tmp.Requete_SQL = row3.Requete_SQL;
											inserttt_tmp.UserName_DB_Name = row3.UserName_DB_Name;
											inserttt_tmp.Resultat_Attendu_de_la_requete = row3.Resultat_Attendu_de_la_requete;
											inserttt_tmp.Perform_action = row3.Perform_action;
											inserttt_tmp.Criticite = row3.Criticite;
											inserttt_tmp.Message_alarme = row3.Message_d_alarme;
											inserttt_tmp.Instructions = row3.Instructions;
											inserttt_tmp.Intervalle_de_polling = row3.Intervalle_de_polling;
											inserttt_tmp.Ref_Service = row3.Ref__Service;
											inserttt_tmp.Objet = row3.Objet;
											inserttt_tmp.Monitored_By = row3.Monitored_By;
											inserttt_tmp.Nom_Template = row3.Nom_Template;
											inserttt = inserttt_tmp;
// ###############################

										} // end of Var scope

										rejectedInnerJoin_tMap_1 = false;

										tos_count_tMap_1++;

										/**
										 * [tMap_1 main ] stop
										 */

										/**
										 * [tMap_1 process_data_begin ] start
										 */

										currentComponent = "tMap_1";

										/**
										 * [tMap_1 process_data_begin ] stop
										 */
// Start of branch "inserttt"
										if (inserttt != null) {

											/**
											 * [tDBOutput_1 main ] start
											 */

											currentComponent = "tDBOutput_1";

											if (execStat) {
												runStat.updateStatOnConnection(iterateId, 1, 1

														, "inserttt"

												);
											}

											whetherReject_tDBOutput_1 = false;
											if (inserttt.id == null) {
												pstmt_tDBOutput_1.setNull(1, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(1, inserttt.id);
											}

											if (inserttt.Ref == null) {
												pstmt_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(2, inserttt.Ref);
											}

											if (inserttt.Etat == null) {
												pstmt_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(3, inserttt.Etat);
											}

											if (inserttt.Ref_composant == null) {
												pstmt_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(4, inserttt.Ref_composant);
											}

											if (inserttt.RG_SG_si_Cluster == null) {
												pstmt_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(5, inserttt.RG_SG_si_Cluster);
											}

											if (inserttt.Requete_SQL == null) {
												pstmt_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(6, inserttt.Requete_SQL);
											}

											if (inserttt.UserName_DB_Name == null) {
												pstmt_tDBOutput_1.setNull(7, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(7, inserttt.UserName_DB_Name);
											}

											if (inserttt.Resultat_Attendu_de_la_requete == null) {
												pstmt_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(8, inserttt.Resultat_Attendu_de_la_requete);
											}

											if (inserttt.Perform_action == null) {
												pstmt_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(9, inserttt.Perform_action);
											}

											if (inserttt.Criticite == null) {
												pstmt_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(10, inserttt.Criticite);
											}

											if (inserttt.Message_alarme == null) {
												pstmt_tDBOutput_1.setNull(11, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(11, inserttt.Message_alarme);
											}

											if (inserttt.Instructions == null) {
												pstmt_tDBOutput_1.setNull(12, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(12, inserttt.Instructions);
											}

											if (inserttt.Intervalle_de_polling == null) {
												pstmt_tDBOutput_1.setNull(13, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(13, inserttt.Intervalle_de_polling);
											}

											if (inserttt.Ref_Service == null) {
												pstmt_tDBOutput_1.setNull(14, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(14, inserttt.Ref_Service);
											}

											if (inserttt.Objet == null) {
												pstmt_tDBOutput_1.setNull(15, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(15, inserttt.Objet);
											}

											if (inserttt.Monitored_By == null) {
												pstmt_tDBOutput_1.setNull(16, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(16, inserttt.Monitored_By);
											}

											if (inserttt.Nom_Template == null) {
												pstmt_tDBOutput_1.setNull(17, java.sql.Types.VARCHAR);
											} else {
												pstmt_tDBOutput_1.setString(17, inserttt.Nom_Template);
											}

											pstmt_tDBOutput_1.addBatch();
											nb_line_tDBOutput_1++;

											batchSizeCounter_tDBOutput_1++;
											if (!whetherReject_tDBOutput_1) {
											}
											if (batchSize_tDBOutput_1 <= batchSizeCounter_tDBOutput_1) {
												try {
													int countSum_tDBOutput_1 = 0;
													for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
														countSum_tDBOutput_1 += (countEach_tDBOutput_1 == java.sql.Statement.EXECUTE_FAILED
																? 0
																: 1);
													}
													rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
													insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
												} catch (java.sql.BatchUpdateException e) {
													globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
													int countSum_tDBOutput_1 = 0;
													for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
														countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0
																: countEach_tDBOutput_1);
													}
													rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
													insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
													System.err.println(e.getMessage());
												}

												batchSizeCounter_tDBOutput_1 = 0;
											}
											commitCounter_tDBOutput_1++;

											if (commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {

												try {
													int countSum_tDBOutput_1 = 0;
													for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
														countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : 1);
													}
													rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
													insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
												} catch (java.sql.BatchUpdateException e) {
													globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
													int countSum_tDBOutput_1 = 0;
													for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
														countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0
																: countEach_tDBOutput_1);
													}
													rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
													insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
													System.err.println(e.getMessage());

												}
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

										} // End of branch "inserttt"

										/**
										 * [tMap_1 process_data_end ] start
										 */

										currentComponent = "tMap_1";

										/**
										 * [tMap_1 process_data_end ] stop
										 */

										/**
										 * [tJavaRow_2 process_data_end ] start
										 */

										currentComponent = "tJavaRow_2";

										/**
										 * [tJavaRow_2 process_data_end ] stop
										 */

									} // End of branch "row2"

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
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
					}

					ok_Hash.put("tJavaRow_2", true);
					end_Hash.put("tJavaRow_2", System.currentTimeMillis());

					/**
					 * [tJavaRow_2 end ] stop
					 */

					/**
					 * [tMap_1 end ] start
					 */

					currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      

					if (execStat) {
						runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
					}

					ok_Hash.put("tMap_1", true);
					end_Hash.put("tMap_1", System.currentTimeMillis());

					/**
					 * [tMap_1 end ] stop
					 */

					/**
					 * [tDBOutput_1 end ] start
					 */

					currentComponent = "tDBOutput_1";

					try {
						if (batchSizeCounter_tDBOutput_1 != 0) {
							int countSum_tDBOutput_1 = 0;

							for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
								countSum_tDBOutput_1 += (countEach_tDBOutput_1 == java.sql.Statement.EXECUTE_FAILED ? 0
										: 1);
							}
							rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

							insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

						}

					} catch (java.sql.BatchUpdateException e) {
						globalMap.put(currentComponent + "_ERROR_MESSAGE", e.getMessage());

						int countSum_tDBOutput_1 = 0;
						for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
							countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
						}
						rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

						insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

						System.err.println(e.getMessage());

					}
					batchSizeCounter_tDBOutput_1 = 0;

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
						runStat.updateStat(resourceMap, iterateId, 2, 0, "inserttt");
					}

					ok_Hash.put("tDBOutput_1", true);
					end_Hash.put("tDBOutput_1", System.currentTimeMillis());

					if (execStat) {
						runStat.updateStatOnConnection("OnComponentOk1", 0, "ok");
					}
					tFileCopy_1Process(globalMap);

					/**
					 * [tDBOutput_1 end ] stop
					 */

					if (execStat) {
						runStat.updateStatOnConnection("iterate2", 2, "exec" + NB_ITERATE_tFileInputExcel_1);
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
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
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

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileList_1_SUBPROCESS_STATE", 1);
	}

	public void tFileCopy_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileCopy_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

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

				/**
				 * [tFileCopy_1 begin ] start
				 */

				ok_Hash.put("tFileCopy_1", false);
				start_Hash.put("tFileCopy_1", System.currentTimeMillis());

				currentComponent = "tFileCopy_1";

				int tos_count_tFileCopy_1 = 0;

				/**
				 * [tFileCopy_1 begin ] stop
				 */

				/**
				 * [tFileCopy_1 main ] start
				 */

				currentComponent = "tFileCopy_1";

				String srcFileName_tFileCopy_1 = ((String) globalMap.get("tFileList_1_CURRENT_FILEPATH"));

				java.io.File srcFile_tFileCopy_1 = new java.io.File(srcFileName_tFileCopy_1);

				// here need check first, before mkdirs().
				if (!srcFile_tFileCopy_1.exists() || !srcFile_tFileCopy_1.isFile()) {
					String errorMessageFileDoesnotExistsOrIsNotAFile_tFileCopy_1 = String
							.format("The source File \"%s\" does not exist or is not a file.", srcFileName_tFileCopy_1);
					System.err.println(errorMessageFileDoesnotExistsOrIsNotAFile_tFileCopy_1);
					globalMap.put("tFileCopy_1_ERROR_MESSAGE", errorMessageFileDoesnotExistsOrIsNotAFile_tFileCopy_1);
				}
				String desDirName_tFileCopy_1 = "C:/Users/Majdi/Downloads/fait";

				String desFileName_tFileCopy_1 = srcFile_tFileCopy_1.getName();

				if (desFileName_tFileCopy_1 != null && ("").equals(desFileName_tFileCopy_1.trim())) {
					desFileName_tFileCopy_1 = "NewName.temp";
				}

				java.io.File desFile_tFileCopy_1 = new java.io.File(desDirName_tFileCopy_1, desFileName_tFileCopy_1);

				if (!srcFile_tFileCopy_1.getPath().equals(desFile_tFileCopy_1.getPath())) {
					java.io.File parentFile_tFileCopy_1 = desFile_tFileCopy_1.getParentFile();

					if (parentFile_tFileCopy_1 != null && !parentFile_tFileCopy_1.exists()) {
						parentFile_tFileCopy_1.mkdirs();
					}
					try {
						org.talend.FileCopy.copyFile(srcFile_tFileCopy_1.getPath(), desFile_tFileCopy_1.getPath(),
								false, false);
					} catch (Exception e) {
						globalMap.put("tFileCopy_1_ERROR_MESSAGE", e.getMessage());
						System.err.println("tFileCopy_1 " + e.getMessage());
					}

				}
				globalMap.put("tFileCopy_1_DESTINATION_FILEPATH", desFile_tFileCopy_1.getPath());
				globalMap.put("tFileCopy_1_DESTINATION_FILENAME", desFile_tFileCopy_1.getName());

				globalMap.put("tFileCopy_1_SOURCE_DIRECTORY", srcFile_tFileCopy_1.getParent());
				globalMap.put("tFileCopy_1_DESTINATION_DIRECTORY", desFile_tFileCopy_1.getParent());

				tos_count_tFileCopy_1++;

				/**
				 * [tFileCopy_1 main ] stop
				 */

				/**
				 * [tFileCopy_1 process_data_begin ] start
				 */

				currentComponent = "tFileCopy_1";

				/**
				 * [tFileCopy_1 process_data_begin ] stop
				 */

				/**
				 * [tFileCopy_1 process_data_end ] start
				 */

				currentComponent = "tFileCopy_1";

				/**
				 * [tFileCopy_1 process_data_end ] stop
				 */

				/**
				 * [tFileCopy_1 end ] start
				 */

				currentComponent = "tFileCopy_1";

				ok_Hash.put("tFileCopy_1", true);
				end_Hash.put("tFileCopy_1", System.currentTimeMillis());

				/**
				 * [tFileCopy_1 end ] stop
				 */
			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileCopy_1 finally ] start
				 */

				currentComponent = "tFileCopy_1";

				/**
				 * [tFileCopy_1 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileCopy_1_SUBPROCESS_STATE", 1);
	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_AAAAAAAAAAA_exemple_bd = new byte[0];
		static byte[] commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[0];

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
				if (length > commonByteArray_AAAAAAAAAAA_exemple_bd.length) {
					if (length < 1024 && commonByteArray_AAAAAAAAAAA_exemple_bd.length == 0) {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[1024];
					} else {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length);
				strReturn = new String(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length, utf8Charset);
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
				if (length > commonByteArray_AAAAAAAAAAA_exemple_bd.length) {
					if (length < 1024 && commonByteArray_AAAAAAAAAAA_exemple_bd.length == 0) {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[1024];
					} else {
						commonByteArray_AAAAAAAAAAA_exemple_bd = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length);
				strReturn = new String(commonByteArray_AAAAAAAAAAA_exemple_bd, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_AAAAAAAAAAA_exemple_bd) {

				try {

					int length = 0;

					this.filepath = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_AAAAAAAAAAA_exemple_bd) {

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

				row4Struct row4 = new row4Struct();

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
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

					row4.filepath = row_tIterateToFlow_1_AI.filepath;

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

								, "row4"

						);
					}

///////////////////////		

					String[] row_tLogRow_1 = new String[1];

					if (row4.filepath != null) { //
						row_tLogRow_1[0] = String.valueOf(row4.filepath);

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
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
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
		final exemple_bd exemple_bdClass = new exemple_bd();

		int exitCode = exemple_bdClass.runJobInTOS(args);

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
			java.io.InputStream inContext = exemple_bd.class.getClassLoader()
					.getResourceAsStream("aaaaaaaaaaa/exemple_bd_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = exemple_bd.class.getClassLoader()
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

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : exemple_bd");
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
 * 158154 characters generated by Talend Open Studio for Data Integration on the
 * 28 juillet 2023 à 3:08:09 PM WAT
 ************************************************************************************************/
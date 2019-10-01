/*
 * #%L
 * OME Metadata Converter (OME-XML -> OME-OWL)
 * %%
 * Copyright (C) 2019 RIKEN and Open Microscopy Environment:
 *   - Board of Regents of the University of Wisconsin-Madison
 *   - Glencoe Software, Inc.
 *   - University of Dundee
 *
 * 
 * 
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */


package jp.riken.isc.dko.ome.metadataConverter;

import java.util.Map;
import java.util.HashMap;

public class URISet {

// -------------------------------------------------------------------------------------------------------------------
//  Namespaces
//-------------------------------------------------------------------------------------------------------------------

	// OME TOPLEVEL NAMESPAES
	static final public String OME_201606_NAMESPACE = "http://openmicroscopy.org/rdf/2016-06/ome_core/ontology/";

	static final public String OME_201606_UNIT_NAMESPACE = "http://openmicroscopy.org/rdf/2016-06/ome_core/ontology/Unit/";

	static final public String OME_201606_ANGLE_UNIT_NAMESPACE = OME_201606_UNIT_NAMESPACE + "Angle#";
	static final public String OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE = OME_201606_UNIT_NAMESPACE
			+ "ElectricPotential#";
	static final public String OME_201606_FREQUENCY_UNIT_NAMESPACE = OME_201606_UNIT_NAMESPACE + "Frequency#";
	static final public String OME_201606_LENGTH_UNIT_NAMESPACE = OME_201606_UNIT_NAMESPACE + "Length#";
	static final public String OME_201606_POWER_UNIT_NAMESPACE = OME_201606_UNIT_NAMESPACE + "Power#";
	static final public String OME_201606_PRESSURE_UNIT_NAMESPACE = OME_201606_UNIT_NAMESPACE + "Pressure#";
	static final public String OME_201606_TIME_UNIT_NAMESPACE = OME_201606_UNIT_NAMESPACE + "Time#";
	static final public String OME_201606_TEMPERATURE_UNIT_NAMESPACE = OME_201606_UNIT_NAMESPACE + "Temperature#";

	static final public String RDF_NAMESPACE = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	static final public String RDFS_NAMESPACE = "http://www.w3.org/2000/01/rdf-schema#";
	static final public String DCTERMS_NAMESPACE = "http://purl.org/dc/terms/";
	static final public String FOAF_NAMESPACE = "http://xmlns.com/foaf/0.1/";
	static final public String XSD_NAMESPACE = "http://www.w3.org/2001/XMLSchema#";
	static final public String QUDT_NAMESPACE = "http://qudt.org/schema/qudt#";
	static final public String PATO_NAMESPACE = "http://purl.obolibrary.org/obo/PATO_";
	static final public String SBO_NAMESPACE = "http://purl.obolibrary.org/obo/SBO_";

	// PREFIX for Jena
	static public Map<String, String> prefixMap = new HashMap<String, String>() {
		{
			put("ome", OME_201606_NAMESPACE);
			put("rdf", RDF_NAMESPACE);
			put("rdfs", RDFS_NAMESPACE);
			put("xsd", XSD_NAMESPACE);
			put("foaf", FOAF_NAMESPACE);
			put("dcterms", DCTERMS_NAMESPACE);
			put("qudt", QUDT_NAMESPACE);
			put("PATO", PATO_NAMESPACE);
			put("SBO", SBO_NAMESPACE);
			put("angleUnit", OME_201606_ANGLE_UNIT_NAMESPACE);
			put("electricPotentialUnit", OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE);
			put("frequencyUnit", OME_201606_FREQUENCY_UNIT_NAMESPACE);
			put("lengthUnit", OME_201606_LENGTH_UNIT_NAMESPACE);
			put("powerUnit", OME_201606_POWER_UNIT_NAMESPACE);
			put("pressurehUnit", OME_201606_PRESSURE_UNIT_NAMESPACE);
			put("timeUnit", OME_201606_TIME_UNIT_NAMESPACE);
			put("temperatureUnit", OME_201606_TEMPERATURE_UNIT_NAMESPACE);
		}
	};

// -------------------------------------------------------------------------------------------------------------------
//  Class, Datatype and Property URIs
//-------------------------------------------------------------------------------------------------------------------

	// RDF classes, datatypes and properties
	static final public String RDF_TYPE_PROERTY_URI = RDF_NAMESPACE + "type";
	static final public String RDF_VALUE_PROERTY_URI = RDF_NAMESPACE + "value";
	static final public String RDF_LANGSTRING_DATATYPE_URI = RDF_NAMESPACE + "langString";
	static final public String RDF_XMLLITERAL_DATATYPE_URI = RDF_NAMESPACE + "XMLLiteral";
	static final public String RDF_BAG_CLASS_URI = RDF_NAMESPACE + "Bag";

	// RDFs classes, datatypes and properties
	static final public String RDFS_LABEL_PROPERTY_URI = RDFS_NAMESPACE + "label";
	static final public String RDFS_COMMENT_PROPERTY_URI = RDFS_NAMESPACE + "comment";

	// DublinCore classes, datatypes and properties
	static final public String DCTERMS_IDENTIFIER_PROPERTY_URI = DCTERMS_NAMESPACE + "identifier";

	// Friend of a Friend (FOAF) classes, datatypes and properties
	static final public String FOAF_PERSON_CLASS_URI = FOAF_NAMESPACE + "Person";
	static final public String FOAF_GIVENNAME_PROPERTY_URI = FOAF_NAMESPACE + "givenName";
	static final public String FOAF_FAMILYNAME_PROPERTY_URI = FOAF_NAMESPACE + "familyName";
	static final public String FOAF_MIDDLENAME_PROPERTY_URI = FOAF_NAMESPACE + "middleName";
	static final public String FOAF_MBOX_PROPERTY_URI = FOAF_NAMESPACE + "mbox";
	static final public String FOAF_ACCOUNTNAME_PROPERTY_URI = FOAF_NAMESPACE + "accountName";
	// missing in FOAF
	static final public String OME_INSTITUTION_PROPERTY_URI = OME_201606_NAMESPACE + "institution";

	// XSD datatypes
	static final public String XSD_DATETIME_DATATYPE_URI = XSD_NAMESPACE + "dateTime";
	static final public String XSD_POSITIVEINTEGER_DATATYPE_URI = XSD_NAMESPACE + "positiveInteger";
	static final public String XSD_BASE64BINARY_DATATYPE_URI = XSD_NAMESPACE + "base64Binary";
	static final public String XSD_NONNEGATIVELONG_DATATYPE_URI = XSD_NAMESPACE + "nonNegativeLong";
	static final public String XSD_NONNEGATIVEINTEGER_DATATYPE_URI = XSD_NAMESPACE + "nonNegativeInteger";
	static final public String XSD_INTEGER_DATATYPE_URI = XSD_NAMESPACE + "integer";

	// QUDT properties
	static final public String QUDT_UNIT_PROPERTY_URI = QUDT_NAMESPACE + "unit";

	// OME
	static final public String OME_A00_PROPERTY_URI = OME_201606_NAMESPACE + "a00";
	static final public String OME_A01_PROPERTY_URI = OME_201606_NAMESPACE + "a01";
	static final public String OME_A02_PROPERTY_URI = OME_201606_NAMESPACE + "a02";
	static final public String OME_A10_PROPERTY_URI = OME_201606_NAMESPACE + "a10";
	static final public String OME_A11_PROPERTY_URI = OME_201606_NAMESPACE + "a11";
	static final public String OME_A12_PROPERTY_URI = OME_201606_NAMESPACE + "a12";
	static final public String OME_ACQUISITIONDATE_PROPERTY_URI = OME_201606_NAMESPACE + "acquisitionDate";
	static final public String OME_ACQUISITIONMODE_CLASS_URI = OME_201606_NAMESPACE + "AcquisitionMode";
	static final public String OME_ACQUISITIONMODE_PROPERTY_URI = OME_201606_NAMESPACE + "acquisitionMode";
	static final public String OME_AFFINETRANSFORM_CLASS_URI = OME_201606_NAMESPACE + "AffineTransform";
	static final public String OME_AIRPRESSURE_PROPERTY_URI = OME_201606_NAMESPACE + "airPressure";
	static final public String OME_AMPLIFICATIONGAIN_PROPERTY_URI = OME_201606_NAMESPACE + "amplificationGain";
	static final public String OME_ANNOTATION_PROPERTY_URI = OME_201606_NAMESPACE + "annotation";
	static final public String OME_ANNOTATOR_PROPERTY_URI = OME_201606_NAMESPACE + "annotator";
	static final public String OME_ARC_CLASS_URI = OME_201606_NAMESPACE + "Arc";
	static final public String OME_ARCTYPE_CLASS_URI = OME_201606_NAMESPACE + "ArcType";
	static final public String OME_ARCTYPE_PROPERTY_URI = OME_201606_NAMESPACE + "arcType";
	static final public String OME_ATTENUATION_PROPERTY_URI = OME_201606_NAMESPACE + "attenuation";

	static final public String OME_BIGENDIAN_PROPERTY_URI = OME_201606_NAMESPACE + "bigEndian";
	static final public String OME_BINARYFILE_CLASS_URI = OME_201606_NAMESPACE + "BinaryFile";
	static final public String OME_BINARYONLY_CLASS_URI = OME_201606_NAMESPACE + "BinaryOnly";
	static final public String OME_BINDATA_CLASS_URI = OME_201606_NAMESPACE + "BinData";
	static final public String OME_BINDATA_PROPERTY_URI = OME_201606_NAMESPACE + "binData";
	static final public String OME_BINNING_CLASS_URI = OME_201606_NAMESPACE + "Binning";
	static final public String OME_BINNING_PROPERTY_URI = OME_201606_NAMESPACE + "binning";
	static final public String OME_BOOLEANANNOTATION_CLASS_URI = OME_201606_NAMESPACE + "BooleanAnnotation";

	static final public String OME_CALIBRATEDMAGNIFICATION_PROPERTY_URI = OME_201606_NAMESPACE
			+ "calibratedMagnification";
	static final public String OME_CHANNEL_CLASS_URI = OME_201606_NAMESPACE + "Channel";
	static final public String OME_CHANNEL_PROPERTY_URI = OME_201606_NAMESPACE + "channel";
	static final public String OME_CO2PERCENT_PROPERTY_URI = OME_201606_NAMESPACE + "co2Percent";
	static final public String OME_COLOR_CLASS_URI = OME_201606_NAMESPACE + "Color";
	static final public String OME_COLOR_PROPERTY_URI = OME_201606_NAMESPACE + "color";
	static final public String OME_COLUMN_PROPERTY_URI = OME_201606_NAMESPACE + "column";
	static final public String OME_COLUMNNAMINGCONVENTION_PROPERTY_URI = OME_201606_NAMESPACE
			+ "columnNamingConvention";
	static final public String OME_COLUMNS_PROPERTY_URI = OME_201606_NAMESPACE + "columns";
	static final public String OME_COMMENTANNOTATION_CLASS_URI = OME_201606_NAMESPACE + "CommentAnnotation";
	static final public String OME_COMPRESSION_CLASS_URI = OME_201606_NAMESPACE + "Compression";
	static final public String OME_COMPRESSION_PROPERTY_URI = OME_201606_NAMESPACE + "compression";
	static final public String OME_CONTRASTMETHOD_CLASS_URI = OME_201606_NAMESPACE + "ContrastMethod";
	static final public String OME_CONTRASTMETHOD_PROPERTY_URI = OME_201606_NAMESPACE + "contrastMethod";
	static final public String OME_CORRECTION_CLASS_URI = OME_201606_NAMESPACE + "Correction";
	static final public String OME_CORRECTION_PROPERTY_URI = OME_201606_NAMESPACE + "correction";
	static final public String OME_CORRECTIONCOLLAR_PROPERTY_URI = OME_201606_NAMESPACE + "correctionCollar";
	static final public String OME_CUTIN_PROPERTY_URI = OME_201606_NAMESPACE + "cutIn";
	static final public String OME_CUTINTOLERANCE_PROPERTY_URI = OME_201606_NAMESPACE + "cutInTolerance";
	static final public String OME_CUTOUT_PROPERTY_URI = OME_201606_NAMESPACE + "cutOut";
	static final public String OME_CUTOUTTOLERANCE_PROPERTY_URI = OME_201606_NAMESPACE + "cutOutTolerance";

	static final public String OME_DATA_PROPERTY_URI = OME_201606_NAMESPACE + "data";
	static final public String OME_DATASET_CLASS_URI = OME_201606_NAMESPACE + "Dataset";
	static final public String OME_DATASET_PROPERTY_URI = OME_201606_NAMESPACE + "dataset";
	static final public String OME_DELTAT_PROPERTY_URI = OME_201606_NAMESPACE + "deltaT";
	static final public String OME_DETECTOR_CLASS_URI = OME_201606_NAMESPACE + "Detector";
	static final public String OME_DETECTOR_PROPERTY_URI = OME_201606_NAMESPACE + "detector";
	static final public String OME_DETECTORSETTINGS_CLASS_URI = OME_201606_NAMESPACE + "DetectorSettings";
	static final public String OME_DETECTORSETTINGS_PROPERTY_URI = OME_201606_NAMESPACE + "detectorSettings";
	static final public String OME_DETECTORTYPE_CLASS_URI = OME_201606_NAMESPACE + "DetectorType";
	static final public String OME_DETECTORTYPE_PROPERTY_URI = OME_201606_NAMESPACE + "detectorType";
	static final public String OME_DICHROIC_CLASS_URI = OME_201606_NAMESPACE + "Dichroic";
	static final public String OME_DICHROIC_PROPERTY_URI = OME_201606_NAMESPACE + "dichroic";
	static final public String OME_DIMENSIONORDER_CLASS_URI = OME_201606_NAMESPACE + "DimensionOrder";
	static final public String OME_DIMENSIONORDER_PROPERTY_URI = OME_201606_NAMESPACE + "dimensionOrder";
	static final public String OME_DOUBLEANNOTATION_CLASS_URI = OME_201606_NAMESPACE + "DoubleAnnotation";

	static final public String OME_ELLIPSE_CLASS_URI = OME_201606_NAMESPACE + "Ellipse";
	static final public String OME_EMISSIONFILTER_PROPERTY_URI = OME_201606_NAMESPACE + "emissionFilter";
	static final public String OME_EMISSIONWAVELENGTH_PROPERTY_URI = OME_201606_NAMESPACE + "emissionWavelength";
	static final public String OME_ENDTIME_PROPERTY_URI = OME_201606_NAMESPACE + "endTime";
	static final public String OME_EXCITATIONFILTER_PROPERTY_URI = OME_201606_NAMESPACE + "excitationFilter";
	static final public String OME_EXCITATIONWAVELENGTH_PROPERTY_URI = OME_201606_NAMESPACE + "excitationWavelength";
	static final public String OME_EXPERIMENT_CLASS_URI = OME_201606_NAMESPACE + "Experiment";
	static final public String OME_EXPERIMENT_PROPERTY_URI = OME_201606_NAMESPACE + "experiment";
	static final public String OME_EXPERIMENTER_PROPERTY_URI = OME_201606_NAMESPACE + "experimenter";
	static final public String OME_EXPERIMENTERGROUP_CLASS_URI = OME_201606_NAMESPACE + "ExperimenterGroup";
	static final public String OME_EXPERIMENTERGROUP_PROPERTY_URI = OME_201606_NAMESPACE + "experimenterGroup";
	static final public String OME_EXPERIMENTTYPE_CLASS_URI = OME_201606_NAMESPACE + "ExperimentType";
	static final public String OME_EXPERIMENTTYPE_PROPERTY_URI = OME_201606_NAMESPACE + "experimentType";
	static final public String OME_EXPOSURETIME_PROPERTY_URI = OME_201606_NAMESPACE + "exposureTIme";
	static final public String OME_EXTERNAL_CLASS_URI = OME_201606_NAMESPACE + "External";
	static final public String OME_EXTERNAL_PROPERTY_URI = OME_201606_NAMESPACE + "external";
	static final public String OME_EXTERNALIDENTIFIER_PROPERTY_URI = OME_201606_NAMESPACE + "externalIdentifier";
	static final public String OME_EXTERNALDESCRIPTION_PROPERTY_URI = OME_201606_NAMESPACE + "externalDescription";
	static final public String OME_EXERNALIDENTIFIER_PROPERTY_URI = OME_201606_NAMESPACE + "externalIdentifier";

	static final public String OME_FIELDINDEX_PROPERTY_URI = OME_201606_NAMESPACE + "fieldIndex";
	static final public String OME_FILAMENT_CLASS_URI = OME_201606_NAMESPACE + "filament";
	static final public String OME_FILAMENTTYPE_CLASS_URI = OME_201606_NAMESPACE + "FilamentType";
	static final public String OME_FILAMENTTYPE_PROPERTY_URI = OME_201606_NAMESPACE + "filamentType";
	static final public String OME_FILEANNOTATION_CLASS_URI = OME_201606_NAMESPACE + "FileAnnotation";
	static final public String OME_FILLCOLOR_PROPERTY_URI = OME_201606_NAMESPACE + "fillColor";
	static final public String OME_FILLRULE_CLASS_URI = OME_201606_NAMESPACE + "FillRule";
	static final public String OME_FILLRULE_PROPERTY_URI = OME_201606_NAMESPACE + "fillRule";
	static final public String OME_FILTER_CLASS_URI = OME_201606_NAMESPACE + "Filter";
	static final public String OME_FILTER_PROPERTY_URI = OME_201606_NAMESPACE + "filter";
	static final public String OME_FILTERSET_CLASS_URI = OME_201606_NAMESPACE + "FilterSet";
	static final public String OME_FILTERSET_PROPERTY_URI = OME_201606_NAMESPACE + "filterSet";
	static final public String OME_FILTERTYPE_CLASS_URI = OME_201606_NAMESPACE + "FilterType";
	static final public String OME_FILTERTYPE_PROPERTY_URI = OME_201606_NAMESPACE + "filterType";
	static final public String OME_FILTERWHEEL_PROPERTY_URI = OME_201606_NAMESPACE + "filterWheel";
	static final public String OME_FIRSTC_PROPERTY_URI = OME_201606_NAMESPACE + "firstC";
	static final public String OME_FIRSTT_PROPERTY_URI = OME_201606_NAMESPACE + "firstT";
	static final public String OME_FIRSTZ_PROPERTY_URI = OME_201606_NAMESPACE + "firstZ";
	static final public String OME_FLUOR_PROPERTY_URI = OME_201606_NAMESPACE + "fluor";
	static final public String OME_FOLDER_CLASS_URI = OME_201606_NAMESPACE + "Folder";
	static final public String OME_FOLDER_PROPERTY_URI = OME_201606_NAMESPACE + "folder";
	static final public String OME_FONT_CLASS_URI = OME_201606_NAMESPACE + "Font";
	static final public String OME_FONT_PROPERTY_URI = OME_201606_NAMESPACE + "font";
	static final public String OME_FONTFAMILY_CLASS_URI = OME_201606_NAMESPACE + "FontFamily";
	static final public String OME_FONTFAMILY_PROPERTY_URI = OME_201606_NAMESPACE + "fontFamily";
	static final public String OME_FONTSIZE_PROPERTY_URI = OME_201606_NAMESPACE + "fontSize";
	static final public String OME_FONTSTYLE_CLASS_URI = OME_201606_NAMESPACE + "FontStyle";
	static final public String OME_FONTSTYLE_PROPERTY_URI = OME_201606_NAMESPACE + "fontStyle";
	static final public String OME_FREQUENCYMULTIPLICATION_PROPERTY_URI = OME_201606_NAMESPACE
			+ "frequencyMultiplication";

	static final public String OME_GAIN_PROPERTY_URI = OME_201606_NAMESPACE + "gain";
	static final public String OME_GENERICEXCITATIONSOURCE_CLASS_URI = OME_201606_NAMESPACE + "genericExcitationSource";

	static final public String OME_HASHSHA1_PROPERTY_URI = OME_201606_NAMESPACE + "hashSHA1";
	static final public String OME_HEIGHT_PROPERTY_URI = OME_201606_NAMESPACE + "height";
	static final public String OME_HREF_PROPERTY_URI = OME_201606_NAMESPACE + "href";
	static final public String OME_HUMIDITY_PROPERTY_URI = OME_201606_NAMESPACE + "humidity";

	static final public String OME_IFD_PROPERTY_URI = OME_201606_NAMESPACE + "ifd";
	static final public String OME_ILLUMINATIONTYPE_CLASS_URI = OME_201606_NAMESPACE + "IlluminationType";
	static final public String OME_ILLUMINATIONTYPE_PROPERTY_URI = OME_201606_NAMESPACE + "illuminationType";
	static final public String OME_IMAGE_CLASS_URI = OME_201606_NAMESPACE + "Image";
	static final public String OME_IMAGE_PEOPERTY_URI = OME_201606_NAMESPACE + "image";
	static final public String OME_IMAGINGCONDITION_CLASS_URI = OME_201606_NAMESPACE + "ImagingCondition";
	static final public String OME_IMAGINGCONDITION_PROPERTY_URI = OME_201606_NAMESPACE + "imagingCondition";
	static final public String OME_IMAGINGENVIRONMENT_CLASS_URI = OME_201606_NAMESPACE + "ImagingEnvironment";
	static final public String OME_IMAGINGENVIRONMENT_PROPERTY_URI = OME_201606_NAMESPACE + "imagingEnvironment";
	static final public String OME_IMMERSION_CLASS_URI = OME_201606_NAMESPACE + "Immersion";
	static final public String OME_IMMERSION_PROPERTY_URI = OME_201606_NAMESPACE + "immersion";
	static final public String OME_INDEX_PROPERTY_URI = OME_201606_NAMESPACE + "index";
	static final public String OME_INSTRUMENT_CLASS_URI = OME_201606_NAMESPACE + "Instrument";
	static final public String OME_INSTRUMENT_PROPERTY_URI = OME_201606_NAMESPACE + "instrument";
	static final public String OME_INTEGRATION_PROPERTY_URI = OME_201606_NAMESPACE + "integration";
	static final public String OME_INTERLEAVED_PROPERTY_URI = OME_201606_NAMESPACE + "interleaved";
	static final public String OME_IRIS_PROPERTY_URI = OME_201606_NAMESPACE + "iris";

	static final public String OME_LABEL_CLASS_URI = OME_201606_NAMESPACE + "Label";
	static final public String OME_LASER_CLASS_URI = OME_201606_NAMESPACE + "Laser";
	static final public String OME_LASERMEDIUM_CLASS_URI = OME_201606_NAMESPACE + "LaserMedium";
	static final public String OME_LASERMEDIUM_PROPERTY_URI = OME_201606_NAMESPACE + "laserMedium";
	static final public String OME_LASERTYPE_CLASS_URI = OME_201606_NAMESPACE + "LaserType";
	static final public String OME_LASERTYPE_PROPERTY_URI = OME_201606_NAMESPACE + "laserType";
	static final public String OME_LEADER_PROPERTY_URI = OME_201606_NAMESPACE + "leader";
	static final public String OME_LED_CLASS_URI = OME_201606_NAMESPACE + "LED";
	static final public String OME_LENGTH_PROPERTY_URI = OME_201606_NAMESPACE + "length";
	static final public String OME_LENSNA_PROPERTY_URI = OME_201606_NAMESPACE + "lensNA";
	static final public String OME_LIGHTPATH_CLASS_URI = OME_201606_NAMESPACE + "LightPath";
	static final public String OME_LIGHTPATH_PROPERTY_URI = OME_201606_NAMESPACE + "lightPath";
	static final public String OME_LIGHTSOURCE_CLASS_URI = OME_201606_NAMESPACE + "LightSource";
	static final public String OME_LIGHTSOURCE_PROPERTY_URI = OME_201606_NAMESPACE + "lightSource";
	static final public String OME_LIGHTSOURCESETTINGS_CLASS_URI = OME_201606_NAMESPACE + "LightSourceSettings";
	static final public String OME_LIGHTSOURCESETTINGS_PROPERTY_URI = OME_201606_NAMESPACE + "lightSourceSettings";
	static final public String OME_LINE_CLASS_URI = OME_201606_NAMESPACE + "Line";
	static final public String OME_LISTANNOTATION_CLASS_URI = OME_201606_NAMESPACE + "ListAnnotation";
	static final public String OME_LONGANNOTATION_CLASS_URI = OME_201606_NAMESPACE + "LongAnnotation";
	static final public String OME_LOCKED_PROPERTY_URI = OME_201606_NAMESPACE + "locked";
	static final public String OME_LOTNUMBER_PROPERTY_URI = OME_201606_NAMESPACE + "lotNumber";

	static final public String OME_MANUFACTURER_PROPERTY_URI = OME_201606_NAMESPACE + "manufacturer";
	static final public String OME_MAP_PROPERTY_URI = OME_201606_NAMESPACE + "map";
	static final public String OME_MAPANNOTATION_CLASS_URI = OME_201606_NAMESPACE + "MapAnnotation";
	static final public String OME_MAPKEY_PROPERTY_URI = OME_201606_NAMESPACE + "key";
	static final public String OME_MAPVALUE_PROPERTY_URI = OME_201606_NAMESPACE + "value";
	static final public String OME_MARKER_CLASS_URI = OME_201606_NAMESPACE + "Marker";
	static final public String OME_MARKER_PROPERTY_URI = OME_201606_NAMESPACE + "marker";
	static final public String OME_MASK_CLASS_URI = OME_201606_NAMESPACE + "Mask";
	static final public String OME_MAXIMUMFIELDCOUNT_PROPERTY_URI = OME_201606_NAMESPACE + "maximumFieldCount";
	static final public String OME_MEDIUM_CLASS_URI = OME_201606_NAMESPACE + "Medium";
	static final public String OME_MEDIUM_PROPERTY_URI = OME_201606_NAMESPACE + "medium";
	static final public String OME_METADATAFILE_PROPERTY_URI = OME_201606_NAMESPACE + "metadataFile";
	static final public String OME_METADATAONLY_PROPERTY_URI = OME_201606_NAMESPACE + "metadataOnly";
	static final public String OME_MICROBEAMMANIPULATION_CLASS_URI = OME_201606_NAMESPACE + "MicrobeamManipulation";
	static final public String OME_MICROBEAMMANIPULATION_PROPERTY_URI = OME_201606_NAMESPACE + "microbeamManipulation";
	static final public String OME_MICROSCOPE_CLASS_URI = OME_201606_NAMESPACE + "Microscope";
	static final public String OME_MICROSCOPE_PROPERTY_URI = OME_201606_NAMESPACE + "microscope";
	static final public String OME_MICROSCOPETYPE_CLASS_URI = OME_201606_NAMESPACE + "MicroscopeType";
	static final public String OME_MICROSCOPETYPE_PROPERTY_URI = OME_201606_NAMESPACE + "microscopeType";
	static final public String OME_MIMETYPE_PROPERTY_URI = OME_201606_NAMESPACE + "mimeType";
	static final public String OME_MODEL_PROPERTY_URI = OME_201606_NAMESPACE + "model";

	static final public String OME_NAMESPACE_PROPERTY_URI = OME_201606_NAMESPACE + "namespace";
	static final public String OME_NAMINGCONVENTION_CLASS_URI = OME_201606_NAMESPACE + "NamingConvention";
	static final public String OME_NAMINGCONVENTION_PROPERTY_URI = OME_201606_NAMESPACE + "namingConvention";
	static final public String OME_NDFILTER_PROPERTY_URI = OME_201606_NAMESPACE + "ndFilter";
	static final public String OME_NOMINALMAGNIFICATION_PROPERTY_URI = OME_201606_NAMESPACE + "nominalMagnification";

	static final public String OME_OBJECTIVE_CLASS_URI = OME_201606_NAMESPACE + "Objective";
	static final public String OME_OBJECTIVE_PROPERTY_URI = OME_201606_NAMESPACE + "objective";
	static final public String OME_OBJECTIVESETTINGS_CLASS_URI = OME_201606_NAMESPACE + "ObjectiveSettings";
	static final public String OME_OBJECTIVESETTINGS_PROPERTY_URI = OME_201606_NAMESPACE + "objectiveSettings";
	static final public String OME_OFFSET_PROPERTY_URI = OME_201606_NAMESPACE + "offset";

	static final public String OME_PHYSICALSIZEX_PROPERTY_URI = OME_201606_NAMESPACE + "physicalSizeX";
	static final public String OME_PHYSICALSIZEY_PROPERTY_URI = OME_201606_NAMESPACE + "physicalSizeY";
	static final public String OME_PHYSICALSIZEZ_PROPERTY_URI = OME_201606_NAMESPACE + "physicalSizeZ";
	static final public String OME_PINHOLESIZE_PROPERTY_URI = OME_201606_NAMESPACE + "pinholeSize";
	static final public String OME_PIXELS_CLASS_URI = OME_201606_NAMESPACE + "Pixels";
	static final public String OME_PIXELS_PROPERTY_URI = OME_201606_NAMESPACE + "pixels";
	static final public String OME_PIXELTYPE_CLASS_URI = OME_201606_NAMESPACE + "PixelType";
	static final public String OME_PIXELTYPE_PROPERTY_URI = OME_201606_NAMESPACE + "pixelType";
	static final public String OME_PLANE_CLASS_URI = OME_201606_NAMESPACE + "Plane";
	static final public String OME_PLANE_PROPERTY_URI = OME_201606_NAMESPACE + "plane";
	static final public String OME_PLANECOUNT_PROPERTY_URI = OME_201606_NAMESPACE + "planeCount";
	static final public String OME_PLATE_CLASS_URI = OME_201606_NAMESPACE + "Plate";
	static final public String OME_PLATE_PROPERTY_URI = OME_201606_NAMESPACE + "plate";
	static final public String OME_PLATEACQUISITION_CLASS_URI = OME_201606_NAMESPACE + "PlateAcquisition";
	static final public String OME_PLATEACQUISITION_PROPERTY_URI = OME_201606_NAMESPACE + "plateAcquisition";
	static final public String OME_POCKELCELL_PROPERTY_URI = OME_201606_NAMESPACE + "pockelCell";
	static final public String OME_POCKELCELLSETTING_PROPERTY_URI = OME_201606_NAMESPACE + "pockelCellSetting";
	static final public String OME_POINT_CLASS_URI = OME_201606_NAMESPACE + "Point";
	static final public String OME_POINTS_PROPERTY_URI = OME_201606_NAMESPACE + "points";
	static final public String OME_POLYGON_CLASS_URI = OME_201606_NAMESPACE + "Polygon";
	static final public String OME_POLYLINE_CLASS_URI = OME_201606_NAMESPACE + "Polyline";
	static final public String OME_POSITIONX_PROPERTY_URI = OME_201606_NAMESPACE + "positionX";
	static final public String OME_POSITIONY_PROPERTY_URI = OME_201606_NAMESPACE + "positionY";
	static final public String OME_POSITIONZ_PROPERTY_URI = OME_201606_NAMESPACE + "positionZ";
	static final public String OME_POWER_PROPERTY_URI = OME_201606_NAMESPACE + "power";
	static final public String OME_PROJECT_CLASS_URI = OME_201606_NAMESPACE + "Project";
	static final public String OME_PROJECT_PROPERTY_URI = OME_201606_NAMESPACE + "project";
	static final public String OME_PROTOCOL_CLASS_URI = OME_201606_NAMESPACE + "Protocol";
	static final public String OME_PROTOCOL_PROPERTY_URI = OME_201606_NAMESPACE + "protocol";
	static final public String OME_PROTOCOLDESCRIPTION_PROPERTY_URI = OME_201606_NAMESPACE + "protocolDescription";
	static final public String OME_PROTOCOLIDENTIFIER_PROPERTY_URI = OME_201606_NAMESPACE + "protocolIdentifier";
	static final public String OME_PULSE_CLASS_URI = OME_201606_NAMESPACE + "Pulse";
	static final public String OME_PULSE_PROPERTY_URI = OME_201606_NAMESPACE + "pulse";
	static final public String OME_PUMP_CLASS_URI = OME_201606_NAMESPACE + "Pump";
	static final public String OME_PUMP_PROPERTY_URI = OME_201606_NAMESPACE + "pump";

	static final public String OME_RADIUSX_PROPERTY_URI = OME_201606_NAMESPACE + "radiusX";
	static final public String OME_RADIUSY_PROPERTY_URI = OME_201606_NAMESPACE + "radiusY";
	static final public String OME_RECTANGLE_CLASS_URI = OME_201606_NAMESPACE + "Rectangle";
	static final public String OME_READOUTRATE_PROPERTY_URI = OME_201606_NAMESPACE + "readOutRate";
	static final public String OME_REAGENT_CLASS_URI = OME_201606_NAMESPACE + "Reagenet";
	static final public String OME_REAGENT_PROPERTY_URI = OME_201606_NAMESPACE + "reagent";
	static final public String OME_REAGENTIDENTIFIER_PROPERTY_URI = OME_201606_NAMESPACE + "reagentIdentifier";
	static final public String OME_REAGENTSET_CLASS_URI = OME_201606_NAMESPACE + "ReagenetSet";
	static final public String OME_REAGENTSET_PROPERTY_URI = OME_201606_NAMESPACE + "reagentSet";
	static final public String OME_REAGENTSETDESCRIPTION_PROPERTY_URI = OME_201606_NAMESPACE + "reagentSetDescription";
	static final public String OME_REAGENTSETIDENTIFIER_PROPERTY_URI = OME_201606_NAMESPACE + "reagentSetIdentifier";
	static final public String OME_REFRACTIVEINDEX_PROPERTY_URI = OME_201606_NAMESPACE + "refractiveIndex";
	static final public String OME_REPETITIONRATE_PROPERTY_URI = OME_201606_NAMESPACE + "repetitionRate";
	static final public String OME_RIGHTS_CLASS_URI = OME_201606_NAMESPACE + "Rights";
	static final public String OME_RIGHTSHOLDER_PROPERTY_URI = OME_201606_NAMESPACE + "rightsHolder";
	static final public String OME_RIGHTSHELD_PROPERTY_URI = OME_201606_NAMESPACE + "rightsHeld";
	static final public String OME_ROI_CLASS_URI = OME_201606_NAMESPACE + "RegionOfInterest";
	static final public String OME_ROI_PROPERTY_URI = OME_201606_NAMESPACE + "regionOfInterest";
	static final public String OME_ROW_PROPERTY_URI = OME_201606_NAMESPACE + "row";
	static final public String OME_ROWNAMINGCONVENTION_PROPERTY_URI = OME_201606_NAMESPACE + "rowNamingConvention";
	static final public String OME_ROWS_PROPERTY_URI = OME_201606_NAMESPACE + "rows";

	static final public String OME_SAMPLESPERPIXEL_PROPERTY_URI = OME_201606_NAMESPACE + "samplesPerPixel";
	static final public String OME_SCREEN_CLASS_URI = OME_201606_NAMESPACE + "Screen";
	static final public String OME_SCREEN_PROPERTY_URI = OME_201606_NAMESPACE + "screen";
	static final public String OME_SCREENTYPE_CLASS_URI = OME_201606_NAMESPACE + "ScreenType";
	static final public String OME_SCREENTYPE_PROPERTY_URI = OME_201606_NAMESPACE + "screenType";
	static final public String OME_SERIALNUMBER_PROPERTY_URI = OME_201606_NAMESPACE + "serialNumber";
	static final public String OME_SHA1_PROPERTY_URI = OME_201606_NAMESPACE + "sha1";
	static final public String OME_SHAPE_PROPERTY_URI = OME_201606_NAMESPACE + "shape";
	static final public String OME_SIGNIFICANTBITS_PROPERTY_URI = OME_201606_NAMESPACE + "significantBits";
	static final public String OME_SIZE_PROPERTY_URI = OME_201606_NAMESPACE + "size";
	static final public String OME_SIZEC_PROPERTY_URI = OME_201606_NAMESPACE + "sizeC";
	static final public String OME_SIZET_PROPERTY_URI = OME_201606_NAMESPACE + "sizeT";
	static final public String OME_SIZEX_PROPERTY_URI = OME_201606_NAMESPACE + "sizeX";
	static final public String OME_SIZEY_PROPERTY_URI = OME_201606_NAMESPACE + "sizeY";
	static final public String OME_SIZEZ_PROPERTY_URI = OME_201606_NAMESPACE + "sizeZ";
	static final public String OME_STAGELABEL_CLASS_URI = OME_201606_NAMESPACE + "StageLabel";
	static final public String OME_STAGELABEL_PROPERTY_URI = OME_201606_NAMESPACE + "stageLabel";
	static final public String OME_STARTTIME_PROPERTY_URI = OME_201606_NAMESPACE + "startTime";
	static final public String OME_STATUS_PROPERTY_URI = OME_201606_NAMESPACE + "status";
	static final public String OME_STROKECOLOR_PROPERTY_URI = OME_201606_NAMESPACE + "strokeColor";
	static final public String OME_STROKEDASHARRAY_PROPERTY_URI = OME_201606_NAMESPACE + "strokeDashArray";
	static final public String OME_STROKEWIDTH_PROPERTY_URI = OME_201606_NAMESPACE + "strokeWidth";
	static final public String OME_STRUCTUREDANNOTATIONS_CLASS_URI = OME_201606_NAMESPACE + "StructuredAnnotations";
	static final public String OME_STRUCTUREDANNOTATIONS_PROPERTY_URI = OME_201606_NAMESPACE + "structuredAnnotations";

	static final public String OME_TAGANNOTATION_CLASS_URI = OME_201606_NAMESPACE + "TagAnnotation";
	static final public String OME_TEMPERATURE_PROPERTY_URI = OME_201606_NAMESPACE + "temperature";
	static final public String OME_TERMANNOTATION_CLASS_URI = OME_201606_NAMESPACE + "TermAnnotation";
	static final public String OME_TEXT_PROPERTY_URI = OME_201606_NAMESPACE + "text";
	static final public String OME_THEC_PROPERTY_URI = OME_201606_NAMESPACE + "theC";
	static final public String OME_THET_PROPERTY_URI = OME_201606_NAMESPACE + "theT";
	static final public String OME_THEZ_PROPERTY_URI = OME_201606_NAMESPACE + "theZ";
	static final public String OME_TIFFDATA_CLASS_URI = OME_201606_NAMESPACE + "TiffData";
	static final public String OME_TIFFDATA_PROPERTY_URI = OME_201606_NAMESPACE + "tiffData";
	static final public String OME_TIMEINCREMENT_PROPERTY_URI = OME_201606_NAMESPACE + "timeIncrement";
	static final public String OME_TIMEPOINT_PROPERTY_URI = OME_201606_NAMESPACE + "timepoint";
	static final public String OME_TIMESTAMPANNOTATION_CLASS_URI = OME_201606_NAMESPACE + "TimestampAnnotation";
	static final public String OME_TRANSFORM_PROPERTY_URI = OME_201606_NAMESPACE + "transform";
	static final public String OME_TRANSMITTANCE_PROPERTY_URI = OME_201606_NAMESPACE + "transmittance";
	static final public String OME_TRANSMITTANCERANGE_CLASS_URI = OME_201606_NAMESPACE + "TransmittanceRange";
	static final public String OME_TRANSMITTANCERANGE_PROPERTY_URI = OME_201606_NAMESPACE + "transmittanceRange";
	static final public String OME_TUNEABLE_PROPERTY_URI = OME_201606_NAMESPACE + "tuneable";

	static final public String OME_UNION_PROPERTY_URI = OME_201606_NAMESPACE + "union";
	static final public String OME_UUID_PROPERTY_URI = OME_201606_NAMESPACE + "uuid";

	static final public String OME_VOLTAGE_PROPERTY_URI = OME_201606_NAMESPACE + "voltage";

	static final public String OME_WAVELENGTH_PROPERTY_URI = OME_201606_NAMESPACE + "wavelength";
	static final public String OME_WELL_CLASS_URI = OME_201606_NAMESPACE + "Well";
	static final public String OME_WELL_PROPERTY_URI = OME_201606_NAMESPACE + "well";
	static final public String OME_WELLORIGINX_PROPERTY_URI = OME_201606_NAMESPACE + "wellOriginX";
	static final public String OME_WELLORIGINY_PROPERTY_URI = OME_201606_NAMESPACE + "wellOriginY";
	static final public String OME_WELLSAMPLE_CLASS_URI = OME_201606_NAMESPACE + "WellSample";
	static final public String OME_WELLSAMPLE_PROPERTY_URI = OME_201606_NAMESPACE + "wellSample";
	static final public String OME_WELLTYPE_PROPERTY_URI = OME_201606_NAMESPACE + "wellType";
	static final public String OME_WIDTH_PROPERTY_URI = OME_201606_NAMESPACE + "width";
	static final public String OME_WORKINGDISTANCE_PROPERTY_URI = OME_201606_NAMESPACE + "workingDistance";

	static final public String OME_X_PROPERTY_URI = OME_201606_NAMESPACE + "x";
	static final public String OME_X1_PROPERTY_URI = OME_201606_NAMESPACE + "x1";
	static final public String OME_X2_PROPERTY_URI = OME_201606_NAMESPACE + "x2";
	static final public String OME_XMLANNOTATION_CLASS_URI = OME_201606_NAMESPACE + "XMLAnnotation";

	static final public String OME_Y_PROPERTY_URI = OME_201606_NAMESPACE + "y";
	static final public String OME_Y1_PROPERTY_URI = OME_201606_NAMESPACE + "y1";
	static final public String OME_Y2_PROPERTY_URI = OME_201606_NAMESPACE + "y2";

	static final public String OME_Z_PROPERTY_URI = OME_201606_NAMESPACE + "z";
	static final public String OME_ZOOM_PROPERTY_URI = OME_201606_NAMESPACE + "zoom";

	// PATO qualities
	static final public String PATO_DISTANCE_CLASS_URI = PATO_NAMESPACE + "0000040";
	static final public String PATO_FREQUENCY_CLASS_URI = PATO_NAMESPACE + "0000044";
	static final public String PATO_HUMIDITY_CLASS_URI = PATO_NAMESPACE + "0015009";
	static final public String PATO_PRESSURE_CLASS_URI = PATO_NAMESPACE + "0001025";
	static final public String PATO_POSITION_CLASS_URI = PATO_NAMESPACE + "0000140";
	static final public String PATO_POWER_CLASS_URI = PATO_NAMESPACE + "0001024";
	static final public String PATO_SIZE_CLASS_URI = PATO_NAMESPACE + "0000117";
	static final public String PATO_TEMPERATURE_CLASS_URI = PATO_NAMESPACE + "0000146";
	static final public String PATO_TIME_CLASS_URI = PATO_NAMESPACE + "0000165";
	static final public String PATO_WAVELENGTH_CLASS_URI = PATO_NAMESPACE + "0001242";
	static final public String PATO_WIDTH_CLASS_URI = PATO_NAMESPACE + "0000921";

	// SBO qualities
	static final public String SBO_VOLTAGE_CLASS_URI = SBO_NAMESPACE + "000259";

// -------------------------------------------------------------------------------------------------------------------
//  Units
//-------------------------------------------------------------------------------------------------------------------
	// Length units
	static public String getLengthUnitURI(String symbol) throws Exception {
		if (symbol.equals("Ym")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "Ym";
		}
		if (symbol.equals("Zm")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "Zm";
		}
		if (symbol.equals("Em")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "Em";
		}
		if (symbol.equals("Pm")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "Pm";
		}
		if (symbol.equals("Tm")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "Tm";
		}
		if (symbol.equals("Gm")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "Gm";
		}
		if (symbol.equals("Mm")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "Mm";
		}
		if (symbol.equals("km")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "km";
		}
		if (symbol.equals("hm")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "hm";
		}
		if (symbol.equals("dam")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "dam";
		}
		if (symbol.equals("m")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "m";
		}
		if (symbol.equals("dm")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "dm";
		}
		if (symbol.equals("cm")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "cm";
		}
		if (symbol.equals("mm")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "mm";
		}
		if (symbol.equals("µm")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "um";
		}
		if (symbol.equals("nm")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "nm";
		}
		if (symbol.equals("pm")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "pm";
		}
		if (symbol.equals("fm")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "fm";
		}
		if (symbol.equals("am")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "am";
		}
		if (symbol.equals("zm")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "zm";
		}
		if (symbol.equals("Å")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "A";
		}
		if (symbol.equals("thou")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "thou";
		}
		if (symbol.equals("li")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "li";
		}
		if (symbol.equals("in")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "in";
		}
		if (symbol.equals("ft")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "ft";
		}
		if (symbol.equals("yd")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "yd";
		}
		if (symbol.equals("mi")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "mi";
		}
		if (symbol.equals("ua")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "ua";
		}
		if (symbol.equals("ly")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "ly";
		}
		if (symbol.equals("pc")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "pc";
		}
		if (symbol.equals("pt")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "pt";
		}
		if (symbol.equals("pixel")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "pixel";
		}
		if (symbol.equals("reference frame")) {
			return OME_201606_LENGTH_UNIT_NAMESPACE + "referenceFrame";
		}
		throw new Exception("Unsupported length unit: " + symbol);
	}

	// Power units
	static public String getPowerUnitURI(String symbol) throws Exception {
		if (symbol.equals("YW")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "YW";
		}
		if (symbol.equals("ZW")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "ZW";
		}
		if (symbol.equals("EW")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "EW";
		}
		if (symbol.equals("PW")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "PW";
		}
		if (symbol.equals("TW")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "TW";
		}
		if (symbol.equals("GW")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "GW";
		}
		if (symbol.equals("MW")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "MW";
		}
		if (symbol.equals("kW")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "kW";
		}
		if (symbol.equals("hW")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "hW";
		}
		if (symbol.equals("daW")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "daW";
		}
		if (symbol.equals("W")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "W";
		}
		if (symbol.equals("dW")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "dW";
		}
		if (symbol.equals("cW")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "cW";
		}
		if (symbol.equals("mW")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "mW";
		}
		if (symbol.equals("µW")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "uW";
		}
		if (symbol.equals("nW")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "nW";
		}
		if (symbol.equals("pW")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "pW";
		}
		if (symbol.equals("fW")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "fW";
		}
		if (symbol.equals("aW")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "aW";
		}
		if (symbol.equals("zW")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "zW";
		}
		if (symbol.equals("yW")) {
			return OME_201606_POWER_UNIT_NAMESPACE + "yW";
		}
		throw new Exception("Unsupported power unit: " + symbol);
	}

	// Electric potential units
	static public String getElectricPotentialUnitURI(String symbol) throws Exception {
		if (symbol.equals("YV")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "YV";
		}
		if (symbol.equals("ZV")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "ZV";
		}
		if (symbol.equals("EV")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "EV";
		}
		if (symbol.equals("PV")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "PV";
		}
		if (symbol.equals("TV")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "TV";
		}
		if (symbol.equals("GV")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "GV";
		}
		if (symbol.equals("MV")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "MV";
		}
		if (symbol.equals("kV")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "kV";
		}
		if (symbol.equals("hV")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "hV";
		}
		if (symbol.equals("daV")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "daV";
		}
		if (symbol.equals("V")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "V";
		}
		if (symbol.equals("dV")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "dV";
		}
		if (symbol.equals("cV")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "cV";
		}
		if (symbol.equals("mV")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "mV";
		}
		if (symbol.equals("µV")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "uV";
		}
		if (symbol.equals("nV")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "nV";
		}
		if (symbol.equals("pV")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "pV";
		}
		if (symbol.equals("fV")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "fV";
		}
		if (symbol.equals("aV")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "aV";
		}
		if (symbol.equals("zV")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "zV";
		}
		if (symbol.equals("yV")) {
			return OME_201606_ELECTRICPOTENTIAL_UNIT_NAMESPACE + "yV";
		}
		throw new Exception("Unsupported electric potential unit: " + symbol);
	}

	// Pressure units
	static public String getPressureUnitURI(String symbol) throws Exception {
		if (symbol.equals("YPa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "YPa";
		}
		if (symbol.equals("ZPa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "ZPa";
		}
		if (symbol.equals("EPa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "EPa";
		}
		if (symbol.equals("PPa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "PPa";
		}
		if (symbol.equals("TPa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "TPa";
		}
		if (symbol.equals("GPa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "GPa";
		}
		if (symbol.equals("MPa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "MPa";
		}
		if (symbol.equals("kPa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "kPa";
		}
		if (symbol.equals("hPa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "hPa";
		}
		if (symbol.equals("daPa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "daPa";
		}
		if (symbol.equals("Pa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "Pa";
		}
		if (symbol.equals("dPa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "dPa";
		}
		if (symbol.equals("cPa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "cPa";
		}
		if (symbol.equals("mPa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "mPa";
		}
		if (symbol.equals("µPa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "uPa";
		}
		if (symbol.equals("nPa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "nPa";
		}
		if (symbol.equals("pPa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "pPa";
		}
		if (symbol.equals("fPa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "fPa";
		}
		if (symbol.equals("aPa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "aPa";
		}
		if (symbol.equals("zPa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "zPa";
		}
		if (symbol.equals("yPa")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "yPa";
		}
		if (symbol.equals("bar")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "bar";
		}
		if (symbol.equals("Mbar")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "Mbar";
		}
		if (symbol.equals("kbar")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "kbar";
		}
		if (symbol.equals("dbar")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "dbar";
		}
		if (symbol.equals("cbar")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "cbar";
		}
		if (symbol.equals("mbar")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "mbar";
		}
		if (symbol.equals("atm")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "atm";
		}
		if (symbol.equals("psi")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "psi";
		}
		if (symbol.equals("Torr")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "Torr";
		}
		if (symbol.equals("mTorr")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "mTorr";
		}
		if (symbol.equals("mm Hg")) {
			return OME_201606_PRESSURE_UNIT_NAMESPACE + "mmHg";
		}
		throw new Exception("Unsupported pressure unit: " + symbol);
	}

	// Frequency units
	static public String getFrequencyUnitURI(String symbol) throws Exception {
		if (symbol.equals("YHz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "YHz";
		}
		if (symbol.equals("ZHz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "ZHz";
		}
		if (symbol.equals("EHz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "EHz";
		}
		if (symbol.equals("PHz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "PHz";
		}
		if (symbol.equals("THz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "THz";
		}
		if (symbol.equals("GHz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "GHz";
		}
		if (symbol.equals("MHz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "MHz";
		}
		if (symbol.equals("kHz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "kHz";
		}
		if (symbol.equals("hHz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "hHz";
		}
		if (symbol.equals("daHz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "daHz";
		}
		if (symbol.equals("Hz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "Hz";
		}
		if (symbol.equals("dHz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "dHz";
		}
		if (symbol.equals("cHz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "cHz";
		}
		if (symbol.equals("mHz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "mHz";
		}
		if (symbol.equals("µHz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "uHz";
		}
		if (symbol.equals("nHz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "nHz";
		}
		if (symbol.equals("pHz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "pHz";
		}
		if (symbol.equals("fHz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "fHz";
		}
		if (symbol.equals("aHz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "aHz";
		}
		if (symbol.equals("zHz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "zHz";
		}
		if (symbol.equals("yHz")) {
			return OME_201606_FREQUENCY_UNIT_NAMESPACE + "yHz";
		}
		throw new Exception("Unsupported frequency unit: " + symbol);
	}

	// Time units
	static public String getTimeUnitURI(String symbol) throws Exception {
		if (symbol.equals("Ys")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "Ys";
		}
		if (symbol.equals("Zs")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "Zs";
		}
		if (symbol.equals("Es")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "Es";
		}
		if (symbol.equals("Ps")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "Ps";
		}
		if (symbol.equals("Ts")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "Ts";
		}
		if (symbol.equals("Gs")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "Gs";
		}
		if (symbol.equals("Ms")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "Ms";
		}
		if (symbol.equals("ks")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "ks";
		}
		if (symbol.equals("hs")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "hs";
		}
		if (symbol.equals("das")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "das";
		}
		if (symbol.equals("s")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "s";
		}
		if (symbol.equals("ds")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "ds";
		}
		if (symbol.equals("cs")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "cs";
		}
		if (symbol.equals("ms")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "ms";
		}
		if (symbol.equals("µs")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "us";
		}
		if (symbol.equals("ns")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "ns";
		}
		if (symbol.equals("ps")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "ps";
		}
		if (symbol.equals("fs")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "fs";
		}
		if (symbol.equals("as")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "as";
		}
		if (symbol.equals("zs")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "zs";
		}
		if (symbol.equals("ys")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "ys";
		}
		if (symbol.equals("min")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "min";
		}
		if (symbol.equals("h")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "h";
		}
		if (symbol.equals("d")) {
			return OME_201606_TIME_UNIT_NAMESPACE + "d";
		}
		throw new Exception("Unsupported time unit: " + symbol);
	}

	// Angle units
	static public String getAngleUnitURI(String symbol) throws Exception {
		if (symbol.equals("deg")) {
			return OME_201606_ANGLE_UNIT_NAMESPACE + "deg";
		}
		if (symbol.equals("rad")) {
			return OME_201606_ANGLE_UNIT_NAMESPACE + "rad";
		}
		if (symbol.equals("gon")) {
			return OME_201606_ANGLE_UNIT_NAMESPACE + "gon";
		}
		throw new Exception("Unsupported angle unit: " + symbol);
	}

	// Temperature units
	static public String getTemperatureUnitURI(String symbol) throws Exception {
		if (symbol.equals("°C")) {
			return OME_201606_TEMPERATURE_UNIT_NAMESPACE + "degreeCelsius";
		}
		if (symbol.equals("°F")) {
			return OME_201606_TEMPERATURE_UNIT_NAMESPACE + "degreeFahrenheit";
		}
		if (symbol.equals("K")) {
			return OME_201606_TEMPERATURE_UNIT_NAMESPACE + "kelvin";
		}
		if (symbol.equals("°R")) {
			return OME_201606_TEMPERATURE_UNIT_NAMESPACE + "degreeRankine";
		}
		throw new Exception("Unsupported tempreture unit: " + symbol);
	}

// -------------------------------------------------------------------------------------------------------------------
//  Literals
//-------------------------------------------------------------------------------------------------------------------
	// acquisitionMode
	static public String getAcquisitionModeURI(String symbol) throws Exception {
		if (symbol.equals("WideField")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#wideField";
		}
		if (symbol.equals("LaserScanningConfocalMicroscopy")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#laserScanningConfocalMicroscopy";
		}
		if (symbol.equals("SpinningDiskConfocal")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#spinningDiskConfocal";
		}
		if (symbol.equals("SlitScanConfocal")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#slitScanConfocal";
		}
		if (symbol.equals("MultiPhotonMicroscopy")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#multiPhotonMicroscopy";
		}
		if (symbol.equals("StructuredIllumination")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#structuredIllumination";
		}
		if (symbol.equals("SingleMoleculeImaging")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#singleMoleculeImaging";
		}
		if (symbol.equals("TotalInternalReflection")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#totalInternalReflection";
		}
		if (symbol.equals("FluorescenceLifetime")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#fluorescenceLifetime";
		}
		if (symbol.equals("SpectralImaging")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#spectralImaging";
		}
		if (symbol.equals("FluorescenceCorrelationSpectroscopy")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#fluorescenceCorrelationSpectroscopy";
		}
		if (symbol.equals("NearFieldScanningOpticalMicroscopy")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#nearFieldScanningOpticalMicroscopy";
		}
		if (symbol.equals("SecondHarmonicGenerationImaging")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#secondHarmonicGenerationImaging";
		}
		if (symbol.equals("PALM")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#PALM";
		}
		if (symbol.equals("STORM")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#STORM";
		}
		if (symbol.equals("STED")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#STED";
		}
		if (symbol.equals("TIRF")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#TIRF";
		}
		if (symbol.equals("FSM")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#FSM";
		}
		if (symbol.equals("LCM")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#LCM";
		}
		if (symbol.equals("Other")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#other";
		}
		if (symbol.equals("BrightField")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#brightField";
		}
		if (symbol.equals("SweptFieldConfocal")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#sweptFieldConfocal";
		}
		if (symbol.equals("SPIM")) {
			return OME_ACQUISITIONMODE_CLASS_URI + "#SPIM";
		}
		throw new Exception("Unsupported acquisitionMode value: " + symbol);
	}

	// arcType
	static public String getArcTypeURI(String symbol) throws Exception {
		if (symbol.equals("Hg")) {
			return OME_ARCTYPE_CLASS_URI + "#Hg";
		}
		if (symbol.equals("Xe")) {
			return OME_ARCTYPE_CLASS_URI + "#Xe";
		}
		if (symbol.equals("HgXe")) {
			return OME_ARCTYPE_CLASS_URI + "#HgXe";
		}
		if (symbol.equals("Other")) {
			return OME_ARCTYPE_CLASS_URI + "#other";
		}
		throw new Exception("Unsupported arcType value: " + symbol);
	}

	// filamentType
	static public String getFilamentTypeURI(String symbol) throws Exception {
		if (symbol.equals("Incandescent")) {
			return OME_FILAMENTTYPE_CLASS_URI + "#incandescent";
		}
		if (symbol.equals("Halogen")) {
			return OME_FILAMENTTYPE_CLASS_URI + "#halogen";
		}
		if (symbol.equals("Other")) {
			return OME_FILAMENTTYPE_CLASS_URI + "#other";
		}
		throw new Exception("Unsupported filamentType value: " + symbol);
	}

	// laserMedium
	static public String getLaserMediumURI(String symbol) throws Exception {
		if (symbol.equals("Cu")) {
			return OME_LASERMEDIUM_CLASS_URI + "#Cu";
		}
		if (symbol.equals("Ag")) {
			return OME_LASERMEDIUM_CLASS_URI + "#Ag";
		}
		if (symbol.equals("ArFl")) {
			return OME_LASERMEDIUM_CLASS_URI + "#ArFl";
		}
		if (symbol.equals("ArCl")) {
			return OME_LASERMEDIUM_CLASS_URI + "#ArCl";
		}
		if (symbol.equals("KrFl")) {
			return OME_LASERMEDIUM_CLASS_URI + "#KrFl";
		}
		if (symbol.equals("KrCl")) {
			return OME_LASERMEDIUM_CLASS_URI + "#KrCl";
		}
		if (symbol.equals("XeFl")) {
			return OME_LASERMEDIUM_CLASS_URI + "#XeFl";
		}
		if (symbol.equals("XeCl")) {
			return OME_LASERMEDIUM_CLASS_URI + "#XeCl";
		}
		if (symbol.equals("XeBr")) {
			return OME_LASERMEDIUM_CLASS_URI + "#XeBr";
		}
		if (symbol.equals("N")) {
			return OME_LASERMEDIUM_CLASS_URI + "#N";
		}
		if (symbol.equals("Ar")) {
			return OME_LASERMEDIUM_CLASS_URI + "#Ar";
		}
		if (symbol.equals("Kr")) {
			return OME_LASERMEDIUM_CLASS_URI + "#Kr";
		}
		if (symbol.equals("Xe")) {
			return OME_LASERMEDIUM_CLASS_URI + "#Xe";
		}
		if (symbol.equals("HeNe")) {
			return OME_LASERMEDIUM_CLASS_URI + "#HeNe";
		}
		if (symbol.equals("HeCd")) {
			return OME_LASERMEDIUM_CLASS_URI + "#HeCd";
		}
		if (symbol.equals("CO")) {
			return OME_LASERMEDIUM_CLASS_URI + "#CO";
		}
		if (symbol.equals("CO2")) {
			return OME_LASERMEDIUM_CLASS_URI + "#CO2";
		}
		if (symbol.equals("H2O")) {
			return OME_LASERMEDIUM_CLASS_URI + "#H2O";
		}
		if (symbol.equals("HFl")) {
			return OME_LASERMEDIUM_CLASS_URI + "#HFl";
		}
		if (symbol.equals("NdGlass")) {
			return OME_LASERMEDIUM_CLASS_URI + "#NdGlass";
		}
		if (symbol.equals("NdYAG")) {
			return OME_LASERMEDIUM_CLASS_URI + "#NdYAG";
		}
		if (symbol.equals("ErGlass")) {
			return OME_LASERMEDIUM_CLASS_URI + "#ErGlass";
		}
		if (symbol.equals("ErYAG")) {
			return OME_LASERMEDIUM_CLASS_URI + "#ErYAG";
		}
		if (symbol.equals("HoYLF")) {
			return OME_LASERMEDIUM_CLASS_URI + "#HoYLF";
		}
		if (symbol.equals("HoYAG")) {
			return OME_LASERMEDIUM_CLASS_URI + "#HoYAG";
		}
		if (symbol.equals("Ruby")) {
			return OME_LASERMEDIUM_CLASS_URI + "#Ruby";
		}
		if (symbol.equals("TiSapphire")) {
			return OME_LASERMEDIUM_CLASS_URI + "#TiSapphire";
		}
		if (symbol.equals("Alexandrite")) {
			return OME_LASERMEDIUM_CLASS_URI + "#Alexandrite";
		}
		if (symbol.equals("Rhodamine6G")) {
			return OME_LASERMEDIUM_CLASS_URI + "#Rhodamine6G";
		}
		if (symbol.equals("CoumarinC30")) {
			return OME_LASERMEDIUM_CLASS_URI + "#CoumarinC30";
		}
		if (symbol.equals("GaAs")) {
			return OME_LASERMEDIUM_CLASS_URI + "#GaAs";
		}
		if (symbol.equals("GaAlAs")) {
			return OME_LASERMEDIUM_CLASS_URI + "#GaAlAs";
		}
		if (symbol.equals("EMinus")) {
			return OME_LASERMEDIUM_CLASS_URI + "#EMinus";
		}
		if (symbol.equals("Other")) {
			return OME_LASERMEDIUM_CLASS_URI + "#other";
		}
		throw new Exception("Unsupported laserMedium value: " + symbol);
	}

	// pulse
	static public String getPulseURI(String symbol) throws Exception {
		if (symbol.equals("CW")) {
			return OME_PULSE_CLASS_URI + "#CW";
		}
		if (symbol.equals("Single")) {
			return OME_PULSE_CLASS_URI + "#single";
		}
		if (symbol.equals("QSwitched")) {
			return OME_PULSE_CLASS_URI + "#qSwitched";
		}
		if (symbol.equals("Repetitive")) {
			return OME_PULSE_CLASS_URI + "#repetitive";
		}
		if (symbol.equals("ModeLocked")) {
			return OME_PULSE_CLASS_URI + "#modeLocked";
		}
		if (symbol.equals("Other")) {
			return OME_PULSE_CLASS_URI + "#other";
		}
		throw new Exception("Unsupported pulse value: " + symbol);
	}

	// laserType
	static public String getLaserTypeURI(String symbol) throws Exception {
		if (symbol.equals("Excimer")) {
			return OME_LASERTYPE_CLASS_URI + "#excimer";
		}
		if (symbol.equals("Gas")) {
			return OME_LASERTYPE_CLASS_URI + "#gas";
		}
		if (symbol.equals("MetalVapor")) {
			return OME_LASERTYPE_CLASS_URI + "#metalVapor";
		}
		if (symbol.equals("SolidState")) {
			return OME_LASERTYPE_CLASS_URI + "#solidState";
		}
		if (symbol.equals("Dye")) {
			return OME_LASERTYPE_CLASS_URI + "#dye";
		}
		if (symbol.equals("Semiconductor")) {
			return OME_LASERTYPE_CLASS_URI + "#semiconductor";
		}
		if (symbol.equals("FreeElectron")) {
			return OME_LASERTYPE_CLASS_URI + "#freeElectron";
		}
		if (symbol.equals("Other")) {
			return OME_LASERTYPE_CLASS_URI + "#other";
		}
		throw new Exception("Unsupported laserType value: " + symbol);
	}

	// binning
	static public String getBinningURI(String symbol) throws Exception {
		if (symbol.equals("1x1")) {
			return OME_BINNING_CLASS_URI + "#oneXOne";
		}
		if (symbol.equals("2x2")) {
			return OME_BINNING_CLASS_URI + "#twoXTwo";
		}
		if (symbol.equals("4x4")) {
			return OME_BINNING_CLASS_URI + "#fourXFour";
		}
		if (symbol.equals("8x8")) {
			return OME_BINNING_CLASS_URI + "#eightXEight";
		}
		if (symbol.equals("Other")) {
			return OME_BINNING_CLASS_URI + "#other";
		}
		throw new Exception("Unsupported binning value: " + symbol);
	}

	// compression
	static public String getCompressionURI(String symbol) throws Exception {
		if (symbol.equals("zlib")) {
			return OME_COMPRESSION_CLASS_URI + "#zlib";
		}
		if (symbol.equals("bzip2")) {
			return OME_COMPRESSION_CLASS_URI + "#bzip2";
		}
		if (symbol.equals("none")) {
			return OME_COMPRESSION_CLASS_URI + "#none";
		}
		throw new Exception("Unsupported compression value: " + symbol);
	}

	// contrastMethod
	static public String getContrastMethodURI(String symbol) throws Exception {
		if (symbol.equals("Brightfield")) {
			return OME_CONTRASTMETHOD_CLASS_URI + "#brightfield";
		}
		if (symbol.equals("Phase")) {
			return OME_CONTRASTMETHOD_CLASS_URI + "#phase";
		}
		if (symbol.equals("DIC")) {
			return OME_CONTRASTMETHOD_CLASS_URI + "#DIC";
		}
		if (symbol.equals("HoffmanModulation")) {
			return OME_CONTRASTMETHOD_CLASS_URI + "#hoffmanModulation";
		}
		if (symbol.equals("ObliqueIllumination")) {
			return OME_CONTRASTMETHOD_CLASS_URI + "#obliqueIllumination";
		}
		if (symbol.equals("PolarizedLight")) {
			return OME_CONTRASTMETHOD_CLASS_URI + "#polarizedLight";
		}
		if (symbol.equals("Darkfield")) {
			return OME_CONTRASTMETHOD_CLASS_URI + "#darkfield";
		}
		if (symbol.equals("Fluorescence")) {
			return OME_CONTRASTMETHOD_CLASS_URI + "#fluorescence";
		}
		if (symbol.equals("Other")) {
			return OME_CONTRASTMETHOD_CLASS_URI + "#other";
		}
		throw new Exception("Unsupported contrastMethod value: " + symbol);
	}

	// correction
	static public String getCorrectionURI(String symbol) throws Exception {
		if (symbol.equals("UV")) {
			return OME_CORRECTION_CLASS_URI + "#UV";
		}
		if (symbol.equals("PlanApo")) {
			return OME_CORRECTION_CLASS_URI + "#planApo";
		}
		if (symbol.equals("PlanFluor")) {
			return OME_CORRECTION_CLASS_URI + "#planFluor";
		}
		if (symbol.equals("SuperFluor")) {
			return OME_CORRECTION_CLASS_URI + "#superFluor";
		}
		if (symbol.equals("VioletCorrected")) {
			return OME_CORRECTION_CLASS_URI + "#violetCorrected";
		}
		if (symbol.equals("Achro")) {
			return OME_CORRECTION_CLASS_URI + "#achro";
		}
		if (symbol.equals("Achromat")) {
			return OME_CORRECTION_CLASS_URI + "#achromat";
		}
		if (symbol.equals("Fluor")) {
			return OME_CORRECTION_CLASS_URI + "#fluor";
		}
		if (symbol.equals("Fl")) {
			return OME_CORRECTION_CLASS_URI + "#fl";
		}
		if (symbol.equals("Fluar")) {
			return OME_CORRECTION_CLASS_URI + "#fluar";
		}
		if (symbol.equals("Neofluar")) {
			return OME_CORRECTION_CLASS_URI + "#neofluar";
		}
		if (symbol.equals("Fluotar")) {
			return OME_CORRECTION_CLASS_URI + "#fluotar";
		}
		if (symbol.equals("Apo")) {
			return OME_CORRECTION_CLASS_URI + "#apo";
		}
		if (symbol.equals("PlanNeofluar")) {
			return OME_CORRECTION_CLASS_URI + "#planNeofluar";
		}
		if (symbol.equals("Other")) {
			return OME_CORRECTION_CLASS_URI + "#other";
		}
		throw new Exception("Unsupported correction value: " + symbol);
	}
	
	// detectorType
	static public String getDetectorTypeURI(String symbol) throws Exception {
		if (symbol.equals("CCD")) {
			return OME_DETECTORTYPE_CLASS_URI + "#CCD";
		}
		if (symbol.equals("IntensifiedCCD")) {
			return OME_DETECTORTYPE_CLASS_URI + "#intensifiedCCD";
		}
		if (symbol.equals("AnalogVideo")) {
			return OME_DETECTORTYPE_CLASS_URI + "#analogVideo";
		}
		if (symbol.equals("PMT")) {
			return OME_DETECTORTYPE_CLASS_URI + "#PMT";
		}
		if (symbol.equals("Photodiode")) {
			return OME_DETECTORTYPE_CLASS_URI + "#photodiode";
		}
		if (symbol.equals("Spectroscopy")) {
			return OME_DETECTORTYPE_CLASS_URI + "#spectroscopy";
		}
		if (symbol.equals("LifetimeImaging")) {
			return OME_DETECTORTYPE_CLASS_URI + "#lifetimeImaging";
		}
		if (symbol.equals("CorrelationSpectroscopy")) {
			return OME_DETECTORTYPE_CLASS_URI + "#correlationSpectroscopy";
		}
		if (symbol.equals("FTIR")) {
			return OME_DETECTORTYPE_CLASS_URI + "#FTIR";
		}
		if (symbol.equals("EMCCD")) {
			return OME_DETECTORTYPE_CLASS_URI + "#EMCCD";
		}
		if (symbol.equals("APD")) {
			return OME_DETECTORTYPE_CLASS_URI + "#APD";
		}
		if (symbol.equals("CMOS")) {
			return OME_DETECTORTYPE_CLASS_URI + "#CMOS";
		}
		if (symbol.equals("EBCCD")) {
			return OME_DETECTORTYPE_CLASS_URI + "#EBCCD";
		}
		if (symbol.equals("Other")) {
			return OME_DETECTORTYPE_CLASS_URI + "#other";
		}
		throw new Exception("Unsupported detectorType value: " + symbol);
	}
	
	// dimensionOrder
	static public String getDimensionOrderURI(String symbol) throws Exception {
		if (symbol.equals("XYZCT")) {
			return OME_DIMENSIONORDER_CLASS_URI + "#xyzct";
		}
		if (symbol.equals("XYZTC")) {
			return OME_DIMENSIONORDER_CLASS_URI + "#xyztc";
		}
		if (symbol.equals("XYCTZ")) {
			return OME_DIMENSIONORDER_CLASS_URI + "#xyctz";
		}
		if (symbol.equals("XYCZT")) {
			return OME_DIMENSIONORDER_CLASS_URI + "#xyczt";
		}
		if (symbol.equals("XYTCZ")) {
			return OME_DIMENSIONORDER_CLASS_URI + "#xytcz";
		}
		if (symbol.equals("XYTZC")) {
			return OME_DIMENSIONORDER_CLASS_URI + "#xytzc";
		}
		throw new Exception("Unsupported dimensionOrder value: " + symbol);
	}
	
	// experimentType
	static public String getExperimentTypeURI(String symbol) throws Exception {
		if (symbol.equals("FP")) {
			return OME_EXPERIMENTTYPE_CLASS_URI + "#fp";
		}
		if (symbol.equals("FRET")) {
			return OME_EXPERIMENTTYPE_CLASS_URI + "#fret";
		}
		if (symbol.equals("TimeLapse")) {
			return OME_EXPERIMENTTYPE_CLASS_URI + "#timeLapse";
		}
		if (symbol.equals("FourDPlus")) {
			return OME_EXPERIMENTTYPE_CLASS_URI + "#fourDPlus";
		}
		if (symbol.equals("Screen")) {
			return OME_EXPERIMENTTYPE_CLASS_URI + "#screen";
		}
		if (symbol.equals("Immunocytochemistry")) {
			return OME_EXPERIMENTTYPE_CLASS_URI + "#immunocytochemistry";
		}
		if (symbol.equals("Immunofluorescence")) {
			return OME_EXPERIMENTTYPE_CLASS_URI + "#immunofluorescence";
		}
		if (symbol.equals("FISH")) {
			return OME_EXPERIMENTTYPE_CLASS_URI + "#FISH";
		}
		if (symbol.equals("Electrophysiology")) {
			return OME_EXPERIMENTTYPE_CLASS_URI + "#electrophysiology";
		}
		if (symbol.equals("IonImaging")) {
			return OME_EXPERIMENTTYPE_CLASS_URI + "#ionImaging";
		}
		if (symbol.equals("Colocalization")) {
			return OME_EXPERIMENTTYPE_CLASS_URI + "#colocalization";
		}
		if (symbol.equals("PGIDocumentation")) {
			return OME_EXPERIMENTTYPE_CLASS_URI + "#PGIDocumentation";
		}
		if (symbol.equals("FluorescenceLifetime")) {
			return OME_EXPERIMENTTYPE_CLASS_URI + "#fluorescenceLifetime";
		}
		if (symbol.equals("SpectralImaging")) {
			return OME_EXPERIMENTTYPE_CLASS_URI + "#spectralImaging";
		}
		if (symbol.equals("Photobleaching")) {
			return OME_EXPERIMENTTYPE_CLASS_URI + "#photobleaching";
		}
		if (symbol.equals("SPIM")) {
			return OME_EXPERIMENTTYPE_CLASS_URI + "#SPIM";
		}
		if (symbol.equals("Other")) {
			return OME_EXPERIMENTTYPE_CLASS_URI + "#other";
		}
		throw new Exception("Unsupported experimentType value: " + symbol);
	}

	// fillRule
	static public String getFillRuleURI(String symbol) throws Exception {
		if (symbol.equals("EvenOdd")) {
			return OME_FILLRULE_CLASS_URI + "#evenOdd";
		}
		if (symbol.equals("NonZero")) {
			return OME_FILLRULE_CLASS_URI + "#nonZero";
		}
		throw new Exception("Unsupported fillRule value: " + symbol);
	}
	
	// filterType
	static public String getFilterTypeURI(String symbol) throws Exception {
		if (symbol.equals("Dichroic")) {
			return OME_FILTERTYPE_CLASS_URI + "#dichroic";
		}
		if (symbol.equals("LongPass")) {
			return OME_FILTERTYPE_CLASS_URI + "#longPass";
		}
		if (symbol.equals("ShortPass")) {
			return OME_FILTERTYPE_CLASS_URI + "#shortPass";
		}
		if (symbol.equals("BandPass")) {
			return OME_FILTERTYPE_CLASS_URI + "#bandPass";
		}
		if (symbol.equals("MultiPass")) {
			return OME_FILTERTYPE_CLASS_URI + "#multiPass";
		}
		if (symbol.equals("NeutralDensity")) {
			return OME_FILTERTYPE_CLASS_URI + "#neutralDensity";
		}
		if (symbol.equals("Tuneable")) {
			return OME_FILTERTYPE_CLASS_URI + "#tuneable";
		}
		if (symbol.equals("Other")) {
			return OME_FILTERTYPE_CLASS_URI + "#other";
		}
		throw new Exception("Unsupported filterType value: " + symbol);
	}
	
	// fontFamily
	static public String getFontFamilyURI(String symbol) throws Exception {
		if (symbol.equals("serif")) {
			return OME_FONTFAMILY_CLASS_URI + "#serif";
		}
		if (symbol.equals("sans-serif")) {
			return OME_FONTFAMILY_CLASS_URI + "#sans-serif";
		}
		if (symbol.equals("cursive")) {
			return OME_FONTFAMILY_CLASS_URI + "#cursive";
		}
		if (symbol.equals("fantasy")) {
			return OME_FONTFAMILY_CLASS_URI + "#fantasy";
		}
		if (symbol.equals("monospace")) {
			return OME_FONTFAMILY_CLASS_URI + "#monospace";
		}
		throw new Exception("Unsupported fontFamily value: " + symbol);
	}
	
	// fontStyle
	static public String getFontStyleURI(String symbol) throws Exception {
		if (symbol.equals("Bold")) {
			return OME_FONTSTYLE_CLASS_URI + "#bold";
		}
		if (symbol.equals("BoldItalic")) {
			return OME_FONTSTYLE_CLASS_URI + "#boldItalic";
		}
		if (symbol.equals("Italic")) {
			return OME_FONTSTYLE_CLASS_URI + "#italic";
		}
		if (symbol.equals("Normal")) {
			return OME_FONTSTYLE_CLASS_URI + "#normal";
		}
		throw new Exception("Unsupported fontStyle value: " + symbol);
	}
	
	// illuminationType
	static public String getIlluminationTypeURI(String symbol) throws Exception {
		if (symbol.equals("Transmitted")) {
			return OME_ILLUMINATIONTYPE_CLASS_URI + "#transmitted";
		}
		if (symbol.equals("Epifluorescence")) {
			return OME_ILLUMINATIONTYPE_CLASS_URI + "#epifluorescence";
		}
		if (symbol.equals("Oblique")) {
			return OME_ILLUMINATIONTYPE_CLASS_URI + "#oblique";
		}
		if (symbol.equals("NonLinear")) {
			return OME_ILLUMINATIONTYPE_CLASS_URI + "#nonLinear";
		}
		if (symbol.equals("Other")) {
			return OME_ILLUMINATIONTYPE_CLASS_URI + "#other";
		}
		throw new Exception("Unsupported illuminationType value: " + symbol);
	}
	
	// immersion
	static public String getImmersionURI(String symbol) throws Exception {
		if (symbol.equals("Oil")) {
			return OME_IMMERSION_CLASS_URI + "#oil";
		}
		if (symbol.equals("Water")) {
			return OME_IMMERSION_CLASS_URI + "#water";
		}
		if (symbol.equals("WaterDipping")) {
			return OME_IMMERSION_CLASS_URI + "#waterDipping";
		}
		if (symbol.equals("Air")) {
			return OME_IMMERSION_CLASS_URI + "#air";
		}
		if (symbol.equals("Multi")) {
			return OME_IMMERSION_CLASS_URI + "#multi";
		}
		if (symbol.equals("Glycerol")) {
			return OME_IMMERSION_CLASS_URI + "#glycerol";
		}
		if (symbol.equals("Other")) {
			return OME_IMMERSION_CLASS_URI + "#other";
		}
		throw new Exception("Unsupported immersion value: " + symbol);
	}
	
	// marker
	static public String getMarkerURI(String symbol) throws Exception {
		if (symbol.equals("Arrow")) {
			return OME_MARKER_CLASS_URI + "#arrow";
		}
		throw new Exception("Unsupported marker value: " + symbol);
	}
	
	// microbeamManipulation
	static public String getMicrobeamManipulationTypeURI(String symbol) throws Exception {
		if (symbol.equals("FRAP")) {
			return OME_MICROBEAMMANIPULATION_CLASS_URI + "#FRAP";
		}
		if (symbol.equals("FLIP")) {
			return OME_MICROBEAMMANIPULATION_CLASS_URI + "#FLIP";
		}
		if (symbol.equals("InverseFRAP")) {
			return OME_MICROBEAMMANIPULATION_CLASS_URI + "#inverseFRAP";
		}
		if (symbol.equals("Photoablation")) {
			return OME_MICROBEAMMANIPULATION_CLASS_URI + "#photoablation";
		}
		if (symbol.equals("Photoactivation")) {
			return OME_MICROBEAMMANIPULATION_CLASS_URI + "#photoactivation";
		}
		if (symbol.equals("Uncaging")) {
			return OME_MICROBEAMMANIPULATION_CLASS_URI + "#uncaging";
		}
		if (symbol.equals("OpticalTrapping")) {
			return OME_MICROBEAMMANIPULATION_CLASS_URI + "#opticalTrapping";
		}
		if (symbol.equals("Other")) {
			return OME_MICROBEAMMANIPULATION_CLASS_URI + "#other";
		}
		throw new Exception("Unsupported microbeamManipulation value: " + symbol);
	}
	
	// microscopeType
	static public String getMicroscopeTypeURI(String symbol) throws Exception {
		if (symbol.equals("Upright")) {
			return OME_MICROSCOPETYPE_CLASS_URI + "#upright";
		}
		if (symbol.equals("Inverted")) {
			return OME_MICROSCOPETYPE_CLASS_URI + "#inverted";
		}
		if (symbol.equals("Dissection")) {
			return OME_MICROSCOPETYPE_CLASS_URI + "#dissection";
		}
		if (symbol.equals("Electrophysiology")) {
			return OME_MICROSCOPETYPE_CLASS_URI + "#electrophysiology";
		}
		if (symbol.equals("Other")) {
			return OME_MICROSCOPETYPE_CLASS_URI + "#other";
		}
		throw new Exception("Unsupported microscopeType value: " + symbol);
	}
	
	// pixelType
	static public String getPixelTypeURI(String symbol) throws Exception {
		if (symbol.equals("int8")) {
			return OME_PIXELTYPE_CLASS_URI + "#int8";
		}
		if (symbol.equals("int16")) {
			return OME_PIXELTYPE_CLASS_URI + "#int16";
		}
		if (symbol.equals("int32")) {
			return OME_PIXELTYPE_CLASS_URI + "#int32";
		}
		if (symbol.equals("uint8")) {
			return OME_PIXELTYPE_CLASS_URI + "#uint8";
		}
		if (symbol.equals("uint16")) {
			return OME_PIXELTYPE_CLASS_URI + "#uint16";
		}
		if (symbol.equals("uint32")) {
			return OME_PIXELTYPE_CLASS_URI + "#uint32";
		}
		if (symbol.equals("float")) {
			return OME_PIXELTYPE_CLASS_URI + "#float";
		}
		if (symbol.equals("double")) {
			return OME_PIXELTYPE_CLASS_URI + "#double";
		}
		if (symbol.equals("complex")) {
			return OME_PIXELTYPE_CLASS_URI + "#complex";
		}
		if (symbol.equals("double-complex")) {
			return OME_PIXELTYPE_CLASS_URI + "#double-complex";
		}
		if (symbol.equals("bit")) {
			return OME_PIXELTYPE_CLASS_URI + "#bit";
		}
		throw new Exception("Unsupported pixelType value: " + symbol);
	}

	// screenType
	static public String getScreenTypeURI(String symbol) throws Exception {
		if (symbol.equals("RNAi")) {
			return OME_SCREENTYPE_CLASS_URI + "#RNAi";
		}
		if (symbol.equals("cDNA")) {
			return OME_SCREENTYPE_CLASS_URI + "#cDNA";
		}
		if (symbol.equals("SiRNA")) {
			return OME_SCREENTYPE_CLASS_URI + "#SiRNA";
		}
		if (symbol.equals("Other")) {
			return OME_SCREENTYPE_CLASS_URI + "#other";
		}
		throw new Exception("Unsupported screenType value: " + symbol);
	}

		
		// medium
	static public String getMediumURI(String symbol) throws Exception {
		if (symbol.equals("Air")) {
			return OME_MEDIUM_CLASS_URI + "#air";
		}
		if (symbol.equals("Oil")) {
			return OME_MEDIUM_CLASS_URI + "#oil";
		}
		if (symbol.equals("Water")) {
			return OME_MEDIUM_CLASS_URI + "#water";
		}
		if (symbol.equals("Glycerol")) {
			return OME_MEDIUM_CLASS_URI + "#glycerol";
		}
		if (symbol.equals("Other")) {
			return OME_MEDIUM_CLASS_URI + "#other";
		}
		throw new Exception("Unsupported medium value: " + symbol);
	}

	// namingConvention
	static public String getNamingConventionURI(String symbol) throws Exception {
		if (symbol.equals("letter")) {
			return OME_NAMINGCONVENTION_CLASS_URI + "#letter";
		}
		if (symbol.equals("number")) {
			return OME_NAMINGCONVENTION_CLASS_URI + "#number";
		}
		throw new Exception("Unsupported medium value: " + symbol);
	}
}

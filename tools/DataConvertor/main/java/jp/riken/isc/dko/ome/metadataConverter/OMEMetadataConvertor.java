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

import loci.formats.ome.OMEXMLMetadata;
import loci.formats.services.OMEXMLService;
import loci.formats.services.OMEXMLServiceImpl;
import ome.units.quantity.ElectricPotential;
import ome.units.quantity.Frequency;
import ome.units.quantity.Length;
import ome.units.quantity.Power;
import ome.units.quantity.Pressure;
import ome.units.quantity.Temperature;
import ome.units.quantity.Time;
import ome.units.unit.Unit;
import ome.xml.meta.OMEXMLMetadataRoot;
import ome.xml.model.AffineTransform;
import ome.xml.model.Annotation;
import ome.xml.model.Arc;
import ome.xml.model.BinData;
import ome.xml.model.BinaryFile;
import ome.xml.model.BinaryOnly;
import ome.xml.model.BooleanAnnotation;
import ome.xml.model.Channel;
import ome.xml.model.CommentAnnotation;
import ome.xml.model.Dataset;
import ome.xml.model.Detector;
import ome.xml.model.DetectorSettings;
import ome.xml.model.Dichroic;
import ome.xml.model.DoubleAnnotation;
import ome.xml.model.Ellipse;
import ome.xml.model.Experiment;
import ome.xml.model.Experimenter;
import ome.xml.model.ExperimenterGroup;
import ome.xml.model.External;
import ome.xml.model.Filament;
import ome.xml.model.FileAnnotation;
import ome.xml.model.Filter;
import ome.xml.model.FilterSet;
import ome.xml.model.Folder;
import ome.xml.model.GenericExcitationSource;
import ome.xml.model.Project;
import ome.xml.model.ROI;
import ome.xml.model.Reagent;
import ome.xml.model.Rectangle;
import ome.xml.model.Rights;
import ome.xml.model.Screen;
import ome.xml.model.Shape;
import ome.xml.model.StageLabel;
import ome.xml.model.StructuredAnnotations;
import ome.xml.model.TagAnnotation;
import ome.xml.model.TermAnnotation;
import ome.xml.model.TiffData;
import ome.xml.model.TimestampAnnotation;
import ome.xml.model.TransmittanceRange;
import ome.xml.model.UUID;
import ome.xml.model.Union;
import ome.xml.model.Well;
import ome.xml.model.WellSample;
import ome.xml.model.XMLAnnotation;
import ome.xml.model.enums.AcquisitionMode;
import ome.xml.model.enums.ArcType;
import ome.xml.model.enums.Binning;
import ome.xml.model.enums.Compression;
import ome.xml.model.enums.ContrastMethod;
import ome.xml.model.enums.Correction;
import ome.xml.model.enums.DetectorType;
import ome.xml.model.enums.DimensionOrder;
import ome.xml.model.enums.ExperimentType;
import ome.xml.model.enums.FilamentType;
import ome.xml.model.enums.FillRule;
import ome.xml.model.enums.FilterType;
import ome.xml.model.enums.FontFamily;
import ome.xml.model.enums.FontStyle;
import ome.xml.model.enums.IlluminationType;
import ome.xml.model.enums.Immersion;
import ome.xml.model.enums.LaserMedium;
import ome.xml.model.enums.LaserType;
import ome.xml.model.enums.Marker;
import ome.xml.model.enums.Medium;
import ome.xml.model.enums.MicrobeamManipulationType;
import ome.xml.model.enums.MicroscopeType;
import ome.xml.model.enums.NamingConvention;
import ome.xml.model.enums.PixelType;
import ome.xml.model.enums.Pulse;
import ome.xml.model.primitives.Color;
import ome.xml.model.primitives.NonNegativeInteger;
import ome.xml.model.primitives.NonNegativeLong;
import ome.xml.model.primitives.PercentFraction;
import ome.xml.model.primitives.PositiveInteger;
import ome.xml.model.primitives.Timestamp;
import loci.common.services.ServiceException;

import ome.xml.model.Image;
import ome.xml.model.ImagingEnvironment;
import ome.xml.model.Instrument;
import ome.xml.model.Label;
import ome.xml.model.Laser;
import ome.xml.model.LightEmittingDiode;
import ome.xml.model.LightPath;
import ome.xml.model.LightSource;
import ome.xml.model.LightSourceSettings;
import ome.xml.model.Line;
import ome.xml.model.ListAnnotation;
import ome.xml.model.LongAnnotation;
import ome.xml.model.MapAnnotation;
import ome.xml.model.MapPair;
import ome.xml.model.Mask;
import ome.xml.model.MetadataOnly;
import ome.xml.model.MicrobeamManipulation;
import ome.xml.model.Microscope;
import ome.xml.model.Objective;
import ome.xml.model.ObjectiveSettings;
import ome.xml.model.Pixels;
import ome.xml.model.Plane;
import ome.xml.model.Plate;
import ome.xml.model.PlateAcquisition;
import ome.xml.model.Point;
import ome.xml.model.Polygon;
import ome.xml.model.Polyline;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class OMEMetadataConvertor {

	// createlog: mapping between java objects and generated RDF resources.
	private static HashMap<Object, Resource> createLog = null;

	// numberTable: the maximum number for automatic numbering for each data class  
	private static HashMap<String, Integer> numberTable = null;

	// idTable: prefix and generated IDs
	private static HashMap<String, HashSet<String>> idTable = null;

	// valueTable: mapping values with units and generated RDF blank nodes.
	private static HashMap<ValueWithUnit, Resource> valueTable = null;

	// colorTable: mapping color values and generated RDF blank nodes.
	private static HashMap<Integer, Resource> colorTable = null;

	
	class ValueWithUnit {
		public Literal value;
		public Resource unit;
		public Resource valueClass;

		public ValueWithUnit(Literal value, Resource unit, Resource valueClass) {
			this.value = value;
			this.unit = unit;
			this.valueClass = valueClass;
		}

		@Override
		public int hashCode() {
			int h = 0;
			if (value != null) {
				h += value.hashCode();
			}
			if (unit != null) {
				h += unit.hashCode();
			}
			if (valueClass != null) {
				h += valueClass.hashCode();
			}
			return h;
		}

		@Override
		public boolean equals(Object obj2) {
			if (obj2 == null) {
				return false;
			}
			if (!(obj2 instanceof ValueWithUnit)) {
				return false;
			}

			ValueWithUnit val2 = (ValueWithUnit) obj2;

			if ((valueClass == null && val2.valueClass == null) || (valueClass != null && valueClass.getURI() != null
					&& val2.valueClass != null && valueClass.getURI().equals(val2.valueClass.getURI()))) {
				// nothing to do
			} else {
				return false;
			}
			if ((unit == null && val2.unit == null) || (unit != null && unit.getURI() != null && val2.unit != null
					&& unit.getURI().equals(val2.unit.getURI()))) {
				// nothing to do
			} else {
				return false;
			}
			if ((value == null && val2.value == null) || (value != null && value.equals(val2.value))) {
				return true;
			}
			return false;
		}
	}

	private void createColorBlankNode(Color color, Model model, Resource subject, Property property) throws Exception {
		if (color != null) {
			Integer colorValue = color.getValue();
			if (colorTable.containsKey(colorValue)) {
				Resource colorNode = colorTable.get(colorValue);
				model.add(subject, property, colorNode);
			} else {
				Resource colorNode = model.createResource();
				Property rdfTypeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
				model.add(colorNode, rdfTypeProp, model.createResource(URISet.OME_COLOR_CLASS_URI));
				model.add(subject, property, colorNode);
				Property rdfValueProp = model.createProperty(URISet.RDF_VALUE_PROERTY_URI);
				model.add(colorNode, rdfValueProp,
						model.createTypedLiteral(colorValue, URISet.XSD_INTEGER_DATATYPE_URI));
				colorTable.put(colorValue, colorNode);
			}
		}
	}

	private void createLengthBlankNodeValue(Length length, String defaultUnitSymbol, Model model, Resource subject,
			Property property, Resource quantityClass) throws Exception {
		if (length != null) {
			float value = length.value().floatValue();
			Unit<Length> unit = length.unit();
			String unitSymbol = unit.getSymbol();
			if (unitSymbol == null) {
				unitSymbol = defaultUnitSymbol;
			}

			Literal valueLiteral = model.createTypedLiteral(value);
			String unitURI = URISet.getLengthUnitURI(unitSymbol);
			Resource unitRes = null;
			if (unitURI != null) {
				unitRes = model.createResource(unitURI);
			}
			ValueWithUnit valueWithUnit = new ValueWithUnit(valueLiteral, unitRes, quantityClass);
			if (valueTable.containsKey(valueWithUnit)) {
				Resource lengthNode = valueTable.get(valueWithUnit);
				model.add(subject, property, lengthNode);
			} else {
				Resource lengthNode = model.createResource();
				Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
				model.add(lengthNode, typeProp, quantityClass);
				model.add(subject, property, lengthNode);
				Property rdfValueProp = model.createProperty(URISet.RDF_VALUE_PROERTY_URI);
				model.add(lengthNode, rdfValueProp, valueLiteral);
				Property qudtUnitProp = model.createProperty(URISet.QUDT_UNIT_PROPERTY_URI);
				if (unitRes != null) {
					model.add(lengthNode, qudtUnitProp, unitRes);
				}
				valueTable.put(valueWithUnit, lengthNode);
			}
		}
	}

	
	private void createTimeBlankNodeValue(Time time, String defaultUnitSymbol, Model model, Resource subject,
			Property property, Resource quantityClass) throws Exception {
		if (time != null) {
			float value = time.value().floatValue();
			Unit<Time> unit = time.unit();
			String unitSymbol = unit.getSymbol();
			if (unitSymbol == null) {
				unitSymbol = defaultUnitSymbol;
			}

			Literal valueLiteral = model.createTypedLiteral(value);
			String unitURI = URISet.getTimeUnitURI(unitSymbol);
			Resource unitRes = null;
			if (unitURI != null) {
				unitRes = model.createResource(unitURI);
			}
			ValueWithUnit valueWithUnit = new ValueWithUnit(valueLiteral, unitRes, quantityClass);
			if (valueTable.containsKey(valueWithUnit)) {
				Resource lengthNode = valueTable.get(valueWithUnit);
				model.add(subject, property, lengthNode);
			} else {
				Resource lengthNode = model.createResource();
				Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
				model.add(lengthNode, typeProp, quantityClass);
				model.add(subject, property, lengthNode);
				Property rdfValueProp = model.createProperty(URISet.RDF_VALUE_PROERTY_URI);
				model.add(lengthNode, rdfValueProp, valueLiteral);
				Property qudtUnitProp = model.createProperty(URISet.QUDT_UNIT_PROPERTY_URI);
				if (unitRes != null) {
					model.add(lengthNode, qudtUnitProp, unitRes);
				}
				valueTable.put(valueWithUnit, lengthNode);
			}
		}
	}

	private void createPressureBlankNodeValue(Pressure pressure, String defaultUnitSymbol, Model model,
			Resource subject, Property property, Resource quantityClass) throws Exception {
		if (pressure != null) {
			float value = pressure.value().floatValue();
			Unit<Pressure> unit = pressure.unit();
			String unitSymbol = unit.getSymbol();
			if (unitSymbol == null) {
				unitSymbol = defaultUnitSymbol;
			}

			Literal valueLiteral = model.createTypedLiteral(value);
			String unitURI = URISet.getPressureUnitURI(unitSymbol);
			Resource unitRes = null;
			if (unitURI != null) {
				unitRes = model.createResource(unitURI);
			}
			ValueWithUnit valueWithUnit = new ValueWithUnit(valueLiteral, unitRes, quantityClass);
			if (valueTable.containsKey(valueWithUnit)) {
				Resource lengthNode = valueTable.get(valueWithUnit);
				model.add(subject, property, lengthNode);
			} else {
				Resource lengthNode = model.createResource();
				Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
				model.add(lengthNode, typeProp, quantityClass);
				model.add(subject, property, lengthNode);
				Property rdfValueProp = model.createProperty(URISet.RDF_VALUE_PROERTY_URI);
				model.add(lengthNode, rdfValueProp, valueLiteral);
				Property qudtUnitProp = model.createProperty(URISet.QUDT_UNIT_PROPERTY_URI);
				if (unitRes != null) {
					model.add(lengthNode, qudtUnitProp, unitRes);
				}
				valueTable.put(valueWithUnit, lengthNode);
			}
		}
	}

	
	private void createTemperatureBlankNodeValue(Temperature temperature, String defaultUnitSymbol, Model model,
			Resource subject, Property property, Resource quantityClass) throws Exception {
		if (temperature != null) {
			float value = temperature.value().floatValue();
			Unit<Temperature> unit = temperature.unit();
			String unitSymbol = unit.getSymbol();
			if (unitSymbol == null) {
				unitSymbol = defaultUnitSymbol;
			}

			Literal valueLiteral = model.createTypedLiteral(value);
			String unitURI = URISet.getTemperatureUnitURI(unitSymbol);
			Resource unitRes = null;
			if (unitURI != null) {
				unitRes = model.createResource(unitURI);
			}
			ValueWithUnit valueWithUnit = new ValueWithUnit(valueLiteral, unitRes, quantityClass);
			if (valueTable.containsKey(valueWithUnit)) {
				Resource lengthNode = valueTable.get(valueWithUnit);
				model.add(subject, property, lengthNode);
			} else {
				Resource lengthNode = model.createResource();
				Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
				model.add(lengthNode, typeProp, quantityClass);
				model.add(subject, property, lengthNode);
				Property rdfValueProp = model.createProperty(URISet.RDF_VALUE_PROERTY_URI);
				model.add(lengthNode, rdfValueProp, valueLiteral);
				Property qudtUnitProp = model.createProperty(URISet.QUDT_UNIT_PROPERTY_URI);
				if (unitRes != null) {
					model.add(lengthNode, qudtUnitProp, unitRes);
				}
				valueTable.put(valueWithUnit, lengthNode);
			}
		}
	}

	
	private void createPowerBlankNodeValue(Power power, String defaultUnitSymbol, Model model, Resource subject,
			Property property, Resource quantityClass) throws Exception {
		if (power != null) {
			float value = power.value().floatValue();
			Unit<Power> unit = power.unit();
			String unitSymbol = unit.getSymbol();
			if (unitSymbol == null) {
				unitSymbol = defaultUnitSymbol;
			}

			Literal valueLiteral = model.createTypedLiteral(value);
			String unitURI = URISet.getPowerUnitURI(unitSymbol);
			Resource unitRes = null;
			if (unitURI != null) {
				unitRes = model.createResource(unitURI);
			}
			ValueWithUnit valueWithUnit = new ValueWithUnit(valueLiteral, unitRes, quantityClass);
			if (valueTable.containsKey(valueWithUnit)) {
				Resource lengthNode = valueTable.get(valueWithUnit);
				model.add(subject, property, lengthNode);
			} else {
				Resource lengthNode = model.createResource();
				Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
				model.add(lengthNode, typeProp, quantityClass);
				model.add(subject, property, lengthNode);
				Property rdfValueProp = model.createProperty(URISet.RDF_VALUE_PROERTY_URI);
				model.add(lengthNode, rdfValueProp, valueLiteral);
				Property qudtUnitProp = model.createProperty(URISet.QUDT_UNIT_PROPERTY_URI);
				if (unitRes != null) {
					model.add(lengthNode, qudtUnitProp, unitRes);
				}
				valueTable.put(valueWithUnit, lengthNode);
			}
		}
	}

	private void createFrequencyBlankNodeValue(Frequency frequency, String defaultUnitSymbol, Model model,
			Resource subject, Property property, Resource quantityClass) throws Exception {
		if (frequency != null) {
			float value = frequency.value().floatValue();
			Unit<Frequency> unit = frequency.unit();
			String unitSymbol = unit.getSymbol();
			if (unitSymbol == null) {
				unitSymbol = defaultUnitSymbol;
			}

			Literal valueLiteral = model.createTypedLiteral(value);
			String unitURI = URISet.getFrequencyUnitURI(unitSymbol);
			Resource unitRes = null;
			if (unitURI != null) {
				unitRes = model.createResource(unitURI);
			}
			ValueWithUnit valueWithUnit = new ValueWithUnit(valueLiteral, unitRes, quantityClass);
			if (valueTable.containsKey(valueWithUnit)) {
				Resource lengthNode = valueTable.get(valueWithUnit);
				model.add(subject, property, lengthNode);
			} else {
				Resource lengthNode = model.createResource();
				Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
				model.add(lengthNode, typeProp, quantityClass);
				model.add(subject, property, lengthNode);
				Property rdfValueProp = model.createProperty(URISet.RDF_VALUE_PROERTY_URI);
				model.add(lengthNode, rdfValueProp, valueLiteral);
				Property qudtUnitProp = model.createProperty(URISet.QUDT_UNIT_PROPERTY_URI);
				if (unitRes != null) {
					model.add(lengthNode, qudtUnitProp, unitRes);
				}
				valueTable.put(valueWithUnit, lengthNode);
			}
		}
	}

	private void createElectricPotentialBlankNodeValue(ElectricPotential electricPotential, String defaultUnitSymbol,
			Model model, Resource subject, Property property, Resource quantityClass) throws Exception {
		if (electricPotential != null) {
			float value = electricPotential.value().floatValue();
			Unit<ElectricPotential> unit = electricPotential.unit();
			String unitSymbol = unit.getSymbol();
			if (unitSymbol == null) {
				unitSymbol = defaultUnitSymbol;
			}

			Literal valueLiteral = model.createTypedLiteral(value);
			String unitURI = URISet.getElectricPotentialUnitURI(unitSymbol);
			Resource unitRes = null;
			if (unitURI != null) {
				unitRes = model.createResource(unitURI);
			}
			ValueWithUnit valueWithUnit = new ValueWithUnit(valueLiteral, unitRes, quantityClass);
			if (valueTable.containsKey(valueWithUnit)) {
				Resource lengthNode = valueTable.get(valueWithUnit);
				model.add(subject, property, lengthNode);
			} else {
				Resource lengthNode = model.createResource();
				Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
				model.add(lengthNode, typeProp, quantityClass);
				model.add(subject, property, lengthNode);
				Property rdfValueProp = model.createProperty(URISet.RDF_VALUE_PROERTY_URI);
				model.add(lengthNode, rdfValueProp, valueLiteral);
				Property qudtUnitProp = model.createProperty(URISet.QUDT_UNIT_PROPERTY_URI);
				if (unitRes != null) {
					model.add(lengthNode, qudtUnitProp, unitRes);
				}
				valueTable.put(valueWithUnit, lengthNode);
			}
		}
	}

	public static void main(String[] args) throws Exception {

// for just local tests
// 	     if (args.length == 0) {
//			String inputFile = "/temp/ome/xml";
//			String outputFile = "/temp/ome/rdf";
//			String rdfFormat = "Turtle";
//			String instanceNamespace = "http://org.openmicroscopy.org/rdf/2016-06/ome_core/samples/";
//
//			OMEMetadataConvertor loader = new OMEMetadataConvertor();
//			loader.convertToRDF(inputFile, outputFile, rdfFormat, instanceNamespace);
//			return;
//		}

		if (args.length != 3) {
			System.out.println(
					"Usage: java -jar OMEMetadataConvertor.jar <inputFile/Dir> <outputFile/Dir> <rdfFormat> <namespace>");
		}
		String inputFile = args[0];
		String outputFile = args[1];
		String rdfFormat = args[2];
		String instanceNamespace = args[3];

		OMEMetadataConvertor loader = new OMEMetadataConvertor();
		loader.convertToRDF(inputFile, outputFile, rdfFormat, instanceNamespace);
	}

	public void convertToRDF(String inputFileStr, String outputFileStr, String rdfFormat, String instanceNamespace)
			throws Exception {

		String ext = getExtension(rdfFormat);

		OMEMetadataConvertor loader = new OMEMetadataConvertor();
		File inFile = new File(inputFileStr);
		File outFile = new File(outputFileStr);
		if (!inFile.exists()) {
			throw new Exception("InputFile not found: " + inputFileStr);
		}
		if (inFile.isFile()) {
			if (outFile.exists() && outFile.isDirectory()) {
				String fileName = inFile.getName();
				outFile = new File(outFile, fileName + "." + ext);
			}
		} else {
			if (inFile.isDirectory()) {
				if (!outFile.exists()) {
					outFile.mkdirs();
				} else {
					if (outFile.isFile()) {
						throw new Exception("OutputFile is an exsiting file: " + outputFileStr);
					}
				}
			}
		}

		// convert
		if (inFile.isFile()) {
			loader.toRDF(inFile, outFile, rdfFormat, instanceNamespace);
		}
		if (inFile.isDirectory()) {
			File[] files = inFile.listFiles();
			for (File file : files) {
				try {
					String inFileName = file.getName();
					File dOutFile = new File(outFile, inFileName + "." + ext);
					loader.toRDF(file, dOutFile, rdfFormat, instanceNamespace);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private String getExtension(String rdfFormat) throws Exception {
		if (rdfFormat == null) {
			throw new Exception("RDF format is null");
		}

		// turtle
		if (rdfFormat.equals("TURTLE") || rdfFormat.equals("TTL") || rdfFormat.equals("Turtle")) {
			return "ttl";
		}
		// ntriples
		if (rdfFormat.equals("N-TRIPLES") || rdfFormat.equals("N-TRIPLE") || rdfFormat.equals("NT")) {
			return "nt";
		}
		// JSON
		if (rdfFormat.equals("JSON-LD") || rdfFormat.equals("RDF/JSON")) {
			return "json";
		}
		// xml
		if (rdfFormat.equals("RDF/XML-ABBREV") || rdfFormat.equals("RDF/XML")) {
			return "xml";
		}
		// n3
		if (rdfFormat.equals("N3")) {
			return "n3";
		}
		throw new Exception("RDF format error: " + rdfFormat);
	}

	public OMEMetadataConvertor() {
		;
	}

	public void toRDF(File inFile, File outFile, String rdfFormat, String instanceNamespace) throws Exception {

		System.out.println(inFile);
		String xml = null;
		xml = readAll(inFile.getCanonicalPath());

		OMEXMLService service = new OMEXMLServiceImpl();
		OMEXMLMetadata metadata = null;
		try {
			metadata = service.createOMEXMLMetadata(xml, "2016-06");
		} catch (ServiceException ex) {
			;
		}
		createLog = new HashMap<Object, Resource>();
		numberTable = new HashMap<String, Integer>();
		idTable = new HashMap<String, HashSet<String>>();
		valueTable = new HashMap<ValueWithUnit, Resource>();
		colorTable = new HashMap<Integer, Resource>();

		toRDF(metadata, outFile, rdfFormat, instanceNamespace);

		createLog.clear();
		numberTable.clear();
		idTable.clear();
		valueTable.clear();
		colorTable.clear();
	}

	private String getUniqueID(String prefix, String candidate) {
		if (idTable.containsKey(prefix)) {
			HashSet<String> idList = idTable.get(prefix);
			if (candidate != null && !idList.contains(candidate)) {
				idList.add(candidate);
				return candidate;
			} else {
				String id = null;
				while (true) {
					id = generateNewID(prefix);
					if (!idList.contains(id)) {
						idList.add(id);
						break;
					}
				}
				return id;
			}
		} else {
			String id = null;
			if (candidate != null) {
				id = candidate;
			} else {
				id = generateNewID(prefix);
			}
			HashSet<String> idList = new HashSet<String>();
			idList.add(id);
			idTable.put(prefix, idList);
			return id;
		}
	}

	private String generateNewID(String prefix) {
		String newID = null;
		if (numberTable.containsKey(prefix)) {
			Integer integer = numberTable.get(prefix);
			int val = integer.intValue() + 1;
			newID = prefix + ":" + val;
			numberTable.remove(prefix);
			numberTable.put(prefix, Integer.valueOf(val));
		} else {
			numberTable.put(prefix, Integer.valueOf(0));
			newID = prefix + ":0";
		}
		return newID;
	}

	public static String readAll(final String path) throws IOException {
		return Files.lines(Paths.get(path), Charset.forName("UTF-8"))
				.collect(Collectors.joining(System.getProperty("line.separator")));
	}

	public void toRDF(OMEXMLMetadata metadata, File outFile, String rdfFormat, String instanceNamespace)
			throws Exception {

		// model
		Model model = ModelFactory.createDefaultModel();

		model.setNsPrefixes(URISet.prefixMap);
		model.setNsPrefix("ins", instanceNamespace);

		OMEXMLMetadataRoot root = (OMEXMLMetadataRoot) metadata.getRoot();

		String uuid = root.getUUID();
		if (uuid != null) {
			Property uuid_prop = model.createProperty(URISet.OME_UUID_PROPERTY_URI);

			// TODO the subject is the RDF itself. the blank node will be replaced.
			model.add(model.createResource(), uuid_prop, uuid);
		}

		Rights rights = root.getRights();
		toRDF(rights, model, instanceNamespace);

		// project
		int projectCnt = root.sizeOfProjectList();
		for (int i = 0; i < projectCnt; i++) {
			Project project = root.getProject(i);
			toRDF(project, model, instanceNamespace);
		}

		// dataset
		int datasetCnt = root.sizeOfDatasetList();
		for (int i = 0; i < datasetCnt; i++) {
			Dataset dataset = root.getDataset(i);
			toRDF(dataset, model, instanceNamespace);
		}

		// folder
		int folderCnt = root.sizeOfFolderList();
		for (int i = 0; i < folderCnt; i++) {
			Folder folder = root.getFolder(i);
			toRDF(folder, model, instanceNamespace);
		}

		// experiment
		int experimentCnt = root.sizeOfExperimentList();
		for (int i = 0; i < experimentCnt; i++) {
			Experiment experiment = root.getExperiment(i);
			toRDF(experiment, model, instanceNamespace);
		}

		// plate
		int plateCnt = root.sizeOfPlateList();
		for (int i = 0; i < plateCnt; i++) {
			Plate plate = root.getPlate(i);
			toRDF(plate, model, instanceNamespace);
		}

		// screen
		int screenCnt = root.sizeOfScreenList();
		for (int i = 0; i < screenCnt; i++) {
			Screen screen = root.getScreen(i);
			toRDF(screen, model, instanceNamespace);
		}

		// experimenter
		int experimenterCnt = root.sizeOfExperimenterList();
		for (int i = 0; i < experimenterCnt; i++) {
			Experimenter experimenter = root.getExperimenter(i);
			toRDF(experimenter, model, instanceNamespace);
		}

		// experimenterGroup
		int experimenterGroupCnt = root.sizeOfExperimenterGroupList();
		for (int i = 0; i < experimenterGroupCnt; i++) {
			ExperimenterGroup experimenterGroup = root.getExperimenterGroup(i);
			toRDF(experimenterGroup, model, instanceNamespace);
		}

		// instrument
		int instrumentCnt = root.sizeOfInstrumentList();
		for (int i = 0; i < instrumentCnt; i++) {
			Instrument instrument = root.getInstrument(i);
			toRDF(instrument, model, instanceNamespace);
		}

		// image
		int imageCnt = root.sizeOfImageList();
		for (int i = 0; i < imageCnt; i++) {
			Image image = root.getImage(i);
			toRDF(image, model, instanceNamespace);
		}

		// structuredannotations
		StructuredAnnotations structuredAnnotations = root.getStructuredAnnotations();
		toRDF(structuredAnnotations, model, instanceNamespace);

		// roi
		int roiCnt = root.sizeOfROIList();
		for (int i = 0; i < roiCnt; i++) {
			ROI roi = root.getROI(i);
			toRDF(roi, model, instanceNamespace);
		}

		// binaryOnly
		BinaryOnly binaryOnly = root.getBinaryOnly();
		toRDF(binaryOnly, model, instanceNamespace);

//		System.out.println(metadata.dumpXML());
//		StringWriter stringWriter = new StringWriter();
//		model.write(stringWriter, rdfFormat);
//		System.out.println(stringWriter.toString());

		FileWriter fileWriter = new FileWriter(outFile);
		model.write(fileWriter, rdfFormat);
		fileWriter.flush();
		fileWriter.close();

	}

//----------------------------------------------------------------------------------------
//
//  toRDF for each OME-XML model class.
//
//----------------------------------------------------------------------------------------

//-------------------------------------------------------------------------------------------------------------------
//  Rights
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Rights rights, Model model, String instanceNamespace) throws Exception {
		if (rights == null) {
			return;
		}
		if (createLog.containsKey(rights)) {
			return;
		}

		String genID = getUniqueID("rights", null);

		String rightsHeld = rights.getRightsHeld();
		String rightsHolder = rights.getRightsHolder();
		Resource rightsRef = model.createResource(instanceNamespace + "Rights/" + genID);
		Resource rightsClassRef = model.createResource(URISet.OME_RIGHTS_CLASS_URI);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		model.add(rightsRef, typeProp, rightsClassRef);
		if (rightsHolder != null) {
			Property rightsHolderProp = model.createProperty(URISet.OME_RIGHTSHOLDER_PROPERTY_URI);
			model.add(rightsRef, rightsHolderProp, rightsHolder);
		}
		if (rightsHeld != null) {
			Property rightsHeldProp = model.createProperty(URISet.OME_RIGHTSHELD_PROPERTY_URI);
			model.add(rightsRef, rightsHeldProp, rightsHeld);
		}

		createLog.put(rights, rightsRef);
	}

//-------------------------------------------------------------------------------------------------------------------
//  Project
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Project project, Model model, String instanceNamespace) throws Exception {
		toRDF(project, null, model, instanceNamespace);
	}

	public void toRDF(Project project, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (project == null) {
			return;
		}
		if (createLog.containsKey(project)) {
			if (origin != null) {
				Resource res = createLog.get(project);
				Property projectProp = model.createProperty(URISet.OME_PROJECT_PROPERTY_URI);
				model.add(origin, projectProp, res);
			}
			return;
		}

		// id
		String id = project.getID();
		String genID = getUniqueID("project", id);
		Resource projectRes = model.createResource(instanceNamespace + "Project/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource projectClsRes = model.createResource(URISet.OME_PROJECT_CLASS_URI);
		model.add(projectRes, typeProp, projectClsRes);
		if (id != null) {
			Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
			model.add(projectRes, idProp, id);
		}
		if (origin != null) {
			Property projectProp = model.createProperty(URISet.OME_PROJECT_PROPERTY_URI);
			model.add(origin, projectProp, projectRes);
		}
		createLog.put(project, projectRes);

		// description
		String description = project.getDescription();
		if (description != null) {
			Property commentProp = model.createProperty(URISet.RDFS_COMMENT_PROPERTY_URI);
			model.add(projectRes, commentProp, description, "en");
		}

		// name
		String name = project.getName();
		if (name != null) {
			Property labelProp = model.createProperty(URISet.RDFS_LABEL_PROPERTY_URI);
			model.add(projectRes, labelProp, name, "en");
		}

		// link
		// experimenter
		Experimenter experimenter = project.getLinkedExperimenter();
		if (experimenter != null) {
			toRDF(experimenter, projectRes, null, model, instanceNamespace);
		}

		// experimenterGroup
		ExperimenterGroup experimenterGroup = project.getLinkedExperimenterGroup();
		if (experimenterGroup != null) {
			toRDF(experimenterGroup, projectRes, model, instanceNamespace);
		}

		// annotation
		int linkedAnnotationCnt = project.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation annotation = project.getLinkedAnnotation(k);
			toRDF(annotation, projectRes, model, instanceNamespace);
		}

		// dataset
		int linkedDatasetCnt = project.sizeOfLinkedDatasetList();
		for (int k = 0; k < linkedDatasetCnt; k++) {
			Dataset dataset = project.getLinkedDataset(k);
			toRDF(dataset, projectRes, model, instanceNamespace);
		}

	}

//-------------------------------------------------------------------------------------------------------------------
//  Annotation
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Annotation annotation, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (annotation == null) {
			return;
		}
		if (createLog.containsKey(annotation)) {
			if (origin != null) {
				Resource res = createLog.get(annotation);
				Property annotationProp = model.createProperty(URISet.OME_ANNOTATION_PROPERTY_URI);
				model.add(origin, annotationProp, res);
			}
			return;
		}

		String id = annotation.getID();
		Resource annotationRes = null;
		Property rdfTypeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Property rdfValueProp = model.createProperty(URISet.RDF_VALUE_PROERTY_URI);
		if (annotation instanceof XMLAnnotation) {
			XMLAnnotation xmlAnnotation = (XMLAnnotation) annotation;
			String value = xmlAnnotation.getValue();
			String genID = getUniqueID("xmlAnnotation", id);
			annotationRes = model.createResource(instanceNamespace + "XMLAnnotation/" + genID);
			Resource xmlAnnotationClassRes = model.createResource(URISet.OME_XMLANNOTATION_CLASS_URI);
			model.add(annotationRes, rdfTypeProp, xmlAnnotationClassRes);
			if (value != null) {
				model.add(annotationRes, rdfValueProp,
						model.createTypedLiteral(value, URISet.RDF_XMLLITERAL_DATATYPE_URI));
			}
		}
		if (annotation instanceof BooleanAnnotation) {
			BooleanAnnotation booleanAnnotation = (BooleanAnnotation) annotation;
			Boolean value = booleanAnnotation.getValue();
			String genID = getUniqueID("booleanAnnotation", id);
			annotationRes = model.createResource(instanceNamespace + "BooleanAnnotation/" + genID);
			Resource booleanAnnotationClassRes = model.createResource(URISet.OME_BOOLEANANNOTATION_CLASS_URI);
			model.add(annotationRes, rdfTypeProp, booleanAnnotationClassRes);
			if (value != null) {
				model.add(annotationRes, rdfValueProp, model.createTypedLiteral(value));
			}
		}
		if (annotation instanceof CommentAnnotation) {
			CommentAnnotation commentAnnotation = (CommentAnnotation) annotation;
			String value = commentAnnotation.getValue();
			String genID = getUniqueID("commentAnnotation", id);
			annotationRes = model.createResource(instanceNamespace + "CommentAnnotation/" + genID);
			Resource commentAnnotationClassRes = model.createResource(URISet.OME_COMMENTANNOTATION_CLASS_URI);
			model.add(annotationRes, rdfTypeProp, commentAnnotationClassRes);
			if (value != null) {
				model.add(annotationRes, rdfValueProp, value, "en");
			}
		}
		if (annotation instanceof DoubleAnnotation) {
			DoubleAnnotation doubleAnnotation = (DoubleAnnotation) annotation;
			Double value = doubleAnnotation.getValue();
			String genID = getUniqueID("doubleAnnotation", id);
			annotationRes = model.createResource(instanceNamespace + "DoubleAnnotation/" + genID);
			Resource doubleAnnotationClassRes = model.createResource(URISet.OME_DOUBLEANNOTATION_CLASS_URI);
			model.add(annotationRes, rdfTypeProp, doubleAnnotationClassRes);
			if (value != null) {
				model.add(annotationRes, rdfValueProp, model.createTypedLiteral(value));
			}
		}
		if (annotation instanceof FileAnnotation) {
			FileAnnotation fileAnnotation = (FileAnnotation) annotation;
			BinaryFile value = fileAnnotation.getBinaryFile();
			String genID = getUniqueID("fileAnnotation", id);
			annotationRes = model.createResource(instanceNamespace + "FileAnnotation/" + genID);
			Resource fileAnnotationClsRes = model.createResource(URISet.OME_FILEANNOTATION_CLASS_URI);
			model.add(annotationRes, rdfTypeProp, fileAnnotationClsRes);
			if (value != null) {
				String binDataGenID = getUniqueID("binaryFile", null);
				Resource binaryFileRes = model.createResource(instanceNamespace + "BinaryFile/" + binDataGenID);
				Resource binaryFileClsRes = model.createResource(URISet.OME_BINARYFILE_CLASS_URI);
				model.add(binaryFileRes, rdfTypeProp, binaryFileClsRes);
				model.add(annotationRes, rdfValueProp, binaryFileRes);

				// binaryFile
				BinData binData = value.getBinData();
				if (binData != null) {
					toRDF(binData, binaryFileRes, model, instanceNamespace);
				}

				// external
				External external = value.getExternal();
				if (external != null) {
					toRDF(external, binaryFileRes, model, instanceNamespace);
				}

				// fillName
				String fileName = value.getFileName();
				if (fileName != null) {
					Property rdfsLabelProp = model.createProperty(URISet.RDFS_LABEL_PROPERTY_URI);
					model.add(binaryFileRes, rdfsLabelProp, fileName);
				}

				// mimeType
				String mimeType = value.getMIMEType();
				if (mimeType != null) {
					Property mimeTypeProp = model.createProperty(URISet.OME_MIMETYPE_PROPERTY_URI);
					model.add(binaryFileRes, mimeTypeProp, mimeType);
				}

				// size
				NonNegativeLong size = value.getSize();
				if (size != null) {
					Property sizeProp = model.createProperty(URISet.OME_SIZE_PROPERTY_URI);
					model.add(binaryFileRes, sizeProp, model.createTypedLiteral(size.getValue().longValue(),
							URISet.XSD_NONNEGATIVELONG_DATATYPE_URI));
				}
			}
		}
		if (annotation instanceof ListAnnotation) {
			ListAnnotation listAnnotation = (ListAnnotation) annotation;
			StructuredAnnotations value = listAnnotation.getStructuredAnnotations();
			String genID = getUniqueID("listAnnotation", id);
			annotationRes = model.createResource(instanceNamespace + "ListAnnotation/" + genID);
			Resource listAnnotationClsRes = model.createResource(URISet.OME_LISTANNOTATION_CLASS_URI);
			model.add(annotationRes, rdfTypeProp, listAnnotationClsRes);
			if (value != null) {
				toRDF(value, annotationRes, rdfValueProp, model, instanceNamespace);
			}
		}
		if (annotation instanceof LongAnnotation) {
			LongAnnotation longAnnotation = (LongAnnotation) annotation;
			Long value = longAnnotation.getValue();
			String genID = getUniqueID("longAnnotation", id);
			annotationRes = model.createResource(instanceNamespace + "LongAnnotation/" + genID);
			Resource longAnnotationClassRes = model.createResource(URISet.OME_LONGANNOTATION_CLASS_URI);
			model.add(annotationRes, rdfTypeProp, longAnnotationClassRes);
			if (value != null) {
				model.add(annotationRes, rdfValueProp, model.createTypedLiteral(value));
			}
		}
		if (annotation instanceof MapAnnotation) {
			MapAnnotation mapAnnotation = (MapAnnotation) annotation;
			List<MapPair> value = mapAnnotation.getValue();
			String genID = getUniqueID("mapAnnotation", null);
			annotationRes = model.createResource(instanceNamespace + "MapAnnotation/" + genID);
			Resource mapAnnotationClassRes = model.createResource(URISet.OME_MAPANNOTATION_CLASS_URI);
			model.add(annotationRes, rdfTypeProp, mapAnnotationClassRes);
			// mapPair
			if (value != null) {
				Property mapProp = model.createProperty(URISet.OME_MAP_PROPERTY_URI);
				Property mapKeyProp = model.createProperty(URISet.OME_MAPKEY_PROPERTY_URI);
				Property mapValueProp = model.createProperty(URISet.OME_MAPVALUE_PROPERTY_URI);
				for (MapPair mapPair : value) {
					String key = mapPair.getName();
					String val = mapPair.getValue();
					Resource mapRes = model.createResource();
					model.add(annotationRes, mapProp, mapRes);
					model.add(mapRes, mapKeyProp, key);
					model.add(mapRes, mapValueProp, val);
				}
			}
		}
		if (annotation instanceof TagAnnotation) {
			TagAnnotation tagAnnotation = (TagAnnotation) annotation;
			String value = tagAnnotation.getValue();
			String genID = getUniqueID("tagAnnotation", id);
			annotationRes = model.createResource(instanceNamespace + "TagAnnotation/" + genID);
			Resource tagAnnotationClassRes = model.createResource(URISet.OME_TAGANNOTATION_CLASS_URI);
			model.add(annotationRes, rdfTypeProp, tagAnnotationClassRes);
			if (value != null) {
				model.add(annotationRes, rdfValueProp, model.createTypedLiteral(value));
			}
		}
		if (annotation instanceof TermAnnotation) {
			TermAnnotation termAnnotation = (TermAnnotation) annotation;
			String value = termAnnotation.getValue();
			String genID = getUniqueID("termAnnotation", id);
			annotationRes = model.createResource(instanceNamespace + "TermAnnotation/" + genID);
			Resource termAnnotationClassRes = model.createResource(URISet.OME_TERMANNOTATION_CLASS_URI);
			model.add(annotationRes, rdfTypeProp, termAnnotationClassRes);
			if (value != null) {
				model.add(annotationRes, rdfValueProp, model.createTypedLiteral(value));
			}
		}
		if (annotation instanceof TimestampAnnotation) {
			TimestampAnnotation timestampAnnotation = (TimestampAnnotation) annotation;
			Timestamp value = timestampAnnotation.getValue();
			String genID = getUniqueID("timestampAnnotation", id);
			annotationRes = model.createResource(instanceNamespace + "TimestampAnnotation/" + genID);
			Resource timestampAnnotationClassRes = model.createResource(URISet.OME_TIMESTAMPANNOTATION_CLASS_URI);
			model.add(annotationRes, rdfTypeProp, timestampAnnotationClassRes);
			if (value != null) {
				model.add(annotationRes, rdfValueProp,
						model.createTypedLiteral(value.getValue(), URISet.XSD_DATETIME_DATATYPE_URI));
			}
		}

		if (id != null) {
			Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
			model.add(annotationRes, idProp, id);
		}

		createLog.put(annotation, annotationRes);
		if (origin != null) {
			Property annotationProp = model.createProperty(URISet.OME_ANNOTATION_PROPERTY_URI);
			model.add(origin, annotationProp, annotationRes);
		}

		// description
		String description = annotation.getDescription();
		if (description != null) {
			Property commentProp = model.createProperty(URISet.RDFS_COMMENT_PROPERTY_URI);
			model.add(annotationRes, commentProp, description, "en");
		}

		// name
		String namespace = annotation.getNamespace();
		if (namespace != null) {
			Property namespaceProp = model.createProperty(URISet.OME_NAMESPACE_PROPERTY_URI);
			model.add(annotationRes, namespaceProp, namespace);
		}

		String annotator = annotation.getAnnotator();
		if (annotator != null) {
			String personGenID = getUniqueID("person", null);
			Resource personRef = model.createResource(instanceNamespace + "Person/" + personGenID);
			Property rdfsLabelProp = model.createProperty(URISet.RDFS_LABEL_PROPERTY_URI);
			model.add(personRef, rdfsLabelProp, annotator);
			Property annotatorProp = model.createProperty(URISet.OME_ANNOTATOR_PROPERTY_URI);
			model.add(annotationRes, annotatorProp, personRef);
			Resource personClsRes = model.createResource(URISet.FOAF_PERSON_CLASS_URI);
			model.add(personRef, rdfTypeProp, personClsRes);
		}

		// link
		// annotation
		int linkedAnnotationCnt = annotation.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation linkedAnnotation = annotation.getLinkedAnnotation(k);
			toRDF(linkedAnnotation, annotationRes, model, instanceNamespace);
		}

//		// channel
//		int linkedChannelCnt = annotation.sizeOfLinkedChannelList();
//		for (int k = 0; k < linkedChannelCnt; k++) {
//			Channel linkedChannel = annotation.getLinkedChannel(k);
//			toRDF(linkedChannel, annotationRes, model, instanceNamespace);
//		}
//
//		// dataset
//		int linkedDatasetCnt = annotation.sizeOfLinkedDatasetList();
//		for (int k = 0; k < linkedDatasetCnt; k++) {
//			Dataset linkedDataset = annotation.getLinkedDataset(k);
//			toRDF(linkedDataset, annotationRes, model, instanceNamespace);
//		}
//
//		// detector
//		int linkedDetectorCnt = annotation.sizeOfLinkedDetectorList();
//		for (int k = 0; k < linkedDetectorCnt; k++) {
//			Detector linkedDetector = annotation.getLinkedDetector(k);
//			toRDF(linkedDetector, annotationRes, model, instanceNamespace);
//		}
//
//		// dichroic
//		int linkedDichroicCnt = annotation.sizeOfLinkedDichroicList();
//		for (int k = 0; k < linkedDichroicCnt; k++) {
//			Dichroic linkedDichroic = annotation.getLinkedDichroic(k);
//			toRDF(linkedDichroic, annotationRes, model, instanceNamespace);
//		}
//
//		// experimenter
//		int linkedExperimenterCnt = annotation.sizeOfLinkedExperimenterList();
//		for (int k = 0; k < linkedExperimenterCnt; k++) {
//			Experimenter linkedExperimenter = annotation.getLinkedExperimenter(k);
//			toRDF(linkedExperimenter, annotationRes, null, model, instanceNamespace);
//		}
//
//		// experimenterGroup
//		int linkedExperimenterGroupCnt = annotation.sizeOfLinkedExperimenterGroupList();
//		for (int k = 0; k < linkedExperimenterGroupCnt; k++) {
//			ExperimenterGroup linkedExperimenterGroup = annotation.getLinkedExperimenterGroup(k);
//			toRDF(linkedExperimenterGroup, annotationRes, model, instanceNamespace);
//		}
//
//		// filter
//		int linkedFilterCnt = annotation.sizeOfLinkedFilterList();
//		for (int k = 0; k < linkedFilterCnt; k++) {
//			Filter linkedFilter = annotation.getLinkedFilter(k);
//			toRDF(linkedFilter, annotationRes, model, instanceNamespace);
//		}
//
//		// folder
//		int linkedFolderCnt = annotation.sizeOfLinkedFolderList();
//		for (int k = 0; k < linkedFolderCnt; k++) {
//			Folder linkedFolder = annotation.getLinkedFolder(k);
//			toRDF(linkedFolder, annotationRes, model, instanceNamespace);
//		}
//
//		// image
//		int linkedImageCnt = annotation.sizeOfLinkedImageList();
//		for (int k = 0; k < linkedImageCnt; k++) {
//			Image linkedImage = annotation.getLinkedImage(k);
//			toRDF(linkedImage, annotationRes, model, instanceNamespace);
//		}
//
//		// instrument
//		int linkedInstrumentCnt = annotation.sizeOfLinkedInstrumentList();
//		for (int k = 0; k < linkedInstrumentCnt; k++) {
//			Instrument linkedInstrument = annotation.getLinkedInstrument(k);
//			toRDF(linkedInstrument, annotationRes, model, instanceNamespace);
//		}
//
//		// lightPath
//		int linkedLightPathCnt = annotation.sizeOfLinkedLightPathList();
//		for (int k = 0; k < linkedLightPathCnt; k++) {
//			LightPath linkedLightPath = annotation.getLinkedLightPath(k);
//			toRDF(linkedLightPath, annotationRes, model, instanceNamespace);
//		}
//
//		// lightSource
//		int linkedLightSourceCnt = annotation.sizeOfLinkedLightSourceList();
//		for (int k = 0; k < linkedLightSourceCnt; k++) {
//			LightSource linkedLightSource = annotation.getLinkedLightSource(k);
//			toRDF(linkedLightSource, annotationRes, model, instanceNamespace);
//		}
//
//		// objective
//		int linkedObjectiveCnt = annotation.sizeOfLinkedObjectiveList();
//		for (int k = 0; k < linkedObjectiveCnt; k++) {
//			Objective linkedObjective = annotation.getLinkedObjective(k);
//			toRDF(linkedObjective, annotationRes, model, instanceNamespace);
//		}
//
//		// plane
//		int linkedPlaneCnt = annotation.sizeOfLinkedPlaneList();
//		for (int k = 0; k < linkedPlaneCnt; k++) {
//			Plane linkedPlane = annotation.getLinkedPlane(k);
//			toRDF(linkedPlane, annotationRes, model, instanceNamespace);
//		}
//
//		// plate
//		int linkedPlateCnt = annotation.sizeOfLinkedPlateList();
//		for (int k = 0; k < linkedPlateCnt; k++) {
//			Plate linkedPlate = annotation.getLinkedPlate(k);
//			toRDF(linkedPlate, annotationRes, model, instanceNamespace);
//		}
//
//		// plateAcquisition
//		int linkedPlateAcquisitionCnt = annotation.sizeOfLinkedPlateAcquisitionList();
//		for (int k = 0; k < linkedPlateAcquisitionCnt; k++) {
//			PlateAcquisition linkedPlateAcquisition = annotation.getLinkedPlateAcquisition(k);
//			toRDF(linkedPlateAcquisition, annotationRes, model, instanceNamespace);
//		}
//
//		// project
//		int linkedProjectCnt = annotation.sizeOfLinkedProjectList();
//		for (int k = 0; k < linkedProjectCnt; k++) {
//			Project linkedProject = annotation.getLinkedProject(k);
//			toRDF(linkedProject, annotationRes, model, instanceNamespace);
//		}
//
//		// reagent
//		int linkedReagentCnt = annotation.sizeOfLinkedReagentList();
//		for (int k = 0; k < linkedReagentCnt; k++) {
//			Reagent linkedReagent = annotation.getLinkedReagent(k);
//			toRDF(linkedReagent, annotationRes, model, instanceNamespace);
//		}
//
//		// ROI
//		int linkedROICnt = annotation.sizeOfLinkedROIList();
//		for (int k = 0; k < linkedROICnt; k++) {
//			ROI linkedROI = annotation.getLinkedROI(k);
//			toRDF(linkedROI, annotationRes, model, instanceNamespace);
//		}
//
//		// screen
//		int linkedScreenCnt = annotation.sizeOfLinkedScreenList();
//		for (int k = 0; k < linkedScreenCnt; k++) {
//			Screen linkedScreen = annotation.getLinkedScreen(k);
//			toRDF(linkedScreen, annotationRes, model, instanceNamespace);
//		}
//
//		// shape
//		int linkedShapeCnt = annotation.sizeOfLinkedShapeList();
//		for (int k = 0; k < linkedShapeCnt; k++) {
//			Shape linkedShape = annotation.getLinkedShape(k);
//			toRDF(linkedShape, annotationRes, model, instanceNamespace);
//		}
//
//		// well
//		int linkedWellCnt = annotation.sizeOfLinkedWellList();
//		for (int k = 0; k < linkedWellCnt; k++) {
//			Well linkedWell = annotation.getLinkedWell(k);
//			toRDF(linkedWell, annotationRes, model, instanceNamespace);
//		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  External
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(External external, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (external == null) {
			return;
		}
		if (createLog.containsKey(external)) {
			if (origin != null) {
				Property externalProp = model.createProperty(URISet.OME_EXTERNAL_PROPERTY_URI);
				Resource res = createLog.get(external);
				model.add(origin, externalProp, res);
			}
			return;
		}

		String genID = getUniqueID("external", null);
		Resource externalRes = model.createResource(instanceNamespace + "External/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource externalClsRes = model.createResource(URISet.OME_EXTERNAL_CLASS_URI);
		model.add(externalRes, typeProp, externalClsRes);

		createLog.put(external, externalRes);
		if (origin != null) {
			Property externalProp = model.createProperty(URISet.OME_EXTERNAL_PROPERTY_URI);
			model.add(origin, externalProp, externalRes);
		}

		// compression
		Compression compression = external.getCompression();
		if (compression != null) {
			String compressionValue = compression.getValue();
			if (compressionValue != null) {
				compressionValue = compressionValue.toLowerCase();
				Resource compRes = model.createResource(URISet.getCompressionURI(compressionValue));
				Property compressionProp = model.createProperty(URISet.OME_COMPRESSION_PROPERTY_URI);
				model.add(externalRes, compressionProp, compRes);
				// prefix
				model.setNsPrefix("compression", URISet.OME_COMPRESSION_CLASS_URI + "#");
			}
		}

		// href
		String href = external.gethref();
		if (href != null) {
			Property hrefProp = model.createProperty(URISet.OME_HREF_PROPERTY_URI);
			model.add(externalRes, hrefProp, href);
		}

		// sha1
		String sha1 = external.getSHA1();
		if (sha1 != null) {
			Property sha1Prop = model.createProperty(URISet.OME_SHA1_PROPERTY_URI);
			model.add(externalRes, sha1Prop, sha1);
		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  Dataset
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Dataset dataset, Model model, String instanceNamespace) throws Exception {
		toRDF(dataset, null, model, instanceNamespace);
	}

	public void toRDF(Dataset dataset, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (dataset == null) {
			return;
		}
		if (createLog.containsKey(dataset)) {
			if (origin != null) {
				Property datasetProp = model.createProperty(URISet.OME_DATASET_PROPERTY_URI);
				Resource res = createLog.get(dataset);
				model.add(origin, datasetProp, res);
			}
			return;
		}

		String id = dataset.getID();
		String genID = getUniqueID("dataset", id);
		Resource datasetRes = model.createResource(instanceNamespace + "Dataset/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource datasetClsRes = model.createResource(URISet.OME_DATASET_CLASS_URI);
		model.add(datasetRes, typeProp, datasetClsRes);
		if (id != null) {
			Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
			model.add(datasetRes, idProp, id);
		}

		createLog.put(dataset, datasetRes);
		if (origin != null) {
			Property externalProp = model.createProperty(URISet.OME_DATASET_PROPERTY_URI);
			model.add(origin, externalProp, datasetRes);
		}

		// description
		String description = dataset.getDescription();
		if (description != null) {
			Property commentProp = model.createProperty(URISet.RDFS_COMMENT_PROPERTY_URI);
			model.add(datasetRes, commentProp, description, "en");
		}

		// name
		String name = dataset.getName();
		if (name != null) {
			Property labelProp = model.createProperty(URISet.RDFS_LABEL_PROPERTY_URI);
			model.add(datasetRes, labelProp, name, "en");
		}

		// link
		// experimenter
		Experimenter experimenter = dataset.getLinkedExperimenter();
		if (experimenter != null) {
			toRDF(experimenter, datasetRes, null, model, instanceNamespace);
		}

		// experimenterGroup
		ExperimenterGroup experimenterGroup = dataset.getLinkedExperimenterGroup();
		if (experimenterGroup != null) {
			toRDF(experimenterGroup, datasetRes, model, instanceNamespace);
		}

		// annotation
		int linkedAnntationCnt = dataset.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnntationCnt; k++) {
			Annotation annotation = dataset.getLinkedAnnotation(k);
			toRDF(annotation, datasetRes, model, instanceNamespace);
		}

		// image
		int linkedImageCnt = dataset.sizeOfLinkedImageList();
		for (int k = 0; k < linkedImageCnt; k++) {
			Image image = dataset.getLinkedImage(k);
			toRDF(image, datasetRes, model, instanceNamespace);
		}

//		// project
//		int linkedProjectCnt = dataset.sizeOfLinkedProjectList();
//		for (int k = 0; k < linkedProjectCnt; k++) {
//			Project project = dataset.getLinkedProject(k);
//			toRDF(project, datasetRes, model, instanceNamespace);
//		}

	}

//-------------------------------------------------------------------------------------------------------------------
//  Folder
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Folder folder, Model model, String instanceNamespace) throws Exception {
		toRDF(folder, null, model, instanceNamespace);
	}

	public void toRDF(Folder folder, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (folder == null) {
			return;
		}

		if (createLog.containsKey(folder)) {
			if (origin != null) {
				Property folderProp = model.createProperty(URISet.OME_FOLDER_PROPERTY_URI);
				Resource res = createLog.get(folder);
				model.add(origin, folderProp, res);
			}
			return;
		}

		String id = folder.getID();
		String genID = getUniqueID("folder", id);
		Resource folderRes = model.createResource(instanceNamespace + "Folder/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource folderClsRes = model.createResource(URISet.OME_FOLDER_CLASS_URI);
		model.add(folderRes, typeProp, folderClsRes);
		if (id != null) {
			Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
			model.add(folderRes, idProp, id);
		}

		createLog.put(folder, folderRes);
		if (origin != null) {
			Property folderProp = model.createProperty(URISet.OME_FOLDER_PROPERTY_URI);
			model.add(origin, folderProp, folderRes);
		}

		// description
		String description = folder.getDescription();
		if (description != null) {
			Property commentProp = model.createProperty(URISet.RDFS_COMMENT_PROPERTY_URI);
			model.add(folderRes, commentProp, description, "en");
		}

		// name
		String name = folder.getName();
		if (name != null) {
			Property labelProp = model.createProperty(URISet.RDFS_LABEL_PROPERTY_URI);
			model.add(folderRes, labelProp, name, "en");
		}

		// link
		// annotation
		int linkedAnnotationCnt = folder.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation annotation = folder.getLinkedAnnotation(k);
			toRDF(annotation, folderRes, model, instanceNamespace);
		}

		// childfolder
		// int linkedChildFolderCnt =folder.sizeOfLinkedChildFolderList();
		// Property childFolderProp =
		// model.createProperty(URISet.OME_CHILDFOLDER_PROPERTY_URI);
		// for (int k = 0; k < linkedChildFolderCnt; k++) {
		// Folder linkedChildFolder = folder.getLinkedChildFolder(k);
		// toRDF(linkedChildFolder, folderRes, childFolderProp, model,
		// instanceNamespace);
		// }

		// folder
		int linkedFolderCnt = folder.sizeOfLinkedChildFolderList();
		for (int k = 0; k < linkedFolderCnt; k++) {
			Folder linkedFolder = folder.getLinkedFolder(k);
			toRDF(linkedFolder, folderRes, model, instanceNamespace);
		}

		// image
		int linkedImageCnt = folder.sizeOfLinkedImageList();
		for (int k = 0; k < linkedImageCnt; k++) {
			Image linkedImage = folder.getLinkedImage(k);
			toRDF(linkedImage, folderRes, model, instanceNamespace);
		}

		// ROI
		int linkedROICnt = folder.sizeOfLinkedROIList();
		for (int k = 0; k < linkedROICnt; k++) {
			ROI linkedROI = folder.getLinkedROI(k);
			toRDF(linkedROI, folderRes, model, instanceNamespace);
		}
	}
//-------------------------------------------------------------------------------------------------------------------
//  Experiment
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Experiment experiment, Model model, String instanceNamespace) throws Exception {
		toRDF(experiment, null, model, instanceNamespace);
	}

	public void toRDF(Experiment experiment, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (experiment == null) {
			return;
		}

		if (createLog.containsKey(experiment)) {
			if (origin != null) {
				Property experimentProp = model.createProperty(URISet.OME_EXPERIMENT_PROPERTY_URI);
				Resource res = createLog.get(experiment);
				model.add(origin, experimentProp, res);
			}
			return;
		}

		String id = experiment.getID();
		String genID = getUniqueID("experiment", id);
		Resource experimentRes = model.createResource(instanceNamespace + "Experiment/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource experimentClsRes = model.createResource(URISet.OME_EXPERIMENT_CLASS_URI);
		model.add(experimentRes, typeProp, experimentClsRes);
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(experimentRes, idProp, id);

		createLog.put(experiment, experimentRes);
		if (origin != null) {
			Property experimentProp = model.createProperty(URISet.OME_EXPERIMENT_PROPERTY_URI);
			model.add(origin, experimentProp, experimentRes);
		}

		// description
		String description = experiment.getDescription();
		if (description != null) {
			Property commentProp = model.createProperty(URISet.RDFS_COMMENT_PROPERTY_URI);
			model.add(experimentRes, commentProp, description, "en");
		}

		// experimentType
		ExperimentType expType = experiment.getType();
		if (expType != null) {
			String expTypeValue = expType.getValue();
			if (expTypeValue != null) {
				Resource experimentTypeRes = model.createResource(URISet.getExperimentTypeURI(expTypeValue));
				Property experimentTypeProp = model.createProperty(URISet.OME_EXPERIMENTTYPE_PROPERTY_URI);
				model.add(experimentRes, experimentTypeProp, experimentTypeRes);
				// prefix
				model.setNsPrefix("experimentType", URISet.OME_EXPERIMENTTYPE_CLASS_URI + "#");
			}
		}

		// microbeamManipulation
		int mmCnt = experiment.sizeOfMicrobeamManipulationList();
		for (int m = 0; m < mmCnt; m++) {
			MicrobeamManipulation microbeamManipulation = experiment.getMicrobeamManipulation(m);
			toRDF(microbeamManipulation, experimentRes, model, instanceNamespace);
		}

		// link
		// experimenter
		Experimenter linkedExperimenter = experiment.getLinkedExperimenter();
		if (linkedExperimenter != null) {
			toRDF(linkedExperimenter, experimentRes, null, model, instanceNamespace);
		}

//		// image
//		int linkedImageCnt = experiment.sizeOfLinkedImageList();
//		for (int k = 0; k < linkedImageCnt; k++) {
//			Image linkedImage = experiment.getLinkedImage(k);
//			toRDF(linkedImage, experimentRes, model, instanceNamespace);
//		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  MicrobeamManipulation
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(MicrobeamManipulation microbeamManipulation, Resource origin, Model model,
			String instanceNamespace) throws Exception {
		if (microbeamManipulation == null) {
			return;
		}

		if (createLog.containsKey(microbeamManipulation)) {
			if (origin != null) {
				Property microbeamManipulationProp = model
						.createProperty(URISet.OME_MICROBEAMMANIPULATION_PROPERTY_URI);
				Resource res = createLog.get(microbeamManipulation);
				model.add(origin, microbeamManipulationProp, res);
			}
			return;
		}

		String id = microbeamManipulation.getID();
		String genID = getUniqueID("microbeamManipulation", id);
		Resource microbeamManipulationRes = model.createResource(instanceNamespace + "MicrobeamManipulation/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource microbeamManipulationClsRes = model.createResource(URISet.OME_MICROBEAMMANIPULATION_CLASS_URI);
		model.add(microbeamManipulationRes, typeProp, microbeamManipulationClsRes);
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(microbeamManipulationRes, idProp, id);

		createLog.put(microbeamManipulation, microbeamManipulationRes);
		if (origin != null) {
			Property microbeamManipulationProp = model.createProperty(URISet.OME_MICROBEAMMANIPULATION_PROPERTY_URI);
			model.add(origin, microbeamManipulationProp, microbeamManipulationRes);
		}

		// description
		String description = microbeamManipulation.getDescription();
		if (description != null) {
			Property commentProp = model.createProperty(URISet.RDFS_COMMENT_PROPERTY_URI);
			model.add(microbeamManipulationRes, commentProp, description, "en");
		}

		// experiment
		Experiment experiment = microbeamManipulation.getExperiment();
		if (experiment != null) {
			toRDF(experiment, microbeamManipulationRes, model, instanceNamespace);
		}

		// lightSourceSettings
		int lightSourceSettingsCnt = microbeamManipulation.sizeOfLightSourceSettingsList();
		for (int m = 0; m < lightSourceSettingsCnt; m++) {
			LightSourceSettings lightSourceSettings = microbeamManipulation.getLightSourceSettings(m);
			toRDF(lightSourceSettings, microbeamManipulationRes, model, instanceNamespace);
		}

		// microbeamManipulationType
		MicrobeamManipulationType microbeamManipulationType = microbeamManipulation.getType();
		if (microbeamManipulationType != null) {
			String uri = URISet.getMicrobeamManipulationTypeURI(microbeamManipulationType.getValue());
			Resource res = model.createResource(uri);
			Property microbeamManipulationTypeProp = model
					.createProperty(URISet.OME_MICROBEAMMANIPULATION_PROPERTY_URI);
			model.add(microbeamManipulationRes, microbeamManipulationTypeProp, res);
			// prefix
			model.setNsPrefix("microbeamManipulation", URISet.OME_MICROBEAMMANIPULATION_CLASS_URI + "#");
		}

		// link
		// experimenter
		Experimenter linkedExperimenter = microbeamManipulation.getLinkedExperimenter();
		if (linkedExperimenter != null) {
			toRDF(linkedExperimenter, microbeamManipulationRes, null, model, instanceNamespace);
		}

//		// image
//		int linkedImageCnt = microbeamManipulation.sizeOfLinkedImageList();
//		for (int k = 0; k < linkedImageCnt; k++) {
//			Image linkedImage = microbeamManipulation.getLinkedImage(k);
//			toRDF(linkedImage, microbeamManipulationRes, model, instanceNamespace);
//		}

		// ROI
		int linkedROICnt = microbeamManipulation.sizeOfLinkedROIList();
		for (int k = 0; k < linkedROICnt; k++) {
			ROI linkedROI = microbeamManipulation.getLinkedROI(k);
			toRDF(linkedROI, microbeamManipulationRes, model, instanceNamespace);
		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  LightSourceSettings
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(LightSourceSettings lightSourceSettings, Resource origin, Model model, String instanceNamespace)
			throws Exception {
		if (lightSourceSettings == null) {
			return;
		}

		if (createLog.containsKey(lightSourceSettings)) {
			if (origin != null) {
				Property lightSourceSettingsProp = model.createProperty(URISet.OME_LIGHTSOURCESETTINGS_PROPERTY_URI);
				Resource res = createLog.get(lightSourceSettings);
				model.add(origin, lightSourceSettingsProp, res);
			}
			return;
		}

		String id = lightSourceSettings.getID();
		String genID = getUniqueID("lightSourceSettings", id);
		Resource lightSourceSettingsRes = model.createResource(instanceNamespace + "LightSourceSettings/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource lightSourceSettingsClsRes = model.createResource(URISet.OME_LIGHTSOURCESETTINGS_CLASS_URI);
		model.add(lightSourceSettingsRes, typeProp, lightSourceSettingsClsRes);
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(lightSourceSettingsRes, idProp, id);

		createLog.put(lightSourceSettings, lightSourceSettingsRes);
		if (origin != null) {
			Property lightSourceSettingsProp = model.createProperty(URISet.OME_LIGHTSOURCESETTINGS_PROPERTY_URI);
			model.add(origin, lightSourceSettingsProp, lightSourceSettingsRes);
		}

		// microbeamManipulation
		MicrobeamManipulation microbeamManipulation = lightSourceSettings.getMicrobeamManipulation();
		if (microbeamManipulation != null) {
			toRDF(microbeamManipulation, lightSourceSettingsRes, model, instanceNamespace);
		}

		// wavelength
		createLengthBlankNodeValue(lightSourceSettings.getWavelength(),
				LightSourceSettings.getWavelengthUnitXsdDefault(), model, lightSourceSettingsRes,
				model.createProperty(URISet.OME_WAVELENGTH_PROPERTY_URI),
				model.createResource(URISet.PATO_WAVELENGTH_CLASS_URI));

		// lightSource
		LightSource lightSource = lightSourceSettings.getLightSource();
		if (lightSource != null) {
			toRDF(lightSource, lightSourceSettingsRes, null, model, instanceNamespace);
		}

		// attenuation
		PercentFraction attenuation = lightSourceSettings.getAttenuation();
		if (attenuation != null) {
			float value = attenuation.getValue();
			Property attenuationProp = model.createProperty(URISet.OME_ATTENUATION_PROPERTY_URI);
			model.add(lightSourceSettingsRes, attenuationProp, model.createTypedLiteral(value));
		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  Plate
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Plate plate, Model model, String instanceNamespace) throws Exception {
		toRDF(plate, null, model, instanceNamespace);
	}

	public void toRDF(Plate plate, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (plate == null) {
			return;
		}

		if (createLog.containsKey(plate)) {
			if (origin != null) {
				Property plateProp = model.createProperty(URISet.OME_PLATE_PROPERTY_URI);
				Resource res = createLog.get(plate);
				model.add(origin, plateProp, res);
			}
			return;
		}

		String id = plate.getID();
		String genID = getUniqueID("plate", id);
		Resource plateRes = model.createResource(instanceNamespace + "Plate/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource plateClsRes = model.createResource(URISet.OME_PLATE_CLASS_URI);
		model.add(plateRes, typeProp, plateClsRes);
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(plateRes, idProp, id);

		createLog.put(plate, plateRes);
		if (origin != null) {
			Property plateProp = model.createProperty(URISet.OME_PLATE_PROPERTY_URI);
			model.add(origin, plateProp, plateRes);
		}

		// description
		String description = plate.getDescription();
		if (description != null) {
			Property commentProp = model.createProperty(URISet.RDFS_COMMENT_PROPERTY_URI);
			model.add(plateRes, commentProp, description, "en");
		}

		// name
		String name = plate.getName();
		if (name != null) {
			Property labelProp = model.createProperty(URISet.RDFS_LABEL_PROPERTY_URI);
			model.add(plateRes, labelProp, name, "en");
		}

		// externalIdentifier
		String externalIdentifier = plate.getExternalIdentifier();
		if (externalIdentifier != null) {
			Property externalIdentifierProp = model.createProperty(URISet.OME_EXERNALIDENTIFIER_PROPERTY_URI);
			model.add(plateRes, externalIdentifierProp, externalIdentifier);
		}

		// columnNamingConversion
		NamingConvention columnNamingConvention = plate.getColumnNamingConvention();
		if (columnNamingConvention != null) {
			Property columnNamingConvensionProp = model.createProperty(URISet.OME_COLUMNNAMINGCONVENTION_PROPERTY_URI);
			toRDF(columnNamingConvention, plateRes, columnNamingConvensionProp, model, instanceNamespace);
		}

		NamingConvention rowNamingConvention = plate.getRowNamingConvention();
		if (rowNamingConvention != null) {
			Property rowNamingConventionProp = model.createProperty(URISet.OME_ROWNAMINGCONVENTION_PROPERTY_URI);
			toRDF(rowNamingConvention, plateRes, rowNamingConventionProp, model, instanceNamespace);
		}

		// columns
		PositiveInteger columns = plate.getColumns();
		if (columns != null) {
			int columnsVal = columns.getValue().intValue();
			Property columnsProp = model.createProperty(URISet.OME_COLUMNS_PROPERTY_URI);
			model.add(plateRes, columnsProp,
					model.createTypedLiteral(columnsVal, URISet.XSD_POSITIVEINTEGER_DATATYPE_URI));
		}

		// rows
		PositiveInteger rows = plate.getRows();
		if (rows != null) {
			int rowsVal = rows.getValue().intValue();
			Property rowsProp = model.createProperty(URISet.OME_ROWS_PROPERTY_URI);
			model.add(plateRes, rowsProp, model.createTypedLiteral(rowsVal, URISet.XSD_POSITIVEINTEGER_DATATYPE_URI));
		}

		// fieldIndex
		NonNegativeInteger fieldIndex = plate.getFieldIndex();
		if (fieldIndex != null) {
			int fieldIndexVal = fieldIndex.getValue().intValue();
			Property fieldIndexProp = model.createProperty(URISet.OME_FIELDINDEX_PROPERTY_URI);
			model.add(plateRes, fieldIndexProp,
					model.createTypedLiteral(fieldIndexVal, URISet.XSD_NONNEGATIVEINTEGER_DATATYPE_URI));
		}

		// status
		String status = plate.getStatus();
		if (status != null) {
			Property statusProp = model.createProperty(URISet.OME_STATUS_PROPERTY_URI);
			model.add(plateRes, statusProp, status);
		}

		// wellOriginX
		Length wellOriginX = plate.getWellOriginX();
		createLengthBlankNodeValue(wellOriginX, Plate.getWellOriginXUnitXsdDefault(), model, plateRes,
				model.createProperty(URISet.OME_WELLORIGINX_PROPERTY_URI),
				model.createResource(URISet.PATO_POSITION_CLASS_URI));

		// wellOriginY
		Length wellOriginY = plate.getWellOriginY();
		createLengthBlankNodeValue(wellOriginY, Plate.getWellOriginYUnitXsdDefault(), model, plateRes,
				model.createProperty(URISet.OME_WELLORIGINY_PROPERTY_URI),
				model.createResource(URISet.PATO_POSITION_CLASS_URI));

		// plateAcquisition
		int plateAcquisitionCnt = plate.sizeOfPlateAcquisitionList();
		for (int m = 0; m < plateAcquisitionCnt; m++) {
			PlateAcquisition plateAcquisition = plate.getPlateAcquisition(m);
			toRDF(plateAcquisition, plateRes, model, instanceNamespace);
		}

		// well
		int wellCnt = plate.sizeOfWellList();
		for (int m = 0; m < wellCnt; m++) {
			Well well = plate.getWell(m);
			toRDF(well, plateRes, model, instanceNamespace);
		}

		// link
		// annotation
		int linkedAnnotationCnt = plate.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation linkedAnnotation = plate.getLinkedAnnotation(k);
			toRDF(linkedAnnotation, plateRes, model, instanceNamespace);
		}

//		// screen
//		int linkedScreenCnt = plate.sizeOfLinkedScreenList();
//		for (int k = 0; k < linkedScreenCnt; k++) {
//			Screen linkedScreen = plate.getLinkedScreen(k);
//			toRDF(linkedScreen, plateRes, model, instanceNamespace);
//		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  NamingConvention
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(NamingConvention namingConvention, Resource origin, Property prop, Model model,
			String instanceNamespace) throws Exception {
		if (namingConvention == null) {
			return;
		}
		String value = namingConvention.getValue().toLowerCase();
		String uri = URISet.getNamingConventionURI(value);
		Resource res = model.createResource(uri);
		model.add(origin, prop, res);
		// prefix
		model.setNsPrefix("namingConvention", URISet.OME_NAMINGCONVENTION_CLASS_URI + "#");
	}

//-------------------------------------------------------------------------------------------------------------------
//  Well
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Well well, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (well == null) {
			return;
		}
		if (createLog.containsKey(well)) {
			if (origin != null) {
				Property wellProp = model.createProperty(URISet.OME_WELL_PROPERTY_URI);
				Resource res = createLog.get(well);
				model.add(origin, wellProp, res);
			}
			return;
		}

		String id = well.getID();
		String genID = getUniqueID("well", id);
		Resource wellRes = model.createResource(instanceNamespace + "Well/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource wellClsRes = model.createResource(URISet.OME_WELL_CLASS_URI);
		createLog.put(well, wellRes);
		if (origin != null) {
			Property wellProp = model.createProperty(URISet.OME_WELL_PROPERTY_URI);
			model.add(origin, wellProp, wellRes);
		}
		model.add(wellRes, typeProp, wellClsRes);
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(wellRes, idProp, id);

		// color
		Color color = well.getColor();
		createColorBlankNode(color, model, wellRes, model.createProperty(URISet.OME_COLOR_PROPERTY_URI));

		// column
		NonNegativeInteger column = well.getColumn();
		if (column != null) {
			int columnVal = column.getValue().intValue();
			Property columnProp = model.createProperty(URISet.OME_COLUMN_PROPERTY_URI);
			model.add(wellRes, columnProp,
					model.createTypedLiteral(columnVal, URISet.XSD_NONNEGATIVEINTEGER_DATATYPE_URI));
		}

		// row
		NonNegativeInteger row = well.getRow();
		if (row != null) {
			int rowVal = row.getValue().intValue();
			Property rowProp = model.createProperty(URISet.OME_ROW_PROPERTY_URI);
			model.add(wellRes, rowProp, model.createTypedLiteral(rowVal, URISet.XSD_NONNEGATIVEINTEGER_DATATYPE_URI));
		}

		// externalDescription
		String externalDescription = well.getExternalDescription();
		if (externalDescription != null) {
			Property externalDescriptionProp = model.createProperty(URISet.OME_EXTERNALDESCRIPTION_PROPERTY_URI);
			model.add(wellRes, externalDescriptionProp, externalDescription, "en");
		}

		// externalIdentifier
		String externalIdentifier = well.getExternalIdentifier();
		if (externalIdentifier != null) {
			Property externalIdentifierProp = model.createProperty(URISet.OME_EXTERNALIDENTIFIER_PROPERTY_URI);
			model.add(wellRes, externalIdentifierProp, externalIdentifier);
		}

		// plate
		Plate plate = well.getPlate();
		if (plate != null) {
			toRDF(plate, wellRes, model, instanceNamespace);
		}

		// wellType
		String wellType = well.getType();
		if (wellType != null) {
			Property wellTypeProp = model.createProperty(URISet.OME_WELLTYPE_PROPERTY_URI);
			model.add(wellRes, wellTypeProp, wellType);
		}

		// wellSample
		int wellSampleCnt = well.sizeOfWellSampleList();
		for (int m = 0; m < wellSampleCnt; m++) {
			WellSample wellSample = well.getWellSample(m);
			toRDF(wellSample, wellRes, model, instanceNamespace);
		}

		// link
		// annotation
		int linkedAnnotationCnt = well.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation linkedAnnotation = well.getLinkedAnnotation(k);
			toRDF(linkedAnnotation, wellRes, model, instanceNamespace);
		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  WellSample
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(WellSample wellSample, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (wellSample == null) {
			return;
		}
		if (createLog.containsKey(wellSample)) {
			if (origin != null) {
				Property wellSampleProp = model.createProperty(URISet.OME_WELLSAMPLE_PROPERTY_URI);
				Resource res = createLog.get(wellSample);
				model.add(origin, wellSampleProp, res);
			}
			return;
		}

		String id = wellSample.getID();
		String genID = getUniqueID("wellSample", id);
		Resource wellSampleRes = model.createResource(instanceNamespace + "WellSample/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource wellSampleClsRes = model.createResource(URISet.OME_WELLSAMPLE_CLASS_URI);
		createLog.put(wellSample, wellSampleRes);
		if (origin != null) {
			Property wellSampleProp = model.createProperty(URISet.OME_WELLSAMPLE_PROPERTY_URI);
			model.add(origin, wellSampleProp, wellSampleRes);
		}
		model.add(wellSampleRes, typeProp, wellSampleClsRes);
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(wellSampleRes, idProp, id);

		// index
		NonNegativeInteger index = wellSample.getIndex();
		if (index != null) {
			int indexVal = index.getValue().intValue();
			Property indexProp = model.createProperty(URISet.OME_INDEX_PROPERTY_URI);
			model.add(wellSampleRes, indexProp,
					model.createTypedLiteral(indexVal, URISet.XSD_NONNEGATIVEINTEGER_DATATYPE_URI));
		}

		// positionX
		Length positionX = wellSample.getPositionX();
		createLengthBlankNodeValue(positionX, WellSample.getPositionXUnitXsdDefault(), model, wellSampleRes,
				model.createProperty(URISet.OME_POSITIONX_PROPERTY_URI),
				model.createResource(URISet.PATO_POSITION_CLASS_URI));

		// positionY
		Length positionY = wellSample.getPositionY();
		createLengthBlankNodeValue(positionY, WellSample.getPositionYUnitXsdDefault(), model, wellSampleRes,
				model.createProperty(URISet.OME_POSITIONY_PROPERTY_URI),
				model.createResource(URISet.PATO_POSITION_CLASS_URI));

		// timepoint
		Timestamp timepoint = wellSample.getTimepoint();
		if (timepoint != null) {
			Property timepointProp = model.createProperty(URISet.OME_TIMEPOINT_PROPERTY_URI);
			model.add(wellSampleRes, timepointProp,
					model.createTypedLiteral(timepoint.getValue(), URISet.XSD_DATETIME_DATATYPE_URI));
		}

		// well
		Well well = wellSample.getWell();
		if (well != null) {
			toRDF(well, wellSampleRes, model, instanceNamespace);
		}

		// link
//		// plateAcquisition
//		int linkedPlateAcquisitionCnt = wellSample.sizeOfLinkedPlateAcquisitionList();
//		for (int k = 0; k < linkedPlateAcquisitionCnt; k++) {
//			PlateAcquisition plateAcquisition = wellSample.getLinkedPlateAcquisition(k);
//			toRDF(plateAcquisition, wellSampleRes, model, instanceNamespace);
//		}

		// image
		Image linkedImage = wellSample.getLinkedImage();
		if (linkedImage != null) {
			toRDF(linkedImage, wellSampleRes, model, instanceNamespace);
		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  PlaeAcquisition
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(PlateAcquisition plateAcquisition, Resource origin, Model model, String instanceNamespace)
			throws Exception {
		if (plateAcquisition == null) {
			return;
		}
		if (createLog.containsKey(plateAcquisition)) {
			if (origin != null) {
				Property externalProp = model.createProperty(URISet.OME_PLATEACQUISITION_PROPERTY_URI);
				Resource res = createLog.get(plateAcquisition);
				model.add(origin, externalProp, res);
			}
			return;
		}

		String id = plateAcquisition.getID();
		String genID = getUniqueID("plateAcquisition", id);
		Resource plateAcquisitionRes = model.createResource(instanceNamespace + "PlateAcquisition/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource plateAcquisitionClsRes = model.createResource(URISet.OME_PLATEACQUISITION_CLASS_URI);
		model.add(plateAcquisitionRes, typeProp, plateAcquisitionClsRes);
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(plateAcquisitionRes, idProp, id);
		createLog.put(plateAcquisition, plateAcquisitionRes);
		if (origin != null) {
			Property externalProp = model.createProperty(URISet.OME_PLATEACQUISITION_PROPERTY_URI);
			model.add(origin, externalProp, plateAcquisitionRes);
		}

		// description
		String description = plateAcquisition.getDescription();
		if (description != null) {
			Property commentProp = model.createProperty(URISet.RDFS_COMMENT_PROPERTY_URI);
			model.add(plateAcquisitionRes, commentProp, description, "en");
		}

		// name
		String name = plateAcquisition.getName();
		if (name != null) {
			Property labelProp = model.createProperty(URISet.RDFS_LABEL_PROPERTY_URI);
			model.add(plateAcquisitionRes, labelProp, name, "en");
		}

		// startTime
		Timestamp startTime = plateAcquisition.getStartTime();
		if (startTime != null) {
			Property startTimeProp = model.createProperty(URISet.OME_STARTTIME_PROPERTY_URI);
			Literal startTimeLiteral = model.createTypedLiteral(startTime.getValue(), URISet.XSD_DATETIME_DATATYPE_URI);
			model.add(plateAcquisitionRes, startTimeProp, startTimeLiteral);
		}

		// endTime
		Timestamp endTime = plateAcquisition.getEndTime();
		if (endTime != null) {
			Property endTimeProp = model.createProperty(URISet.OME_ENDTIME_PROPERTY_URI);
			Literal endTimeLiteral = model.createTypedLiteral(endTime.getValue(), URISet.XSD_DATETIME_DATATYPE_URI);
			model.add(plateAcquisitionRes, endTimeProp, endTimeLiteral);
		}

		// maximumFieldCount
		PositiveInteger maximumFieldCount = plateAcquisition.getMaximumFieldCount();
		if (maximumFieldCount != null) {
			int maximumFieldCountVal = maximumFieldCount.getValue().intValue();
			Property maximumFieldCountProp = model.createProperty(URISet.OME_MAXIMUMFIELDCOUNT_PROPERTY_URI);
			model.add(plateAcquisitionRes, maximumFieldCountProp,
					model.createTypedLiteral(maximumFieldCountVal, URISet.XSD_POSITIVEINTEGER_DATATYPE_URI));
		}

		// plate
		Plate plate = plateAcquisition.getPlate();
		if (plate != null) {
			toRDF(plate, plateAcquisitionRes, model, instanceNamespace);
		}

		// link
		// annotation
		int linkedAnnotationCnt = plateAcquisition.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation linkedAnnotation = plateAcquisition.getLinkedAnnotation(k);
			toRDF(linkedAnnotation, plateAcquisitionRes, model, instanceNamespace);
		}

		// wellSample
		int linkedWellSampleCnt = plateAcquisition.sizeOfLinkedWellSampleList();
		for (int k = 0; k < linkedWellSampleCnt; k++) {
			WellSample linkedWellSample = plateAcquisition.getLinkedWellSample(k);
			toRDF(linkedWellSample, plateAcquisitionRes, model, instanceNamespace);
		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  Screen
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Screen screen, Model model, String instanceNamespace) throws Exception {
		toRDF(screen, null, model, instanceNamespace);
	}

	public void toRDF(Screen screen, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (screen == null) {
			return;
		}
		if (createLog.containsKey(screen)) {
			if (origin != null) {
				Property screenProp = model.createProperty(URISet.OME_SCREEN_PROPERTY_URI);
				Resource res = createLog.get(screen);
				model.add(origin, screenProp, res);
			}
			return;
		}

		String id = screen.getID();
		String genID = getUniqueID("screen", id);
		Resource screenRes = model.createResource(instanceNamespace + "Screen/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource screenClsRes = model.createResource(URISet.OME_SCREEN_CLASS_URI);
		model.add(screenRes, typeProp, screenClsRes);
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(screenRes, idProp, id);
		createLog.put(screen, screenRes);
		if (origin != null) {
			Property screenProp = model.createProperty(URISet.OME_SCREEN_PROPERTY_URI);
			model.add(origin, screenProp, screenRes);
		}

		// description
		String description = screen.getDescription();
		if (description != null) {
			Property commentProp = model.createProperty(URISet.RDFS_COMMENT_PROPERTY_URI);
			model.add(screenRes, commentProp, description, "en");
		}

		// name
		String name = screen.getName();
		if (name != null) {
			Property labelProp = model.createProperty(URISet.RDFS_LABEL_PROPERTY_URI);
			model.add(screenRes, labelProp, name, "en");
		}

		// protocol
		String protocolDescription = screen.getProtocolDescription();
		String protocolIdentifier = screen.getProtocolIdentifier();
		if (protocolDescription != null || protocolIdentifier != null) {
			String protocolGenID = getUniqueID("protocol", null);
			Resource protocolRes = model.createResource(instanceNamespace + "Protocol/" + protocolGenID);
			Resource protocolClsRes = model.createResource(URISet.OME_PROTOCOL_CLASS_URI);
			Property protocolProp = model.createProperty(URISet.OME_PROTOCOL_PROPERTY_URI);
			model.add(screenRes, protocolProp, protocolRes);
			model.add(protocolRes, typeProp, protocolClsRes);
			if (protocolDescription != null) {
				Property protocolDescriptionProp = model.createProperty(URISet.OME_PROTOCOLDESCRIPTION_PROPERTY_URI);
				model.add(protocolRes, protocolDescriptionProp, protocolDescription, "en");
			}
			if (protocolIdentifier != null) {
				Property protocolIdentifierProp = model.createProperty(URISet.OME_PROTOCOLIDENTIFIER_PROPERTY_URI);
				model.add(protocolRes, protocolIdentifierProp, protocolIdentifier);
			}
		}

		// reagentSet
		String reagentSetDescription = screen.getReagentSetDescription();
		String reagentSetIdentifier = screen.getReagentSetIdentifier();
		if (reagentSetDescription != null || reagentSetIdentifier != null) {
			String reagentSetGenID = getUniqueID("reagentSet", null);
			Resource reagentSetRes = model.createResource(instanceNamespace + "reagentSet/" + reagentSetGenID);
			Resource reagentSetClsRes = model.createResource(URISet.OME_REAGENTSET_CLASS_URI);
			Property reagentSetProp = model.createProperty(URISet.OME_REAGENTSET_PROPERTY_URI);
			model.add(screenRes, reagentSetProp, reagentSetRes);
			model.add(reagentSetRes, typeProp, reagentSetClsRes);
			if (reagentSetDescription != null) {
				Property reagentSetDescriptionProp = model
						.createProperty(URISet.OME_REAGENTSETDESCRIPTION_PROPERTY_URI);
				model.add(reagentSetRes, reagentSetDescriptionProp, reagentSetDescription, "en");
			}
			if (reagentSetIdentifier != null) {
				Property reagentSetIdentifierProp = model.createProperty(URISet.OME_REAGENTSETIDENTIFIER_PROPERTY_URI);
				model.add(reagentSetRes, reagentSetIdentifierProp, reagentSetIdentifier);
			}
		}

		// screenType
		String screenType = screen.getType();
		if (screenType != null) {
			Property screenTypeProp = model.createProperty(URISet.OME_SCREENTYPE_PROPERTY_URI);
// TODO
//         // in the current version, enum for screenType is not supported
//			String uri = URISet.getScreenTypeURI(screenType);
//			Resource res = model.createResource(uri);
//			model.add(screenRes, screenTypeProp, res);

			model.add(screenRes, screenTypeProp, screenType);
		}

		// reagent
		int reagentCnt = screen.sizeOfReagentList();
		for (int m = 0; m < reagentCnt; m++) {
			Reagent reagent = screen.getReagent(m);
			toRDF(reagent, screenRes, model, instanceNamespace);
		}

		// link
		// annotation
		int linkedAnnotationCnt = screen.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation linkedAnnotation = screen.getLinkedAnnotation(k);
			toRDF(linkedAnnotation, screenRes, model, instanceNamespace);
		}

		// plate
		int linkedPlateCnt = screen.sizeOfLinkedPlateList();
		for (int k = 0; k < linkedPlateCnt; k++) {
			Plate linkedPlate = screen.getLinkedPlate(k);
			toRDF(linkedPlate, screenRes, model, instanceNamespace);
		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  Reagent
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Reagent reagent, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (reagent == null) {
			return;
		}

		if (createLog.containsKey(reagent)) {
			if (origin != null) {
				Property reagentProp = model.createProperty(URISet.OME_REAGENT_PROPERTY_URI);
				Resource res = createLog.get(reagent);
				model.add(origin, reagentProp, res);
			}
			return;
		}

		String id = reagent.getID();
		String genID = getUniqueID("reagent", id);
		Resource reagentRes = model.createResource(instanceNamespace + "Reagent/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource reagentClsRes = model.createResource(URISet.OME_REAGENT_CLASS_URI);
		model.add(reagentRes, typeProp, reagentClsRes);
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(reagentRes, idProp, id);
		createLog.put(reagent, reagentRes);
		if (origin != null) {
			Property reagentProp = model.createProperty(URISet.OME_REAGENT_PROPERTY_URI);
			model.add(origin, reagentProp, reagentRes);
		}

		// description
		String description = reagent.getDescription();
		if (description != null) {
			Property commentProp = model.createProperty(URISet.RDFS_COMMENT_PROPERTY_URI);
			model.add(reagentRes, commentProp, description, "en");
		}

		// name
		String name = reagent.getName();
		if (name != null) {
			Property labelProp = model.createProperty(URISet.RDFS_LABEL_PROPERTY_URI);
			model.add(reagentRes, labelProp, name, "en");
		}

		// reagentIdentifier
		String reagentIdentifier = reagent.getReagentIdentifier();
		if (reagentIdentifier != null) {
			Property reagentIdentifierProp = model.createProperty(URISet.OME_REAGENTIDENTIFIER_PROPERTY_URI);
			model.add(reagentRes, reagentIdentifierProp, reagentIdentifier);
		}

		// screen
		Screen screen = reagent.getScreen();
		if (screen != null) {
			toRDF(screen, reagentRes, model, instanceNamespace);
		}

		// link
		// annotation
		int linkedAnnotationCnt = reagent.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation linkedAnnotation = reagent.getLinkedAnnotation(k);
			toRDF(linkedAnnotation, reagentRes, model, instanceNamespace);
		}

//		// well
//		int linkedWellCnt = reagent.sizeOfLinkedWellList();
//		for (int k = 0; k < linkedWellCnt; k++) {
//			Well linkedWell = reagent.getLinkedWell(k);
//			toRDF(linkedWell, reagentRes, model, instanceNamespace);
//		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  Experimenter
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Experimenter experimenter, Model model, String instanceNamespace) throws Exception {
		toRDF(experimenter, null, null, model, instanceNamespace);
	}

	public void toRDF(Experimenter experimenter, Resource origin, Property prop, Model model, String instanceNamespace)
			throws Exception {
		if (experimenter == null) {
			return;
		}
		if (createLog.containsKey(experimenter)) {
			if (origin != null) {
				Property tempProp = null;
				if (prop != null) {
					tempProp = prop;
				} else {
					tempProp = model.createProperty(URISet.OME_EXPERIMENTER_PROPERTY_URI);
				}
				Resource res = createLog.get(experimenter);
				model.add(origin, tempProp, res);
			}
			return;
		}

		String id = experimenter.getID();
		String genID = getUniqueID("experimenter", id);
		Resource experimenterRes = model.createResource(instanceNamespace + "Experimenter/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource experimenterClsRes = model.createResource(URISet.FOAF_PERSON_CLASS_URI);
		model.add(experimenterRes, typeProp, experimenterClsRes);
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(experimenterRes, idProp, id);
		createLog.put(experimenter, experimenterRes);
		if (origin != null) {
			Property tempProp = null;
			if (prop != null) {
				tempProp = prop;
			} else {
				tempProp = model.createProperty(URISet.OME_EXPERIMENTER_PROPERTY_URI);
			}
			model.add(origin, tempProp, experimenterRes);
		}

		// email
		String email = experimenter.getEmail();
		if (email != null) {
			Property mboxProp = model.createProperty(URISet.FOAF_MBOX_PROPERTY_URI);
			model.add(experimenterRes, mboxProp, email);
		}

		// firstName
		String firstName = experimenter.getFirstName();
		if (firstName != null) {
			Property givenNameProp = model.createProperty(URISet.FOAF_GIVENNAME_PROPERTY_URI);
			model.add(experimenterRes, givenNameProp, firstName);
		}

		// lastName
		String lastName = experimenter.getLastName();
		if (lastName != null) {
			Property familyNameProp = model.createProperty(URISet.FOAF_FAMILYNAME_PROPERTY_URI);
			model.add(experimenterRes, familyNameProp, lastName);
		}

		// middleName
		String middleName = experimenter.getMiddleName();
		if (middleName != null) {
			Property middleNameProp = model.createProperty(URISet.FOAF_MIDDLENAME_PROPERTY_URI);
			model.add(experimenterRes, middleNameProp, middleName);
		}

		// institution
		String institution = experimenter.getInstitution();
		if (institution != null) {
			Property institutionProp = model.createProperty(URISet.OME_INSTITUTION_PROPERTY_URI);
			model.add(experimenterRes, institutionProp, institution);
		}

		// userName
		String userName = experimenter.getUserName();
		if (userName != null) {
			Property accountNameProp = model.createProperty(URISet.FOAF_ACCOUNTNAME_PROPERTY_URI);
			model.add(experimenterRes, accountNameProp, middleName);
		}

		// link
		// annotation
		int linkedAnnotationCnt = experimenter.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation linkedAnnotation = experimenter.getLinkedAnnotation(k);
			toRDF(linkedAnnotation, experimenterRes, model, instanceNamespace);
		}

//		// dataset
//		int linkedDatasetCnt = experimenter.sizeOfLinkedDatasetList();
//		for (int k = 0; k < linkedDatasetCnt; k++) {
//			Dataset linkedDataset = experimenter.getLinkedDataset(k);
//			toRDF(linkedDataset, experimenterRes, model, instanceNamespace);
//		}
//
//		// experiment
//		int linkedExperimentCnt = experimenter.sizeOfLinkedExperimentList();
//		for (int k = 0; k < linkedExperimentCnt; k++) {
//			Experiment linkedExperiment = experimenter.getLinkedExperiment(k);
//			toRDF(linkedExperiment, experimenterRes, model, instanceNamespace);
//		}
//
//		// experimenterGroup
//		int linkedExperimenterGroupCnt = experimenter.sizeOfLinkedExperimenterGroupList();
//		for (int k = 0; k < linkedExperimenterGroupCnt; k++) {
//			ExperimenterGroup linkedExperimenterGroup = experimenter.getLinkedExperimenterGroup(k);
//			toRDF(linkedExperimenterGroup, experimenterRes, model, instanceNamespace);
//		}
//
//		// image
//		int linkedImageCnt = experimenter.sizeOfLinkedImageList();
//		for (int k = 0; k < linkedImageCnt; k++) {
//			Image linkedImage = experimenter.getLinkedImage(k);
//			toRDF(linkedImage, experimenterRes, model, instanceNamespace);
//		}
//
//		// microbeamManipluation
//		int linkedMicrobeamManipulationCnt = experimenter.sizeOfLinkedMicrobeamManipulationList();
//		for (int k = 0; k < linkedMicrobeamManipulationCnt; k++) {
//			MicrobeamManipulation linkedMicrobeamManipulation = experimenter.getLinkedMicrobeamManipulation(k);
//			toRDF(linkedMicrobeamManipulation, experimenterRes, model, instanceNamespace);
//		}
//
//		// project
//		int linkedProjectCnt = experimenter.sizeOfLinkedProjectList();
//		for (int k = 0; k < linkedProjectCnt; k++) {
//			Project linkedProject = experimenter.getLinkedProject(k);
//			toRDF(linkedProject, experimenterRes, model, instanceNamespace);
//		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  ExperimenterGroup
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(ExperimenterGroup experimenterGroup, Model model, String instanceNamespace) throws Exception {
		toRDF(experimenterGroup, null, model, instanceNamespace);
	}

	public void toRDF(ExperimenterGroup experimenterGroup, Resource origin, Model model, String instanceNamespace)
			throws Exception {
		if (experimenterGroup == null) {
			return;
		}

		if (createLog.containsKey(experimenterGroup)) {
			if (origin != null) {
				Property experimenterGroupProp = model.createProperty(URISet.OME_EXPERIMENTERGROUP_PROPERTY_URI);
				Resource res = createLog.get(experimenterGroup);
				model.add(origin, experimenterGroupProp, res);
			}
			return;
		}

		String id = experimenterGroup.getID();
		String genID = getUniqueID("experimenterGroup", id);
		Resource experimenterGroupRes = model.createResource(instanceNamespace + "ExperimenterGroup/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource experimenterGroupClsRes = model.createResource(URISet.OME_EXPERIMENTERGROUP_CLASS_URI);
		model.add(experimenterGroupRes, typeProp, experimenterGroupClsRes);
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(experimenterGroupRes, idProp, id);
		createLog.put(experimenterGroup, experimenterGroupRes);
		if (origin != null) {
			Property experimenterProp = model.createProperty(URISet.OME_EXPERIMENTER_PROPERTY_URI);
			model.add(origin, experimenterProp, experimenterGroupRes);
		}

		// description
		String description = experimenterGroup.getDescription();
		if (description != null) {
			Property commentProp = model.createProperty(URISet.RDFS_COMMENT_PROPERTY_URI);
			model.add(experimenterGroupRes, commentProp, description, "en");
		}

		// name
		String name = experimenterGroup.getName();
		if (name != null) {
			Property labelProp = model.createProperty(URISet.RDFS_LABEL_PROPERTY_URI);
			model.add(experimenterGroupRes, labelProp, name, "en");
		}

		// link
		// annotation
		int linkedAnnotationCnt = experimenterGroup.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation linkedAnnotation = experimenterGroup.getLinkedAnnotation(k);
			toRDF(linkedAnnotation, experimenterGroupRes, model, instanceNamespace);
		}

//		// dataset
//		int linkedDatasetCnt = experimenterGroup.sizeOfLinkedDatasetList();
//		for (int k = 0; k < linkedDatasetCnt; k++) {
//			Dataset linkedDataset = experimenterGroup.getLinkedDataset(k);
//			toRDF(linkedDataset, experimenterGroupRes, model, instanceNamespace);
//		}

		// experimenter
		int linkedExperimenterCnt = experimenterGroup.sizeOfLinkedExperimenterList();
		for (int k = 0; k < linkedExperimenterCnt; k++) {
			Experimenter linkedExperimenter = experimenterGroup.getLinkedExperimenter(k);
			toRDF(linkedExperimenter, experimenterGroupRes, null, model, instanceNamespace);
		}

//		// image
//		int linkedImageCnt = experimenterGroup.sizeOfLinkedImageList();
//		for (int k = 0; k < linkedImageCnt; k++) {
//			Image linkedImage = experimenterGroup.getLinkedImage(k);
//			toRDF(linkedImage, experimenterGroupRes, model, instanceNamespace);
//		}

		// experimenter (leader)
		int linkedLeaderCnt = experimenterGroup.sizeOfLinkedLeaderList();
		Property leaderProp = model.createProperty(URISet.OME_LEADER_PROPERTY_URI);
		for (int k = 0; k < linkedLeaderCnt; k++) {
			Experimenter linkedLeader = experimenterGroup.getLinkedLeader(k);
			toRDF(linkedLeader, experimenterGroupRes, leaderProp, model, instanceNamespace);
		}

//		// project
//		int linkedProjectCnt = experimenterGroup.sizeOfLinkedProjectList();
//		for (int k = 0; k < linkedProjectCnt; k++) {
//			Project linkedProject = experimenterGroup.getLinkedProject(k);
//			toRDF(linkedProject, experimenterGroupRes, model, instanceNamespace);
//		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  Instrument
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Instrument instrument, Model model, String instanceNamespace) throws Exception {
		toRDF(instrument, null, model, instanceNamespace);
	}

	public void toRDF(Instrument instrument, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (instrument == null) {
			return;
		}
		if (createLog.containsKey(instrument)) {
			if (origin != null) {
				Property instrumentProp = model.createProperty(URISet.OME_INSTRUMENT_PROPERTY_URI);
				Resource res = createLog.get(instrument);
				model.add(origin, instrumentProp, res);
			}
			return;
		}

		String id = instrument.getID();
		String genID = getUniqueID("instrument", id);
		Resource instrumentRes = model.createResource(instanceNamespace + "Instrument/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource instrumentClsRes = model.createResource(URISet.OME_INSTRUMENT_CLASS_URI);
		model.add(instrumentRes, typeProp, instrumentClsRes);
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(instrumentRes, idProp, id);
		createLog.put(instrument, instrumentRes);
		if (origin != null) {
			Property instrumentProp = model.createProperty(URISet.OME_INSTRUMENT_PROPERTY_URI);
			model.add(origin, instrumentProp, instrumentRes);
		}

		// microscope
		Microscope microscope = instrument.getMicroscope();
		if (microscope != null) {
			toRDF(microscope, instrumentRes, model, instanceNamespace);
		}

		// detector
		int detectorCnt = instrument.sizeOfDetectorList();
		for (int m = 0; m < detectorCnt; m++) {
			Detector detector = instrument.getDetector(m);
			toRDF(detector, instrumentRes, model, instanceNamespace);
		}

		// dichroic
		int dichroicCnt = instrument.sizeOfDichroicList();
		for (int m = 0; m < dichroicCnt; m++) {
			Dichroic dichroic = instrument.getDichroic(m);
			toRDF(dichroic, instrumentRes, model, instanceNamespace);
		}

		// filter
		int filterCnt = instrument.sizeOfFilterList();
		for (int m = 0; m < filterCnt; m++) {
			Filter filter = instrument.getFilter(m);
			toRDF(filter, instrumentRes, null, model, instanceNamespace);
		}

		// finterSet
		int filterSetCnt = instrument.sizeOfFilterSetList();
		for (int m = 0; m < filterSetCnt; m++) {
			FilterSet filterSet = instrument.getFilterSet(m);
			toRDF(filterSet, instrumentRes, model, instanceNamespace);
		}

		// lightSource
		int lightSourceCnt = instrument.sizeOfLightSourceList();
		for (int m = 0; m < lightSourceCnt; m++) {
			LightSource lightSource = instrument.getLightSource(m);
			toRDF(lightSource, instrumentRes, null, model, instanceNamespace);
		}

		// objective
		int objectiveCnt = instrument.sizeOfObjectiveList();
		for (int m = 0; m < objectiveCnt; m++) {
			Objective objective = instrument.getObjective(m);
			toRDF(objective, instrumentRes, model, instanceNamespace);
		}

		// link
		// annotation
		int linkedAnnotationCnt = instrument.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation linkedAnnotation = instrument.getLinkedAnnotation(k);
			toRDF(linkedAnnotation, instrumentRes, model, instanceNamespace);
		}

//		// image
//		int linkedImageCnt = instrument.sizeOfLinkedImageList();
//		for (int k = 0; k < linkedImageCnt; k++) {
//			Image linkedImage = instrument.getLinkedImage(k);
//			toRDF(linkedImage, instrumentRes, model, instanceNamespace);
//		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  Microscope
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Microscope microscope, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (microscope == null) {
			return;
		}
		if (createLog.containsKey(microscope)) {
			if (origin != null) {
				Property microscopeProp = model.createProperty(URISet.OME_MICROSCOPE_PROPERTY_URI);
				Resource res = createLog.get(microscope);
				model.add(origin, microscopeProp, res);
			}
			return;
		}

		String genID = getUniqueID("microscope", null);
		Resource microscopeRes = model.createResource(instanceNamespace + "Microscope/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource microscopeClsRes = model.createResource(URISet.OME_MICROSCOPE_CLASS_URI);
		model.add(microscopeRes, typeProp, microscopeClsRes);
		createLog.put(microscope, microscopeRes);
		if (origin != null) {
			Property microscopeProp = model.createProperty(URISet.OME_MICROSCOPE_PROPERTY_URI);
			model.add(origin, microscopeProp, microscopeRes);
		}

		// lotNumber
		String lotNumber = microscope.getLotNumber();
		if (lotNumber != null) {
			Property lotNumberProp = model.createProperty(URISet.OME_LOTNUMBER_PROPERTY_URI);
			model.add(microscopeRes, lotNumberProp, lotNumber);
		}

		// manufacturer
		String manufacturer = microscope.getManufacturer();
		if (manufacturer != null) {
			Property manufacturerProp = model.createProperty(URISet.OME_MANUFACTURER_PROPERTY_URI);
			model.add(microscopeRes, manufacturerProp, manufacturer);
		}

		// model
		String modelStr = microscope.getModel();
		if (modelStr != null) {
			Property modelProp = model.createProperty(URISet.OME_MODEL_PROPERTY_URI);
			model.add(microscopeRes, modelProp, modelStr);
		}

		// serialNumber
		String serialNumber = microscope.getSerialNumber();
		if (serialNumber != null) {
			Property serialNumberProp = model.createProperty(URISet.OME_SERIALNUMBER_PROPERTY_URI);
			model.add(microscopeRes, serialNumberProp, serialNumber);
		}

		// microscopeType
		MicroscopeType microscopeType = microscope.getType();
		if (microscopeType != null) {
			String value = microscopeType.getValue();
			String uri = URISet.getMicroscopeTypeURI(value);
			Resource res = model.createResource(uri);
			Property microscopeTypeProp = model.createProperty(URISet.OME_MICROSCOPETYPE_PROPERTY_URI);
			model.add(microscopeRes, microscopeTypeProp, res);
			// prefix
			model.setNsPrefix("microscopeType", URISet.OME_MICROSCOPETYPE_CLASS_URI + "#");
		}

	}

//-------------------------------------------------------------------------------------------------------------------
//  Detector
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Detector detector, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (detector == null) {
			return;
		}

		if (createLog.containsKey(detector)) {
			if (origin != null) {
				Property detectorProp = model.createProperty(URISet.OME_DETECTOR_PROPERTY_URI);
				Resource res = createLog.get(detector);
				model.add(origin, detectorProp, res);
			}
			return;
		}

		String id = detector.getID();
		String genID = getUniqueID("detector", id);
		Resource detectorRes = model.createResource(instanceNamespace + "Detector/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource detectorClsRes = model.createResource(URISet.OME_DETECTOR_CLASS_URI);
		model.add(detectorRes, typeProp, detectorClsRes);
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(detectorRes, idProp, id);
		createLog.put(detector, detectorRes);
		if (origin != null) {
			Property detectorProp = model.createProperty(URISet.OME_DETECTOR_PROPERTY_URI);
			model.add(origin, detectorProp, detectorRes);
		}

		// lotNumber
		String lotNumber = detector.getLotNumber();
		if (lotNumber != null) {
			Property lotNumberProp = model.createProperty(URISet.OME_LOTNUMBER_PROPERTY_URI);
			model.add(detectorRes, lotNumberProp, lotNumber);
		}

		// manufacturer
		String manufacturer = detector.getManufacturer();
		if (manufacturer != null) {
			Property manufacturerProp = model.createProperty(URISet.OME_MANUFACTURER_PROPERTY_URI);
			model.add(detectorRes, manufacturerProp, manufacturer);
		}

		// model
		String modelStr = detector.getModel();
		if (modelStr != null) {
			Property modelProp = model.createProperty(URISet.OME_MODEL_PROPERTY_URI);
			model.add(detectorRes, modelProp, modelStr);
		}

		// serialNumber
		String serialNumber = detector.getSerialNumber();
		if (serialNumber != null) {
			Property serialNumberProp = model.createProperty(URISet.OME_SERIALNUMBER_PROPERTY_URI);
			model.add(detectorRes, serialNumberProp, serialNumber);
		}

		// detectorType
		DetectorType detectorType = detector.getType();
		if (detectorType != null) {
			String value = detectorType.getValue();
			String uri = URISet.getDetectorTypeURI(value);
			Resource res = model.createResource(uri);
			Property detectorTypeProp = model.createProperty(URISet.OME_DETECTORTYPE_PROPERTY_URI);
			model.add(detectorRes, detectorTypeProp, res);
			// prefix
			model.setNsPrefix("detectorType", URISet.OME_DETECTORTYPE_CLASS_URI + "#");
		}

		// amplificationGain
		Double amplificationGain = detector.getAmplificationGain();
		if (amplificationGain != null) {
			Property amplificationGainProp = model.createProperty(URISet.OME_AMPLIFICATIONGAIN_PROPERTY_URI);
			model.add(detectorRes, amplificationGainProp, model.createTypedLiteral(amplificationGain.doubleValue()));
		}

		// gain
		Double gain = detector.getGain();
		if (gain != null) {
			Property gainProp = model.createProperty(URISet.OME_GAIN_PROPERTY_URI);
			model.add(detectorRes, gainProp, model.createTypedLiteral(gain.doubleValue()));
		}

		// offset
		Double offset = detector.getOffset();
		if (offset != null) {
			Property offsetProp = model.createProperty(URISet.OME_OFFSET_PROPERTY_URI);
			model.add(detectorRes, offsetProp, model.createTypedLiteral(offset.doubleValue()));
		}

		// zoom
		Double zoom = detector.getZoom();
		if (zoom != null) {
			Property zoomProp = model.createProperty(URISet.OME_ZOOM_PROPERTY_URI);
			model.add(detectorRes, zoomProp, model.createTypedLiteral(zoom.doubleValue()));
		}

		// instrument
		Instrument instrument = detector.getInstrument();
		if (instrument != null) {
			toRDF(instrument, detectorRes, model, instanceNamespace);
		}

		// voltage
		ElectricPotential voltage = detector.getVoltage();
		createElectricPotentialBlankNodeValue(voltage, Detector.getVoltageUnitXsdDefault(), model, detectorRes,
				model.createProperty(URISet.OME_VOLTAGE_PROPERTY_URI),
				model.createResource(URISet.SBO_VOLTAGE_CLASS_URI));

		// link
		// annotation
		int linkedAnnotationCnt = detector.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation linkedAnnotation = detector.getLinkedAnnotation(k);
			toRDF(linkedAnnotation, detectorRes, model, instanceNamespace);
		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  Dichroic
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Dichroic dichroic, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (dichroic == null) {
			return;
		}

		if (createLog.containsKey(dichroic)) {
			if (origin != null) {
				Property dichroicProp = model.createProperty(URISet.OME_DICHROIC_PROPERTY_URI);
				Resource res = createLog.get(dichroic);
				model.add(origin, dichroicProp, res);
			}
			return;
		}

		String id = dichroic.getID();
		String genID = getUniqueID("dichroic", id);
		Resource dichroicRes = model.createResource(instanceNamespace + "dichroic/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource dichroicClsRes = model.createResource(URISet.OME_DICHROIC_CLASS_URI);
		model.add(dichroicRes, typeProp, dichroicClsRes);
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(dichroicRes, idProp, id);
		createLog.put(dichroic, dichroicRes);
		if (origin != null) {
			Property dichroicProp = model.createProperty(URISet.OME_DICHROIC_PROPERTY_URI);
			model.add(origin, dichroicProp, dichroicRes);
		}

		// lotNumber
		String lotNumber = dichroic.getLotNumber();
		if (lotNumber != null) {
			Property lotNumberProp = model.createProperty(URISet.OME_LOTNUMBER_PROPERTY_URI);
			model.add(dichroicRes, lotNumberProp, lotNumber);
		}

		// manufacturer
		String manufacturer = dichroic.getManufacturer();
		if (manufacturer != null) {
			Property manufacturerProp = model.createProperty(URISet.OME_MANUFACTURER_PROPERTY_URI);
			model.add(dichroicRes, manufacturerProp, manufacturer);
		}

		// model
		String modelStr = dichroic.getModel();
		if (modelStr != null) {
			Property modelProp = model.createProperty(URISet.OME_MODEL_PROPERTY_URI);
			model.add(dichroicRes, modelProp, modelStr);
		}

		// serialNumber
		String serialNumber = dichroic.getSerialNumber();
		if (serialNumber != null) {
			Property serialNumberProp = model.createProperty(URISet.OME_SERIALNUMBER_PROPERTY_URI);
			model.add(dichroicRes, serialNumberProp, serialNumber);
		}

		// instrument
		Instrument instrument = dichroic.getInstrument();
		if (instrument != null) {
			toRDF(instrument, dichroicRes, model, instanceNamespace);
		}

		// link
		// annotation
		int linkedAnnotationCnt = dichroic.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation linkedAnnotation = dichroic.getLinkedAnnotation(k);
			toRDF(linkedAnnotation, dichroicRes, model, instanceNamespace);
		}

//		// filterSet
//		int linkedFilterSetCnt = dichroic.sizeOfLinkedFilterSetList();
//		for (int k = 0; k < linkedFilterSetCnt; k++) {
//			FilterSet linkedFilterSet = dichroic.getLinkedFilterSet(k);
//			toRDF(linkedFilterSet, dichroicRes, model, instanceNamespace);
//		}
//
//		// lightPath
//		int linkedLightPathCnt = dichroic.sizeOfLinkedLightPathList();
//		for (int k = 0; k < linkedLightPathCnt; k++) {
//			LightPath linkedLightPath = dichroic.getLinkedLightPath(k);
//			toRDF(linkedLightPath, dichroicRes, model, instanceNamespace);
//		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  Filter
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Filter filter, Resource origin, Property prop, Model model, String instanceNamespace)
			throws Exception {
		if (filter == null) {
			return;
		}

		if (createLog.containsKey(filter)) {
			Resource res = createLog.get(filter);
			if (origin != null) {
				if (prop != null) {
					model.add(origin, prop, res);

				} else {
					Property filterProp = model.createProperty(URISet.OME_FILTER_PROPERTY_URI);
					model.add(origin, filterProp, res);
				}
			}
			return;
		}

		String id = filter.getID();
		String genID = getUniqueID("filter", id);
		Resource filterRes = model.createResource(instanceNamespace + "Filter/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource filterClsRes = model.createResource(URISet.OME_FILTER_CLASS_URI);
		model.add(filterRes, typeProp, filterClsRes);
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(filterRes, idProp, id);
		createLog.put(filter, filterRes);
		if (origin != null) {
			if (prop != null) {
				model.add(origin, prop, filterRes);
			} else {
				Property filterProp = model.createProperty(URISet.OME_FILTER_PROPERTY_URI);
				model.add(origin, filterProp, filterRes);
			}
		}

		// lotNumber
		String lotNumber = filter.getLotNumber();
		if (lotNumber != null) {
			Property lotNumberProp = model.createProperty(URISet.OME_LOTNUMBER_PROPERTY_URI);
			model.add(filterRes, lotNumberProp, lotNumber);
		}

		// manufacturer
		String manufacturer = filter.getManufacturer();
		if (manufacturer != null) {
			Property manufacturerProp = model.createProperty(URISet.OME_MANUFACTURER_PROPERTY_URI);
			model.add(filterRes, manufacturerProp, manufacturer);
		}

		// model
		String modelStr = filter.getModel();
		if (modelStr != null) {
			Property modelProp = model.createProperty(URISet.OME_MODEL_PROPERTY_URI);
			model.add(filterRes, modelProp, modelStr);
		}

		// serialNumber
		String serialNumber = filter.getSerialNumber();
		if (serialNumber != null) {
			Property serialNumberProp = model.createProperty(URISet.OME_SERIALNUMBER_PROPERTY_URI);
			model.add(filterRes, serialNumberProp, serialNumber);
		}

		// instrument
		Instrument instrument = filter.getInstrument();
		if (instrument != null) {
			toRDF(instrument, filterRes, model, instanceNamespace);
		}

		// filterWheel
		String filterWheel = filter.getFilterWheel();
		if (filterWheel != null) {
			Property filterWheelProp = model.createProperty(URISet.OME_FILTERWHEEL_PROPERTY_URI);
			model.add(filterRes, filterWheelProp, filterWheel);
		}

		// transmittanceRange
		TransmittanceRange transmittanceRange = filter.getTransmittanceRange();
		if (transmittanceRange != null) {
			toRDF(transmittanceRange, filterRes, model, instanceNamespace);
		}

		// filterType
		FilterType filterType = filter.getType();
		if (filterType != null) {
			String value = filterType.getValue();
			String uri = URISet.getFilterTypeURI(value);
			Resource res = model.createResource(uri);
			Property filterTypeProp = model.createProperty(URISet.OME_FILTERTYPE_PROPERTY_URI);
			model.add(filterRes, filterTypeProp, res);
			// prefix
			model.setNsPrefix("filterType", URISet.OME_FILTERTYPE_CLASS_URI + "#");
		}

		// link
		// annotation
		int linkedAnnotationCnt = filter.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation linkedAnnotation = filter.getLinkedAnnotation(k);
			toRDF(linkedAnnotation, filterRes, model, instanceNamespace);
		}

//		  int linkedFilterSetEmissionFilterCnt =
//		  filter.sizeOfLinkedFilterSetEmissionFilterList(); for (int k = 0; k <
//		  linkedFilterSetEmissionFilterCnt; k++) { FilterSet
//		  linkedFilterSetEmissionFilter = filter.getLinkedFilterSetEmissionFilter(k);
//		  toRDF(linkedFilterSetEmissionFilter, filterRes, model, instanceNamespace); }
//		  
//		  int linkedFilterSetExcitationFilterCnt =
//		  filter.sizeOfLinkedFilterSetExcitationFilterList(); for (int k = 0; k <
//		  linkedFilterSetExcitationFilterCnt; k++) { FilterSet
//		  linkedFilterSetExcitationFilter =
//		  filter.getLinkedFilterSetExcitationFilter(k);
//		  toRDF(linkedFilterSetExcitationFilter, filterRes, model, instanceNamespace);
//		  }
//		  
//		  int linkedLightPathEmissionFilterCnt =
//		  filter.sizeOfLinkedLightPathEmissionFilterList(); for (int k = 0; k <
//		  linkedLightPathEmissionFilterCnt; k++) { LightPath
//		  linkedLightPathEmissionFilter = filter.getLinkedLightPathEmissionFilter(k);
//		  toRDF(linkedLightPathEmissionFilter, filterRes, model, instanceNamespace); }
//		  
//		  int linkedLightPathExcitationFilterCnt =
//		  filter.sizeOfLinkedLightPathExcitationFilterList(); for (int k = 0; k <
//		  linkedLightPathExcitationFilterCnt; k++) { LightPath
//		  linkedLightPathExcitationFilter =
//		  filter.getLinkedLightPathExcitationFilter(k);
//		  toRDF(linkedLightPathExcitationFilter, filterRes, model, instanceNamespace);
//		  }
	}

//-------------------------------------------------------------------------------------------------------------------
//  TransmittanceRange
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(TransmittanceRange transmittanceRange, Resource origin, Model model, String instanceNamespace)
			throws Exception {
		if (transmittanceRange == null) {
			return;
		}
		if (createLog.containsKey(transmittanceRange)) {
			if (origin != null) {
				Property transmittanceRangeProp = model.createProperty(URISet.OME_TRANSMITTANCERANGE_PROPERTY_URI);
				Resource res = createLog.get(transmittanceRange);
				model.add(origin, transmittanceRangeProp, res);
			}
			return;
		}

		String genID = getUniqueID("transmittanceRange", null);
		Resource transmittanceRangeRes = model.createResource(instanceNamespace + "TransmittanceRange/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource transmittanceRangeClsRes = model.createResource(URISet.OME_TRANSMITTANCERANGE_CLASS_URI);
		model.add(transmittanceRangeRes, typeProp, transmittanceRangeClsRes);
		createLog.put(transmittanceRange, transmittanceRangeRes);
		if (origin != null) {
			Property transmittanceRangeProp = model.createProperty(URISet.OME_FILTER_PROPERTY_URI);
			model.add(origin, transmittanceRangeProp, transmittanceRangeRes);
		}

		// cutIn
		Length cutIn = transmittanceRange.getCutIn();
		createLengthBlankNodeValue(cutIn, TransmittanceRange.getCutInUnitXsdDefault(), model, transmittanceRangeRes,
				model.createProperty(URISet.OME_CUTIN_PROPERTY_URI),
				model.createResource(URISet.PATO_WAVELENGTH_CLASS_URI));

		// cutInTolerance
		Length cutInTolerance = transmittanceRange.getCutInTolerance();
		createLengthBlankNodeValue(cutInTolerance, TransmittanceRange.getCutInToleranceUnitXsdDefault(), model,
				transmittanceRangeRes, model.createProperty(URISet.OME_CUTINTOLERANCE_PROPERTY_URI),
				model.createResource(URISet.PATO_WAVELENGTH_CLASS_URI));

		// cutOut
		Length cutOut = transmittanceRange.getCutOut();
		createLengthBlankNodeValue(cutOut, TransmittanceRange.getCutOutUnitXsdDefault(), model, transmittanceRangeRes,
				model.createProperty(URISet.OME_CUTOUT_PROPERTY_URI),
				model.createResource(URISet.PATO_WAVELENGTH_CLASS_URI));

		// cutOutTolerance
		Length cutOutTolerance = transmittanceRange.getCutOutTolerance();
		createLengthBlankNodeValue(cutOutTolerance, TransmittanceRange.getCutOutToleranceUnitXsdDefault(), model,
				transmittanceRangeRes, model.createProperty(URISet.OME_CUTOUTTOLERANCE_PROPERTY_URI),
				model.createResource(URISet.PATO_WAVELENGTH_CLASS_URI));

		// transmittance
		PercentFraction transmittance = transmittanceRange.getTransmittance();
		if (transmittance != null) {
			float value = transmittance.getValue();
			Property transmittanceProp = model.createProperty(URISet.OME_TRANSMITTANCE_PROPERTY_URI);
			model.add(transmittanceRangeRes, transmittanceProp, model.createTypedLiteral(value));
		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  FilterSet
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(FilterSet filterSet, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (filterSet == null) {
			return;
		}

		if (createLog.containsKey(filterSet)) {
			if (origin != null) {
				Property filterSetProp = model.createProperty(URISet.OME_FILTERSET_PROPERTY_URI);
				Resource res = createLog.get(filterSet);
				model.add(origin, filterSetProp, res);
			}
			return;
		}

		String id = filterSet.getID();
		String genID = getUniqueID("filterSet", id);
		Resource filterSetRes = model.createResource(instanceNamespace + "FilterSet/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource filterSetClsRes = model.createResource(URISet.OME_FILTERSET_CLASS_URI);
		model.add(filterSetRes, typeProp, filterSetClsRes);
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(filterSetRes, idProp, id);
		createLog.put(filterSet, filterSetRes);
		if (origin != null) {
			Property filterSetProp = model.createProperty(URISet.OME_FILTERSET_PROPERTY_URI);
			model.add(origin, filterSetProp, filterSetRes);
		}

		// lotNumber
		String lotNumber = filterSet.getLotNumber();
		if (lotNumber != null) {
			Property lotNumberProp = model.createProperty(URISet.OME_LOTNUMBER_PROPERTY_URI);
			model.add(filterSetRes, lotNumberProp, lotNumber);
		}

		// manufacturer
		String manufacturer = filterSet.getManufacturer();
		if (manufacturer != null) {
			Property manufacturerProp = model.createProperty(URISet.OME_MANUFACTURER_PROPERTY_URI);
			model.add(filterSetRes, manufacturerProp, manufacturer);
		}

		// model
		String modelStr = filterSet.getModel();
		if (modelStr != null) {
			Property modelProp = model.createProperty(URISet.OME_MODEL_PROPERTY_URI);
			model.add(filterSetRes, modelProp, modelStr);
		}

		// serialNumber
		String serialNumber = filterSet.getSerialNumber();
		if (serialNumber != null) {
			Property serialNumberProp = model.createProperty(URISet.OME_SERIALNUMBER_PROPERTY_URI);
			model.add(filterSetRes, serialNumberProp, serialNumber);
		}

		// instrument
		Instrument instrument = filterSet.getInstrument();
		if (instrument != null) {
			toRDF(instrument, filterSetRes, model, instanceNamespace);
		}

		// link
//		// channel
//		int linkedChannelCnt = filterSet.sizeOfLinkedChannelList();
//		for (int k = 0; k < linkedChannelCnt; k++) {
//			Channel channel = filterSet.getLinkedChannel(k);
//		}

		// dichroic
		Dichroic linkedDichroic = filterSet.getLinkedDichroic();
		toRDF(linkedDichroic, filterSetRes, model, instanceNamespace);

		// emissionFilter
		int linkedEmissionFilterCnt = filterSet.sizeOfLinkedEmissionFilterList();
		Property emissionFilterProp = model.createProperty(URISet.OME_EMISSIONFILTER_PROPERTY_URI);
		for (int k = 0; k < linkedEmissionFilterCnt; k++) {
			Filter linkedEmissionFilter = filterSet.getLinkedEmissionFilter(k);
			toRDF(linkedEmissionFilter, filterSetRes, emissionFilterProp, model, instanceNamespace);
		}

		// excitationFilter
		int linkedExcitationFilterCnt = filterSet.sizeOfLinkedExcitationFilterList();
		Property excitationFilterProp = model.createProperty(URISet.OME_EXCITATIONFILTER_PROPERTY_URI);
		for (int k = 0; k < linkedExcitationFilterCnt; k++) {
			Filter linkedExcitationFilter = filterSet.getLinkedExcitationFilter(k);
			toRDF(linkedExcitationFilter, filterSetRes, excitationFilterProp, model, instanceNamespace);
		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  LightSource
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(LightSource lightSource, Resource origin, Property prop, Model model, String instanceNamespace)
			throws Exception {
		if (lightSource == null) {
			return;
		}

		if (createLog.containsKey(lightSource)) {
			if (origin != null) {
				Resource res = createLog.get(lightSource);
				if (prop != null) {
					model.add(origin, prop, res);
				} else {
					Property lightSourceProp = model.createProperty(URISet.OME_LIGHTSOURCE_PROPERTY_URI);
					model.add(origin, lightSourceProp, res);
				}
			}
			return;
		}

		String id = lightSource.getID();
		Resource lightSourceRes = null;
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);

		if (lightSource instanceof Arc) {
			Arc arc = (Arc) lightSource;
			String genID = getUniqueID("arc", id);
			lightSourceRes = model.createResource(instanceNamespace + "Arc/" + genID);
			Resource arcClsRes = model.createResource(URISet.OME_ARC_CLASS_URI);
			model.add(lightSourceRes, typeProp, arcClsRes);

			// arcType
			ArcType arcType = arc.getType();
			if (arcType != null) {
				String uri = URISet.getArcTypeURI(arcType.getValue());
				Resource arcTypeRes = model.createResource(uri);
				Property arcTypeProp = model.createProperty(URISet.OME_ARCTYPE_PROPERTY_URI);
				model.add(lightSourceRes, arcTypeProp, arcTypeRes);
				// prefix
				model.setNsPrefix("arcType", URISet.OME_ARCTYPE_CLASS_URI + "#");
			}
		}

		if (lightSource instanceof Filament) {
			Filament filament = (Filament) lightSource;
			String genID = getUniqueID("filament", id);
			lightSourceRes = model.createResource(instanceNamespace + "Filament/" + genID);
			Resource filamentClsRes = model.createResource(URISet.OME_FILAMENT_CLASS_URI);
			model.add(lightSourceRes, typeProp, filamentClsRes);

			// filamentType
			FilamentType filamentType = filament.getType();
			if (filamentType != null) {
				String uri = URISet.getFilamentTypeURI(filamentType.getValue());
				Resource filamentTypeRes = model.createResource(uri);
				Property filamentTypeProp = model.createProperty(URISet.OME_FILAMENTTYPE_PROPERTY_URI);
				model.add(lightSourceRes, filamentTypeProp, filamentTypeRes);
				// prefix
				model.setNsPrefix("filamentType", URISet.OME_FILAMENTTYPE_CLASS_URI + "#");
			}
		}

		if (lightSource instanceof GenericExcitationSource) {
			GenericExcitationSource genericExcitationSource = (GenericExcitationSource) lightSource;
			String genID = getUniqueID("genericExcitationSource", id);
			lightSourceRes = model.createResource(instanceNamespace + "GenericExcitationSource/" + genID);
			Resource genericExcitationSourceClsRes = model.createResource(URISet.OME_GENERICEXCITATIONSOURCE_CLASS_URI);
			model.add(lightSourceRes, typeProp, genericExcitationSourceClsRes);

			// map
			List<MapPair> mapList = genericExcitationSource.getMap();
			if (mapList != null) {
				Property mapProp = model.createProperty(URISet.OME_MAP_PROPERTY_URI);
				Property mapKeyProp = model.createProperty(URISet.OME_MAPKEY_PROPERTY_URI);
				Property mapValueProp = model.createProperty(URISet.OME_MAPVALUE_PROPERTY_URI);
				for (MapPair map : mapList) {
					String key = map.getName();
					String value = map.getValue();
					Resource mapRes = model.createResource();
					model.add(lightSourceRes, mapProp, mapRes);
					model.add(mapRes, mapKeyProp, key);
					model.add(mapRes, mapValueProp, value);
				}
			}
		}

		if (lightSource instanceof Laser) {
			Laser laser = (Laser) lightSource;
			String genID = getUniqueID("laser", id);
			lightSourceRes = model.createResource(instanceNamespace + "Laser/" + genID);
			Resource laserClsRes = model.createResource(URISet.OME_LASER_CLASS_URI);
			model.add(lightSourceRes, typeProp, laserClsRes);

			// frequencyMultiplication
			PositiveInteger frequencyMultiplication = laser.getFrequencyMultiplication();
			if (frequencyMultiplication != null) {
				Property frequencyMultiplicationProp = model
						.createProperty(URISet.OME_FREQUENCYMULTIPLICATION_PROPERTY_URI);
				model.add(lightSourceRes, frequencyMultiplicationProp, model.createTypedLiteral(
						frequencyMultiplication.getValue().toString(), URISet.XSD_POSITIVEINTEGER_DATATYPE_URI));
			}

			// laserMedium
			LaserMedium laserMedium = laser.getLaserMedium();
			if (laserMedium != null) {
				String uri = URISet.getLaserMediumURI(laserMedium.getValue());
				Resource laserMediumRes = model.createResource(uri);
				Property laserMediumProp = model.createProperty(URISet.OME_LASERMEDIUM_PROPERTY_URI);
				model.add(lightSourceRes, laserMediumProp, laserMediumRes);
				// prefix
				model.setNsPrefix("laserMedium", URISet.OME_LASERMEDIUM_CLASS_URI + "#");

			}

			// pump
			LightSource pump = laser.getLinkedPump();
			if (pump != null) {
				Property pumpProp = model.createProperty(URISet.OME_PUMP_PROPERTY_URI);
				toRDFAsPump(pump, lightSourceRes, pumpProp, model, instanceNamespace);
			}

			// pockelCell
			Boolean pockelCell = laser.getPockelCell();
			if (pockelCell != null) {
				Property pockelCellProp = model.createProperty(URISet.OME_POCKELCELL_PROPERTY_URI);
				model.add(lightSourceRes, pockelCellProp, model.createTypedLiteral(pockelCell.booleanValue()));
			}

			// pulse
			Pulse pulse = laser.getPulse();
			if (pulse != null) {
				String uri = URISet.getPulseURI(laserMedium.getValue());
				Resource pulseRes = model.createResource(uri);
				Property pulseProp = model.createProperty(URISet.OME_PULSE_PROPERTY_URI);
				model.add(lightSourceRes, pulseProp, pulseRes);
				// prefix
				model.setNsPrefix("pulse", URISet.OME_PULSE_CLASS_URI + "#");
			}

			// repetitionRate
			Frequency repetitionRate = laser.getRepetitionRate();
			createFrequencyBlankNodeValue(repetitionRate, TransmittanceRange.getCutInUnitXsdDefault(), model,
					lightSourceRes, model.createProperty(URISet.OME_REPETITIONRATE_PROPERTY_URI),
					model.createResource(URISet.PATO_FREQUENCY_CLASS_URI));

			// tuneable
			Boolean tuneable = laser.getTuneable();
			if (tuneable != null) {
				Property tuneableProp = model.createProperty(URISet.OME_TUNEABLE_PROPERTY_URI);
				model.add(lightSourceRes, tuneableProp, model.createTypedLiteral(tuneable.booleanValue()));
			}

			// laserType
			LaserType laserType = laser.getType();
			if (laserType != null) {
				String uri = URISet.getLaserTypeURI(laserType.getValue());
				Resource laserTypeRes = model.createResource(uri);
				Property laserTypeProp = model.createProperty(URISet.OME_LASERTYPE_PROPERTY_URI);
				model.add(lightSourceRes, laserTypeProp, laserTypeRes);
				// prefix
				model.setNsPrefix("laserType", URISet.OME_LASERTYPE_CLASS_URI + "#");
			}

			// wavelength
			Length wavelength = laser.getWavelength();
			createLengthBlankNodeValue(wavelength, Laser.getWavelengthUnitXsdDefault(), model, lightSourceRes,
					model.createProperty(URISet.OME_WAVELENGTH_PROPERTY_URI),
					model.createResource(URISet.PATO_WAVELENGTH_CLASS_URI));
		}

		if (lightSource instanceof LightEmittingDiode) {
			String genID = getUniqueID("led", id);
			lightSourceRes = model.createResource(instanceNamespace + "LED/" + genID);
			Resource ledClsRes = model.createResource(URISet.OME_LED_CLASS_URI);
			model.add(lightSourceRes, typeProp, ledClsRes);
		}

		if (id != null) {
			Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
			model.add(lightSourceRes, idProp, id);
		}

		createLog.put(lightSource, lightSourceRes);
		if (origin != null) {
			if (prop != null) {
				model.add(origin, prop, lightSourceRes);
			} else {
				Property lightSourceProp = model.createProperty(URISet.OME_LIGHTSOURCE_PROPERTY_URI);
				model.add(origin, lightSourceProp, lightSourceRes);
			}
		}

		// lotNumber
		String lotNumber = lightSource.getLotNumber();
		if (lotNumber != null) {
			Property lotNumberProp = model.createProperty(URISet.OME_LOTNUMBER_PROPERTY_URI);
			model.add(lightSourceRes, lotNumberProp, lotNumber);
		}

		// manufacturer
		String manufacturer = lightSource.getManufacturer();
		if (manufacturer != null) {
			Property manufacturerProp = model.createProperty(URISet.OME_MANUFACTURER_PROPERTY_URI);
			model.add(lightSourceRes, manufacturerProp, manufacturer);
		}

		// model
		String modelStr = lightSource.getModel();
		if (modelStr != null) {
			Property modelProp = model.createProperty(URISet.OME_MODEL_PROPERTY_URI);
			model.add(lightSourceRes, modelProp, modelStr);
		}

		// serialNumber
		String serialNumber = lightSource.getSerialNumber();
		if (serialNumber != null) {
			Property serialNumberProp = model.createProperty(URISet.OME_SERIALNUMBER_PROPERTY_URI);
			model.add(lightSourceRes, serialNumberProp, serialNumber);
		}

		// instrument
		Instrument instrument = lightSource.getInstrument();
		if (instrument != null) {
			toRDF(instrument, lightSourceRes, model, instanceNamespace);
		}

		// power
		Power power = lightSource.getPower();
		createPowerBlankNodeValue(power, LightSource.getPowerUnitXsdDefault(), model, lightSourceRes,
				model.createProperty(URISet.OME_POWER_PROPERTY_URI), model.createResource(URISet.PATO_POWER_CLASS_URI));

		// link
		// annotation
		int linkedAnnotationCnt = lightSource.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation linkedAnnotation = lightSource.getLinkedAnnotation(k);
			toRDF(linkedAnnotation, lightSourceRes, model, instanceNamespace);
		}
	}

// -------------------------------------------------------------------------------------------------------------------
//  Pump
//-------------------------------------------------------------------------------------------------------------------

	public void toRDFAsPump(LightSource lightSource, Resource origin, Property prop, Model model,
			String instanceNamespace) throws Exception {
		if (lightSource == null) {
			return;
		}

		if (createLog.containsKey(lightSource)) {
			if (origin != null) {
				Resource res = createLog.get(lightSource);
				if (prop != null) {
					model.add(origin, prop, res);
				} else {
					Property pumpProp = model.createProperty(URISet.OME_PUMP_PROPERTY_URI);
					model.add(origin, pumpProp, res);
				}
			}
			return;
		}

		String id = lightSource.getID();
		Resource lightSourceRes = null;
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);

		String genID = getUniqueID("pump", id);
		lightSourceRes = model.createResource(instanceNamespace + "Pump/" + genID);
		Resource pumpClsRes = model.createResource(URISet.OME_PUMP_CLASS_URI);
		model.add(lightSourceRes, typeProp, pumpClsRes);

		if (id != null) {
			Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
			model.add(lightSourceRes, idProp, id);
		}

		createLog.put(lightSource, lightSourceRes);
		if (origin != null) {
			if (prop != null) {
				model.add(origin, prop, lightSourceRes);
			} else {
				Property pumpProp = model.createProperty(URISet.OME_PUMP_PROPERTY_URI);
				model.add(origin, pumpProp, lightSourceRes);
			}
		}
	}
//-------------------------------------------------------------------------------------------------------------------
//  Objective
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Objective objective, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (objective == null) {
			return;
		}
		if (createLog.containsKey(objective)) {
			if (origin != null) {
				Property objectiveProp = model.createProperty(URISet.OME_OBJECTIVE_PROPERTY_URI);
				Resource res = createLog.get(objective);
				model.add(origin, objectiveProp, res);
			}
			return;
		}

		String id = objective.getID();
		String genID = getUniqueID("objective", id);
		Resource objectiveRes = model.createResource(instanceNamespace + "Objective/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource objectiveClsRes = model.createResource(URISet.OME_OBJECTIVE_CLASS_URI);
		model.add(objectiveRes, typeProp, objectiveClsRes);
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(objectiveRes, idProp, id);
		createLog.put(objective, objectiveRes);
		if (origin != null) {
			Property objectiveProp = model.createProperty(URISet.OME_OBJECTIVE_PROPERTY_URI);
			model.add(origin, objectiveProp, objectiveRes);
		}

		// calibratedMagnification
		Double calibratedMagnification = objective.getCalibratedMagnification();
		if (calibratedMagnification != null) {
			Property calibratedMagnificationProp = model
					.createProperty(URISet.OME_CALIBRATEDMAGNIFICATION_PROPERTY_URI);
			model.add(objectiveRes, calibratedMagnificationProp,
					model.createTypedLiteral(calibratedMagnification.doubleValue()));
		}

		// correction
		Correction correction = objective.getCorrection();
		if (correction != null) {
			toRDF(correction, objectiveRes, model, instanceNamespace);
		}

		// immersion
		Immersion immersion = objective.getImmersion();
		if (immersion != null) {
			toRDF(immersion, objectiveRes, model, instanceNamespace);
		}

		// instrument
		Instrument instrument = objective.getInstrument();
		if (instrument != null) {
			toRDF(instrument, objectiveRes, model, instanceNamespace);
		}

		// iris
		Boolean iris = objective.getIris();
		if (iris != null) {
			Property irisProp = model.createProperty(URISet.OME_IRIS_PROPERTY_URI);
			model.add(objectiveRes, irisProp, model.createTypedLiteral(iris.booleanValue()));
		}

		// lensNA
		Double lensNA = objective.getLensNA();
		if (lensNA != null) {
			Property lensNAProp = model.createProperty(URISet.OME_LENSNA_PROPERTY_URI);
			model.add(objectiveRes, lensNAProp, model.createTypedLiteral(lensNA.doubleValue()));
		}

		// lotNumber
		String lotNumber = objective.getLotNumber();
		if (lotNumber != null) {
			Property lotNumberProp = model.createProperty(URISet.OME_LOTNUMBER_PROPERTY_URI);
			model.add(objectiveRes, lotNumberProp, lotNumber);
		}

		// manufacturer
		String manufacturer = objective.getManufacturer();
		if (manufacturer != null) {
			Property manufacturerProp = model.createProperty(URISet.OME_MANUFACTURER_PROPERTY_URI);
			model.add(objectiveRes, manufacturerProp, manufacturer);
		}

		// model
		String modelStr = objective.getModel();
		if (modelStr != null) {
			Property modelProp = model.createProperty(URISet.OME_MODEL_PROPERTY_URI);
			model.add(objectiveRes, modelProp, modelStr);
		}

		// nominalMagnification
		Double nominalMagnification = objective.getNominalMagnification();
		if (nominalMagnification != null) {
			Property nominalMagnificationProp = model.createProperty(URISet.OME_NOMINALMAGNIFICATION_PROPERTY_URI);
			model.add(objectiveRes, nominalMagnificationProp,
					model.createTypedLiteral(nominalMagnification.doubleValue()));
		}

		// serialNumber
		String serialNumber = objective.getSerialNumber();
		if (serialNumber != null) {
			Property serialNumberProp = model.createProperty(URISet.OME_SERIALNUMBER_PROPERTY_URI);
			model.add(objectiveRes, serialNumberProp, serialNumber);
		}

		// workingDistance
		Length workingDistance = objective.getWorkingDistance();
		createLengthBlankNodeValue(workingDistance, Objective.getWorkingDistanceUnitXsdDefault(), model, objectiveRes,
				model.createProperty(URISet.OME_WORKINGDISTANCE_PROPERTY_URI),
				model.createResource(URISet.PATO_DISTANCE_CLASS_URI));

		// link
		// annotation
		int linkedAnnotationCnt = objective.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation linkedAnnotation = objective.getLinkedAnnotation(k);
			toRDF(linkedAnnotation, objectiveRes, model, instanceNamespace);
		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  Correction
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Correction correction, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (correction == null) {
			return;
		}
		String value = correction.getValue();
		Resource res = model.createResource(URISet.getCorrectionURI(value));
		Property correctionProp = model.createProperty(URISet.OME_CORRECTION_PROPERTY_URI);
		model.add(origin, correctionProp, res);
		// prefix
		model.setNsPrefix("correction", URISet.OME_CORRECTION_CLASS_URI + "#");

	}

//-------------------------------------------------------------------------------------------------------------------
//  Immersion
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Immersion immersion, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (immersion == null) {
			return;
		}
		String value = immersion.getValue();
		Resource res = model.createResource(URISet.getImmersionURI(value));
		Property immersionProp = model.createProperty(URISet.OME_IMMERSION_PROPERTY_URI);
		model.add(origin, immersionProp, res);
		// prefix
		model.setNsPrefix("immersion", URISet.OME_IMMERSION_CLASS_URI + "#");
	}

//-------------------------------------------------------------------------------------------------------------------
//  Image
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Image image, Model model, String instanceNamespace) throws Exception {
		toRDF(image, null, model, instanceNamespace);
	}

	public void toRDF(Image image, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (image == null) {
			return;
		}
		if (createLog.containsKey(image)) {
			if (origin != null) {
				Property imageProp = model.createProperty(URISet.OME_IMAGE_PEOPERTY_URI);
				Resource res = createLog.get(image);
				model.add(origin, imageProp, res);
			}
			return;
		}

		String id = image.getID();
		String genID = getUniqueID("image", id);
		Resource imageRes = model.createResource(instanceNamespace + "Image/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource imageClsRes = model.createResource(URISet.OME_IMAGE_CLASS_URI);
		model.add(imageRes, typeProp, imageClsRes);
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(imageRes, idProp, id);
		createLog.put(image, imageRes);
		if (origin != null) {
			Property imageProp = model.createProperty(URISet.OME_IMAGE_PEOPERTY_URI);
			model.add(origin, imageProp, imageRes);
		}

		// name
		String name = image.getName();
		if (name != null) {
			Property labelProp = model.createProperty(URISet.RDFS_LABEL_PROPERTY_URI);
			model.add(imageRes, labelProp, name, "en");
		}

		// acquisitionDate
		Timestamp acquisitionDate = image.getAcquisitionDate();
		if (acquisitionDate != null) {
			Property acquisitionDateProp = model.createProperty(URISet.OME_ACQUISITIONDATE_PROPERTY_URI);
			Literal acquisitionDateLiteral = model.createTypedLiteral(acquisitionDate.getValue(),
					URISet.XSD_DATETIME_DATATYPE_URI);
			model.add(imageRes, acquisitionDateProp, acquisitionDateLiteral);
		}

		// pixels
		Pixels pixels = image.getPixels();
		if (pixels != null) {
			toRDF(pixels, imageRes, model, instanceNamespace);
		}

		ImagingEnvironment imagingEnvironment = image.getImagingEnvironment();
		ObjectiveSettings objectiveSettings = image.getObjectiveSettings();
		StageLabel stageLabel = image.getStageLabel();
		if (imagingEnvironment != null || objectiveSettings != null || stageLabel != null) {
			// imagingCondition
			String imagingConditionGenID = getUniqueID("imagingCondition", id);
			Resource imagingConditionRes = model
					.createResource(instanceNamespace + "ImagingCondition/" + imagingConditionGenID);
			Resource imagingConditionClsRes = model.createResource(URISet.OME_IMAGINGCONDITION_CLASS_URI);
			model.add(imagingConditionRes, typeProp, imagingConditionClsRes);
			Property imagingConditionProp = model.createProperty(URISet.OME_IMAGINGCONDITION_PROPERTY_URI);
			model.add(imageRes, imagingConditionProp, imagingConditionRes);

			if (imagingEnvironment != null) {
				toRDF(imagingEnvironment, imagingConditionRes, model, instanceNamespace);
			}

			if (objectiveSettings != null) {
				toRDF(objectiveSettings, imagingConditionRes, model, instanceNamespace);
			}

			if (stageLabel != null) {
				toRDF(stageLabel, imagingConditionRes, model, instanceNamespace);
			}
		}

		// link
		// experiment
		Experiment linkedExperiment = image.getLinkedExperiment();
		if (linkedExperiment != null) {
			toRDF(linkedExperiment, imageRes, model, instanceNamespace);
		}

		// experimenter
		Experimenter linkedExperimenter = image.getLinkedExperimenter();
		if (linkedExperimenter != null) {
			toRDF(linkedExperimenter, imageRes, null, model, instanceNamespace);
		}

		// experimenterGroup
		ExperimenterGroup linkedExperimenterGroup = image.getLinkedExperimenterGroup();
		if (linkedExperimenterGroup != null) {
			toRDF(linkedExperimenterGroup, imageRes, model, instanceNamespace);
		}

		// annotation
		int linkedAnnotationCnt = image.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation linkedAnnotation = image.getLinkedAnnotation(k);
			toRDF(linkedAnnotation, imageRes, model, instanceNamespace);
		}

//		// dataset
//		int linkedDatasetCnt = image.sizeOfLinkedDatasetList();
//		for (int k = 0; k < linkedDatasetCnt; k++) {
//			Dataset linkedDataset = image.getLinkedDataset(k);
//			toRDF(linkedDataset, imageRes, model, instanceNamespace);
//		}
//
//		// folder
//		int linkedFolderCnt = image.sizeOfLinkedFolderList();
//		for (int k = 0; k < linkedFolderCnt; k++) {
//			Folder linkedFolder = image.getLinkedFolder(k);
//			toRDF(linkedFolder, imageRes, model, instanceNamespace);
//		}

		// microbeamManipulation
		int linkedMicrobeamManipulationCnt = image.sizeOfLinkedMicrobeamManipulationList();
		for (int k = 0; k < linkedMicrobeamManipulationCnt; k++) {
			MicrobeamManipulation linkedMicrobeamManipulation = image.getLinkedMicrobeamManipulation(k);
			toRDF(linkedMicrobeamManipulation, imageRes, model, instanceNamespace);
		}

		// ROI
		int linkedROICnt = image.sizeOfLinkedROIList();
		for (int k = 0; k < linkedROICnt; k++) {
			ROI linkedROI = image.getLinkedROI(k);
			toRDF(linkedROI, imageRes, model, instanceNamespace);
		}

//		// wellSample
//		int linkedWellSampleCnt = image.sizeOfLinkedWellSampleList();
//		for (int k = 0; k < linkedWellSampleCnt; k++) {
//			WellSample linkedWellSample = image.getLinkedWellSample(k);
//			toRDF(linkedWellSample, imageRes, model, instanceNamespace);
//		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  ImagingEnvironment
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(ImagingEnvironment imagingEnvironment, Resource origin, Model model, String instanceNamespace)
			throws Exception {
		if (imagingEnvironment == null) {
			return;
		}
		String genID = getUniqueID("imagingEnvironment", null);
		Resource imagingEnvironmentRes = model.createResource(instanceNamespace + "ImagingEnvironment/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource imagingEnvironmentClsRes = model.createResource(URISet.OME_IMAGINGENVIRONMENT_CLASS_URI);
		model.add(imagingEnvironmentRes, typeProp, imagingEnvironmentClsRes);
		createLog.put(imagingEnvironment, imagingEnvironmentRes);
		if (origin != null) {
			Property imagingEnvironmentProp = model.createProperty(URISet.OME_IMAGINGENVIRONMENT_PROPERTY_URI);
			model.add(origin, imagingEnvironmentProp, imagingEnvironmentRes);
		}

		// airPressure
		Pressure airPressure = imagingEnvironment.getAirPressure();
		createPressureBlankNodeValue(airPressure, ImagingEnvironment.getAirPressureUnitXsdDefault(), model,
				imagingEnvironmentRes, model.createProperty(URISet.OME_AIRPRESSURE_PROPERTY_URI),
				model.createResource(URISet.PATO_PRESSURE_CLASS_URI));

		// co2Precent
		PercentFraction co2Percent = imagingEnvironment.getCO2Percent();
		if (co2Percent != null) {
			float value = co2Percent.getValue();
			Property co2PercentProp = model.createProperty(URISet.OME_CO2PERCENT_PROPERTY_URI);
			model.add(imagingEnvironmentRes, co2PercentProp, model.createTypedLiteral(value));
		}

		// humidity
		PercentFraction humidity = imagingEnvironment.getHumidity();
		if (humidity != null) {
			float value = humidity.getValue();
			Property humidityProp = model.createProperty(URISet.OME_HUMIDITY_PROPERTY_URI);
			model.add(imagingEnvironmentRes, humidityProp, model.createTypedLiteral(value));
		}

		// map
		List<MapPair> mapList = imagingEnvironment.getMap();
		if (mapList != null) {
			Property mapProp = model.createProperty(URISet.OME_MAP_PROPERTY_URI);
			Property mapKeyProp = model.createProperty(URISet.OME_MAPKEY_PROPERTY_URI);
			Property mapValueProp = model.createProperty(URISet.OME_MAPVALUE_PROPERTY_URI);
			for (MapPair map : mapList) {
				String key = map.getName();
				String value = map.getValue();
				Resource mapRes = model.createResource();
				model.add(imagingEnvironmentRes, mapProp, mapRes);
				model.add(mapRes, mapKeyProp, key);
				model.add(mapRes, mapValueProp, value);
			}
		}

		// temperature
		Temperature temperature = imagingEnvironment.getTemperature();
		createTemperatureBlankNodeValue(temperature, ImagingEnvironment.getTemperatureUnitXsdDefault(), model,
				imagingEnvironmentRes, model.createProperty(URISet.OME_TEMPERATURE_PROPERTY_URI),
				model.createResource(URISet.PATO_TEMPERATURE_CLASS_URI));
	}

//-------------------------------------------------------------------------------------------------------------------
//  ObjectiveSettings
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(ObjectiveSettings objectiveSettings, Resource origin, Model model, String instanceNamespace)
			throws Exception {
		if (objectiveSettings == null) {
			return;
		}
		if (createLog.containsKey(objectiveSettings)) {
			if (origin != null) {
				Property objectiveSettingsProp = model.createProperty(URISet.OME_OBJECTIVESETTINGS_PROPERTY_URI);
				Resource res = createLog.get(objectiveSettings);
				model.add(origin, objectiveSettingsProp, res);
			}
			return;
		}

		String id = objectiveSettings.getID();
		String genID = getUniqueID("objectiveSettings", id);
		Resource objectiveSettingsRes = model.createResource(instanceNamespace + "ObjectiveSettings/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource objectiveSettingsClsRes = model.createResource(URISet.OME_OBJECTIVESETTINGS_CLASS_URI);
		model.add(objectiveSettingsRes, typeProp, objectiveSettingsClsRes);
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(objectiveSettingsRes, idProp, id);
		createLog.put(objectiveSettings, objectiveSettingsRes);
		if (origin != null) {
			Property objectiveSettingsProp = model.createProperty(URISet.OME_OBJECTIVESETTINGS_PROPERTY_URI);
			model.add(origin, objectiveSettingsProp, objectiveSettingsRes);
		}

		// correctionCollar
		Double correctionCollar = objectiveSettings.getCorrectionCollar();
		if (correctionCollar != null) {
			Property correctionCollarProp = model.createProperty(URISet.OME_CORRECTIONCOLLAR_PROPERTY_URI);
			model.add(objectiveSettingsRes, correctionCollarProp,
					model.createTypedLiteral(correctionCollar.doubleValue()));
		}

		// medium
		Medium medium = objectiveSettings.getMedium();
		if (medium != null) {
			String value = medium.getValue();
			Resource res = model.createResource(URISet.getMediumURI(value));
			Property mediumProp = model.createProperty(URISet.OME_MEDIUM_PROPERTY_URI);
			model.add(objectiveSettingsRes, mediumProp, res);
			// prefix
			model.setNsPrefix("medium", URISet.OME_MEDIUM_CLASS_URI + "#");
		}

		// objective
		Objective objective = objectiveSettings.getObjective();
		if (objective != null) {
			toRDF(objective, objectiveSettingsRes, model, instanceNamespace);
		}

		// refractiveIndex
		Double refractiveIndex = objectiveSettings.getRefractiveIndex();
		if (refractiveIndex != null) {
			Property refractiveIndexProp = model.createProperty(URISet.OME_REFRACTIVEINDEX_PROPERTY_URI);
			model.add(objectiveSettingsRes, refractiveIndexProp,
					model.createTypedLiteral(refractiveIndex.doubleValue()));
		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  StageLabel
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(StageLabel stageLabel, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (stageLabel == null) {
			return;
		}
		if (createLog.containsKey(stageLabel)) {
			if (origin != null) {
				Property stageLabelProp = model.createProperty(URISet.OME_STAGELABEL_PROPERTY_URI);
				Resource res = createLog.get(stageLabel);
				model.add(origin, stageLabelProp, res);
			}
			return;
		}

		String genID = getUniqueID("stageLabel", null);
		Resource stageLabelRes = model.createResource(instanceNamespace + "StageLabel/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource stageLabelClsRes = model.createResource(URISet.OME_STAGELABEL_CLASS_URI);
		model.add(stageLabelRes, typeProp, stageLabelClsRes);
		createLog.put(stageLabel, stageLabelRes);
		if (origin != null) {
			Property stageLabelProp = model.createProperty(URISet.OME_STAGELABEL_PROPERTY_URI);
			model.add(origin, stageLabelProp, stageLabelRes);
		}

		// name
		String name = stageLabel.getName();
		if (name != null) {
			Property labelProp = model.createProperty(URISet.RDFS_LABEL_PROPERTY_URI);
			model.add(stageLabelRes, labelProp, name, "en");
		}

		// x
		Length x = stageLabel.getX();
		createLengthBlankNodeValue(x, StageLabel.getXUnitXsdDefault(), model, stageLabelRes,
				model.createProperty(URISet.OME_X_PROPERTY_URI), model.createResource(URISet.PATO_POSITION_CLASS_URI));

		// y
		Length y = stageLabel.getY();
		createLengthBlankNodeValue(y, StageLabel.getYUnitXsdDefault(), model, stageLabelRes,
				model.createProperty(URISet.OME_Y_PROPERTY_URI), model.createResource(URISet.PATO_POSITION_CLASS_URI));

		// z
		Length z = stageLabel.getZ();
		createLengthBlankNodeValue(z, StageLabel.getZUnitXsdDefault(), model, stageLabelRes,
				model.createProperty(URISet.OME_Z_PROPERTY_URI), model.createResource(URISet.PATO_POSITION_CLASS_URI));
	}

//-------------------------------------------------------------------------------------------------------------------
//  Pixels
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Pixels pixels, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (pixels == null) {
			return;
		}
		if (createLog.containsKey(pixels)) {
			if (origin != null) {
				Property pixelsProp = model.createProperty(URISet.OME_PIXELS_PROPERTY_URI);
				Resource res = createLog.get(pixels);
				model.add(origin, pixelsProp, res);
			}
			return;
		}

		String id = pixels.getID();
		String genID = getUniqueID("pixels", id);
		Resource pixelsRes = model.createResource(instanceNamespace + "Pixels/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource pixelsClsRes = model.createResource(URISet.OME_PIXELS_CLASS_URI);
		model.add(pixelsRes, typeProp, pixelsClsRes);
		createLog.put(pixels, pixelsRes);
		if (origin != null) {
			Property pixelsProp = model.createProperty(URISet.OME_PIXELS_PROPERTY_URI);
			model.add(origin, pixelsProp, pixelsRes);
		}
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(pixelsRes, idProp, id);

		// bigEndian
		Boolean bigEndian = pixels.getBigEndian();
		if (bigEndian != null) {
			Property bigEndianProp = model.createProperty(URISet.OME_BIGENDIAN_PROPERTY_URI);
			model.add(pixelsRes, bigEndianProp, model.createTypedLiteral(bigEndian.booleanValue()));
		}

		// dimensionOrder
		DimensionOrder dimensionOrder = pixels.getDimensionOrder();
		if (dimensionOrder != null) {
			Resource dimensionOrderRes = model.createResource(URISet.getDimensionOrderURI(dimensionOrder.getValue()));
			Property dimensionOrderProp = model.createProperty(URISet.OME_DIMENSIONORDER_PROPERTY_URI);
			model.add(pixelsRes, dimensionOrderProp, dimensionOrderRes);
			// prefix
			model.setNsPrefix("dimensionOrder", URISet.OME_DIMENSIONORDER_CLASS_URI + "#");
		}

		// interleaved
		Boolean interleaved = pixels.getInterleaved();
		if (interleaved != null) {
			Property interleavedProp = model.createProperty(URISet.OME_INTERLEAVED_PROPERTY_URI);
			model.add(pixelsRes, interleavedProp, model.createTypedLiteral(interleaved.booleanValue()));
		}

		// metadataOnly
		MetadataOnly metadataOnly = pixels.getMetadataOnly();
		if (metadataOnly != null) {
			Property metadataOnlyProp = model.createProperty(URISet.OME_METADATAONLY_PROPERTY_URI);
			model.add(pixelsRes, metadataOnlyProp, model.createTypedLiteral(true));
		}

		// physicalSizeX
		Length physicalSizeX = pixels.getPhysicalSizeX();
		createLengthBlankNodeValue(physicalSizeX, Pixels.getPhysicalSizeXUnitXsdDefault(), model, pixelsRes,
				model.createProperty(URISet.OME_PHYSICALSIZEX_PROPERTY_URI),
				model.createResource(URISet.PATO_SIZE_CLASS_URI));

		// physicalSizeY
		Length physicalSizeY = pixels.getPhysicalSizeY();
		createLengthBlankNodeValue(physicalSizeY, Pixels.getPhysicalSizeYUnitXsdDefault(), model, pixelsRes,
				model.createProperty(URISet.OME_PHYSICALSIZEY_PROPERTY_URI),
				model.createResource(URISet.PATO_SIZE_CLASS_URI));

		// physicalSizeZ
		Length physicalSizeZ = pixels.getPhysicalSizeZ();
		createLengthBlankNodeValue(physicalSizeZ, Pixels.getPhysicalSizeZUnitXsdDefault(), model, pixelsRes,
				model.createProperty(URISet.OME_PHYSICALSIZEZ_PROPERTY_URI),
				model.createResource(URISet.PATO_SIZE_CLASS_URI));

		// significantBits
		PositiveInteger significantBits = pixels.getSignificantBits();
		if (significantBits != null) {
			Property significantBitsProp = model.createProperty(URISet.OME_SIGNIFICANTBITS_PROPERTY_URI);
			model.add(pixelsRes, significantBitsProp, model.createTypedLiteral(significantBits.getValue().toString(),
					URISet.XSD_POSITIVEINTEGER_DATATYPE_URI));
		}

		// sizeC
		PositiveInteger sizeC = pixels.getSizeC();
		if (sizeC != null) {
			Property sizeCProp = model.createProperty(URISet.OME_SIZEC_PROPERTY_URI);
			model.add(pixelsRes, sizeCProp,
					model.createTypedLiteral(sizeC.getValue().toString(), URISet.XSD_POSITIVEINTEGER_DATATYPE_URI));
		}

		// sizeT
		PositiveInteger sizeT = pixels.getSizeT();
		if (sizeT != null) {
			Property sizeTProp = model.createProperty(URISet.OME_SIZET_PROPERTY_URI);
			model.add(pixelsRes, sizeTProp,
					model.createTypedLiteral(sizeT.getValue().toString(), URISet.XSD_POSITIVEINTEGER_DATATYPE_URI));
		}

		// sizeZ
		PositiveInteger sizeZ = pixels.getSizeZ();
		if (sizeZ != null) {
			Property sizeZProp = model.createProperty(URISet.OME_SIZEZ_PROPERTY_URI);
			model.add(pixelsRes, sizeZProp,
					model.createTypedLiteral(sizeZ.getValue().toString(), URISet.XSD_POSITIVEINTEGER_DATATYPE_URI));
		}

		// sizeX
		PositiveInteger sizeX = pixels.getSizeX();
		if (sizeX != null) {
			Property sizeXProp = model.createProperty(URISet.OME_SIZEX_PROPERTY_URI);
			model.add(pixelsRes, sizeXProp,
					model.createTypedLiteral(sizeX.getValue().toString(), URISet.XSD_POSITIVEINTEGER_DATATYPE_URI));
		}

		// sizeY
		PositiveInteger sizeY = pixels.getSizeY();
		if (sizeY != null) {
			Property sizeYProp = model.createProperty(URISet.OME_SIZEY_PROPERTY_URI);
			model.add(pixelsRes, sizeYProp,
					model.createTypedLiteral(sizeY.getValue().toString(), URISet.XSD_POSITIVEINTEGER_DATATYPE_URI));
		}

		// timeIncrement
		Time timeIncrement = pixels.getTimeIncrement();
		createTimeBlankNodeValue(timeIncrement, Pixels.getTimeIncrementUnitXsdDefault(), model, pixelsRes,
				model.createProperty(URISet.OME_TIMEINCREMENT_PROPERTY_URI),
				model.createResource(URISet.PATO_TIME_CLASS_URI));

		// pixelType
		PixelType pixelType = pixels.getType();
		if (pixelType != null) {
			String value = pixelType.getValue();
			String uri = URISet.getPixelTypeURI(value);
			Resource res = model.createResource(uri);
			Property pixelTypeProp = model.createProperty(URISet.OME_PIXELTYPE_PROPERTY_URI);
			model.add(pixelsRes, pixelTypeProp, res);
			// prefix
			model.setNsPrefix("pixelType", URISet.OME_PIXELTYPE_CLASS_URI + "#");
		}

		// bnData
		int binDataCnt = pixels.sizeOfBinDataList();
		for (int m = 0; m < binDataCnt; m++) {
			BinData bindata = pixels.getBinData(m);
			toRDF(bindata, pixelsRes, model, instanceNamespace);
		}

		// channel
		int channelCnt = pixels.sizeOfChannelList();
		for (int m = 0; m < channelCnt; m++) {
			Channel channel = pixels.getChannel(m);
			toRDF(channel, pixelsRes, model, instanceNamespace);
		}

		// plane
		int planeCnt = pixels.sizeOfPlaneList();
		for (int m = 0; m < planeCnt; m++) {
			Plane plane = pixels.getPlane(m);
			toRDF(plane, pixelsRes, model, instanceNamespace);
		}

		// tiffData
		int tiffDataCnt = pixels.sizeOfTiffDataList();
		for (int m = 0; m < tiffDataCnt; m++) {
			TiffData tiffData = pixels.getTiffData(m);
			toRDF(tiffData, pixelsRes, model, instanceNamespace);
		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  TiffData
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(TiffData tiffData, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (tiffData == null) {
			return;
		}
		if (createLog.containsKey(tiffData)) {
			if (origin != null) {
				Property tiffDataProp = model.createProperty(URISet.OME_TIFFDATA_PROPERTY_URI);
				Resource res = createLog.get(tiffData);
				model.add(origin, tiffDataProp, res);
			}
			return;
		}

		String genID = getUniqueID("tiffData", null);
		Resource tiffDataRes = model.createResource(instanceNamespace + "TiffData/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource tiffDataClsRes = model.createResource(URISet.OME_TIFFDATA_CLASS_URI);
		model.add(tiffDataRes, typeProp, tiffDataClsRes);
		createLog.put(tiffData, tiffDataRes);
		if (origin != null) {
			Property tiffDataProp = model.createProperty(URISet.OME_TIFFDATA_PROPERTY_URI);
			model.add(origin, tiffDataProp, tiffDataRes);
		}

		// firstC
		NonNegativeInteger firstC = tiffData.getFirstC();
		if (firstC != null) {
			Property firstCProp = model.createProperty(URISet.OME_FIRSTC_PROPERTY_URI);
			model.add(tiffDataRes, firstCProp, model.createTypedLiteral(firstC.getNumberValue().longValue(),
					URISet.XSD_NONNEGATIVELONG_DATATYPE_URI));
		}

		// firstT
		NonNegativeInteger firstT = tiffData.getFirstT();
		if (firstT != null) {
			Property firstTProp = model.createProperty(URISet.OME_FIRSTT_PROPERTY_URI);
			model.add(tiffDataRes, firstTProp, model.createTypedLiteral(firstT.getNumberValue().longValue(),
					URISet.XSD_NONNEGATIVELONG_DATATYPE_URI));
		}

		// firstZ
		NonNegativeInteger firstZ = tiffData.getFirstZ();
		if (firstZ != null) {
			Property firstZProp = model.createProperty(URISet.OME_FIRSTZ_PROPERTY_URI);
			model.add(tiffDataRes, firstZProp, model.createTypedLiteral(firstZ.getNumberValue().longValue(),
					URISet.XSD_NONNEGATIVELONG_DATATYPE_URI));
		}

		// ifd
		NonNegativeInteger ifd = tiffData.getIFD();
		if (ifd != null) {
			Property ifdProp = model.createProperty(URISet.OME_IFD_PROPERTY_URI);
			model.add(tiffDataRes, ifdProp, model.createTypedLiteral(ifd.getNumberValue().longValue(),
					URISet.XSD_NONNEGATIVELONG_DATATYPE_URI));
		}

		// ifd
		NonNegativeInteger planeCount = tiffData.getPlaneCount();
		if (planeCount != null) {
			Property planeCountProp = model.createProperty(URISet.OME_PLANECOUNT_PROPERTY_URI);
			model.add(tiffDataRes, planeCountProp, model.createTypedLiteral(planeCount.getNumberValue().longValue(),
					URISet.XSD_NONNEGATIVELONG_DATATYPE_URI));
		}

		// uuid
		UUID uuid = tiffData.getUUID();
		if (uuid != null) {
			String value = uuid.getValue();
			Property uuidProp = model.createProperty(URISet.OME_UUID_PROPERTY_URI);
			model.add(tiffDataRes, uuidProp, value);
		}

		// pixels
		Pixels pixels = tiffData.getPixels();
		if (pixels != null) {
			toRDF(pixels, tiffDataRes, model, instanceNamespace);
		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  BinData
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(BinData binData, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (binData == null) {
			return;
		}
		if (createLog.containsKey(binData)) {
			if (origin != null) {
				Property binDataProp = model.createProperty(URISet.OME_BINDATA_PROPERTY_URI);
				Resource res = createLog.get(binData);
				model.add(origin, binDataProp, res);
			}
			return;
		}

		String genID = getUniqueID("binData", null);
		Resource binDataRes = model.createResource(instanceNamespace + "BinData/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource binDataClsRes = model.createResource(URISet.OME_BINDATA_CLASS_URI);
		model.add(binDataRes, typeProp, binDataClsRes);
		createLog.put(binData, binDataRes);
		if (origin != null) {
			Property binDataProp = model.createProperty(URISet.OME_BINDATA_PROPERTY_URI);
			model.add(origin, binDataProp, binDataRes);
		}

		byte[] base64Binary = binData.getBase64Binary();
		if (base64Binary != null) {
			Property dataProp = model.createProperty(URISet.OME_DATA_PROPERTY_URI);
			model.addLiteral(binDataRes, dataProp,
					model.createTypedLiteral(base64Binary, URISet.XSD_BASE64BINARY_DATATYPE_URI));
		}

		Boolean bigEndian = binData.getBigEndian();
		if (bigEndian != null) {
			Property bigEndianProp = model.createProperty(URISet.OME_BIGENDIAN_PROPERTY_URI);
			model.add(binDataRes, bigEndianProp, model.createTypedLiteral(bigEndian.booleanValue()));
		}
		NonNegativeLong length = binData.getLength();
		if (length != null) {
			Property lengthProp = model.createProperty(URISet.OME_LENGTH_PROPERTY_URI);
			model.add(binDataRes, lengthProp, model.createTypedLiteral(length.getNumberValue().longValue(),
					URISet.XSD_NONNEGATIVELONG_DATATYPE_URI));

		}
		Pixels pixels = binData.getPixels();
		if (pixels != null) {
			toRDF(pixels, binDataRes, model, instanceNamespace);
		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  Channel
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Channel channel, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (channel == null) {
			return;
		}
		if (createLog.containsKey(channel)) {
			if (origin != null) {
				Property channelProp = model.createProperty(URISet.OME_CHANNEL_PROPERTY_URI);
				Resource res = createLog.get(channel);
				model.add(origin, channelProp, res);
			}
			return;
		}
		String id = channel.getID();
		String genID = getUniqueID("channel", id);
		Resource channelRes = model.createResource(instanceNamespace + "Channel/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource channelClsRes = model.createResource(URISet.OME_CHANNEL_CLASS_URI);
		model.add(channelRes, typeProp, channelClsRes);
		createLog.put(channel, channelRes);
		if (origin != null) {
			Property channelProp = model.createProperty(URISet.OME_CHANNEL_PROPERTY_URI);
			model.add(origin, channelProp, channelRes);
		}
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(channelRes, idProp, id);

		// acquisitionMode
		AcquisitionMode acquisitionMode = channel.getAcquisitionMode();
		if (acquisitionMode != null) {
			String value = acquisitionMode.getValue();
			String uri = URISet.getAcquisitionModeURI(value);
			Resource res = model.createResource(uri);
			Property acquisitionModeProp = model.createProperty(URISet.OME_ACQUISITIONMODE_PROPERTY_URI);
			model.add(channelRes, acquisitionModeProp, res);
			// prefix
			model.setNsPrefix("acquisitionMode", URISet.OME_ACQUISITIONMODE_CLASS_URI + "#");
		}

		// color
		Color color = channel.getColor();
		createColorBlankNode(color, model, channelRes, model.createProperty(URISet.OME_COLOR_PROPERTY_URI));

		// contrastMethod
		ContrastMethod contrastMethod = channel.getContrastMethod();
		if (contrastMethod != null) {
			String value = contrastMethod.getValue();
			String uri = URISet.getContrastMethodURI(value);
			Resource res = model.createResource(uri);
			Property contrastMethodProp = model.createProperty(URISet.OME_CONTRASTMETHOD_PROPERTY_URI);
			model.add(channelRes, contrastMethodProp, res);
			// prefix
			model.setNsPrefix("contrastMethod", URISet.OME_CONTRASTMETHOD_CLASS_URI + "#");
		}

		// detectorSettings
		DetectorSettings detectorSettings = channel.getDetectorSettings();
		if (detectorSettings != null) {
			toRDF(detectorSettings, channelRes, model, instanceNamespace);
		}

		// emissionWaveLength
		Length emissionWaveLength = channel.getEmissionWavelength();
		createLengthBlankNodeValue(emissionWaveLength, Channel.getEmissionWavelengthUnitXsdDefault(), model, channelRes,
				model.createProperty(URISet.OME_EMISSIONWAVELENGTH_PROPERTY_URI),
				model.createResource(URISet.PATO_WAVELENGTH_CLASS_URI));

		// excitationWaveLength
		Length excitationWaveLength = channel.getExcitationWavelength();
		createLengthBlankNodeValue(excitationWaveLength, Channel.getExcitationWavelengthUnitXsdDefault(), model,
				channelRes, model.createProperty(URISet.OME_EXCITATIONWAVELENGTH_PROPERTY_URI),
				model.createResource(URISet.PATO_WAVELENGTH_CLASS_URI));

		// fluor
		String fluor = channel.getFluor();
		if (fluor != null) {
			Property flourProp = model.createProperty(URISet.OME_FLUOR_PROPERTY_URI);
			model.add(channelRes, flourProp, fluor);
		}

		// illuminationType
		IlluminationType illuminationType = channel.getIlluminationType();
		if (illuminationType != null) {
			String value = illuminationType.getValue();
			String uri = URISet.getIlluminationTypeURI(value);
			Resource res = model.createResource(uri);
			Property illuminationTypeProp = model.createProperty(URISet.OME_ILLUMINATIONTYPE_PROPERTY_URI);
			model.add(channelRes, illuminationTypeProp, res);
			// prefix
			model.setNsPrefix("illuminationType", URISet.OME_ILLUMINATIONTYPE_CLASS_URI + "#");
		}

		// lightPath
		LightPath lightPath = channel.getLightPath();
		if (lightPath != null) {
			toRDF(lightPath, channelRes, model, instanceNamespace);
		}

		// lightSourceSettings
		LightSourceSettings lightSourceSettings = channel.getLightSourceSettings();
		if (lightSourceSettings != null) {
			toRDF(lightSourceSettings, channelRes, model, instanceNamespace);
		}

		// name
		String name = channel.getName();
		if (name != null) {
			Property labelProp = model.createProperty(URISet.RDFS_LABEL_PROPERTY_URI);
			model.add(channelRes, labelProp, name, "en");
		}

		// ndFilter
		Double ndFilter = channel.getNDFilter();
		if (ndFilter != null) {
			Property ndFilterProp = model.createProperty(URISet.OME_NDFILTER_PROPERTY_URI);
			model.add(channelRes, ndFilterProp, model.createTypedLiteral(ndFilter.doubleValue()));
		}

		// pinholeSize
		Length pinholeSize = channel.getPinholeSize();
		createLengthBlankNodeValue(pinholeSize, Channel.getPinholeSizeUnitXsdDefault(), model, channelRes,
				model.createProperty(URISet.OME_PINHOLESIZE_PROPERTY_URI),
				model.createResource(URISet.PATO_SIZE_CLASS_URI));

		// pixels
		Pixels pixels = channel.getPixels();
		if (pixels != null) {
			toRDF(pixels, channelRes, model, instanceNamespace);
		}

		// pockelCellSetting
		Integer pockelCellSetting = channel.getPockelCellSetting();
		if (pockelCellSetting != null) {
			Property pockelCellSettingProp = model.createProperty(URISet.OME_POCKELCELLSETTING_PROPERTY_URI);
			model.add(channelRes, pockelCellSettingProp, model.createTypedLiteral(pockelCellSetting.intValue()));
		}

		// samplesPerPixel
		PositiveInteger samplesPerPixel = channel.getSamplesPerPixel();
		if (samplesPerPixel != null) {
			Property samplesPerPixelProp = model.createProperty(URISet.OME_SAMPLESPERPIXEL_PROPERTY_URI);
			int val = samplesPerPixel.getValue().intValue();
			model.add(channelRes, samplesPerPixelProp,
					model.createTypedLiteral(val, URISet.XSD_POSITIVEINTEGER_DATATYPE_URI));
		}

		// link
		// annotation
		int linkedAnnotationCnt = channel.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation linkedAnnotation = channel.getLinkedAnnotation(k);
			toRDF(linkedAnnotation, channelRes, model, instanceNamespace);
		}

		// filterSet
		FilterSet linkedFilterSet = channel.getLinkedFilterSet();
		toRDF(linkedFilterSet, channelRes, model, instanceNamespace);
	}

//-------------------------------------------------------------------------------------------------------------------
//  DetectorSettings
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(DetectorSettings detectorSettings, Resource origin, Model model, String instanceNamespace)
			throws Exception {
		if (detectorSettings == null) {
			return;
		}
		if (createLog.containsKey(detectorSettings)) {
			if (origin != null) {
				Property detectorSettingsProp = model.createProperty(URISet.OME_DETECTORSETTINGS_PROPERTY_URI);
				Resource res = createLog.get(detectorSettings);
				model.add(origin, detectorSettingsProp, res);
			}
			return;
		}
		String id = detectorSettings.getID();
		String genID = getUniqueID("detectorSettings", id);
		Resource detectorSettingsRes = model.createResource(instanceNamespace + "DetectorSettings/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource detectorSettingsClsRes = model.createResource(URISet.OME_DETECTORSETTINGS_CLASS_URI);
		model.add(detectorSettingsRes, typeProp, detectorSettingsClsRes);
		createLog.put(detectorSettings, detectorSettingsRes);
		if (origin != null) {
			Property detectorSettingsProp = model.createProperty(URISet.OME_DETECTORSETTINGS_PROPERTY_URI);
			model.add(origin, detectorSettingsProp, detectorSettingsRes);
		}
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(detectorSettingsRes, idProp, id);

		// binning
		Binning binning = detectorSettings.getBinning();
		if (binning != null) {
			String value = binning.getValue();
			String uri = URISet.getBinningURI(value);
			Property binningProp = model.createProperty(URISet.OME_BINNING_PROPERTY_URI);
			Resource binningRes = model.createResource(uri);
			model.add(detectorSettingsRes, binningProp, binningRes);
			// prefix
			model.setNsPrefix("binning", URISet.OME_BINNING_CLASS_URI + "#");
		}

		// detector
		Detector detector = detectorSettings.getDetector();
		if (detector != null) {
			toRDF(detector, detectorSettingsRes, model, instanceNamespace);
		}

		// gain
		Double gain = detectorSettings.getGain();
		if (gain != null) {
			Property gainProp = model.createProperty(URISet.OME_GAIN_PROPERTY_URI);
			model.add(detectorSettingsRes, gainProp, model.createTypedLiteral(gain.doubleValue()));
		}

		// integration
		PositiveInteger integration = detectorSettings.getIntegration();
		if (integration != null) {
			Property integrationProp = model.createProperty(URISet.OME_INTEGRATION_PROPERTY_URI);
			model.add(detectorSettingsRes, integrationProp, model.createTypedLiteral(integration.getValue().toString(),
					URISet.XSD_POSITIVEINTEGER_DATATYPE_URI));
		}

		// offset
		Double offset = detectorSettings.getOffset();
		if (offset != null) {
			Property offsetProp = model.createProperty(URISet.OME_OFFSET_PROPERTY_URI);
			model.add(detectorSettingsRes, offsetProp, model.createTypedLiteral(offset.doubleValue()));
		}

		// voltage
		ElectricPotential voltage = detectorSettings.getVoltage();
		createElectricPotentialBlankNodeValue(voltage, DetectorSettings.getVoltageUnitXsdDefault(), model,
				detectorSettingsRes, model.createProperty(URISet.OME_VOLTAGE_PROPERTY_URI),
				model.createResource(URISet.SBO_VOLTAGE_CLASS_URI));

		// readOutRate
		Frequency readOutRate = detectorSettings.getReadOutRate();
		createFrequencyBlankNodeValue(readOutRate, DetectorSettings.getReadOutRateUnitXsdDefault(), model,
				detectorSettingsRes, model.createProperty(URISet.OME_READOUTRATE_PROPERTY_URI),
				model.createResource(URISet.PATO_FREQUENCY_CLASS_URI));

		// zoom
		Double zoom = detectorSettings.getZoom();
		if (zoom != null) {
			Property zoomProp = model.createProperty(URISet.OME_ZOOM_PROPERTY_URI);
			model.add(detectorSettingsRes, zoomProp, model.createTypedLiteral(zoom.doubleValue()));
		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  StructuredAnnotations
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(StructuredAnnotations structuredAnnotations, Model model, String instanceNamespace)
			throws Exception {
		toRDF(structuredAnnotations, null, null, model, instanceNamespace);
	}

	public void toRDF(StructuredAnnotations structuredAnnotations, Resource origin, Property prop, Model model,
			String instanceNamespace) throws Exception {
		if (structuredAnnotations == null) {
			return;
		}

		if (createLog.containsKey(structuredAnnotations)) {
			if (origin != null) {
				Resource res = createLog.get(structuredAnnotations);
				if (prop != null) {
					model.add(origin, prop, res);
				} else {
					Property structuredAnnotationsProp = model
							.createProperty(URISet.OME_STRUCTUREDANNOTATIONS_PROPERTY_URI);
					model.add(origin, structuredAnnotationsProp, res);
				}
			}
			return;
		}

		String genID = getUniqueID("structuredAnnotations", null);
		Resource structuredAnnotationsRes = model.createResource(instanceNamespace + "StructuredAnnotations/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource structuredAnnotationsClsRes = model.createResource(URISet.OME_STRUCTUREDANNOTATIONS_CLASS_URI);
		model.add(structuredAnnotationsRes, typeProp, structuredAnnotationsClsRes);
		createLog.put(structuredAnnotations, structuredAnnotationsRes);
		if (origin != null) {
			if (prop != null) {
				model.add(origin, prop, structuredAnnotationsRes);
			} else {
				Property structuredAnnotationsProp = model
						.createProperty(URISet.OME_STRUCTUREDANNOTATIONS_PROPERTY_URI);
				model.add(origin, structuredAnnotationsProp, structuredAnnotationsRes);
			}
		}

		int booleanAnnotationCnt = structuredAnnotations.sizeOfBooleanAnnotationList();
		for (int m = 0; m < booleanAnnotationCnt; m++) {
			BooleanAnnotation booleanAnnotation = structuredAnnotations.getBooleanAnnotation(m);
			toRDF(booleanAnnotation, structuredAnnotationsRes, model, instanceNamespace);
		}
		int commentAnnotationCnt = structuredAnnotations.sizeOfCommentAnnotationList();
		for (int m = 0; m < commentAnnotationCnt; m++) {
			CommentAnnotation commentAnnotation = structuredAnnotations.getCommentAnnotation(m);
			toRDF(commentAnnotation, structuredAnnotationsRes, model, instanceNamespace);
		}
		int doubleAnnotationCnt = structuredAnnotations.sizeOfDoubleAnnotationList();
		for (int m = 0; m < doubleAnnotationCnt; m++) {
			DoubleAnnotation doubleAnnotation = structuredAnnotations.getDoubleAnnotation(m);
			toRDF(doubleAnnotation, structuredAnnotationsRes, model, instanceNamespace);
		}
		int fileAnnotationCnt = structuredAnnotations.sizeOfFileAnnotationList();
		for (int m = 0; m < fileAnnotationCnt; m++) {
			FileAnnotation fileAnnotation = structuredAnnotations.getFileAnnotation(m);
			toRDF(fileAnnotation, structuredAnnotationsRes, model, instanceNamespace);
		}
		int listAnnotationCnt = structuredAnnotations.sizeOfListAnnotationList();
		for (int m = 0; m < listAnnotationCnt; m++) {
			ListAnnotation listAnnotation = structuredAnnotations.getListAnnotation(m);
			toRDF(listAnnotation, structuredAnnotationsRes, model, instanceNamespace);
		}
		int longAnnotationCnt = structuredAnnotations.sizeOfLongAnnotationList();
		for (int m = 0; m < longAnnotationCnt; m++) {
			LongAnnotation longAnnotation = structuredAnnotations.getLongAnnotation(m);
			toRDF(longAnnotation, structuredAnnotationsRes, model, instanceNamespace);
		}
		int mapAnnotationCnt = structuredAnnotations.sizeOfMapAnnotationList();
		for (int m = 0; m < mapAnnotationCnt; m++) {
			MapAnnotation mapAnnotation = structuredAnnotations.getMapAnnotation(m);
			toRDF(mapAnnotation, structuredAnnotationsRes, model, instanceNamespace);
		}
		int tagAnnotationCnt = structuredAnnotations.sizeOfTagAnnotationList();
		for (int m = 0; m < tagAnnotationCnt; m++) {
			TagAnnotation tagAnnotation = structuredAnnotations.getTagAnnotation(m);
			toRDF(tagAnnotation, structuredAnnotationsRes, model, instanceNamespace);
		}
		int termAnnotationCnt = structuredAnnotations.sizeOfTermAnnotationList();
		for (int m = 0; m < termAnnotationCnt; m++) {
			TermAnnotation termAnnotation = structuredAnnotations.getTermAnnotation(m);
			toRDF(termAnnotation, structuredAnnotationsRes, model, instanceNamespace);
		}
		int timestampAnnotationCnt = structuredAnnotations.sizeOfTimestampAnnotationList();
		for (int m = 0; m < timestampAnnotationCnt; m++) {
			TimestampAnnotation timestampAnnotation = structuredAnnotations.getTimestampAnnotation(m);
			toRDF(timestampAnnotation, structuredAnnotationsRes, model, instanceNamespace);
		}
		int xmlAnnotationCnt = structuredAnnotations.sizeOfXMLAnnotationList();
		for (int m = 0; m < xmlAnnotationCnt; m++) {
			XMLAnnotation xmlAnnotation = structuredAnnotations.getXMLAnnotation(m);
			toRDF(xmlAnnotation, structuredAnnotationsRes, model, instanceNamespace);
		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  ROI
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(ROI roi, Model model, String instanceNamespace) throws Exception {
		toRDF(roi, null, model, instanceNamespace);
	}

	public void toRDF(ROI roi, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (roi == null) {
			return;
		}
		if (createLog.containsKey(roi)) {
			if (origin != null) {
				Property roiProp = model.createProperty(URISet.OME_ROI_PROPERTY_URI);
				Resource res = createLog.get(roi);
				model.add(origin, roiProp, res);
			}
			return;
		}

		String id = roi.getID();
		String genID = getUniqueID("roi", id);
		Resource roiRes = model.createResource(instanceNamespace + "RegionOfInterest/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource roiClsRes = model.createResource(URISet.OME_ROI_CLASS_URI);
		model.add(roiRes, typeProp, roiClsRes);
		createLog.put(roi, roiRes);
		if (origin != null) {
			Property roiProp = model.createProperty(URISet.OME_ROI_PROPERTY_URI);
			model.add(origin, roiProp, roiRes);
		}
		Property idProp = model.createProperty(URISet.DCTERMS_IDENTIFIER_PROPERTY_URI);
		model.add(roiRes, idProp, id);

		// description
		String description = roi.getDescription();
		if (description != null) {
			Property commentProp = model.createProperty(URISet.RDFS_COMMENT_PROPERTY_URI);
			model.add(roiRes, commentProp, description, "en");
		}

		// name
		String name = roi.getName();
		if (name != null) {
			Property labelProp = model.createProperty(URISet.RDFS_LABEL_PROPERTY_URI);
			model.add(roiRes, labelProp, name, "en");
		}

		// union
		Union union = roi.getUnion();
		if (union != null) {
			toRDF(union, roiRes, model, instanceNamespace);
		}

		// link
		// annotation
		int linkedAnnotationCnt = roi.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation linkedAnnotation = roi.getLinkedAnnotation(k);
			toRDF(linkedAnnotation, roiRes, model, instanceNamespace);
		}

//		int linkedFolderCnt = roi.sizeOfLinkedFolderList();
//		for (int k = 0; k < linkedFolderCnt; k++) {
//			Folder linkedFolder = roi.getLinkedFolder(k);
//		}
//
//		int linkedImageCnt = roi.sizeOfLinkedImageList();
//		for (int k = 0; k < linkedImageCnt; k++) {
//			Image linkedImage = roi.getLinkedImage(k);
//		}
//
//		int linkedMicrobeamManupulationCnt = roi.sizeOfLinkedMicrobeamManipulationList();
//		for (int k = 0; k < linkedMicrobeamManupulationCnt; k++) {
//			MicrobeamManipulation linkedMicrobeamManupulation = roi.getLinkedMicrobeamManipulation(k);
//		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  UNION
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Union union, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (union == null) {
			return;
		}
		if (createLog.containsKey(union)) {
			if (origin != null) {
				Property roiProp = model.createProperty(URISet.OME_UNION_PROPERTY_URI);
				Resource res = createLog.get(union);
				model.add(origin, roiProp, res);
			}
			return;
		}

		Resource unionRes = model.createResource();
		Property rdfTypeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource rdfBagClsRes = model.createResource(URISet.RDF_BAG_CLASS_URI);
		model.add(unionRes, rdfTypeProp, rdfBagClsRes);
		createLog.put(union, unionRes);
		if (origin != null) {
			Property unionProp = model.createProperty(URISet.OME_UNION_PROPERTY_URI);
			model.add(origin, unionProp, unionRes);
		}

		int shapeCnt = union.sizeOfShapeList();
		for (int m = 0; m < shapeCnt; m++) {
			Shape shape = union.getShape(m);
			toRDF(shape, unionRes, model, instanceNamespace);
		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  Shape
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Shape shape, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (shape == null) {
			return;
		}

		if (createLog.containsKey(shape)) {
			if (origin != null) {
				Resource res = createLog.get(shape);
				Property shapeProp = model.createProperty(URISet.OME_SHAPE_PROPERTY_URI);
				model.add(origin, shapeProp, res);
			}
			return;
		}

		String id = shape.getID();
		Resource shapeRes = null;
		Property rdfTypeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);

		// ellipse
		if (shape instanceof Ellipse) {
			Ellipse ellipse = (Ellipse) shape;
			String genID = getUniqueID("ellipse", id);
			shapeRes = model.createResource(instanceNamespace + "Ellipse/" + genID);
			Resource ellipseClassRes = model.createResource(URISet.OME_ELLIPSE_CLASS_URI);
			model.add(shapeRes, rdfTypeProp, ellipseClassRes);

			// getRadiusX
			Double radiusX = ellipse.getRadiusX();
			if (radiusX != null) {
				Property radiusXProp = model.createProperty(URISet.OME_RADIUSX_PROPERTY_URI);
				model.add(shapeRes, radiusXProp, model.createTypedLiteral(radiusX.doubleValue()));
			}

			// getRadiusY
			Double radiusY = ellipse.getRadiusY();
			if (radiusY != null) {
				Property radiusYProp = model.createProperty(URISet.OME_RADIUSY_PROPERTY_URI);
				model.add(shapeRes, radiusYProp, model.createTypedLiteral(radiusY.doubleValue()));
			}

			// x
			Double x = ellipse.getX();
			if (x != null) {
				Property xProp = model.createProperty(URISet.OME_X_PROPERTY_URI);
				model.add(shapeRes, xProp, model.createTypedLiteral(x.doubleValue()));
			}

			// y
			Double y = ellipse.getY();
			if (y != null) {
				Property yProp = model.createProperty(URISet.OME_Y_PROPERTY_URI);
				model.add(shapeRes, yProp, model.createTypedLiteral(y.doubleValue()));
			}
		}

		// Label
		if (shape instanceof Label) {
			Label label = (Label) shape;
			String genID = getUniqueID("label", id);
			shapeRes = model.createResource(instanceNamespace + "Label/" + genID);
			Resource labelClassRes = model.createResource(URISet.OME_LABEL_CLASS_URI);
			model.add(shapeRes, rdfTypeProp, labelClassRes);

			// x
			Double x = label.getX();
			if (x != null) {
				Property xProp = model.createProperty(URISet.OME_X_PROPERTY_URI);
				model.add(shapeRes, xProp, model.createTypedLiteral(x.doubleValue()));
			}

			// y
			Double y = label.getY();
			if (y != null) {
				Property yProp = model.createProperty(URISet.OME_Y_PROPERTY_URI);
				model.add(shapeRes, yProp, model.createTypedLiteral(y.doubleValue()));
			}
		}

		// line
		if (shape instanceof Line) {
			Line line = (Line) shape;
			String genID = getUniqueID("line", id);
			shapeRes = model.createResource(instanceNamespace + "Line/" + genID);
			Resource lineClassRes = model.createResource(URISet.OME_LINE_CLASS_URI);
			model.add(shapeRes, rdfTypeProp, lineClassRes);

			// markerStart
			Marker markerStart = line.getMarkerStart();
			if (markerStart != null) {
				String value = markerStart.getValue();
				Resource res = model.createResource(URISet.getMarkerURI(value));
				Property markerProp = model.createProperty(URISet.OME_MARKER_PROPERTY_URI);
				model.add(shapeRes, markerProp, res);
				// prefix
				model.setNsPrefix("marker", URISet.OME_MARKER_CLASS_URI + "#");
			}

			// markerEnd
			Marker markerEnd = line.getMarkerEnd();
			if (markerEnd != null) {
				String value = markerEnd.getValue();
				Resource res = model.createResource(URISet.getMarkerURI(value));
				Property markerProp = model.createProperty(URISet.OME_MARKER_PROPERTY_URI);
				model.add(shapeRes, markerProp, res);
				// prefix
				model.setNsPrefix("marker", URISet.OME_MARKER_CLASS_URI + "#");
			}

			// x1
			Double x1 = line.getX1();
			if (x1 != null) {
				Property x1Prop = model.createProperty(URISet.OME_X1_PROPERTY_URI);
				model.add(shapeRes, x1Prop, model.createTypedLiteral(x1.doubleValue()));
			}

			// x2
			Double x2 = line.getX2();
			if (x2 != null) {
				Property x2Prop = model.createProperty(URISet.OME_X2_PROPERTY_URI);
				model.add(shapeRes, x2Prop, model.createTypedLiteral(x2.doubleValue()));
			}

			// y1
			Double y1 = line.getY1();
			if (y1 != null) {
				Property y1Prop = model.createProperty(URISet.OME_Y1_PROPERTY_URI);
				model.add(shapeRes, y1Prop, model.createTypedLiteral(y1.doubleValue()));
			}

			// y2
			Double y2 = line.getY2();
			if (y2 != null) {
				Property y2Prop = model.createProperty(URISet.OME_Y2_PROPERTY_URI);
				model.add(shapeRes, y2Prop, model.createTypedLiteral(y2.doubleValue()));
			}
		}

		// mask
		if (shape instanceof Mask) {
			Mask mask = (Mask) shape;
			String genID = getUniqueID("mask", id);
			shapeRes = model.createResource(instanceNamespace + "Mask/" + genID);
			Resource maskClassRes = model.createResource(URISet.OME_MASK_CLASS_URI);
			model.add(shapeRes, rdfTypeProp, maskClassRes);

			// bindata
			BinData bindata = mask.getBinData();
			if (bindata != null) {
				toRDF(bindata, shapeRes, model, instanceNamespace);
			}

			// height
			Double height = mask.getHeight();
			if (height != null) {
				Property heightProp = model.createProperty(URISet.OME_HEIGHT_PROPERTY_URI);
				model.add(shapeRes, heightProp, model.createTypedLiteral(height.doubleValue()));
			}

			// width
			Double width = mask.getWidth();
			if (width != null) {
				Property widthProp = model.createProperty(URISet.OME_WIDTH_PROPERTY_URI);
				model.add(shapeRes, widthProp, model.createTypedLiteral(width.doubleValue()));
			}

			// x
			Double x = mask.getX();
			if (x != null) {
				Property xProp = model.createProperty(URISet.OME_X_PROPERTY_URI);
				model.add(shapeRes, xProp, model.createTypedLiteral(x.doubleValue()));
			}

			// y
			Double y = mask.getY();
			if (y != null) {
				Property yProp = model.createProperty(URISet.OME_Y_PROPERTY_URI);
				model.add(shapeRes, yProp, model.createTypedLiteral(y.doubleValue()));
			}
		}

		// point
		if (shape instanceof Point) {
			Point point = (Point) shape;
			String genID = getUniqueID("point", id);
			shapeRes = model.createResource(instanceNamespace + "Point/" + genID);
			Resource pointClassRes = model.createResource(URISet.OME_POINT_CLASS_URI);
			model.add(shapeRes, rdfTypeProp, pointClassRes);

			// x
			Double x = point.getX();
			if (x != null) {
				Property xProp = model.createProperty(URISet.OME_X_PROPERTY_URI);
				model.add(shapeRes, xProp, model.createTypedLiteral(x.doubleValue()));
			}

			// y
			Double y = point.getY();
			if (y != null) {
				Property yProp = model.createProperty(URISet.OME_Y_PROPERTY_URI);
				model.add(shapeRes, yProp, model.createTypedLiteral(y.doubleValue()));
			}
		}

		// polygon
		if (shape instanceof Polygon) {
			Polygon polygon = (Polygon) shape;
			String genID = getUniqueID("polygon", id);
			shapeRes = model.createResource(instanceNamespace + "Polygon/" + genID);
			Resource polygonClassRes = model.createResource(URISet.OME_POLYGON_CLASS_URI);
			model.add(shapeRes, rdfTypeProp, polygonClassRes);

			// points
			String points = polygon.getPoints();
			if (points != null) {
				Property pointsProp = model.createProperty(URISet.OME_POINTS_PROPERTY_URI);
				model.add(shapeRes, pointsProp, points);
			}

		}

		// polyline
		if (shape instanceof Polyline) {
			Polyline polyline = (Polyline) shape;
			String genID = getUniqueID("polyline", id);
			shapeRes = model.createResource(instanceNamespace + "Polyline/" + genID);
			Resource polylineClassRes = model.createResource(URISet.OME_POLYLINE_CLASS_URI);
			model.add(shapeRes, rdfTypeProp, polylineClassRes);

			// markerStart
			Marker markerStart = polyline.getMarkerStart();
			if (markerStart != null) {
				String value = markerStart.getValue();
				Resource res = model.createResource(URISet.getMarkerURI(value));
				Property markerProp = model.createProperty(URISet.OME_MARKER_PROPERTY_URI);
				model.add(shapeRes, markerProp, res);
				// prefix
				model.setNsPrefix("marker", URISet.OME_MARKER_CLASS_URI + "#");
			}

			// markerEnd
			Marker markerEnd = polyline.getMarkerEnd();
			if (markerEnd != null) {
				String value = markerEnd.getValue();
				Resource res = model.createResource(URISet.getMarkerURI(value));
				Property markerProp = model.createProperty(URISet.OME_MARKER_PROPERTY_URI);
				model.add(shapeRes, markerProp, res);
				// prefix
				model.setNsPrefix("marker", URISet.OME_MARKER_CLASS_URI + "#");
			}

			// points
			String points = polyline.getPoints();
			if (points != null) {
				Property pointsProp = model.createProperty(URISet.OME_POINTS_PROPERTY_URI);
				model.add(shapeRes, pointsProp, points);
			}
		}

		// rectangle
		if (shape instanceof Rectangle) {
			Rectangle rectangle = (Rectangle) shape;
			String genID = getUniqueID("rectangle", id);
			shapeRes = model.createResource(instanceNamespace + "Recrtangle/" + genID);
			Resource rectangleClassRes = model.createResource(URISet.OME_RECTANGLE_CLASS_URI);
			model.add(shapeRes, rdfTypeProp, rectangleClassRes);

			// height
			Double height = rectangle.getHeight();
			if (height != null) {
				Property heightProp = model.createProperty(URISet.OME_HEIGHT_PROPERTY_URI);
				model.add(shapeRes, heightProp, model.createTypedLiteral(height.doubleValue()));
			}

			// width
			Double width = rectangle.getWidth();
			if (width != null) {
				Property widthProp = model.createProperty(URISet.OME_WIDTH_PROPERTY_URI);
				model.add(shapeRes, widthProp, model.createTypedLiteral(width.doubleValue()));
			}

			// x
			Double x = rectangle.getX();
			if (x != null) {
				Property xProp = model.createProperty(URISet.OME_X_PROPERTY_URI);
				model.add(shapeRes, xProp, model.createTypedLiteral(x.doubleValue()));
			}

			// y
			Double y = rectangle.getY();
			if (y != null) {
				Property yProp = model.createProperty(URISet.OME_Y_PROPERTY_URI);
				model.add(shapeRes, yProp, model.createTypedLiteral(y.doubleValue()));

			}
		}

		createLog.put(shape, shapeRes);
		if (origin != null) {
			Property shapeProp = model.createProperty(URISet.OME_SHAPE_PROPERTY_URI);
			model.add(origin, shapeProp, shapeRes);
		}

		// color
		Color fillColor = shape.getFillColor();
		createColorBlankNode(fillColor, model, shapeRes, model.createProperty(URISet.OME_COLOR_PROPERTY_URI));

		FillRule fillRule = shape.getFillRule();
		if (fillRule != null) {
			String value = fillRule.getValue();
			Resource res = model.createResource(URISet.getFillRuleURI(value));
			Property fillRuleProp = model.createProperty(URISet.OME_FILLRULE_PROPERTY_URI);
			model.add(shapeRes, fillRuleProp, res);
			// prefix
			model.setNsPrefix("fillRule", URISet.OME_FILLRULE_CLASS_URI + "#");
		}

		// Font
		FontFamily fontFamily = shape.getFontFamily();
		Length fontSize = shape.getFontSize();
		FontStyle fontStyle = shape.getFontStyle();
		if (fontFamily != null || fontSize != null || fontStyle != null) {
			Resource fontRes = model.createResource();
			Resource fontClsRes = model.createResource(URISet.OME_FONT_CLASS_URI);
			Property fontProp = model.createProperty(URISet.OME_FONT_PROPERTY_URI);
			model.add(shapeRes, fontProp, fontRes);
			model.add(fontRes, rdfTypeProp, fontClsRes);

			// fontFamily
			if (fontFamily != null) {
				String value = fontFamily.getValue();
				Resource res = model.createResource(URISet.getFontFamilyURI(value));
				Property fontFamilyProp = model.createProperty(URISet.OME_FONTFAMILY_PROPERTY_URI);
				model.add(fontRes, fontFamilyProp, res);
				// prefix
				model.setNsPrefix("fontFamily", URISet.OME_FONTFAMILY_CLASS_URI + "#");
			}

			// fontSize
			createLengthBlankNodeValue(fontSize, Shape.getFontSizeUnitXsdDefault(), model, fontRes,
					model.createProperty(URISet.OME_FONTSIZE_PROPERTY_URI),
					model.createResource(URISet.PATO_SIZE_CLASS_URI));
		}

		// locked
		Boolean locked = shape.getLocked();
		if (locked != null) {
			Property lockedProp = model.createProperty(URISet.OME_LOCKED_PROPERTY_URI);
			model.add(shapeRes, lockedProp, model.createTypedLiteral(locked.booleanValue()));
		}

		// strokeColor
		Color strokeColor = shape.getStrokeColor();
		createColorBlankNode(strokeColor, model, shapeRes, model.createProperty(URISet.OME_STROKECOLOR_PROPERTY_URI));

		// strokeDashArray
		String strokeDashArray = shape.getStrokeDashArray();
		if (strokeDashArray != null) {
			Property strokeDashArrayProp = model.createProperty(URISet.OME_STROKEDASHARRAY_PROPERTY_URI);
			model.add(shapeRes, strokeDashArrayProp, strokeDashArray);
		}

		// strokeWidth
		Length strokeWidth = shape.getStrokeWidth();
		createLengthBlankNodeValue(strokeWidth, Shape.getStrokeWidthUnitXsdDefault(), model, shapeRes,
				model.createProperty(URISet.OME_STROKEWIDTH_PROPERTY_URI),
				model.createResource(URISet.PATO_WIDTH_CLASS_URI));

		// text
		String text = shape.getText();
		if (text != null) {
			Property textProp = model.createProperty(URISet.OME_TEXT_PROPERTY_URI);
			model.add(shapeRes, textProp, text);
		}

		// theC
		NonNegativeInteger theC = shape.getTheC();
		if (theC != null) {
			int theCVal = theC.getValue().intValue();
			Property theCProp = model.createProperty(URISet.OME_THEC_PROPERTY_URI);
			model.add(shapeRes, theCProp,
					model.createTypedLiteral(theCVal, URISet.XSD_NONNEGATIVEINTEGER_DATATYPE_URI));
		}

		// theT
		NonNegativeInteger theT = shape.getTheT();
		if (theT != null) {
			int theTVal = theT.getValue().intValue();
			Property theTProp = model.createProperty(URISet.OME_THET_PROPERTY_URI);
			model.add(shapeRes, theTProp,
					model.createTypedLiteral(theTVal, URISet.XSD_NONNEGATIVEINTEGER_DATATYPE_URI));
		}

		// theZ
		NonNegativeInteger theZ = shape.getTheZ();
		if (theZ != null) {
			int theZVal = theZ.getValue().intValue();
			Property theZProp = model.createProperty(URISet.OME_THEZ_PROPERTY_URI);
			model.add(shapeRes, theZProp,
					model.createTypedLiteral(theZVal, URISet.XSD_NONNEGATIVEINTEGER_DATATYPE_URI));
		}

		// transform
		AffineTransform transform = shape.getTransform();
		if (transform != null) {
			toRDF(transform, shapeRes, model, instanceNamespace);
		}

		// union
		Union union = shape.getUnion();
		if (union != null) {
			toRDF(union, shapeRes, model, instanceNamespace);
		}

		// link
		// annotation
		int linkedAnnotationCnt = shape.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation linkedAnnotation = shape.getLinkedAnnotation(k);
			toRDF(linkedAnnotation, shapeRes, model, instanceNamespace);
		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  Transform
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(AffineTransform transform, Resource origin, Model model, String instanceNamespace)
			throws Exception {
		if (transform == null) {
			return;
		}
		// create as Blank Node
		Resource transformRes = model.createResource();
		Resource transformClsRes = model.createResource(URISet.OME_AFFINETRANSFORM_CLASS_URI);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Property transformProp = model.createProperty(URISet.OME_TRANSFORM_PROPERTY_URI);
		model.add(origin, transformProp, transformRes);
		model.add(transformRes, typeProp, transformClsRes);

		// a00
		Double a00 = transform.getA00();
		Property a00Prop = model.createProperty(URISet.OME_A00_PROPERTY_URI);
		model.add(transformRes, a00Prop, model.createTypedLiteral(a00.doubleValue()));

		// a01
		Double a01 = transform.getA01();
		Property a01Prop = model.createProperty(URISet.OME_A01_PROPERTY_URI);
		model.add(transformRes, a01Prop, model.createTypedLiteral(a01.doubleValue()));

		// a02
		Double a02 = transform.getA02();
		Property a02Prop = model.createProperty(URISet.OME_A02_PROPERTY_URI);
		model.add(transformRes, a02Prop, model.createTypedLiteral(a02.doubleValue()));

		// a10
		Double a10 = transform.getA10();
		Property a10Prop = model.createProperty(URISet.OME_A10_PROPERTY_URI);
		model.add(transformRes, a10Prop, model.createTypedLiteral(a10.doubleValue()));

		// a11
		Double a11 = transform.getA11();
		Property a11Prop = model.createProperty(URISet.OME_A11_PROPERTY_URI);
		model.add(transformRes, a11Prop, model.createTypedLiteral(a11.doubleValue()));

		// a12
		Double a12 = transform.getA12();
		Property a12Prop = model.createProperty(URISet.OME_A12_PROPERTY_URI);
		model.add(transformRes, a12Prop, model.createTypedLiteral(a12.doubleValue()));
	}

//-------------------------------------------------------------------------------------------------------------------
//  BinaryOnly
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(BinaryOnly binaryOnly, Model model, String instanceNamespace) throws Exception {
		if (binaryOnly == null) {
			return;
		}
		if (createLog.containsKey(binaryOnly)) {
			return;
		}

		String genID = getUniqueID("binaryOnly", null);
		Resource binaryOnlyRes = model.createResource(instanceNamespace + "BinaryOnly/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource binaryOnlyClsRes = model.createResource(URISet.OME_BINARYONLY_CLASS_URI);
		model.add(binaryOnlyRes, typeProp, binaryOnlyClsRes);
		createLog.put(binaryOnly, binaryOnlyRes);

		String metadataFile = binaryOnly.getMetadataFile();
		Property metadataFileProp = model.createProperty(URISet.OME_METADATAFILE_PROPERTY_URI);
		model.add(binaryOnlyRes, metadataFileProp, metadataFile);

		String uuid = binaryOnly.getUUID();
		Property uuidProp = model.createProperty(URISet.OME_UUID_PROPERTY_URI);
		model.add(binaryOnlyRes, uuidProp, uuid);
	}

//-------------------------------------------------------------------------------------------------------------------
//  LightPath
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(LightPath lightPath, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (lightPath == null) {
			return;
		}
		if (createLog.containsKey(lightPath)) {
			if (origin != null) {
				Property lightPathProp = model.createProperty(URISet.OME_LIGHTPATH_PROPERTY_URI);
				Resource res = createLog.get(lightPath);
				model.add(origin, lightPathProp, res);
			}
			return;
		}

		String genID = getUniqueID("lightPath", null);
		Resource lightPathRes = model.createResource(instanceNamespace + "LightPath/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource lightPathClsRes = model.createResource(URISet.OME_LIGHTPATH_CLASS_URI);
		model.add(lightPathRes, typeProp, lightPathClsRes);
		createLog.put(lightPath, lightPathRes);
		if (origin != null) {
			Property lightPathProp = model.createProperty(URISet.OME_LIGHTPATH_PROPERTY_URI);
			model.add(origin, lightPathProp, lightPathRes);
		}

		// link
		// dichroic
		Dichroic linkedDichroic = lightPath.getLinkedDichroic();
		toRDF(linkedDichroic, lightPathRes, model, instanceNamespace);

		// annotation
		int linkedAnnotationCnt = lightPath.sizeOfLinkedAnnotationList();
		for (int k = 0; k < linkedAnnotationCnt; k++) {
			Annotation linkedAnnotation = lightPath.getLinkedAnnotation(k);
			toRDF(linkedAnnotation, lightPathRes, model, instanceNamespace);
		}

		// filter (emission filter)
		int linkedEmissionFilterCnt = lightPath.sizeOfLinkedEmissionFilterList();
		Property emissionFilterProp = model.createProperty(URISet.OME_EMISSIONFILTER_PROPERTY_URI);
		for (int k = 0; k < linkedEmissionFilterCnt; k++) {
			Filter linkedEmissionFilter = lightPath.getLinkedEmissionFilter(k);
			toRDF(linkedEmissionFilter, lightPathRes, emissionFilterProp, model, instanceNamespace);
		}

		// filter (excitation filter)
		int linkedExcitationFilterCnt = lightPath.sizeOfLinkedExcitationFilterList();
		Property excitationFilterProp = model.createProperty(URISet.OME_EXCITATIONFILTER_PROPERTY_URI);
		for (int k = 0; k > linkedExcitationFilterCnt; k++) {
			Filter linkedExcitationFilter = lightPath.getLinkedExcitationFilter(k);
			toRDF(linkedExcitationFilter, lightPathRes, excitationFilterProp, model, instanceNamespace);
		}
	}

//-------------------------------------------------------------------------------------------------------------------
//  Plane
//-------------------------------------------------------------------------------------------------------------------

	public void toRDF(Plane plane, Resource origin, Model model, String instanceNamespace) throws Exception {
		if (plane == null) {
			return;
		}
		if (createLog.containsKey(plane)) {
			if (origin != null) {
				Property planeProp = model.createProperty(URISet.OME_PLANE_PROPERTY_URI);
				Resource res = createLog.get(plane);
				model.add(origin, planeProp, res);
			}
			return;
		}

		String genID = getUniqueID("plane", null);
		Resource planeRes = model.createResource(instanceNamespace + "Plane/" + genID);
		Property typeProp = model.createProperty(URISet.RDF_TYPE_PROERTY_URI);
		Resource planeClsRes = model.createResource(URISet.OME_PLANE_CLASS_URI);
		model.add(planeRes, typeProp, planeClsRes);
		createLog.put(plane, planeRes);
		if (origin != null) {
			Property planeProp = model.createProperty(URISet.OME_PLANE_PROPERTY_URI);
			model.add(origin, planeProp, planeRes);
		}

		// deltaT
		Time deltaT = plane.getDeltaT();
		createTimeBlankNodeValue(deltaT, Plane.getDeltaTUnitXsdDefault(), model, planeRes,
				model.createProperty(URISet.OME_POSITIONX_PROPERTY_URI),
				model.createResource(URISet.PATO_POSITION_CLASS_URI));

		// exposureTime
		Time exposureTime = plane.getExposureTime();
		createTimeBlankNodeValue(exposureTime, Plane.getExposureTimeUnitXsdDefault(), model, planeRes,
				model.createProperty(URISet.OME_EXPOSURETIME_PROPERTY_URI),
				model.createResource(URISet.PATO_TIME_CLASS_URI));

		// hashSha1
		String hashSha1 = plane.getHashSHA1();
		if (hashSha1 != null) {
			Property hashSha1Prop = model.createProperty(URISet.OME_HASHSHA1_PROPERTY_URI);
			model.add(planeRes, hashSha1Prop, hashSha1);
		}

		// pixels
		Pixels pixels = plane.getPixels();
		if (pixels != null) {
			toRDF(pixels, planeRes, model, instanceNamespace);
		}

		// positionX
		Length positionX = plane.getPositionX();
		createLengthBlankNodeValue(positionX, Plane.getPositionXUnitXsdDefault(), model, planeRes,
				model.createProperty(URISet.OME_POSITIONX_PROPERTY_URI),
				model.createResource(URISet.PATO_POSITION_CLASS_URI));

		// positionY
		Length positionY = plane.getPositionY();
		createLengthBlankNodeValue(positionY, Plane.getPositionYUnitXsdDefault(), model, planeRes,
				model.createProperty(URISet.OME_POSITIONY_PROPERTY_URI),
				model.createResource(URISet.PATO_POSITION_CLASS_URI));

		// positionZ
		Length positionZ = plane.getPositionZ();
		createLengthBlankNodeValue(positionZ, Plane.getPositionZUnitXsdDefault(), model, planeRes,
				model.createProperty(URISet.OME_POSITIONZ_PROPERTY_URI),
				model.createResource(URISet.PATO_POSITION_CLASS_URI));

		// theC
		NonNegativeInteger theC = plane.getTheC();
		if (theC != null) {
			int theCVal = theC.getValue().intValue();
			Property theCProp = model.createProperty(URISet.OME_THEC_PROPERTY_URI);
			model.add(planeRes, theCProp,
					model.createTypedLiteral(theCVal, URISet.XSD_NONNEGATIVEINTEGER_DATATYPE_URI));
		}

		// theT
		NonNegativeInteger theT = plane.getTheT();
		if (theT != null) {
			int theTVal = theT.getValue().intValue();
			Property theTProp = model.createProperty(URISet.OME_THET_PROPERTY_URI);
			model.add(planeRes, theTProp,
					model.createTypedLiteral(theTVal, URISet.XSD_NONNEGATIVEINTEGER_DATATYPE_URI));
		}

		// theZ
		NonNegativeInteger theZ = plane.getTheZ();
		if (theZ != null) {
			int theZVal = theZ.getValue().intValue();
			Property theZProp = model.createProperty(URISet.OME_THEZ_PROPERTY_URI);
			model.add(planeRes, theZProp,
					model.createTypedLiteral(theZVal, URISet.XSD_NONNEGATIVEINTEGER_DATATYPE_URI));
		}
	}
}

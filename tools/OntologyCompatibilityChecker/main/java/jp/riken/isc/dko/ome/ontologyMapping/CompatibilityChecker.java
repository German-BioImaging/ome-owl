/*
 * #%L
 * OME-OWL contology compatibility checker
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


package jp.riken.isc.dko.ome.ontologyMapping;

import java.util.HashSet;

import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.NodeIterator;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;

public class CompatibilityChecker {


	public CompatibilityChecker(){
	}
	
	static public void main(String[] args) throws Exception{
		CompatibilityChecker mc = new CompatibilityChecker();

		// usage
		// args[0]: reference of base ontology
		// args[1]: ontology data format of the base ontology
		// args[2]: reference of entended ontology
		// args[3]: ontology data format of the extended ontology
		
		
		Model model_base = mc.readOntology(args[0], args[1]);
		Model model_ext = mc.readOntology(args[2], args[3]);
		
		System.out.println(mc.analyse(model_base, model_ext));
	}

	
	private Model readOntology(String reference, String type) throws Exception{
		Model model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		model.read(reference, type);
		return model;
	}


	public  Mapping analyse(Model model_base, Model model_ext) throws Exception{
		// list classes
		Property type_base = model_base.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
		Resource owl_Class_base = model_base.createResource("http://www.w3.org/2002/07/owl#Class");
		

		HashSet<Resource> classes_base = new HashSet<Resource>();
		HashSet<Resource> subClasses_ext = new HashSet<Resource>();
		HashSet<Resource> additionalPropClasses_ext = new HashSet<Resource>();
		HashSet<Resource> otherClasses_ext = new HashSet<Resource>();
		
		ResIterator iter = model_base.listSubjectsWithProperty(type_base, owl_Class_base);
		while (iter.hasNext()) {
			Resource res = iter.next();
//			System.out.println(res.getURI());
			classes_base.add(res);
		}

		iter = model_ext.listSubjectsWithProperty(type_base, owl_Class_base);
		while (iter.hasNext()) {
			Resource res = iter.next();
//			System.out.println(res.getURI());
			Resource[] results = getSuperClasses(model_ext, res);
			if( results == null || results.length == 0 ) {
				// check if 
				otherClasses_ext.add(res);
			}else {
				boolean flag = false;
				for( int k = 0; k < results.length; k++ ) {
//					System.out.println("  " + results[k].getURI());
					if(classes_base.contains(results[k])) {
						flag = true;
						break;
					}
				}
				if( flag ) {
						subClasses_ext.add(res);
				}else {
						otherClasses_ext.add(res);
				}
					
			}
		}

		// check range
//		System.out.println(otherClasses_ext.size());
//		System.out.println(subClasses_ext.size());
		
		Resource owl_ObjectProperty_base = model_base.createResource("http://www.w3.org/2002/07/owl#ObjectProperty");
		Property domain_base = model_base.createProperty("http://www.w3.org/2000/01/rdf-schema#domain");
		Property range_base = model_base.createProperty("http://www.w3.org/2000/01/rdf-schema#range");

		
		iter = model_ext.listSubjectsWithProperty(type_base, owl_ObjectProperty_base);
		while( iter.hasNext() ) {
			Resource prop = iter.next();
			// get domain
			NodeIterator domainIter = model_ext.listObjectsOfProperty(prop, domain_base);
			boolean domFlag = false;
			while( domainIter.hasNext() ) {
				RDFNode node = domainIter.next();
				if( node.isResource() ) {
					Resource domClass = node.asResource();
					if( classes_base.contains(domClass) || subClasses_ext.contains(domClass)) {
						domFlag = true;
					}
				}
			}
			if( domFlag ) {
				// check range
				NodeIterator rangeIter = model_ext.listObjectsOfProperty(prop, range_base);
				while( rangeIter.hasNext()) {
					RDFNode rangeNode = rangeIter.next();
					if( rangeNode.isResource () ) {
						Resource rangeCls = rangeNode.asResource();
						HashSet<Resource> rangeSubClasses = getSubClasses(model_ext, rangeCls);
						for(Resource rangeSubClass: rangeSubClasses) {
							if( otherClasses_ext.contains(rangeSubClass)) {
								otherClasses_ext.remove(rangeSubClass);
								additionalPropClasses_ext.add(rangeSubClass);
							}
						}
					}
				}
			}
		}
		
		int otherClassesSize = 0;
		while( otherClassesSize != otherClasses_ext.size()) {
			otherClassesSize = otherClasses_ext.size();
			iter = model_ext.listSubjectsWithProperty(type_base, owl_ObjectProperty_base);
			while( iter.hasNext() ) {
				Resource prop = iter.next();
				// get domain
				NodeIterator domainIter = model_ext.listObjectsOfProperty(prop, domain_base);
				boolean domFlag = false;
				while( domainIter.hasNext() ) {
					RDFNode node = domainIter.next();
					if( node.isResource() ) {
						Resource domClass = node.asResource();
						if( additionalPropClasses_ext.contains(domClass) ) {
							domFlag = true;
						}else {
							HashSet<Resource> domSubClasses = getSubClasses(model_ext, domClass);
							for(Resource domSubClass: domSubClasses) {
								if( additionalPropClasses_ext.contains(domSubClass)) {
									domFlag = true;
								}
							}
						}
					}
				}
				if( domFlag ) {
					// check range
					NodeIterator rangeIter = model_ext.listObjectsOfProperty(prop, range_base);
					while( rangeIter.hasNext()) {
						RDFNode rangeNode = rangeIter.next();
						if( rangeNode.isResource () ) {
							Resource rangeCls = rangeNode.asResource();
							HashSet<Resource> rangeSubClasses = getSubClasses(model_ext, rangeCls);
							for(Resource rangeSubClass: rangeSubClasses) {
								if( otherClasses_ext.contains(rangeSubClass)) {
									otherClasses_ext.remove(rangeSubClass);
									additionalPropClasses_ext.add(rangeSubClass);
								}
							}
						}
					}
				}
			}
		}
		
		
		
		HashSet<Resource> otherClasses_tmp = new HashSet<Resource>();
		// Abstract Parent Class
		for( Resource cls: otherClasses_ext) {
			HashSet<Resource> sClasses = getSubClasses(model_ext, cls);
			if( sClasses != null && sClasses.size() != 0 ) {
//				otherClasses_ext.remove(cls);
			}else {
				otherClasses_tmp.add(cls);
			}
		}
		otherClasses_ext = otherClasses_tmp;
		
		
		if( otherClasses_ext.size() == 0 ) {
			if( additionalPropClasses_ext.size() == 0 ) {
				return Mapping.bijective;
			}else {
				return Mapping.injection;
			}
		}

//		System.out.println(otherClasses_ext.size());
//
//		for(Resource otherCls: otherClasses_ext) {
//			System.out.println(otherCls.getURI());
//		}
		
		return Mapping.none;
	}
	
	
	public HashSet<Resource> getSubClasses(Model model, Resource superClass) throws Exception{
		Property subClassOf = model.createProperty("http://www.w3.org/2000/01/rdf-schema#subClassOf");
		ResIterator resIter = model.listSubjectsWithProperty(subClassOf, superClass);
		HashSet<Resource> results = new HashSet<Resource>();
		results.add(superClass);
		while( resIter.hasNext() ) {
			Resource subj = resIter.next();
			if( !results.contains(subj)) {
				results.add(subj);
				HashSet<Resource> sClasses = getSubClasses(model, subj);
				for(Resource cls: sClasses) {
					results.add(cls);
				}
			}
		}
		return results;
	}
	
	
	public boolean isSubclass(Model model, Resource superClass, Resource subClass) throws Exception{
		Property subClassOf = model.createProperty("http://www.w3.org/2000/01/rdf-schema#subClassOf");
		ResIterator resIter = model.listSubjectsWithProperty(subClassOf, superClass);
		boolean flag = false;
		while( resIter.hasNext() ) {
			Resource subj = resIter.next();
			if( subj.getURI().equals(subClass.getURI())) {
				return true;
			}else {
				flag = isSubclass(model, subj, subClass);
				if( flag ) {
					return true;
				}
			}
			
		}
		return flag;
		
	}
	
	
	public Resource[] getSuperClasses(Model model, Resource baseClass) throws Exception{
		Property subClassOf = model.createProperty("http://www.w3.org/2000/01/rdf-schema#subClassOf");
		NodeIterator nodeIter = model.listObjectsOfProperty(baseClass, subClassOf);
		HashSet<Resource> results = new HashSet<Resource>();
		while(nodeIter.hasNext()) {
			RDFNode clsNode = nodeIter.next();
			if( clsNode.isResource()) {
				Resource cls = clsNode.asResource();
				Resource[] resList = getSuperClasses(model, cls);
				if( resList == null || resList.length == 0 ) {
					results.add(cls);
				}else {
					for( int i = 0; i < resList.length; i++ ) {
						Resource[] tempResList = getSuperClasses(model, resList[i]);
						for( int k = 0; k < tempResList.length; k++ ) {
							results.add(tempResList[k]);
						}
					}
				}
			}
		}
		return results.toArray(new Resource[0]);
	}
	
	
	
	static public enum MappingRelation{
		asIs,
		subClassOf
	};

	
	static public enum Mapping {
		bijective,
		injection,
		none
	};
}

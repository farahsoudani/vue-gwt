if (!window.VueGWT)
	window.VueGWT = {};
if (!window.VueGWT.tools)
	window.VueGWT.tools = {};

const tools = window.VueGWT.tools;

/**
 * This object provides methods to integrate Java in Vue.js world
 * @author Adrien Baron
 */
tools.createInstanceForVueClass = function (extendedVueClass) {
	return new extendedVueClass();
};
tools.extendVueClass = function (vueClassToExtend, vueComponentOptions) {
	return vueClassToExtend.extend(vueComponentOptions);
};
tools.getFunctionBody = function (myFunction) {
	// Get content between first { and last }
	const m = myFunction.toString().match(/\{([\s\S]*)\}/m)[1];
	// Strip comments
	return m.replace(/^\s*\/\/.*$/mg, '').trim();
};
tools.javaArrayToJsArray = function (javaArray) {
	// No conversion is needed, but Java compiler won't agree otherwise
	return javaArray;
};
tools.wrapMethodWithBefore = function (object, methodName, beforeMethodCall) {
	const proto = Object.getPrototypeOf(object);
	const originalFunc = proto[methodName];
	if (originalFunc === null)
		console.error("Attempting to wrap a non existing method", object, methodName);

	proto[methodName] = function () {
		beforeMethodCall(object, methodName, arguments);
		return originalFunc.apply(this, arguments);
	};
};
tools.wrapMethodWithAfter = function (object, methodName, afterMethodCall) {
	const proto = Object.getPrototypeOf(object);
	const originalFunc = proto[methodName];
	if (originalFunc === null)
		console.error("Attempting to wrap a non existing method", object, methodName);

	proto[methodName] = function () {
		var result = originalFunc.apply(this, arguments);
		afterMethodCall(object, methodName, result, arguments);
		return result;
	};
};
tools.wrapMethod = function (object, methodName, beforeMethodCall, afterMethodCall) {
	const proto = Object.getPrototypeOf(object);
	const originalFunc = proto[methodName];
	if (originalFunc === null)
		console.error("Attempting to wrap a non existing method", object, methodName);

	proto[methodName] = function () {
		beforeMethodCall(object, methodName, arguments);
		const result = originalFunc.apply(this, arguments);
		afterMethodCall(object, methodName, result, arguments);
		return result;
	};
};
tools.mergeVueConstructorWithJavaComponent = function (extendedVueConstructor, jsTypeConstructor) {
	const vueProto = extendedVueConstructor.prototype;
	let jsTypeProto = jsTypeConstructor.prototype;
	// Copy from the @JsType VueComponent prototype
	for (let protoProp in jsTypeProto) {
		if (jsTypeProto.hasOwnProperty(protoProp) && !vueProto.hasOwnProperty(protoProp)) {
			vueProto[protoProp] = jsTypeProto[protoProp];
		}
	}
	// Also copy from our VueComponent prototype
	jsTypeProto = Object.getPrototypeOf(jsTypeProto);
	for (let protoProp in jsTypeProto) {
		if (jsTypeProto.hasOwnProperty(protoProp) && !vueProto.hasOwnProperty(protoProp)) {
			vueProto[protoProp] = jsTypeProto[protoProp];
		}
	}
};

/**
 * Vue GWT plugin that call the Java Constructor once our Component is created
 */
Vue.use(function (Vue) {
	Vue.mixin({
		created: function () {
			if (!this.$options.vuegwt$vueComponentJsTypeConstructor || this.vuegwt$constructorApplied)
				return;

			this.vuegwt$constructorApplied = true;
			this.$options.vuegwt$vueComponentJsTypeConstructor.apply(this);
		}
	})
});
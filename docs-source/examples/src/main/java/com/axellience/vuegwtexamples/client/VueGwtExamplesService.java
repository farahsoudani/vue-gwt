package com.axellience.vuegwtexamples.client;

import com.axellience.vuegwt.client.Vue;
import com.axellience.vuegwt.client.VueGWT;
import com.axellience.vuegwt.client.component.VueComponent;
import com.axellience.vuegwt.client.tools.JsTools;
import com.axellience.vuegwt.client.vue.VueFactory;
import com.axellience.vuegwt.client.vue.VueJsConstructor;
import com.axellience.vuegwtexamples.client.examples.bindinlinestyle.BindInlineStyleComponent;
import com.axellience.vuegwtexamples.client.examples.buttonplusone.ButtonPlusOneComponent;
import com.axellience.vuegwtexamples.client.examples.canhide.CanHideComponent;
import com.axellience.vuegwtexamples.client.examples.counterwithevent.CounterWithEventComponent;
import com.axellience.vuegwtexamples.client.examples.evennumbers.EvenNumbersComponent;
import com.axellience.vuegwtexamples.client.examples.exclamation.ExclamationComponent;
import com.axellience.vuegwtexamples.client.examples.extendjavacomponent.ChildComponent;
import com.axellience.vuegwtexamples.client.examples.extendsjscomponent.ChildJavaComponent;
import com.axellience.vuegwtexamples.client.examples.focus.FocusDirectiveComponent;
import com.axellience.vuegwtexamples.client.examples.greet.GreetComponent;
import com.axellience.vuegwtexamples.client.examples.hiwhat.HiWhatComponent;
import com.axellience.vuegwtexamples.client.examples.instanciatejscomponent.FullJsWithMethodsComponentFactory;
import com.axellience.vuegwtexamples.client.examples.kitten.KittenComponent;
import com.axellience.vuegwtexamples.client.examples.link.LinkComponent;
import com.axellience.vuegwtexamples.client.examples.melisandre.MelisandreComponent;
import com.axellience.vuegwtexamples.client.examples.message.MessageComponent;
import com.axellience.vuegwtexamples.client.examples.parent.ParentComponent;
import com.axellience.vuegwtexamples.client.examples.propdefaultvalue.ParentPropDefaultValueComponent;
import com.axellience.vuegwtexamples.client.examples.recursive.RecursiveComponent;
import com.axellience.vuegwtexamples.client.examples.reverse.ReverseComponent;
import com.axellience.vuegwtexamples.client.examples.shareddatamodel.SharedDataModelComponent;
import com.axellience.vuegwtexamples.client.examples.simplelink.SimpleLinkComponent;
import com.axellience.vuegwtexamples.client.examples.simplerender.RenderAppComponent;
import com.axellience.vuegwtexamples.client.examples.simpletodolist.SimpleTodoListComponent;
import com.axellience.vuegwtexamples.client.examples.todolist.TodoListComponent;
import com.axellience.vuegwtexamples.client.examples.todotext.TodoTextComponent;
import com.axellience.vuegwtexamples.client.examples.todotextcomputed.TodoTextComputedComponent;
import com.axellience.vuegwtexamples.client.examples.tree.TreeComponent;
import com.axellience.vuegwtexamples.client.examples.vforonobject.VForOnObjectComponent;
import com.axellience.vuegwtexamples.client.examples.vforonobjectwithkey.VForOnObjectWithKeyComponent;
import com.axellience.vuegwtexamples.client.examples.vforonobjectwithkeyandindex.VForOnObjectWithKeyAndIndexComponent;
import com.axellience.vuegwtexamples.client.examples.vforwithindex.VForWithIndexComponent;
import com.axellience.vuegwtexamples.client.examples.vforwithrange.VForWithRangeComponent;
import com.axellience.vuegwtexamples.client.examples.vonwithdomevent.VOnWithDOMEventComponent;
import com.google.gwt.dom.client.Document;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(namespace = JsPackage.GLOBAL)
public class VueGwtExamplesService
{
    public static void initExamples()
    {
        if (Document.get().getElementById("fullJsComponent") != null)
        {
            VueJsConstructor vueClass = (VueJsConstructor) JsTools.getWindow().get("FullJsComponent");
            VueComponent myComponent = vueClass.instantiate();
            myComponent.$mount("#fullJsComponent");
        }

        ExampleInjector exampleInjector = DaggerExampleInjector.builder().build();

        addExample("simpleLinkComponent", SimpleLinkComponent.class);
        addExample("linkComponent", LinkComponent.class);
        addExample("canHideComponent", CanHideComponent.class);
        addExample("simpleTodoListComponent", SimpleTodoListComponent.class);
        addExample("exclamationComponent", ExclamationComponent.class);
        addExample("messageComponent", MessageComponent.class);
        addExample("parentComponent", ParentComponent.class);
        addExample("todoListComponent", TodoListComponent.class);
        addExample("melisandreComponent", MelisandreComponent.class);
        addExample("kittenComponent", KittenComponent.class);
        addExample("reverseComponent", ReverseComponent.class);
        addExample("vForWithIndexComponent", VForWithIndexComponent.class);
        addExample("vForWithRangeComponent", VForWithRangeComponent.class);
        addExample("vForOnObjectComponent", VForOnObjectComponent.class);
        addExample("vForOnObjectWithKeyComponent", VForOnObjectWithKeyComponent.class);
        addExample("vForOnObjectWithKeyAndIndexComponent",
            VForOnObjectWithKeyAndIndexComponent.class);
        addExample("evenNumbersComponent", EvenNumbersComponent.class);
        addExample("bindInlineStyleComponent", BindInlineStyleComponent.class);
        addExample("buttonPlusOneComponent", ButtonPlusOneComponent.class);
        addExample("greetComponent", GreetComponent.class);
        addExample("hiWhatComponent", HiWhatComponent.class);
        addExample("vOnWithDOMEventComponent", VOnWithDOMEventComponent.class);
        addExample("todoTextComponent", TodoTextComponent.class);
        addExample("todoTextComputedComponent", TodoTextComputedComponent.class);
        addExample("sharedDataModelComponent1", SharedDataModelComponent.class);
        addExample("sharedDataModelComponent2", SharedDataModelComponent.class);
        addExample("sharedDataModelComponent3", SharedDataModelComponent.class);
        addExample("counterWithEventComponent", CounterWithEventComponent.class);
        addExample("treeComponent", TreeComponent.class);
        addExample("recursiveComponent", RecursiveComponent.class);
        addExample("focusDirectiveComponent", FocusDirectiveComponent.class);
        addExample("renderAppComponent", RenderAppComponent.class);
        addExample("extendJavaComponent", ChildComponent.class);
        addExample("extendJsComponent", ChildJavaComponent.class);
        addExample("fullJsWithMethodsComponent", FullJsWithMethodsComponentFactory.get());
        addExample("propDefaultValueComponent", ParentPropDefaultValueComponent.class);
        addExample("gotQuotesComponent", exampleInjector.gotQuoteComponentFactory());
    }

    private static void addExample(String exampleId, Class<? extends VueComponent> exampleVueClass)
    {
        addExample(exampleId, VueGWT.getFactory(exampleVueClass));
    }

    private static void addExample(String exampleId,
        VueFactory<? extends VueComponent> exampleVueFactory)
    {
        // If we find the containing div for this example, we instantiate it
        if (Document.get().getElementById(exampleId) != null)
        {
            VueComponent exampleInstance = Vue.attach("#" + exampleId, exampleVueFactory);
            JsTools.getWindow().set(exampleId, exampleInstance);
        }
    }
}

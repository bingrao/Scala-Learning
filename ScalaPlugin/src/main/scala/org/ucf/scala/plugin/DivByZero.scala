package org.ucf.scala.plugin

import scala.tools.nsc
import nsc.Global
import nsc.Phase
import nsc.plugins.Plugin
import nsc.plugins.PluginComponent

class DivByZero(val global: Global) extends  Plugin {
  import global._

  override val name:String = "DivByZero"
  override val description: String = "Check for division by zero"
  override val components: List[PluginComponent] = List[PluginComponent](Component)

  private object Component extends PluginComponent {
    override val global: DivByZero.this.global.type = DivByZero.this.global
    override val runsAfter: List[String] = List[String]("RefChecks")
    override val runsBefore: List[String] = List[String]("Before Checks")

    override val phaseName: String = DivByZero.this.name

    def newPhase(_prev: Phase) = new DivByZeroPhase(_prev)

    class DivByZeroPhase(prev: Phase) extends StdPhase(prev){
      override def name: String = DivByZero.this.name

      override def apply(unit: CompilationUnit): Unit = {
        for( tree@Apply(Select(rcvr,nme.DIV),List(Literal(Constant(0)))) <- unit.body;
             if rcvr.tpe <:< definitions.IntClass.tpe){
          unit.error(tree.pos, "Definitely division by Zero")
        }
      }
    }
  }


}

package ee.cone.c4gate

import ee.cone.c4actor._

trait SSEApp extends DataDependenciesApp {
  def indexFactory: IndexFactory
  def sseConfig: SSEUI
  //
  private lazy val httpPostByConnectionJoin = indexFactory.createJoinMapIndex(new HttpPostByConnectionJoin)
  private lazy val sseConnectionJoin = indexFactory.createJoinMapIndex(new SSEConnectionJoin(sseConfig))
  override def dataDependencies: List[DataDependencyTo[_]] =
    httpPostByConnectionJoin :: sseConnectionJoin :: super.dataDependencies
}
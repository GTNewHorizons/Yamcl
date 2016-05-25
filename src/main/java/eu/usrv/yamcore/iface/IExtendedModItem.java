
package eu.usrv.yamcore.iface;


public interface IExtendedModItem<T>
{
  T getConstructedItem();

  String getUnlocalizedNameForRegistration();

  String getCreativeTabName();
}

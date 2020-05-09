declare var window: any;

export class DynamicEnvironment {
  public get urlMantenedor() {
    return window.config.url;
  }
}
